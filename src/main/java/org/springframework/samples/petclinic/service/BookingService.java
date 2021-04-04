package org.springframework.samples.petclinic.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Booking;
import org.springframework.samples.petclinic.model.Vet;
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
	public Booking findBookingByFecha(LocalDate hoy, int petId) {
		return bookingRepository.findBookingByFecha(hoy, petId);
	}
	
	
	
	// Añadido por AlvaroSC
		//Comprobar si funciona bien o si siquiera es necesario
	
	@Transactional
	public void deleteBookings(final Booking booking)  throws DataAccessException {
		this.bookingRepository.delete(booking);
    }
	
}
