package org.springframework.samples.petclinic.repository;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.petclinic.model.Adoption;


public interface AdoptionRepository extends CrudRepository<Adoption, Integer> {
	
	Adoption findAdoptionById(int adoptionId) throws DataAccessException;
	
	@Query(value = "SELECT * FROM adoptions WHERE STATUS = FALSE AND PET_ID=?1",
			nativeQuery = true)
	Adoption findAdoptionByStatus(int petId) throws DataAccessException;
	
	@Query(value = "SELECT * FROM adoptions WHERE STATUS = FALSE",
			nativeQuery = true)
	List<Adoption> findActiveByStatus() throws DataAccessException;
}
