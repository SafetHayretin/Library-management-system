package com.safetKyuchyukhalil.libraryManagementSystem.service.borrowing;

import com.safetKyuchyukhalil.libraryManagementSystem.entity.books.Book;
import com.safetKyuchyukhalil.libraryManagementSystem.entity.books.Borrowing;
import com.safetKyuchyukhalil.libraryManagementSystem.entity.users.Member;
import com.safetKyuchyukhalil.libraryManagementSystem.repository.books.BookRepository;
import com.safetKyuchyukhalil.libraryManagementSystem.repository.borrowing.BorrowingRepository;
import com.safetKyuchyukhalil.libraryManagementSystem.repository.users.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class BorrowingService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private BorrowingRepository borrowingRepository;

    public List<Book> findAllAvailableBooks() {
        return bookRepository.findAllAvailableBooks();
    }

   public void borrowBook(Long memberId, Long bookId) {
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new RuntimeException("Member not found!"));
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new RuntimeException("Book not found!"));

        if (book.getAvailableCopies() > 0) {
            Borrowing borrowing = new Borrowing();
            borrowing.setMember(member);
            borrowing.setBook(book);
            borrowing.setBorrowDate(LocalDate.now());
            borrowing.setReturnDate(LocalDate.now().plusWeeks(2));  // 2 weeks loan period

            book.setAvailableCopies(book.getAvailableCopies() - 1);
            bookRepository.save(book);
            borrowingRepository.save(borrowing);
        } else {
            throw new RuntimeException("No available copies to borrow");
        }
   }

   public void returnBook(Long borrowingId) {
       Borrowing borrowing = borrowingRepository.findById(borrowingId)
               .orElseThrow(() -> new RuntimeException("Borrowing record not found"));

       Book book = borrowing.getBook();
       book.setAvailableCopies(book.getAvailableCopies() + 1);
       bookRepository.save(book);

       borrowing.setReturnDate(LocalDate.now());
       borrowingRepository.save(borrowing);
   }
}
