package com.estsoft.demo.blog.repository;

import com.estsoft.demo.blog.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("blogArticleRepository")
public interface ArticleRepository extends JpaRepository<Article, Long> {
}
