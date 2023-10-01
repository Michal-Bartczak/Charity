package pl.coderslab.charity.service;

import org.springframework.stereotype.Service;
import pl.coderslab.charity.entity.Donation;
import pl.coderslab.charity.repository.DonationRepository;

import java.util.*;

@Service
public class DonationService {

    private final DonationRepository donationRepository;
    private final UserService userService;

    public DonationService(DonationRepository donationRepository, UserService userService) {
        this.donationRepository = donationRepository;
        this.userService = userService;
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

    public Donation addUsernameToDonation(Donation donation){
       donation.setUsername( userService.getCurrentUsernameForCustomer());
        return donation;
    }
    public List<Donation> getAllDonationForUsername(String username){
       List<Donation> donations = donationRepository.findAllByUsername(username);
       return donations;

    }

    public Donation findDonationById(Long id) {
        Optional<Donation> donationOptional = donationRepository.findById(id);
        return donationOptional.orElse(new Donation()); // Zwróć obiekt Donation lub pusty obiekt nowy
    }
}
