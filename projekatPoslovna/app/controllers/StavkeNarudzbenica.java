package controllers;

import java.util.List;

import models.Narudzba;
import models.RobaUsluga;
import models.StavkaNarudzbenice;
import models.StavkeOtpremnice;
import play.data.validation.Required;
import play.mvc.Controller;

public class StavkeNarudzbenica extends Controller{

	public static void showStavkeNarudzbenica(String mode){
		List<StavkaNarudzbenice>stavkeNarudzbenica=StavkaNarudzbenice.findAll();
		List<RobaUsluga>robeUsluga=RobaUsluga.findAll();
		List<Narudzba>narudzbe=Narudzba.findAll();

		if (mode == null || mode.equals(""))
			mode = "edit";
		render(stavkeNarudzbenica,robeUsluga,narudzbe, mode);
	}
	public static void add(@Required String jedinicaMere,float kolicina,long narudzba,@Required long robaUsluga, long faktura) {
		if(validation.hasErrors()) {
			for(play.data.validation.Error error : validation.errors()) {
			System.out.println(error.message());
			}
			validation.keep();
			showStavkeNarudzbenica("add");
		}else {
			
			StavkaNarudzbenice stavkaNarudzbenice=new StavkaNarudzbenice();
			Narudzba narudzbaa=Narudzba.findById(narudzba);
			stavkaNarudzbenice.kolicina=kolicina;
			stavkaNarudzbenice.jedinicaMere=jedinicaMere;
			stavkaNarudzbenice.robaUsluga=RobaUsluga.findById(robaUsluga);
			stavkaNarudzbenice.narudzba=narudzbaa;
			stavkaNarudzbenice.save();
			narudzbaa.save();
			validation.keep();


			showStavkeNarudzbenica("add");
	}
	}
	public static void filter(long robaIliUsluga, long narudzbenica, float kolicina,String jedinicaMere){
		List<StavkaNarudzbenice> stavkeNarudzbenice = StavkaNarudzbenice.find("byRobaIliUsluga_idAndNarudzbenica_idAndKolicinaAndJedinicaMereLike", robaIliUsluga,narudzbenica,kolicina, jedinicaMere).fetch();
		String mode = "edit";
		renderTemplate("StavkeNarudzbenice/show.html", stavkeNarudzbenice, mode);
	}
	public static void edit(long robaUsluga, long narudzba, float kolicina,String jedinicaMere,long id){
		StavkaNarudzbenice stavkaNarudzbenice = StavkaNarudzbenice.findById(id);
		Narudzba narudzbaa=Narudzba.findById(narudzba);
		stavkaNarudzbenice.kolicina = kolicina;
		stavkaNarudzbenice.jedinicaMere = jedinicaMere;
		stavkaNarudzbenice.narudzba = narudzbaa;
		stavkaNarudzbenice.robaUsluga = RobaUsluga.findById(robaUsluga);

		stavkaNarudzbenice.save();
		narudzbaa.save();
		showStavkeNarudzbenica("");
	}	
	public static void delete(Long id){
		StavkaNarudzbenice stavkaNarudzbenice = StavkaNarudzbenice.findById(id);
		stavkaNarudzbenice.delete();
		showStavkeNarudzbenica("");
	}

public static void stavke(long id){
		
		List<StavkaNarudzbenice> stavkeNarudzbenica = StavkaNarudzbenice.find("byNarudzba_id", id).fetch();
		String mode = "edit";
		System.out.println(stavkeNarudzbenica);
		renderTemplate("StavkeNarudzbenica/showStavkeNarudzbenica.html", stavkeNarudzbenica, mode);
	}
	
}
