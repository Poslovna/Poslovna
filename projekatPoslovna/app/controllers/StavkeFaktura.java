package controllers;

import java.util.List;

import models.Faktura;
import models.RobaUsluga;
import models.StavkaNarudzbenice;
import models.StavkeFakture;

import play.mvc.Controller;

public class StavkeFaktura extends Controller{

	
	public static void showStavkeFaktura(String mode){
		List<StavkeFakture> stavkeFaktura = StavkeFakture.findAll();
		List<Faktura> fakture = Faktura.findAll();
		List<RobaUsluga> robeUsluga = RobaUsluga.findAll();
		
		if(mode == null || mode.equals(""))
			mode = "edit";
		render(stavkeFaktura, fakture, robeUsluga, mode);
	
	}
	
	
	public static void create(Double kolicina, Double jedinicnaCena, Double rabat, Double osnovicaZaPDV, Double procenatPDV, Double iznosPDV, Double iznosStavke, long faktura, long robaUsluga){
		StavkeFakture stavkeFakture = new StavkeFakture();
		RobaUsluga roba=RobaUsluga.findById(robaUsluga);
		Faktura fakturaa=Faktura.findById(faktura);
		stavkeFakture.kolicina = kolicina;
		stavkeFakture.jedinicnaCena=roba.stavkeCenovnika.get(0).jedinicnaCena;
		stavkeFakture.rabat = rabat;
		stavkeFakture.osnovicaZaPDV=kolicina*stavkeFakture.jedinicnaCena-rabat;
		stavkeFakture.procenatPDV=roba.grupaRobe.pdv.stopaPdva.get(0).procenat;
		stavkeFakture.iznosPDV=stavkeFakture.osnovicaZaPDV*stavkeFakture.procenatPDV/100;
		stavkeFakture.iznosStavke=stavkeFakture.osnovicaZaPDV+stavkeFakture.iznosPDV;
		stavkeFakture.robaUsluga=RobaUsluga.findById(robaUsluga);
		stavkeFakture.faktura=fakturaa;
		fakturaa.iznosZaPlacanje+=stavkeFakture.iznosStavke;
		fakturaa.osnovica+=stavkeFakture.osnovicaZaPDV;
		fakturaa.ukupanPDV+=stavkeFakture.iznosPDV;
		
		fakturaa.save();
		stavkeFakture.save();
		showStavkeFaktura("add");
	}
	
	public static void edit(long id, Double kolicina, Double jedinicnaCena, Double rabat, Double osnovicaZaPDV, Double procenatPDV, Double iznosPDV, Double iznosStavke, long faktura, long robaUsluga){
		StavkeFakture stavkeFakture = StavkeFakture.findById(id);
		RobaUsluga roba=RobaUsluga.findById(robaUsluga);
		Faktura fakturaa=Faktura.findById(faktura);
		stavkeFakture.kolicina = kolicina;
		stavkeFakture.jedinicnaCena=roba.stavkeCenovnika.get(0).jedinicnaCena;
		stavkeFakture.rabat = rabat;
		stavkeFakture.osnovicaZaPDV=kolicina*stavkeFakture.jedinicnaCena-rabat;
		stavkeFakture.procenatPDV=roba.grupaRobe.pdv.stopaPdva.get(0).procenat;
		stavkeFakture.iznosPDV=stavkeFakture.osnovicaZaPDV*stavkeFakture.procenatPDV/100;
		stavkeFakture.iznosStavke=stavkeFakture.osnovicaZaPDV+stavkeFakture.iznosPDV;
		stavkeFakture.robaUsluga=RobaUsluga.findById(robaUsluga);
		stavkeFakture.faktura=fakturaa;
		fakturaa.iznosZaPlacanje+=stavkeFakture.iznosStavke;
		fakturaa.osnovica+=stavkeFakture.osnovicaZaPDV;
		fakturaa.ukupanPDV+=stavkeFakture.iznosPDV;
		
		fakturaa.save();
		stavkeFakture.save();
		
	}
	
	public static void delete(Long id){
		StavkeFakture stavkeFakture = StavkeFakture.findById(id);
		stavkeFakture.delete();
		showStavkeFaktura("");
	}
	
	public static void refresh(Long id, String kolicina, Double jedinicnaCena, Double rabat, Double osnovicaZaPDV, Double procenatPDV, Double iznosPDV, Double iznosStavke, long fakture, long robaUsluga){
		StavkeFaktura.refresh(id, kolicina, jedinicnaCena, rabat, osnovicaZaPDV, procenatPDV, iznosPDV, iznosStavke, fakture, robaUsluga);
		showStavkeFaktura("");
	}
	
	
	public static void filter(float jedinicnaCena){
		List<StavkeFakture> stavkeFakture = StavkeFakture.find("byJedinicnaCena",jedinicnaCena ).fetch();
//		List<Faktura> fakture = Faktura.findAll();
//		List<RobaUsluga> robeUsluga = RobaUsluga.findAll();
		
		String mode = "edit";
		renderTemplate("StavkeFaktura/showStavkeFaktura.html", stavkeFakture, mode);
	}
	
//	public static void filter(long robaIliUsluga, long faktura, float kolicina,float jedinicnaCena,float rabat,float osnovica, float procenatPDV, float iznosPDV, float iznosStavke){
//		List<StavkaFakture> stavkeFakture = StavkaFakture.find("byRobaIliUsluga_idAndCenovnik_idAndKolicinaAndJedinicnaCenaAndRabatAndOsnovicaAndProcenatPDVAndIznosPDVAndIznosStavke", robaIliUsluga,faktura,kolicina, jedinicnaCena, rabat, osnovica, procenatPDV, iznosPDV, iznosStavke).fetch();
//		String mode = "edit";
//		renderTemplate("StavkeFakture/show.html", stavkeFakture, mode);
//	}
public static void stavke(long id){
		
		List<StavkeFakture> stavkeFaktura = StavkeFakture.find("byFaktura_id", id).fetch();
		String mode = "edit";
		System.out.println(stavkeFaktura);
		renderTemplate("StavkeFaktura/showStavkeFaktura.html", stavkeFaktura, mode);
	}
}
