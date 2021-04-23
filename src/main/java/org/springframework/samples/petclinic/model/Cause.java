package org.springframework.samples.petclinic.model;


import java.util.Collections;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.Column;


import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.beans.support.PropertyComparator;

@Entity
@Table(name = "causes")
public class Cause extends BaseEntity {


	@NotNull
	private String name;
	
	
	@NotNull
	private String description;
	
	
	@NotNull
	@Min(0)
	private Double budgetTarget;
	
	
	@NotNull
	private String organization;
	/**
	@ManyToOne
	@JoinColumn(name = "owner_id")
	private Owner owner;
	**/
	@NotNull
	private Boolean isClosed;
	
	
	//Getters and Setters
	
	public Boolean getIsClosed() {
		return this.isClosed;
	}
	
	public void setIsClosed(final Boolean isClosed) {
		this.isClosed = isClosed;
	}
	
	/**
	public Owner getOwner() {
		return owner;
	}
	
	public void setOwner(Owner owner) {
		this.owner = owner;
	}
		**/	
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
