package com.study.spring.repository;

import com.study.spring.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> { // post, 기본키 타입 설정
}
