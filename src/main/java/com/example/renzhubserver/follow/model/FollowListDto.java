package com.example.renzhubserver.follow.model;

import com.example.renzhubserver.user.model.response.UserBasicInfoResDto;
import com.example.renzhubserver.user.model.response.UserFollowDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class FollowListDto {
    private List<UserFollowDto> followList;
}
