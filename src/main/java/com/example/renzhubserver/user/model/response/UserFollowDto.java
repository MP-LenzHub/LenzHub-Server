package com.example.renzhubserver.user.model.response;

import com.example.renzhubserver.user.model.UserGrade;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserFollowDto {
    private String userName;
    private UserGrade grade;
    private int filterCount;
}
