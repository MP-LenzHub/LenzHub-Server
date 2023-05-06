package com.example.renzhubserver.user.model.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class UserRegisterReqDto {
    private String name;
    private String userId;
    private String password;
    private int followCount;
}
