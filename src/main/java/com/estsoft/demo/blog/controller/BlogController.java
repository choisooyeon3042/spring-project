package com.estsoft.demo.blog.controller;

import com.estsoft.demo.blog.domain.Article;
import com.estsoft.demo.blog.domain.Comment;
import com.estsoft.demo.blog.dto.*;
import com.estsoft.demo.blog.service.BlogService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BlogController {

    private final BlogService blogService;

    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    @PostMapping("/api/articles")
    public ResponseEntity<ArticleResponse> saveArticle(@RequestBody AddArticleRequest request) {
        Article savedArticle = blogService.saveArticle(request);

        // Article -> ArticleResponse 변환 후 리턴
        return ResponseEntity.status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(savedArticle.toDto());
    }

    @GetMapping("/api/articles")
    public ResponseEntity<List<ArticleResponse>> findAllArticles() {
        List<Article> articles = blogService.findArticles();

        List<ArticleResponse> responseBody = articles.stream()
                .map(ArticleResponse::new)
                .toList();

        return ResponseEntity.ok(responseBody);
    }

    // 단건 조회 GET /api/articles/{id}
    @GetMapping("/api/articles/{id}")
    public ResponseEntity<ArticleResponse> findArticle(@PathVariable("id") Long id) {
        Article article = blogService.findArticle(id);
        return ResponseEntity.ok(article.toDto());
    }

    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable("id") Long id) {
        blogService.deleteArticle(id);

        return ResponseEntity.ok().build();
    }

    @PutMapping("/api/articles/{id}")
    public ResponseEntity<ArticleResponse> updateArticle(@PathVariable("id") Long id,
                                                         @RequestBody UpdateArticleRequest request) {
        Article article = blogService.updateArticle(id, request);

        ArticleResponse response = article.toDto();
        return ResponseEntity.ok(response);
    }

    @PostMapping("/api/articles/{articleId}/comments")
    public ResponseEntity<CommentResponse> saveComment(@PathVariable("articleId") Long articleId,
                                                       @RequestBody CommentRequest request) {
        Comment comment = blogService.saveComment(articleId, request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new CommentResponse(comment));
    }

    @GetMapping("/api/comments/{commentId}")
    public ResponseEntity<CommentResponse> findComment(@PathVariable("commentId") Long commentId) {
        Comment comment = blogService.findComment(commentId);
        return ResponseEntity.ok(new CommentResponse(comment));
    }

    @PutMapping("/api/comments/{commentId}")
    public ResponseEntity<CommentResponse> updateComment(@PathVariable Long commentId,
                                                         @RequestBody CommentRequest request) {
        Comment comment = blogService.updateComment(commentId, request);
        return ResponseEntity.ok(new CommentResponse(comment));
    }

    @DeleteMapping("/api/comments/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long commentId) {
        blogService.deleteComment(commentId);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/api/articles/{articleId}/comments")
    public ResponseEntity<ArticleCommentResponse> findArticleWithComment(@PathVariable Long articleId) {
       Article article = blogService.findArticle(articleId);

       return ResponseEntity.ok(new ArticleCommentResponse(article));
    }

}