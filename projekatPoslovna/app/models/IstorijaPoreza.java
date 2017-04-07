package models;


import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import play.db.jpa.Model;

@Entity
public class IstorijaPoreza extends Model {
	
	@Column(unique=true, length=30)
	public Integer idIPoreza;
	
	@Column(length=30)
	public String datumPrimene;
	
	@OneToMany(mappedBy="istorijaPoreza")
	public List<PoreskaStopa> poreskaStopa;
	
	@ManyToOne
	public Preduzece preduzece;


}
