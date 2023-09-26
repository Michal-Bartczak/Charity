package pl.coderslab.charity.service;

import org.springframework.stereotype.Service;
import pl.coderslab.charity.entity.Donation;
import pl.coderslab.charity.repository.DonationRepository;

@Service
public class DonationService {

    private final DonationRepository donationRepository;

    public DonationService(DonationRepository donationRepository) {
        this.donationRepository = donationRepository;
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
}