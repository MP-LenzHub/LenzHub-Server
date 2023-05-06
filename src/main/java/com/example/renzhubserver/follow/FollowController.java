package com.example.renzhubserver.follow;

import com.example.renzhubserver.config.BaseResponseDto;
import com.example.renzhubserver.follow.model.FollowSimpleReqDto;
import com.example.renzhubserver.follow.model.FollowSimpleResDto;
import com.example.renzhubserver.follow.model.FollowDeleteResDto;
import com.example.renzhubserver.follow.model.FollowListDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/follow")
public class FollowController {
    @Autowired
    private FollowService followService;

    // 팔로우 리스트
    @GetMapping("/{userId}")
    public BaseResponseDto<FollowListDto> getFollowList(@PathVariable("userId") String userId){
        FollowListDto followListDto = followService.getFollowingList(userId);
        return new BaseResponseDto<>(followListDto);
    }

    // 팔로우 취소
    @PatchMapping("/")
    public BaseResponseDto<FollowDeleteResDto> delete(@RequestBody FollowSimpleReqDto followSimpleReqDto){
        FollowDeleteResDto followDeleteResDto = followService.deleteFollowRelation(followSimpleReqDto);
        return new BaseResponseDto<>(followDeleteResDto);
    }

    // 팔로우 추가
    @PostMapping("/")
    public BaseResponseDto<FollowSimpleResDto> save(@RequestBody FollowSimpleReqDto followSimpleReqDto){
        FollowSimpleResDto followSimpleResDto = followService.save(followSimpleReqDto);
        return new BaseResponseDto<>(followSimpleResDto);
    }
}

