package com.estsoft.demo.service;

import com.estsoft.demo.domain.Book;
import com.estsoft.demo.dto.AddBookRequest;
import com.estsoft.demo.repository.BookRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    // 전체 책 목록 조회
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    // 책 정보 저장
    public Book saveBook(AddBookRequest request) {
        Book book = request.toEntity();
        return bookRepository.save(book);
    }

    // 책 단건 조회 id 기준으로 조회
    public Book getBook(String id) {
        return bookRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("no exists id: " + id));
    }
}
