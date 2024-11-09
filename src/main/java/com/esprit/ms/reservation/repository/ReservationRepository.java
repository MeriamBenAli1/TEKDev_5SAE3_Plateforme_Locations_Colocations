package com.esprit.ms.microservicemesssagerie.repository;

import com.esprit.ms.microservicemesssagerie.entity.Reservation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends CrudRepository<Reservation,Long> {
}