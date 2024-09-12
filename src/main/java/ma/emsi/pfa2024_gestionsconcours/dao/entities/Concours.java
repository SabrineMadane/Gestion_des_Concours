package ma.emsi.pfa2024_gestionsconcours.dao.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.domain.Page;

import java.io.File;
import java.util.List;

@Entity
@Table(name = "Concours")
@Getter
@Setter
@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Concours  {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nom;
    private String reference;
    private String description;
    private int nbreEtudiantAdmis;
    private String Status;
    private int annee;
    private File image;

}
