package ma.emsi.pfa2024_gestionsconcours.service;

import ma.emsi.pfa2024_gestionsconcours.dao.entities.Concours;
import ma.emsi.pfa2024_gestionsconcours.dao.repositories.ConcoursRepo;
import org.hibernate.boot.spi.ClassLoaderAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConcoursService implements ConcoursManager{

    @Autowired
    ConcoursRepo concoursRepository;
    @Override
    public Concours addConcours(Concours concours) {
        try{
            return  concoursRepository.save(concours);
        }catch (Exception exception){
            System.out.println(exception.getMessage());
            return null;
        }
    }

    @Override
    public Concours updateConcours(Concours concours) {
        return concoursRepository.save(concours);
    }

    @Override
    public Concours saveConcours(Concours concours) {
        return concoursRepository.save(concours);
    }

    @Override
    public boolean deleteConcoursById(Integer id) {
        concoursRepository.deleteById(id);
        return false;
    }

    @Override
    public List<Concours> getAllConcours() {
        return concoursRepository.findAll();
    }

    @Override
    public Page<Concours> searchConcours(String keyword, int page, int size) {
        return concoursRepository.findByNomContains(keyword, PageRequest.of(page, size));
    }

    @Override
    public List<Concours> getByKeyword(String keyword) {
        Page<Concours> page = concoursRepository.findByNomContains(keyword, PageRequest.of(0, 5));
        return page.getContent();
    }

    @Override
    public Concours getConcoursById(Integer id) {
        return concoursRepository.findById(id).orElse(null);
    }

    @Override
    public Page<Concours> getAllConcoursByPage(int page, int size) {
        return concoursRepository.findAll(PageRequest.of(page, size));
    }
}
