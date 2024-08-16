package com.study.spring.service;

import com.study.spring.domain.Post;
import com.study.spring.dto.request.PostCreate;
import com.study.spring.dto.response.PostResponse;
import com.study.spring.repository.PostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@AutoConfigureMockMvc
class PostServiceTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private PostService postservice;

    @Autowired
    private PostRepository postRepository;

    @BeforeEach
    void clean(){
        postRepository.deleteAll();
    }


    @Test
    @DisplayName("글 작성 요청이 잘 되는지")
    void test1() {
        //given
        PostCreate build = PostCreate.builder()
                .title("제목입니다")
                .content("내용입니다.")
                .build();
        //when
        postservice.write(build);
        //then
        assertEquals(1L, postRepository.count());
        Post post = postRepository.findAll().get(0);
        assertEquals("제목입니다.", post.getTitle());
        assertEquals("내용입니다.", post.getContent());

    }


    @Test
    @DisplayName("글 단건조회")
    void test2() {
        //given
        Post post = Post.builder()
                .title("제목이에요.")
                .content("내용이에요")
                .build();

        postRepository.save(post);

        //when
        PostResponse response = postservice.get(post.getId());
        //then
        assertNotNull(response);
        assertEquals(1L, postRepository.count());
        assertEquals("제목이에요.", response.getTitle());
        assertEquals("내용이에요", response.getContent());


    }

    @Test
    @DisplayName("글 전체조회")
    void test3() {
        //given
        List<Post> postList = IntStream.range(0, 10)
                .mapToObj(i ->{
                    return Post.builder()
                            .title("제목입니다" + i)
                            .content("내용입니다" + i)
                            .build();
                })
                .collect(Collectors.toList());

        postRepository.saveAll(postList);

        //when
        List<PostResponse> response = postservice.getAll();


        //then
        assertEquals(10L, response.size());
        assertEquals("제목입니다9", response.get(9).getTitle());

    }
}