package com.safetKyuchyukhalil.libraryManagementSystem.exception.books;

public class BookNotFoundException extends RuntimeException {
    public BookNotFoundException(String message) {
        super(message);
    }
}
