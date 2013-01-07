package fr.la.jproductbase.metier;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe m&eacute;tier de rapport de testeur.
 * 
 * @author stc
 * 
 */
public class TesterReport {
	private int idTesterReport;
	private Timestamp timestamp;
	private int state;
	private Timestamp date;
	private String testVersion;
	private String result;
	private int ConsoUnomi;
	private int ConsoUmini;
	private int ConsoUmaxi;
	private Tester tester;
	private TestType testType;
	private String operatorCode;
	private Product product;
	private List<Defect> defects = new ArrayList<Defect>();
	private int idTesterReportNext;
	private static SimpleDateFormat dateFormat = new SimpleDateFormat(
			"dd/MM/yyyy");

	/**
	 * Cr&eacute;er un rapport de testeur.
	 * 
	 * @param date
	 *            Date et heure de création.
	 * @param testVersion
	 *            Version du test.
	 * @param result
	 *            R&eacute;sultat du test.
	 * @param tester
	 *            Testeur qui a r&eacute;alis&eacute; les tests.
	 * @param testType
	 *            Type des tests.
	 * @param product
	 *            Produit sur lequel les tests ont &eacute;t&eacute;
	 *            r&eacute;alis&eacute;s.
	 */
	public TesterReport(Timestamp date, String testVersion, String result,
			Tester tester, TestType testType, Product product) {
		this.date = date;
		this.testVersion = testVersion;
		this.result = result;
		this.tester = tester;
		this.testType = testType;
		this.product = product;
	}

	/**
	 * Cr&eacute;er un rapport de testeur.
	 * 
	 * @param state
	 *            Etat de l'enregistrement.
	 * @param date
	 *            Date et heure de création.
	 * @param result
	 *            R&eacute;sultat du test.
	 * @param testType
	 *            Type des tests.
	 * @param operatorCode
	 *            Code de l'op&eacute;rateur qui a r&eacute;alis&eacute; les
	 *            tests.
	 * @param product
	 *            Produit sur lequel les tests ont &eacute;t&eacute;
	 *            r&eacute;alis&eacute;s.
	 */
	public TesterReport(int state, Timestamp date, String result,
			TestType testType, String operatorCode, Product product) {
		this.state = state;
		this.date = date;
		this.result = result;
		this.testType = testType;
		this.operatorCode = operatorCode;
		this.product = product;
	}

	/**
	 * Cr&eacute;er un rapport de testeur.
	 * 
	 * @param state
	 *            Etat de l'enregistrement.
	 * @param date
	 *            Date et heure de création.
	 * @param testVersion
	 *            Version du test.
	 * @param result
	 *            R&eacute;sultat du test.
	 * @param tester
	 *            Testeur qui a r&eacute;alis&eacute; les tests.
	 * @param testType
	 *            Type des tests.
	 * @param operatorCode
	 *            Code de l'op&eacute;rateur qui a r&eacute;alis&eacute; les
	 *            tests.
	 * @param product
	 *            Produit sur lequel les tests ont &eacute;t&eacute;
	 *            r&eacute;alis&eacute;s.
	 */
	public TesterReport(int state, Timestamp date, String testVersion,
			String result, Tester tester, TestType testType,
			String operatorCode, Product product) {
		this(state, date, result, testType, operatorCode, product);
		this.testVersion = testVersion;
		this.tester = tester;
	}

	/**
	 * Cr&eacute;er un rapport de testeur.
	 * 
	 * @param state
	 *            Etat de l'enregistrement.
	 * @param date
	 *            Date et heure de création.
	 * @param testVersion
	 *            Version du test.
	 * @param result
	 *            R&eacute;sultat du test.
	 * @param tester
	 *            Testeur qui a r&eacute;alis&eacute; les tests.
	 * @param testType
	 *            Type des tests.
	 * @param operatorCode
	 *            Code de l'op&eacute;rateur qui a r&eacute;alis&eacute; les
	 *            tests.
	 * @param product
	 *            Produit sur lequel les tests ont &eacute;t&eacute;
	 *            r&eacute;alis&eacute;s.
	 * @param defects
	 *            D&eacutefauts.
	 */
	public TesterReport(int state, Timestamp date, String testVersion,
			String result, int consoUmini, int consoUnomi, int consoUmaxi,
			Tester tester, TestType testType, String operatorCode,
			Product product, List<Defect> defects) {
		this(state, date, testVersion, result, tester, testType, operatorCode,
				product);
		this.ConsoUmini = consoUmini;
		this.ConsoUnomi = consoUnomi;
		this.ConsoUmaxi = consoUmaxi;
		this.defects = defects;
	}

	/**
	 * Cr&eacute;er un rapport de testeur.
	 * 
	 * @param idTesterReport
	 *            Identifiant du rapport de test.
	 * @param timestamp
	 *            Horadatage de l'enregistrement.
	 * @param state
	 *            Etat de l'enregistrement.
	 * @param date
	 *            Date et heure de création.
	 * @param testVersion
	 *            Version du test.
	 * @param result
	 *            R&eacute;sultat du test.
	 * @param tester
	 *            Testeur qui a r&eacute;alis&eacute; les tests.
	 * @param testType
	 *            Type des tests.
	 * @param operatorCode
	 *            Code de l'op&eacute;rateur qui a r&eacute;alis&eacute; les
	 *            tests.
	 * @param product
	 *            Produit sur lequel les tests ont &eacute;t&eacute;
	 *            r&eacute;alis&eacute;s.
	 * @param idTesterReportNext
	 *            Identifiant du rapport de test suivant.
	 */
	public TesterReport(int idTesterReport, Timestamp timestamp, int state,
			Timestamp date, String testVersion, String result, Tester tester,
			TestType testType, String operatorCode, Product product,
			int idTesterReportNext) {
		this(state, date, testVersion, result, tester, testType, operatorCode,
				product);
		this.idTesterReport = idTesterReport;
		this.timestamp = timestamp;
		this.state = state;
		this.idTesterReportNext = idTesterReportNext;
	}

	/**
	 * GETTERS AND SETTERS
	 */

	/**
	 * @return the idTesterReport
	 */
	public int getIdTesterReport() {
		return idTesterReport;
	}

	/**
	 * @param idTesterReport
	 *            the idTesterReport to set
	 */
	public void setIdTesterReport(int idTesterReport) {
		this.idTesterReport = idTesterReport;
	}

	/**
	 * @return the timestamp
	 */
	public Timestamp getTimestamp() {
		return timestamp;
	}

	/**
	 * @param timestamp
	 *            the timestamp to set
	 */
	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	/**
	 * @return the state
	 */
	public int getState() {
		return state;
	}

	/**
	 * @param state
	 *            the state to set
	 */
	public void setState(int state) {
		this.state = state;
	}

	/**
	 * @return the date
	 */
	public Timestamp getDate() {
		return date;
	}

	/**
	 * @param date
	 *            the date to set
	 */
	public void setDate(Timestamp date) {
		this.date = date;
	}

	/**
	 * @return the testVersion
	 */
	public String getTestVersion() {
		return testVersion;
	}

	/**
	 * @param testVersion
	 *            the testVersion to set
	 */
	public void setTestVersion(String testVersion) {
		this.testVersion = testVersion;
	}

	/**
	 * @return the result
	 */
	public String getResult() {
		return result;
	}

	/**
	 * @param result
	 *            the result to set
	 */
	public void setResult(String result) {
		this.result = result;
	}

	/**
	 * @return the consoUnomi
	 */
	public int getConsoUnomi() {
		return ConsoUnomi;
	}

	/**
	 * @param consoUnomi
	 *            the consoUnomi to set
	 */
	public void setConsoUnomi(int consoUnomi) {
		ConsoUnomi = consoUnomi;
	}

	/**
	 * @return the consoUmini
	 */
	public int getConsoUmini() {
		return ConsoUmini;
	}

	/**
	 * @param consoUmini
	 *            the consoUmini to set
	 */
	public void setConsoUmini(int consoUmini) {
		ConsoUmini = consoUmini;
	}

	/**
	 * @return the consoUmaxi
	 */
	public int getConsoUmaxi() {
		return ConsoUmaxi;
	}

	/**
	 * @param consoUmaxi
	 *            the consoUmaxi to set
	 */
	public void setConsoUmaxi(int consoUmaxi) {
		ConsoUmaxi = consoUmaxi;
	}

	/**
	 * @return the tester
	 */
	public Tester getTester() {
		return tester;
	}

	/**
	 * @param tester
	 *            the tester to set
	 */
	public void setTester(Tester tester) {
		this.tester = tester;
	}

	/**
	 * @return the testType
	 */
	public TestType getTestType() {
		return testType;
	}

	/**
	 * @param testType
	 *            the testType to set
	 */
	public void setTestType(TestType testType) {
		this.testType = testType;
	}

	/**
	 * @return the operator
	 */
	public String getOperatorCode() {
		return operatorCode;
	}

	/**
	 * @param operatorCode
	 *            the operatorCode to set
	 */
	public void setOperatorCode(String operatorCode) {
		this.operatorCode = operatorCode;
	}

	/**
	 * @return the product
	 */
	public Product getProduct() {
		return product;
	}

	/**
	 * @param product
	 *            the product to set
	 */
	public void setProduct(Product product) {
		this.product = product;
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
	 * @return the idTesterReportNext
	 */
	public int getIdTesterReportNext() {
		return idTesterReportNext;
	}

	/**
	 * @param idTesterReportNext
	 *            the idTesterReportNext to set
	 */
	public void setIdTesterReportNext(int idTesterReportNext) {
		this.idTesterReportNext = idTesterReportNext;
	}

	/**
	 * @return the dateFormat
	 */
	public static SimpleDateFormat getDateFormat() {
		return dateFormat;
	}

	/**
	 * PUBLIC METHODS
	 */

	/**
	 * Ajout d'un d&eacute;faut au rapport.
	 * 
	 * @param defect
	 *            D&eacute;faut &agrave; ajouter.
	 */
	public void addDefect(Defect defect) {
		if (null != defect) {
			this.defects.add(defect);
		} else {
			// Don't add null value
		}
	}

	/**
	 * Supprime un d&eacute;faut du produit
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
}
