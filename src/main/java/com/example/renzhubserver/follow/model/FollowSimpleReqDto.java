package com.example.renzhubserver.follow.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class FollowSimpleReqDto {
    private String toUserId;
    private String fromUserId;
}
