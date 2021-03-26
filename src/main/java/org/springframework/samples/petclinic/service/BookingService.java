package org.springframework.samples.petclinic.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Booking;
import org.springframework.samples.petclinic.repository.BookingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookingService {
	
private BookingRepository bookingRepository;	

	@Autowired
	public BookingService(BookingRepository bookingRepository) {
		this.bookingRepository = bookingRepository;
	}	
	
	@Transactional
    public void saveBooking(Booking booking) throws DataAccessException {
		bookingRepository.save(booking);      
    }
	
	@Transactional
	public void findBookingByFecha(LocalDate hoy, int petId) {
		bookingRepository.findBookingByFecha(hoy, petId);
	}
	
}
