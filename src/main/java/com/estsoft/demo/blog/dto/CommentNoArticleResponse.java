package com.estsoft.demo.blog.dto;

import com.estsoft.demo.blog.domain.Article;
import com.estsoft.demo.blog.domain.Comment;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class CommentNoArticleResponse {
    private Long commentId;
    private Long articleId;
    private String body;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;

    public CommentNoArticleResponse(Comment comment) {
        this.commentId = comment.getCommentId();
        this.body = comment.getBody();
        this.createdAt = comment.getCreatedAt();
        Article article = comment.getArticle();
        this.articleId = article.getId();
    }
}
