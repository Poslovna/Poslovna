package models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import play.db.jpa.Model;

@Entity
public class Porez extends Model {
	

	
	@Column(unique=true, length=2) 
	public String oznakaPoreza;
	
	@Column(length=20)
	public String nazivPoreza;
	
	@Column(length=6) 
	public Boolean vazeci;
	
	@OneToMany(mappedBy="porez")
	public List<ObracunatiPorezi> obracunatiPorezi;
	
	@OneToMany(mappedBy="porez")
	public List<PoreskaStopa> poreskaStopa;
	




}
