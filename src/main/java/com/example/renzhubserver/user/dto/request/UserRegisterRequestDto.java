package com.example.renzhubserver.user.dto.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class UserRegisterRequestDto {
    private String name;
    private String userId;
    private String password;
    private String grade;
}
