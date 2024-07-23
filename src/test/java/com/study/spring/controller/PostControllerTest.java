package com.study.spring.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.study.spring.dto.request.PostCreate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;


import static org.springframework.http.MediaType.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class PostControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;
@Test
@DisplayName("/post 요청이 잘 되는지")
void test1() throws Exception{
    //given
    PostCreate request = PostCreate.builder()
            .title("제목")
            .content("내용")
            .build();

    String json = objectMapper.writeValueAsString(request);

    System.out.println(json);

    mockMvc.perform(post("/post")
            .contentType(APPLICATION_JSON) //옵션 + 엔터 > static import하면 거추장스러운거 삭제 가능
            .content(json)
    )
            .andExpect(status().isOk())
            .andDo(print());
}
}