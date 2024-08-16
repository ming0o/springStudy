package com.study.spring.dto.request;

import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PostCreate {
    @NotBlank(message = "제목을 입력하세요") //공백 불가능
    private String title;

    @NotBlank(message = "내용을 입력하세요")
    private String content;

    @Builder
    public PostCreate(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
