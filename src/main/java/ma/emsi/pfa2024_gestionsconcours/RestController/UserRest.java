package ma.emsi.pfa2024_gestionsconcours.RestController;


import ma.emsi.pfa2024_gestionsconcours.dao.entities.User;
import ma.emsi.pfa2024_gestionsconcours.dao.repositories.UserRepository;
import ma.emsi.pfa2024_gestionsconcours.service.CandidatService;
import ma.emsi.pfa2024_gestionsconcours.service.ConcoursService;
import ma.emsi.pfa2024_gestionsconcours.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("Rest")
public class UserRest {
    @Autowired
    UserRepository userRepository;
    @Autowired private UserService userService;
    @Autowired private ConcoursService concoursService;
    @Autowired private CandidatService candidatService;

//    @GetMapping("/")
//    public ResponseEntity<?> ebHealth() {
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
//    @GetMapping("/health")
//    public ResponseEntity<?> health() {
//        return new ResponseEntity<>(HttpStatus.OK);
//    }

    @GetMapping(path="/getAllUser")
    public List<User> getAllUser()
    {
        System.out.println("getAllUser ");
        return userService.findUsers();
    }


    @DeleteMapping("/DeleteUser/{id}") // Methode Delete Doesn't Work
    public ResponseEntity<User> DeleteUser(@PathVariable int id)
    {
        User deletedUser = userService.DeleteUser(id);
        return new ResponseEntity<User>(deletedUser, HttpStatus.OK);
    }


}
