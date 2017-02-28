package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import play.db.jpa.Model;

@Entity
public class Cenovnik extends Model {

	@Column(length=6) 
	public Integer rbrCenovnika;
	
	@Column(length=40) 
	public String datmPrimene;
	
	@ManyToOne
	public Preduzece preduzece;
	
	@OneToMany(mappedBy="cenovnik")
	public List<StavkeCenovnika> stavkeCenovnika;

	public Cenovnik(Integer rbrCenovnika, String datmPrimene) {
		super();
		this.rbrCenovnika = rbrCenovnika;
		this.datmPrimene = datmPrimene;
		
		//preduzece = new Preduzece();
		stavkeCenovnika = new ArrayList<StavkeCenovnika>();
		
	}


	
	

}
