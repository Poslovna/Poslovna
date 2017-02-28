package models;

import java.util.Date;
import java.util.List;

import javafx.geometry.Pos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.joda.time.DateTime;

import play.data.validation.Max;
import play.data.validation.Min;
import play.db.jpa.Model;

@Entity
public class Faktura extends Model {
	

		
	@Column(unique=true, length=6) 
	public Integer brojFakture;
	
	@Column(length=1)
	public String tipFakture;
	
	@Column(length=40)
	public Date datumFakture;
	
	@Column(length=40)
	public Date datumValute;
	
	@Column(length=40)
	public Date datumObracuna;
	
	@Column(nullable=false)
	@Max(15)
	@Min(2)
	public Double ukupnoRobaIUsluga;
	
	@Column(nullable=false)
	@Max(15)
	@Min(2)
	public Double ukupanRabat;
	
	@Column(nullable=false)
	@Max(15)
	@Min(2)
	public Double ukupanPorez;
	
	@Column(nullable=false)
	@Max(15)
	@Min(2)
	public Double iznosFakture;
	
	@Column(length=30)
	public String uplataNaRacun;

	@Column(length=20)
	public String pozivNaBroj;
	
	@Column(length=1)
	public String statusFakture;
	
	@ManyToOne
	public Preduzece preduzece;
	
	@ManyToOne
	public PoslovnaGodina poslovnaGodina;
	
	@OneToMany(mappedBy="faktura")
	public List<StavkeFakture> stavkeFakture;
	
	@ManyToOne
	public PoslovniPartner poslovniPartner;
	
	@ManyToOne
	public Otpremnica otpremnica;

	@OneToMany(mappedBy="faktura")
	public List<ObracunatiPorezi> obracunatiPorezi;
	
	
	


}
