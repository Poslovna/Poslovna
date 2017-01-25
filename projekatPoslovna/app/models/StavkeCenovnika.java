package models;

import javax.persistence.Column;
import javax.persistence.ManyToOne;

import play.data.validation.Max;
import play.data.validation.Min;
import play.db.jpa.Model;

public class StavkeCenovnika extends Model {
	
	@Column(nullable=false)
	@Max(15)
	@Min(2)
	public Double jedinicnaCena;
	
	@ManyToOne
	public RobaUsluga robaUsluga;
	
	@ManyToOne
	public Cenovnik cenovnik;

}
