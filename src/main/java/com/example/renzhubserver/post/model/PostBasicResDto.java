package com.example.renzhubserver.post.model;

import com.example.renzhubserver.user.model.response.UserBasicInfoResDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class PostBasicResDto {
    private List<PostBasicInfo> postList;
}
