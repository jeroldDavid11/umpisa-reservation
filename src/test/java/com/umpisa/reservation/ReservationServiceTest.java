package com.umpisa.reservation;


import com.umpisa.reservation.dao.ReservationDAO;
import com.umpisa.reservation.dto.ReservationRequest;
import com.umpisa.reservation.model.Reservation;
import com.umpisa.reservation.service.ReservationService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * Unit test for ReservationService.
 */
public class ReservationServiceTest {

    @InjectMocks
    private ReservationService reservationService;

    @Mock
    private ReservationDAO reservationDAO;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateReservation() {
        ReservationRequest request = new ReservationRequest();
        request.setName("John Doe");
        request.setPhone("123456789");
        request.setEmail("john@example.com");
        request.setReservationDateTime(LocalDateTime.now().plusDays(1));
        request.setNumberOfGuests(4);

        Reservation reservation = new Reservation();
        reservation.setId(1L);

        when(reservationDAO.saveReservation(any(Reservation.class))).thenReturn(reservation);

        Reservation createdReservation = reservationService.createReservation(request);

        verify(reservationDAO, times(1)).saveReservation(any(Reservation.class));
        assertEquals(1L, createdReservation.getId());
    }

    @Test
    public void testUpdateReservation() {
        Long reservationId = 1L;
        ReservationRequest request = new ReservationRequest();
        request.setReservationDateTime(LocalDateTime.now().plusDays(2));
        request.setNumberOfGuests(5);

        Reservation existingReservation = new Reservation();
        existingReservation.setId(reservationId);

        when(reservationDAO.findReservationById(reservationId)).thenReturn(Optional.of(existingReservation));
        when(reservationDAO.saveReservation(any(Reservation.class))).thenReturn(existingReservation);

        Optional<Reservation> updatedReservation = reservationService.updateReservation(reservationId, request);

        verify(reservationDAO, times(1)).saveReservation(existingReservation);
        assertEquals(5, updatedReservation.get().getNumberOfGuests());
    }

    @Test
    public void testDeleteReservation() {
        Long reservationId = 1L;
        Reservation reservation = new Reservation();
        reservation.setId(reservationId);

        when(reservationDAO.findReservationById(reservationId)).thenReturn(Optional.of(reservation));

        reservationService.deleteReservation(reservationId);

        verify(reservationDAO, times(1)).deleteReservation(reservationId);
    }
}