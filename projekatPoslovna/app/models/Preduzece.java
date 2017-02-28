package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import play.db.jpa.Model;

@Entity
public class Preduzece extends Model {
	

	
	@Column(length=40)
	public String naziv;
	
	@Column(length=40)
	public String adresaPreduzeca;
	
	@Column(length=40)
	public String pib;
	
	@Column(length=40)
	public String telefon;
	
	@Column(length=40)
	public String email;
	
	@Column(length=40)
	public String logo;
	
	@ManyToOne
	public Mesto mesto;
	
	@OneToMany(mappedBy="preduzece")
	public List<Faktura> fakture;
	
	@OneToMany(mappedBy="preduzece")
	public List<PoslovniPartner> poslovniPartner;

	@OneToMany(mappedBy="preduzece")
	public List<Cenovnik> cenovnik;
	
	@OneToMany(mappedBy="preduzece")
	public List<GrupaRobe> grupaRobe;
	
	@OneToMany(mappedBy="preduzece")
	public List<IstorijaPoreza> istorijaPoreza;
/*
	public Preduzece(String naziv, String adresaPreduzeca, String pib, String telefon, String email, String logo, Mesto mesto) {
		super();
		this.naziv = naziv;
		this.adresaPreduzeca = adresaPreduzeca;
		this.pib = pib;
		this.telefon = telefon;
		this.email = email;
		this.logo = logo;
		//this.mesto = mesto;
		Preduzece preduzece = new Preduzece(naziv, adresaPreduzeca, pib, telefon, email, logo, mesto);
		fakture = new ArrayList<Faktura>();
		poslovniPartner = new ArrayList<PoslovniPartner>();
		cenovnik = new ArrayList<Cenovnik>();
		grupaRobe = new ArrayList<GrupaRobe>();
		istorijaPoreza = new ArrayList<IstorijaPoreza>();
	}
*/
	
	
	
	
}
