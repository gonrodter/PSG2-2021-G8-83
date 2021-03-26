package org.springframework.samples.petclinic.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collection;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.model.Specialty;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class SpecialtyServiceTests {

	@Autowired
	private SpecialtyService specialtyService;
	
	@Test
	void shouldFindSpecialties() {
		Collection<Specialty> especialidades = specialtyService.findAll();
		assertThat(especialidades.size()).isEqualTo(3);
	}
	
}
