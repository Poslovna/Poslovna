package models;



import java.sql.Date;
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
	public String datumFakture;
	
	@Column(length=40)
	public String datumValute;
	
	@Column(length=40)
	public String datumObracuna;
	
	@Column(nullable=true)
	@Max(15)
	@Min(2)
	public Double ukupnoRobaIUsluga;
	
	@Column(nullable=true)
	@Max(15)
	@Min(2)
	public Double ukupanRabat;
	
	@Column(nullable=true)
	@Max(15)
	@Min(2)
	public Double ukupanPorez;
	
	@Column(nullable=false) 
	public Double osnovica;
	
	@Column(nullable=false) 
	public Double ukupanPDV;
	
	@Column(nullable=true)
	@Max(15)
	@Min(2)
	public Double iznosZaPlacanje;
	
	
	
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


	public Faktura(Integer brojFakture, String tipFakture, String datumFakture,
			String datumValute, String datumObracuna, Double ukupnoRobaIUsluga,
			Double ukupanRabat, Double ukupanPorez, Double osnovica,
			Double ukupanPDV, Double iznosZaPlacanje, String uplataNaRacun,
			String pozivNaBroj, String statusFakture, Preduzece preduzece,
			PoslovnaGodina poslovnaGodina, List<StavkeFakture> stavkeFakture,
			PoslovniPartner poslovniPartner, Otpremnica otpremnica) {
		
		this.brojFakture = brojFakture;
		this.tipFakture = tipFakture;
		this.datumFakture = datumFakture;
		this.datumValute = datumValute;
		this.datumObracuna = datumObracuna;
		this.ukupnoRobaIUsluga = ukupnoRobaIUsluga;
		this.ukupanRabat = ukupanRabat;
		this.ukupanPorez = ukupanPorez;
		this.osnovica = osnovica;
		this.ukupanPDV = ukupanPDV;
		this.iznosZaPlacanje = iznosZaPlacanje;
		this.uplataNaRacun = uplataNaRacun;
		this.pozivNaBroj = pozivNaBroj;
		this.statusFakture = statusFakture;
		this.preduzece = preduzece;
		this.poslovnaGodina = poslovnaGodina;
		this.stavkeFakture = stavkeFakture;
		this.poslovniPartner = poslovniPartner;
		this.otpremnica = otpremnica;
	}
	
	public Faktura(){
		super();
	}
	
	
	
	
	


}
