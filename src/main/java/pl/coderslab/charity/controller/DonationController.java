package pl.coderslab.charity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.entity.Donation;
import pl.coderslab.charity.service.CategoryService;
import pl.coderslab.charity.service.DonationService;
import pl.coderslab.charity.service.InstitutionService;
import pl.coderslab.charity.service.UserService;

import javax.validation.Valid;

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
            System.out.println(donation);
            return "form";
        }
        donationService.saveDonation(donation);
        System.out.println(donation);
        return "redirect:/user/form-confirm";
    }
    @GetMapping("/form-confirm")
    public String getConfirmForm(){
        return "form-confirm";
    }
}
