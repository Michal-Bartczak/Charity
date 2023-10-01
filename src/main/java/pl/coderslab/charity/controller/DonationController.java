package pl.coderslab.charity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.entity.Donation;
import pl.coderslab.charity.service.CategoryService;
import pl.coderslab.charity.service.DonationService;
import pl.coderslab.charity.service.InstitutionService;
import pl.coderslab.charity.service.UserService;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

@Controller
@RequestMapping("/user")
public class DonationController {
    private final CategoryService categoryService;
    private final InstitutionService institutionService;

    private final DonationService donationService;
    private final UserService userService;

    public DonationController(CategoryService categoryService, InstitutionService institutionService, DonationService donationService, UserService userService) {
        this.categoryService = categoryService;
        this.institutionService = institutionService;
        this.donationService = donationService;
        this.userService = userService;
    }

    @GetMapping("/form")
    public String getFormForDonation(Model model){
        model.addAttribute("categoriesThings", categoryService.getAllCategories());
        model.addAttribute("institutions", institutionService.findAllInstitution());
        model.addAttribute("donationForm", new Donation());
        model.addAttribute("username", userService.getCurrentUsernameForCustomer());
        return "form";
    }
    @PostMapping("/form")
    public String addFormDonation(@Valid Donation donation,
                                  BindingResult bindingResult){
        if (bindingResult.hasErrors()){

            return "form";
        }
        donationService.addUsernameToDonation(donation);
        donationService.saveDonation(donation);

        return "redirect:/user/form-confirm";
    }
    @GetMapping("/form-confirm")
    public String getConfirmForm(Model model) {
        model.addAttribute(userService.getCurrentUsernameForCustomer());
        return "form-confirm";
    }

    @GetMapping("/collections")
    public String getCollections(Model model){
        model.addAttribute("username",userService.getCurrentUsernameForCustomer());
        model.addAttribute("donations", donationService.getAllDonationForUsername(userService.getCurrentUsernameForCustomer()));
        return "ownCollections";
    }
    @GetMapping("/details/{id}")
    public String getDetails(@PathVariable("id") Long donationId,
                             Model model){
        model.addAttribute("username",userService.getCurrentUsernameForCustomer());
        model.addAttribute("donationDetails", donationService.findDonationById(donationId));
        return "donationDetails";
    }
}
