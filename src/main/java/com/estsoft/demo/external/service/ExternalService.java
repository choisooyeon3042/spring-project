package com.estsoft.demo.external.service;

import com.estsoft.demo.external.domain.ExternalArticle;
import com.estsoft.demo.external.dto.PostContent;
import com.estsoft.demo.external.repository.ExternalArticleRepository;
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
public class ExternalService {
    private final ExternalArticleRepository articleRepository;

    public void call() {
        String url = "https://jsonplaceholder.typicode.com/posts";
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<List<PostContent>> response =
                restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<>() {});
        List<PostContent> postContents = response.getBody();

        // 저장
        List<ExternalArticle> articles = postContents.stream()
                .map(PostContent::toEntity)
                .toList();

        articleRepository.saveAll(articles);

    }

}
