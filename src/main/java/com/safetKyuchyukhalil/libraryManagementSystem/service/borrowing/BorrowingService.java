package com.safetKyuchyukhalil.libraryManagementSystem.service.borrowing;

import com.safetKyuchyukhalil.libraryManagementSystem.entity.books.Book;
import com.safetKyuchyukhalil.libraryManagementSystem.entity.borrowing.Borrowing;
import com.safetKyuchyukhalil.libraryManagementSystem.entity.users.Member;
import com.safetKyuchyukhalil.libraryManagementSystem.repository.borrowing.BorrowingRepository;
import com.safetKyuchyukhalil.libraryManagementSystem.service.books.BookService;
import com.safetKyuchyukhalil.libraryManagementSystem.service.users.MemberService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class BorrowingService {

    private final BookService bookService;

    private final MemberService memberService;

    private final BorrowingRepository borrowingRepository;

    public BorrowingService(BookService bookService, MemberService memberService, BorrowingRepository borrowingRepository) {
        this.bookService = bookService;
        this.memberService = memberService;
        this.borrowingRepository = borrowingRepository;
    }

    public void borrowBook(Long memberId, Long bookId) {
        Member member = memberService.findById(memberId);
        Book book = bookService.findById(bookId);

        if (book.getAvailableCopies() > 0) {
            Borrowing borrowing = new Borrowing();
            borrowing.setMember(member);
            borrowing.setBook(book);
            borrowing.setBorrowDate(LocalDate.now());
            borrowing.setReturnDate(LocalDate.now().plusWeeks(2));  // 2 weeks loan period

            book.setAvailableCopies(book.getAvailableCopies() - 1);
            bookService.save(book);
            borrowingRepository.save(borrowing);
        } else {
            throw new RuntimeException("No available copies to borrow");
        }
    }

    public void returnBook(Long borrowingId) {
        Borrowing borrowing = findById(borrowingId);

        Book book = borrowing.getBook();
        book.setAvailableCopies(book.getAvailableCopies() + 1);
        bookService.save(book);

        borrowing.setReturnDate(LocalDate.now());
        borrowingRepository.save(borrowing);
    }

    public Borrowing findById(Long borrowingId) {
        return borrowingRepository.findById(borrowingId)
                .orElseThrow(() -> new RuntimeException("Borrowing record not found"));
    }

    public List<Book> findBorrowedBooksByUser(Long id) {
        List<Borrowing> borrowings = borrowingRepository.findBorrowedBooksByUser(id);
        List<Book> books = new ArrayList<>();
        borrowings.forEach(b -> books.add(b.getBook()));

        return books;
    }

    public List<Book> findAllBorrowedBooks() {
        List<Borrowing> borrowings = borrowingRepository.findAll();
        List<Book> books = new ArrayList<>();
        borrowings.forEach(b -> books.add(b.getBook()));

        return books;
    }

    public List<Borrowing> findAllOverdueBorrowings() {
        LocalDate date = LocalDate.now();

        return borrowingRepository.findAllOverdueBorrowings(date);
    }
}
