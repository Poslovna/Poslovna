package controllers;

import java.util.Date;
import java.util.List;
import models.Mesto;
import models.Otpremnica;
import models.PoslovnaGodina;
import models.PoslovniPartner;
import models.Preduzece;
import play.mvc.*;

public class Otpremnice extends Controller {

    public static void showOtpremnice(String mode) {
    	List<Otpremnica> otpremnica = Otpremnica.findAll();
    	List<PoslovniPartner> poslovniPartner = PoslovniPartner.findAll();
    	List<PoslovnaGodina> poslovnaGodina= PoslovnaGodina.findAll();
    	
    	if(mode == null || mode.equals(""))
    		mode = "edit";
        render(otpremnica,poslovniPartner, poslovnaGodina,  mode);
    }
    public static void create(Integer brojOtpremnice,Double osnovica, Double ukupanPDV, Double iznosZaPlacanje,
    		long poslovnaGodina, long poslovniPartner, long id)
    {
    	
    	Otpremnica otpremnica = new Otpremnica();
		
    	otpremnica.brojOtpremnice= brojOtpremnice;
    	otpremnica.osnovica = osnovica;
    	otpremnica.ukupanPDV = ukupanPDV;
    	otpremnica.iznosZaPlacanje = iznosZaPlacanje;

    	
    	
    	otpremnica.poslovniPartner = PoslovniPartner.findById(poslovniPartner);
    	otpremnica.poslovnaGodina = PoslovnaGodina.findById(poslovnaGodina);
    	System.out.println(id);
    	otpremnica.save();
    	showOtpremnice("add");
    }
    
    public static void edit(Integer brojOtpremnice,Double osnovica, Double ukupanPDV, Double iznosZaPlacanje,
    		long poslovnaGodina, long poslovniPartner, long id){
    	
    	Otpremnica otpremnica = Otpremnica.findById(id);
    	otpremnica.brojOtpremnice = brojOtpremnice;
    	otpremnica.osnovica = osnovica;
    	otpremnica.ukupanPDV = ukupanPDV;
    	otpremnica.iznosZaPlacanje = iznosZaPlacanje;
    	
    	otpremnica.poslovnaGodina = PoslovnaGodina.findById(poslovnaGodina);
    	otpremnica.poslovniPartner = PoslovniPartner.findById(poslovniPartner);
    	otpremnica.save();
    	showOtpremnice("");
    }
    
    public static void delete(Long id){
		Otpremnica otpremnica= Otpremnica.findById(id);
		otpremnica.delete();
		showOtpremnice("");

    }
    
//    public static void filter(String datumIzdavanjaRacuna, long poslovnaGodina,long poslovniPartneri){
//		List<Otpremnica> otpremnice = Otpremnica.find("byDatumIzdavanjaRacunaAndPoslovnaGodina_idAndPosloniPartneri_id", datumIzdavanjaRacuna, poslovnaGodina, poslovniPartneri).fetch();
//		String mode = "edit";
//		renderTemplate("Otpremnice/show.html", otpremnice, mode);
//	}
    
}
