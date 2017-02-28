package models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import play.db.jpa.Model;

@Entity
public class Narudzba extends Model {
	

	
	@Column(unique=true,length=6) 
	public Integer brojNarudzbe;
	
	@Column(length=20) 
	public Date datumNarudzbe;
	
	@Column(length=20)
	public String sifra;
	
	@Column(length=20)
	public Double kolicina;
	
	@ManyToOne
	public Otpremnica otpremnica;
	
	@OneToMany(mappedBy="narudzba")
	public List<RobaUsluga> robaUsluga;
	
	@ManyToOne
	public PoslovnaGodina poslovnaGodina;
	
	@ManyToOne
	public PoslovniPartner poslovniPartner;



}
