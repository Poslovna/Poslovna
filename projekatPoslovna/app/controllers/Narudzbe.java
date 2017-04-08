package controllers;

import java.util.Date;

import java.util.List;

import models.Faktura;
import models.Narudzba;
import models.Otpremnica;
import models.PoslovnaGodina;
import models.PoslovniPartner;
import models.Preduzece;
import models.StavkaNarudzbenice;
import models.StavkeFakture;
import models.StavkeOtpremnice;
import play.data.validation.Error;
import play.mvc.Controller;

public class Narudzbe extends Controller {
	
	public static void showNarudzbe(String mode){
		List<Narudzba> narudzbe = Narudzba.findAll();
		List<Otpremnica> otpremnice = Otpremnica.findAll();

		List<PoslovnaGodina> poslovneGodine = PoslovnaGodina.findAll();
		List<PoslovniPartner> poslovniPartneri = PoslovniPartner.findAll();
		if(mode == null || mode.equals(""))
			mode = "edit";
		render(narudzbe,otpremnice, poslovneGodine,poslovniPartneri, mode);

	}
		public static void add(Integer brojNarudzbe, String datumNarudzbe,String sifra, Double kolicina,  
				long otpremnica, long poslovnaGodina, long poslovniPartner ) {
			
			if(validation.hasErrors()) {
				for(Error error : validation.errors()) {
				System.out.println(error.message());
				}
				validation.keep();
				showNarudzbe("add");
			}else {
				Narudzba narudzba = new Narudzba();
				narudzba.brojNarudzbe = brojNarudzbe;
				narudzba.datumNarudzbe = datumNarudzbe;
				narudzba.sifra = sifra;
				narudzba.kolicina = kolicina;
				narudzba.otpremnica = Otpremnica.findById(otpremnica);
				narudzba.poslovnaGodina = PoslovnaGodina.findById(poslovnaGodina);
				narudzba.poslovniPartner = PoslovniPartner.findById(poslovniPartner);

				narudzba.save();
				validation.keep();
				showNarudzbe("add");
			
		}
		}
		
		public static void search(long poslovniPartner, long poslovnaGodina, long otpremnica){
			
			List<Narudzba> narudzba = Narudzba.find("byPoslovniPartner_idAndPoslovnaGodina_idAndOtpremnica_id",
					poslovniPartner, poslovnaGodina, otpremnica).fetch();
			
//			List<Otpremnica> otpremnice = Otpremnica.findAll();
//			List<PoslovnaGodina> poslovneGodine = PoslovnaGodina.findAll();
//			List<PoslovniPartner> poslovniPartneri = PoslovniPartner.findAll();
			
			String mode = "edit";
			renderTemplate("Narudzbe/showNarudzbe.html", narudzba, mode);
		
		}
		
		
		public static void edit( long id,Integer brojNarudzbe, String datumNarudzbe,String sifra, Double kolicina,  
				long preduzece, long poslovnaGodina, long poslovniPartner, long otpremnica){
			
			Narudzba narudzba = Narudzba.findById(id);
			narudzba.brojNarudzbe = brojNarudzbe;
			narudzba.datumNarudzbe = datumNarudzbe;
			narudzba.sifra = sifra;
			narudzba.kolicina = kolicina;
			narudzba.otpremnica = Otpremnica.findById(otpremnica);
			narudzba.poslovnaGodina = PoslovnaGodina.findById(poslovnaGodina);
			narudzba.poslovniPartner = PoslovniPartner.findById(poslovniPartner);
			narudzba.save();
			showNarudzbe("");

							
		}
		
		
		public static void delete(long id){
			Narudzba narudzba = Narudzba.findById(id);
			narudzba.delete();
			showNarudzbe("");

				
		}

		public static void generate(int brojFakture, long poslovnaGodina, long poslovniPartner, long id){

			List<Faktura> fakturaL=Faktura.findAll();
			int n=fakturaL.size()-1;
			Narudzba narudzba =Narudzba.findById(id);
			Faktura fakturaa=fakturaL.get(n);
			
			Faktura faktura=new Faktura();
			faktura.ukupanPDV = 0.0 ;
			faktura.brojFakture=fakturaa.brojFakture+1;
			faktura.datumFakture="";
			faktura.datumValute="";
			faktura.osnovica=0.0;
			faktura.poslovniPartner=narudzba.poslovniPartner;
			faktura.poslovnaGodina=narudzba.poslovnaGodina;
			faktura.iznosZaPlacanje=faktura.osnovica+faktura.ukupanPDV;
			faktura.preduzece=narudzba.poslovniPartner.preduzece;
			faktura.save();
			double PDV=0.0;
			long fId=0;
			double cena=0.0;
			double osnovicaa=0.0;
			for (StavkaNarudzbenice stavkaN : narudzba.stavkaNarudzbenice) {
				StavkeFakture stavkaFakture=new StavkeFakture();
				stavkaFakture.kolicina=stavkaN.kolicina;
				stavkaFakture.jedinicnaCena=stavkaN.robaUsluga.stavkeCenovnika.get(0).jedinicnaCena;
				stavkaFakture.osnovicaZaPDV=stavkaFakture.kolicina*stavkaFakture.jedinicnaCena;
				stavkaFakture.procenatPDV=stavkaN.robaUsluga.grupaRobe.pdv.stopaPdva.get(0).procenat;
				stavkaFakture.iznosPDV=stavkaFakture.osnovicaZaPDV*stavkaFakture.procenatPDV;
				stavkaFakture.iznosStavke=stavkaFakture.osnovicaZaPDV+stavkaFakture.iznosPDV;
				stavkaFakture.robaUsluga=stavkaN.robaUsluga;
				stavkaFakture.faktura=faktura;
				stavkaFakture.rabat=0.0;
				stavkaFakture.save();
				PDV=PDV+stavkaFakture.iznosPDV;
				cena=cena+stavkaFakture.iznosStavke;
				osnovicaa=osnovicaa+stavkaFakture.osnovicaZaPDV;
				fId=stavkaFakture.faktura.getId();
			}
			System.out.println(faktura);
			Faktura faktura2=Faktura.findById(fId);
			faktura2.ukupanPDV=PDV;
			faktura2.iznosZaPlacanje=cena;
			faktura2.osnovica=osnovicaa;
			faktura2.save();
			Fakture.showFakture("");
//			for (StavkeFakture stavkaF : faktura.stavkeFakture) {
//				System.out.println("wrehwhweh");
//				StavkeOtpremnice stavkaO=new StavkeOtpremnice();
//				stavkaO.iznosStavke=stavkaF.iznosStavke;
//				stavkaO.kolicina=stavkaF.kolicina;
//				stavkaO.jedinicnaCena=stavkaF.jedinicnaCena;
//				stavkaO.rabat=stavkaF.rabat;
//				stavkaO.osnovicaZaPDV=stavkaF.osnovicaZaPDV;
//				stavkaO.procenatPDV=stavkaF.procenatPDV;
//				stavkaO.robaUsluga=stavkaF.robaUsluga;
//				stavkaO.iznosPDV=stavkaF.iznosPDV;
//				stavkaO.otpremnica=otpremnica;
//				System.out.println(stavkaO);
//				stavkaO.save();
//				
//			}
			
			
		}	
		

}
