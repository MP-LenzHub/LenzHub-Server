package com.example.renzhubserver.user;

import com.example.renzhubserver.user.model.User;
import com.example.renzhubserver.user.model.response.UserInfoResDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserId(String userId);
    //존재하면 true
    boolean existsByUserId(String userId);
    //존재하면 true
    boolean existsByUserIdAndPassword(String userId, String password);
    //성공하면 1, 실패하면 0
    Long deleteByUserId(String userId);
    //유저 검색
    List<User> findByUserIdContaining(String userId);
}

