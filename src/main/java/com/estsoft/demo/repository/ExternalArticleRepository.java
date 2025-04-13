package com.estsoft.demo.repository;

import com.estsoft.demo.domain.ExternalArticle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExternalArticleRepository extends JpaRepository<ExternalArticle, Integer> {
}
