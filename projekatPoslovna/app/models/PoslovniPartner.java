package models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import play.db.jpa.Model;

@Entity
public class PoslovniPartner extends Model {
	

	@Column(length=40)
	public String nazivPartnera;
	
	@Column(length=40)
	public String adresa;
	
	@Column(length=40)
	public String vrstaPartnera;
	
	@OneToMany(mappedBy="poslovniPartner")
	public List<Faktura> fakture;
	
	@ManyToOne
	public Mesto mesto;
	
	@ManyToOne
	public Preduzece preduzece;
	
	@OneToMany(mappedBy="poslovniPartner")
	public List<Otpremnica> otpremnica;
	
	@OneToMany(mappedBy="poslovniPartner")
	public List<Narudzba> narudzba;


}
