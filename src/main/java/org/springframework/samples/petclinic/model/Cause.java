package org.springframework.samples.petclinic.model;


import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "causes")
public class Cause extends BaseEntity {


	@NotNull
	@NotBlank (message = "El nombre no puede estar vacio")
	private String name;
	
	
	@NotNull
	@NotBlank (message = "La descripción no puede estar vacia")
	private String description;
	
	
	@NotNull (message = "Escribe un numero porfavor")
	@Min(value = 5 , message = "La cantidad solicitada deberia de ser mayor que 5 euros")
	private Double budgetTarget;
	
	
	@NotNull
	@NotBlank (message = "La organizacion que reciba el dinero no puede estar vacia")
	private String organization;
	
	@NotNull
	private Boolean isClosed;
	
	
	//Getters and Setters
	
	public Boolean getIsClosed() {
		return this.isClosed;
	}
	
	public void setIsClosed(final Boolean isClosed) {
		this.isClosed = isClosed;
	}
	

	public String getName() {
		return name;
	}
	
	public void setName(final String name) {
		this.name = name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(final String description) {
		this.description = description;
	}
	
	public Double getBudgetTarget() {
		return budgetTarget;
	}
	
	public void setBudgetTarget(final Double budgetTarget) {
		this.budgetTarget = budgetTarget;
	}
	
	public String getOrganization() {
		return organization;
	}
	
	public void setOrganization(final String organization) {
		this.organization = organization;
	}
	
	
	
	
}
