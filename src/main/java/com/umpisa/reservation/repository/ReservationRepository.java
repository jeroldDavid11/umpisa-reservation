package com.umpisa.reservation.repository;

import com.umpisa.reservation.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repository interface for Reservation entities.
 */
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    
    /**
     * Finds all reservations by email.
     *
     * @param email The email associated with reservations.
     * @return A list of reservations for the provided email.
     */
    List<Reservation> findAllByEmail(String email);
}
