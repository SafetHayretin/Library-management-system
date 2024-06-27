package com.safetKyuchyukhalil.libraryManagementSystem.controller.reservation;

import com.safetKyuchyukhalil.libraryManagementSystem.DTOs.reservation.ReservationRequest;
import com.safetKyuchyukhalil.libraryManagementSystem.service.reservation.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping
    public ResponseEntity<String> makeReservation(@RequestBody ReservationRequest request) {
        try {
            reservationService.makeReservation(request.getBookId(), request.getMemberId());
            return ResponseEntity.ok("Reservation successful.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
