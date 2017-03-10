package controllers;

import java.util.List;

import models.Faktura;
import models.Porez;
import models.Preduzece;
import play.mvc.*;

public class ObracunatiPorezi extends Controller {

    public static void showObracunatiPorezi(String mode) {
    	List<models.ObracunatiPorezi> obracunatiPorezi = models.ObracunatiPorezi.findAll();
    	List<Faktura> faktura = Faktura.findAll();
    	List<Porez> porez = Porez.findAll();
    	if(mode == null || mode.equals(""))
    		
    		mode = "edit";
    	System.out.println("usoo");
        render(obracunatiPorezi, faktura, porez, mode);
    }

    
    public static void create(double stopa, double iznos, long id){
    	models.ObracunatiPorezi obracunatiPorezi = new models.ObracunatiPorezi();
    	obracunatiPorezi.stopa = stopa;
    	obracunatiPorezi.iznos = iznos;
    	
    	obracunatiPorezi.faktura = Faktura.findById(id);
    	obracunatiPorezi.porez = Porez.findById(id);
    	
    	obracunatiPorezi.save();
    	showObracunatiPorezi("add");

    }
    public static void edit(double stopa, double iznos, long id){
    
    	models.ObracunatiPorezi obracunatiPorezi = models.ObracunatiPorezi.findById(id);
    	obracunatiPorezi.stopa = stopa;
    	obracunatiPorezi.iznos = iznos;
    	obracunatiPorezi.faktura = Faktura.findById(id);
    	obracunatiPorezi.porez = Porez.findById(id);
    	obracunatiPorezi.save();
    	showObracunatiPorezi("");
    }
    
    public static void delete(long id){
    	models.ObracunatiPorezi obracunatiPorezi = models.ObracunatiPorezi.findById(id);
    	obracunatiPorezi.delete();
    	showObracunatiPorezi("");
    }
}
