package com.study.spring.controller;

import com.study.spring.dto.request.PostCreate;
import com.study.spring.dto.request.PostUpdate;
import com.study.spring.dto.response.PostResponse;
import com.study.spring.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    // put -> 완전히 대체한다(부분 수정 X), patch -> 부분수정 O
    @PostMapping("/post")
    public void post(@RequestBody @Valid PostCreate request){
        postService.write(request);
    } //valid가 붙어야 아까 요청서에 작성한 notblank가 검증이 된다.

    @GetMapping("/post/{postId}") //단일 게시물 조회
    public PostResponse get(@PathVariable("postId") Long id) {
        return postService.get(id);
    }

    @GetMapping("/post")
    public List<PostResponse> getAll(){ //게시물 전체 조회
        return postService.getAll();
    }

    @PatchMapping("/post/{postId}")
    public PostResponse update(@PathVariable("postId") Long id,
                               @RequestBody @Valid PostUpdate postUpdate) {
        return postService.update(id, postUpdate);
    }
    /*
     url: /api/signIn == 회원가입 >> 대신 /api/sign-in 이런식으로 하이픈을 쓰는 게 국룰임
     전부 소문자, 언더바 대신 하이픈
     Put: 부분 변경 ㄴㄴ 리소스 완전 대체, patch: 부분 변경 가능
     */

    @DeleteMapping("/post/{postId}")
    public Long delete(@PathVariable("postId")Long id){
        return postService.delete(id);
    }
}
