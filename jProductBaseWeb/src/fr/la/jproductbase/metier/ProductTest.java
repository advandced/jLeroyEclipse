package fr.la.jproductbase.metier;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import fr.la.configfilereader.ConfigFileReaderException;
import fr.la.jproductbase.service.ServiceInterface;
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
			String operatorCode) throws ConfigFileReaderException, IOException,
			SQLException, TesterReportException {
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

	/**
	 * Recherche du produit &agrave; partir des donn&eacute;es du test produit.
	 * 
	 * @return the product
	 * 
	 * @throws SQLException
	 * @throws IOException
	 * @throws ConfigFileReaderException
	 * @throws TesterReportException
	 */
	public Product getProduct() throws ConfigFileReaderException, IOException,
			SQLException, TesterReportException {
		ServiceInterface _serviceInterface = new ServiceInterface();

		// Retreive possible ProductConf
		List<ProductConf> _productConfs = _serviceInterface
				.getProductConfs(productConfReference);

		// Retreive possible product
		List<Product> _products = _serviceInterface.getProducts(
				productSerialNumber, productDatecode);

		// Retreive product
		Product _preTesterReportProduct = null;
		for (Product _product : _products) {
			ProductConf _productProductConf = _product.getProductConf();

			for (ProductConf _productConf : _productConfs) {
				if (_productProductConf.getIdProductConf() == _productConf
						.getIdProductConf()) {
					if (null == _preTesterReportProduct) {
						_preTesterReportProduct = _product;
					} else {
						// TODO 2 produits avec le même N° de série + datecode
						// ???
					}
				}
			}
		}
		if (null == _preTesterReportProduct) {
			throw new TesterReportException("Produit inconnu.");
		}

		return _preTesterReportProduct;
	}

	/**
	 * Recherche du type de test &agrave; partir des donn&eacute;es du test
	 * produit.
	 * 
	 * @return the testType
	 * 
	 * @throws SQLException
	 * @throws IOException
	 * @throws ConfigFileReaderException
	 * @throws TesterReportException
	 */
	public TestType getTestType() throws ConfigFileReaderException,
			IOException, SQLException, TesterReportException {
		// Retreive testType
		LabviewTestType _labviewTestType = LabviewTestType
				.valueOf(this.labviewTestType);
		ServiceInterface _serviceInterface = new ServiceInterface();
		TestType _testType = _serviceInterface.retreiveTestType(_labviewTestType);
		if (null == _testType) {
			throw new TesterReportException("Type de test inconnu.");
		}

		return _testType;
	}
}
