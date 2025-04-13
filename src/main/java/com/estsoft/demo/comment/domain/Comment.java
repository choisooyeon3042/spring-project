package com.estsoft.demo.comment.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    @ManyToOne
    @JoinColumn(name = "article_id", referencedColumnName = "articleId", nullable = false)
    @JsonBackReference
    private Article article;

    private String body;

    @CreatedDate
    private LocalDateTime createdAt;

    public Comment(Article article, String body, LocalDateTime createdAt) {
        this.article = article;
        this.body = body;
        this.createdAt = createdAt;
    }
}
