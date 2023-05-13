package com.example.renzhubserver.post.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

@Getter
@Setter
@AllArgsConstructor
public class PostMainResDto {
    private Page<Post> postList;
}
