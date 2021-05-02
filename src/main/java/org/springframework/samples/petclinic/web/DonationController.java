package org.springframework.samples.petclinic.web;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
    	Double amount = donation.getAmount();
    	Cause cause=causeService.findCauseById(causeId);
    	List<Cause> causes = new ArrayList<>();
        causes.addAll(this.causeService.findCauses());
        int n = 0;
        for (int i = 0; i<causes.size(); i++) {
        	if(causes.get(i).getId()==causeId) {
        		n = i;
        	}
        }
        List<Double> donations = new ArrayList<>(this.donationService.findDonationsByCauses(causes));
        double resto = cause.getBudgetTarget()-donations.get(n);
        if (result.hasErrors()) {
        	model.put("donation", donation);
            return VIEWS_DONATION_CREATE_OR_UPDATE_FORM;
        }else if (amount==null ) {
        	model.addAttribute("message","La cantidad no puede estar vacia.");
        	model.put("donation", donation);
            return VIEWS_DONATION_CREATE_OR_UPDATE_FORM;
        }else if(cause.getBudgetTarget()-donations.get(n)-donation.getAmount()<0) {
        	model.addAttribute("message","La cantidad introducida ha superado a la cantidad maxima de "+resto+ " euros para la donación. " );
        	model.put("donation", donation);
            return VIEWS_DONATION_CREATE_OR_UPDATE_FORM;
        }else if (donation.getAmount()<=0) {
        	model.addAttribute("message","La cantidad tiene que ser mayor que 0.");
        	model.put("donation", donation);
            return VIEWS_DONATION_CREATE_OR_UPDATE_FORM;
        }else if(donation.getClient()== "" ){
            	model.addAttribute("message","El nombre no puede estar vacio, si no quiere mostrar su identidad escriba anonimo");
            	model.put("donation", donation);
                return VIEWS_DONATION_CREATE_OR_UPDATE_FORM;	
         }else { 
        
        	donation.setCause(cause);
        	donation.setDate(LocalDate.now());
            this.donationService.saveDonation(donation);
            if(cause.getBudgetTarget() <= causeService.totalBudget(causeId)){
            	cause.setIsClosed(true);
            this.causeService.saveCause(cause);
            }
        }
        return "redirect:/causes";
    }  
}

    

