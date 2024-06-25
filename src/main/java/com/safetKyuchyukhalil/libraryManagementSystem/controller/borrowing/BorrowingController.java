package com.safetKyuchyukhalil.libraryManagementSystem.controller.borrowing;

import com.safetKyuchyukhalil.libraryManagementSystem.entity.books.Book;
import com.safetKyuchyukhalil.libraryManagementSystem.service.borrowing.BorrowingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BorrowingController {

    @Autowired
    private BorrowingService borrowingService;

    @GetMapping
    public List<Book> getAllAvailableBooks(){
        return borrowingService.findAllAvailableBooks();
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
