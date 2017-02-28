package models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import play.data.validation.Max;
import play.data.validation.Min;
import play.db.jpa.Model;

@Entity
public class StopaPDVa extends Model{
	
	@Column(nullable=false)
	@Max(15)
	@Min(2) 
	public Double procenat;
	
	@Column(length=30)
	public Date datumVazenja;
	
	@ManyToOne
	public PDV pdv;

}
