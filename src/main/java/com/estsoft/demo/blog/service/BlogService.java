package com.estsoft.demo.blog.service;

import com.estsoft.demo.blog.Article;
import com.estsoft.demo.blog.dto.AddArticleRequest;
import com.estsoft.demo.blog.dto.UpdateArticleRequest;
import com.estsoft.demo.blog.repository.BlogRepository;
import com.estsoft.demo.repository.Member;
import jakarta.transaction.Transactional;
import org.hibernate.sql.Update;
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
    public Article findArticle(Long id) {
        Optional<Article> optArticle = blogRepository.findById(id);
        return optArticle.orElse(new Article());
    }

    // 삭제
    public void deleteArticle(Long id) {
        blogRepository.deleteById(id);
    }

    // 전체 삭제
    public void deleteAllArticles() {
        blogRepository.deleteAll();
    }

    // 수정 method
    // 트랜잭션 커밋을 해줘야 변경 감지해서 update해줌
    @Transactional // = begin; commit;
    public Article updateArticle(Long id, UpdateArticleRequest request) {
        // findById (수정하기 이전 Article 객체)
        Article article = blogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not exists id:" + id)); // 수정할 수 없는 상태

        // update
        article.update(request.getTitle(), request.getContent());
        return article;
    }
}
