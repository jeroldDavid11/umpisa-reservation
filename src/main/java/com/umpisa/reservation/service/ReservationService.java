package com.umpisa.reservation.service;

import com.umpisa.reservation.dao.ReservationDAO;
import com.umpisa.reservation.dto.ReservationRequest;
import com.umpisa.reservation.model.Reservation;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service for reservation management.
 */
@Service
public class ReservationService {

    private final ReservationDAO reservationDAO;

    /**
     * Constructs the service with a reservation DAO.
     *
     * @param reservationDAO The reservation DAO.
     */
    public ReservationService(ReservationDAO reservationDAO) {
        this.reservationDAO = reservationDAO;
    }

    public Reservation createReservation(ReservationRequest request) {
        Reservation reservation = new Reservation();
        reservation.setName(request.getName());
        reservation.setPhone(request.getPhone());
        reservation.setEmail(request.getEmail());
        reservation.setReservationDateTime(request.getReservationDateTime());
        reservation.setNumberOfGuests(request.getNumberOfGuests());

        Reservation savedReservation = reservationDAO.saveReservation(reservation);
        sendNotification(savedReservation, "Reservation Created");

        return savedReservation;
    }

    public List<Reservation> getReservationsByEmail(String email) {
        return reservationDAO.getReservationsByEmail(email).stream()
                .filter(reservation -> reservation.getReservationDateTime().isAfter(LocalDateTime.now()))
                .collect(Collectors.toList());
    }

    public Optional<Reservation> updateReservation(Long id, ReservationRequest request) {
        return reservationDAO.findReservationById(id).map(reservation -> {
            reservation.setReservationDateTime(request.getReservationDateTime());
            reservation.setNumberOfGuests(request.getNumberOfGuests());
            Reservation updatedReservation = reservationDAO.saveReservation(reservation);
            sendNotification(updatedReservation, "Reservation Updated");
            return updatedReservation;
        });
    }

    public void deleteReservation(Long id) {
        reservationDAO.findReservationById(id).ifPresent(reservation -> {
            reservationDAO.deleteReservation(id);
            sendNotification(reservation, "Reservation Canceled");
        });
    }

    private void sendNotification(Reservation reservation, String message) {
        System.out.println("Sent SMS: " + message + " for Reservation ID: " + reservation.getId());
        System.out.println("Sent EMAIL: " + message + " for Reservation ID: " + reservation.getId());
    }

    @Scheduled(cron = "0 0 0/4 * * ?")  // Every 4 hours at the top of the hour
    public void sendReminder() {
        List<Reservation> upcomingReservations = reservationDAO.getReservationsByEmail("")
                .stream()
                .filter(reservation -> reservation.getReservationDateTime().isAfter(LocalDateTime.now())
                        && reservation.getReservationDateTime().isBefore(LocalDateTime.now().plusHours(4)))
                .collect(Collectors.toList());

        for (Reservation reservation : upcomingReservations) {
            System.out.println("Sent SMS: Reminder for your reservation in 4 hours. Reservation ID: " + reservation.getId());
            System.out.println("Sent EMAIL: Reminder for your reservation in 4 hours. Reservation ID: " + reservation.getId());
        }
    }
}
