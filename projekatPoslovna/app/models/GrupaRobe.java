package models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import play.db.jpa.Model;

@Entity
public class GrupaRobe extends Model {
	

	
	@Column(length=20)
	public String nazivGrupe;
	
	@ManyToOne
	public Preduzece preduzece;
	
	@OneToMany(mappedBy="grupaRobe")
	public List<RobaUsluga> robaUsluga;
	
	@ManyToOne
	public PDV pdv;
	


}
