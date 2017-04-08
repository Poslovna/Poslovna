package controllers;

import java.util.List;

import models.Otpremnica;
import models.RobaUsluga;
import models.StavkeCenovnika;
import models.StavkeOtpremnice;
import play.mvc.Controller;

public class StavkeOtpremnica extends Controller{
	
	public static void showStavkeOtpremnica(String mode){
		List<StavkeOtpremnice> stavkeOtpremnice = StavkeOtpremnice.findAll();
		List<Otpremnica> otpremnica = Otpremnica.findAll();
		List<RobaUsluga> robeUsluga = RobaUsluga.findAll();
		
		if(mode == null || mode.equals(""))
			mode = "edit";
		render(stavkeOtpremnice, otpremnica, robeUsluga, mode);
	
	}
	
	
	public static void create(Double kolicina, Double jedinicnaCena, Double rabat, Double osnovicaZaPDV, Double procenatPDV, Double iznosPDV, Double iznosStavke, long otpremnica, long robaUsluga){
		StavkeOtpremnice stavkeOtpremnice = new StavkeOtpremnice();
		Otpremnica otpremnicaa = Otpremnica.findById(otpremnica);
		stavkeOtpremnice.kolicina = kolicina;
		stavkeOtpremnice.jedinicnaCena = jedinicnaCena;
		stavkeOtpremnice.rabat = rabat;
		stavkeOtpremnice.osnovicaZaPDV = osnovicaZaPDV;
		stavkeOtpremnice.procenatPDV = procenatPDV;
		stavkeOtpremnice.iznosPDV = iznosPDV;
		stavkeOtpremnice.iznosStavke = iznosStavke;
		stavkeOtpremnice.robaUsluga = RobaUsluga.findById(robaUsluga);
		stavkeOtpremnice.otpremnica = otpremnicaa;
		otpremnicaa.iznosZaPlacanje += stavkeOtpremnice.iznosStavke;
		otpremnicaa.osnovica += stavkeOtpremnice.osnovicaZaPDV;
		otpremnicaa.ukupanPDV += stavkeOtpremnice.iznosPDV;
		stavkeOtpremnice.save();
		otpremnicaa.save();
		showStavkeOtpremnica("add");
	}
	
	public static void edit(long id, Double kolicina, Double jedinicnaCena, Double rabat, Double osnovicaZaPDV, Double procenatPDV, Double iznosPDV, Double iznosStavke, long otpremnica, long robaUsluga){
		StavkeOtpremnice stavkeOtpremnice = StavkeOtpremnice.findById(id);
		Otpremnica otpremnicaa = Otpremnica.findById(otpremnica);
		stavkeOtpremnice.kolicina = kolicina;
		stavkeOtpremnice.jedinicnaCena = jedinicnaCena;
		stavkeOtpremnice.rabat = rabat;
		stavkeOtpremnice.osnovicaZaPDV = osnovicaZaPDV;
		stavkeOtpremnice.procenatPDV = procenatPDV;
		stavkeOtpremnice.iznosPDV = iznosPDV;
		stavkeOtpremnice.iznosStavke = iznosStavke;
		stavkeOtpremnice.otpremnica = otpremnicaa;
		otpremnicaa.iznosZaPlacanje += stavkeOtpremnice.iznosStavke;
		otpremnicaa.osnovica += stavkeOtpremnice.osnovicaZaPDV;
		otpremnicaa.ukupanPDV += stavkeOtpremnice.iznosPDV;
		stavkeOtpremnice.robaUsluga = RobaUsluga.findById(robaUsluga);
		stavkeOtpremnice.save();
		otpremnicaa.save();
		showStavkeOtpremnica("");
		
	}
	
	public static void delete(long id){
		StavkeOtpremnice stavkeOtpremnice = StavkeOtpremnice.findById(id);
		stavkeOtpremnice.delete();
		showStavkeOtpremnica("");
	}
	
	public static void refresh(Long id, String kolicina, Double jedinicnaCena, Double rabat, Double osnovicaZaPDV, Double procenatPDV, Double iznosPDV, Double iznosStavke, long otpremnica, long robaUsluga){
		StavkeOtpremnica.refresh(id, kolicina, jedinicnaCena, rabat, osnovicaZaPDV, procenatPDV, iznosPDV, iznosStavke, otpremnica, robaUsluga);
		showStavkeOtpremnica("");
	}
	
	
	public static void filter(float jedinicnaCena){
		List<StavkeOtpremnice> stavkeOtpremnice = StavkeOtpremnice.find("byJedinicnaCena",jedinicnaCena ).fetch();
//		List<Faktura> fakture = Faktura.findAll();
//		List<RobaUsluga> robeUsluga = RobaUsluga.findAll();
		
		String mode = "edit";
		renderTemplate("StavkeOtpremnica/showStavkeOtpremnica.html", stavkeOtpremnice, mode);
	}
	public static void stavke(long id){
		
		List<StavkeOtpremnice> stavkeOtpremnice = StavkeOtpremnice.find("byOtpremnica_id", id).fetch();
		String mode = "edit";
		System.out.println(stavkeOtpremnice);
		renderTemplate("StavkeOtpremnica/showStavkeOtpremnica.html", stavkeOtpremnice, mode);
	}
}
