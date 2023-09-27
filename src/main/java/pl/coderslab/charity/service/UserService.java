package pl.coderslab.charity.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.coderslab.charity.entity.User;
import pl.coderslab.charity.model.RegistrationForm;
import pl.coderslab.charity.repository.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean checkConfirmPassword(RegistrationForm registrationForm){
        return registrationForm.getPassword().equals(registrationForm.getConfirmPassword());
    }
    public void saveUser(User user){
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public boolean isEmailAlreadyInUse(User user) {
        User existingUser = userRepository.findByEmail(user.getEmail());
        return existingUser == null;
    }
}
