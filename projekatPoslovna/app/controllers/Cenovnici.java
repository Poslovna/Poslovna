package controllers;

import java.util.List;

import models.Cenovnik;
import models.Preduzece;
import play.mvc.*;

public class Cenovnici extends Controller {

    public static void show1(String mode) {
    	List<Cenovnik> cenovnici = Cenovnik.findAll();
    	if(mode == null || mode.equals(""))
    		
    		mode = "edit";
    	System.out.println("usoo");
        render(cenovnici, mode);
    }

    
    public static void create(Integer rbrCenovnika, String datmPrimene){
    	Cenovnik cenovnik = new Cenovnik(rbrCenovnika, datmPrimene);
    	cenovnik.save();
    	show1("add");

    }
    public static void edit(){
    
    }
    
    public static void filter(){
    	
    }
}
