package models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import play.db.jpa.Model;

@Entity
public class PoslovnaGodina extends Model {
	

	
	@Column(length=4)
	public Integer godina;
	
	@Column(nullable=false)
	public Boolean zakljucena;
	
//	if(zakljucena == true){
//		String zakljucena2 = "da";
//	}else{
//		String zakljucena2 = "ne";
//	}

	
	@OneToMany(mappedBy="poslovnaGodina")
	public List<Faktura> fakture;
	
	@OneToMany(mappedBy="poslovnaGodina")
	public List<Otpremnica> otpremnica;
	
	@OneToMany(mappedBy="poslovnaGodina")
	public List<Narudzba> narudzba;

	
}

