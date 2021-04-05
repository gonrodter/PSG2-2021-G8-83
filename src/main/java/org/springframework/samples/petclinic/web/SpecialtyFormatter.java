package org.springframework.samples.petclinic.web;

import java.text.ParseException;
import java.util.Collection;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.samples.petclinic.model.Specialty;
import org.springframework.samples.petclinic.service.SpecialtyService;
import org.springframework.stereotype.Component; 

@Component
public class SpecialtyFormatter implements Formatter<Specialty>{
	
	private final SpecialtyService specialtyService;
	
	@Autowired
	public SpecialtyFormatter(SpecialtyService specialtyService) {
		this.specialtyService = specialtyService;
	}

	@Override
	public String print(Specialty object, Locale locale) {
		return object.getName();
	}

	@Override
	public Specialty parse(String text, Locale locale) throws ParseException {
		Collection<Specialty> especialidades = specialtyService.findAll();
		for(Specialty s : especialidades) {
			if(s.getName().equals(text)) {
				return s;
			}
		}
		throw new ParseException("specialty not found: " + text, 0);
	}

}
