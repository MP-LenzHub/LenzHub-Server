package com.example.renzhubserver.post;

import com.example.renzhubserver.post.model.Post;
import com.example.renzhubserver.user.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
    Page<Post> findByUser(User user, PageRequest pageRequest);
}
