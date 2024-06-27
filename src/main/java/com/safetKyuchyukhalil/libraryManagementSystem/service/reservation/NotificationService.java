package com.safetKyuchyukhalil.libraryManagementSystem.service.reservation;

import com.safetKyuchyukhalil.libraryManagementSystem.entity.books.Book;
import com.safetKyuchyukhalil.libraryManagementSystem.entity.reservation.Notification;
import com.safetKyuchyukhalil.libraryManagementSystem.entity.users.Member;
import com.safetKyuchyukhalil.libraryManagementSystem.repository.reservation.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class NotificationService {

    private final EmailService emailService;

    private final NotificationRepository notificationRepository;

    public NotificationService(EmailService emailService, NotificationRepository notificationRepository) {
        this.emailService = emailService;
        this.notificationRepository = notificationRepository;
    }

    public void sendReservationConfirmation(Member member, Book book) {
        String subject = "Reservation Confirmation";
        String body = "Dear " + member.getName() + ",\n\nYou have successfully reserved the book: " + book.getTitle() + ".";
        emailService.sendEmail(member.getEmail(), subject, body);

        Notification notification = new Notification();
        notification.setMessage(body);
        notification.setNotificationDate(LocalDateTime.now());
        notification.setMember(member);
        notification.setSent(true);

        notificationRepository.save(notification);
    }
}
