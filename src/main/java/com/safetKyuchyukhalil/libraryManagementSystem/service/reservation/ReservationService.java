package com.safetKyuchyukhalil.libraryManagementSystem.service.reservation;

import com.safetKyuchyukhalil.libraryManagementSystem.entity.books.Book;
import com.safetKyuchyukhalil.libraryManagementSystem.entity.reservation.Reservation;
import com.safetKyuchyukhalil.libraryManagementSystem.entity.users.Member;
import com.safetKyuchyukhalil.libraryManagementSystem.repository.books.BookRepository;
import com.safetKyuchyukhalil.libraryManagementSystem.repository.reservation.ReservationRepository;
import com.safetKyuchyukhalil.libraryManagementSystem.repository.users.MemberRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class ReservationService {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private NotificationService notificationService;

    @Transactional
    public void makeReservation(Long bookId, Long memberId) throws Exception {
        Optional<Book> optionalBook = bookRepository.findById(bookId);
        if (!optionalBook.isPresent()) {
            throw new Exception("Book not found.");
        }

        Book book = optionalBook.get();
        int availableCopies = book.getAvailableCopies();
        if (availableCopies > 0) {
            throw new Exception("Book is not available for reservation.");
        }

        Optional<Member> optionalMember = memberRepository.findById(memberId);
        if (!optionalMember.isPresent()) {
            throw new Exception("Member not found.");
        }

        Member member = optionalMember.get();
        Reservation reservation = new Reservation();
        reservation.setBook(book);
        reservation.setMember(member);
        reservation.setReservationDate(LocalDate.now());

        reservationRepository.save(reservation);

        book.setAvailableCopies(availableCopies - 1);
        bookRepository.save(book);

        // Send reservation confirmation notification
        notificationService.sendReservationConfirmation(member, book);
    }
}
