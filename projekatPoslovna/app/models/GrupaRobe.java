package models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import play.db.jpa.Model;

public class GrupaRobe extends Model {
	
	@Column(unique=true, length=6)
	public Integer idRobe;
	
	@Column(length=20)
	public String nazivGrupe;
	
	@ManyToOne
	public Preduzece preduzece;
	
	@OneToMany(mappedBy="grupaRobe")
	public List<RobaUsluga> robaUsluga;
	
	@ManyToOne
	public PDV pdv;
	


}
