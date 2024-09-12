package ma.emsi.pfa2024_gestionsconcours.dao.repositories;

import ma.emsi.pfa2024_gestionsconcours.dao.entities.Administrateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdministrateurRepo extends JpaRepository<Administrateur, Integer> {
}
