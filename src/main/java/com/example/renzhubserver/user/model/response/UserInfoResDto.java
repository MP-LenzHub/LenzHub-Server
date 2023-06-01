package com.example.renzhubserver.user.model.response;

import com.example.renzhubserver.post.model.Post;
import com.example.renzhubserver.post.model.PostBasicResDto;
import com.example.renzhubserver.user.model.UserGrade;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class UserInfoResDto {
    private String userId;
    private String name;
    private String profileImgUrl;
    private UserGrade userGrade;
    private Long followCounts;
    private int likedCounts;
    private PostBasicResDto likedPosts;
    private PostBasicResDto createPosts;
}
