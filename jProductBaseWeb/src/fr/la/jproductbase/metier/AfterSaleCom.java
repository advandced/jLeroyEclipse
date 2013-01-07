package fr.la.jproductbase.metier;

import fr.la.configfilereader.ConfigFileReaderException;
import fr.la.jproductbase.service.ServiceInterface;
import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Classe m&eacute;tier d'intervention SAV.
 * 
 * @author jhe
 * 
 */
public class AfterSaleCom implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int idAfterSaleCom;
	private String quotationNumber;
	private Date quotationDate;
	private Float savPrice;
	private String quotationComment;
	private int savOrderNumber;
	private Date savOrderDate;
	private String orderComment;
	private Timestamp timestamp;
	private int state;
	private AfterSaleReport afterSaleReport;

	public AfterSaleCom() {

	}

	public AfterSaleCom(int idAfterSaleCom, String quotationNumber,
			Date quotationDate, Float SAVPrince, String quotationComment,
			int SAVOrderNumber, Date SAVOrderData, String OrderComment,
			Timestamp timestamp, int state, AfterSaleReport AfterSaleReport) {
		this.idAfterSaleCom = idAfterSaleCom;
		this.quotationNumber = quotationNumber;
		this.quotationDate = quotationDate;
		this.savPrice = SAVPrince;
		this.quotationComment = quotationComment;
		this.savOrderNumber = SAVOrderNumber;
		this.savOrderDate = SAVOrderData;
		this.orderComment = OrderComment;
		this.timestamp = timestamp;
		this.state = state;
		this.afterSaleReport = AfterSaleReport;
	}

	public AfterSaleCom(int idAfterSaleCom, String quotationNumber,
			Date quotationDate, Float SAVPrince, String quotationComment,
			int SAVOrderNumber, Date SAVOrderData, String OrderComment) {
		this.idAfterSaleCom = idAfterSaleCom;
		this.quotationNumber = quotationNumber;
		this.quotationDate = quotationDate;
		this.savPrice = SAVPrince;
		this.quotationComment = quotationComment;
		this.savOrderNumber = SAVOrderNumber;
		this.savOrderDate = SAVOrderData;
		this.orderComment = OrderComment;
	}

	public AfterSaleCom(int aftersalecom, String numDevis, float montantDevis,
			Date dateDevis, String comm) throws SQLException,
			ConfigFileReaderException, IOException {
		this.idAfterSaleCom = aftersalecom;
		this.quotationNumber = numDevis;
		this.quotationComment = comm;
		this.quotationDate = dateDevis;
		this.savPrice = montantDevis;
	}

	public AfterSaleCom(int aftersalecom, String numDevis, float montantDevis,
			Date dateDevis, String comm, int aftersalereport)
			throws SQLException, ConfigFileReaderException, IOException {
		ServiceInterface moduleGlobal = new ServiceInterface();
		this.afterSaleReport = moduleGlobal.getAfterSaleReport(aftersalereport);
		this.idAfterSaleCom = aftersalecom;
		this.quotationNumber = numDevis;
		this.quotationComment = comm;
		this.quotationDate = dateDevis;
		this.savPrice = montantDevis;
	}

	public AfterSaleCom(int aftersalecom, int numCmd, Date dateCmd)
			throws SQLException, ConfigFileReaderException, IOException {
		this.idAfterSaleCom = aftersalecom;
		this.savOrderNumber = numCmd;
		this.savOrderDate = dateCmd;
	}
	
	public AfterSaleCom(AfterSaleReport _aftersalereport){
		this.afterSaleReport = _aftersalereport;
	}

	public int getIdAfterSaleCom() {
		return idAfterSaleCom;
	}

	public void setIdAfterSaleCom(int idAfterSaleCom) {
		this.idAfterSaleCom = idAfterSaleCom;
	}

	public String getQuotationNumber() {
		return quotationNumber;
	}

	public void setQuotationNumber(String quotationNumber) {
		this.quotationNumber = quotationNumber;
	}

	public Date getQuotationDate() {
		return quotationDate;
	}

	public void setQuotationDate(Date quotationDate) {
		this.quotationDate = quotationDate;
	}

	public Float getSavPrice() {
		return savPrice;
	}

	public void setSavPrice(Float savPrice) {
		this.savPrice = savPrice;
	}

	public String getQuotationComment() {
		return quotationComment;
	}

	public void setQuotationComment(String quotationComment) {
		this.quotationComment = quotationComment;
	}

	public int getSavOrderNumber() {
		return savOrderNumber;
	}

	public void setSavOrderNumber(int sAVOrderNumber) {
		savOrderNumber = sAVOrderNumber;
	}

	public Date getSavOrderDate() {
		return savOrderDate;
	}

	public void setSavOrderDate(Date sAVOrderData) {
		savOrderDate = sAVOrderData;
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

	public AfterSaleReport getAfterSaleReport() {
		return afterSaleReport;
	}

	public void setAfterSaleReport(AfterSaleReport afterSaleReport) {
		this.afterSaleReport = afterSaleReport;
	}

	public String getOrderComment() {
		return orderComment;
	}

	public void setOrderComment(String orderComment) {
		this.orderComment = orderComment;
	}
}