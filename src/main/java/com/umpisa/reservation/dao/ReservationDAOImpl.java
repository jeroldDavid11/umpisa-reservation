package com.umpisa.reservation.dao;

import com.umpisa.reservation.model.Reservation;
import com.umpisa.reservation.repository.ReservationRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 * Implementation of ReservationDAO for database operations.
 */
@Component
public class ReservationDAOImpl implements ReservationDAO {

    private final ReservationRepository reservationRepository;

    /**
     * Constructor for ReservationDAOImpl.
     *
     * @param reservationRepository The repository for reservations.
     */
    public ReservationDAOImpl(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @Override
    public Reservation saveReservation(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    @Override
    public List<Reservation> getReservationsByEmail(String email) {
        return reservationRepository.findAllByEmail(email);
    }

    @Override
    public Optional<Reservation> findReservationById(Long id) {
        return reservationRepository.findById(id);
    }

    @Override
    public void deleteReservation(Long id) {
        reservationRepository.deleteById(id);
    }
}

