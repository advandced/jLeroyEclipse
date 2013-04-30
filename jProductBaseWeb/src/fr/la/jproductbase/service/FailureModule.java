package fr.la.jproductbase.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import fr.la.jproductbase.dao.CustomerCommentDao;
import fr.la.jproductbase.dao.ElementChangedDao;
import fr.la.jproductbase.dao.FailureDao;
import fr.la.jproductbase.dao.FailureReportCommentDao;
import fr.la.jproductbase.dao.ProductionFailureReportDao;
import fr.la.jproductbase.dao.TesterReportDao;
import fr.la.jproductbase.metier.AfterSaleReport;
import fr.la.jproductbase.metier.CustomerComment;
import fr.la.jproductbase.metier.ElementChanged;
import fr.la.jproductbase.metier.Failure;
import fr.la.jproductbase.metier.FailureReportComment;
import fr.la.jproductbase.metier.Product;
import fr.la.jproductbase.metier.ProductConf;
import fr.la.jproductbase.metier.ProductionFailureReport;
import fr.la.jproductbase.metier.TestType;
import fr.la.jproductbase.metier.Tester;
import fr.la.jproductbase.metier.TesterReport;

public class FailureModule {

	ProductionFailureReportDao _failureReportDao;
	FailureDao _failureDao;
	ElementChangedDao _elementChangedDao;
	CustomerCommentDao _customerCommentDao;
	FailureReportCommentDao _failureReportCommentDao;
	TesterReportDao _testerReportDao;
	
	ProductConfModule _productConfModule;
	ProductModule _productModule;
	TesterModule _testerModule;
	TestTypeModule _testTypeModule;
	
	
	public FailureModule(ProductConfModule productConfModule, 
							ProductModule productModule, 
							TesterModule testerModule, 
							TestTypeModule testTypeModule, 
							
							ProductionFailureReportDao failureReportDao, 
							FailureDao failureDao, 
							ElementChangedDao elementChangedDao, 
							CustomerCommentDao customerCommentDao, 
							FailureReportCommentDao failureReportCommentDao,
							TesterReportDao testerReportDao) {
		
		_productConfModule = productConfModule;
		_productModule = productModule;
		_testerModule = testerModule;
		_testTypeModule = testTypeModule;
		
		_failureReportDao = failureReportDao;
		_failureDao = failureDao;
		_elementChangedDao = elementChangedDao;
		_customerCommentDao = customerCommentDao;
		_failureReportCommentDao = failureReportCommentDao;
		_testerReportDao = testerReportDao;
	}

	public List<ProductionFailureReport> getProductionFailureReports() {
		List<ProductionFailureReport> _failureReports = _failureReportDao.listProductionFailureReport();
		return _failureReports;
	}

	public List<ProductionFailureReport> getProductionFailureReports(Product product) {
		List<ProductionFailureReport> _failureReports = _failureReportDao.listProductionFailureReport(product);
		return _failureReports;
	}

	public List<ProductionFailureReport> getProductionFailureReports(Date fromDate, Date toDate) {
		java.sql.Date _fromDate = new java.sql.Date(fromDate.getTime());
		java.sql.Date _toDate = new java.sql.Date(toDate.getTime());
		List<ProductionFailureReport> _failureReports = _failureReportDao.listProductionFailureReport(_fromDate, _toDate);
		return _failureReports;
	}

	public ProductionFailureReport getProductionFailureReport(TesterReport testerReport) {
		ProductionFailureReport _failureReport = _failureReportDao.getProductionFailureReport(testerReport);
		return _failureReport;
	}

	public List<ProductionFailureReport> getUnclosedProductionFailureReports() {
		List<ProductionFailureReport> _failureReports = _failureReportDao.unclosedProductionFailureReport();
		return _failureReports;
	}

	public List<String> getFaces() {
		List<String> _faces = new ArrayList<String>();
		_faces.add("Composant");
		_faces.add("Soudure");
		return _faces;
	}

	public List<String> getCompTypes() {
		List<String> _compTypes = new ArrayList<String>();
		_compTypes.add("Traditionnel");
		_compTypes.add("CMS");
		return _compTypes;
	}

	public Failure addFailureDismantedCard(ProductionFailureReport productionFailureReport, Failure failure) {
		Failure _failure = null;
		productionFailureReport.getFailures().add(failure);
		_failure = this.updateFailures(productionFailureReport);
		return _failure;
	}

	public List<Failure> getFailures(ProductionFailureReport productionFailureReport) {
		List<Failure> _failures = _failureDao.getFailures(productionFailureReport);
		return _failures;
	}

	public List<Failure> getFailures(AfterSaleReport afterSaleReport) {
		List<Failure> _failures = _failureDao.getFailures(afterSaleReport);
		return _failures;
	}

	/*
	 * Suppression d'un failure dans la bdd
	 */
	public void removeFailure(Failure failure) {
		_failureDao.removeFailure(failure);
	}

	public List<ElementChanged> getElementChanged(Failure failure) {
		List<ElementChanged> _elementsChanged = _elementChangedDao.getElementsChanged(failure);
		return _elementsChanged;
	}

	public CustomerComment getCustomerComment(ProductionFailureReport productionFailureReport) {
		CustomerComment _customerComment = _customerCommentDao.getCustomerComment(productionFailureReport);
		return _customerComment;
	}

	public FailureReportComment getFailureReportComment(ProductionFailureReport productionFailureReport) {
		FailureReportComment _failureReportComment = _failureReportCommentDao.getFailureReportComment(productionFailureReport);
		return _failureReportComment;
	}

	public ProductionFailureReport getProductionFailureReport(int idProductionFailureReport) {
		ProductionFailureReport _failureReport = _failureReportDao.getProductionFailureReport(idProductionFailureReport);
		return _failureReport;
	}

	public ProductionFailureReport setProductionFailureReport(
				ProductionFailureReport productionFailureReport,
				Date failureReportDate, String productConfReference,
				String productConfMajorIndex, String productMinorIndex,
				String serialNumber, String datecode, String testTypeName,
				String testerName, String operatorCode, String customerComment,
				String failureCode, String comment, List<Failure> failures) 
		{
			ProductionFailureReport _productionFailureReport = productionFailureReport;

			// Retrieve product
			ProductConf _productConf = _productConfModule.getProductConf(productConfReference, productConfMajorIndex,productMinorIndex);
			
			Product _product = _productModule.getProduct(_productConf, serialNumber, datecode);
			if (null == _product) {
				_product = _productModule.addProduct(_productConf,serialNumber, datecode);
			} else {
				// Nothing to do
			}
			// Retrieve testType
			TestType _testType = _testTypeModule.getTestType(testTypeName);
			// Retrieve tester
			Tester _tester = _testerModule.getTester(testerName);
			// Retrieve testerReport
			TesterReport _testerReport = null;
			if (null == _productionFailureReport) {
				// New failure report

				// Add testerReport
				_testerReport = _testerReportDao.addTesterReport(_testType,_tester, new java.sql.Timestamp(new Date().getTime()), operatorCode, _product);
			} else {
				// Existing failure report

				// Retreive testerReport
				_testerReport = productionFailureReport.getTesterReport();
			}

			// Update failures
			// List<Failure> _failures = new ArrayList<Failure>();
			this.updateFailures(_product, failures);
			_productionFailureReport = this.setProductionFailureReport(
					_productionFailureReport, failureReportDate, _product,
					_testType, _testerReport, operatorCode, customerComment,
					failureCode, comment, failures);

		return _productionFailureReport;
	}

	public ProductionFailureReport updateProductionFailureReport(
			ProductionFailureReport productionFailureReport) {

		ProductionFailureReport _productionFailureReport = productionFailureReport;

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
			List<Failure> failures) {
		ProductionFailureReport _productionFailureReport = productionFailureReport;
		if (null == _productionFailureReport) {
			// New production failure report

			// Add productionFailureReport
			_productionFailureReport = this.addProductionFailureReport(
					failureReportDate, product, testerReport, customerComment,
					comment, failureCode, failures);
		} else {
			// Existing failure report
			
			// Update productionFailureReport
			this.updateProductionFailureReport(productionFailureReport,
					failureReportDate, product, customerComment, comment,
					failureCode, failures);
		}

		return _productionFailureReport;
	}

	public ProductionFailureReport setProductionFailureReport(TesterReport testerReport, String customerComment) {
		ProductionFailureReport _productionFailureReport = null;

		this.setProductionFailureReport(null, testerReport.getDate(),
				testerReport.getProduct(), testerReport.getTestType(),
				testerReport, testerReport.getOperatorCode(),
				customerComment, "", "", null);

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
	public List<Failure> updateFailures(Product product, List<Failure> failures) {
		Failure _failureCardChanged = null;
		for (Failure failure : failures) {

			if(failure.getIdFailure()==0){
			// Update product component link
			if (true == failure.isDismantleCard()) {
				// Dismantle product component
				_productModule.removeProductComponent(product,
						failure.getProduct());
				_failureCardChanged = failure;
			} else {
				if (failure.getNewFailureCardChanged() != null) {
					if (_failureCardChanged.getIdFailure() == failure
							.getNewFailureCardChanged().getIdFailure()) {
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
			List<Failure> failures) {
		// Add failureReport
		ProductionFailureReport _productionfailureReport = _failureReportDao.addProductionFailureReport(
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
	private void updateCustomerComment(ProductionFailureReport productionFailureReport,	String customerComment) {
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
	private void updateProductionFailureReportComment(ProductionFailureReport productionFailureReport, String comment) {
		if (!comment.isEmpty()) {

			FailureReportComment _failureReportComment = _failureReportCommentDao
					.getFailureReportComment(productionFailureReport);

			if (_failureReportComment.getIdFailureReportComment() == 0) {
				_failureReportCommentDao.addFailureReportComment(productionFailureReport, comment);
			} else {
				_failureReportCommentDao.updateFailureReportComment(productionFailureReport, comment);
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
			String comment, String failureCode, List<Failure> failures) {

		_failureReportDao.updateProductionFailureReport(
				productionFailureReport,
				new java.sql.Date(registrationDate.getTime()), product,
				failureCode);
		productionFailureReport.setFailures(failures);
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
	private Failure updateFailures(ProductionFailureReport productionFailureReport) {
		ProductionFailureReport _baseProductionFailureReport = new ProductionFailureReport(
				productionFailureReport.getIdProductionFailureReport(),
				productionFailureReport.getTimestamp(),
				productionFailureReport.getState(),
				productionFailureReport.getRegistrationDate(),
				productionFailureReport.getFailureCode(),
				productionFailureReport.getProduct(),
				productionFailureReport.getTesterReport());


		// Failures of the productionFailureReport in database
		List<Failure> _baseFailures = _failureDao.getFailures(_baseProductionFailureReport);
		// Failures of the failureReport
		List<Failure> _objectFailures = productionFailureReport.getFailures();

		// Save _objectFailures in database
		Failure _failure = null;

		List<Failure> listFailureWithCardDismantled = new ArrayList<Failure>();
		boolean _contains = false;
		int _nbFailures = _objectFailures.size();
		for (int _index = 0; _index < _nbFailures; _index++) {
			_failure = _objectFailures.get(_index);
			_contains = this.contains(_baseFailures, _failure);
			if (!_contains) {
				// Add failure
				if (_failure.getNewFailureCardChanged() != null) {

					for (Failure failureWithCardDismantled : listFailureWithCardDismantled) {

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
							_failure.getNewFailureCardChanged().setIdFailure(failureWithCardDismantled.getIdFailure());
							_failure = this.addFailure(_failure, productionFailureReport);

						} else {

							
						}
					}

				} else {
					if (_failure.isDismantleCard()) {
						_failure = this.addFailure(_failure,productionFailureReport);
						listFailureWithCardDismantled.add(_failure);
					}
					else {
						_failure = this.addFailure(_failure,productionFailureReport);
					}
				}
			} else {
				// Update failure
				this.updateFailure(_failure, productionFailureReport);
			}

		}

		// Remove _baseFailures not in _objectFailures
		for (int _index = 0; _index < _baseFailures.size(); _index++) {
			_failure = _baseFailures.get(_index);
			_contains = this.contains(_objectFailures, _failure);
			if (!_contains) {
				// Remove failure
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
	private void removeElementsChanged(Failure failure) {
		if (null != failure) {
			// Read elements changed link to this failure in database
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
	private void removeElementChanged(ElementChangedDao elementChangedDao, ElementChanged elementChanged) {
		if (null != elementChanged) {
			elementChangedDao.removeElementChanged(elementChanged);
		} else {
			// Unknown elementChanged
		}
	}

	/*
	 * Ajout des elementChanged d'une failure dans la bdd
	 */
	private void addElementsChanged(Failure failure) {
		if (null != failure) {
			List<ElementChanged> _elementsChanged = failure.getElementsChanged();
			for (ElementChanged _elementChanged : _elementsChanged) {
				this.addElementChanged(_elementChanged, failure);
			}
		}
	}

	/*
	 * Ajout d'un elementChanged d'une failure dans la bdd
	 */
	private void addElementChanged(ElementChanged elementChanged, Failure failure) {
		_elementChangedDao.addElementChanged(elementChanged, failure);
	}

	/*
	 * Ajout des failure d'un failureReport dans la bdd
	 */
	private void addFailures(ProductionFailureReport productionFailureReport)  {
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
	public void addFailures(AfterSaleReport afterSaleReport) {
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
	private Failure addFailure(Failure failure,	ProductionFailureReport productionFailureReport) {
		// TODO Create product ?
		Failure _failure = _failureDao.addFailure(failure, productionFailureReport);
		this.addElementsChanged(_failure);
		return _failure;
	}

	/*
	 * Ajout d'une failure d'un afterSaleReport dans la bdd
	 */
	public Failure addFailure(Failure failure, AfterSaleReport afterSaleReport) {
		// TODO Create product ?
		Failure _failure = _failureDao.addFailure(failure, afterSaleReport);

		failure.setIdFailure(_failure.getIdFailure());
		this.addElementsChanged(failure);
		
		return _failure;
	}

	/*
	 * Mise à jour d'une failure d'un failureReport dans la bdd
	 */
	private void updateFailure(Failure failure,	ProductionFailureReport failureReport) {
		_failureDao.updateFailure(failure, failureReport);
		// Add elements changed
		this.addElementsChanged(failure);
	}

	public void closeProductionFailureReport(ProductionFailureReport productionFailureReport) {
		if (null != productionFailureReport) {
			// Verification
			// State
			if (1 == productionFailureReport.getState()) {
				// Close failureReport
				_failureReportDao.closeProductionFailureReport(productionFailureReport);

			} else {
				// Not active failure report
			}
		} else {
			// Non-existent failure report
		}
	}

	/*
	 * 05-06-12 : RMO : Mise à jour d'une failure d'une intervention dans la bdd
	 */
	public void updateFailure(Failure failure) {
		_failureDao.updateFailure(failure);
		// Remove previous elements changed
		this.removeElementsChanged(failure);
		// Add elements changed
		this.addElementsChanged(failure);
	}
}
