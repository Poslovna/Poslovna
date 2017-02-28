package models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import play.db.jpa.Model;

@Entity
public class PDV extends Model{
	

	
	@Column(length=30)
	public String nazivPDV;
	
	@OneToMany(mappedBy="pdv")
	public List<StopaPDVa> stopaPdva;
	
	@OneToMany(mappedBy="pdv")
	public List<GrupaRobe> grupaRobe;
	
	


}
