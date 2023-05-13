package com.example.renzhubserver.post;

import com.example.renzhubserver.config.BaseResponseDto;
import com.example.renzhubserver.post.model.PostBasicResDto;
import com.example.renzhubserver.post.model.PostMessageResDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/board")
public class PostController {
    @Autowired
    private final PostService postService;

    // main 화면 게시물 조회
    @GetMapping("/")
    public BaseResponseDto<PostBasicResDto> readAllPosts(@RequestParam(defaultValue = "0") int page,
                                                         @RequestParam(defaultValue = "10") int size){
        PostBasicResDto postBasicResDto = postService.readAllPosts(page, size);
        return new BaseResponseDto<>(postBasicResDto);
    }

    // 게시물 추가

    // 게시물 삭제

    // 유저가 작성한 게시물 조회
    @GetMapping("/{userId}")
    public BaseResponseDto<PostBasicResDto> readUserPosts(@PathVariable String userId,
                                                          @RequestParam(defaultValue = "0") int page,
                                                          @RequestParam(defaultValue = "10") int size){
        PostBasicResDto postBasicResDto = postService.readUserPosts(userId, page, size);
        return new BaseResponseDto<>(postBasicResDto);
    }

    // 좋아요 누르기
    @PostMapping("/{userId}/{postId}/likes")
    public BaseResponseDto<PostMessageResDto> createLikePost(@PathVariable Long userId,
                                                             @PathVariable Long postId){
        PostMessageResDto postMessageResDto = postService.createLikePost(userId, postId);
        return new BaseResponseDto<>(postMessageResDto);
    }

    // 좋아요 취소
    @DeleteMapping("/{userId}/{postId}/unlikes")
    public BaseResponseDto<PostMessageResDto> createUnlikeBoard(@PathVariable Long userId,
                                                                @PathVariable Long postId){
        PostMessageResDto postMessageResDto = postService.createUnlikeBoard(userId, postId);
        return new BaseResponseDto<>(postMessageResDto);
    }

        // 유저가 좋아요 누른 게시물 조회
    @GetMapping("/{userId}/{postId}/likes")
    public BaseResponseDto<PostBasicResDto> readUserLikedPosts(@PathVariable Long userId,
                                                               @RequestParam(defaultValue = "0") int page,
                                                               @RequestParam(defaultValue = "10") int size){
        PostBasicResDto postBasicResDto = postService.readLikePost(userId, page, size);
        return new BaseResponseDto<>(postBasicResDto);
    }
}
