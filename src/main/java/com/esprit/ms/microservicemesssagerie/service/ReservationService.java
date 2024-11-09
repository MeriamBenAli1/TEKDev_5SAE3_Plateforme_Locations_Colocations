package com.esprit.ms.microservicemesssagerie.service;

import com.esprit.ms.microservicemesssagerie.entity.Reservation;
import com.esprit.ms.microservicemesssagerie.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservationService implements IReservationService {
    @Autowired
    private ReservationRepository reservationRepository;


    @Override
    public Reservation saveReservation(Reservation reservation) {
        reservation.calculateDuration();
        return reservationRepository.save(reservation);
    }


    @Override
    public Optional<Reservation> getReservationById(Integer id) {
        return reservationRepository.findById(id);
    }


    @Override
    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    @Override
    public Reservation updateReservation(Integer id, Reservation reservationDetails) {
        Optional<Reservation> existingReservationOpt = reservationRepository.findById(id);

        if (existingReservationOpt.isPresent()) {
            Reservation existingReservation = existingReservationOpt.get();

            // Mise à jour des informations de réservation
            existingReservation.setFirstName(reservationDetails.getFirstName());
            existingReservation.setLastName(reservationDetails.getLastName());
            existingReservation.setPhoneNumber(reservationDetails.getPhoneNumber());
            existingReservation.setEmail(reservationDetails.getEmail());
            existingReservation.setStartDate(reservationDetails.getStartDate());
            existingReservation.setEndDate(reservationDetails.getEndDate());
            existingReservation.setGuestCount(reservationDetails.getGuestCount());
            existingReservation.setSpecialRequests(reservationDetails.getSpecialRequests());
            existingReservation.setPaymentType(reservationDetails.getPaymentType());
            existingReservation.setCardNumber(reservationDetails.getCardNumber());

            // Recalculer la durée et le prix total
            existingReservation.calculateDuration();
            existingReservation.setTotalPrice(calculateTotalPrice(existingReservation));

            return reservationRepository.save(existingReservation);
        } else {
            throw new IllegalArgumentException("Reservation not found with id " + id);
        }
    }

    @Override
    public void deleteReservation(Integer id) {
        reservationRepository.deleteById(id);
    }


    @Override
    public double calculateTotalPrice(Reservation reservation) {
        double nightlyRate = 100.0; // Tarif de base par nuit
        return nightlyRate * reservation.getNightsDuration();
    }
}
