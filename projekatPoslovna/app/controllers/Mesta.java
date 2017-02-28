package controllers;

import java.util.List;

import models.Mesto;
import play.mvc.*;

public class Mesta extends Controller {

    public static void showMesta(String mode) {
    	List<Mesto> mesta = Mesto.findAll();
    	if(mode == null || mode.equals(""))
    		mode = "edit";
        render(mesta, mode);
    }
    
    public static void create(String nazivMesta){
    	Mesto mesto = new Mesto();
    	mesto.nazivMesta = nazivMesta;
    	mesto.save();
    	showMesta("add");
    }

    public static void edit(long id, String nazivMesta){

    	Mesto mesto = Mesto.findById(id);
    	mesto.nazivMesta = nazivMesta;
    	mesto.save();
    	showMesta("edit");
    }
    
public static void delete(long id){
    	
    	Mesto mesto = Mesto.findById(id);
    	mesto.delete();
    	showMesta("");
    	
    }

public static void refresh(long id, String nazivMesta){
	Mesta.refresh(id, nazivMesta);
	showMesta("");

	}

public static void filter(String nazivMesta){
	List mesta = Mesto.find("byNazivMestaLike", "%" + nazivMesta + "%").fetch();
	
	String mode = "edit";
	renderTemplate("Mesta/showMesta.html", mesta, mode);
	
	}
}
