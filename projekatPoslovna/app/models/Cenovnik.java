package models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import play.db.jpa.Model;

public class Cenovnik extends Model {
	
	@Column(unique=true, length=6) 
	public Integer idCenovnika;
	
	@Column(length=40) 
	public String datmPrimene;
	
	@ManyToOne
	public Preduzece preduzece;
	
	@OneToMany(mappedBy="cenovnik")
	public List<StavkeCenovnika> stavkeCenovnika;



}
