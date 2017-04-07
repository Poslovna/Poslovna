package controllers;

import java.util.Date;
import java.util.List;

import models.IstorijaPoreza;
import models.PDV;
import models.Porez;
import models.Preduzece;
import models.StopaPDVa;
import play.data.validation.*;
import play.data.validation.Error;
import play.mvc.Controller;

public class StopePDV extends Controller {
	public static void show(String mode){
		List<StopaPDVa>stopePDVa=StopaPDVa.findAll();
		List<PDV>pdvi=PDV.findAll();
		if (mode == null || mode.equals(""))
			mode = "edit";
		render(stopePDVa,pdvi,mode);
	}
	public static void create(double procenat,String datumVazenja,long pdv){
		StopaPDVa stopaPDV = new StopaPDVa();
		stopaPDV.procenat = procenat;
		stopaPDV.datumVazenja = datumVazenja;
		stopaPDV.pdv = PDV.findById(pdv);
		stopaPDV.save();
		show("add");
	}
	public static void edit(long id,String datumVazenja,double procenat,long pdv){
		StopaPDVa stopaPDVa = StopaPDVa.findById(id);
		stopaPDVa.datumVazenja=datumVazenja;
		stopaPDVa.procenat=procenat;
		stopaPDVa.pdv=PDV.findById(pdv);
		stopaPDVa.save();
		show("");
	}	
	public static void delete(Long id){
		StopaPDVa stopaPDVa = StopaPDVa.findById(id);
		stopaPDVa.delete();
		show("");
	}
	public static void filter( double procenat,String datumVazenja){
		List<StopaPDVa> stopePDVa = StopaPDVa.find("byProcenatLikeAndDatumVazenjaLike",procenat,"%" + datumVazenja + "%").fetch();
		
		
		String mode = "edit";
		renderTemplate("StopePDV/show.html", stopePDVa, mode);
	}
	
}
