package ma.emsi.pfa2024_gestionsconcours.service;

import ma.emsi.pfa2024_gestionsconcours.dao.entities.Concours;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ConcoursManager {
    Concours addConcours(Concours concours);
    Concours updateConcours(Concours concours);

    Concours saveConcours(Concours concours);

    public boolean deleteConcoursById(Integer id);
    public List<Concours> getAllConcours();
    public Page<Concours> searchConcours(String keyword, int page, int size);
    public List<Concours> getByKeyword(String keyword);

    public Concours getConcoursById(Integer id);
    Page<Concours> getAllConcoursByPage(int page, int size);

}
