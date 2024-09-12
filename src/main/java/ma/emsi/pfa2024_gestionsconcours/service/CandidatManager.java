package ma.emsi.pfa2024_gestionsconcours.service;

import ma.emsi.pfa2024_gestionsconcours.dao.entities.Candidat;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CandidatManager {
    public Candidat saveCandidat(Candidat candidat) ;

    public Candidat addCandidat(Candidat candidat);
    public void deleteCandidatById(Integer id);
    public Candidat updateCandidat(Candidat candidat);
    public List<Candidat> getAllCandidat();

    public Candidat getCandidatById(Integer id);

    void registerUser(Candidat user);

    public Page<Candidat> searchCandidats(String keyword, int page, int size);
    public List<Candidat> getByKeyword(String keyword);
    Page<Candidat> getAllCandidatByPage(int page, int size);
}
