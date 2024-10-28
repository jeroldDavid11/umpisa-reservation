package com.umpisa.reservation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Main application class to start the Spring Boot application.
 */

@SpringBootApplication
@EnableScheduling
public class UmpisaReservationApplication {

    public static void main(String[] args) {
        SpringApplication.run(UmpisaReservationApplication.class, args);
    }
}