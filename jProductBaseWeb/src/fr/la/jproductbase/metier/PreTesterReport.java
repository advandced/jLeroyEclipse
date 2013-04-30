package fr.la.jproductbase.metier;

import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class PreTesterReport implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<ProductTest> productTests;
	private String result;
	private boolean resultConfirmation;
	private String customerComment;

	/**
	 * Cr&eacute;ation d'un pr&eacute;-rapport de test.
	 */
	public PreTesterReport() {
		this.productTests = new ArrayList<ProductTest>();
	}

	/**
	 * @return the productTests
	 */
	public List<ProductTest> getProductTests() {
		return productTests;
	}

	/**
	 * @param productTests
	 *            the productTests to set
	 */
	public void setProductTests(List<ProductTest> productTests) {
		this.productTests = productTests;
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
	 * @return the resultConfirmation
	 */
	public boolean isResultConfirmation() {
		return resultConfirmation;
	}

	/**
	 * @param resultConfirmation
	 *            the resultConfirmation to set
	 */
	public void setResultConfirmation(boolean resultConfirmation) {
		this.resultConfirmation = resultConfirmation;
	}

	/**
	 * @return the customerComment
	 */
	public String getCustomerComment() {
		return customerComment;
	}

	/**
	 * @param customerComment
	 *            the customerComment to set
	 */
	public void setCustomerComment(String customerComment) {
		this.customerComment = customerComment;
	}

	/**
	 * PUBLIC METHODS
	 */

	/**
	 * Ajout d'un test produit au pr&eacute;-rapport.
	 * 
	 * @param productTest
	 *            Test produit &agrave; ajouter.
	 */
	public void addProductTest(ProductTest productTest) {
		if (null != productTest) {
			this.productTests.add(productTest);
		} else {
			// Don't add null value
		}
	}

	/**
	 * Supprime un test produit au pr&eacute;-rapport.
	 * 
	 * @param productTest
	 *            Test produit &agrave; supprimer.
	 */
	public void removeProductTest(ProductTest productTest) {
		if (null != productTest) {
			this.productTests.remove(productTest);
		} else {
			// Don't add null value
		}
	}

	public static PreTesterReport getPreTesterReport(InputStream fileInputStream) {
		PreTesterReport _preTesterReport = null;

		SAXParserFactory _saxFactory = SAXParserFactory.newInstance();
		SAXParser _saxParser;
		try {
			_saxParser = _saxFactory.newSAXParser();
			PreTesterReportHandler _handler = new PreTesterReportHandler();
			_saxParser.parse(fileInputStream, _handler);

			_preTesterReport = _handler.getPreTesterReport();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		return _preTesterReport;
	}
}
