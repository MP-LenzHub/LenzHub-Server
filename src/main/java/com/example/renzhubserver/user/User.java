package com.example.renzhubserver.user;

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
    private String grade;
    private String profileImg;

    @Builder
    public User(String name, String password, String grade, String profileImg) {
        this.name = name;
        this.password = password;
        this.grade = grade;
        this.profileImg = profileImg;
    }
}
