package org.springframework.samples.petclinic.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Booking;
import org.springframework.samples.petclinic.model.Owner;
import org.springframework.samples.petclinic.model.Pet;
import org.springframework.samples.petclinic.service.BookingService;
import org.springframework.samples.petclinic.service.OwnerService;
import org.springframework.samples.petclinic.service.PetService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BookingController {
	
	private static final String VIEWS_BOOKINGS_CREATE_OR_UPDATE_FORM = "owners/createBookingForm";

	private final BookingService bookingService;
	private final OwnerService ownerService;
	private final PetService petService;
       
	@Autowired
	public BookingController(BookingService bookingService, OwnerService ownerService, PetService petService) {
		this.bookingService = bookingService;
		this.ownerService=ownerService;
		this.petService=petService;
	}
	
	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}
	
	@InitBinder("booking")
	public void initReservaBinder(WebDataBinder dataBinder) {
		dataBinder.setValidator(new BookingValidator());
	}

	@GetMapping(value = "/owners/*/pets/{petId}/booking/new")
	public String initCreationForm(@PathVariable("petId") int petId, ModelMap model) {
		Booking booking=new Booking();
		model.put("booking", booking);
		return VIEWS_BOOKINGS_CREATE_OR_UPDATE_FORM;
	}

	@PostMapping(value = "/owners/{ownerId}/pets/{petId}/booking/new")
	public String processCreationForm(@PathVariable("ownerId") int ownerId, @PathVariable("petId") int petId,
			@Valid Booking booking, BindingResult result) {
		if (result.hasErrors()) {
			return VIEWS_BOOKINGS_CREATE_OR_UPDATE_FORM;
		}
		else {
			Pet pet=petService.findPetById(petId);
			Owner owner= ownerService.findOwnerById(ownerId);
			booking.setOwner(owner);
			booking.setPet(pet);
			this.bookingService.saveBooking(booking);
			return "redirect:/owners/find";
		}
	}

	

}
