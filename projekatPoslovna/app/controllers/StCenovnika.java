package controllers;

import java.util.List;

import models.Cenovnik;
import models.PoreskaStopa;
import models.RobaUsluga;
import models.StavkeCenovnika;
import play.mvc.*;

public class StCenovnika extends Controller {

    public static void showStCenovnika(String mode) {
    	List<StavkeCenovnika> stCenovnika = StavkeCenovnika.findAll();

    	List<RobaUsluga> robeUsluga = RobaUsluga.findAll();
    	List<Cenovnik> cenovnici = Cenovnik.findAll();
    	if(mode == null || mode.equals(""))
    		mode = "edit";
        render(stCenovnika, robeUsluga, cenovnici,  mode);
    }

    public static void create(double jedinicnaCena, long robaUsluga, long cenovnik){
    	
    	StavkeCenovnika stCenovnik = new StavkeCenovnika();
    	stCenovnik.jedinicnaCena = jedinicnaCena;
    	stCenovnik.robaUsluga = RobaUsluga.findById(robaUsluga);
    	stCenovnik.cenovnik = Cenovnik.findById(cenovnik);
    	stCenovnik.save();
    	showStCenovnika("add");
    }
    
    public static void edit(double jedinicnaCena, long robaUsluga, long cenovnik, long id){
    	StavkeCenovnika stCenovnik = StavkeCenovnika.findById(id);
    	stCenovnik.jedinicnaCena = jedinicnaCena;
    	stCenovnik.robaUsluga = RobaUsluga.findById(robaUsluga);
    	stCenovnik.cenovnik = Cenovnik.findById(cenovnik);
    	stCenovnik.save();
    	showStCenovnika("");
    }

    public static void delete(Long id){
		StavkeCenovnika stCenovnika = StavkeCenovnika.findById(id);
		stCenovnika.delete();
		showStCenovnika("");
    }
    
	public static void filter(double jedinicnaCena){
		List<StavkeCenovnika> stCenovnika = StavkeCenovnika.find("byJedinicnaCena", jedinicnaCena).fetch();
		String mode = "edit";
		renderTemplate("StCenovnika/showStCenovnika.html", stCenovnika, mode);
	}
	public static void stavke(long id){
		
		List<StavkeCenovnika> stCenovnika = StavkeCenovnika.find("byCenovnik_id", id).fetch();
		String mode = "edit";
		renderTemplate("StCenovnika/showStCenovnika.html", stCenovnika, mode);
	}

}
