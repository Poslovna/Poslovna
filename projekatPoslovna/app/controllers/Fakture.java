package controllers;

import java.sql.Date;
import java.util.List;

import play.data.validation.Required;
import play.data.validation.Error;
import play.mvc.Controller;
import models.Faktura;
import models.Otpremnica;
import models.PoslovnaGodina;
import models.PoslovniPartner;
import models.Preduzece;


//ne radi pretraga i brisanje

public class Fakture extends Controller {
	
	public static void showFakture(String mode){
	List<Faktura> fakture = Faktura.findAll();
	List<Preduzece> preduzeca = Preduzece.findAll();
	List<PoslovnaGodina> poslovneGodine = PoslovnaGodina.findAll();
	List<PoslovniPartner> poslovniPartneri = PoslovniPartner.findAll();
	List<Otpremnica> otpremnice = Otpremnica.findAll();
	if(mode == null || mode.equals(""))
		mode = "edit";
	render(fakture,preduzeca,poslovneGodine,poslovniPartneri,otpremnice, mode);

}
	public static void add(Integer brojFakture, String tipFakture, 
			Date datumFakture, Date datumValute, Date datumObracuna, 
			Double ukupnoRobaIUsluga, Double ukupanRabat, Double ukupanPorez,
			Double iznosFakture, String uplataNaRacun, String pozivNaBroj,
			String statusFakture, long preduzece, long poslovnaGodina, 
			long poslovniPartner, long otpremnica) {
		
		if(validation.hasErrors()) {
			for(Error error : validation.errors()) {
			System.out.println(error.message());
			}
			validation.keep();
			showFakture("add");
		}else {
			Faktura faktura = new Faktura();
			faktura.brojFakture = brojFakture;
			faktura.tipFakture = tipFakture;
			faktura.datumFakture = datumFakture;
			faktura.datumValute = datumValute;
			faktura.datumObracuna = datumObracuna;
			faktura.ukupnoRobaIUsluga = ukupnoRobaIUsluga;
			faktura.ukupanRabat = ukupanRabat;
			faktura.ukupanPorez = ukupanPorez;
			faktura.iznosFakture = iznosFakture;
			faktura.uplataNaRacun = uplataNaRacun;
			faktura.pozivNaBroj = pozivNaBroj;
			faktura.statusFakture = statusFakture;
			faktura.preduzece = Preduzece.findById(preduzece);
			faktura.poslovnaGodina = PoslovnaGodina.findById(poslovnaGodina);
			faktura.poslovniPartner = PoslovniPartner.findById(poslovniPartner);
			faktura.otpremnica = Otpremnica.findById(otpremnica);
			faktura.save();
			validation.keep();
			showFakture("add");
		
	}
	}
	
	public static void search(Integer brojFakture, String tipFakture, 
			Date datumFakture, Date datumValute, Date datumObracuna, 
			Double ukupnoRobaIUsluga, Double ukupanRabat, Double ukupanPorez,
			Double iznosFakture, String uplataNaRacun, String pozivNaBroj,
			String statusFakture, long preduzece, long poslovnaGodina, 
			long poslovniPartner, long otpremnica){
		
		List<Faktura> fakture = Faktura.find("byBrojFaktureLikeAndTipFaktureLikeAndDatumFaktureLikeAndDatumValuteLikeAndDatumObracunaLikeAndUkupnoRobaIUslugaLikeAndUkupanRabatLikeAndUkupanPorezLikeAndStatusFaktureLikeAndPreduzece_idAndPoslovnaGodina_idAndPoslovniPartner_idAndOtpremnica_id",
				"%"+ brojFakture +"%", "%"+tipFakture+"%","%"+datumFakture+"%", "%"+datumFakture+"%", "%"+datumFakture+"%", "%"+datumValute+"%", "%"+datumObracuna+"%", "%"+ukupnoRobaIUsluga+"%", "%"+ukupanRabat+"%","%"+ukupanPorez+"%", "%"+statusFakture+"%", preduzece, poslovnaGodina, poslovniPartner, otpremnica).fetch();
		List<Preduzece> preduzeca = Preduzece.findAll();
		List<PoslovnaGodina> poslovneGodine = PoslovnaGodina.findAll();
		List<PoslovniPartner> poslovniPartneri = PoslovniPartner.findAll();
		List<Otpremnica> otpremnice = Otpremnica.findAll();
		String mode = "edit";
		renderTemplate("Fakture/show.html", fakture,preduzeca,
				poslovneGodine,poslovniPartneri,otpremnice, mode);
	
	}
	
	
	public static void edit(Integer brojFakture, String tipFakture, 
			Date datumFakture, Date datumValute, Date datumObracuna, 
			Double ukupnoRobaIUsluga, Double ukupanRabat, Double ukupanPorez,
			Double iznosFakture, String uplataNaRacun, String pozivNaBroj,
			String statusFakture, long preduzece, long poslovnaGodina, 
			long poslovniPartner, long otpremnica, long id){
		
		Faktura faktura = Faktura.findById(id);
		faktura.brojFakture = brojFakture;
		faktura.tipFakture = tipFakture;
		faktura.datumFakture = datumFakture;
		faktura.datumValute = datumValute;
		faktura.datumObracuna = datumObracuna;
		faktura.ukupnoRobaIUsluga = ukupnoRobaIUsluga;
		faktura.ukupanRabat = ukupanRabat;
		faktura.ukupanPorez = ukupanPorez;
		faktura.iznosFakture = iznosFakture;
		faktura.uplataNaRacun = uplataNaRacun;
		faktura.pozivNaBroj = pozivNaBroj;
		faktura.statusFakture = statusFakture;
		faktura.preduzece = Preduzece.findById(preduzece);
		faktura.poslovnaGodina = PoslovnaGodina.findById(poslovnaGodina);
		faktura.poslovniPartner = PoslovniPartner.findById(poslovniPartner);
		faktura.otpremnica = Otpremnica.findById(otpremnica);
		faktura.save();
		showFakture("");

						
	}
	
	
	public static void delete(long id){
		Faktura faktura = Faktura.findById(id);
		faktura.delete();
		showFakture("");

			
	}

}
