package com.estsoft.demo.external.repository;

import com.estsoft.demo.external.domain.ExternalComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExternalCommentRepository extends JpaRepository<ExternalComment, Integer> {
}
