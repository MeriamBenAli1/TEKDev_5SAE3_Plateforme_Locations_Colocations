package com.esprit.ms.annonce;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/annonces")
public class AnnonceRestAPI {
    @Autowired
    private AnnonceService annonceService;

    @PostMapping
    public ResponseEntity<Annonce> createAnnonce(@RequestBody Annonce annonce) {
        Annonce savedAnnonce = annonceService.addAnnonce(annonce);
        return ResponseEntity.ok(savedAnnonce);
    }

    @GetMapping
    public ResponseEntity<List<Annonce>> getAllAnnonces() {
        System.out.println("Hello");
        List<Annonce> annonces = annonceService.getAllAnnonces();
        return ResponseEntity.ok(annonces);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Annonce> getAnnonceById(@PathVariable int id) {
        Annonce annonce = annonceService.getAnnonceById(id);
        return ResponseEntity.ok(annonce);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Annonce> updateAnnonce(@PathVariable int id, @RequestBody Annonce updatedAnnonce) {
        Annonce annonce = annonceService.updateAnnonce(id, updatedAnnonce);
        return ResponseEntity.ok(annonce);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAnnonce(@PathVariable int id) {
        annonceService.deleteAnnonce(id);
        return ResponseEntity.noContent().build();
    }
}
