package com.example.renzhubserver.post;

import com.example.renzhubserver.like.LikeRepository;
import com.example.renzhubserver.like.model.Like;
import com.example.renzhubserver.post.model.Post;
import com.example.renzhubserver.post.model.PostBasicInfo;
import com.example.renzhubserver.post.model.PostBasicResDto;
import com.example.renzhubserver.post.model.PostMessageResDto;
import com.example.renzhubserver.user.UserRepository;
import com.example.renzhubserver.user.model.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    public PostBasicResDto readAllPosts(int page, int size){
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "id"));
        Page<Post> posts = postRepository.findAll(pageRequest);
        return new PostBasicResDto(getPostBasicInfo(posts));
    }
    // 유저가 작성한 post
    public PostBasicResDto readUserPosts(String userId, int page, int size){
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "id"));
        User user = userRepository.findByUserId(userId);
        Page<Post> posts = postRepository.findByUser(user, pageRequest);
        return new PostBasicResDto(getPostBasicInfo(posts));
    }
    // 유저가 좋아요한 post
    public PostBasicResDto readLikePost(Long userId, int page, int size){
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found"));
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "id"));
        Page<Post> posts = likeRepository.findByUser(user, pageRequest);
        return new PostBasicResDto(getPostBasicInfo(posts));
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
    private List<PostBasicInfo> getPostBasicInfo(Page<Post> posts){
        List<PostBasicInfo> postBasicInfos = new ArrayList<>();
        posts.getContent().forEach(post -> postBasicInfos.add(new PostBasicInfo(post.getId(), post.getTitle(), post.getUser().getName(), post.getPrice(), post.getCategory_name(), post.getDate(), post.getProfileImg())));
        return postBasicInfos;
    }
}
