package com.estsoft.demo.external.dto;

import com.estsoft.demo.external.domain.ExternalComment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CommentContent {
    private int postId;
    private int id;
    private String name;
    private String email;
    private String body;

    public ExternalComment toEntity() {
        return new ExternalComment(id, postId, name, email, body);
    }
}
