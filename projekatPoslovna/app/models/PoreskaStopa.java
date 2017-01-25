package models;

import javax.persistence.Column;
import javax.persistence.ManyToOne;

import play.data.validation.Max;
import play.data.validation.Min;
import play.db.jpa.Model;

public class PoreskaStopa extends Model {
	
	@Column(nullable=false)
	@Max(5)
	@Min(2)
	public Double iznosStope;
	
	@ManyToOne
	public Porez porez;
	
	@ManyToOne
	public IstorijaPoreza istorijaPoreza;

}
