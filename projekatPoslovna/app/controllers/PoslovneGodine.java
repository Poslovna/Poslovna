package controllers;

import java.util.List;

import models.PoslovnaGodina;
import play.data.validation.Required;
import play.mvc.Controller;

public class PoslovneGodine extends Controller{

	
	public static void showGodina(String mode){
		List<PoslovnaGodina> godine = PoslovnaGodina.findAll();
		if (mode == null || mode.equals(""))
			mode = "edit";
		render(godine, mode);
	}
	
	
	public static void create(int godina, Boolean zakljucena, String zakljucenaStr){
		PoslovnaGodina poslovnaGodina = new PoslovnaGodina();
		poslovnaGodina.godina = godina;
		poslovnaGodina.zakljucena = zakljucena;
		if (zakljucena == null){
			zakljucenaStr = "ne";
		}else zakljucenaStr = "da";
		poslovnaGodina.zakljucenaStr = zakljucenaStr;
		
		poslovnaGodina.save();
		showGodina("add");
	}
	
	public static void edit(long id, int godina, Boolean zakljucena, String zakljucenaStr){
		PoslovnaGodina poslovnaGodina = PoslovnaGodina.findById(id);
		poslovnaGodina.godina = godina;
		poslovnaGodina.zakljucena = zakljucena;
		if (zakljucena == null){
			zakljucenaStr = "ne";
		}else zakljucenaStr = "da";
		poslovnaGodina.save();
		showGodina("edit");
	}
	
	public static void delete(Long id){
		PoslovnaGodina poslovnaGodina = PoslovnaGodina.findById(id);
		poslovnaGodina.delete();
		showGodina("");
	}
	
	public static void refresh(Long id, int godina){
		PoslovneGodine.refresh(id, godina);
		showGodina("");
	}
	
	public static void filter(int godina){
		String year = "" + godina;
		List<PoslovnaGodina> poslovneGodine = PoslovnaGodina.find("byGodina",year).fetch();
		
		
		String mode = "edit";
		renderTemplate("PoslovneGodine/showGodina.html", poslovneGodine, mode);
	}
}
	
	
//}	public static void filter(@Required int godina){
//	List<PoslovnaGodina> poslovneGodine =PoslovnaGodina.find("byGodina", godina).fetch();
//	String mode = "edit";
//	renderTemplate("PoslovneGodine/show.html", poslovneGodine, mode);
//}
