package com.safetKyuchyukhalil.libraryManagementSystem.service.reservation;

import com.safetKyuchyukhalil.libraryManagementSystem.entity.books.Book;
import com.safetKyuchyukhalil.libraryManagementSystem.entity.reservation.Reservation;
import com.safetKyuchyukhalil.libraryManagementSystem.entity.users.Member;
import com.safetKyuchyukhalil.libraryManagementSystem.repository.reservation.ReservationRepository;
import com.safetKyuchyukhalil.libraryManagementSystem.service.books.BookService;
import com.safetKyuchyukhalil.libraryManagementSystem.service.users.MemberService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ReservationService {

    private final BookService bookService;


    private final MemberService memberService;


    private final ReservationRepository reservationRepository;


    private final NotificationService notificationService;

    public ReservationService(BookService bookService, MemberService memberService, ReservationRepository reservationRepository, NotificationService notificationService) {
        this.bookService = bookService;
        this.memberService = memberService;
        this.reservationRepository = reservationRepository;
        this.notificationService = notificationService;
    }

    @Transactional
    public void makeReservation(Long bookId, Long memberId) throws Exception {
        Book book = bookService.findById(bookId);

        int availableCopies = book.getAvailableCopies();
        if (availableCopies > 0) {
            throw new Exception("Book is not available for reservation.");
        }

        Member member = memberService.findById(memberId);

        Reservation reservation = new Reservation();
        reservation.setBook(book);
        reservation.setMember(member);
        reservation.setReservationDate(LocalDate.now());

        reservationRepository.save(reservation);

        book.setAvailableCopies(availableCopies - 1);
        bookService.save(book);

        notificationService.sendReservationConfirmation(member, book);
    }
}
