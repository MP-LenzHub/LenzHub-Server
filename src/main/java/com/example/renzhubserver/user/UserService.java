package com.example.renzhubserver.user;

import com.example.renzhubserver.config.BaseException;
import com.example.renzhubserver.follow.FollowRepository;
import com.example.renzhubserver.like.model.Like;
import com.example.renzhubserver.post.PostRepository;
import com.example.renzhubserver.post.model.Post;
import com.example.renzhubserver.post.model.PostBasicInfo;
import com.example.renzhubserver.post.model.PostBasicResDto;
import com.example.renzhubserver.user.model.User;
import com.example.renzhubserver.user.model.UserGrade;
import com.example.renzhubserver.user.model.response.UserInfoResDto;
import com.example.renzhubserver.user.model.request.UserLoginReqDto;
import com.example.renzhubserver.user.model.request.UserRegisterReqDto;
import com.example.renzhubserver.user.model.response.UserDeleteResDto;
import com.example.renzhubserver.user.model.response.UserLoginResDto;
import com.example.renzhubserver.user.model.response.UserRegisterResDto;
import com.example.renzhubserver.user.model.response.UserSearchResDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.example.renzhubserver.config.BaseResponseStatus.*;

@Service
@Transactional //Transaction 안에서 모든게 일어나야한다.
@RequiredArgsConstructor
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private FollowRepository followRepository;

    /**
     * 로그인
     */
    public UserLoginResDto login(UserLoginReqDto user) throws BaseException{
        if(!userRepository.existsByUserIdAndPassword(user.getUserId(), user.getPassword()))
            throw new BaseException(USERS_EMPTY);
        return new UserLoginResDto(true);
    }

    /**
     * 회원가입
     */
    public UserRegisterResDto join(UserRegisterReqDto user) throws BaseException {
        if(userRepository.existsByUserId(user.getUserId()))
            throw new BaseException(USERS_EXIST_USER_ID);
        User newUser = new User(user.getName(), user.getUserId(), user.getPassword(), UserGrade.Basic, null);
        userRepository.save(newUser);
        return new UserRegisterResDto(true);
    }

    /**
     * 회원탈퇴
     */
    public UserDeleteResDto delete(String userId) throws BaseException{
        if(userRepository.deleteByUserId(userId) == 0)
            throw new BaseException(USER_DELETE);
        return new UserDeleteResDto("회원탈퇴에 성공했습니다.");
    }

    /**
     * 유저검색
     */
    public UserSearchResDto search(String userId){
        List<User> users = userRepository.findByUserIdContaining(userId);
        return new UserSearchResDto(users);
    }

    /**
     * 유저 프로필
     */
    public UserInfoResDto getUserProfile(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found"));
        Long followCount = followRepository.countByFromUser(user.getId());  // 팔로우 수 (following)
        List<Post> likedPost = new ArrayList<>();
        user.getLikes().forEach(like -> like.getPost());
        PostBasicResDto likedPosts = new PostBasicResDto(getPostBasicInfo(likedPost));
        PostBasicResDto createdPost = new PostBasicResDto(getPostBasicInfo(user.getPosts()));
        return new UserInfoResDto(
                    user.getUserId(),
                    user.getName(),
                    user.getProfileImg(),
                    followCount,
                    likedPosts,
                    createdPost
                );
    }
    private List<PostBasicInfo> getPostBasicInfo(List<Post> posts){
        List<PostBasicInfo> postBasicInfos = new ArrayList<>();
        posts.forEach(post -> postBasicInfos.add(new PostBasicInfo(post.getId(), post.getTitle(), post.getUser().getName(), post.getPrice(), post.getCategory_name(), post.getDate(), post.getBeforeImg(), post.getAfterImg())));
        return postBasicInfos;
    }
}
