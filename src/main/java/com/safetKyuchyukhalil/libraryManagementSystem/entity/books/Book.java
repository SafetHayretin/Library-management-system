package com.safetKyuchyukhalil.libraryManagementSystem.entity.books;

import com.safetKyuchyukhalil.libraryManagementSystem.DTOs.books.BookResponse;
import com.safetKyuchyukhalil.libraryManagementSystem.entity.users.Member;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String author;

    private String isbn;

    private LocalDate publishedDate;

    private int availableCopies;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public BookResponse toResponse() {
        BookResponse bookResponse = new BookResponse();

        bookResponse.setAvailableCopies(getAvailableCopies());
        bookResponse.setAuthor(getAuthor());
        bookResponse.setTitle(getTitle());
        bookResponse.setIsbn(getIsbn());
        bookResponse.setPublishedDate(getPublishedDate());

        return bookResponse;
    }
}
