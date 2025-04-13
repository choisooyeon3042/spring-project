package com.estsoft.demo.comment.controller;

import com.estsoft.demo.comment.domain.Article;
import com.estsoft.demo.comment.dto.CommentRequestDTO;
import com.estsoft.demo.comment.dto.CommentResponseDTO;
import com.estsoft.demo.comment.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    // 댓글 저장
    @PostMapping("/api/articles/{articleId}/comments")
    public ResponseEntity<CommentResponseDTO> createComment(@PathVariable Long articleId,
                                                            @RequestBody CommentRequestDTO request) {
        CommentResponseDTO response = commentService.createComment(articleId, request.getBody());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }

    // 댓글 정보 조회
    @GetMapping("/api/comments/{commentId}")
    public ResponseEntity<CommentResponseDTO> getComment(@PathVariable Long commentId) {
        CommentResponseDTO response = commentService.getComment(commentId);

        return ResponseEntity
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }

    // 댓글 정보 수정
    @PutMapping("/api/comments/{commentId}")
    public ResponseEntity<CommentResponseDTO> updateComment(@PathVariable Long commentId,
                                                            @RequestBody CommentRequestDTO requestDTO) {

        CommentResponseDTO responseDTO = commentService.updateComment(commentId, requestDTO.getBody());

        return ResponseEntity.ok(responseDTO);
    }

    // 댓글 삭제
    @DeleteMapping("/api/comments/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long commentId) {
        commentService.deleteComment(commentId);

        return ResponseEntity.ok().build();
    }

    // 게시글과 댓글 정보를 한번에 조회
    @GetMapping("/api/articles/{articleId}/comments")
    public ResponseEntity<Article> getArticleComments(@PathVariable Long articleId) {
        Article article = commentService.getArticleComments(articleId);

        return ResponseEntity.ok(article);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handlerIllegalArgumentException(IllegalArgumentException e) {
        return e.getMessage();
    }
}
