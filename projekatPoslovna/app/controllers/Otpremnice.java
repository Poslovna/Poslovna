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
    	List<PoslovniPartner> poslovniPartner = PoslovniPartner.findAll();
    	List<PoslovnaGodina> poslovnaGodina= PoslovnaGodina.findAll();
    	List<Otpremnica> otpremnica = Otpremnica.findAll();
    	if(mode == null || mode.equals(""))
    		mode = "edit";
        render(poslovniPartner, poslovnaGodina, otpremnica, mode);
    }
    public static void create(Integer brojOtpremnice, 
    		String mestoIzdavanjaRacuna, 
    		Date datumIzdavanjaRacuna, 
    		String nacinPlacanja, String rokPlacanja, long id)
    {
    	
    	Otpremnica otpremnica = new Otpremnica();
		
    	otpremnica.brojOtpremnice= brojOtpremnice;
    	otpremnica.mestoIzdavanjaRacuna= mestoIzdavanjaRacuna;
    	otpremnica.datumIzdavanjaRacuna= datumIzdavanjaRacuna;
    	otpremnica.nacinPlacanja= nacinPlacanja;
    	otpremnica.rokPlacanja= rokPlacanja;
    	
    	
    	otpremnica.poslovniPartner = PoslovniPartner.findById(id);
    	otpremnica.poslovnaGodina = PoslovnaGodina.findById(id);
    	System.out.println(id);
    	otpremnica.save();
    	showOtpremnice("add");
    }
    
    public static void edit(Integer brojOtpremnice, 
    		String mestoIzdavanjaRacuna, 
    		Date datumIzdavanjaRacuna, 
    		String nacinPlacanja, String rokPlacanja,
    		long id){
    	
    	Otpremnica otpremnica = Otpremnica.findById(id);
    	otpremnica.brojOtpremnice= brojOtpremnice;
    	otpremnica.mestoIzdavanjaRacuna= mestoIzdavanjaRacuna;
    	otpremnica.datumIzdavanjaRacuna= datumIzdavanjaRacuna;
    	otpremnica.nacinPlacanja= nacinPlacanja;
    	otpremnica.rokPlacanja= rokPlacanja;
    	otpremnica.poslovnaGodina= PoslovnaGodina.findById(id);
    	otpremnica.poslovniPartner = PoslovniPartner.findById(id);
    	otpremnica.save();
    	showOtpremnice("");
    }
    
    public static void delete(long id){
		Otpremnica otpremnica= Otpremnica.findById(id);
		otpremnica.delete();
		showOtpremnice("");



    }
    
}
