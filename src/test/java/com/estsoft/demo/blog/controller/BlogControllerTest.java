package com.estsoft.demo.blog.controller;

import com.estsoft.demo.blog.domain.Post;
import com.estsoft.demo.blog.dto.AddArticleRequest;
import com.estsoft.demo.blog.dto.UpdateArticleRequest;
import com.estsoft.demo.blog.repository.BlogRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class BlogControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private BlogRepository blogRepository;
    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        blogRepository.deleteAll();
    }

    @Test
    void saveArticle() throws Exception {
        // given:   Object -> json (ObjectMapper 사용해서 직렬화)
        AddArticleRequest request = new AddArticleRequest("제목", "내용");
        String requestBody = objectMapper.writeValueAsString(request);  // 직렬화
//        System.out.println("requestBody: " + requestBody);

        // when:  POST /api/articles (API 요청)
        ResultActions resultActions = mockMvc.perform(post("/api/articles")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody));

        // then:
        resultActions.andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value(request.getTitle()))
                .andExpect(jsonPath("$.content").value(request.getContent()));
    }

    // 전체 목록조회 API 테스트 코드
    @Test
    public void findAllArticles() throws Exception{
        // given: article 값 저장
        Post savedArticle = Post.builder()
                .title("저장하려는 제목")
                .content("저장하려는 내용")
                .build();
        blogRepository.save(savedArticle);


        // when:GET / api/articles API 호출
        ResultActions resultActions = mockMvc.perform(get("/api/articles"));


        // then:
        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value(savedArticle.getTitle()))
                .andExpect(jsonPath("$[0].content").value(savedArticle.getContent()));
    }

    // 단건 조회 API 테스트 코드
    @Test
    public void findArticleById() throws Exception {
        // given: article 값 저장, id
        Post article = blogRepository.save(new Post("제목TT", "내용CC"));
        Long id = article.getId();

        // when: GET /api/articles/{id} API호출                        /api/articles/3
        ResultActions resultActions = mockMvc.perform(get("/api/articles/{id}", id));

        // then: 값 검증 - given절 값, when절 결과 값 비교하여 검증
        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.title").value(article.getTitle()))
                .andExpect(jsonPath("$.content").value(article.getContent()));
    }

    // 삭제 테스트 코드
    @Test
    public void deleteArticle() throws Exception{
        // given: article 저장, getId
        Post article = blogRepository.save(new Post("제목0_0", "내용"));
        Long id = article.getId();

        // when: DELETE API 호출
        ResultActions resultActions = mockMvc.perform(delete("/api/articles/{id}", id));

        // then: status code 200 ok 검증, article 전체 조회 결과 isEmpty() true
        resultActions.andExpect(status().isOk());

        List<Post> list = blogRepository.findAll();
        Assertions.assertThat(list).isEmpty();
        Assertions.assertThat(list.size()).isEqualTo(0);

    }

    // 수정 테스트 코드
    @Test
    public void updateArticle() throws Exception {
        // given: 게시글 추가, id추출, 수정할 값 셋팅(json)
        Post saved = blogRepository.save(new Post("dummy_title", "dummy_content"));
        Long id = saved.getId();
        UpdateArticleRequest request = new UpdateArticleRequest("update_title", "update_content");

        // request(object) -> json 직렬화     // jackson
        String requestBody = objectMapper.writeValueAsString(request);

        // when: 게시글 수정 api 호출
        ResultActions resultActions = mockMvc.perform(put("/api/articles/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody));

        // then: status code 검증, 값 검증 (responseBody값 = given절 값)
        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value(request.getTitle()))
                .andExpect(jsonPath("$.content").value(request.getContent()));

        Post article = blogRepository.findById(id).orElseThrow();
        // article.getTitle() == request.getTitle();
        assertThat(article.getTitle()).isEqualTo(request.getTitle());
        assertThat(article.getContent()).isEqualTo(request.getContent());
    }

    @DisplayName("Exception 발생시 400 Status Code 반환 검증 테스트 코드")
    @Test
    public void updateArticleFailed() throws Exception{
        // given: id = ?
        Long noExistsId = 1000L;
        UpdateArticleRequest request = new UpdateArticleRequest("수정할_title", "수정할_content");
        String requestBody = objectMapper.writeValueAsString(request);

        // when: 게시글 수정 api호출
        ResultActions resultActions = mockMvc.perform(put("/api/articles/{id}", noExistsId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody));

        // then:
        resultActions.andExpect(status().isBadRequest());
    }
}