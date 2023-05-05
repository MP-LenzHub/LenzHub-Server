package com.example.renzhubserver.user;

import com.example.renzhubserver.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    //존재하면 true
    boolean existsByUserId(String userId);
    //존재하면 true
    boolean existsByUserIdAndPassword(String userId, String password);
}

