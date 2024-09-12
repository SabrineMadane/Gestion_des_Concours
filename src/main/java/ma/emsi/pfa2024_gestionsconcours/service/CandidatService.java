package ma.emsi.pfa2024_gestionsconcours.service;

import ma.emsi.pfa2024_gestionsconcours.dao.entities.Candidat;
import ma.emsi.pfa2024_gestionsconcours.dao.repositories.CandidatRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CandidatService implements CandidatManager{
    @Autowired
    CandidatRepo CandidatRepository;
    @Override
    public Candidat saveCandidat(Candidat candidat) {
        return CandidatRepository.save(candidat);
    }

    @Override
    public Candidat addCandidat(Candidat candidat) {
        try{
            return  CandidatRepository.save(candidat);
        }catch (Exception exception){
            System.out.println(exception.getMessage()); //Logger
            return null;
        }
    }

    @Override
    public void deleteCandidatById(Integer id) {
        CandidatRepository.deleteById(id);
    }

    @Override
    public Candidat updateCandidat(Candidat candidat) {
        return CandidatRepository.save(candidat);
    }

    @Override
    public List<Candidat> getAllCandidat() {
        return CandidatRepository.findAll();
    }

    @Override
    public Candidat getCandidatById(Integer id) {
        return CandidatRepository.findById(id).get();
    }

    @Override
    public void registerUser(Candidat user) {
        CandidatRepository.save(user);
    }

    @Override
    public Page<Candidat> searchCandidats(String keyword, int page, int size) {
        return CandidatRepository.findByNomContains(keyword, PageRequest.of(page, size));
    }

    @Override
    public List<Candidat> getByKeyword(String keyword) {
        Page<Candidat> page = CandidatRepository.findByNomContains(keyword, PageRequest.of(0, 5));
        return page.getContent();
    }

    @Override
    public Page<Candidat> getAllCandidatByPage(int page, int size) {
        return CandidatRepository.findAll(PageRequest.of(page, size));
    }
}
