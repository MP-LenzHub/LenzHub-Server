package com.example.renzhubserver.user;

import com.example.renzhubserver.config.BaseException;
import com.example.renzhubserver.user.model.User;
import com.example.renzhubserver.config.BaseResponseDto;
import com.example.renzhubserver.user.model.request.UserLoginReqDto;
import com.example.renzhubserver.user.model.request.UserRegisterReqDto;
import com.example.renzhubserver.user.model.response.UserLoginResDto;
import com.example.renzhubserver.user.model.response.UserRegisterResDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    //로그인
    @PostMapping("/login")
    public BaseResponseDto<UserLoginResDto> login(@RequestBody UserLoginReqDto user){
        try{
            UserLoginResDto userLoginResDto = userService.login(user);
            return new BaseResponseDto<>(userLoginResDto);
        } catch (BaseException exception){
            return new BaseResponseDto<>((exception.getStatus()));
        }
    }

    // 회원가입
    @PostMapping("/register")
    public BaseResponseDto<UserRegisterResDto> register(@RequestBody UserRegisterReqDto user) {
        try {
            UserRegisterResDto userRegisterResponseDto = userService.join(user);
            return new BaseResponseDto<>(userRegisterResponseDto);
        } catch (BaseException exception){
            return new BaseResponseDto<>((exception.getStatus()));
        }
    }
}
