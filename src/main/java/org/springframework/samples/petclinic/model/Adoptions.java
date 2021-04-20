package org.springframework.samples.petclinic.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.sun.xml.txw2.annotation.XmlElement;

@XmlRootElement
public class Adoptions {
	
private List<Adoption> adoptionList;
	
	@XmlElement
	public List<Adoption> getAdoptionsList() {
		if (adoptionList == null) {
			adoptionList = new ArrayList<>();
		}
		return adoptionList;
	}

}
