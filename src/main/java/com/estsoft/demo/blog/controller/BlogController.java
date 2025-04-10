package com.estsoft.demo.blog.controller;

import com.estsoft.demo.blog.domain.Article;
import com.estsoft.demo.blog.dto.AddArticleRequest;
import com.estsoft.demo.blog.dto.ArticleResponse;
import com.estsoft.demo.blog.dto.UpdateArticleRequest;
import com.estsoft.demo.blog.service.BlogService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public Article findArticle(@PathVariable Long id) {
        return blogService.findArticle(id);
    }

    // DELETE /api/articles/{id}
    // @RequestMapping(method = RequestMethod.DELETE)
    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable Long id) {
        blogService.deleteArticle(id);

        return ResponseEntity.ok().build();
    }

    // 전체 삭제
    @DeleteMapping("/api/articles")
    public ResponseEntity<Void> deleteAllArticles() {
        blogService.deleteAllArticles();
        return ResponseEntity.noContent().build();
    }

    // PUT http://localhost:8080/api/articles/1     {title, content}
    @PutMapping("/api/articles/{id}")
    public ResponseEntity<ArticleResponse> updateArticle(@PathVariable("id") Long id,
                                                         @RequestBody UpdateArticleRequest request) {
        Article article = blogService.updateArticle(id, request);

        // Article -> ArticleResponse
        ArticleResponse response = article.toDto();
        return ResponseEntity.ok(response);
    }

    // IllegalArgumentException 500x -> 400 Error
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handlerIllegalArgumentException(IllegalArgumentException e) {
        return e.getMessage();
    }
}
