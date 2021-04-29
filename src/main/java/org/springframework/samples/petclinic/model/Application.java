package org.springframework.samples.petclinic.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "application")
public class Application extends BaseEntity{
	
	@NotEmpty
	@Column(name = "description")
	private String description;
	
	@ManyToOne(optional=false)
	private Owner applicant;

	@ManyToOne(optional=false)
	private Adoption adoption;
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Owner getApplicant() {
		return applicant;
	}

	public void setApplicant(Owner applicant) {
		this.applicant = applicant;
	}

	public Adoption getAdoption() {
		return adoption;
	}

	public void setAdoption(Adoption adoption) {
		this.adoption = adoption;
	}
}
