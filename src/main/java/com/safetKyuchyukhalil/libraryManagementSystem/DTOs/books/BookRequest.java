package com.safetKyuchyukhalil.libraryManagementSystem.DTOs.books;

import com.safetKyuchyukhalil.libraryManagementSystem.entity.books.Book;

import java.time.LocalDate;

public class BookRequest {
    private String title;

    private String author;

    private String isbn;

    private LocalDate publishedDate;

    private int availableCopies;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public LocalDate getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(LocalDate publishedDate) {
        this.publishedDate = publishedDate;
    }

    public int getAvailableCopies() {
        return availableCopies;
    }

    public void setAvailableCopies(int availableCopies) {
        this.availableCopies = availableCopies;
    }

    public Book toEntity(){
        Book book = new Book();

        book.setAvailableCopies(getAvailableCopies());
        book.setAuthor(getAuthor());
        book.setTitle(getTitle());
        book.setIsbn(getIsbn());
        book.setPublishedDate(getPublishedDate());

        return book;
    }
}
