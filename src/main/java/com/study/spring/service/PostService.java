package com.study.spring.service;

import com.study.spring.domain.Post;
import com.study.spring.dto.request.PostCreate;
import com.study.spring.dto.request.PostUpdate;
import com.study.spring.dto.response.PostResponse;
import com.study.spring.exception.post.PostNotFound;
import com.study.spring.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public PostResponse get(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new RuntimeException("존재하지 않는 글 입니다."));

        return PostResponse.builder()
                .id(post.getId())
                .content(post.getContent())
                .title(post.getTitle())
                .build();

    }

    public List<PostResponse> getAll(){
        List<Post> postList = postRepository.findAll();

        return postList.stream().map(PostResponse::new).collect(Collectors.toList());
    }

    public PostResponse update(Long id, PostUpdate postUpdate) {
        Post post = postRepository.findById(id)
                .orElseThrow(PostNotFound::new);

        post.update(postUpdate.getTitle(), postUpdate.getContent());

        return PostResponse.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .build();
    }

    public Long delete(Long id) {
        Post post = postRepository.findById(id)
            .orElseThrow(PostNotFound::new);

        postRepository.delete(post);
        return post.getId();
    }
}
