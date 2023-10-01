package pl.coderslab.charity.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.coderslab.charity.entity.Admin;
import pl.coderslab.charity.repository.AdminRepository;

@Service
public class AdminService {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final AdminRepository adminRepository;

    public AdminService(BCryptPasswordEncoder bCryptPasswordEncoder, AdminRepository adminRepository) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.adminRepository = adminRepository;
    }

    public void cryptPasswordAdminAndSave(Admin admin){
        admin.setPassword(bCryptPasswordEncoder.encode(admin.getPassword()));
        adminRepository.save(admin);
    }
}
