package pl.coderslab.charity.model;

import org.springframework.stereotype.Component;
import pl.coderslab.charity.entity.User;

import org.springframework.core.convert.converter.Converter;

@Component
public class RegistrationFormToUserConverter implements Converter<RegistrationForm, User> {

    @Override
    public User convert(RegistrationForm registrationForm) {
        User user = new User();
        user.setUsername(registrationForm.getUsername());
        user.setEmail(registrationForm.getEmail());
        user.setPassword(registrationForm.getPassword());
        return user;
    }
}
