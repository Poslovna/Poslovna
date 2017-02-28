package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import play.db.jpa.Model;

@Entity
public class Mesto extends Model {
	

	
	@Column(length=30) 
	public String nazivMesta;
	
	@OneToMany(mappedBy="mesto")
	public List<Preduzece> preduzeca;
	
	@OneToMany(mappedBy="mesto")
	public List<PoslovniPartner> poslovniPartner;

/*
	public Mesto(String nazivMesta){
		super();
		this.nazivMesta = nazivMesta;
		preduzeca = new ArrayList<Preduzece>();
		poslovniPartner = new ArrayList<PoslovniPartner>();
	}
	*/
	
}

