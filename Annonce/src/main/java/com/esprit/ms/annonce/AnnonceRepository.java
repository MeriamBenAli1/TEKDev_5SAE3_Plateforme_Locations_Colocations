package com.esprit.ms.annonce;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AnnonceRepository extends JpaRepository<Annonce, Integer> {
    // Tu peux ajouter d'autres méthodes de recherche si nécessaire
}
