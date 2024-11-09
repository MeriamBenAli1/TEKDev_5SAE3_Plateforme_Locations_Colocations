package com.esprit.ms.microservicemesssagerie.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "reservations")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // Informations personnelles
    private String firstName;

    private String lastName;

    private String phoneNumber;

    private String email;


    private LocalDate startDate;

    private LocalDate endDate;

    private Integer nightsDuration;

    private Integer monthsDuration;

    private String status;

    private Double totalPrice;
    private Integer guestCount;

    private String specialRequests;

    private LocalDate reservationTimestamp;

    private String paymentType;

    private String cardNumber;

    // Méthode pour calculer la durée de la réservation en jours et mois
    public void calculateDuration() {
        if (startDate != null && endDate != null) {
            this.nightsDuration = (int) java.time.temporal.ChronoUnit.DAYS.between(startDate, endDate);
            this.monthsDuration = (int) java.time.temporal.ChronoUnit.MONTHS.between(startDate, endDate);
        }
    }
}