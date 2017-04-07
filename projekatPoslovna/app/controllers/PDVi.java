package controllers;

import java.util.List;

import models.PDV;
import play.mvc.Controller;

public class PDVi extends Controller {
	
	 public static void showPDV(String mode) {
	    	List<PDV> pdvi = PDV.findAll();
	    	if(mode == null || mode.equals(""))
	    		
	    		mode = "edit";
	        render(pdvi, mode);
	    }
	 public static void create(String nazivPDV){
	    	PDV pdv = new PDV(nazivPDV);        
	    	pdv.save();
	    	showPDV("add");

	    }
	 public static void edit(Long id, String nazivPDV){

	    	PDV pdv = PDV.findById(id);
	    	pdv.nazivPDV = nazivPDV;
	    	pdv.save();
	    	showPDV("edit");
	    }
	 public static void delete(Long id){
	    	
		 	PDV pdv = PDV.findById(id);
		 	pdv.delete();
	    	showPDV("");
	    	
	    }

	public static void refresh(Long id, String nazivPDV){
		PDVi.refresh(id, nazivPDV);
		showPDV("");

		}

	public static void filter(String nazivPDV){
		List<PDV>  pdvi = PDV.find("byNazivPDVLike",nazivPDV).fetch();
		
		String mode = "edit";
		renderTemplate("PDVi/showPDV.html", pdvi, mode);
		
		}
	 
}
