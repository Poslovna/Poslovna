package controllers;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;





import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

import org.hibernate.cfg.Environment;

import com.mysql.jdbc.Connection;








import models.Faktura;
import models.PoslovnaGodina;
import models.PoslovniPartner;
import models.Preduzece;
import play.data.validation.Error;
import play.data.validation.Required;
import play.mvc.Controller;

public class Izvestaji extends Controller {
	public static void show(String mode){
		List<Faktura>fakture=Faktura.findAll();
		if (mode == null || mode.equals(""))
			mode = "edit";
		render(fakture, mode);
	}
	
	public static void report1(@Required Date datumPocetka,
			@Required Date datumZavrsetka){
			try {

			Map params = new HashMap(1);
			params.put("datumPocetka", datumPocetka);
			params.put("datumZavrsetka", datumZavrsetka);
			JasperPrint jp = JasperFillManager.fillReport( "C://Users/sneza/git/Poslovna/Poslovna/projekatPoslovna/lib/kif.jasper",params,DriverManager.getConnection("jdbc:jtds:sqlserver://SNEZA-PC/projekat", "sa", "123") );
			JasperViewer.viewReport(jp, false);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			show("");
			
	}	
	public static void report2(@Required long id){
		try {

			Map params = new HashMap(1);
			params.put("id", id);
			JasperPrint jp = JasperFillManager.fillReport("C://Users/sneza/git/Poslovna/Poslovna/projekatPoslovna/lib/Faktura.jasper",params,DriverManager.getConnection("jdbc:jtds:sqlserver://SNEZA-PC/projekat", "sa", "123") );
			JasperViewer.viewReport(jp, false);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			show("");
	}	
	
	 
		

	
}
