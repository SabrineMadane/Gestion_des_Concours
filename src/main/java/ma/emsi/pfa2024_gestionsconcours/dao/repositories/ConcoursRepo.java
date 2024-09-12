package ma.emsi.pfa2024_gestionsconcours.dao.repositories;

import ma.emsi.pfa2024_gestionsconcours.dao.entities.Concours;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ConcoursRepo extends JpaRepository<Concours, Integer> {
    Page<Concours> findByNomContains(String keyword, Pageable pageable);

}
