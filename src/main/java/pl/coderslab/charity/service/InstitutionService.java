package pl.coderslab.charity.service;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.coderslab.charity.entity.Institution;
import pl.coderslab.charity.repository.InstitutionRepository;

import java.util.List;

@Service
public class InstitutionService {
    private final InstitutionRepository institutionRepository;

    public InstitutionService(InstitutionRepository institutionRepository) {
        this.institutionRepository = institutionRepository;
    }

    public List<Institution> findAllInstitution(){
        return institutionRepository.findAll(Pageable.ofSize(4)).toList();
    }
}
