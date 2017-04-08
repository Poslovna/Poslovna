package controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import models.Cenovnik;
import models.Mesto;
import models.Preduzece;
import models.StavkeCenovnika;
import play.mvc.*;

public class Cenovnici extends Controller {

    public static void showCenovnici(String mode) {
    	List<Preduzece> preduzeca = Preduzece.findAll();
    	List<Cenovnik> cenovnici = Cenovnik.findAll();
    	if(mode == null || mode.equals(""))
    		mode = "edit";
        render(preduzeca, cenovnici, mode);
    }

    
    public static void create(Integer rbrCenovnika, String datmPrimene, long preduzece){
    	Cenovnik cenovnik = new Cenovnik();
    	cenovnik.rbrCenovnika = rbrCenovnika;
    	cenovnik.datmPrimene = datmPrimene;
    	cenovnik.preduzece = Preduzece.findById(preduzece);
    	cenovnik.save();
    	showCenovnici("add");

    }
    public static void edit(Integer rbrCenovnika, String datmPrimene, long preduzece, long id){
    	
    	Cenovnik cenovnik = Cenovnik.findById(id);
    	cenovnik.rbrCenovnika = rbrCenovnika;
    	cenovnik.datmPrimene = datmPrimene;
    	cenovnik.preduzece = Preduzece.findById(preduzece);
    	cenovnik.save();
    	showCenovnici("");
    	
    }
    
    public static void delete(long id){
	
    	Cenovnik cenovnik = Cenovnik.findById(id);
    	cenovnik.delete();
    	showCenovnici("");		
    }
    
    //dodaj pretragu za rbrCenovnika
    public static void filter(Integer rbrCenovnika, String datmPrimene, long preduzece){
    	List cenovnici = Cenovnik.find("byPreduzece_idLikeAndDatmPrimeneLike", "%" + preduzece + "%", "%" + datmPrimene + "%").fetch();
    	
    	String mode = "edit";
    	renderTemplate("Cenovnici/showCenovnici.html", cenovnici, mode);
    }
    
    
    public static void copy( long id, float procenat){
    	System.out.println("ajdeeeeeeeeee");
		Cenovnik cenovnik = Cenovnik.findById(id);
		Cenovnik cenovnik2=new Cenovnik();
		cenovnik2.rbrCenovnika =cenovnik.rbrCenovnika;
		cenovnik2.datmPrimene=cenovnik.datmPrimene;
		cenovnik2.preduzece=cenovnik.preduzece;
		cenovnik2.stavkeCenovnika = new ArrayList<StavkeCenovnika>();
		System.out.println(id);
		System.out.println(procenat);
		System.out.println(cenovnik2);
		System.out.println(cenovnik.stavkeCenovnika.size());
		System.out.println(cenovnik.stavkeCenovnika);

		cenovnik2.save();
		
			
		
		for (StavkeCenovnika  stavka : cenovnik.stavkeCenovnika) {
			StavkeCenovnika stavka2=new StavkeCenovnika();
			stavka2.robaUsluga=stavka.robaUsluga;
			

			System.out.println("------------------");
			System.out.println(stavka);

			System.out.println(stavka2);
			if (procenat>0) {
				stavka2.jedinicnaCena=stavka.jedinicnaCena+(stavka.jedinicnaCena*procenat/100);
				
			}
			else{
				procenat=-procenat;
				stavka2.jedinicnaCena=stavka.jedinicnaCena-(stavka.jedinicnaCena*procenat/100);				
			}
			stavka2.cenovnik=cenovnik2;
			stavka2.save();
			cenovnik2.stavkeCenovnika.add(stavka2);

		}

		cenovnik2.save();
		showCenovnici("");
	}
}
	


