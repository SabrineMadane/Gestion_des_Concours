package ma.emsi.pfa2024_gestionsconcours.service;

import ma.emsi.pfa2024_gestionsconcours.dao.entities.Administrateur;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface AdministrateurManager {
    public Administrateur addAdministrateur(Administrateur administrateur);
    public boolean deleteAdministrateur(Administrateur administrateur);
    public boolean deleteAdministrateurById(Integer id);
    public Administrateur updateAdministrateur(Administrateur administrateur);
    public List<Administrateur> getAllAdministrateur();

    public Administrateur getAdministrateurById(Integer id);
}
