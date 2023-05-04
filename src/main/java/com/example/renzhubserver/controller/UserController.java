package com.example.renzhubserver.controller;

import com.example.renzhubserver.dto.BaseResponseDto;
import com.example.renzhubserver.service.UserService;
import com.example.renzhubserver.user.User;
import com.example.renzhubserver.user.dto.request.UserRegisterRequestDto;
import com.example.renzhubserver.user.dto.response.UserRegisterResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class UserController {
    private final UserService userService;
    // 회원가입
    @PostMapping("api/user")
    public BaseResponseDto<UserRegisterResponseDto> register(@RequestBody UserRegisterRequestDto user) {
        System.out.println("POST test");

        User newUser = new User(user.getName(), user.getUserId(), user.getPassword(), user.getGrade());

        UserRegisterResponseDto userRegisterResponseDto = userService.join(newUser);
        return new BaseResponseDto<>(userRegisterResponseDto);
    }
}
