package fr.la.jproductbase.metier;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.SAXException;

public class LabviewTesterReport implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String fileExtension;
	private String typeProduit;
	private String date;
	private String heure;
	private String versionTest;
	private String typeTest;
	private String stationId;
	private String serialNumber;
	private String refMaterielle;
	private String resultat;
	private String macAdresse;
	private String ConsoUnomi;
	private String ConsoUmini;
	private String ConsoUmaxi;
	private List<Defect> defects;

	/**
	 * Cr&eacute;er un rapport de testeur labview.
	 * 
	 * @param typeProduit
	 *            Type de produit.
	 * @param date
	 *            Date du rapport.
	 * @param heure
	 *            Heure du rapport.
	 * @param versionTest
	 *            Version du test.
	 * @param typeTest
	 *            Type de test.
	 * @param stationId
	 *            Testeur.
	 * @param serialNumber
	 *            Num&eacute;ro de s&eacute;rie du produit.
	 * @param refMaterielle
	 *            R&eacute;f&eacute;rence mat&eacute;rielle.
	 * @param resultat
	 *            R&eacute;sultat du test.
	 * @param macAdresse
	 *            Adresse mac.
	 */
	public LabviewTesterReport(String typeProduit, String date, String heure,
			String versionTest, String typeTest, String stationId,
			String serialNumber, String refMaterielle, String resultat,
			String macAdresse) {
		this.typeProduit = typeProduit;
		this.date = date;
		this.heure = heure;
		this.versionTest = versionTest;
		this.typeTest = typeTest;
		this.stationId = stationId;
		this.serialNumber = serialNumber;
		this.refMaterielle = refMaterielle;
		this.resultat = resultat;
		this.macAdresse = macAdresse;
	}

	/**
	 * Cr&eacute;er un rapport de testeur labview.
	 */
	public LabviewTesterReport() {
		this.defects = new ArrayList<Defect>();
	}

	/**
	 * @return the fileExtension
	 */
	public String getFileExtension() {
		return fileExtension;
	}

	/**
	 * @param fileExtension
	 *            the fileExtension to set
	 */
	public void setFileExtension(String fileExtension) {
		this.fileExtension = fileExtension;
	}

	/**
	 * @return the typeProduit
	 */
	public String getTypeProduit() {
		return typeProduit;
	}

	/**
	 * @param typeProduit
	 *            the typeProduit to set
	 */
	public void setTypeProduit(String typeProduit) {
		this.typeProduit = typeProduit;
	}

	/**
	 * @return the date
	 */
	public String getDate() {
		return date;
	}

	/**
	 * @param date
	 *            the date to set
	 */
	public void setDate(String date) {
		this.date = date;
	}

	/**
	 * @return the heure
	 */
	public String getHeure() {
		return heure;
	}

	/**
	 * @param heure
	 *            the heure to set
	 */
	public void setHeure(String heure) {
		this.heure = heure;
	}

	/**
	 * @return the versionTest
	 */
	public String getVersionTest() {
		return versionTest;
	}

	/**
	 * @param versionTest
	 *            the versionTest to set
	 */
	public void setVersionTest(String versionTest) {
		this.versionTest = versionTest;
	}

	/**
	 * @return the typeTest
	 */
	public String getTypeTest() {
		return typeTest;
	}

	/**
	 * @param typeTest
	 *            the typeTest to set
	 */
	public void setTypeTest(String typeTest) {
		this.typeTest = typeTest;
	}

	/**
	 * @return the stationId
	 */
	public String getStationId() {
		return stationId;
	}

	/**
	 * @param stationId
	 *            the stationId to set
	 */
	public void setStationId(String stationId) {
		this.stationId = stationId;
	}

	/**
	 * @return the serialNumber
	 */
	public String getSerialNumber() {
		return serialNumber;
	}

	/**
	 * @param serialNumber
	 *            the serialNumber to set
	 */
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	/**
	 * @return the refMaterielle
	 */
	public String getRefMaterielle() {
		return refMaterielle;
	}

	/**
	 * @param refMaterielle
	 *            the refMaterielle to set
	 */
	public void setRefMaterielle(String refMaterielle) {
		this.refMaterielle = refMaterielle;
	}

	/**
	 * @return the resultat
	 */
	public String getResultat() {
		return resultat;
	}

	/**
	 * @param resultat
	 *            the resultat to set
	 */
	public void setResultat(String resultat) {
		this.resultat = resultat;
	}

	/**
	 * @return the macAdresse
	 */
	public String getMacAdresse() {
		return macAdresse;
	}

	/**
	 * @param macAdresse
	 *            the macAdresse to set
	 */
	public void setMacAdresse(String macAdresse) {
		this.macAdresse = macAdresse;
	}

	/**
	 * @return the defects
	 */
	public List<Defect> getDefects() {
		return defects;
	}

	/**
	 * @param defects
	 *            the defects to set
	 */
	public void setDefects(List<Defect> defects) {
		this.defects = defects;
	}

	/**
	 * @return the consoUnomi
	 */
	public String getConsoUnomi() {
		return ConsoUnomi;
	}

	/**
	 * @param consoUnomi
	 *            the consoUnomi to set
	 */
	public void setConsoUnomi(String consoUnomi) {
		ConsoUnomi = consoUnomi;
	}

	/**
	 * @return the consoUmini
	 */
	public String getConsoUmini() {
		return ConsoUmini;
	}

	/**
	 * @param consoUmini
	 *            the consoUmini to set
	 */
	public void setConsoUmini(String consoUmini) {
		ConsoUmini = consoUmini;
	}

	/**
	 * @return the consoUmaxi
	 */
	public String getConsoUmaxi() {
		return ConsoUmaxi;
	}

	/**
	 * @param consoUmaxi
	 *            the consoUmaxi to set
	 */
	public void setConsoUmaxi(String consoUmaxi) {
		ConsoUmaxi = consoUmaxi;
	}

	/**
	 * PUBLIC METHODS
	 */

	/**
	 * Ajout d'un d&eacute;faut au rapport.
	 * 
	 * @param defect
	 *            Td&eacute;faut &agrave; ajouter.
	 */
	public void addDefect(Defect defect) {
		if (null != defect) {
			this.defects.add(defect);
		} else {
			// Don't add null value
		}
	}

	/**
	 * Supprime un d&eacute;faut au rapport.
	 * 
	 * @param defect
	 *            D&eacute;faut &agrave; supprimer.
	 */
	public void removeDefect(Defect defect) {
		if (null != defect) {
			this.defects.remove(defect);
		} else {
			// Don't add null value
		}
	}

	/**
	 * Lecture d'un rapport de d&eacute;fauts XML Labview.
	 * 
	 * @param xmlInputStream
	 *            Flux d'entr&eacute;e XML du rapport de d&eacute;fauts Labview.
	 * 
	 * @return Rapport de d&eacute;fauts Labview.
	 * 
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 */
	public static LabviewTesterReport getLabviewXmlTesterReport(
			InputStream xmlInputStream) throws ParserConfigurationException,
			SAXException, IOException {
		LabviewTesterReport _labviewTesterReport = null;
		_labviewTesterReport = LabviewTesterReport
				.getXmlLabviewTesterReport(xmlInputStream);
		return _labviewTesterReport;
	}

	/**
	 * Lecture du rapport de d&eacute;fauts Labview.
	 * 
	 * @param labviewTesterReportFileName
	 *            Fichier du rapport de d&eacute;fauts Labview.
	 * 
	 * @return Rapport de d&eacute;fauts Labview.
	 * 
	 * @throws IOException 
	 * @throws SAXException 
	 * @throws ParserConfigurationException 
	 */
	public static LabviewTesterReport getLabviewTesterReport(
			String labviewTesterReportFileName) throws ParserConfigurationException, SAXException, IOException {
		LabviewTesterReport _labviewTesterReport = null;

		// labviewTesterReportFileName extension
		int _beginIndex = labviewTesterReportFileName.lastIndexOf(".");
		if (-1 != _beginIndex) {
			try {
				String _labviewTesterReportFileExtension = labviewTesterReportFileName
						.substring(_beginIndex).toLowerCase();
				if (_labviewTesterReportFileExtension.equals(".xml")) {
					// Standard labview tester report
					InputStream _xmlInputStream = new FileInputStream(
							labviewTesterReportFileName);
					_labviewTesterReport = LabviewTesterReport
							.getXmlLabviewTesterReport(_xmlInputStream);
				} else {
					if (_labviewTesterReportFileExtension.equals(".zip")) {
						// Labview dynamic tester report
						_labviewTesterReport = LabviewTesterReport
								.getZipLabviewTesterReport(labviewTesterReportFileName);
					} else {
						// Unknown file extension
					}
				}

				if (null != _labviewTesterReport) {
					_labviewTesterReport
							.setFileExtension(_labviewTesterReportFileExtension);
				}
			} catch (IndexOutOfBoundsException e) {
				// No file extension
			}
		} else {
			// Unknown file extension
		}

		return _labviewTesterReport;
	}

	private static LabviewTesterReport getXmlLabviewTesterReport(
			InputStream inputStream) throws ParserConfigurationException,
			SAXException, IOException {
		LabviewTesterReport _labviewTesterReport = null;

		SAXParserFactory _saxFactory = SAXParserFactory.newInstance();
		SAXParser _saxParser = _saxFactory.newSAXParser();

		LabviewTesterReportHandler _handler = new LabviewTesterReportHandler();
		_saxParser.parse(inputStream, _handler);

		_labviewTesterReport = _handler.getLabviewTesterReport();

		return _labviewTesterReport;
	}

	private static LabviewTesterReport getZipLabviewTesterReport(
			String labviewTesterReportFileName) {
		LabviewTesterReport _labviewTesterReport = null;

		if (-1 != labviewTesterReportFileName.lastIndexOf("Pass")) {
			_labviewTesterReport = new LabviewTesterReport();
			_labviewTesterReport.setResultat("Passed");
		} else {
			if (-1 != labviewTesterReportFileName.lastIndexOf("Fail")) {
				_labviewTesterReport = new LabviewTesterReport();
				_labviewTesterReport.setResultat("Failed");
			} else {
				_labviewTesterReport = null;
			}
		}

		return _labviewTesterReport;
	}
}
