package com.estsoft.demo.blog.service;

import com.estsoft.demo.blog.domain.Article;
import com.estsoft.demo.blog.domain.Comment;
import com.estsoft.demo.blog.dto.AddArticleRequest;
import com.estsoft.demo.blog.dto.CommentRequest;
import com.estsoft.demo.blog.dto.UpdateArticleRequest;
import com.estsoft.demo.blog.exception.NotExistsIdException;
import com.estsoft.demo.blog.repository.ArticleRepository;
import com.estsoft.demo.blog.repository.CommentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BlogService {

    private final ArticleRepository blogRepository;
    private final CommentRepository commentRepository;

    public BlogService(ArticleRepository blogRepository, CommentRepository commentRepository) {
        this.blogRepository = blogRepository;
        this.commentRepository = commentRepository;
    }

    public Article saveArticle(AddArticleRequest request) {
        return blogRepository.save(request.toEntity());
    }

    public List<Article> findArticles() {
        return blogRepository.findAll();
    }

    public Article findArticle(Long id) {
        return blogRepository.findById(id).orElseThrow(() -> new NotExistsIdException(id));
    }

    public void deleteArticle(Long id) {
        blogRepository.deleteById(id);   // delete from article where id = ${id}
    }

    @Transactional
    public Article updateArticle(Long id, UpdateArticleRequest request) {
        Article article = blogRepository.findById(id)
                .orElseThrow(() -> new NotExistsIdException(id)); // 500 Error

        article.update(request.getTitle(), request.getContent());
        return article;
    }

    public Comment saveComment(Long articleId, CommentRequest request) {
        // 게시글 정보 찾기 article_id
        Article article = findArticle(articleId);
        return commentRepository.save(new Comment(request.getBody(), article));
    }

    public Comment findComment(Long commentId) {
        return commentRepository.findById(commentId)
                .orElseThrow(() -> new NotExistsIdException(commentId));
    }

    @Transactional
    public Comment updateComment(Long commentId, CommentRequest request) {
        Comment comment = findComment(commentId);
        return comment.updateBody(request.getBody());
    }

    public void deleteComment(Long commentId) {
        commentRepository.deleteById(commentId);
    }

}