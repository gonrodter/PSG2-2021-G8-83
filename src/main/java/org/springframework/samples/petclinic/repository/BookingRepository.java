package org.springframework.samples.petclinic.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.petclinic.model.BaseEntity;
import org.springframework.samples.petclinic.model.Booking;
import org.springframework.samples.petclinic.model.Visit;

public interface BookingRepository extends CrudRepository<Booking, String> {
	
	List<Booking> findByPetId(Integer petId);
	
	@Query(value = "SELECT * FROM BOOKING WHERE (CAST(CHECK_OUT AS date) >= CAST(?1 AS date)) AND PET_ID=?2",
			nativeQuery = true)
	Booking findBookingByFecha(LocalDate hoy, int petId) throws DataAccessException;

	
	
	
	
	
	//Implementado por AlvaroSC
	/**
	 * Delete a <code>Booking</code> to the data store.
	 * 
	 * @param Booking the <code>Booking</code> to delete
	 * @see BaseEntity#isNew
	 */
	
	
}
