package fr.la.jasperreport;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import fr.la.jproductbase.dao.ConnectionProduct;

public class JasperReport {
	
	ConnectionProduct cnxProduct = new ConnectionProduct();
	
	public void generatefordetailintervention(int id) {
		try{	   
			String relativeWebPath = "/WEB-INF/jasper/fiche_suiveuse.jrxml";
			ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
			String absoluteDiskPath = servletContext.getRealPath(relativeWebPath);
			System.out.println(absoluteDiskPath);
			InputStream input = new FileInputStream(new File(absoluteDiskPath));
			JasperDesign jasperDesign = JRXmlLoader.load(input);
			System.out.println("Compiling Report Designs");
			net.sf.jasperreports.engine.JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
			System.out.println("Creating JasperPrint Object");
			Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("id", id);
			System.out.println("Filling Report to File");
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, this.cnxProduct.getCnx());
			JasperViewer.viewReport(jasperPrint);
			//Exporting the report
			//OutputStream output = new FileOutputStream(new File("C:/catalog1.pdf"));
			//JasperExportManager.exportReportToPdfStream(jasperPrint, output);
			System.out.println("Report Generation Complete");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (JRException e) {
			e.printStackTrace();
		}
	}
}