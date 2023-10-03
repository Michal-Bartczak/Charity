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

import javax.servlet.http.HttpSession;
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
        model.addAttribute("donation", new Donation());
        model.addAttribute("username", userService.getCurrentUsernameForUser());
        return "form";
    }
    @PostMapping("/form")
    public String addFormDonation(@Valid Donation donation,
                                  BindingResult bindingResult,
                                  Model model,
                                  HttpSession session){
        if (bindingResult.hasErrors()){
             model.addAttribute("username", userService.getCurrentUsernameForUser());
            return "form";
        }
        donationService.addUserToDonation(donation);
        donationService.saveDonation(donation);
        session.setAttribute("username",userService.getCurrentUsernameForUser());
        return "redirect:/user/form-confirm";
    }
    @GetMapping("/form-confirm")
    public String getConfirmForm(Model model) {
        model.addAttribute(userService.getCurrentUsernameForUser());
        return "form-confirm";
    }

    @GetMapping("/collections")
    public String getCollections(Model model){
        model.addAttribute("username",userService.getCurrentUsernameForUser());
        model.addAttribute("donations", donationService.getAllDonationForUser(userService.getCurrentUserObject()));
        return "ownCollections";
    }
    @GetMapping("/details/{id}")
    public String getDetails(@PathVariable("id") Long donationId,
                             Model model){
        model.addAttribute("username",userService.getCurrentUsernameForUser());
        model.addAttribute("donationDetails", donationService.findDonationById(donationId));
        return "donationDetails";
    }
    @GetMapping("/delete/{id}")
    public String confirmDelete(@PathVariable("id") Long donationId,
                                Model model){
        model.addAttribute("username",userService.getCurrentUsernameForUser());
        model.addAttribute("donationId", donationId);
        return "delete-confirm";
    }
    @PostMapping("/delete/{id}")
    public String deleteCollection(@PathVariable("id") Long id,
                                   HttpSession session){
        donationService.deleteDonation(id);
        session.setAttribute("username",userService.getCurrentUsernameForUser());
        return "redirect:/user/collections";
    }
}
