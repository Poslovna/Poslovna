package models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import play.db.jpa.Model;

public class RobaUsluga extends Model {
	
	@Column(unique=true, length=6) 
	public Integer idRobeUsluge;
	
	@Column(length=30)
	public String nazivRobeUsluge;
	
	@Column(length=12)
	public String jedinicaMere;
	
	
	@OneToMany(mappedBy="robaUsluga")
	public List<StavkeFakture> stavkeFakture;
	
	@ManyToOne
	public GrupaRobe grupaRobe;
	
	@OneToMany(mappedBy="robaUsluga")
	public List<StavkeCenovnika> stavkeCenovnika;
	
	@ManyToOne
	public Otpremnica otpremnica;
	
	@ManyToOne
	public Narudzba narudzba;
	
	
	

}
