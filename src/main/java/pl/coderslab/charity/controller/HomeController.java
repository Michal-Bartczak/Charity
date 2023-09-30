package pl.coderslab.charity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.model.RegistrationForm;
import pl.coderslab.charity.model.RegistrationFormToUserConverter;
import pl.coderslab.charity.service.DonationService;
import pl.coderslab.charity.service.InstitutionService;
import pl.coderslab.charity.service.UserService;

import javax.validation.Valid;


@Controller
public class HomeController {

    private final InstitutionService institutionService;
    private final DonationService donationService;
    private final UserService userService;
    private final RegistrationFormToUserConverter registrationFormToUserConverter;

    public HomeController(InstitutionService institutionService, DonationService donationService, UserService userService, RegistrationFormToUserConverter registrationFormToUserConverter) {
        this.institutionService = institutionService;
        this.donationService = donationService;
        this.userService = userService;
        this.registrationFormToUserConverter = registrationFormToUserConverter;
    }


    @RequestMapping("/")
    public String homeAction(Model model) {
        model.addAttribute("institutions", institutionService.findAllInstitution());
        model.addAttribute("donationsQuantity", donationService.getTotalQuantityOfDonations());
        model.addAttribute("countDonations", donationService.getCountDonations());
        return "index";
    }

    @GetMapping("/register")
    public String registrationAction(Model model) {
        model.addAttribute("registrationForm", new RegistrationForm());
        return "register";
    }

    @PostMapping("/register")
    public String getRegistrationForm(@Valid @ModelAttribute("registrationForm") RegistrationForm registrationForm,
                                      BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "register";
        }

        if (userService.checkConfirmPassword(registrationForm)) {
            if (!userService.isUsernameAlreadyInUse(registrationFormToUserConverter.convert(registrationForm))) {
                userService.saveUser(registrationFormToUserConverter.convert(registrationForm));
                return "registration-confirm";
            } else {
                bindingResult.rejectValue("email", "email.busy", "Ten adres email jest już zajęty");
                return "register";
            }
        } else {
            bindingResult.rejectValue("confirmPassword", "password.mismatch", "Hasła nie pasują do siebie");
            return "register";
        }
    }

}
