package com.example.renzhubserver.user;

import com.example.renzhubserver.config.BaseException;
import com.example.renzhubserver.user.model.User;
import com.example.renzhubserver.user.model.UserGrade;
import com.example.renzhubserver.user.model.request.UserLoginReqDto;
import com.example.renzhubserver.user.model.request.UserRegisterReqDto;
import com.example.renzhubserver.user.model.response.UserLoginResDto;
import com.example.renzhubserver.user.model.response.UserRegisterResDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.example.renzhubserver.config.BaseResponseStatus.USERS_EMPTY;
import static com.example.renzhubserver.config.BaseResponseStatus.USERS_EXIST_USER_ID;

@Service
@Transactional //Transaction 안에서 모든게 일어나야한다.
@RequiredArgsConstructor
public class UserService {
    @Autowired
    private UserRepository userRepository;

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
}
