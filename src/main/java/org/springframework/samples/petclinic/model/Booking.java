package org.springframework.samples.petclinic.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;
@Entity
@Table(name = "booking")
public class Booking extends BaseEntity{
	
	@Column(name = "check_in")
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private LocalDate checkIn;
	
	@Column(name = "check_out")
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private LocalDate checkOut;
	
	@ManyToOne(optional=false)
	private Owner owner;
	
	@ManyToOne(optional=false)
	private Pet pet;

	public LocalDate getCheckIn() {
		return checkIn;
	}

	public void setCheckIn(LocalDate checkIn) {
		this.checkIn = checkIn;
	}

	public LocalDate getCheckOut() {
		return checkOut;
	}

	public void setCheckOut(LocalDate checkOut) {
		this.checkOut = checkOut;
	}

	public Owner getOwner() {
		return owner;
	}

	public void setOwner(Owner owner) {
		this.owner = owner;
	}

	public Pet getPet() {
		return pet;
	}

	public void setPet(Pet pet) {
		this.pet = pet;
	}

	
	
	
}
