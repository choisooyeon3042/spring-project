package com.estsoft.demo.blog.controller;

import com.estsoft.demo.blog.domain.Post;
import com.estsoft.demo.blog.dto.AddPostRequest;
import com.estsoft.demo.blog.dto.PostResponse;
import com.estsoft.demo.blog.dto.UpdatePostRequest;
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
    @PostMapping("/api/posts")
    public ResponseEntity<PostResponse> saveArticle(@RequestBody AddPostRequest request) {
        Post savedArticle = blogService.saveArticle(request);

        // Article -> ArticleResponse 변환 후 리턴
        return ResponseEntity.status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(savedArticle.toDto());
    }

    @GetMapping("/api/posts")
    public ResponseEntity <List<PostResponse>> findAllArticles() {
       List<Post> articles = blogService.findArticles();

       List<PostResponse> responseBody = articles.stream().map(article ->
               new PostResponse(article.getId(), article.getTitle(), article.getContent()))
               .toList();

       return ResponseEntity.ok(responseBody); // 200 status
    }

    // GET /articles/{id} 게시글 단건 조회
    @ResponseBody
    @GetMapping("/api/posts/{id}")
    public Post findArticle(@PathVariable Long id) {
        return blogService.findArticle(id);
    }

    // DELETE /api/articles/{id}
    // @RequestMapping(method = RequestMethod.DELETE)
    @DeleteMapping("/api/posts/{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable Long id) {
        blogService.deleteArticle(id);

        return ResponseEntity.ok().build();
    }

    // 전체 삭제
    @DeleteMapping("/api/posts")
    public ResponseEntity<Void> deleteAllArticles() {
        blogService.deleteAllArticles();
        return ResponseEntity.noContent().build();
    }

    // PUT http://localhost:8080/api/articles/1     {title, content}
    @PutMapping("/api/posts/{id}")
    public ResponseEntity<PostResponse> updateArticle(@PathVariable("id") Long id,
                                                      @RequestBody UpdatePostRequest request) {
        Post article = blogService.updateArticle(id, request);

        // Article -> ArticleResponse
        PostResponse response = article.toDto();
        return ResponseEntity.ok(response);
    }

}
