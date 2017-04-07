package controllers;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

import com.thoughtworks.xstream.XStream;

import play.data.validation.Required;
import play.data.validation.Error;
import play.mvc.Controller;
import models.Faktura;
import models.Otpremnica;
import models.PoslovnaGodina;
import models.PoslovniPartner;
import models.Preduzece;
import models.StavkeFakture;
import models.StavkeOtpremnice;


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
			String datumFakture, String datumValute, String datumObracuna, 
			Double ukupnoRobaIUsluga, Double ukupanRabat, Double ukupanPorez,
			Double osnovica, Double ukupanPDV,
			Double iznosZaPlacanje, String uplataNaRacun, String pozivNaBroj,
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
			faktura.osnovica = osnovica;
			faktura.ukupanPDV = ukupanPDV;
			faktura.iznosZaPlacanje = iznosZaPlacanje;
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
	
	public static void search(long preduzece){
		
		List<Preduzece> preduzeca = Preduzece.findAll();
		
		List<Faktura> fakture = Faktura.find("byPreduzece_id", preduzece).fetch();
		String mode = "search";
		renderTemplate("Fakture/showFakture.html", fakture, preduzeca, mode);
		
//		List<Faktura> fakture = Faktura.find("byBrojFaktureLikeAndTipFaktureLikeAndDatumFaktureLikeAndDatumValuteLikeAndDatumObracunaLikeAndUkupnoRobaIUslugaLikeAndUkupanRabatLikeAndUkupanPorezLikeAndStatusFaktureLikeAndPreduzece_idAndPoslovnaGodina_idAndPoslovniPartner_idAndOtpremnica_id",
//				"%"+ brojFakture +"%", "%"+tipFakture+"%","%"+datumFakture+"%", "%"+datumFakture+"%", "%"+datumFakture+"%", "%"+datumValute+"%", "%"+datumObracuna+"%", "%"+ukupnoRobaIUsluga+"%", "%"+ukupanRabat+"%","%"+ukupanPorez+"%", "%"+statusFakture+"%", preduzece, poslovnaGodina, poslovniPartner, otpremnica).fetch();
//		List<PoslovnaGodina> poslovneGodine = PoslovnaGodina.findAll();
//		List<PoslovniPartner> poslovniPartneri = PoslovniPartner.findAll();
//		List<Otpremnica> otpremnice = Otpremnica.findAll();
//		renderTemplate("Fakture/show.html", fakture,preduzeca,
//				poslovneGodine,poslovniPartneri,otpremnice, mode);
	
	}
	
	public static void edit(Integer brojFakture, String tipFakture, 
			String datumFakture, String datumValute, String datumObracuna, 
			Double ukupnoRobaIUsluga, Double ukupanRabat, Double ukupanPorez,
			Double osnovica, Double ukupanPDV,
			Double iznosZaPlacanje, String uplataNaRacun, String pozivNaBroj,
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
		faktura.osnovica = osnovica;
		faktura.ukupanPDV = ukupanPDV;
		faktura.iznosZaPlacanje = iznosZaPlacanje;
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
	
	public static void export(long id) throws IOException{
		String FILENAME = "E:\\filename.xml";

		Faktura faktura = Faktura.findById(id);
		
		XStream xstream=new XStream();
		
		String xmlString=xstream.toXML(faktura);
		System.out.println(xmlString);
		FileWriter fw = new FileWriter(FILENAME);
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write(xmlString);
		bw.close();
		showFakture("");
		
	}
	
	public static void generate(int brojOtpremnice, long poslovnaGodina, long poslovniPartner, long id){

		List<Otpremnica> otpremnicaL=Otpremnica.findAll();
		int n=otpremnicaL.size()-1;
		Faktura faktura=Faktura.findById(id);
		Otpremnica otpremnicaa=otpremnicaL.get(n);		
		Otpremnica otpremnica=new Otpremnica();
		otpremnica.brojOtpremnice=otpremnicaa.brojOtpremnice+1;
		otpremnica.osnovica=faktura.osnovica;
		otpremnica.ukupanPDV=faktura.ukupanPDV;
		otpremnica.iznosZaPlacanje=faktura.iznosZaPlacanje;
		otpremnica.poslovnaGodina=PoslovnaGodina.findById(poslovnaGodina);
		otpremnica.poslovniPartner=PoslovniPartner.findById(poslovniPartner);
		otpremnica.save();
		System.out.println(otpremnica);
	
		for (StavkeFakture stavkaF : faktura.stavkeFakture) {
			System.out.println("wrehwhweh");
			StavkeOtpremnice stavkaO=new StavkeOtpremnice();
			stavkaO.iznosStavke=stavkaF.iznosStavke;
			stavkaO.kolicina=stavkaF.kolicina;
			stavkaO.jedinicnaCena=stavkaF.jedinicnaCena;
			stavkaO.rabat=stavkaF.rabat;
			stavkaO.osnovicaZaPDV=stavkaF.osnovicaZaPDV;
			stavkaO.procenatPDV=stavkaF.procenatPDV;
			stavkaO.robaUsluga=stavkaF.robaUsluga;
			stavkaO.iznosPDV=stavkaF.iznosPDV;
			stavkaO.otpremnica=otpremnica;
			System.out.println(stavkaO);
			stavkaO.save();
			
		}
		
		showFakture("");
	}	
	

}
