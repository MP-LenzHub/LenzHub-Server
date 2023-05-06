package com.example.renzhubserver.user;

import com.example.renzhubserver.config.BaseException;
import com.example.renzhubserver.follow.FollowRepository;
import com.example.renzhubserver.post.model.Post;
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
import org.springframework.stereotype.Service;

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
//    public UserInfoResDto getUserProfile(String userId) {
//        User user = userRepository.findByUserId(userId);
//        Long followCount = followRepository.countByFromUser(user.getId());  // 팔로우 수 (following)
//        int savedPostCount =
//        List<Post> savedPost =
//        int createdPostCount =
//        List<Post> createdPost =
//        return new UserInfoResDto(
//                user.getUserId(),
//                user.getName(),
//                user.getProfileImg(),
//                followCount,
//                );
//    }
}
