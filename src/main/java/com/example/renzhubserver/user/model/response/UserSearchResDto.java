package com.example.renzhubserver.user.model.response;

import com.example.renzhubserver.user.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserSearchResDto {
    private List<User> userInfoResDto;
}
