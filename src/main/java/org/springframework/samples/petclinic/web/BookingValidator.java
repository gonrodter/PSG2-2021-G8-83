package org.springframework.samples.petclinic.web;

import java.time.LocalDate;

import org.springframework.samples.petclinic.model.Booking;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class BookingValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Booking.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		Booking booking = (Booking) obj;
		LocalDate checkIn= booking.getCheckIn();
		LocalDate checkOut= booking.getCheckOut();
		LocalDate fechaActual = LocalDate.now();

		
		if (checkIn==null) {
			errors.rejectValue("checkIn", "Campo obligatorio", "Por favor, introduzca una fecha");
		} else if(checkIn.isBefore(fechaActual)) {
			errors.rejectValue("checkIn", "La fecha de la reserva no puede ser anterior al día de hoy", 
					"La fecha de la reserva no puede ser anterior al día de hoy");		
		} 
		
		if (checkOut==null) {
			errors.rejectValue("checkOut", "Campo obligatorio", "Por favor, introduzca una fecha");
		} else if(checkOut.isBefore(checkIn)) {
			errors.rejectValue("checkOut", "La fecha de fin de reserva  no puede ser anterior al fecha de inicio", 
					"La fecha de fin de reserva  no puede ser anterior al fecha de inicio");		
		} 
		
		}
		
	}



