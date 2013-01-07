package fr.la.jproductbase.metier;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Classe m&eacute;tier d'intervention SAV.
 * 
 * @author rmo
 * 
 */
public class AfterSaleReport implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int idAfterSaleReport;
	private Timestamp timestamp;
	private int state;
	private String majorIndexIn;
	private String majorIndexOut;
	private Date arrivalDate;
	private String ecsNumber;
	private String ncNature;
	private Date firstAnalyseDate;
	private Date materialInfoDate;
	private Date reparationDate;
	private Date qualityControlDate;
	private Date expeditionDate;
	private int functionnalTest; //0 : ko ; 1 : ok ; 2 : non connu
	private int visualControl; //0 : ko ; 1 : ok ; 2 : non connu
	private String asker;
	private String intervenant;
	private String interventionSheetLink;
	private String comment;
	private String refCustomer;
	private List<Failure> failures = new ArrayList<Failure>();
	private ApparentCause apparentCause;
	private Product product;

	/**
	 * Cr&eacute;er un rapport d'intervention SAV.
	 * 
	 * @param idApparentCause
	 *            Identifiant de la cause probable.
	 * @param timestamp
	 *            Horadatage de l'enregistrement.
	 * @param state
	 *            Etat de l'enregistrement.
	 * @param idAfterSaleReport
	 * @param timestamp
	 * @param state
	 * @param arrivalDate
	 * @param ecsNumber
	 * @param ncNature
	 * @param firstAnalyseDate
	 * @param materialInfoDate
	 * @param reparationDate
	 * @param qualityControlDate
	 * @param expeditionDate
	 * @param functionnalTest
	 * @param visualControl
	 * @param asker
	 * @param intervenant
	 * @param interventionSheetLink
	 * @param comment
	 * @param failures
	 *            D&eacute;fauts li&eacute;s au rapport.
	 * @param apparentCause
	 * 
	 * @param product
	 *            Produit concernn&eacute; par le rapport.
	 */
	public AfterSaleReport(int idAfterSaleReport, Timestamp timestamp,
			int state, Date arrivalDate, String ecsNumber, String ncNature,
			Date firstAnalyseDate, Date materialInfoDate, Date reparationDate,
			Date qualityControlDate, Date expeditionDate,
			int functionnalTest, int visualControl, String asker,
			String intervenant, String interventionSheetLink, String comment,
			List<Failure> failures, ApparentCause apparentCause, Product product) {
		this.idAfterSaleReport = idAfterSaleReport;
		this.timestamp = timestamp;
		this.state = state;
		this.arrivalDate = arrivalDate;
		this.ecsNumber = ecsNumber;
		this.ncNature = ncNature;
		this.firstAnalyseDate = firstAnalyseDate;
		this.materialInfoDate = materialInfoDate;
		this.reparationDate = reparationDate;
		this.qualityControlDate = qualityControlDate;
		this.expeditionDate = expeditionDate;
		this.functionnalTest = functionnalTest;
		this.visualControl = visualControl;
		this.asker = asker;
		this.intervenant = intervenant;
		this.interventionSheetLink = interventionSheetLink;
		this.comment = comment;
		this.failures = failures;
		this.apparentCause = apparentCause;
		this.product = product;
	}
	
	//Constructeur servant pour l'ajout du qualityControlDate
	
	public AfterSaleReport(int idAfterSaleReport, Date qualityControlDate){
		this.idAfterSaleReport = idAfterSaleReport;
		this.qualityControlDate = qualityControlDate;
	}
	
	public AfterSaleReport(int idafterSAleReport, Product product){
		this.idAfterSaleReport = idafterSAleReport;
		this.product = product;
	}
	
	public AfterSaleReport(){}
	
	public void addFailure(Failure failure) {
		if (null != failure) {
			if (!(null != failures)) {failures = new ArrayList<Failure>();}
			this.failures.add(failure);
		} else {
			// Don't add null value
		}
	}

	/**
	 * GETTERS AND SETTERS
	 */

	public int getIdAfterSaleReport() {
		return idAfterSaleReport;
	}

	public void setIdAfterSaleReport(int idAfterSaleReport) {
		this.idAfterSaleReport = idAfterSaleReport;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}
	
	public String getMajorIndexIn() {
		return majorIndexIn;
	}

	public void setMajorIndexIn(String majorIndexIn) {
		this.majorIndexIn = majorIndexIn;
	}
	
	public String getMajorIndexOut() {
		return majorIndexOut;
	}

	public void setMajorIndexOut(String majorIndexOut) {
		this.majorIndexOut = majorIndexOut;
	}

	public Date getArrivalDate() {
		return arrivalDate;
	}

	public void setArrivalDate(Date arrivalDate) {
		this.arrivalDate = arrivalDate;
	}

	public String getEcsNumber() {
		return ecsNumber;
	}

	public void setEcsNumber(String ecsNumber) {
		this.ecsNumber = ecsNumber;
	}

	public String getNcNature() {
		return ncNature;
	}

	public void setNcNature(String ncNature) {
		this.ncNature = ncNature;
	}

	public Date getFirstAnalyseDate() {
		return firstAnalyseDate;
	}

	public void setFirstAnalyseDate(Date firstAnalyseDate) {
		this.firstAnalyseDate = firstAnalyseDate;
	}

	public Date getMaterialInfoDate() {
		return materialInfoDate;
	}

	public void setMaterialInfoDate(Date materialInfoDate) {
		this.materialInfoDate = materialInfoDate;
	}

	public Date getReparationDate() {
		return reparationDate;
	}

	public void setReparationDate(Date reparationDate) {
		this.reparationDate = reparationDate;
	}

	public Date getQualityControlDate() {
		return qualityControlDate;
	}

	public void setQualityControlDate(Date qualityControlDate) {
		this.qualityControlDate = qualityControlDate;
	}

	public Date getExpeditionDate() {
		return expeditionDate;
	}

	public void setExpeditionDate(Date expeditionDate) {
		this.expeditionDate = expeditionDate;
	}

	public int getFunctionnalTest() {
		return functionnalTest;
	}

	public void setFunctionnalTest(int functionnalTest) {
		this.functionnalTest = functionnalTest;
	}

	public int getVisualControl() {
		return visualControl;
	}

	public void setVisualControl(int visualControl) {
		this.visualControl = visualControl;
	}

	public String getAsker() {
		return asker;
	}

	public void setAsker(String asker) {
		this.asker = asker;
	}

	public String getIntervenant() {
		return intervenant;
	}

	public void setIntervenant(String intervenant) {
		this.intervenant = intervenant;
	}

	public String getInterventionSheetLink() {
		return interventionSheetLink;
	}

	public void setInterventionSheetLink(String interventionSheetLink) {
		this.interventionSheetLink = interventionSheetLink;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	
	public String getRefCustomer() {
		return refCustomer;
	}

	public void setRefCustomer(String refCustomer) {
		this.refCustomer = refCustomer;
	}

	public List<Failure> getFailures() {
		return failures;
	}

	public void setFailures(List<Failure> failures) {
		this.failures = failures;
	}

	public ApparentCause getApparentCause() {
		return apparentCause;
	}

	public void setApparentCause(ApparentCause apparentCause) {
		this.apparentCause = apparentCause;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@Override
	public String toString() {
		return "AfterSaleReport [idAfterSaleReport=" + idAfterSaleReport
				+ ", timestamp=" + timestamp + ", state=" + state
				+ ", majorIndexIn=" + majorIndexIn + ", majorIndexOut="
				+ majorIndexOut + ", arrivalDate=" + arrivalDate
				+ ", ecsNumber=" + ecsNumber + ", ncNature=" + ncNature
				+ ", firstAnalyseDate=" + firstAnalyseDate
				+ ", materialInfoDate=" + materialInfoDate
				+ ", reparationDate=" + reparationDate
				+ ", qualityControlDate=" + qualityControlDate
				+ ", expeditionDate=" + expeditionDate + ", functionnalTest="
				+ functionnalTest + ", visualControl=" + visualControl
				+ ", asker=" + asker + ", intervenant=" + intervenant
				+ ", interventionSheetLink=" + interventionSheetLink
				+ ", comment=" + comment + ", refCustomer=" + refCustomer
				+ ", failures=" + failures + ", apparentCause=" + apparentCause
				+ ", product=" + product + "]";
	}

	
	
	
}
