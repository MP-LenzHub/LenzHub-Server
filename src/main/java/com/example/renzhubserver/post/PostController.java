package com.example.renzhubserver.post;

import com.example.renzhubserver.aspect.LogExecutionTime;
import com.example.renzhubserver.config.BaseResponseDto;
import com.example.renzhubserver.post.model.PostBasicInfo;
import com.example.renzhubserver.post.model.PostBasicResDto;
import com.example.renzhubserver.post.model.PostCreateReqDto;
import com.example.renzhubserver.post.model.PostMessageResDto;
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
    @LogExecutionTime
    @GetMapping("")
    public BaseResponseDto<PostBasicResDto> readAllPosts(@RequestParam(defaultValue = "0") int page,
                                                         @RequestParam(defaultValue = "20") int size){
        PostBasicResDto postBasicResDto = postService.readAllPosts(page, size);
        return new BaseResponseDto<>(postBasicResDto);
    }
    /**
     * 게시물 추가
     */
    @LogExecutionTime
    @PostMapping("/{userId}")
    public BaseResponseDto<PostMessageResDto> createPost(@PathVariable Long userId,
                                                         @RequestPart PostCreateReqDto postCreateReqDto,
                                                         @RequestPart MultipartFile beforeImage,
                                                         @RequestPart MultipartFile afterImage
                                                         ){
        try{
            PostMessageResDto  postMessageResDto = postService.createPost(userId, postCreateReqDto, beforeImage, afterImage);
            return new BaseResponseDto<>(postMessageResDto);
        }catch (IOException e){
            return new BaseResponseDto<>(new PostMessageResDto("이상함"));
        }
    }
    /**
     * 게시물 삭제
     */
    @LogExecutionTime
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
                                                          @RequestParam(defaultValue = "20") int size){
        PostBasicResDto postBasicResDto = postService.readUserPosts(userId, page, size);
        return new BaseResponseDto<>(postBasicResDto);
    }
    /**
     * 좋아요 누르기
     */
    @LogExecutionTime
    @PutMapping("/{userId}/{postId}/likes")
    public BaseResponseDto<PostMessageResDto> createLikePost(@PathVariable Long userId,
                                                             @PathVariable Long postId){
        PostMessageResDto postMessageResDto = postService.createLikePost(userId, postId);
        return new BaseResponseDto<>(postMessageResDto);
    }
    /**
     * 좋아요 취소
     */
    @LogExecutionTime
    @DeleteMapping("/{userId}/{postId}/unlikes")
    public BaseResponseDto<PostMessageResDto> createUnlikeBoard(@PathVariable Long userId,
                                                                @PathVariable Long postId){
        PostMessageResDto postMessageResDto = postService.createUnlikeBoard(userId, postId);
        return new BaseResponseDto<>(postMessageResDto);
    }
    /**
     * 유저가 좋아요 누른 게시물 조회
     */
    @LogExecutionTime
    @GetMapping("/{userId}/likes")
    public BaseResponseDto<PostBasicResDto> readUserLikedPosts(@PathVariable Long userId,
                                                               @RequestParam(defaultValue = "0") int page,
                                                               @RequestParam(defaultValue = "20") int size){
        PostBasicResDto postBasicResDto = postService.readLikePost(userId, page, size);
        return new BaseResponseDto<>(postBasicResDto);
    }
    /**
     * 상세 게시물 조회
     */
    @LogExecutionTime
    @GetMapping("/{postId}/detailed")
    public BaseResponseDto<PostBasicInfo> readPost(@PathVariable Long postId){
        PostBasicInfo readPost = postService.readPost(postId);
        return new BaseResponseDto<>(readPost);
    }
    /**
     * 카테고리별 조회
     */
    @LogExecutionTime
    @GetMapping("/category")
    public BaseResponseDto<PostBasicResDto> readCategoryPost(@RequestParam String category){
        PostBasicResDto postBasicResDto = postService.readCategoryPost(category);
        return new BaseResponseDto<>(postBasicResDto);
    }
    /**
     * 무료 카테고리별 조회
     */
    @LogExecutionTime
    @GetMapping("/category/free")
    public BaseResponseDto<PostBasicResDto> readFreeCategoryPost(@RequestParam String category){
        PostBasicResDto postBasicResDto = postService.readFreeCategoryPost(category);
        return new BaseResponseDto<>(postBasicResDto);
    }
    /**
     * 유료 카테고리별 조회
     */
    @LogExecutionTime
    @GetMapping("/category/pay")
    public BaseResponseDto<PostBasicResDto> readNotFreeCategoryPost(@RequestParam String category){
        PostBasicResDto postBasicResDto = postService.readNotFreeCategoryPost(category);
        return new BaseResponseDto<>(postBasicResDto);
    }
    /**
     * title 조회
     */
    @LogExecutionTime
    @GetMapping("/title")
    public BaseResponseDto<PostBasicResDto> readTitlePost(@RequestParam String title){
        PostBasicResDto postBasicResDto = postService.readTitlePost(title);
        return new BaseResponseDto<>(postBasicResDto);
    }
}
