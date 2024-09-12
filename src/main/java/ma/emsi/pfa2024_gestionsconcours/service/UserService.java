package ma.emsi.pfa2024_gestionsconcours.service;



import ma.emsi.pfa2024_gestionsconcours.dao.entities.User;
import ma.emsi.pfa2024_gestionsconcours.dao.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    public User getUserByEmail(String email){
        User user = userRepository.findUserByEmail(email);
        return user;
    }
    public User createUser(User user){
        User newUser = userRepository.save(user);
        userRepository.flush();
        return newUser;
    }


        public List<User> findUsers() {
            return userRepository.findAllUsers();
    }

//    public User deleteEtudiant(@RequestParam(name="id") int id ) { return userRepository.deleteById(id) ;}
    public User DeleteUser(int id){
        User user = userRepository.findUserById(id);
        userRepository.deleteById(id);
        System.out.println(user);
        return user;
    }
}


