package com.estsoft.demo.comment.dto;

import com.estsoft.demo.comment.domain.Comment;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CommentResponseDTO {
    private Long commentId;
    private Long articleId;
    private String body;
    private LocalDateTime createdAt;
    private ArticleResponseDTO article;

    public CommentResponseDTO(Comment comment) {
        this.commentId = comment.getCommentId();
        this.articleId = comment.getArticle().getArticleId();
        this.body = comment.getBody();
        this.createdAt = comment.getCreatedAt();
        this.article = new ArticleResponseDTO(comment.getArticle());
    }

}