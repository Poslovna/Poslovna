package controllers;

import java.util.Date;
import java.util.List;

import models.IstorijaPoreza;
import models.Preduzece;
import play.mvc.Controller;

public class IstorijePoreza extends Controller{

	
	public static void showIPoreza(String mode){
		List<IstorijaPoreza> istorijePoreza = IstorijaPoreza.findAll();
		List<Preduzece> preduzeca = Preduzece.findAll();
		if(mode == null || mode.equals(""))
			mode = "edit";
		render(istorijePoreza, preduzeca, mode);
	}
	
	
	public static void create(Integer idIPoreza, String datumPrimene, long preduzece){
		IstorijaPoreza istorijaPoreza = new IstorijaPoreza();
		istorijaPoreza.idIPoreza = idIPoreza;
		istorijaPoreza.datumPrimene = datumPrimene;
		istorijaPoreza.preduzece = Preduzece.findById(preduzece);
		istorijaPoreza.save();
		showIPoreza("add");
	}
	
	
	public static void edit(long id, Integer idIPoreza, String datumPrimene, long preduzece){
		IstorijaPoreza istorijaPoreza = IstorijaPoreza.findById(id);
		istorijaPoreza.idIPoreza = idIPoreza;
		istorijaPoreza.datumPrimene = datumPrimene;
		istorijaPoreza.preduzece = Preduzece.findById(preduzece);
		istorijaPoreza.save();
		showIPoreza("");
	}
	
	public static void delete(Long id){
		IstorijaPoreza istorijaPoreza = IstorijaPoreza.findById(id);
		istorijaPoreza.delete();
		showIPoreza("");
	}
	
	public static void refresh(Long id, String datumPrimene){
		IstorijePoreza.refresh(id, datumPrimene);
		showIPoreza("");
	}
	
	public static void filter(long preduzece, String datumPrimene){
		List<IstorijaPoreza> istorijePoreza = IstorijaPoreza.find("byPreduzece_idAndDatumPrimene", preduzece, datumPrimene).fetch();
//		List<Preduzece> preduzeca = Preduzece.findAll();
		
		String mode = "edit";
		renderTemplate("IstorijePoreza/showIPoreza.html", istorijePoreza, mode);
	}
	
}
