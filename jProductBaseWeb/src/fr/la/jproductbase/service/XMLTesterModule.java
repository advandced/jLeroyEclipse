package fr.la.jproductbase.service;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.jdom.JDOMException;

import fr.la.jproductbase.dao.PreTesterReportDaoXml;
import fr.la.jproductbase.metier.LabviewTestType;

public class XMLTesterModule {
	/**
	 * Sauvegarde un pr&eacute;-rapport dans un fichier XML.
	 * 
	 * @param xmlFileName
	 *            Fichier xml.
	 * @param productConfReference
	 *            R&eacute;f&eacute;rence de la configuration produit.
	 * @param productSerialNumber
	 *            Num&eacute;ro de s&eacute;rie du produit.
	 * @param productDatecode
	 *            Datecode du produit.
	 * @param testTypeLabviewName
	 *            Code labview du type de test.
	 * @param inFlow
	 *            Produit &agrave; consid&eacute;rer dans le flux.
	 * @param operatorCode
	 *            Code op&eacute;rateur.
	 * @param append
	 *            Ajouter les donn&eacute;es au fichier.
	 * 
	 * @throws IOException
	 * @throws FileNotFoundException
	 * @throws JDOMException
	 */
	public void setPreTesterReportXml(String xmlFileName,
			String productConfReference, String productSerialNumber,
			String productDatecode, String testTypeLabviewName, Boolean inFlow,
			String operatorCode, boolean append) throws FileNotFoundException,
			IOException, JDOMException {
		PreTesterReportDaoXml _testerDaoXml = new PreTesterReportDaoXml(
				xmlFileName);
		_testerDaoXml.writePreTesterReport(productConfReference,
				productSerialNumber, productDatecode, testTypeLabviewName,
				inFlow, operatorCode, append);
	}

	/**
	 * Sauvegarde un pr&eacute;-rapport dans un fichier XML.
	 * 
	 * @param xmlFileName
	 *            Fichier xml.
	 * @param productConfReference
	 *            R&eacute;f&eacute;rence de la configuration produit.
	 * @param productSerialNumber
	 *            Num&eacute;ro de s&eacute;rie du produit.
	 * @param productDatecode
	 *            Datecode du produit.
	 * @param testTypeLabviewName
	 *            Code labview du type de test.
	 * @param inFlow
	 *            Produit &agrave; consid&eacute;rer dans le flux.
	 * @param operatorCode
	 *            Code op&eacute;rateur.
	 * 
	 * @throws IOException
	 * @throws FileNotFoundException
	 * @throws JDOMException
	 */
	public void setPreTesterReportXml(String xmlFileName,
			String productConfReference, String productSerialNumber,
			String productDatecode, String testTypeLabviewName, Boolean inFlow,
			String operatorCode) throws FileNotFoundException, IOException,
			JDOMException {
		boolean _append = false;
		LabviewTestType _labviewTestType = LabviewTestType
				.valueOf(testTypeLabviewName);
		if (LabviewTestType.TestDyn == _labviewTestType) {
			// testDyn
			_append = true;
		} else {
			// other test
			_append = false;
		}

		this.setPreTesterReportXml(xmlFileName, productConfReference,
				productSerialNumber, productDatecode, testTypeLabviewName,
				inFlow, operatorCode, _append);
	}

	/**
	 * Met &agrave; jour le r&eacute;sultat du test, sa confirmation et le
	 * commentaire client dans le fichier XML du test produit.
	 * 
	 * @param xmlFileName
	 *            Fichier xml.
	 * @param testResult
	 *            R&eacute;sultat du test.
	 * @param confirmTestResult
	 *            Confirmation du r&eacute;sultat.
	 * @param customerComment
	 *            Commentaire client.
	 * 
	 * @throws IOException
	 * @throws JDOMException
	 */
	public void updatePreTesterReportXml(String xmlFileName, String testResult,
			boolean confirmTestResult, StringBuffer customerComment) {
		PreTesterReportDaoXml _testerDaoXml = new PreTesterReportDaoXml(
				xmlFileName);
		_testerDaoXml.writePreTesterReportResult(testResult, confirmTestResult,
				customerComment);
	}
}
