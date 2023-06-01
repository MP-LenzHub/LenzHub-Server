package com.example.renzhubserver.like;

import com.example.renzhubserver.like.model.Like;
import com.example.renzhubserver.post.model.Post;
import com.example.renzhubserver.user.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Like, Long> {
    // 유저가 좋아요를 누른경우
    boolean existsByUserAndPost(User user, Post post);
    Page<Like> findByUserId(Long userId, Pageable pageable);
}
