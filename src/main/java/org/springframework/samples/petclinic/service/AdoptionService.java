package org.springframework.samples.petclinic.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Adoption;
import org.springframework.samples.petclinic.repository.AdoptionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class AdoptionService {
	
private AdoptionRepository adoptionRepository;	

	@Autowired
	public AdoptionService(AdoptionRepository adoptionRepository) {
		this.adoptionRepository = adoptionRepository;
	}	
	
	@Transactional
    public void saveAdoption(Adoption adoption) throws DataAccessException {
		adoptionRepository.save(adoption);      
    }
	
	
	@Transactional
	public Adoption findAdoptionByStatus(int petId) {
		return adoptionRepository.findAdoptionByStatus(petId);
	}
	
	@Transactional(readOnly = true)
    public List<Adoption> findActiveByStatus() throws DataAccessException {
		return adoptionRepository.findActiveByStatus();
    }
	
	
}
