package com.example.renzhubserver.user.model.response;

import com.example.renzhubserver.post.model.Post;
import com.example.renzhubserver.post.model.PostBasicResDto;
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
    private Long followCount;
    private PostBasicResDto likedPosts;
    private PostBasicResDto createPosts;
}
