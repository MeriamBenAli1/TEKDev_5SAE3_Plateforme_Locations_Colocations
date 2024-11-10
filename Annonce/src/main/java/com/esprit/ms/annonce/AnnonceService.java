package com.esprit.ms.annonce;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AnnonceService {
    @Autowired
    private AnnonceRepository annonceRepository;

    @Transactional
    public Annonce addAnnonce(Annonce annonce) {
        return annonceRepository.save(annonce);
    }

    @Transactional
    public List<Annonce> getAllAnnonces() {
        List<Annonce> annonces = annonceRepository.findAll();
        for (Annonce annonce : annonces) {
            // Initialiser les collections avant de les sérialiser
            Hibernate.initialize(annonce.getPhotos());
            Hibernate.initialize(annonce.getEquipements());
        }
        return annonces;
    }

    @Transactional
    public Annonce getAnnonceById(int id) {
        Annonce annonce = annonceRepository.findById(id).orElse(null);
        if (annonce != null) {
            // Initialiser les collections avant de les sérialiser
            Hibernate.initialize(annonce.getPhotos());
            Hibernate.initialize(annonce.getEquipements());
        }
        return annonce;
    }

    @Transactional
    public Annonce updateAnnonce(int id, Annonce updatedAnnonce) {
        updatedAnnonce.setId(id);
        return annonceRepository.save(updatedAnnonce);
    }

    public void deleteAnnonce(int id) {
        annonceRepository.deleteById(id);
    }
}
