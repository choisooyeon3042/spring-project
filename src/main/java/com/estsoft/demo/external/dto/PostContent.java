package com.estsoft.demo.external.dto;

import com.estsoft.demo.external.domain.ExternalArticle;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PostContent {
    private int userId;
    private int id;
    private String title;
    private String body;

    public ExternalArticle toEntity() {
        return new ExternalArticle(id, userId, title, body);
    }
}
