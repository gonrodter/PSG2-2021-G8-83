package org.springframework.samples.petclinic.web;



import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Adoption;
import org.springframework.samples.petclinic.model.Adoptions;
import org.springframework.samples.petclinic.model.Owner;
import org.springframework.samples.petclinic.model.Pet;
import org.springframework.samples.petclinic.service.AdoptionService;
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
public class AdoptionController {
	
	private static final String VIEWS_PETS_ADOPTION_FORM = "owners/AdoptionPetForm";

	private final AdoptionService adoptionService;
	private final OwnerService ownerService;
	private final PetService petService;
       
	@Autowired
	public AdoptionController(AdoptionService adoptionService, OwnerService ownerService, PetService petService) {
		this.adoptionService = adoptionService;
		this.ownerService=ownerService;
		this.petService=petService;
	}
	
	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}


	@GetMapping(value = "/owners/{ownerId}/pets/{petId}/adoption")
    public String initAdoptionForm(@PathVariable("petId") int petId, ModelMap model) {
 		Pet pet = this.petService.findPetById(petId);
 		model.put("pet", pet);
 		Adoption adoption = new Adoption();
 		model.put("adoption", adoption);
 		return VIEWS_PETS_ADOPTION_FORM;
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
	            return "redirect:/owners/{ownerId}";
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
			return "owners/adoptionList";
	    }

}
