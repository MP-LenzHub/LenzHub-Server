package com.example.renzhubserver.post;

import com.example.renzhubserver.config.BaseException;
import com.example.renzhubserver.fileUpload.S3Service;
import com.example.renzhubserver.lenz.LenzRepository;
import com.example.renzhubserver.lenz.model.Lenz;
import com.example.renzhubserver.lenz.model.LenzBasicInfoDto;
import com.example.renzhubserver.like.LikeRepository;
import com.example.renzhubserver.like.model.Like;
import com.example.renzhubserver.post.model.*;
import com.example.renzhubserver.user.UserRepository;
import com.example.renzhubserver.user.model.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springdoc.api.ErrorMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional //Transaction 안에서 모든게 일어나야한다.
@RequiredArgsConstructor
public class PostService {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private LikeRepository likeRepository;
    @Autowired
    private LenzRepository lenzRepository;
    @Autowired
    private final S3Service s3Uploader;

    public PostBasicResDto readAllPosts(int page, int size){
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "id"));
        Page<Post> posts = postRepository.findAll(pageRequest);
        return new PostBasicResDto(getPostBasicInfo(posts.getContent()));
    }
    // 유저가 작성한 post
    public PostBasicResDto readUserPosts(Long userId, int page, int size){
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "id"));
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found"));
        Page<Post> posts = postRepository.findByUser(user, pageRequest);
        return new PostBasicResDto(getPostBasicInfo(posts.getContent()));
    }
    // 유저가 좋아요한 post
    public PostBasicResDto readLikePost(Long userId, int page, int size){
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "user_id"));
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found"));
        Page<Like> posts = likeRepository.findByUserId(user.getId(), pageable);
        List<Post> likedPosts = posts.stream()
                .map(Like::getPost)
                .collect(Collectors.toList());
        return new PostBasicResDto(getPostBasicInfo(likedPosts));
    }
    public PostMessageResDto createLikePost(Long userId, Long postId){
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found"));
        Post post = postRepository.findById(postId).orElseThrow(() -> new IllegalArgumentException("User not found"));
        if(likeRepository.existsByUserAndPost(user, post))
            return new PostMessageResDto("이미 쫗아용!");
        post.addLikedBy(user);
        postRepository.save(post);
        return new PostMessageResDto("쫗아용!");
    }
    public PostMessageResDto createUnlikeBoard(Long userId, Long postId){
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found"));
        Post post = postRepository.findById(postId).orElseThrow(() -> new IllegalArgumentException("User not found"));
        if(!likeRepository.existsByUserAndPost(user, post))
            return new PostMessageResDto("쫗아요 안했는딩");
        post.removeLikedBy(user);
        postRepository.save(post);
        return new PostMessageResDto("안쫗아용!");
    }
    public PostMessageResDto createPost(Long userId, PostCreateReqDto postCreateReqDto, MultipartFile beforeImage, MultipartFile afterImage) throws IOException {
        // 이미지 업로드
        String beforeImg = s3Uploader.upload(beforeImage);
        String afterImg = s3Uploader.upload(afterImage);
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found"));
        Lenz lenz = new Lenz(postCreateReqDto.getLenzBasicInfoDto().getBrightness(), postCreateReqDto.getLenzBasicInfoDto().getContrast(), postCreateReqDto.getLenzBasicInfoDto().getBackLight(), postCreateReqDto.getLenzBasicInfoDto().getSaturate(), postCreateReqDto.getLenzBasicInfoDto().getGrain(), postCreateReqDto.getLenzBasicInfoDto().getTemperature(), postCreateReqDto.getLenzBasicInfoDto().getBrightness(), postCreateReqDto.getLenzBasicInfoDto().getDistortion());
        Post post = new Post(postCreateReqDto.getTitle(), postCreateReqDto.getPrice(), postCreateReqDto.getDescription(), postCreateReqDto.getCategory_name(), beforeImage.getOriginalFilename(), afterImage.getOriginalFilename(), beforeImg, afterImg, user, lenz);
//        post.addLikedBy(user);
        postRepository.save(post);
        return new PostMessageResDto("이미지 업로드 되었습니다.");
    }
    public PostMessageResDto deleteBoard(Long postId, String postName){
        Post post = postRepository.findById(postId).orElseThrow(() -> new IllegalArgumentException("User not found"));
        // storage 삭제
        s3Uploader.delete(postName);
        postRepository.delete(post);
        return new PostMessageResDto("삭제 됐슴니다");
    }
    public PostBasicInfo readPost(Long postId){
        Post post = postRepository.findById(postId).orElseThrow(() -> new IllegalArgumentException("User not found"));
        User user = post.getUser();
        return new PostBasicInfo(post.getId(), user.getId(), post.getTitle(), user.getName(),  user.getProfileImg(), post.getDescription(), post.getPrice(), post.getCategory(), post.getLikes().size(), post.getCreatedDate(), post.getBeforeFileName(), post.getAfterFileName(), post.getBeforeImg(), post.getAfterImg(), getLenzBasicInfoDto(post.getLenz()));
    }
    public PostBasicResDto readCategoryPost(String category){
        List<Post> posts = postRepository.findByCategory(category);
        return new PostBasicResDto(getPostBasicInfo(posts));
    }
    public PostBasicResDto readFreeCategoryPost(String category){
        List<Post> posts = postRepository.findByFreeCategory(category);
        return new PostBasicResDto(getPostBasicInfo(posts));
    }
    public PostBasicResDto readNotFreeCategoryPost(String category){
        List<Post> posts = postRepository.findByNotFreeCategory(category);
        return new PostBasicResDto(getPostBasicInfo(posts));
    }
    public PostBasicResDto readTitlePost(String title){
        List<Post> posts = postRepository.findByTitleContaining(title);
        return new PostBasicResDto(getPostBasicInfo(posts));
    }
    private List<PostBasicInfo> getPostBasicInfo(List<Post> posts){
        List<PostBasicInfo> postBasicInfos = new ArrayList<>();
        posts.forEach(post ->
                postBasicInfos.add(new PostBasicInfo(post.getId(), post.getUser().getId(), post.getTitle(), post.getUser().getName(), post.getUser().getProfileImg(), post.getDescription(), post.getPrice(), post.getCategory(), post.getLikes().size(), post.getCreatedDate(), post.getBeforeFileName(), post.getAfterFileName(), post.getBeforeImg(), post.getAfterImg(), getLenzBasicInfoDto(post.getLenz()))));
        return postBasicInfos;
    }
    private LenzBasicInfoDto getLenzBasicInfoDto(Lenz lenz){
        return new LenzBasicInfoDto(lenz.getBrightness(), lenz.getContrast(), lenz.getBackLight(), lenz.getSaturate(), lenz.getGrain(), lenz.getTemperature(), lenz.getSharpen(), lenz.getDistortion());
    }
}
