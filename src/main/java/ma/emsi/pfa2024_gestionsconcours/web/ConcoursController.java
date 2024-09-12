package ma.emsi.pfa2024_gestionsconcours.web;

import ma.emsi.pfa2024_gestionsconcours.dao.entities.Candidat;
import ma.emsi.pfa2024_gestionsconcours.dao.entities.Concours;
import ma.emsi.pfa2024_gestionsconcours.service.CandidatManager;
import ma.emsi.pfa2024_gestionsconcours.service.ConcoursManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ConcoursController {
    @Autowired
    CandidatManager candidatManager;

    @GetMapping("")
    public String accceuil1() {
        return "redirect:/login";
    }

    @GetMapping("/user/detailConcours")
    public String detailConcours(Model model) {
        return "detailConcours" ;}

    @GetMapping("/user/index")
    public String index(Model model) {
            return "index" ;}

    @GetMapping("/admin/indexAdmin")
    public String indexAdmin(Model model) {
        return "indexAdmin" ;
    }
    @Autowired
    ConcoursManager concoursManager;

    @RequestMapping("/ajouterConcours")
    public String showAjouterConcours(){
        return "AjouterConcours";
    }

    @RequestMapping("saveconcours")
    public String saveConcours(@ModelAttribute("concoursVue") Concours concoursController) {
        Concours saveConcours = concoursManager.saveConcours (concoursController);
        return "AjouterConcours";
    }

    @RequestMapping("/concoursList")
    public String clientList (ModelMap modelMap,
                              @RequestParam(name="page", defaultValue = "0") int page,
                              @RequestParam(name="size", defaultValue = "5") int size,
                              @RequestParam(name = "search", defaultValue = "") String keyword) {
        Page<Concours> concoursController = concoursManager.searchConcours(keyword, page, size);
        modelMap.addAttribute("concoursVue", concoursController.getContent());
        modelMap.addAttribute ( "concoursVue", concoursController);
        modelMap.addAttribute ( "currentPage", page);
        modelMap.addAttribute ( "pages", new int[concoursController.getTotalPages()]);
        modelMap.addAttribute("keyword", keyword);
        return "ConcoursList";
    }
    @GetMapping("/search")
    public String searchConcours(@RequestParam("keyword") String keyword, Model model) {
        List<Concours> concoursController = concoursManager.getByKeyword(keyword);
        model.addAttribute("concoursVue", keyword);
        model.addAttribute("concoursVue", concoursController);
        return "search";
    }
    @RequestMapping("/supprimerConcours")
    public String supprimerConcours (@RequestParam("id") int id, ModelMap modelMap) {
        concoursManager.deleteConcoursById(id);
        List<Concours> concoursController = concoursManager.getAllConcours();
        modelMap.addAttribute("concoursVue", concoursController);
        return "ConcoursList";
    }
    @RequestMapping("/modifierConcours")
    public String modifierConcours(@RequestParam("id") int id, ModelMap modelMap) {
        Concours concoursController = concoursManager.getConcoursById(id);
        modelMap.addAttribute( "concoursVue", concoursController);
        return "ModifierConcours";
    }
    @RequestMapping("/updateConcours")
    public String updateConcours(@ModelAttribute("concoursVue") Concours concoursController) {
        concoursManager.updateConcours(concoursController);
        return "redirect:/concoursList";
    }

    @GetMapping("/signup")
    public String showSignUpForm(ModelMap model) {
        model.addAttribute("candidat", new Candidat());
        return "signup";
    }
    @PostMapping("/signup")
    public String signUp(@ModelAttribute("candidat") Candidat user) {

        candidatManager.registerUser(user);
        return "/login";
    }
    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @GetMapping("/update/{id}")
    public String showUpdateConcours(@PathVariable("id") Integer id, Model model) {
        Concours concours = concoursManager.getConcoursById(id);
        model.addAttribute("concours", concours);
        return "update";
    }
    @PostMapping("/update/{id}")
    public String updateConcours(@PathVariable("id") Integer id, @ModelAttribute("creator") Concours updatedConcours) {
        Concours existingConcours = concoursManager.getConcoursById(id);
        if (existingConcours != null) {
            existingConcours.setNom(updatedConcours.getNom());
            existingConcours.setReference(updatedConcours.getReference());
            existingConcours.setDescription(updatedConcours.getDescription());
            existingConcours.setNbreEtudiantAdmis(updatedConcours.getNbreEtudiantAdmis());
            concoursManager.saveConcours(existingConcours);
        }
        return "redirect:/concoursList";}

    @RequestMapping("/ajouterCandidat")
    public String showAjouterCandidat(){
        return "AjouterCandidat";
    }
    @RequestMapping("/saveCandidat")
    public String saveCandidat(@ModelAttribute("candidatsVue") Candidat candidatController) {
        Candidat saveCandidat = candidatManager.saveCandidat (candidatController);
        return "AjouterCandidat";
    }

    @RequestMapping("/candidatList")
    public String candidatList (ModelMap modelMap,
                                @RequestParam(name="page", defaultValue = "0") int page,
                                @RequestParam(name="size", defaultValue = "5") int size,
                                @RequestParam(name = "search", defaultValue = "") String keyword) {
        Page<Candidat> candidatController = candidatManager.searchCandidats(keyword, page, size);
        modelMap.addAttribute("candidatsVue", candidatController.getContent());
        modelMap.addAttribute ( "candidatsVue", candidatController);
        modelMap.addAttribute ( "currentPage", page);
        modelMap.addAttribute ( "pages", new int[candidatController.getTotalPages()]);
        modelMap.addAttribute("keyword", keyword);
        return "CandidatList";
    }
    @GetMapping("/searchC")
    public String searchCandidats(@RequestParam("keyword") String keyword, Model model) {
        List<Candidat> candidatController = candidatManager.getByKeyword(keyword);
        model.addAttribute("candidatsVue", keyword);
        model.addAttribute("candidatsVue", candidatController);
        return "searchC";
    }
    @RequestMapping("/supprimerCandidat")
    public String supprimerCandidat (@RequestParam("id") int id,
                                     ModelMap modelMap) {
        candidatManager.deleteCandidatById(id);
        List<Candidat> candidatController = candidatManager.getAllCandidat();
        modelMap.addAttribute("candidatsVue", candidatController);
        return "CandidatList";
    }
    @RequestMapping("/modifierCandidat")
    public String modifierCandidat(@RequestParam("id") int id, ModelMap modelMap) {
        Candidat candidatController = candidatManager.getCandidatById(id);
        modelMap.addAttribute( "candidatsVue", candidatController);
        return "ModifierCandidat";
    }
    @RequestMapping("/updateCandidat")
    public String updateCandidat(@ModelAttribute("candidatsVue") Candidat candidatController) {
        candidatManager.updateCandidat(candidatController);
        return "redirect:/candidatList";
    }
    @PostMapping("/uploadCandidatFile")
    public String uploadCandidatFile(@RequestParam("file") MultipartFile file,
                                     RedirectAttributes redirectAttributes) {
        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Veuillez sélectionner un fichier à télécharger.");
            return "redirect:/ajouterCandidat";
        }

        try (BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            String line;
            List<Candidat> candidats = new ArrayList<>();

            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 11) {
                    Candidat candidat = new Candidat();
                    candidat.setNom(data[0]);
                    candidat.setPrenom(data[1]);
                    candidat.setUsername(data[2]);
                    candidat.setEmail(data[9]);
                    candidat.setPassword(data[10]);
                    candidat.setAdresse(data[7]);
                    candidat.setCne(data[3]);
                    candidat.setCin(data[4]);
                    candidat.setTelephone(data[5]);
                    candidat.setDiplome(data[8]);
                    candidat.setMoyenne(Double.parseDouble(data[6]));

                    candidats.add(candidat);
                }
            }

            candidats.forEach(candidatManager::saveCandidat);
            redirectAttributes.addFlashAttribute("message", "Vous avez téléchargé avec succès '" + file.getOriginalFilename() + "'.");

        } catch (IOException e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("message", "Échec du téléchargement de '" + file.getOriginalFilename() + "'.");
        }

        return "redirect:/ajouterCandidat";
    }


}
