package pl.coderslab.charity.controller;

import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.entity.Admin;
import pl.coderslab.charity.entity.Institution;
import pl.coderslab.charity.entity.User;
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
        return "/admin/admin-homepage";
    }



    @GetMapping("/institution/delete/{id}")
    public String getDeleteInstitutionConfirm(@PathVariable("id") Long institutionId,
                                              Model model) {
        model.addAttribute("adminName", adminService.getCurrentUsernameForAdmin());
        model.addAttribute("institution", institutionService.getInstitutionById(institutionId));
        return "/admin/institution-action/confirm-delete-institution";
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
        return "/admin/institution-action/show-details-institution";
    }

    @GetMapping("/institution/edit/{id}")
    public String showEditFormInstitution(@PathVariable("id") Long institutionId,
                                          Model model) {
        model.addAttribute("adminName", adminService.getCurrentUsernameForAdmin());
        model.addAttribute("institutionDetails", institutionService.getInstitutionById(institutionId));
        model.addAttribute("institution", new Institution());
        return "/admin/institution-action/edit-details-institution";
    }

    @PostMapping("/institution/edit/{id}")
    public String getEditFormInstitution(@PathVariable("id") Long institutionId,
                                         Model model,
                                         @ModelAttribute("institution")
                                         @Valid Institution institution,
                                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("adminName", adminService.getCurrentUsernameForAdmin());
            return "/admin/institution-action/edit-details-institution";
        }
        institutionService.saveInstitution(institution);
        return "redirect:/admin/homepage";
    }
    @GetMapping("/institution/add")
    public String getInstitutionFormForAdd(Model model){
        model.addAttribute("adminName", adminService.getCurrentUsernameForAdmin());
        model.addAttribute("institution", new Institution());
        return "/admin/institution-action/add-institution";
    }
    @PostMapping("/institution/add")
    public String addInstitution(@Valid Institution institution,
                                 BindingResult bindingResult,
                                 Model model){
        if (bindingResult.hasErrors()){
            model.addAttribute("adminName", adminService.getCurrentUsernameForAdmin());
            return "/admin/institution-action/add-institution";
        }
        institutionService.saveInstitution(institution);
        return "redirect:/admin/homepage";
    }

    @GetMapping("admins-list")
    public String getAllAdminsList(Model model){
        model.addAttribute("adminName", adminService.getCurrentUsernameForAdmin());
        model.addAttribute("adminsList", adminService.getAllAdmins());
        return "/admin/admins-action/list";
    }
    @GetMapping("/admin-add")
    public String getAdminFormForAdd(Model model){
        model.addAttribute("adminName", adminService.getCurrentUsernameForAdmin());
        model.addAttribute("adminForm", new Admin());
        return "/admin/admins-action/add";
    }
    @PostMapping("/admin-add")
    public String addAdmin(@ModelAttribute("adminForm") @Valid Admin admin,
                           BindingResult bindingResult,
                           Model model){
        if (bindingResult.hasErrors()){
            model.addAttribute("adminName", adminService.getCurrentUsernameForAdmin());
            return "/admin/admins-action/add";
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
        return "/admin/admins-action/edit";
    }
    //czy te id są konieczne ?
    @PostMapping("/admin-edit/{id}")
    public String editAdmin(@PathVariable("id") Long adminId,
                            Model model,
                            @ModelAttribute("adminEditForm") @Valid
                            Admin admin, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "/admin/admins-action/edit";
        }
        adminService.saveAdminWithoutPassword(admin);
        return "redirect:/admin/admins-list";
    }
    @GetMapping("/admin-delete/{id}")
    public String getDeleteAdminConfirm(@PathVariable("id") Long adminId,
                                        Model model){
        model.addAttribute("adminName", adminService.getCurrentUsernameForAdmin());
        model.addAttribute("admin", adminId);
        return "/admin/admins-action/delete";
    }
    @PostMapping("admin-delete/{id}")
    public String deleteAdmin(@PathVariable("id") Long adminId,
                              Model model){
        if (adminService.checkDeleteAdminWithLogInAdmin(adminId)){
            return "redirect:/admin/admins-list";
        }
        model.addAttribute("adminName", adminService.getCurrentUsernameForAdmin());
        return "/admin/admins-action/delete-error";
    }
    @GetMapping("/users-list")
    public String getUsersList(Model model) {
        model.addAttribute("adminName", adminService.getCurrentUsernameForAdmin());
        model.addAttribute("usersList", userService.getAllUsers());
        return "/admin/users-action/users-list-admin";
    }
    @GetMapping("/user-details/{id}")
    public String editFormUserDetails(@PathVariable("id") Long userId,
                                 Model model){
        model.addAttribute("adminName", adminService.getCurrentUsernameForAdmin());
        model.addAttribute("userId", userId);
        model.addAttribute("user", new User());
        return "/admin/users-action/edit-details";
    }
    @PostMapping("/user-details/{id}")
    public String editUserDetails(@PathVariable("id") Long userId,
                                  @ModelAttribute("user") @Valid User user, BindingResult bindingResult,
                                  Model model){
        if(bindingResult.hasErrors()){
            return "/admin/users-action/edit-details";
        }
        userService.saveUserWithoutPassword(user);
        return"redirect:/admin/users-list";
    }
    @GetMapping("/user-add")
    public String getFormUserAdd(Model model){
        model.addAttribute("adminName", adminService.getCurrentUsernameForAdmin());
        model.addAttribute("userForm", new User());
        return "/admin/users-action/add";
    }
    @PostMapping("/user-add")
    public String addUser(@ModelAttribute("userForm") @Valid
                          User user,
                          BindingResult bindingResult,
                          Model model){
        if (bindingResult.hasErrors()){
            return"/admin/users-action/add";
        }
        userService.saveUser(user);
        return "redirect:/admin/users-list";
    }

}
