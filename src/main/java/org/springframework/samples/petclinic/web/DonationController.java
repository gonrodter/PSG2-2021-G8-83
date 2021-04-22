package org.springframework.samples.petclinic.web;


import java.time.LocalDate;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Cause;
import org.springframework.samples.petclinic.model.Donation;
import org.springframework.samples.petclinic.service.DonationService;
import org.springframework.samples.petclinic.service.CauseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/causes/{causeId}")
public class DonationController {

	private static final String VIEWS_DONATION_CREATE_OR_UPDATE_FORM = "donations/createOrUpdateDonationForm";
	
    private final DonationService donationService;
    private final CauseService causeService;

    @Autowired
    public DonationController(DonationService donationService, CauseService causeService) {
        this.donationService = donationService;
        this.causeService = causeService;
    }
    
    @ModelAttribute("cause")
    public Cause findOwner(@PathVariable("causeId") int causeId) {
        return this.causeService.findCauseById(causeId);
    }
    
    @InitBinder("cause")
    public void initCauseBinder(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @GetMapping(value = "/donations/new")
    public String initCreationForm(@PathVariable("causeId") int causeId, ModelMap model) {
        Donation donation = new Donation();
        model.put("donation", donation);
        return VIEWS_DONATION_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping(value = "/donations/new")
    public String processCreationForm(@PathVariable("causeId") int causeId, Donation donation, BindingResult result, ModelMap model) {
    	Cause cause=causeService.findCauseById(causeId);
    	/*if (cause.getIsClosed()){
            result.rejectValue("client", "closed");
            result.rejectValue("amount", "closed");
    	} */
        if (result.hasErrors()) {
        	model.put("donation", donation);
            return VIEWS_DONATION_CREATE_OR_UPDATE_FORM;
        } else { 
        	donation.setCause(cause);;
        	donation.setDate(LocalDate.now());
            this.donationService.saveDonation(donation);
            if(cause.getBudgetTarget() <= causeService.totalBudget(causeId)){
            	cause.setIsClosed(true);
            this.causeService.saveCause(cause);
            }
          
        return "redirect:/causes";
    }  
}

    }

