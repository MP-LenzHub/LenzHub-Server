package com.example.renzhubserver.post.model;

import com.example.renzhubserver.lenz.model.LenzBasicInfoDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class PostBasicInfo {
    private long id;
    private long userIdx;
    private String title;
    private String userName;
    private String profileImg;
    private String description;
    private int price;
    private String category_name;
    private int LikedCount;
    private LocalDateTime date;
    private String beforeFileName;
    private String afterFileName;
    private String beforeImg;
    private String afterImg;
    private LenzBasicInfoDto lenzBasicInfoDto;
}
