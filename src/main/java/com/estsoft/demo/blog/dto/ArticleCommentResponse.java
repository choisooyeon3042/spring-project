package com.estsoft.demo.blog.dto;

import com.estsoft.demo.blog.domain.Article;
import com.estsoft.demo.blog.domain.Comment;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
public class ArticleCommentResponse {
    private Long articleId;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<CommentNoArticleResponse> comments;

    public ArticleCommentResponse(Article article) {
        this.articleId = article.getId();
        this.title = article.getTitle();
        this.content = article.getContent();
        this.createdAt = article.getCreatedAt();
        this.updatedAt = article.getUpdatedAt();

        List<Comment> commentList = article.getCommentList();
        this.comments = commentList.stream().map(CommentNoArticleResponse::new).toList();

    }
}
