package fr.la.jproductbase.service;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.naming.NamingException;

import fr.la.configfilereader.ConfigFileReaderException;
import fr.la.jproductbase.dao.ConnectionOperator;
import fr.la.jproductbase.dao.ConnectionProduct;
import fr.la.jproductbase.dao.ConnectionTester;
import fr.la.jproductbase.dao.CustomerCommentDao;
import fr.la.jproductbase.dao.CustomerCommentDaoException;
import fr.la.jproductbase.dao.CustomerCommentDaoImpl;
import fr.la.jproductbase.dao.ElementChangedDao;
import fr.la.jproductbase.dao.ElementChangedDaoException;
import fr.la.jproductbase.dao.ElementChangedDaoImpl;
import fr.la.jproductbase.dao.FailureDao;
import fr.la.jproductbase.dao.FailureDaoException;
import fr.la.jproductbase.dao.FailureDaoImpl;
import fr.la.jproductbase.dao.FailureReportCommentDao;
import fr.la.jproductbase.dao.FailureReportCommentDaoException;
import fr.la.jproductbase.dao.FailureReportCommentDaoImpl;
import fr.la.jproductbase.dao.ProductDaoException;
import fr.la.jproductbase.dao.ProductionFailureReportDao;
import fr.la.jproductbase.dao.ProductionFailureReportDaoException;
import fr.la.jproductbase.dao.ProductionFailureReportDaoImpl;
import fr.la.jproductbase.dao.TesterReportDaoException;
import fr.la.jproductbase.metier.AfterSaleReport;
import fr.la.jproductbase.metier.CustomerComment;
import fr.la.jproductbase.metier.ElementChanged;
import fr.la.jproductbase.metier.Failure;
import fr.la.jproductbase.metier.FailureReportComment;
import fr.la.jproductbase.metier.JProductBaseException;
import fr.la.jproductbase.metier.Product;
import fr.la.jproductbase.metier.ProductConf;
import fr.la.jproductbase.metier.ProductionFailureReport;
import fr.la.jproductbase.metier.TestType;
import fr.la.jproductbase.metier.Tester;
import fr.la.jproductbase.metier.TesterReport;

public class FailureModule {
	private ConnectionProduct cnxProduct;
	private ConnectionOperator cnxOperator;
	private ConnectionTester cnxTester;

	protected FailureModule(ConnectionProduct cnxProduct,
			ConnectionOperator cnxOperator, ConnectionTester cnxTester) {
		this.cnxProduct = cnxProduct;
		this.cnxOperator = cnxOperator;
		this.cnxTester = cnxTester;
	}

	protected List<ProductionFailureReport> getProductionFailureReports()
			throws SQLException, ConfigFileReaderException, IOException {
		ProductionFailureReportDao _failureReportDao = new ProductionFailureReportDaoImpl(
				this.cnxProduct, this.cnxOperator, this.cnxTester);

		List<ProductionFailureReport> _failureReports = _failureReportDao
				.listProductionFailureReport();

		return _failureReports;
	}

	protected List<ProductionFailureReport> getProductionFailureReports(
			Product product) throws SQLException, ConfigFileReaderException,
			IOException {
		ProductionFailureReportDao _failureReportDao = new ProductionFailureReportDaoImpl(
				this.cnxProduct, this.cnxOperator, this.cnxTester);

		List<ProductionFailureReport> _failureReports = _failureReportDao
				.listProductionFailureReport(product);

		return _failureReports;
	}

	protected List<ProductionFailureReport> getProductionFailureReports(
			Date fromDate, Date toDate) throws SQLException,
			ConfigFileReaderException, IOException {
		ProductionFailureReportDao _failureReportDao = new ProductionFailureReportDaoImpl(
				this.cnxProduct, this.cnxOperator, this.cnxTester);

		java.sql.Date _fromDate = new java.sql.Date(fromDate.getTime());
		java.sql.Date _toDate = new java.sql.Date(toDate.getTime());

		List<ProductionFailureReport> _failureReports = _failureReportDao
				.listProductionFailureReport(_fromDate, _toDate);

		return _failureReports;
	}

	protected ProductionFailureReport getProductionFailureReport(
			TesterReport testerReport) throws SQLException {
		ProductionFailureReportDao _failureReportDao = new ProductionFailureReportDaoImpl(
				this.cnxProduct, this.cnxOperator, this.cnxTester);

		ProductionFailureReport _failureReport = _failureReportDao
				.getProductionFailureReport(testerReport);

		return _failureReport;
	}

	protected List<ProductionFailureReport> getUnclosedProductionFailureReports()
			throws SQLException, ConfigFileReaderException, IOException {
		ProductionFailureReportDao _failureReportDao = new ProductionFailureReportDaoImpl(
				this.cnxProduct, this.cnxOperator, this.cnxTester);

		List<ProductionFailureReport> _failureReports = _failureReportDao
				.unclosedProductionFailureReport();

		return _failureReports;
	}

	protected List<String> getFaces() {
		List<String> _faces = new ArrayList<String>();
		_faces.add("Composant");
		_faces.add("Soudure");

		return _faces;
	}

	protected List<String> getCompTypes() {
		List<String> _compTypes = new ArrayList<String>();
		_compTypes.add("Traditionnel");
		_compTypes.add("CMS");

		return _compTypes;
	}

	protected Failure addFailureDismantedCard(
			ProductionFailureReport productionFailureReport, Failure failure) {
		Failure _failure = null;
		productionFailureReport.getFailures().add(failure);
		System.out.println("list failure add dimsntle"
				+ productionFailureReport);
		try {
			_failure = this.updateFailures(productionFailureReport);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FailureDaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ElementChangedDaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return _failure;
	}

	protected List<Failure> getFailures(
			ProductionFailureReport productionFailureReport)
			throws SQLException {
		FailureDao _failureDao = new FailureDaoImpl(this.cnxProduct,
				this.cnxOperator);

		List<Failure> _failures = _failureDao
				.getFailures(productionFailureReport);

		return _failures;
	}

	protected List<Failure> getFailures(AfterSaleReport afterSaleReport)
			throws SQLException {
		FailureDao _failureDao = new FailureDaoImpl(this.cnxProduct,
				this.cnxOperator);

		List<Failure> _failures = _failureDao.getFailures(afterSaleReport);

		return _failures;
	}

	/*
	 * Suppression d'un failure dans la bdd
	 */
	protected void removeFailure(Failure failure) throws SQLException {
		FailureDao _failureDao = new FailureDaoImpl(this.cnxProduct,
				this.cnxOperator);

		_failureDao.removeFailure(failure);
	}

	protected List<ElementChanged> getElementChanged(Failure failure)
			throws SQLException {
		ElementChangedDao _elementChangedDao = new ElementChangedDaoImpl(
				this.cnxProduct);

		List<ElementChanged> _elementsChanged = _elementChangedDao
				.getElementsChanged(failure);

		return _elementsChanged;
	}

	protected CustomerComment getCustomerComment(
			ProductionFailureReport productionFailureReport)
			throws SQLException {
		CustomerCommentDao _customerCommentDao = new CustomerCommentDaoImpl(
				this.cnxProduct);

		CustomerComment _customerComment = _customerCommentDao
				.getCustomerComment(productionFailureReport);

		return _customerComment;
	}

	protected FailureReportComment getFailureReportComment(
			ProductionFailureReport productionFailureReport)
			throws SQLException {
		FailureReportCommentDao _failureReportCommentDao = new FailureReportCommentDaoImpl(
				this.cnxProduct);

		FailureReportComment _failureReportComment = _failureReportCommentDao
				.getFailureReportComment(productionFailureReport);

		return _failureReportComment;
	}

	protected ProductionFailureReport getProductionFailureReport(
			int idProductionFailureReport) throws SQLException,
			ConfigFileReaderException, IOException {
		ProductionFailureReportDao _failureReportDao = new ProductionFailureReportDaoImpl(
				this.cnxProduct, this.cnxOperator, this.cnxTester);

		ProductionFailureReport _failureReport = _failureReportDao
				.getProductionFailureReport(idProductionFailureReport);

		return _failureReport;
	}

	protected ProductionFailureReport setProductionFailureReport(
			ProductionFailureReport productionFailureReport,
			Date failureReportDate, String productConfReference,
			String productConfMajorIndex, String productMinorIndex,
			String serialNumber, String datecode, String testTypeName,
			String testerName, String operatorCode, String customerComment,
			String failureCode, String comment, List<Failure> failures)
			throws SQLException, JProductBaseException, ProductDaoException,
			FailureModuleException, ConfigFileReaderException, IOException,
			ParseException, TesterReportDaoException, NamingException {
		ProductionFailureReport _productionFailureReport = productionFailureReport;
System.out.println("valeur de failures " + failures);
		try {
			// Start transaction
			this.cnxProduct.getCnx().setAutoCommit(false);
			this.cnxOperator.getCnx().setAutoCommit(false);
			this.cnxTester.getCnx().setAutoCommit(false);

			// Retrieve product
			ProductConfModule _productConfModule = new ProductConfModule(
					this.cnxProduct);
			ProductConf _productConf = _productConfModule.getProductConf(
					productConfReference, productConfMajorIndex,
					productMinorIndex);
			ProductModule _productModule = new ProductModule(this.cnxProduct);
			Product _product = _productModule.getProduct(_productConf,
					serialNumber, datecode);
			System.out.println("on recherche le produit" + _product);
			if (null == _product) {
				System.out.println("le produit n'existe pas on l'enregistre");
				_product = _productModule.addProduct(_productConf,
						serialNumber, datecode);

			} else {
				// Nothing to do
			}

			ServiceInterface _serviceInterface = new ServiceInterface();

			// Retrieve testType
			TestType _testType = _serviceInterface.getTestType(testTypeName);
			// Retrieve tester
			Tester _tester = _serviceInterface.getTester(testerName);
			// Retrieve testerReport
			TesterReport _testerReport = null;
			if (null == _productionFailureReport) {
				// New failure report

				// Add testerReport
				_testerReport = _serviceInterface.addTesterReport(_testType,
						_tester, operatorCode, _product);
			} else {
				// Existing failure report

				// Retreive testerReport
				_testerReport = productionFailureReport.getTesterReport();
			}

			// Update failures
			// List<Failure> _failures = new ArrayList<Failure>();
			System.out.println("update " + _product + " / " + failures);
			this.updateFailures(_product, failures);
			System.out.println("apres update failure" + failures);

			_productionFailureReport = this.setProductionFailureReport(
					_productionFailureReport, failureReportDate, _product,
					_testType, _testerReport, operatorCode, customerComment,
					failureCode, comment, failures);

			// Commit
			this.cnxTester.getCnx().commit();
			this.cnxOperator.getCnx().commit();
			this.cnxProduct.getCnx().commit();
		} catch (Exception e) {
			this.cnxTester.getCnx().rollback();
			this.cnxOperator.getCnx().rollback();
			this.cnxProduct.getCnx().rollback();
			e.printStackTrace();
			throw new FailureModuleException(e.getMessage());
		}

		return _productionFailureReport;
	}

	protected ProductionFailureReport updateProductionFailureReport(
			ProductionFailureReport productionFailureReport)
			throws SQLException, JProductBaseException, ProductDaoException,
			FailureModuleException, ConfigFileReaderException, IOException,
			ParseException, TesterReportDaoException, NamingException {
		System.out.println("test update");
		ProductionFailureReport _productionFailureReport = productionFailureReport;
		try {
			System.out.println("update productionFailureReport"
					+ _productionFailureReport
					+ " , "
					+ _productionFailureReport.getRegistrationDate()
					+ ",  "
					+ _productionFailureReport.getProduct()
					+ ", "
					+ _productionFailureReport.getTesterReport().getTestType()
					+ ", "
					+ _productionFailureReport.getTesterReport()
					+ " , "
					+ _productionFailureReport.getTesterReport()
							.getOperatorCode()
					+ " , "
					+ _productionFailureReport.getCustomerComment()
							.getComment()
					+ " , "
					+ _productionFailureReport.getFailureCode()
					+ " , "
					+ _productionFailureReport.getFailureReportComment()
							.getComment() + " , "
					+ _productionFailureReport.getFailures());

			_productionFailureReport = this.setProductionFailureReport(
					_productionFailureReport, _productionFailureReport
							.getRegistrationDate(), _productionFailureReport
							.getProduct(), _productionFailureReport
							.getTesterReport().getTestType(),
					_productionFailureReport.getTesterReport(),
					_productionFailureReport.getTesterReport()
							.getOperatorCode(), _productionFailureReport
							.getCustomerComment().getComment(),
					_productionFailureReport.getFailureCode(),
					_productionFailureReport.getFailureReportComment()
							.getComment(), _productionFailureReport
							.getFailures());

			// Commit

			this.cnxTester.getCnx().commit();
			this.cnxOperator.getCnx().commit();
			this.cnxProduct.getCnx().commit();
		} catch (Exception e) {
			// this.cnxTester.getCnx().rollback();
			// this.cnxOperator.getCnx().rollback();
			// this.cnxProduct.getCnx().rollback();
			e.printStackTrace();
			throw new FailureModuleException(e.getMessage());
		}

		return _productionFailureReport;
	}

	/*
	 * Enregistre un rapport de d&eacute;faut dans la base de donn&eacute;s.
	 * 
	 * @param failureReport Rapport de d&eacute;faut.
	 * 
	 * @param failureReportDate Date du rapport de d&eacute;faut.
	 * 
	 * @param product Produit.
	 * 
	 * @param testType Type de test.
	 * 
	 * @param testerReport Testeur.
	 * 
	 * @param operatorCode Code op&eacute;rateur.
	 * 
	 * @param customerComment Description du d&eacute;faut.
	 * 
	 * @param failureCode Code d&eacute;faut.
	 * 
	 * @param comment Commentaire.
	 * 
	 * @param failures
	 * 
	 * @return FailureReport Rapport de d&eacute;faut.
	 * 
	 * @throws SQLException
	 * 
	 * @throws FailureModuleException
	 */
	private ProductionFailureReport setProductionFailureReport(
			ProductionFailureReport productionFailureReport,
			Date failureReportDate, Product product, TestType testType,
			TesterReport testerReport, String operatorCode,
			String customerComment, String failureCode, String comment,
			List<Failure> failures) throws SQLException,
			FailureModuleException, ProductionFailureReportDaoException,
			FailureDaoException, ElementChangedDaoException,
			FailureReportCommentDaoException, CustomerCommentDaoException,
			ConfigFileReaderException, IOException, NamingException {
		ProductionFailureReport _productionFailureReport = productionFailureReport;
		if (null == _productionFailureReport) {
			// New production failure report

			// Add productionFailureReport
			_productionFailureReport = this.addProductionFailureReport(
					failureReportDate, product, testerReport, customerComment,
					comment, failureCode, failures);
		} else {
			// Existing failure report
			System.out.println("update production failure report "
					+ productionFailureReport);
			
			// Update productionFailureReport
			this.updateProductionFailureReport(productionFailureReport,
					failureReportDate, product, customerComment, comment,
					failureCode, failures);
		}

		return _productionFailureReport;
	}

	protected ProductionFailureReport setProductionFailureReport(
			TesterReport testerReport, String customerComment)
			throws SQLException, FailureModuleException,
			ConfigFileReaderException, IOException, NamingException {
		ProductionFailureReport _productionFailureReport = null;

		try {
			// Start transaction
			this.cnxProduct.getCnx().setAutoCommit(false);
			this.cnxOperator.getCnx().setAutoCommit(false);
			this.cnxTester.getCnx().setAutoCommit(false);

			this.setProductionFailureReport(null, testerReport.getDate(),
					testerReport.getProduct(), testerReport.getTestType(),
					testerReport, testerReport.getOperatorCode(),
					customerComment, "", "", null);

			// Commit
			this.cnxTester.getCnx().commit();
			this.cnxOperator.getCnx().commit();
			this.cnxProduct.getCnx().commit();
		} catch (Exception e) {
			this.cnxTester.getCnx().rollback();
			this.cnxOperator.getCnx().rollback();
			this.cnxProduct.getCnx().rollback();
			e.printStackTrace();
			throw new FailureModuleException(e.getMessage());
		}

		return _productionFailureReport;
	}

	/*
	 * Création de la liste des failures qui seront attachées à un failureReport
	 * à partir d'un tableau
	 * 
	 * @param product Produit concerné
	 * 
	 * @param failures Tableau des failures à transformer en liste de failure
	 */
	protected List<Failure> updateFailures(Product product, List<Failure> failures)
			throws ConfigFileReaderException, IOException, SQLException,
			ParseException, JProductBaseException, ProductDaoException,
			NamingException {
		//List<Failure> _failures = new ArrayList<Failure>();
		Failure _failureCardChanged = null;
		//OperatorModule _operatorModule = new OperatorModule(this.cnxOperator);

		//Operator _operator;
		System.out.println("update Failures "+ product + " / Failure" + failures);
		ProductModule _productModule = new ProductModule(this.cnxProduct);
		//Product _productComponent;

		for (Failure failure : failures) {

			if(failure.getIdFailure()==0){
			// Update product component link
			if (true == failure.isDismantleCard()) {
				// Dismantle product component
				System.out.println("carte remplacee" + product + " / "
						+ failure.getProduct().getSerialNumber());
				_productModule.removeProductComponent(product,
						failure.getProduct());
				_failureCardChanged = failure;
			} else {
				System.out.println("failure traitement" + failure);
				if (failure.getNewFailureCardChanged() != null) {
					if (_failureCardChanged.getIdFailure() == failure
							.getNewFailureCardChanged().getIdFailure()) {
						System.out.println("nouvelle failure " + failure.getProduct().getSerialNumber());
						_productModule.addProductComponent(product,
								failure.getProduct());
					}
				}
			}
			}
			else {
				
			}
			// _failures.add(_failure);
		}

		return failures;
	}

	/*
	 * Ajout d'un failureReport dans la bdd
	 */
	private ProductionFailureReport addProductionFailureReport(
			Date registrationDate, Product product, TesterReport testerReport,
			String customerComment, String comment, String failureCode,
			List<Failure> failures) throws SQLException,
			ProductionFailureReportDaoException, FailureDaoException,
			ElementChangedDaoException, FailureReportCommentDaoException,
			CustomerCommentDaoException, ConfigFileReaderException, IOException, NamingException {
		// Add failureReport
		ProductionFailureReportDao _failureReportDao = new ProductionFailureReportDaoImpl(
				this.cnxProduct, this.cnxOperator, this.cnxTester);

		ProductionFailureReport _productionfailureReport = _failureReportDao
				.addProductionFailureReport(
						new java.sql.Date(registrationDate.getTime()), product,
						testerReport, failureCode);
		_productionfailureReport.setFailures(failures);

		// Update customerComment
		this.updateCustomerComment(_productionfailureReport, customerComment);

		// Update failureReportComment
		this.updateProductionFailureReportComment(_productionfailureReport,
				comment);

		// Add failures
		this.addFailures(_productionfailureReport);

		return _productionfailureReport;
	}

	/*
	 * Mise à jour du customerComment d'un failureReport dans la bdd
	 */
	private void updateCustomerComment(
			ProductionFailureReport productionFailureReport,
			String customerComment) throws SQLException,
			CustomerCommentDaoException {
		CustomerCommentDao _customerCommentDao = new CustomerCommentDaoImpl(
				this.cnxProduct);

		if (false == customerComment.isEmpty()) {
			CustomerComment _customerComment = _customerCommentDao
					.getCustomerComment(productionFailureReport);
			if (null == _customerComment) {
				_customerCommentDao.addCustomerComment(productionFailureReport,
						customerComment);
			} else {
				_customerCommentDao.updateCustomerComment(
						productionFailureReport, customerComment);
			}
		} else {
			_customerCommentDao.removeCustomerComment(productionFailureReport);
		}
	}

	/*
	 * Mise à jour du failureReportComment d'un failureReport dans la bdd
	 */
	private void updateProductionFailureReportComment(
			ProductionFailureReport productionFailureReport, String comment)
			throws SQLException, NamingException {
		FailureReportCommentDao _failureReportCommentDao = new FailureReportCommentDaoImpl(
				this.cnxProduct);

		if (!comment.isEmpty()) {

			FailureReportComment _failureReportComment = _failureReportCommentDao
					.getFailureReportComment(productionFailureReport);

			if (_failureReportComment.getIdFailureReportComment() == 0) {

				try {
					_failureReportCommentDao.addFailureReportComment(
							productionFailureReport, comment);
					this.cnxProduct.getCnx().commit();
				} catch (FailureReportCommentDaoException e) {
					this.cnxProduct.getCnx().rollback();
					e.printStackTrace();
				}
			} else {
				try {
					_failureReportCommentDao.updateFailureReportComment(
							productionFailureReport, comment);
					this.cnxProduct.getCnx().commit();
				} catch (FailureReportCommentDaoException e) {
					this.cnxProduct.getCnx().rollback();
					e.printStackTrace();
				}
			}
		} else {
			_failureReportCommentDao
					.removeFailureReportComment(productionFailureReport);
		}
	}

	/*
	 * Mise à jour d'un failureReport dans la bdd
	 */
	private void updateProductionFailureReport(
			ProductionFailureReport productionFailureReport,
			Date registrationDate, Product product, String customerComment,
			String comment, String failureCode, List<Failure> failures)
			throws SQLException, ProductionFailureReportDaoException,
			CustomerCommentDaoException, FailureReportCommentDaoException,
			FailureDaoException, ElementChangedDaoException, NamingException {
		// Update failureReport
		ProductionFailureReportDao _failureReportDao = new ProductionFailureReportDaoImpl(
				this.cnxProduct, this.cnxOperator, this.cnxTester);

		_failureReportDao.updateProductionFailureReport(
				productionFailureReport,
				new java.sql.Date(registrationDate.getTime()), product,
				failureCode);
		

		productionFailureReport.setFailures(failures);

		System.out.println("valeur production failure report après"
				+ productionFailureReport);

		// Update customerComment
		this.updateCustomerComment(productionFailureReport, customerComment);

		// Update failureReportComment
		this.updateProductionFailureReportComment(productionFailureReport,
				comment);

		// Update failures
		this.updateFailures(productionFailureReport);
	}

	/*
	 * Mise à jour des failures d'un failureReport dans la bdd
	 */
	private Failure updateFailures(
			ProductionFailureReport productionFailureReport)
			throws SQLException, FailureDaoException,
			ElementChangedDaoException {
		ProductionFailureReport _baseProductionFailureReport = new ProductionFailureReport(
				productionFailureReport.getIdProductionFailureReport(),
				productionFailureReport.getTimestamp(),
				productionFailureReport.getState(),
				productionFailureReport.getRegistrationDate(),
				productionFailureReport.getFailureCode(),
				productionFailureReport.getProduct(),
				productionFailureReport.getTesterReport());
		FailureDao _failureFailureDao = new FailureDaoImpl(this.cnxProduct,
				this.cnxOperator);

		// Failures of the productionFailureReport in database
		List<Failure> _baseFailures = _failureFailureDao
				.getFailures(_baseProductionFailureReport);
		// Failures of the failureReport
		List<Failure> _objectFailures = productionFailureReport.getFailures();

		// Save _objectFailures in database
		Failure _failure = null;

		List<Failure> listFailureWithCardDismantled = new ArrayList<Failure>();
		boolean _contains = false;
		int _nbFailures = _objectFailures.size();
		for (int _index = 0; _index < _nbFailures; _index++) {
			_failure = _objectFailures.get(_index);
			System.out.println("failure en traitement" + _failure);

			_contains = this.contains(_baseFailures, _failure);
			if (!_contains) {
				// Add failure
				System.out.println("*** failure a inserer" + _failure);
				if (_failure.getNewFailureCardChanged() != null) {

					for (Failure failureWithCardDismantled : listFailureWithCardDismantled) {
						System.out.println(failureWithCardDismantled.getProduct()
							.getProductConf().getReference() + " == "+_failure.getProduct()
							.getProductConf().getReference() + (failureWithCardDismantled
									.getProduct()
									.getDatecode()
									+" == "+_failure.getProduct()
											.getDatecode()));
                    System.out.println((failureWithCardDismantled.getProduct()
							.getProductConf().getReference().equals(_failure.getProduct()
							.getProductConf().getReference()))
							+ " && "+ (failureWithCardDismantled
									.getProduct()
									.getDatecode()
									.equals(_failure.getNewFailureCardChanged().getProduct().
											getDatecode())+  " && "+ (failureWithCardDismantled
									.getProduct().getSerialNumber()
									+" == "+_failure.getNewFailureCardChanged().getProduct()
											.getSerialNumber())));
						if ((failureWithCardDismantled.getProduct()
								.getProductConf().getReference().equals(_failure.getProduct()
								.getProductConf().getReference()))
								&& (failureWithCardDismantled
										.getProduct()
										.getDatecode()
										.equals(_failure.getNewFailureCardChanged().getProduct().
												getDatecode()) && (failureWithCardDismantled
										.getProduct().getSerialNumber()
										.equals(_failure.getNewFailureCardChanged().getProduct()
												.getSerialNumber())))) {
							System.out
									.println("modify id of new Card Dismantle with the true id inserted in data base");
							_failure.getNewFailureCardChanged().setIdFailure(failureWithCardDismantled.getIdFailure());
							_failure = this.addFailure(_failure,
									productionFailureReport);

						} else {

							
						}
					}

				} else {
					System.out.println("failure null");
					if (_failure.isDismantleCard()) {
						System.out
								.println(" the card is dismantled, set the object in memory failure");
						
						_failure = this.addFailure(_failure,
								productionFailureReport);
						listFailureWithCardDismantled.add(_failure);
					}
					else {
						System.out.println("create normal failure");
						_failure = this.addFailure(_failure,
								productionFailureReport);
					}
				}
			} else {
				// Update failure
				System.out.println("failure a updater " + _failure);
				this.updateFailure(_failure, productionFailureReport);
			}

		}

		// Remove _baseFailures not in _objectFailures
		for (int _index = 0; _index < _baseFailures.size(); _index++) {
			_failure = _baseFailures.get(_index);
			_contains = this.contains(_objectFailures, _failure);
			if (!_contains) {
				// Remove failure
				System.out.println("remove failure");
				this.removeFailure(_failure);
			} else {
				// Existing failure
			}
		}
		return _failure;
	}

	/*
	 * Search a failure in a failure list.
	 */
	private boolean contains(List<Failure> failures, Failure failure) {
		boolean _contains = false;

		int _index = 0;
		int _listSize = failures.size();
		if (0 < _listSize) {
			Failure _failure = null;
			do {
				_failure = failures.get(_index);

				if (failure.getIdFailure() == _failure.getIdFailure()) {
					_contains = true;
				}

				// Next
				_index++;
			} while ((false == _contains) && (_index < _listSize));
		}

		return _contains;
	}

	/*
	 * Suppression des elementChanged d'une failure dans la bdd
	 */
	private void removeElementsChanged(Failure failure) throws SQLException {
		if (null != failure) {
			// Read elements changed link to this failure in database
			ElementChangedDao _elementChangedDao = new ElementChangedDaoImpl(
					this.cnxProduct);

			List<ElementChanged> _elementsChanged = _elementChangedDao
					.getElementsChanged(failure);
			ElementChanged _elementChanged = null;
			for (int _index = 0; _index < _elementsChanged.size(); _index++) {
				_elementChanged = _elementsChanged.get(_index);
				this.removeElementChanged(_elementChangedDao, _elementChanged);

			}
		} else {
			// Unknown failure
		}
	}

	/*
	 * Suppression d'un elementChanged d'une failure dans la bdd
	 */
	private void removeElementChanged(ElementChangedDao elementChangedDao,
			ElementChanged elementChanged) throws SQLException {
		if (null != elementChanged) {
			elementChangedDao.removeElementChanged(elementChanged);
		} else {
			// Unknown elementChanged
		}
	}

	/*
	 * Ajout des elementChanged d'une failure dans la bdd
	 */
	private void addElementsChanged(Failure failure) throws SQLException,
			ElementChangedDaoException {
		
		if (null != failure) {
			System.out.println("failure avant inserer element changed"
					+ failure);
			List<ElementChanged> _elementsChanged = failure
					.getElementsChanged();
			for (ElementChanged _elementChanged : _elementsChanged) {
				this.addElementChanged(_elementChanged, failure);
			}
		}
	}

	/*
	 * Ajout d'un elementChanged d'une failure dans la bdd
	 */
	private void addElementChanged(ElementChanged elementChanged,
			Failure failure) throws SQLException, ElementChangedDaoException {
		ElementChangedDao _elementChangedDao = new ElementChangedDaoImpl(
				this.cnxProduct);

		_elementChangedDao.addElementChanged(elementChanged, failure);
	}

	/*
	 * Ajout des failure d'un failureReport dans la bdd
	 */
	private void addFailures(ProductionFailureReport productionFailureReport)
			throws SQLException, FailureDaoException,
			ElementChangedDaoException, ConfigFileReaderException, IOException {
		List<Failure> _failures = productionFailureReport.getFailures();
		if (null != _failures) {
			for (Failure _failure : _failures) {
				this.addFailure(_failure, productionFailureReport);
			}
		} else {
			// Report without failure
		}
	}

	/*
	 * Ajout des failure d'un afterSaleReport dans la bdd
	 */
	protected void addFailures(AfterSaleReport afterSaleReport)
			throws SQLException, FailureDaoException,
			ElementChangedDaoException, ConfigFileReaderException, IOException {
		List<Failure> _failures = afterSaleReport.getFailures();
		if (null != _failures) {
			for (Failure _failure : _failures) {
				this.addFailure(_failure, afterSaleReport);
			}
		} else {
			// Report without failure
		}
	}

	/*
	 * Ajout d'une failure d'un failureReport dans la bdd
	 */
	private Failure addFailure(Failure failure,
			ProductionFailureReport productionFailureReport)
			throws SQLException, FailureDaoException,
			ElementChangedDaoException {
		FailureDao _failureDao = new FailureDaoImpl(this.cnxProduct,
				this.cnxOperator);
		System.out.println("add failure 896" + failure.getProduct().getIdProduct());
		// TODO Create product ?
		Failure _failure = _failureDao.addFailure(failure,
				productionFailureReport);

		this.addElementsChanged(_failure);

		System.out.println("return add Failure" + _failure);
		return _failure;
	}

	/*
	 * Ajout d'une failure d'un afterSaleReport dans la bdd
	 */
	protected Failure addFailure(Failure failure, AfterSaleReport afterSaleReport)
			throws SQLException, FailureDaoException,
			ElementChangedDaoException {
		FailureDao _failureDao = new FailureDaoImpl(this.cnxProduct,
				this.cnxOperator);

		// TODO Create product ?
		Failure _failure = _failureDao.addFailure(failure, afterSaleReport);

		failure.setIdFailure(_failure.getIdFailure());
		System.out.println("DAO Failure" + failure);
		this.addElementsChanged(failure);
		
		return _failure;
	}

	/*
	 * Mise à jour d'une failure d'un failureReport dans la bdd
	 */
	private void updateFailure(Failure failure,
			ProductionFailureReport failureReport) throws SQLException,
			FailureDaoException, ElementChangedDaoException {
		System.out.println("methode update Failure" + failure);
		FailureDao _failureDao = new FailureDaoImpl(this.cnxProduct,
				this.cnxOperator);

		_failureDao.updateFailure(failure, failureReport);

		// Remove previous elements changed
		//this.removeElementsChanged(failure);

		// Add elements changed
		this.addElementsChanged(failure);
	}

	protected void closeProductionFailureReport(
			ProductionFailureReport productionFailureReport) throws Exception {
		try {
			// Start transaction
			this.cnxProduct.getCnx().setAutoCommit(false);
			this.cnxOperator.getCnx().setAutoCommit(false);
			this.cnxTester.getCnx().setAutoCommit(false);

			if (null != productionFailureReport) {
				// Verification
				// State
				if (1 == productionFailureReport.getState()) {
					// Close failureReport
					ProductionFailureReportDao _failureReportDao = new ProductionFailureReportDaoImpl(
							this.cnxProduct, this.cnxOperator, this.cnxTester);

					_failureReportDao
							.closeProductionFailureReport(productionFailureReport);

				} else {
					// Not active failure report
				}
			} else {
				// Non-existent failure report
			}

			// Commit
			this.cnxTester.getCnx().commit();
			this.cnxOperator.getCnx().commit();
			this.cnxProduct.getCnx().commit();
		} catch (SQLException e) {
			this.cnxTester.getCnx().rollback();
			this.cnxOperator.getCnx().rollback();
			this.cnxProduct.getCnx().rollback();
			throw new Exception(e.getMessage());
		}
	}

	/*
	 * 05-06-12 : RMO : Mise à jour d'une failure d'une intervention dans la bdd
	 */
	protected void updateFailure(Failure failure) throws SQLException,
			FailureDaoException, ElementChangedDaoException {
		FailureDao _failureDao = new FailureDaoImpl(this.cnxProduct,
				this.cnxOperator);

		_failureDao.updateFailure(failure);
		System.out.println("Avant remove : "
				+ failure.getElementsChanged().size());

		// Remove previous elements changed
		this.removeElementsChanged(failure);
		System.out.println("Après remove : "
				+ failure.getElementsChanged().size());

		// Add elements changed
		this.addElementsChanged(failure);
	}
}
