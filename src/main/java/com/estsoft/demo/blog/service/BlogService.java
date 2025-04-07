package com.estsoft.demo.blog.service;

import com.estsoft.demo.blog.Article;
import com.estsoft.demo.blog.dto.AddArticleRequest;
import com.estsoft.demo.blog.repository.BlogRepository;
import com.estsoft.demo.repository.Member;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BlogService {

    private final BlogRepository blogRepository;

    public BlogService(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    public Article saveArticle(AddArticleRequest request) {
        return blogRepository.save(request.toEntity());
    }

    // 전체 목록 조회 code
    public List<Article> findArticles() {
        return blogRepository.findAll();
    }

    // 블로그 게시글 단건 조회
    public Article selectArticleById(Long id) {
        Optional<Article> optArticle = blogRepository.findById(id);
        return optArticle.orElse(new Article());
    }
}
