package com.example.renzhubserver.post;

import com.example.renzhubserver.post.model.Post;
import com.example.renzhubserver.user.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    Page<Post> findByUser(User user, PageRequest pageRequest);
    List<Post> findByCategory(String category);
    @Query(value = "SELECT post FROM Post post WHERE post.price = 0 AND post.category = :category")
    List<Post> findByFreeCategory(@Param("category") String category);
    @Query(value = "SELECT post FROM Post post WHERE post.price >= 1 AND post.category = :category")
    List<Post> findByNotFreeCategory(@Param("category") String category);
}
