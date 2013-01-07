package fr.la.jproductbase.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

import javax.swing.JOptionPane;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

public class PrintReport implements Runnable {
	private String report;
	private Connection cnx;
	private Map<String, Object> parameters;
	
	public PrintReport(String report, Connection cnx) {
		this.cnx = cnx;
		this.report = report;
	}
	
	public PrintReport(String report, Connection cnx, Map<String, Object> parameters) {
		this(report, cnx);
		this.parameters = parameters;
	}
	
	@Override
	public void run() {
        try {
    		// Chargement et compilation du rapport
            JasperDesign jasperDesign = JRXmlLoader.load(this.report);
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);

            // Execution du rapport
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, this.parameters, this.cnx);

            // Création du rapport au format PDF
            JasperExportManager.exportReportToPdfFile(jasperPrint, "jasperReport.pdf");
            
            // Impression
            JasperPrintManager.printReport(jasperPrint, true);
            
            // Close connection
            this.cnx.close();
            
    		JOptionPane.showMessageDialog(null, "Impression terminée.", "Information", JOptionPane.INFORMATION_MESSAGE);
        } catch (JRException e) {
    		JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
    		e.printStackTrace();
		} catch (SQLException e) {
    		JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}
}
