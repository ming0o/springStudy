package com.study.spring.dto.response;

import com.study.spring.domain.Post;
import lombok.Builder;
import lombok.Getter;


@Getter
public class PostResponse {
    private final Long id;
    private final String title;
    private final String content;

    //생성자 오버로딩
    public PostResponse(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
    }

    @Builder
    public PostResponse(Long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }
}
