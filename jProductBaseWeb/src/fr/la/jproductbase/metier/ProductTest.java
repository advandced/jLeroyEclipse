package fr.la.jproductbase.metier;

import java.io.IOException;
import java.sql.SQLException;
import fr.la.configfilereader.ConfigFileReaderException;
import java.io.Serializable;

public class ProductTest implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String productConfReference;
	private String productSerialNumber;
	private String productDatecode;
	private String labviewTestType;
	private boolean inFlow;
	private String operatorCode;

	/**
	 * Cr&eacute;ation d'un test de produit.
	 * 
	 * @param productConfReference
	 *            R&eacute;f&eacute;rence du la configuration produit.
	 * @param productSerialNumber
	 *            Num&eacute;ro de s&eacute;rie du produit.
	 * @param productDatecode
	 *            Datecode du produit.
	 * @param labviewTestType
	 *            Code labview du type de test.
	 * @param inFlow
	 *            Test comptant dans le flux.
	 * @param operatorCode
	 *            Code op&eacute;rateur.
	 * 
	 * @throws ConfigFileReaderException
	 * @throws IOException
	 * @throws SQLException
	 * @throws TesterReportException
	 */
	public ProductTest(String productConfReference, String productSerialNumber,
			String productDatecode, String labviewTestType, boolean inFlow,
			String operatorCode)  {
		// Product
		this.productConfReference = productConfReference;
		this.productSerialNumber = productSerialNumber;
		this.productDatecode = productDatecode;

		// Test type
		this.labviewTestType = labviewTestType;
		this.inFlow = inFlow;

		// Operator
		this.operatorCode = operatorCode;
	}

	/**
	 * Cr&eacute;ation d'un test de produit.
	 */
	public ProductTest() {
		
	}

	/**
	 * @return the productConfReference
	 */
	public String getProductConfReference() {
		return productConfReference;
	}

	/**
	 * @param productConfReference
	 *            the productConfReference to set
	 */
	public void setProductConfReference(String productConfReference) {
		this.productConfReference = productConfReference;
	}

	/**
	 * @return the productSerialNumber
	 */
	public String getProductSerialNumber() {
		return productSerialNumber;
	}

	/**
	 * @param productSerialNumber
	 *            the productSerialNumber to set
	 */
	public void setProductSerialNumber(String productSerialNumber) {
		this.productSerialNumber = ToolsProduct.deleteAhead("0", productSerialNumber);
	}

	/**
	 * @return the productDatecode
	 */
	public String getProductDatecode() {
		return productDatecode;
	}

	/**
	 * @param productDatecode
	 *            the productDatecode to set
	 */
	public void setProductDatecode(String productDatecode) {
		this.productDatecode = productDatecode;
	}

	/**
	 * @return the labviewTestType
	 */
	public String getLabviewTestType() {
		return labviewTestType;
	}

	/**
	 * @param labviewTestType
	 *            the labviewTestType to set
	 */
	public void setLabviewTestType(String labviewTestType) {
		this.labviewTestType = labviewTestType;
	}

	/**
	 * @return the inFlow
	 */
	public boolean isInFlow() {
		return inFlow;
	}

	/**
	 * @param inFlow
	 *            the inFlow to set
	 */
	public void setInFlow(boolean inFlow) {
		this.inFlow = inFlow;
	}

	/**
	 * @return the operatorCode
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


}
