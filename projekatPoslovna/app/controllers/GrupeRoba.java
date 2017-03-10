package controllers;

import java.util.Date;
import java.util.List;

import models.GrupaRobe;
import models.Mesto;
import models.Otpremnica;
import models.PDV;
import models.PoslovnaGodina;
import models.PoslovniPartner;
import models.Preduzece;
import play.mvc.*;

public class GrupeRoba extends Controller {

    public static void showGrupeRoba(String mode) {
    	List<Preduzece> preduzece = Preduzece.findAll();
    	List<PDV> pdv= PDV.findAll();
    	List<GrupaRobe> grupaRobe = GrupaRobe.findAll();
    	if(mode == null || mode.equals(""))
    		mode = "edit";
        render(preduzece, pdv, grupaRobe, mode);
    }
    public static void create(String nazivGrupe, long id)
    {
    	
    	GrupaRobe grupaRobe = new GrupaRobe();
		
    	grupaRobe.nazivGrupe = nazivGrupe;
    	grupaRobe.pdv = PDV.findById(id);
    	grupaRobe.preduzece = Preduzece.findById(id);
    	
    	
    	
    	System.out.println(id);
    	grupaRobe.save();
    	showGrupeRoba("add");
    }
    
    public static void edit(String nazivGrupe, long id){
    	
    	GrupaRobe grupaRobe = GrupaRobe.findById(id);
    	grupaRobe.nazivGrupe= nazivGrupe;
    	grupaRobe.pdv = PDV.findById(id);
    	grupaRobe.preduzece= Preduzece.findById(id);
    	
    	grupaRobe.save();
    	showGrupeRoba("");
    }
    
    public static void delete(long id){
    	GrupaRobe grupaRobe = GrupaRobe.findById(id);
    	grupaRobe.delete();
		showGrupeRoba("");



    }
    
}
