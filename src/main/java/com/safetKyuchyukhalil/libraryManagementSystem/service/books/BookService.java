package com.safetKyuchyukhalil.libraryManagementSystem.service.books;

import com.safetKyuchyukhalil.libraryManagementSystem.entity.books.Book;
import com.safetKyuchyukhalil.libraryManagementSystem.exception.BookNotFoundException;
import com.safetKyuchyukhalil.libraryManagementSystem.repository.books.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book getBookById(Long id) {
        return bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException("Book not found with id: " + id));
    }

    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    public void deleteBook(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new BookNotFoundException("Book not found with id: " + id);
        }
        bookRepository.deleteById(id);
    }

    public Book findById(Long bookId) {
        return bookRepository.findById(bookId).orElseThrow(() -> new BookNotFoundException("Book not found with id: " + bookId));
    }

    public void save(Book book) {
        bookRepository.save(book);
    }

    public List<Book> findAllAvailableBooks() {
        return bookRepository.findAllAvailableBooks();
    }
}
