package org.springframework.samples.petclinic.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Application;
import org.springframework.samples.petclinic.repository.ApplicationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ApplicationService {
	
	private ApplicationRepository applicationRepository;	

	@Autowired
	public ApplicationService(ApplicationRepository applicationRepository) {
		this.applicationRepository = applicationRepository;
	}
	
	@Transactional
    public void saveApplication(Application application) throws DataAccessException {
		applicationRepository.save(application);      
    }

	@Transactional
	public List<Application> findApplicationByStatus(int petId){
		return this.applicationRepository.findApplicationByStatus(petId);
	}
	
	@Transactional
	public Optional<Application> findById(int id){
		return this.applicationRepository.findById(id);
	}
	
	@Transactional
	public List<Application> findByAdoptionId(int adoptionId){
		return this.applicationRepository.findByAdoptionId(adoptionId);
	}
	
}
