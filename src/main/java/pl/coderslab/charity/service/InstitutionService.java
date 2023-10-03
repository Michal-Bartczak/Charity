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

    public List<Institution> findAllInstitutions(){
        return institutionRepository.findAll();
    }
    public Institution getInstitutionById(Long id){
        return institutionRepository.findById(id).orElse(new Institution());
    }
    public void removeInstitutionById(Long id){
        institutionRepository.delete(getInstitutionById(id));
    }
    public void saveEditInstitution(Institution institution){
        institutionRepository.save(institution);
    }
}
