package com.estsoft.demo.blog.controller;

import com.estsoft.demo.blog.domain.Post;
import org.springframework.ui.Model;
import com.estsoft.demo.blog.dto.ArticleViewResponse;
import com.estsoft.demo.blog.service.BlogService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class BlogPageController {

    private final BlogService blogService;

    public BlogPageController(BlogService blogService) {
        this.blogService = blogService;
    }

    @GetMapping("/posts")
    public String getArticles(Model model) {
        List<ArticleViewResponse> articleList = blogService.findArticles()
                        .stream().map(ArticleViewResponse::new)
                        .toList();

        model.addAttribute("posts", articleList);
        return "postList"; // html 페이지
    }

    // /articles/{id} -> post.html
    @GetMapping("/posts/{id}")
    public String getArticle(@PathVariable Long id, Model model) {
        // 게시글 단건 조회
        Post article = blogService.findArticle(id);

        model.addAttribute("post", new ArticleViewResponse(article));

        return "post";
    }

    // /new-post -> newPost.html (생성/수정)
    // /new-post?id=1 @RequestParam
    // /new-post/{id} @PathVariable
    @GetMapping("/new-post")               // id 값이 필수가 아님.
    public String showBlogEditPage(@RequestParam(required = false) Long id, Model model) {
        if (id == null) {
            model.addAttribute("post", new ArticleViewResponse());
        } else {
            Post article = blogService.findArticle(id);
            model.addAttribute("post", new ArticleViewResponse(article));
        }
        return "newPost";
    }
}
