package ma.emsi.pfa2024_gestionsconcours.service;

import org.springframework.stereotype.Service;
import ma.emsi.pfa2024_gestionsconcours.dao.entities.Administrateur;
import ma.emsi.pfa2024_gestionsconcours.dao.repositories.AdministrateurRepo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class AdministrateurService implements AdministrateurManager{
    @Autowired
    private AdministrateurRepo AdministrateurRepository;
    @Override
    public Administrateur addAdministrateur(Administrateur administrateur) {

        try{
            return  AdministrateurRepository.save(administrateur);
        }catch (Exception exception){
            System.out.println(exception.getMessage()); //Logger
            return null;
        }
    }

    @Override
    public boolean deleteAdministrateur(Administrateur administrateur) {
        return false;
    }

    @Override
    public boolean deleteAdministrateurById(Integer id) {
        return false;
    }

    @Override
    public Administrateur updateAdministrateur(Administrateur administrateur) {
        return null;
    }

    @Override
    public List<Administrateur> getAllAdministrateur() {
        return null;
    }

    @Override
    public Administrateur getAdministrateurById(Integer id) {
        return null;
    }
}
