package ma.emsi.pfa2024_gestionsconcours.dao.repositories;

import ma.emsi.pfa2024_gestionsconcours.dao.entities.Candidat;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidatRepo extends JpaRepository<Candidat, Integer> {
    Page<Candidat> findByNomContains(String keyword, Pageable pageable);
}
