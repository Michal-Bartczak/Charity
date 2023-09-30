package pl.coderslab.charity.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
@Component
@Getter
@Setter
public class RegistrationForm {
    @Email
    private String email;

    @Size(min=5, max=20,message = "Nazwa użytkownika musi być od 5 do 20 znaków")
    private String username;
    @Size(min = 8, max=20, message = "Hasło musi być od 8 do 20 znaków")
    private String password;
    @Size(min = 8, max=20, message = "Hasło musi być od 8 do 20 znaków")
    private String confirmPassword;
}
