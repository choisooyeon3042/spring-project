package com.estsoft.demo.blog.dto;

import com.estsoft.demo.blog.domain.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class ArticleViewResponse {
    private Long id;
    private String title;
    private String content;
    private LocalDateTime createdAt;

    public ArticleViewResponse(Post article) {
       this.id = article.getId();
       this.title = article.getTitle();
       this.content = article.getContent();
       this.createdAt = article.getCreatedAt();
    }
}
