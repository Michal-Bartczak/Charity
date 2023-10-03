package pl.coderslab.charity.service;

import org.springframework.stereotype.Service;
import pl.coderslab.charity.entity.Donation;
import pl.coderslab.charity.entity.User;
import pl.coderslab.charity.repository.DonationRepository;
import pl.coderslab.charity.repository.UserRepository;

import java.util.*;

@Service
public class DonationService {

    private final DonationRepository donationRepository;
    private final UserService userService;
    private final UserRepository userRepository;

    public DonationService(DonationRepository donationRepository, UserService userService, UserRepository userRepository) {
        this.donationRepository = donationRepository;
        this.userService = userService;
        this.userRepository = userRepository;
    }

    public long getTotalQuantityOfDonations(){
        return donationRepository.getTotalQuantityOfDonations().orElse(0L);
    }

    public long getCountDonations(){
       return donationRepository.count();
    }

    public void saveDonation(Donation donation){
        donationRepository.save(donation);
    }

    public Donation addUserToDonation(Donation donation){
       donation.setUser(userRepository.findByUsername(userService.getCurrentUsernameForUser()));
        return donation;
    }
    public List<Donation> getAllDonationForUser(User user){
       return donationRepository.findAllByUser(user);
    }

    public Donation findDonationById(Long id) {
        Optional<Donation> donationOptional = donationRepository.findById(id);
        return donationOptional.orElse(new Donation()); // Zwróć obiekt Donation lub pusty obiekt nowy
    }

    public void deleteDonation(Long id){
        donationRepository.deleteById(id);
    }

    public List<Donation> findAllDonation(){
        return donationRepository.findAll();
    }
}
