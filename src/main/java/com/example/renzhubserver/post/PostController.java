package com.example.renzhubserver.post;

import com.example.renzhubserver.config.BaseException;
import com.example.renzhubserver.config.BaseResponseDto;
import com.example.renzhubserver.post.model.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/board")
public class PostController {
    @Autowired
    private final PostService postService;

    /**
     * main 화면 게시물 조회
     */
    @GetMapping("")
    public BaseResponseDto<PostBasicResDto> readAllPosts(@RequestParam(defaultValue = "0") int page,
                                                         @RequestParam(defaultValue = "10") int size){
        PostBasicResDto postBasicResDto = postService.readAllPosts(page, size);
        return new BaseResponseDto<>(postBasicResDto);
    }
    /**
     * 게시물 추가
     */
    @PostMapping("/{userId}")
    public BaseResponseDto<PostMessageResDto> createPost(@PathVariable Long userId,
                                                         PostCreateReqDto postCreateReqDto){
        try{
            PostMessageResDto  postMessageResDto = postService.createPost(userId, postCreateReqDto);
            return new BaseResponseDto<>(postMessageResDto);
        }catch (IOException e){
            return new BaseResponseDto<>(new PostMessageResDto("이상함"));
        }
    }
    /**
     * 게시물 삭제
     */
    @DeleteMapping("/{postId}/{postName}")
    public BaseResponseDto<PostMessageResDto> deleteBoard(@PathVariable Long postId,
                                                          @PathVariable String postName){
        PostMessageResDto postMessageResDto = postService.deleteBoard(postId, postName);
        return new BaseResponseDto<>(postMessageResDto);
    }
    /**
     * 유저가 작성한 게시물 조회
     */
    @GetMapping("/{userId}")
    public BaseResponseDto<PostBasicResDto> readUserPosts(@PathVariable Long userId,
                                                          @RequestParam(defaultValue = "0") int page,
                                                          @RequestParam(defaultValue = "10") int size){
        PostBasicResDto postBasicResDto = postService.readUserPosts(userId, page, size);
        return new BaseResponseDto<>(postBasicResDto);
    }
    /**
     * 좋아요 누르기
     */
    @PutMapping("/{userId}/{postId}/likes")
    public BaseResponseDto<PostMessageResDto> createLikePost(@PathVariable Long userId,
                                                             @PathVariable Long postId){
        PostMessageResDto postMessageResDto = postService.createLikePost(userId, postId);
        return new BaseResponseDto<>(postMessageResDto);
    }
    /**
     * 좋아요 취소
     */
    @DeleteMapping("/{userId}/{postId}/unlikes")
    public BaseResponseDto<PostMessageResDto> createUnlikeBoard(@PathVariable Long userId,
                                                                @PathVariable Long postId){
        PostMessageResDto postMessageResDto = postService.createUnlikeBoard(userId, postId);
        return new BaseResponseDto<>(postMessageResDto);
    }
    /**
     * 유저가 좋아요 누른 게시물 조회
     */
    @GetMapping("/{userId}/likes")
    public BaseResponseDto<PostBasicResDto> readUserLikedPosts(@PathVariable Long userId,
                                                               @RequestParam(defaultValue = "0") int page,
                                                               @RequestParam(defaultValue = "10") int size){
        PostBasicResDto postBasicResDto = postService.readLikePost(userId, page, size);
        return new BaseResponseDto<>(postBasicResDto);
    }
    /**
     * 상세 게시물 조회
     */
    @GetMapping("/{postId}")
    public BaseResponseDto<PostBasicInfo> readPost(@PathVariable Long postId){
        PostBasicInfo readPost = postService.readPost(postId);
        return new BaseResponseDto<>(readPost);
    }
    /**
     * 카테고리별 조회
     */
    @GetMapping("/{category}")
    public BaseResponseDto<PostBasicResDto> readCategoryPost(@PathVariable String category){
        PostBasicResDto postBasicResDto = postService.readCategoryPost(category);
        return new BaseResponseDto<>(postBasicResDto);
    }
}
