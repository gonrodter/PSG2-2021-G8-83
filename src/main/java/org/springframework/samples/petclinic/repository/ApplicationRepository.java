package org.springframework.samples.petclinic.repository;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.petclinic.model.Application;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationRepository extends CrudRepository<Application, Integer>{

	@Query("SELECT a FROM Application a WHERE a.adoption.status = FALSE AND a.adoption.pet.id=?1")
	List<Application> findApplicationByStatus(int petId) throws DataAccessException;
	
}
