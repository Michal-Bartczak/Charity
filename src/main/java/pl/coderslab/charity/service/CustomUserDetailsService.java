package pl.coderslab.charity.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.coderslab.charity.entity.Admin;
import pl.coderslab.charity.entity.BaseUser;
import pl.coderslab.charity.entity.User;
import pl.coderslab.charity.exception.UnknownUserTypeException;
import pl.coderslab.charity.repository.AdminRepository;
import pl.coderslab.charity.repository.UserRepository;

import java.util.Collection;
import java.util.Collections;
@Service

public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;
    private final AdminRepository adminRepository;

    public CustomUserDetailsService(UserRepository userRepository, AdminRepository adminRepository) {
        this.userRepository = userRepository;
        this.adminRepository = adminRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        BaseUser user = findUserByUsername(username);
        if (user == null){
            throw new UsernameNotFoundException("Użytkownik z tym adresem email nie istnieje w bazie");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), getAuthorities(user));
    }


    public BaseUser findUserByUsername(String username) {
        BaseUser user;
        user = adminRepository.findByUsername(username);
        if (user != null){
            return user;
        }
        user = userRepository.findByUsername(username);
        return user;
    }

    private Collection<? extends GrantedAuthority> getAuthorities(BaseUser user){
        String role;

        if (user instanceof Admin){
            role = "ADMIN";
        } else if( user instanceof User){
            role = "USER";
        } else {
            throw new UnknownUserTypeException("Rola użytkownika nie jest zdefiniowana");
        }
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + role));
    }
}












