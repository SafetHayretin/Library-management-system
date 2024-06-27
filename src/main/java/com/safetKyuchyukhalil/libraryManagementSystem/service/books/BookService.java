package com.safetKyuchyukhalil.libraryManagementSystem.service.books;

import com.safetKyuchyukhalil.libraryManagementSystem.entity.books.Book;
import com.safetKyuchyukhalil.libraryManagementSystem.exception.books.BookNotFoundException;
import com.safetKyuchyukhalil.libraryManagementSystem.repository.books.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public void delete(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new BookNotFoundException("Book not found with id: " + id);
        }
        bookRepository.deleteById(id);
    }

    public Book findById(Long bookId) {
        return bookRepository.findById(bookId)
                .orElseThrow(() -> new BookNotFoundException("Book not found with id: " + bookId));
    }

    public Book save(Book book) {
        return bookRepository.save(book);
    }

    public List<Book> findAllAvailable() {
        return bookRepository.findAllAvailableBooks();
    }
}
