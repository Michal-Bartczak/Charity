package pl.coderslab.charity.service;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.coderslab.charity.entity.Admin;
import pl.coderslab.charity.entity.User;
import pl.coderslab.charity.model.RegistrationForm;
import pl.coderslab.charity.repository.UserRepository;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public boolean checkConfirmPassword(RegistrationForm registrationForm){
        return registrationForm.getPassword().equals(registrationForm.getConfirmPassword());
    }
    public void saveUser(User user){
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public boolean isUsernameAlreadyInUse(User user) {
        //zrób exist
        User existingUser = userRepository.findByUsername(user.getUsername());
        return existingUser != null;
    }

    public String getCurrentUsernameForUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            return ((UserDetails) principal).getUsername();
        } else {
            return principal.toString();
        }
    }
    public User getCurrentUserObject(){
      return userRepository.findByUsername(getCurrentUsernameForUser());
    }
    public Long getCurrentUserId(){
        return userRepository.findByUsername(getCurrentUsernameForUser()).getId();
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }
    public User getUserById(Long userId){
      return userRepository.findById(userId).orElse(new User());
    }
    public String getPasswordById(Long id){
        User user = userRepository.findById(id).orElse(new User());
        return user.getPassword();
    }
    public void saveUserWithoutPassword(User user){
        user.setPassword(getPasswordById(user.getId()));
        userRepository.save(user);
    }
}