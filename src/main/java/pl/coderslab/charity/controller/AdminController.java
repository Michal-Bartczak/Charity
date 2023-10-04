package pl.coderslab.charity.controller;

import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.entity.Admin;
import pl.coderslab.charity.entity.Institution;
import pl.coderslab.charity.service.AdminService;
import pl.coderslab.charity.service.DonationService;
import pl.coderslab.charity.service.InstitutionService;
import pl.coderslab.charity.service.UserService;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final AdminService adminService;
    private final DonationService donationService;
    private final UserService userService;
    private final InstitutionService institutionService;

    public AdminController(AdminService adminService, DonationService donationService, UserService userService, InstitutionService institutionService) {
        this.adminService = adminService;
        this.donationService = donationService;
        this.userService = userService;
        this.institutionService = institutionService;
    }

    @GetMapping("/homepage")
    public String getHomepageAdmin(Model model) {
        model.addAttribute("adminName", adminService.getCurrentUsernameForAdmin());
        model.addAttribute("institutions", institutionService.findAllInstitutions());
        return "admin-homepage";
    }

    @GetMapping("/users-list")
    public String getUsersList(Model model) {
        model.addAttribute("adminName", adminService.getCurrentUsernameForAdmin());
        model.addAttribute("usersList", userService.getAllUsers());
        return "users-list-admin";
    }

    @GetMapping("/institution/delete/{id}")
    public String getDeleteInstitutionConfirm(@PathVariable("id") Long institutionId,
                                              Model model) {
        model.addAttribute("adminName", adminService.getCurrentUsernameForAdmin());
        model.addAttribute("institution", institutionService.getInstitutionById(institutionId));
        return "confirm-delete-institution";
    }

    @PostMapping("/institution/delete/{id}")
    public String deleteInstitution(@PathVariable("id") Long institutionId,
                                    Model model) {
        institutionService.removeInstitutionById(institutionId);
        return "redirect:/admin/homepage";
    }

    @GetMapping("/institution/show-details/{id}")
    public String getDetailsInstitution(@PathVariable("id") Long institutionId,
                                        Model model) {
        model.addAttribute("institutionDetails", institutionService.getInstitutionById(institutionId));
        model.addAttribute("adminName", adminService.getCurrentUsernameForAdmin());
        return "show-details-institution";
    }

    @GetMapping("/institution/edit/{id}")
    public String showEditFormInstitution(@PathVariable("id") Long institutionId,
                                          Model model) {
        model.addAttribute("adminName", adminService.getCurrentUsernameForAdmin());
        model.addAttribute("institutionDetails", institutionService.getInstitutionById(institutionId));
        model.addAttribute("institution", new Institution());
        return "edit-details-institution";
    }

    @PostMapping("/institution/edit/{id}")
    public String getEditFormInstitution(@PathVariable("id") Long institutionId,
                                         Model model,
                                         @ModelAttribute("institution")
                                         @Valid Institution institution,
                                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("adminName", adminService.getCurrentUsernameForAdmin());
            return "edit-details-institution";
        }
        institutionService.saveInstitution(institution);
        return "redirect:/admin/homepage";
    }
    @GetMapping("/institution/add")
    public String getInstitutionFormForAdd(Model model){
        model.addAttribute("adminName", adminService.getCurrentUsernameForAdmin());
        model.addAttribute("institution", new Institution());
        return "add-institution";
    }
    @PostMapping("/institution/add")
    public String addInstitution(@Valid Institution institution,
                                 BindingResult bindingResult,
                                 Model model){
        if (bindingResult.hasErrors()){
            model.addAttribute("adminName", adminService.getCurrentUsernameForAdmin());
            return "add-institution";
        }
        institutionService.saveInstitution(institution);
        return "redirect:/admin/homepage";
    }

    @GetMapping("admins-list")
    public String getAllAdminsList(Model model){
        model.addAttribute("adminName", adminService.getCurrentUsernameForAdmin());
        model.addAttribute("adminsList", adminService.getAllAdmins());
        return "/admins-action/list";
    }
    @GetMapping("/admin-add")
    public String getAdminFormForAdd(Model model){
        model.addAttribute("adminName", adminService.getCurrentUsernameForAdmin());
        model.addAttribute("adminForm", new Admin());
        return "/admins-action/add";
    }
    @PostMapping("/admin-add")
    public String addAdmin(@ModelAttribute("adminForm") @Valid Admin admin,
                           BindingResult bindingResult,
                           Model model){
        if (bindingResult.hasErrors()){
            model.addAttribute("adminName", adminService.getCurrentUsernameForAdmin());
            return "/admins-action/add";
        }
        adminService.cryptPasswordAdminAndSave(admin);
        return "redirect:/admin/admins-list";
    }
    @GetMapping("/admin-edit/{id}")
    public String getEditFormAdmin(Model model,
                                   @PathVariable("id") Long adminId){
        model.addAttribute("adminName", adminService.getCurrentUsernameForAdmin());
        model.addAttribute("adminEditForm", new Admin());
        model.addAttribute("adminId", adminId);
        return "/admins-action/edit";
    }
    //czy te id są konieczne ?
    @PostMapping("/admin-edit/{id}")
    public String editAdmin(@PathVariable("id") Long adminId,
                            Model model,
                            @ModelAttribute("adminEditForm") @Valid
                            Admin admin, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "/admins-action/edit";
        }
        adminService.saveAdminWithoutPassword(admin);
        return "redirect:/admin/admins-list";
    }
    @GetMapping("/admin-delete/{id}")
    public String getDeleteAdminConfirm(@PathVariable("id") Long adminId,
                                        Model model){
        model.addAttribute("adminName", adminService.getCurrentUsernameForAdmin());
        model.addAttribute("admin", adminId);
        return "/admins-action/delete";
    }
    @PostMapping("admin-delete/{id}")
    public String deleteAdmin(@PathVariable("id") Long adminId,
                              Model model){
        if (adminService.checkDeleteAdminWithLogInAdmin(adminId)){
            return "redirect:/admin/admins-list";
        }
        model.addAttribute("adminName", adminService.getCurrentUsernameForAdmin());
        return "/admins-action/delete-error";
    }


}
