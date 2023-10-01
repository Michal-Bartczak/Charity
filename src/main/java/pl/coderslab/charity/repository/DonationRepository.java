package pl.coderslab.charity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.coderslab.charity.entity.Donation;

import java.util.List;
import java.util.Optional;
import java.util.Set;


@Repository
public interface DonationRepository extends JpaRepository<Donation, Long> {

    @Query("SELECT SUM(d.quantity) FROM Donation d")
    Optional<Long> getTotalQuantityOfDonations();

    List<Donation> findAllByUsername(String username);



}
