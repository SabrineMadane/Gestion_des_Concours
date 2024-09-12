package ma.emsi.pfa2024_gestionsconcours.dao.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.io.File;
import java.util.List;

@Entity
@Table(name = "Candidat")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Candidat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nom;
    private String prenom;
    private String username;
    private String email;
    private String password;
    private String adresse;
    private String cne;
    private String cin;
    private String telephone;
    private String diplome;
    private double moyenne;


}
