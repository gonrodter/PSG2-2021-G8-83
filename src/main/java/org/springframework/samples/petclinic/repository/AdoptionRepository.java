package org.springframework.samples.petclinic.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.petclinic.model.Adoption;
import org.springframework.samples.petclinic.model.BaseEntity;
import org.springframework.samples.petclinic.model.Booking;
import org.springframework.samples.petclinic.model.Visit;

public interface AdoptionRepository extends CrudRepository<Adoption, Integer> {
	
	@Query(value = "SELECT * FROM adoptions WHERE STATUS = FALSE AND PET_ID=?1",
			nativeQuery = true)
	Adoption findAdoptionByStatus(int petId) throws DataAccessException;

	
}
