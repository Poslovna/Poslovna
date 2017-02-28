package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import play.data.validation.Max;
import play.data.validation.Min;
import play.db.jpa.Model;

@Entity
public class ObracunatiPorezi extends Model {
	
	@Column(nullable=false)
	@Max(5)
	@Min(2)
	public Double stopa;
	
	@Column(nullable=false)
	@Max(5)
	@Min(2)
	public Double iznos;
	
	@ManyToOne
	public Faktura faktura;
	
	@ManyToOne
	public Porez porez;

}
