package fr.la.jproductbase.metier;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe m&eacute;tier de rapport de d&eacute;fauts.
 * 
 * @author stc
 * 
 */
public class ProductionFailureReport implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int idProductionFailureReport;
	private Timestamp timestamp;
	private int state; // 1: Open, 2: Closed
	private Date registrationDate;
	private Date repairDate;
	private Date dispatchDate;
	private int actionTime;
	private boolean functionalTest;
	private boolean visualControl;
	private String failureCode;
	private Product product;
	private TesterReport testerReport;
	private List<Failure> failures = new ArrayList<Failure>();
	private CustomerComment customerComment;
	private FailureReportComment failureReportComment;
	private static SimpleDateFormat dateFormat = new SimpleDateFormat(
			"dd/MM/yyyy");

	/**
	 * Cr&eacute;er un rapport de d&eacute;fauts.
	 * 
	 * @param idProductionFailureReport
	 *            Identifiant du rapport de d&eacute;faut.
	 * @param timestamp
	 *            Horodatage de l'enregistement.
	 * @param state
	 *            Etat de l'enregistrement.
	 * @param registrationDate
	 *            Date d'enregistrement.
	 * @param failureCode
	 *            Code d&eacute;faut du rapport.
	 * @param product
	 *            Produit concernn&eacute; par le rapport.
	 * @param testerReport
	 *            Rapport de test qui a g&eacute;n&eacute;r&eacute; ce rapport.
	 */
	public ProductionFailureReport(int idProductionFailureReport, Timestamp timestamp, int state,
			Date registrationDate, String failureCode, Product product,
			TesterReport testerReport) {
		this.idProductionFailureReport = idProductionFailureReport;
		this.state = 1;
		this.timestamp = timestamp;
		this.state = state;
		this.registrationDate = registrationDate;
		this.failureCode = failureCode;
		this.product = product;
		this.testerReport = testerReport;
	}

	/**
	 * Cr&eacute;er un rapport de d&eacute;fauts.
	 * 
	 * @param idProductionFailureReport
	 *            Identifiant du rapport de d&eacute;faut.
	 * @param timestamp
	 *            Horodatage de l'enregistement.
	 * @param state
	 *            Etat de l'enregistrement.
	 * @param registrationDate
	 *            Date d'enregistrement.
	 * @param failureCode
	 *            Code d&eacute;faut du rapport.
	 * @param product
	 *            Produit concernn&eacute; par le rapport.
	 * @param testerReport
	 *            Rapport de test qui a g&eacute;n&eacute;r&eacute; ce rapport.
	 * @param failures
	 *            D&eacute;fauts li&eacute;s au rapport.
	 */
	public ProductionFailureReport(int idProductionFailureReport, Timestamp timestamp, int state,
			Date registrationDate, String failureCode, Product product,
			TesterReport testerReport, List<Failure> failures) {
		this(idProductionFailureReport, timestamp, state, registrationDate, failureCode,
				product, testerReport);
		this.failures = failures;
	}
	
	
	public ProductionFailureReport(){}

	/**
	 * GETTERS AND SETTERS
	 */

	/**
	 * @return the idProductionFailureReport
	 */
	public int getIdProductionFailureReport() {
		return idProductionFailureReport;
	}

	/**
	 * @param idProductionFailureReport
	 *            the idProductionFailureReport to set
	 */
	public void setIdProductionFailureReport(int idProductionFailureReport) {
		this.idProductionFailureReport = idProductionFailureReport;
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
	 * @return the registrationDate
	 */
	public Date getRegistrationDate() {
		return registrationDate;
	}

	/**
	 * @param registrationDate
	 *            the registrationDate to set
	 */
	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}

	/**
	 * @return the repairDate
	 */
	public Date getRepairDate() {
		return repairDate;
	}

	/**
	 * @param repairDate
	 *            the repairDate to set
	 */
	public void setRepairDate(Date repairDate) {
		this.repairDate = repairDate;
	}

	/**
	 * @return the dispatchDate
	 */
	public Date getDispatchDate() {
		return dispatchDate;
	}

	/**
	 * @param dispatchDate
	 *            the dispatchDate to set
	 */
	public void setDispatchDate(Date dispatchDate) {
		this.dispatchDate = dispatchDate;
	}

	/**
	 * @return the actionTime
	 */
	public int getActionTime() {
		return actionTime;
	}

	/**
	 * @param actionTime
	 *            the actionTime to set
	 */
	public void setActionTime(int actionTime) {
		this.actionTime = actionTime;
	}

	/**
	 * @return the functionalTest
	 */
	public boolean isFunctionalTest() {
		return functionalTest;
	}

	/**
	 * @param functionalTest
	 *            the functionalTest to set
	 */
	public void setFunctionalTest(boolean functionalTest) {
		this.functionalTest = functionalTest;
	}

	/**
	 * @return the visualControl
	 */
	public boolean isVisualControl() {
		return visualControl;
	}

	/**
	 * @param visualControl
	 *            the visualControl to set
	 */
	public void setVisualControl(boolean visualControl) {
		this.visualControl = visualControl;
	}

	/**
	 * @return the failureCode
	 */
	public String getFailureCode() {
		return failureCode;
	}

	/**
	 * @param failureCode
	 *            the failureCode to set
	 */
	public void setFailureCode(String failureCode) {
		this.failureCode = failureCode;
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
	 * @return the testerReport
	 */
	public TesterReport getTesterReport() {
		return testerReport;
	}

	/**
	 * @param testerReport
	 *            the testerReport to set
	 */
	public void setTesterReport(TesterReport testerReport) {
		this.testerReport = testerReport;
	}

	/**
	 * @return the failures
	 */
	public List<Failure> getFailures() {
		return failures;
	}

	/**
	 * @param failures
	 *            the failures to set
	 */
	public void setFailures(List<Failure> failures) {
		this.failures = failures;
	}

	/**
	 * @return the customerComment
	 */
	public CustomerComment getCustomerComment() {
		return customerComment;
	}

	/**
	 * @param customerComment
	 *            the customerComment to set
	 */
	public void setCustomerComment(CustomerComment customerComment) {
		this.customerComment = customerComment;
	}

	/**
	 * @return the failureReportComment
	 */
	public FailureReportComment getFailureReportComment() {
		return failureReportComment;
	}

	/**
	 * @param failureReportComment
	 *            the failureReportComment to set
	 */
	public void setFailureReportComment(
			FailureReportComment failureReportComment) {
		this.failureReportComment = failureReportComment;
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
	 * Ajout d'un d&eacute;faut au rapport de d&eacute;fauts.
	 * 
	 * @param failure
	 *            D&eacute;faut &agrave; ajouter.
	 */
	public void addFailure(Failure failure) {
		if (null != failure) {
			this.failures.add(failure);
		} else {
			// Don't add null value
		}
	}

	/**
	 * Supprime un d&eacute;faut du rapport de d&eacute;fauts.
	 * 
	 * @param failure
	 *            D&eacute;faut &agrave; supprimer.
	 */
	public void removeFailure(Failure failure) {
		if (null != failure) {
			this.failures.remove(failure);
		} else {
			// Don't add null value
		}
	}

	@Override
	public String toString() {
		return "ProductionFailureReport [idProductionFailureReport="
				+ idProductionFailureReport + ", timestamp=" + timestamp
				+ ", state=" + state + ", registrationDate=" + registrationDate
				+ ", repairDate=" + repairDate + ", dispatchDate="
				+ dispatchDate + ", actionTime=" + actionTime
				+ ", functionalTest=" + functionalTest + ", visualControl="
				+ visualControl + ", failureCode=" + failureCode + ", product="
				+ product + ", testerReport=" + testerReport + ", failures="
				+ failures + ", customerComment=" + customerComment
				+ ", failureReportComment=" + failureReportComment + "]";
	}
	
	
}
