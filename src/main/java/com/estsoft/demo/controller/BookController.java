package com.estsoft.demo.controller;


import com.estsoft.demo.dto.BookViewResponse;
import com.estsoft.demo.domain.Book;
import com.estsoft.demo.dto.AddBookRequest;
import com.estsoft.demo.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.util.List;

@Controller
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    // 전체 조회 GET /books     bookManage.html
    @GetMapping("/books")
    public String getAllBooks(Model model) {
        // 조회
        List<BookViewResponse> bookList = bookService.getAllBooks()
                .stream()
                .map(BookViewResponse::new)
                .toList();

        model.addAttribute("bookList", bookList);
        return "bookManage";
    }

    // 단건 조회 GET /books/{id}    bookDetail.html
    @GetMapping("/books/{id}")
    public String showBookDetail(@PathVariable String id, Model model) {
        Book book = bookService.getBook(id);

        model.addAttribute("book", new BookViewResponse(book));
        return "bookDetail";
    }

    // 단건 저장 POST /books(form 화면 통해서 저장할 데이터 전달)    GET /books로 리디렉션
    @PostMapping("/books")
    public String saveBook(@ModelAttribute AddBookRequest request) {
        bookService.saveBook(request);
        return "redirect:/books";
    }
}
