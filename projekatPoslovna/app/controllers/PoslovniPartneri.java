package controllers;

import java.util.Date;
import java.util.List;

import models.Mesto;
import models.Narudzba;
import models.Otpremnica;
import models.PoslovnaGodina;
import models.PoslovniPartner;
import models.Preduzece;
import play.data.validation.Error;
import play.data.validation.Required;
import play.mvc.Controller;

public class PoslovniPartneri extends Controller {
	
	public static void showPoslovniPartneri(String mode){
		List<PoslovniPartner> poslovniPartneri = PoslovniPartner.findAll();
		List<Mesto> mesta = Mesto.findAll();
		List<Preduzece> preduzeca = Preduzece.findAll();
		
		if(mode == null || mode.equals(""))
			mode = "edit";
		render(poslovniPartneri,mesta, preduzeca, mode);
	}
	
	
	public static void add(String nazivPartnera, String adresa, String vrstaPartnera,
			long mesto, long preduzece) {
		
		if(validation.hasErrors()) {
			for(Error error : validation.errors()) {
			System.out.println(error.message());
			}
			validation.keep();
			showPoslovniPartneri("add");
		}else {
			PoslovniPartner poslovniPartner = new PoslovniPartner();
			poslovniPartner.nazivPartnera = nazivPartnera;
			poslovniPartner.adresa = adresa;
			poslovniPartner.vrstaPartnera = vrstaPartnera;
			poslovniPartner.mesto = Mesto.findById(mesto);
			poslovniPartner.preduzece = Preduzece.findById(preduzece);

			
			poslovniPartner.save();
			validation.keep();
			showPoslovniPartneri("add");
		}
	}
	
	public static void edit(long id, String nazivPartnera, String adresa, String vrstaPartnera,
			long mesto, long preduzece){
		
		PoslovniPartner poslovniPartner = PoslovniPartner.findById(id);
		poslovniPartner.nazivPartnera = nazivPartnera;
		poslovniPartner.adresa = adresa;
		poslovniPartner.vrstaPartnera = vrstaPartnera;
		poslovniPartner.mesto = Mesto.findById(mesto);
		poslovniPartner.preduzece = Preduzece.findById(preduzece);
		poslovniPartner.save();
		showPoslovniPartneri("");
		
	}
	
	public static void search(String nazivPartnera, String adresa, String vrstaPartnera){
		
		List<PoslovniPartner> poslovniPartner = PoslovniPartner.find("byNazivPartneraLikeAndAdresaLikeAndVrstaPartneraLike", "%"+nazivPartnera+"%","%"+adresa+"%", "%"+vrstaPartnera+"%").fetch();
		
//		List<Mesto> mesta = Mesto.findAll();
//		List<Preduzece> preduzeca = Preduzece.findAll();
	
		String mode = "edit";
		renderTemplate("PoslovniPartneri/showPoslovniPartneri.html", poslovniPartner ,mode);
		
		
	}
	
	public static void refresh(Long id, String nazivPartnera, String adresa, String vrstaPartnera,
			long mesto, long preduzece){
		PoslovniPartneri.refresh(id, nazivPartnera, adresa, vrstaPartnera, mesto, preduzece);
		showPoslovniPartneri("");
	}
	
	public static void delete(Long id){
		PoslovniPartner poslovniPartner = PoslovniPartner.findById(id);
		poslovniPartner.delete();
		showPoslovniPartneri("");

			
	}

}
