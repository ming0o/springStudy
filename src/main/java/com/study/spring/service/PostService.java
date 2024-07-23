package com.study.spring.service;

import com.study.spring.domain.Post;
import com.study.spring.dto.request.PostCreate;
import com.study.spring.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class PostService {
    private final PostRepository postRepository;

    public void write(PostCreate request) {
        Post post = Post.builder()
                .content(request.getContent())
                .title(request.getTitle())
                .build();

        postRepository.save(post);
    }
}
