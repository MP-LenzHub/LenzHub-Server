package com.example.renzhubserver.user.model;

import jakarta.persistence.*;
import lombok.*;

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

    @Builder
    public User(String name, String userId, String password, UserGrade grade, String profileImg) {
        this.name = name;
        this.userId = userId;
        this.password = password;
        this.grade = grade;
        this.profileImg = profileImg;
    }
}
