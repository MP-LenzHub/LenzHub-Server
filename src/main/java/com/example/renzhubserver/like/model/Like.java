package com.example.renzhubserver.like.model;

import com.example.renzhubserver.post.model.Post;
import com.example.renzhubserver.user.model.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "likes")
@NoArgsConstructor
public class Like {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "post_id", referencedColumnName = "id")
    private Post post;

    @Builder
    public Like(User user, Post post){
        this.user = user;
        this.post = post;
    }
}
