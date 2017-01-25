package models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import play.db.jpa.Model;

public class IstorijaPoreza extends Model {
	
	@Column(unique=true, length=30)
	public Integer idIPoreza;
	
	@Column(length=30)
	public Date datumPrimene;
	
	@OneToMany(mappedBy="istorijaPoreza")
	public List<PoreskaStopa> poreskaStopa;
	
	@ManyToOne
	public Preduzece preduzece;


}
