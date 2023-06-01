package com.example.renzhubserver.post.model;

import com.example.renzhubserver.lenz.model.Lenz;
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
public class Post extends BaseTimeEntity {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private int price;
    private String description;
    private String category;
    private String beforeFileName;
    private String beforeImg;
    private String afterFileName;
    private String afterImg;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Like> likes = new ArrayList<>();
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "lenz_id", referencedColumnName = "id")
    private Lenz lenz;
    @Builder
    public Post(String title, int price, String description, String category, String beforeFileName, String afterFileName, String beforeImg, String afterImg, User user, Lenz lenz){
        this.title =title;
        this.price = price;
        this.description = description;
        this.category = category;
        this.beforeFileName = beforeFileName;
        this.afterFileName = afterFileName;
        this.beforeImg = beforeImg;
        this.afterImg = afterImg;
        this.user = user;
        this.lenz = lenz;
    }
    public void addLikedBy(User user){
        this.likes.add(new Like(user, this));
    }
    public void removeLikedBy(User user){
        this.likes.remove(new Like(user, this));
    }

}
