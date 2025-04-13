package com.estsoft.demo.comment.service;

import com.estsoft.demo.comment.domain.Article;
import com.estsoft.demo.comment.domain.Comment;
import com.estsoft.demo.comment.dto.CommentResponseDTO;
import com.estsoft.demo.comment.repository.ArticleRepository;
import com.estsoft.demo.comment.repository.CommentRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CommentService {
    public final CommentRepository commentRepository;
    public final ArticleRepository articleRepository;

    public CommentService(CommentRepository commentRepository, ArticleRepository articleRepository) {
        this.commentRepository = commentRepository;
        this.articleRepository = articleRepository;
    }

    // 댓글 생성
    @Transactional
    public CommentResponseDTO createComment(Long articleId, String body) {
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다:" + articleId));

        Comment comment = new Comment(article, body, LocalDateTime.now());
        Comment savedComment = commentRepository.save(comment);

        return new CommentResponseDTO(savedComment);
    }

    // 댓글 조회
    public CommentResponseDTO getComment(Long commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("해당 댓글이 존재하지 않습니다: " + commentId));

        return new CommentResponseDTO(comment);
    }

    // 댓글 수정
    // 트랜잭션 커밋을 해줘야 변경 감지해서 update해줌
    @Transactional
    public CommentResponseDTO updateComment(Long commentId, String newBody) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("해당 댓글이 존재하지 않습니다: " + commentId));

        comment.setBody(newBody);
        return new CommentResponseDTO(comment);
    }

    // 댓글 삭제
    @Transactional
    public void deleteComment(Long commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("해당 댓글이 존재하지 않습니다: " + commentId));
        commentRepository.delete(comment);
    }

    // 게시글과 댓글 정보를 한번에 조회
    public Article getArticleComments(Long articleId) {
        return articleRepository.findById(articleId)
            .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다: " + articleId));

    }
}
