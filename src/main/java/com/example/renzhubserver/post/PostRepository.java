package com.example.renzhubserver.post;

import com.example.renzhubserver.post.model.Post;
import com.example.renzhubserver.user.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    Page<Post> findByUser(User user, PageRequest pageRequest);
    List<Post> findByCategory(String category);
}
