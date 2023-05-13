package com.example.renzhubserver.user;

import com.example.renzhubserver.config.BaseException;
import com.example.renzhubserver.config.BaseResponseDto;
import com.example.renzhubserver.user.model.request.UserLoginReqDto;
import com.example.renzhubserver.user.model.request.UserRegisterReqDto;
import com.example.renzhubserver.user.model.response.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private final UserService userService;

    //로그인
    @GetMapping("/login")
    public BaseResponseDto<UserLoginResDto> login(UserLoginReqDto user){
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
    // 회원탈퇴
    @PatchMapping("/{userId}")
    public BaseResponseDto<UserDeleteResDto> delete(@PathVariable("userId") String userId){
        try{
            UserDeleteResDto userDeleteResDto = userService.delete(userId);
            return new BaseResponseDto<>(userDeleteResDto);
        }catch (BaseException exception){
            return new BaseResponseDto<>((exception.getStatus()));
        }
    }
    // 유저검색
    @GetMapping("/{userId}")
    public BaseResponseDto<UserSearchResDto> search(@PathVariable("userId") String userId){
        UserSearchResDto userSearchResDto = userService.search(userId);
        return new BaseResponseDto<>(userSearchResDto);
    }

    // 유저 프로필 api
//    @GetMapping("/profile/{userId}")
//    public BaseResponseDto<UserInfoResDto> getProfile(@PathVariable("userId") String userId){
//        UserInfoResDto userInfoResDto = userService.getUserProfile(userId);
//        return new BaseResponseDto<>(userInfoResDto);
//    }

}
