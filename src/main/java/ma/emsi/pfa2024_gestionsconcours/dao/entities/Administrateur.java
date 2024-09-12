package ma.emsi.pfa2024_gestionsconcours.dao.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Administrateur")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Administrateur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nom;
    private String prenom;
    private String email;
    private String password;



    public Administrateur(int id,String email,String password){
        this.id=id;
        this.email=email;
        this.password=password;
    }
}
