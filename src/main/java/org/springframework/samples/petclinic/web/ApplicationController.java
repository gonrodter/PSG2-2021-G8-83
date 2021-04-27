package org.springframework.samples.petclinic.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Adoption;
import org.springframework.samples.petclinic.model.Application;
import org.springframework.samples.petclinic.model.Owner;
import org.springframework.samples.petclinic.model.Pet;
import org.springframework.samples.petclinic.service.AdoptionService;
import org.springframework.samples.petclinic.service.ApplicationService;
import org.springframework.samples.petclinic.service.OwnerService;
import org.springframework.samples.petclinic.service.PetService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ApplicationController {

	private final ApplicationService applicationService;
	private final PetService petService;
	private final OwnerService ownerService;
	private final AdoptionService adoptionService;
	
	@Autowired
	public ApplicationController(ApplicationService as, PetService ps, OwnerService os, AdoptionService ads) {
		this.applicationService = as;
		this.petService = ps;
		this.ownerService = os;
		this.adoptionService = ads;
	}

    @GetMapping(value = "/owners/{ownerId}/pets/{petId}/adoptionRequests")
    public String showAdoptionRequestList(@PathVariable("petId") int petId, ModelMap model) {
    	
    	try {
			Adoption a = this.adoptionService.findAdoptionByStatus(petId);
			Owner o = a.getOwner();
    	
	    	List<Application> apps = this.applicationService.findApplicationByStatus(petId);
	    	model.put("apps", apps);
	    	Pet pet = this.petService.findPetById(petId);
	 		model.put("pet", pet);
	    	return "owners/adoptionRequestList";
    	}catch(Exception e) {
    		return "redirect:/owners/profile2";
    	}
    	
    }
    
    @GetMapping(value = "/application/{applicationId}/apply")
    public String applyForAdoption(@PathVariable("applicationId") int applicationId, ModelMap model) {
    	
    	Optional<Application> appOpt = this.applicationService.findById(applicationId);
    	
    	if(!appOpt.isPresent()) return "redirect:/allAdoptions";
    	
    	Application app = appOpt.get();
    	Owner newOwner = app.getApplicant();
    	Owner oldOwner = app.getAdoption().getOwner();
    	Pet pet = app.getAdoption().getPet();
    	Adoption adoption = app.getAdoption();
    	
    	oldOwner.removePet(pet);
    	this.ownerService.saveOwner(oldOwner);
    	newOwner.addPet(pet);
    	this.ownerService.saveOwner(newOwner);
    	adoption.setStatus(true);
    	this.adoptionService.saveAdoption(adoption);
    	
    	return "redirect:/owners/profile";
    	
    }
	
}
