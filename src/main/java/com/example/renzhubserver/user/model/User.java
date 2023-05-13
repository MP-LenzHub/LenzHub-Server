package com.example.renzhubserver.user.model;

import com.example.renzhubserver.like.model.Like;
import com.example.renzhubserver.post.model.Post;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Data
@NoArgsConstructor
public class User {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String userId;
    private String password;
    private UserGrade grade;
    private String profileImg;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Post> posts = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Like> likes = new ArrayList<>();

    @Builder
    public User(String name, String userId, String password, UserGrade grade, String profileImg) {
        this.name = name;
        this.userId = userId;
        this.password = password;
        this.grade = grade;
        this.profileImg = profileImg;
    }
}
