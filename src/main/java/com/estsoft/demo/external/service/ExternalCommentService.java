package com.estsoft.demo.external.service;

import com.estsoft.demo.external.domain.ExternalComment;
import com.estsoft.demo.external.dto.CommentContent;
import com.estsoft.demo.external.repository.ExternalCommentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ExternalCommentService {
    private final ExternalCommentRepository commentRepository;

    public void call() {
        String url = "https://jsonplaceholder.typicode.com/comments";
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<List<CommentContent>> response =
                restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<>() {});
        List<CommentContent> commentContents = response.getBody();

        // 저장
        List<ExternalComment> comments = commentContents.stream()
                .map(CommentContent::toEntity)
                .toList();

        commentRepository.saveAll(comments);

    }
}
