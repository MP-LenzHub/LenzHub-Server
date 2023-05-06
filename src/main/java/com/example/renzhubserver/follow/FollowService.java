package com.example.renzhubserver.follow;

import com.example.renzhubserver.follow.model.*;
import com.example.renzhubserver.user.UserRepository;
import com.example.renzhubserver.user.model.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional //Transaction 안에서 모든게 일어나야한다.
@RequiredArgsConstructor
public class FollowService {
    @Autowired
    private FollowRepository followRepository;
    @Autowired
    private UserRepository userRepository;

    public FollowSimpleResDto save(FollowSimpleReqDto followSimpleReqDto){
        User toUser = userRepository.findByUserId(followSimpleReqDto.getToUserId());
        User fromUser = userRepository.findByUserId(followSimpleReqDto.getFromUserId());
        Follow follow = new Follow(toUser.getId(), fromUser.getId());
        System.out.println(toUser.getId() + fromUser.getId());
        followRepository.save(follow);
        return new FollowSimpleResDto("팔로우 등록되었음");
    }

    public FollowListDto getFollowingList(String userID){
        User user = userRepository.findByUserId(userID);
        return new FollowListDto(followRepository.findAllByToUser(user.getId()));
    }

    public FollowDeleteResDto deleteFollowRelation(FollowSimpleReqDto followSimpleReqDto){
        User toUser = userRepository.findByUserId(followSimpleReqDto.getToUserId());
        User fromUser = userRepository.findByUserId(followSimpleReqDto.getFromUserId());
        followRepository.deleteByToUserAndFromUser(toUser.getId(), fromUser.getId());
        return new FollowDeleteResDto("삭제되었습니다.");
    }
}
