package com.example.renzhubserver.user.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserBasicInfoResDto {
    private String name;
    private Long userId;
}
