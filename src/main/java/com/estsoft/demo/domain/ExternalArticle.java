package com.estsoft.demo.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "article")
public class ExternalArticle {

    @Id
    private int articleId;

    @Column(nullable = false)
    private int userId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

}