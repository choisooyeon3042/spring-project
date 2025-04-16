package com.estsoft.demo.blog.exception;

public class NotExistsIdException extends RuntimeException {
    public NotExistsIdException(Long id) {
        super("Comment with ID " + id + " does not exist.");
    }
}