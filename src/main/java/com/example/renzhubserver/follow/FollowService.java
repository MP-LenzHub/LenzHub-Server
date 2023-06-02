package com.example.renzhubserver.follow;

import com.example.renzhubserver.follow.model.*;
import com.example.renzhubserver.like.model.Like;
import com.example.renzhubserver.post.model.Post;
import com.example.renzhubserver.user.UserRepository;
import com.example.renzhubserver.user.model.User;
import com.example.renzhubserver.user.model.response.UserBasicInfoResDto;
import com.example.renzhubserver.user.model.response.UserFollowDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional //Transaction 안에서 모든게 일어나야한다.
@RequiredArgsConstructor
public class FollowService {
    @Autowired
    private FollowRepository followRepository;
    @Autowired
    private UserRepository userRepository;

    public FollowSimpleResDto save(FollowSimpleReqDto followSimpleReqDto){
        Follow follow = new Follow(followSimpleReqDto.getToUserId(), followSimpleReqDto.getFromUserId());
        followRepository.save(follow);
        return new FollowSimpleResDto("팔로우 등록되었음");
    }

    public FollowListDto getFollowingList(Long userId){
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found"));
        List<UserBasicInfoResDto> followList = followRepository.findAllByToUser(user.getId());
        List<UserFollowDto> userFollowDtoList = new ArrayList<>();
        followList.forEach(userBasicInfoResDto -> {
            Long id = userBasicInfoResDto.getUserId();
            User u = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("User not found"));
            userFollowDtoList.add(new UserFollowDto(u.getId(), u.getName(), u.getGrade(), u.getPosts().size()));
        });
        return new FollowListDto(userFollowDtoList);
    }
    public FollowDeleteResDto deleteFollowRelation(FollowSimpleReqDto followSimpleReqDto){
        followRepository.deleteByToUserAndFromUser(followSimpleReqDto.getToUserId(), followSimpleReqDto.getFromUserId());
        return new FollowDeleteResDto("삭제되었습니다.");
    }
}
