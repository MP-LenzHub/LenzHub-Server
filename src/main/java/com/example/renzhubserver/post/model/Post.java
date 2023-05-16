package com.example.renzhubserver.post.model;

import com.example.renzhubserver.like.model.Like;
import com.example.renzhubserver.user.model.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    private int price;
    private String category_name;
    private Date date;
    private String beforeImg;
    private String afterImg;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Like> likes = new ArrayList<>();
    @Builder
    public Post(String title,int price, String category_name, Date date, String beforeImg, String afterImg){
        this.title =title;
        this.price = price;
        this.category_name = category_name;
        this.date = date;
        this.beforeImg = beforeImg;
        this.afterImg = afterImg;
    }

    public void addLikedBy(User user){
        this.likes.add(new Like(user, this));
    }

    public void removeLikedBy(User user){
        this.likes.remove(new Like(user, this));
    }

}
