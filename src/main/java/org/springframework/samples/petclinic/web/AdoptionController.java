package org.springframework.samples.petclinic.web;



import java.util.List;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Adoption;
import org.springframework.samples.petclinic.model.Adoptions;
import org.springframework.samples.petclinic.model.Application;
import org.springframework.samples.petclinic.model.Owner;
import org.springframework.samples.petclinic.model.Pet;
import org.springframework.samples.petclinic.model.User;
import org.springframework.samples.petclinic.service.AdoptionService;
import org.springframework.samples.petclinic.service.ApplicationService;
import org.springframework.samples.petclinic.service.OwnerService;
import org.springframework.samples.petclinic.service.PetService;
import org.springframework.samples.petclinic.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AdoptionController {
	
	private static final String VIEWS_PETS_ADOPTION_FORM = "owners/AdoptionPetForm";
	private static final String VIEWS_ADOPTION_APPLICATION_FORM = "owners/applicationForAdoptionForm";
	

	private final AdoptionService adoptionService;
	private final OwnerService ownerService;
	private final PetService petService;
	private final ApplicationService applicationService;
	private final UserService userService;
       
	@Autowired
	public AdoptionController(AdoptionService adoptionService, OwnerService ownerService, PetService petService,
			ApplicationService applicationService,UserService userService) {
		this.adoptionService = adoptionService;
		this.ownerService=ownerService;
		this.petService=petService;
		this.applicationService=applicationService;
		this.userService = userService;
	}
	
	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}


	@GetMapping(value = "/owners/{ownerId}/pets/{petId}/adoption")
    public String initAdoptionForm(@PathVariable("petId") int petId, ModelMap model) {
		
		try {
			Adoption a = this.adoptionService.findAdoptionByStatus(petId);
			int id = a.getId();
			return "redirect:/owners/profile1";
		}catch(Exception e){
		
	 		Pet pet = this.petService.findPetById(petId);
	 		model.put("pet", pet);
	 		Adoption adoption = new Adoption();
	 		model.put("adoption", adoption);
	 		return VIEWS_PETS_ADOPTION_FORM;
		}
    }
     
     
    @PostMapping(value = "/owners/{ownerId}/pets/{petId}/adoption")
 	public String processAdoptionForm(@Valid Adoption adoption, BindingResult result, @PathVariable("petId") int petId, @PathVariable("ownerId") int ownerId, ModelMap model) {
 		if (result.hasErrors()) {
 			Pet pet= petService.findPetById(petId);
 			model.put("pet", pet);
 			model.put("adoption", adoption);
 			return VIEWS_PETS_ADOPTION_FORM;
 		}
 		else {
 			Pet pet= petService.findPetById(petId);
 			Adoption adoptionActiva = this.adoptionService.findAdoptionByStatus(petId);
 			if(adoptionActiva ==null) {
	 			Owner owner= ownerService.findOwnerById(ownerId);
	 			adoption.setStatus(false);
	            adoption.setPet(pet);
	            adoption.setOwner(owner);
	            this.adoptionService.saveAdoption(adoption);
	            return "redirect:/owners/profile";
 			}else {
				return "owners/exceptionAdoption";
			}
 		}
 		
 	}
    
    @GetMapping(value = "/allAdoptions")
	    public String showAdoptionList(ModelMap model) {
	    	Adoptions adoptions = new Adoptions();
	    	adoptions.getAdoptionsList().addAll(this.adoptionService.findActiveByStatus());
			model.put("adoptions", adoptions);
			model.put("ownerActivo", getOwnerActivo());
			return "owners/adoptionList";
	    }
    
    @GetMapping(value = "adoption/{adoptionId}/application")
    public String initApplicationForm(@PathVariable("adoptionId") int adoptionId, ModelMap model) {
 		Adoption adoption = this.adoptionService.findAdoptionById(adoptionId);
 		model.put("adoption", adoption);
 		Application application = new Application();
 		model.put("application", application);
 		return VIEWS_ADOPTION_APPLICATION_FORM;
    }
     
     
    @PostMapping(value = "adoption/{adoptionId}/application")
 	public String processApplicationForm(@Valid Application application, BindingResult result, @PathVariable("adoptionId") int adoptionId, ModelMap model) {
 		if (result.hasErrors()) {
 			Adoption adoption = this.adoptionService.findAdoptionById(adoptionId);
 			model.put("adoption", adoption);
 	 		model.put("application", application);
 			return  VIEWS_ADOPTION_APPLICATION_FORM;
 		}
 		else {
 			Adoption adoption = this.adoptionService.findAdoptionById(adoptionId);
 			application.setAdoption(adoption);
 			Owner applicant=getOwnerActivo();
 			application.setApplicant(applicant);
 			this.applicationService.saveApplication(application);
 			return "redirect:/allAdoptions";
		}
 	}
    
    private Owner getOwnerActivo() {
		UserDetails userDetails = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
          userDetails = (UserDetails) principal;
        }
        String userName = userDetails.getUsername();
        User usuario = this.userService.findUser(userName).get();
        Owner Owner= this.ownerService.findByUser(usuario);
        return  Owner;
	}
    
}
