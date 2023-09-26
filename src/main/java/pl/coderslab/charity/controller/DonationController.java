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

import javax.validation.Valid;

@Controller
@RequestMapping("/form")
public class DonationController {
    private final CategoryService categoryService;
    private final InstitutionService institutionService;

    private final DonationService donationService;

    public DonationController(CategoryService categoryService, InstitutionService institutionService, DonationService donationService) {
        this.categoryService = categoryService;
        this.institutionService = institutionService;
        this.donationService = donationService;
    }

    @GetMapping("")
    public String getFormForDonation(Model model){
        model.addAttribute("categoriesThings", categoryService.getAllCategories());
        model.addAttribute("institutions", institutionService.findAllInstitution());
        model.addAttribute("donationForm", new Donation());
        return "form";
    }
    @PostMapping("")
    public String addFormDonation(@Valid Donation donation,
                                  BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            System.out.println(donation);
            return "form";
        }
        donationService.saveDonation(donation);
        System.out.println(donation);
        return "form-confirm";
    }
}
