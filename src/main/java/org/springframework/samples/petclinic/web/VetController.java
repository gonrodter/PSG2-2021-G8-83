/*
 * Copyright 2002-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.samples.petclinic.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Specialty;
import org.springframework.samples.petclinic.model.Vet;
import org.springframework.samples.petclinic.model.Vets;
import org.springframework.samples.petclinic.repository.VetRepository;
import org.springframework.samples.petclinic.service.SpecialtyService;
import org.springframework.samples.petclinic.service.VetService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

/**
 * @author Juergen Hoeller
 * @author Mark Fisher
 * @author Ken Krebs
 * @author Arjen Poutsma
 */
@Controller
public class VetController {

	private final VetService vetService;
	
	private final SpecialtyService specialtyService;

	@Autowired
	public VetController(VetService clinicService,
			SpecialtyService specialtyService) {
		this.vetService = clinicService;
		this.specialtyService = specialtyService;
	}

	@GetMapping(value = { "/vets" })
	public String showVetList(Map<String, Object> model) {
		// Here we are returning an object of type 'Vets' rather than a collection of Vet
		// objects
		// so it is simpler for Object-Xml mapping
		Vets vets = new Vets();
		vets.getVetList().addAll(this.vetService.findVets());
		model.put("vets", vets);
		return "vets/vetList";
	}
	
	@GetMapping(value = { "/vets/new" })
	public String newVetGet(Model model) {
		
		Vet vet = new Vet();
		model.addAttribute("vet", vet);
		
		return "vets/createOrUpdateVetForm";
	}
	
	@PostMapping(value = { "/vets/new" })
	public String newVetPost(@Valid Vet vet, BindingResult result, 
			@RequestParam("specialties") Optional<Collection<Specialty>> specialties, Model model) {
		
		// si existe algun vaterinario en la BD que tenga el mismo nombre y apellidos que vet, condicion=true
		boolean condicion = vetService.findByFirstNameAndLastName(vet.getFirstName(), vet.getLastName()).isPresent();
		
		if(result.hasErrors()) {
			if(specialties.isPresent()) specialties.get().stream().forEach(e -> vet.addSpecialty(e));
			model.addAttribute("vet", vet);
			return "vets/createOrUpdateVetForm";
		}else if(condicion){
			if(specialties.isPresent()) specialties.get().stream().forEach(e -> vet.addSpecialty(e));
			model.addAttribute("vet", vet);
			result.rejectValue("lastName", "ya existe un veterinario registrado con el mismo nombre y apellido",
					"ya existe un veterinario registrado con el mismo nombre y apellido");
			return  "vets/createOrUpdateVetForm";
		}else {
			if(specialties.isPresent()) specialties.get().stream().forEach(e -> vet.addSpecialty(e));
			vetService.save(vet);
			return "redirect:/vets";
		}
		
	}
	
	@GetMapping(value = { "/vets/edit/{vetId}" })
	public String editVetGet(@PathVariable("vetId") int vetId, Model model) {
		
		Optional<Vet> optVet = vetService.findById(vetId);
		
		if(!optVet.isPresent()) {
			return "redirect:/vets";
		}else {
			model.addAttribute("vet", optVet.get());
			return "vets/createOrUpdateVetForm";
		}
		
	}
	
	@PostMapping(value = { "/vets/edit/{vetId}" })
	public String editVetPost(@Valid Vet vet, BindingResult result, @PathVariable("vetId") int vetId, 
			@RequestParam("specialties") Optional<Collection<Specialty>> specialties, Model model) {
		
		// si el id del veterinario no existe, redirige a /vets
		if(!vetService.findById(vetId).isPresent()) return "redirect:/vets";
		
		// como el id del veterinario existe, obtenemos el veterinario sin actualizar, el guardado en la BD
		Vet oldVet = vetService.findById(vetId).get();
		
		// si existe algun vaterinario en la BD que tenga el mismo nombre y apellidos que vet, condicion1=true
		boolean condicion1 = vetService.findByFirstNameAndLastName(vet.getFirstName(), vet.getLastName()).isPresent();
		
		// si oldVet y vet tienen mismo nombre y apellidos, es decir, si no se ha actualizado ni el nombre ni
		// los apellidos, condicion2=true
		boolean condicion2 = oldVet.getFirstName().equals(vet.getFirstName())
					&& oldVet.getLastName().equals(vet.getLastName());
		
		if(result.hasErrors()) {
			// si existen errores en los atributos, recuerda las especialidades seleccionadas antes del reenvío del formulario
			if(specialties.isPresent()) specialties.get().stream().forEach(e -> vet.addSpecialty(e));
			model.addAttribute("vet", vet);
			return "vets/createOrUpdateVetForm";
		}else if(condicion1) {
			// en caso de que halla algun veterianario con el mismo nombre y apellidos
			
			if(condicion2) {
				// si no se ha actualizado ni el nombre ni los apellidos, se habrán actualizado o no el resto de
				// atributos, por tanto actualizo el veterinario en caso de que sí se hayan actualizado el
				// resto de atributos
				if(specialties.isPresent()) specialties.get().stream().forEach(e -> vet.addSpecialty(e));
				vet.setId(vetId);
				vetService.save(vet);
				return "redirect:/vets";
			}else {
				// si se ha actualizado el nombre o los apellidos, como ya existe un veterinario en la BD que tiene
				// ese nombre o apellidos (condicion1), se manda un mensaje diciendo que hay que cambiar el campo del
				// nombre o de los apellidos
				if(specialties.isPresent()) specialties.get().stream().forEach(e -> vet.addSpecialty(e));
				model.addAttribute("vet", vet);
				result.rejectValue("lastName", "ya existe un veterinario registrado con el mismo nombre y apellido",
						"ya existe un veterinario registrado con el mismo nombre y apellido");
				return  "vets/createOrUpdateVetForm";
			}
			
		}
		
		else {
			// si no hay ningun veterinario en la BD con el mismo nombre y apellidos, no hay problema y se actualiza
			// con exito
			if(specialties.isPresent()) specialties.get().stream().forEach(e -> vet.addSpecialty(e));
			vet.setId(vetId);
			vetService.save(vet);
			return "redirect:/vets";
		}
		
	}
	
	// Añadido por AlvaroSC
	
		@GetMapping(value ="/vets/{vetId}/deleteVet")
	    public String deleteVet(@PathVariable("vetId") final int vetId,final ModelMap model) {
	        final Optional<Vet> vet =this.vetService.findById(vetId);
	        if (vet.isPresent()) {
	            this.vetService.deleteVet(vet.get());
	            model.addAttribute("message","Vet deleted");
	            //La redireccion
	            final Collection<Vet> results = this.vetService.findVets();
	            model.put("selections", results);
	        }
	        return "redirect:/vets";
	    }
	
	
	

	@GetMapping(value = { "/vets.xml"})
	public @ResponseBody Vets showResourcesVetList() {
		// Here we are returning an object of type 'Vets' rather than a collection of Vet
		// objects
		// so it is simpler for JSon/Object mapping
		Vets vets = new Vets();
		vets.getVetList().addAll(this.vetService.findVets());
		return vets;
	}
	
	@ModelAttribute("especialidades")
	public Collection<Specialty> getListaEspecialidades(){
		return specialtyService.findAll();
	}

}
