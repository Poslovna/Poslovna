package controllers;

import java.util.List;

import models.GrupaRobe;
import models.Narudzba;
import models.Otpremnica;
import models.RobaUsluga;
import play.mvc.Controller;

public class RobeUsluga extends Controller{

	
	public static void showRobeUsluga(String mode){
		List<RobaUsluga> robeUsluga = RobaUsluga.findAll();
		List<GrupaRobe> grupeRobe = GrupaRobe.findAll();
		List<Otpremnica> otpremnice = Otpremnica.findAll();
		List<Narudzba> narudzbe = Narudzba.findAll();

		if(mode == null || mode.equals(""))
			mode = "edit";
		render(robeUsluga, grupeRobe, otpremnice, narudzbe, mode);
		
		
	}	
	
	public static void create(String nazivRobeUsluge, String jedinicaMere, long grupaRobe, long otpremnica, long narudzba){
		RobaUsluga robaUsluga = new RobaUsluga();
		robaUsluga.nazivRobeUsluge = nazivRobeUsluge;
		robaUsluga.jedinicaMere = jedinicaMere;
		robaUsluga.grupaRobe = GrupaRobe.findById(grupaRobe);
		robaUsluga.otpremnica = Otpremnica.findById(otpremnica);
		robaUsluga.narudzba = Narudzba.findById(narudzba);
		robaUsluga.save();
		showRobeUsluga("add");
	}
	
	public static void edit(long id, String nazivRobeUsluge, String jedinicaMere, long grupaRobe, long otpremnica, long narudzba){
		RobaUsluga robaUsluga = RobaUsluga.findById(id);
		robaUsluga.nazivRobeUsluge = nazivRobeUsluge;
		robaUsluga.jedinicaMere = jedinicaMere;
		robaUsluga.grupaRobe = GrupaRobe.findById(grupaRobe);
		robaUsluga.otpremnica = Otpremnica.findById(otpremnica);
		robaUsluga.narudzba = Narudzba.findById(narudzba);
		robaUsluga.save();
		showRobeUsluga("");
	}
	
	public static void delete(Long id){
		RobaUsluga robaUsluga = RobaUsluga.findById(id);
		robaUsluga.delete();
		showRobeUsluga("");
	}
	
	public static void refresh(Long id, String nazivRobeUsluge){
		RobeUsluga.refresh(id, nazivRobeUsluge);
		showRobeUsluga("");
	}
	
	public static void filter(String nazivRobeUsluge,String jedinicaMere){
		List <RobaUsluga> robeUsluga= RobaUsluga.find("byNazivRobeUslugeLikeAndJedinicaMereLike", "%" + nazivRobeUsluge + "%", "%" + jedinicaMere + "%").fetch();
//		List<GrupaRobe> grupeRobe = GrupaRobe.findAll();
//		List<Otpremnica> otpremnice = Otpremnica.findAll();
//		List<Narudzba> narudzbe = Narudzba.findAll();
		
		String mode = "edit";
		renderTemplate("RobeUsluga/showRobeUsluga.html", robeUsluga,mode);
	}
	
//	public static void filter(String nazivRIU,String jedinicaMere,long grupaRobe){
//		List<RobaIliUsluga> robaIliUsluga = RobaIliUsluga.find("byNazivPDV", nazivRIU).fetch();
//		String mode = "edit";
//		renderTemplate("RobeIliUsluge/show.html", robaIliUsluga, mode);
//	}
	
}
