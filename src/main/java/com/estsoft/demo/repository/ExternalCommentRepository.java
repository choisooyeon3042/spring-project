package com.estsoft.demo.repository;

import com.estsoft.demo.domain.ExternalComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExternalCommentRepository extends JpaRepository<ExternalComment, Integer> {
}
