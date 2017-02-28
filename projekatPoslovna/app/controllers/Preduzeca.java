package controllers;

import java.util.List;

import models.Mesto;
import models.Preduzece;
import play.mvc.*;

public class Preduzeca extends Controller {

    public static void showPreduzeca(String mode) {
    	List<Mesto> mesta = Mesto.findAll();
    	List<Preduzece> preduzeca = Preduzece.findAll();
    	if(mode == null || mode.equals(""))
    		mode = "edit";
        render(mesta, preduzeca, mode);
    }

    
    public static void create(String naziv, String adresaPreduzeca, String pib, String telefon, String email, String logo,
			long mesto){
    	
    	Preduzece preduzece = new Preduzece();
		
    	preduzece.naziv = naziv;
    	preduzece.adresaPreduzeca = adresaPreduzeca;
    	preduzece.pib = pib;
    	preduzece.telefon = telefon;
    	preduzece.email = email;
    	preduzece.logo = logo;
    	
    	preduzece.mesto = Mesto.findById(mesto);
    	System.out.println(mesto);
    	preduzece.save();
    	showPreduzeca("add");
    }
    
    public static void edit(String naziv, String adresaPreduzeca, String pib, String telefon, String email, String logo,
			long mesto, long id){
    	
    	Preduzece preduzece = Preduzece.findById(id);
    	preduzece.naziv = naziv;
    	preduzece.adresaPreduzeca = adresaPreduzeca;
    	preduzece.pib = pib;
    	preduzece.telefon = telefon;
    	preduzece.email = email;
    	preduzece.logo = logo;
    	preduzece.mesto = Mesto.findById(mesto);
    	preduzece.save();
    	showPreduzeca("");
    }
    
    public static void delete(long id){
		Preduzece preduzece = Preduzece.findById(id);
		preduzece.delete();
		showPreduzeca("");

			
	}
    
  
}
