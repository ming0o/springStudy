package com.study.spring.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 기본 생성자, 접근 제어 protected 설정
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 생성 시 에러방지
    private Long id;
    private String title; // 제목
    @Lob // 게시물 내용에 따라 문자열 양으로 부하가 갈 수 있어서 방지용으로 byte로 바꿔주는 역할
    private String content; // 내용

    @Builder
    public Post(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
