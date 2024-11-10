package com.esprit.ms.annonce;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Annonce implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    private String titre;

    private String description;

    @NotNull
    private double prix;

    @NotNull
    private String type; // "location" ou "colocation"

    @NotNull
    private String adresse; // Adresse complète

    @NotNull
    private Integer nombreChambres; // Nombre de chambres (changed to Integer)

    @NotNull
    private Integer nombreSallesDeBain; // Nombre de salles de bain (changed to Integer)

    @ElementCollection
    private List<String> photos; // Liste d'URLs de photos

    @ElementCollection
    private List<String> equipements; // Équipements disponibles

    private String conditionsLocation; // Conditions de location

    @JsonFormat(pattern = "yyyy-MM-dd") // Format de date pour la sérialisation
    private LocalDate dateDisponibilite; // Date de disponibilité

    private String typePropriete; // Type de propriété

    private Double caution; // Montant de la caution (permet la valeur nulle)

    private String contactNom; // Nom du propriétaire

    @JsonIgnore // Masquer le téléphone et l'email du propriétaire
    private String contactTelephone; // Téléphone du propriétaire

    @JsonIgnore
    private String contactEmail; // Email du propriétaire
}
