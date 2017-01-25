package models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import play.db.jpa.Model;

public class Preduzece extends Model {
	
	@Column(unique=true, length=6) 
	public Integer idPreduzeca;
	
	@Column(length=40)
	public String naziv;
	
	@Column(length=40)
	public String adresaPreduzeca;
	
	@Column(length=40)
	public String pib;
	
	@Column(length=40)
	public String telefon;
	
	@Column(length=40)
	public String email;
	
	@Column(length=40)
	public String logo;
	
	@ManyToOne
	public Mesto mesto;
	
	@OneToMany(mappedBy="preduzece")
	public List<Faktura> fakture;
	
	@OneToMany(mappedBy="preduzece")
	public List<PoslovniPartner> poslovniPartner;

	@OneToMany(mappedBy="preduzece")
	public List<Cenovnik> cenovnik;
	
	@OneToMany(mappedBy="preduzece")
	public List<GrupaRobe> grupaRobe;
	
	@OneToMany(mappedBy="preduzece")
	public List<IstorijaPoreza> istorijaPoreza;
}
