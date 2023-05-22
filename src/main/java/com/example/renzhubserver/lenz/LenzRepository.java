package com.example.renzhubserver.lenz;

import com.example.renzhubserver.lenz.model.Lenz;
import com.example.renzhubserver.post.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LenzRepository extends JpaRepository<Lenz, Long> {
}
