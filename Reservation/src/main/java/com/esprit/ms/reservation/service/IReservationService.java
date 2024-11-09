package com.esprit.ms.reservation.service;

import com.esprit.ms.reservation.entity.Reservation;

import java.util.List;
import java.util.Optional;

public interface IReservationService {
    List<Reservation> getAllReservations();
    Optional<Reservation> getReservationById(Long id);
    Reservation createReservation(Reservation reservation);
    Reservation updateReservation(Long id, Reservation reservation);
    void deleteReservation(Long id);
}
