package com.estsoft.demo.blog.controller;

import com.estsoft.demo.blog.Article;
import com.estsoft.demo.blog.dto.AddArticleRequest;
import com.estsoft.demo.blog.dto.ArticleResponse;
import com.estsoft.demo.blog.service.BlogService;
import com.estsoft.demo.repository.Member;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class BlogController {
    // POST /api/articles
    // { title: "", content: ""}
    private final BlogService blogService;

    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    // ResponseEntity
    @PostMapping("/api/articles")
    public ResponseEntity<ArticleResponse> saveArticle(@RequestBody AddArticleRequest request) {
        Article savedArticle = blogService.saveArticle(request);

        // Article -> ArticleResponse 변환 후 리턴
        return ResponseEntity.status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(savedArticle.toDto());
    }

    @GetMapping("/api/articles")
    public ResponseEntity <List<ArticleResponse>> findAllArticles() {
       List<Article> articles = blogService.findArticles();

       List<ArticleResponse> responseBody = articles.stream().map(article ->
               new ArticleResponse(article.getId(), article.getTitle(), article.getContent()))
               .toList();

       return ResponseEntity.ok(responseBody); // 200 status
    }

    // GET /articles/{id} 게시글 단건 조회
    @ResponseBody
    @GetMapping("/api/articles/{id}")
    public Article selectArticleById(@PathVariable Long id) {
        return blogService.selectArticleById(id);
    }
}
