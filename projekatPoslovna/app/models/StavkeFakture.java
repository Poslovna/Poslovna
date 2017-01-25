package models;

import javax.persistence.Column;
import javax.persistence.ManyToOne;

import play.data.validation.Max;
import play.data.validation.Min;
import play.db.jpa.Model;

public class StavkeFakture extends Model{
	
	@Column(unique=true, length=6)
	public Integer idStavke;
	
	@Column(length=30) 
	public String kolicina;
	
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



}
