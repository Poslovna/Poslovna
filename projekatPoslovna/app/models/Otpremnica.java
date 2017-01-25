package models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import play.db.jpa.Model;

public class Otpremnica extends Model  {
	
	@Column(unique=true, length=6)
	public Integer idOtpremnice;
	
	@Column(unique=true, length=6)
	public Integer brojOtpremnice;
	
	@Column(length=20)
	public String mestoIzdavanjaRacuna;
	
	@Column(length=20)
	public Date datumIzdavanjaRacuna;
	
	@Column(length=20)
	public String nacinPlacanja;

	@Column(length=20)
	public String rokPlacanja;
	
	@OneToMany(mappedBy="otpremnica")
	public List<Faktura> fakture;
	
	
	@OneToMany(mappedBy="otpremnica")
	public List<RobaUsluga> robaUsluga;


	@OneToMany(mappedBy="otpremnica")
	public List<Narudzba> narudzba;
	
	@ManyToOne
	public PoslovnaGodina poslovnaGodina;
	
	@ManyToOne
	public PoslovniPartner poslovniPartner;
}
