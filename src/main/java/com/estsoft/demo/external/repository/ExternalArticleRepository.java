package com.estsoft.demo.external.repository;

import com.estsoft.demo.external.domain.ExternalArticle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExternalArticleRepository extends JpaRepository<ExternalArticle, Integer> {
}
