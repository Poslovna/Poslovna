package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import play.data.validation.Max;
import play.data.validation.Min;
import play.db.jpa.Model;

@Entity
public class StavkeFakture extends Model{
	

	
	@Column(length=30) 
	public Double kolicina;
	
	@Column(nullable=false)
	@Max(15)
	@Min(2)
	public Double jedinicnaCena;
	
	@Column(nullable=false)
	@Max(15)
	@Min(2) 
	public Double rabat;
	
	@Column(nullable=false)
	@Max(15)
	@Min(2) 
	public Double osnovicaZaPDV;
	
	@Column(nullable=false)
	@Max(15)
	@Min(2) 
	public Double procenatPDV;
	
	@Column(nullable=false)
	@Max(15)
	@Min(2) 
	public Double iznosPDV;
	
	@Column(nullable=false)
	@Max(15)
	@Min(2)
	public Double iznosStavke;
	
	
	@ManyToOne
	public Faktura faktura;
	
	@ManyToOne
	public  RobaUsluga robaUsluga;

	public StavkeFakture(Double kolicina, Double jedinicnaCena, Double rabat,
			Double osnovicaZaPDV, Double procenatPDV, Double iznosPDV,
			Double iznosStavke, Faktura faktura, RobaUsluga robaUsluga) {
		
		this.kolicina = kolicina;
		this.jedinicnaCena = jedinicnaCena;
		this.rabat = rabat;
		this.osnovicaZaPDV = osnovicaZaPDV;
		this.procenatPDV = procenatPDV;
		this.iznosPDV = iznosPDV;
		this.iznosStavke = iznosStavke;
		this.faktura = faktura;
		this.robaUsluga = robaUsluga;
	}

	public StavkeFakture() {
		super();	}


	

}
