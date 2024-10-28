package com.umpisa.reservation.dao;

import com.umpisa.reservation.model.Reservation;
import java.util.List;
import java.util.Optional;

/**
 * DAO interface for reservation data access.
 */
public interface ReservationDAO {
    Reservation saveReservation(Reservation reservation);
    List<Reservation> getReservationsByEmail(String email);
    Optional<Reservation> findReservationById(Long id);
    void deleteReservation(Long id);
}
