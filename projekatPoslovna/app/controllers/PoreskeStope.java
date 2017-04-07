package controllers;

import java.util.Date;
import java.util.List;

import models.IstorijaPoreza;
import models.PDV;
import models.PoreskaStopa;
import models.Porez;
import models.StopaPDVa;
import play.mvc.Controller;

public class PoreskeStope extends Controller{
	
	public static void showPoreskeStope(String mode){
		List<PoreskaStopa>poreskeStope=PoreskaStopa.findAll();
		List<Porez>porezi=Porez.findAll();
		List<IstorijaPoreza>istorijePoreza=IstorijaPoreza.findAll();
		if (mode == null || mode.equals(""))
			mode = "edit";
		render(poreskeStope,porezi,istorijePoreza,mode);
	}

	
	public static void create(double iznosStope,long porez,long istorijaPoreza){
		PoreskaStopa poreskaStopa= new PoreskaStopa();
		poreskaStopa.iznosStope = iznosStope;
		poreskaStopa.porez = Porez.findById(porez);
		poreskaStopa.istorijaPoreza = IstorijaPoreza.findById(istorijaPoreza);
		poreskaStopa.save();
		showPoreskeStope("add");
	}
	public static void edit(long id,double iznosStope,long porez,long istorijaPoreza){
		PoreskaStopa poreskaStopa = PoreskaStopa.findById(id);
		poreskaStopa.iznosStope = iznosStope;
		poreskaStopa.porez = Porez.findById(porez);
		poreskaStopa.istorijaPoreza = IstorijaPoreza.findById(istorijaPoreza);
		poreskaStopa.save();
		showPoreskeStope("");
	}
	public static void delete(Long id){
		PoreskaStopa poreskaStopa = PoreskaStopa.findById(id);
		poreskaStopa.delete();
		showPoreskeStope("");
	}
	public static void filter(double iznosStope){
		List<PoreskaStopa> poreskeStope = PoreskaStopa.find("byIznosStope", iznosStope).fetch();
		String mode = "edit";
		renderTemplate("PoreskeStope/showPoreskeStope.html", poreskeStope, mode);
	}

}
