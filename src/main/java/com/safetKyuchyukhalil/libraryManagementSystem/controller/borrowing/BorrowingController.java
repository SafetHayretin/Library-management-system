package com.safetKyuchyukhalil.libraryManagementSystem.controller.borrowing;

import com.safetKyuchyukhalil.libraryManagementSystem.entity.books.Book;
import com.safetKyuchyukhalil.libraryManagementSystem.entity.borrowing.Borrowing;
import com.safetKyuchyukhalil.libraryManagementSystem.service.books.BookService;
import com.safetKyuchyukhalil.libraryManagementSystem.service.borrowing.BorrowingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/borrowing")
public class BorrowingController {
    private final BorrowingService borrowingService;

    private final BookService bookService;

    public BorrowingController(BorrowingService borrowingService, BookService bookService) {
        this.borrowingService = borrowingService;
        this.bookService = bookService;
    }

    @GetMapping
    public List<Book> getAllAvailableBooks(){
        return bookService.findAllAvailableBooks();
    }

    @GetMapping("/borrowed-books")
    public List<Book> getAllBorrowedBooks(){
        return borrowingService.findAllBorrowedBooks();
    }

    @GetMapping("/borrowed-books")
    public List<Borrowing> getAllOverdueBooks(){
        return borrowingService.findAllOverdueBorrowings();
    }

    @PostMapping("/borrow")
    public ResponseEntity<String> borrowBook(@RequestParam Long userId, @RequestParam Long bookId) {
        try {
            borrowingService.borrowBook(userId, bookId);

            return ResponseEntity.ok("Book borrowed successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/return")
    public ResponseEntity<String> returnBook(@RequestParam Long borrowingId) {
        try {
            borrowingService.returnBook(borrowingId);
            return ResponseEntity.ok("Book returned successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
