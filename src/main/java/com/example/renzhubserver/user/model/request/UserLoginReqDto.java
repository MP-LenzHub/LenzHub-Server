package com.example.renzhubserver.user.model.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class UserLoginReqDto {
    String userId;
    String password;
}
