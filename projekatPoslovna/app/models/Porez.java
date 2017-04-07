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
	
	@Column(nullable=true)
	public String vazeciStr;
	
	
	@OneToMany(mappedBy="porez")
	public List<PoreskaStopa> poreskaStopa;

	public Porez(String oznakaPoreza, String nazivPoreza, Boolean vazeci,
			String vazeciStr,
			List<PoreskaStopa> poreskaStopa) {
		super();
		this.oznakaPoreza = oznakaPoreza;
		this.nazivPoreza = nazivPoreza;
		this.vazeci = vazeci;
		this.vazeciStr = vazeciStr;
		this.poreskaStopa = poreskaStopa;
	}
	
	public Porez(){
		super();
	}

}
