package com.estsoft.demo.external.controller;

import com.estsoft.demo.external.service.ExternalCommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExternalCommentApiController {

    private final ExternalCommentService commentService;

    public ExternalCommentApiController(ExternalCommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/api/external/comment")
    public ResponseEntity<String> callExternal() {
        // RestTemplate
        commentService.call();

        return ResponseEntity.ok().build();
    }
}

