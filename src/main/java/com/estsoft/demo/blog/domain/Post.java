package com.estsoft.demo.blog.domain;

import com.estsoft.demo.blog.dto.ArticleResponse;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    @Builder
    public Post(String title, String content) {
        this.title = title;
        this.content = content;
    }

    // title, content = ? commit; => update
    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public ArticleResponse toDto() {
        return new ArticleResponse(id, title, content);
    }

    // 빌더 패턴
    // Article.Builder
    // .title(title).content(content).build();
    // => new Article(title, content)
//    public Article(Builder builder) {
//        this.id = builder.id;
//        this.title = builder.title;
//        this.content = builder.content;
//    }
//
//    private static class Builder {
//        private Long id;
//        private String title;
//        private String content;
//
//
//        Builder id(Long id) {
//            this.id = id;
//            return this;
//        }
//
//        Builder title(String title) {
//            this.title = title;
//            return this;
//        }
//        Builder content(String content) {
//            this.content = content;
//            return this;
//        }
//
//        Article build() {
//            return new Article(this);
//        }
//    }
}
