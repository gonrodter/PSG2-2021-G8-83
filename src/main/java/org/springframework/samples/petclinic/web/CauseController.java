package org.springframework.samples.petclinic.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Cause;
import org.springframework.samples.petclinic.service.CauseService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CauseController {

	private static final String VIEWS_CAUSE_CREATE_OR_UPDATE_FORM = "causes/createOrUpdateCauseForm";
    private final CauseService causeService;
   // private final DonationService donationService;


    @Autowired
    public CauseController(final CauseService causeService) {
        this.causeService = causeService;
       // this.donationService = donationService;
    }

    @GetMapping(value = { "/causes"} )
    public String showCauseList(final Map<String, Object> model) {
        final List<Cause> causes = new ArrayList<>();
        causes.addAll(this.causeService.findCauses());
     
       // final List<Double> donations = new ArrayList<>(this.donationService.findDonationsByCauses(causes));
        
        final Map<Cause,Double> res=new HashMap<>();
        for(int i=0;i<causes.size();i++) {
        	res.put(causes.get(i), 0.0);
        }
        model.put("map", res);
        return "causes/causeList";
    }

    @GetMapping(value = "/causes/new")
    public String initCreationForm(final Map<String, Object> model) {
        final Cause cause = new Cause();
        cause.setIsClosed(false);
        model.put("cause", cause);
        return CauseController.VIEWS_CAUSE_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping(value = "/causes/new")
    public String processCreationForm(@Valid final Cause cause, final BindingResult result) {
        if (result.hasErrors()) {
            return CauseController.VIEWS_CAUSE_CREATE_OR_UPDATE_FORM;
        } else {
            this.causeService.saveCause(cause);
            return "redirect:/causes";    
        }
    }
    
    @GetMapping("/causes/{causeId}")
    public ModelAndView showCause(@PathVariable("causeId") final int causeId, final Map<String, Object> model) {
    //	Collection<Donation> donations;
    //	donations = this.causeService.findDonations(causeId);
     //   model.put("donations", donations);
        final ModelAndView mav = new ModelAndView("causes/causeDetails");
        mav.addObject("cause",this.causeService.findCauseById(causeId));
        return mav;
    }
}
