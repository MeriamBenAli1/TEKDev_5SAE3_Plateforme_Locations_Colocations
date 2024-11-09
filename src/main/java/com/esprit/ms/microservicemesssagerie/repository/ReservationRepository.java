package com.esprit.ms.microservicemesssagerie.repository;

import com.esprit.ms.microservicemesssagerie.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
}
