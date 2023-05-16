package com.example.renzhubserver.post.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class PostCreateReqDto {
    private String title;
    private int price;
    private String category_name;
    private Date date;
    MultipartFile beforeImage;
    MultipartFile afterImage;
}
