package models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.OneToMany;

import play.db.jpa.Model;

public class Mesto extends Model {
	
	@Column(unique=true, length=6) 
	public Integer idMesta;
	
	@Column(length=30) 
	public String nazivMesta;
	
	@OneToMany(mappedBy="mesto")
	public List<Preduzece> preduzeca;
	
	@OneToMany(mappedBy="mesto")
	public List<PoslovniPartner> poslovniPartner;


}
