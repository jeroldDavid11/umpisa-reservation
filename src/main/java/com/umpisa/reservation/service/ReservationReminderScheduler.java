package com.umpisa.reservation.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ReservationReminderScheduler {

    private final ReservationService reservationService;

    public ReservationReminderScheduler(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @Scheduled(fixedRate = 3600000) // Runs every hour
    public void sendReservationReminders() {
        reservationService.sendReminder();
    }
}
