package com.example.renzhubserver.post.model;

import com.example.renzhubserver.user.model.UserGrade;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Setter
@Data
@NoArgsConstructor
public class Post {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private String author;
    private int price;
    private String category_name;
    private Date date;
    private int likes;
    private String profileImg;

    @Builder
    public Post(String title, String author, int price, String category_name, Date date, int likes, String profileImg){
        this.title =title;
        this.author = author;
        this.price = price;
        this.category_name = category_name;
        this.date = date;
        this.likes = likes;
        this.profileImg = profileImg;
    }
}
