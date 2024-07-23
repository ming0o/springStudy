package com.study.spring.controller;

import com.study.spring.dto.request.PostCreate;
import com.study.spring.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    // put -> 리소를 완전히 대체한다(부분 수정 X), patch -> 부분수정 O
    @PostMapping("/post")
    public void post(@RequestBody PostCreate request){
        postService.write(request);
    }
}
