package com.safetKyuchyukhalil.libraryManagementSystem.controller.reservation;

import com.safetKyuchyukhalil.libraryManagementSystem.DTOs.reservation.ReservationRequest;
import com.safetKyuchyukhalil.libraryManagementSystem.entity.reservation.Reservation;
import com.safetKyuchyukhalil.libraryManagementSystem.service.reservation.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping
    public ResponseEntity<List<Reservation>> getReservations() {
        return ResponseEntity.ok(reservationService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reservation> getReservationById(@PathVariable Long id) {
        return ResponseEntity.ok(reservationService.findById(id));
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
