package models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import play.db.jpa.Model;

@Entity
public class RobaUsluga extends Model {
	

	
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
