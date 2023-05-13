package com.example.renzhubserver.post.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class PostBasicInfo {
    private long id;
    private String title;
    private String userName;
    private int price;
    private String category_name;
    private Date date;
    private String profileImg;
}
