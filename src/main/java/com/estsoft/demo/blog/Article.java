package com.estsoft.demo.blog;

import com.estsoft.demo.blog.dto.ArticleResponse;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Builder
    public Article(String title, String content) {
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
