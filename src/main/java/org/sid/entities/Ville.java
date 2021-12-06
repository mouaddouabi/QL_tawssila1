package org.sid.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Ville implements Serializable{
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@OneToMany(mappedBy = "ville", fetch=FetchType.LAZY)
	private Collection<Quartier> quartiers;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	
	
	public Ville(Collection<Quartier> quartiers) {
		super();
		this.quartiers = quartiers;
	}
	public Collection<Quartier> getQuartiers() {
		return quartiers;
	}
	public void setQuartiers(Collection<Quartier> quartiers) {
		this.quartiers = quartiers;
	}
	public Ville() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
