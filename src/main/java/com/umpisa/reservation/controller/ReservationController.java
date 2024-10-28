package com.umpisa.reservation.controller;

import com.umpisa.reservation.dto.ReservationRequest;
import com.umpisa.reservation.model.Reservation;
import com.umpisa.reservation.service.ReservationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for managing reservations.
 */
@RestController
@RequestMapping("/reservations")
public class ReservationController {

    private final ReservationService reservationService;

    /**
     * Constructor for ReservationController.
     *
     * @param reservationService The reservation service.
     */
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    /**
     * Creates a new reservation.
     *
     * @param request The reservation details.
     * @return The created reservation.
     */
    @PostMapping
    public ResponseEntity<Reservation> createReservation(@RequestBody ReservationRequest request) {
        return ResponseEntity.ok(reservationService.createReservation(request));
    }

    /**
     * Retrieves reservations by email.
     *
     * @param email The customer's email.
     * @return A list of reservations.
     */
    @GetMapping
    public ResponseEntity<List<Reservation>> getReservations(@RequestParam String email) {
        return ResponseEntity.ok(reservationService.getReservationsByEmail(email));
    }

    /**
     * Updates a reservation.
     *
     * @param id      The reservation ID.
     * @param request The updated reservation details.
     * @return The updated reservation.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Reservation> updateReservation(@PathVariable Long id, @RequestBody ReservationRequest request) {
        return reservationService.updateReservation(id, request)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Deletes a reservation by ID.
     *
     * @param id The reservation ID.
     * @return HTTP 204 if successful.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReservation(@PathVariable Long id) {
        reservationService.deleteReservation(id);
        return ResponseEntity.noContent().build();
    }
}
