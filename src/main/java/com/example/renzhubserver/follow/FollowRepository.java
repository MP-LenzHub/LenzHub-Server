package com.example.renzhubserver.follow;

import com.example.renzhubserver.follow.model.Follow;
import com.example.renzhubserver.user.model.response.UserBasicInfoResDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FollowRepository extends JpaRepository<Follow, Follow.PK> {
    @Query(value = "select new com.example.renzhubserver.user.model.response.UserBasicInfoResDto(u.name, u.id) from Follow f INNER JOIN User u ON f.toUser = u.id where f.fromUser = :userId")
    List<Follow> findAllByFromUser(@Param("userId") Long userId);  // 나를 팔로우하는 관계를 가져옴
    @Query(value = "select new com.example.renzhubserver.user.model.response.UserBasicInfoResDto(u.name, u.id) from Follow f INNER JOIN User u ON f.fromUser = u.id where f.toUser = :userId")
    List<UserBasicInfoResDto> findAllByToUser(@Param("userId") Long userId);	  // 내가 팔로우한 관계를 가져옴
    Long countByToUser(Long userId);    // 팔로워 수 (follower)
    Long countByFromUser(Long userId);  // 팔로우 수 (following)
    Long deleteAllByToUser(Long userId);
    Long deleteAllByFromUser(Long userId);

    Long deleteByToUserAndFromUser(Long toUserId, Long fromUserId);
}
