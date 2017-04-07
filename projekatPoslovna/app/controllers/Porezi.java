package controllers;

import java.util.Date;
import java.util.List;


import models.Porez;
import play.mvc.Controller;

public class Porezi extends Controller{

	public static void showPorezi(String mode){
		List<Porez> porezi = Porez.findAll();
		if(mode == null || mode.equals(""))
    		mode = "edit";
        render(porezi, mode);
	}
	public static void create(String oznakaPoreza, String nazivPoreza, Boolean vazeci, String vazeciStr){
		Porez porez = new Porez();
		porez.oznakaPoreza = oznakaPoreza;
		porez.nazivPoreza = nazivPoreza;
		porez.vazeci = vazeci;
		if (vazeci == null) {
			vazeciStr = "ne";
		}else vazeciStr = "da";
		porez.vazeciStr = vazeciStr;
		
		porez.save();
		showPorezi("add");
	}
	public static void edit(String oznakaPoreza,String nazivPoreza, boolean vazeci,long id){
		Porez porez  = Porez.findById(id); 
		porez.oznakaPoreza=oznakaPoreza;
		porez.nazivPoreza = nazivPoreza;
		porez.vazeci = vazeci;
		porez.save();
		showPorezi("");
	}
	
	public static void delete(Long id){
		Porez porez  = Porez.findById(id);
		porez.delete();
		showPorezi("");
	}
	public static void refresh(long id, String oznakaPoreza, String nazivPoreza,boolean vazeci){
		Porezi.refresh(id, oznakaPoreza, nazivPoreza,vazeci); // mozda vazeci
		showPorezi("");
	}
	public static void filter( String oznakaPoreza, String nazivPoreza,boolean vazeci){
		List porez = Porez.find("byOznakaPorezaLikeAndNazivPorezaLikeAndVazeciLike", "%" + oznakaPoreza + "%", "%"+ nazivPoreza + "%", "%"+ vazeci + "%").fetch();
		
		
		String mode = "edit";
		renderTemplate("Porezi/showPorezi.html", porez, mode);
	}
}
