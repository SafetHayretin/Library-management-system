package com.safetKyuchyukhalil.libraryManagementSystem.controller.books;

import com.safetKyuchyukhalil.libraryManagementSystem.DTOs.books.BookRequest;
import com.safetKyuchyukhalil.libraryManagementSystem.DTOs.books.BookResponse;
import com.safetKyuchyukhalil.libraryManagementSystem.entity.books.Book;
import com.safetKyuchyukhalil.libraryManagementSystem.service.books.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public ResponseEntity<List<BookResponse>> getAllBooks() {
        List<Book> books = bookService.findAll();
        List<BookResponse> responses = books
                                        .stream()
                                        .map(Book::toResponse)
                                        .toList();

        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookResponse> getBookById(@PathVariable Long id) {
        Book book = bookService.findById(id);

        return ResponseEntity.ok(book.toResponse());
    }

    @PostMapping
    public ResponseEntity<BookResponse> createBook(@RequestBody BookRequest book) {
        Book createdBook = bookService.save(book.toEntity());

        return ResponseEntity.status(201).body(createdBook.toResponse());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
