package com.esprit.ms.microservicemesssagerie.service;

import com.esprit.ms.microservicemesssagerie.entity.Reservation;

import java.util.List;
import java.util.Optional;

public interface IReservationService {
    Reservation saveReservation(Reservation reservation);

    Optional<Reservation> getReservationById(Integer id);

    List<Reservation> getAllReservations();

    Reservation updateReservation(Integer id, Reservation reservationDetails);

    void deleteReservation(Integer id);

    double calculateTotalPrice(Reservation reservation);
}
