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
    	List<GrupaRobe> grupeRoba = GrupaRobe.findAll();
    	List<Preduzece> preduzeca = Preduzece.findAll();
    	List<PDV> pdvi= PDV.findAll();

    	if(mode == null || mode.equals(""))
    		mode = "edit";
        render(grupeRoba,preduzeca, pdvi, mode);
    }
    public static void create(String nazivGrupe, long pdv,long preduzece)
    {
    	
    	GrupaRobe grupaRobe = new GrupaRobe();
		
    	grupaRobe.nazivGrupe = nazivGrupe;
    	grupaRobe.pdv = PDV.findById(pdv);
    	grupaRobe.preduzece = Preduzece.findById(preduzece);
    	grupaRobe.save();
    	showGrupeRoba("add");
    }
    
    public static void edit(long id,String nazivGrupe, long pdv,long preduzece){
    	
    	GrupaRobe grupaRobe = GrupaRobe.findById(id);
    	grupaRobe.nazivGrupe= nazivGrupe;
    	grupaRobe.pdv = PDV.findById(pdv);
    	grupaRobe.preduzece= Preduzece.findById(preduzece);   	
    	grupaRobe.save();
    	showGrupeRoba("");
    }
    
    public static void delete(long id){
    	GrupaRobe grupaRobe = GrupaRobe.findById(id);
    	grupaRobe.delete();
		showGrupeRoba("");



    }
    
}
