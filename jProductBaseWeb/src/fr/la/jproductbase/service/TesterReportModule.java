package fr.la.jproductbase.service;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.naming.NamingException;
import javax.swing.JOptionPane;

import fr.la.configfilereader.ConfigFileReaderException;
import fr.la.jproductbase.dao.ConnectionOperator;
import fr.la.jproductbase.dao.ConnectionProduct;
import fr.la.jproductbase.dao.ConnectionTester;
import fr.la.jproductbase.dao.DefectDao;
import fr.la.jproductbase.dao.DefectDaoException;
import fr.la.jproductbase.dao.DefectDaoImpl;
import fr.la.jproductbase.dao.TesterReportDao;
import fr.la.jproductbase.dao.TesterReportDaoException;
import fr.la.jproductbase.dao.TesterReportDaoImpl;
import fr.la.jproductbase.metier.Defect;
import fr.la.jproductbase.metier.ProductionFailureReport;
import fr.la.jproductbase.metier.JProductBaseException;
import fr.la.jproductbase.metier.LabviewTestType;
import fr.la.jproductbase.metier.LabviewTesterReport;
import fr.la.jproductbase.metier.Operator;
import fr.la.jproductbase.metier.PreTesterReport;
import fr.la.jproductbase.metier.Product;
import fr.la.jproductbase.metier.ProductConf;
import fr.la.jproductbase.metier.ProductTest;
import fr.la.jproductbase.metier.TestType;
import fr.la.jproductbase.metier.Tester;
import fr.la.jproductbase.metier.TesterReport;
import fr.la.jproductbase.metier.TesterReportException;

public class TesterReportModule {
	private ConnectionOperator cnxOperator;
	private ConnectionProduct cnxProduct;
	private ConnectionTester cnxTester;

	public TesterReportModule(ConnectionOperator cnxOperator,
			ConnectionProduct cnxProduct, ConnectionTester cnxTester) {
		this.cnxOperator = cnxOperator;
		this.cnxProduct = cnxProduct;
		this.cnxTester = cnxTester;
	}

	/**
	 * Recherche un rapport de testeur dans la base de donn&eacute;s.
	 * 
	 * @param testerReportDate
	 *            Date du rapport.
	 * @param productConfReference
	 *            R&eacute;f&eacute;rence de la configuration produit.
	 * @param productConfMajorIndex
	 *            Indice majeur de la configuration produit.
	 * @param productConfMinorIndex
	 *            Indice mineur de la configuration produit.
	 * @param serialNumber
	 *            Num&eacute;ro de s&eacute;rie du produit.
	 * @param datecode
	 *            Datecode du produit.
	 * @param testTypeName
	 *            Type de test.
	 * 
	 * @throws SQLException
	 * @throws TesterModuleException
	 */
	protected TesterReport getTesterReport(Date testerReportDate,
			String productConfReference, String productConfMajorIndex,
			String productConfMinorIndex, String serialNumber, String datecode,
			String testTypeName) throws SQLException, TesterModuleException {
		TesterReport _testerReport = null;

		// Retrieve product
		ProductConfModule _productConfModule = new ProductConfModule(
				this.cnxProduct);
		ProductConf _productConf = _productConfModule.getProductConf(
				productConfReference, productConfMajorIndex,
				productConfMinorIndex);

		ProductModule _productModule = new ProductModule(this.cnxProduct);
		Product _product = _productModule.getProduct(_productConf,
				serialNumber, datecode);
		if (null != _product) {
			// Retrieve testerType
			TestTypeModule _testTypeModule = new TestTypeModule(this.cnxTester);
			TestType _testType = _testTypeModule.getTestType(testTypeName);
			if (null != _testType) {
				// Verify last testerReport
				TesterReportDao _testerReportDao = new TesterReportDaoImpl(
						cnxProduct, cnxTester);
				_testerReport = _testerReportDao.getTesterReport(new Timestamp(
						testerReportDate.getTime()), _testType, _product);
			} else {
				throw new TesterModuleException("Type de test (" + testTypeName
						+ ") inconnu dans la base de données.");
			}
		} else {
			throw new TesterModuleException(
					"Produit inconnu dans la base de données.");
		}

		return _testerReport;
	}

	/**
	 * Enregistre un rapport de testeur dans la base de donn&eacute;s.
	 * 
	 * @param testerReportDate
	 *            Date du rapport.
	 * @param productConfReference
	 *            R&eacute;f&eacute;rence de la configuration produit.
	 * @param productConfMajorIndex
	 *            Indice majeur de la configuration produit.
	 * @param productConfMinorIndex
	 *            Indice mineur de la configuration produit.
	 * @param serialNumber
	 *            Num&eacute;ro de s&eacute;rie du produit.
	 * @param datecode
	 *            Datecode du produit.
	 * @param testTypeName
	 *            Type de test.
	 * @param reportOperatorCode
	 *            Code de l'op&eacute;rateur.
	 * @param testResult
	 *            R&eacute;sultat du test.
	 * @param customerComment
	 *            Description des d&eacute;fauts.
	 * 
	 * @throws SQLException
	 * @throws TesterModuleException
	 * @throws NamingException
	 */
	protected TesterReport setTesterReport(Date testerReportDate,
			String productConfReference, String productConfMajorIndex,
			String productConfMinorIndex, String serialNumber, String datecode,
			String testTypeName, String reportOperatorCode, String testResult,
			String customerComment) throws SQLException, TesterModuleException,
			NamingException {
		TesterReport _testerReport = null;
		try {
			// Start transaction
			this.cnxTester.getCnx().setAutoCommit(false);

			// Retrieve product
			ProductConfModule _productConfModule = new ProductConfModule(
					this.cnxProduct);
			ProductConf _productConf = _productConfModule.getProductConf(
					productConfReference, productConfMajorIndex,
					productConfMinorIndex);

			ProductModule _productModule = new ProductModule(this.cnxProduct);
			Product _product = _productModule.getProduct(_productConf,
					serialNumber, datecode);
			if (null != _product) {
				// Retrieve testerType
				TestTypeModule _testTypeModule = new TestTypeModule(
						this.cnxTester);
				TestType _testType = _testTypeModule.getTestType(testTypeName);
				if (null != _testType) {
					// Retrieve operator
					OperatorModule _operatorModule = new OperatorModule(
							this.cnxOperator);
					Operator _operator = _operatorModule
							.getOperator(reportOperatorCode);
					if (null == _operator) {
						int _confirm = JOptionPane
								.showConfirmDialog(
										null,
										"Code opérateur '"
												+ reportOperatorCode
												+ "' inconnu.\nConfirmez-vous cette information ?",
										"Confirmation",
										JOptionPane.YES_NO_OPTION);
						if (JOptionPane.YES_OPTION == _confirm) {
							// Continue
						} else {
							throw new TesterModuleException(
									"Opérateur inconnu dans la base de données.");
						}
					}

					boolean _startTest = false;
					boolean _inFlow = this.inFlowProcess(_product, _testType);
					if (false == _inFlow) {
						// Process not respected.
						StringBuffer _msgBuffer = new StringBuffer(150);
						_msgBuffer.append("\n\n");
						_msgBuffer
								.append("Souhaitez-vous tout de même enregistrer le ");
						_msgBuffer.append(testTypeName);

						int _confirm = JOptionPane.showConfirmDialog(null,
								"Le produit ne respecte pas le flux."
										+ _msgBuffer.toString(),
								"Confirmation", JOptionPane.YES_NO_OPTION);
						if (JOptionPane.YES_OPTION == _confirm) {
							_startTest = true;
						} else {
							_startTest = false;
						}
					} else {
						/* Product test respect process flow. */
						_startTest = true;
					}

					if (true == _startTest) {
						/* Etat de rapport */
						int _state = this.getTesterReportState(true, _inFlow);

						/* date du rapport */
						/*
						 * Fixe l'heure à 23:59:59 pour être certain que ce sera
						 * le dernier test lors d'un tri par date. TODO :
						 * Problème possible : Si le résultat est NOK et que le
						 * produit repasse en TF1 le même jour.
						 */
						Timestamp _testerReportDate = new Timestamp(
								testerReportDate.getTime()
										+ (((((23 * 60) + 59) * 60) + 59) * 1000));

						/* New testerReport */
						_testerReport = this.addTesterReport(_state,
								_testerReportDate, testResult, _testType,
								reportOperatorCode, _product);
					} else {
						throw new TesterModuleException(
								"Le flux n'est pas respecté.");
					}
				} else {
					throw new TesterModuleException("Type de test ("
							+ testTypeName
							+ ") inconnu dans la base de données.");
				}
			} else {
				throw new TesterModuleException(
						"Produit inconnu dans la base de données.");
			}

			// Commit
			this.cnxTester.getCnx().commit();
		} catch (Exception e) {
			this.cnxTester.getCnx().rollback();
			e.printStackTrace();
			throw new TesterModuleException(e.getMessage());
		}

		return _testerReport;
	}

	/*
	 * Ajouter un rapport testeur &agrave; la base de donn&eacute;e.
	 * 
	 * @param state Statut de l'enregistrement.
	 * 
	 * @param date Date du rapport.
	 * 
	 * @param result R&eacute;sultat.
	 * 
	 * @param testType Type de test.
	 * 
	 * @param operatorCode Code de l'op&eacute;rateur.
	 * 
	 * @param product Produit test&eacute;.
	 * 
	 * @return Rapport testeur.
	 */
	private TesterReport addTesterReport(int state, Timestamp date,
			String result, TestType testType, String operatorCode,
			Product product) throws SQLException, TesterReportDaoException,
			FailureModuleException, ConfigFileReaderException, IOException,
			TesterModuleException, DefectDaoException, NamingException {
		TesterReport _testerReport = new TesterReport(state, date, result,
				testType, operatorCode, product);

		_testerReport = this.addTesterReport(_testerReport);

		return _testerReport;
	}

	/*
	 * Ajouter un rapport testeur &agrave; la base de donn&eacute;e.
	 * 
	 * @param testerReport Rapport testeur.
	 * 
	 * @return Rapport testeur.
	 */
	private TesterReport addTesterReport(TesterReport testerReport)
			throws SQLException, TesterModuleException,
			TesterReportDaoException, DefectDaoException,
			ConfigFileReaderException, IOException, FailureModuleException,
			NamingException {
		TesterReport _testerReport = this.addTesterReport(testerReport, "");

		return _testerReport;
	}

	/*
	 * Ajouter un rapport testeur &agrave; la base de donn&eacute;e.
	 * 
	 * @param testerReport Rapport testeur.
	 * 
	 * @return Rapport testeur.
	 */
	private TesterReport addTesterReport(TesterReport testerReport,
			String customerComment) throws SQLException, TesterModuleException,
			TesterReportDaoException, ConfigFileReaderException, IOException,
			FailureModuleException, NamingException {
		TesterReport _testerReport = null;
		TesterReportDao _testerReportDao = new TesterReportDaoImpl(cnxProduct,
				cnxTester);
		_testerReport = _testerReportDao.addTesterReport(testerReport);

		// Add testerReport defects.
		DefectDao _defectDao = new DefectDaoImpl(this.cnxTester);
		for (Defect _defect : testerReport.getDefects()) {
			try {
				this.cnxOperator.getCnx().setAutoCommit(false);
				_defectDao.addDefect(_defect, _testerReport);
				this.cnxOperator.getCnx().commit();
			} catch (DefectDaoException e) {
				this.cnxOperator.getCnx().rollback();
				e.printStackTrace();
			}
		}

		// FailureReport si résultat "Failed" et état actif.
		if ((_testerReport.getResult().equals("Failed"))
				&& (0 != _testerReport.getState())) {
			// Create FailureReport
			ServiceInterface _serviceInterface = new ServiceInterface();
			_serviceInterface.setFailureReport(_testerReport, customerComment);
		} else {
			// Don't create FailureReport
		}

		return _testerReport;
	}

	protected TesterReport addTesterReport(TestType testType, Tester tester,
			String operatorCode, Product product) throws SQLException,
			NamingException {
		TesterReportDao _teterReportDao = new TesterReportDaoImpl(
				this.cnxProduct, this.cnxTester);

		java.sql.Timestamp _reportDate = new java.sql.Timestamp(
				new Date().getTime());
		TesterReport _testerReport = null;
		try {
			this.cnxOperator.getCnx().setAutoCommit(false);
			_testerReport = _teterReportDao.addTesterReport(testType, tester,
					_reportDate, operatorCode, product);
			this.cnxOperator.getCnx().commit();
		} catch (TesterReportDaoException e) {
			this.cnxOperator.getCnx().rollback();
			e.printStackTrace();
		}

		return _testerReport;
	}

	/*
	 * D&eacute;termine l'&eacute;tat du rapport de testeur.
	 * 
	 * @param resultConfirmation R&eacute;sultat confirm&eacute;.
	 * 
	 * @param inFlow Test devant &ecirc;tre comptabilis&eacute; dans le flux.
	 * 
	 * @return Etat du rapport de testeur.
	 */
	private int getTesterReportState(boolean resultConfirmation, boolean inFlow) {
		int _state = 1; // default testerReport state
		if (true != resultConfirmation) {
			_state = 0; // testerReport inactive not in flow
		} else {
			if (true != inFlow) {
				_state = 2; // testerReport active not in flow
			} else {
				_state = 1; // testerReport active in flow
			}
		}

		return _state;
	}

	protected boolean inFlowProcess(Product product, TestType testType)
			throws SQLException {
		boolean _inFlow = false;

		if ((null != product) && (null != testType)) {
			// Search last in flow testerReport.
			List<TesterReport> _testerReports = this
					.getInFlowTesterReport(product);
			TesterReport _lastTesterReport = null;
			for (TesterReport _testerReport : _testerReports) {
				if (0 == _testerReport.getIdTesterReportNext()) {
					if (null == _lastTesterReport) {
						_lastTesterReport = _testerReport;
					} else {
						if (_lastTesterReport.getDate().before(
								_testerReport.getDate())) {
							_lastTesterReport = _testerReport;
						} else {
							// Nothing to do
						}
					}
				} else {
					// Implicit report
				}
			}

			boolean _checkProcess = false;
			if (null != _lastTesterReport) {
				String _result = _lastTesterReport.getResult();
				// testerReport result may be "Failed" or "Passed"
				if (_result.equals("Passed")) {
					// Ok for verify process
					_checkProcess = true;
				} else {
					if (_result.equals("Failed")) {
						// failureReport may be closed
						ServiceInterface _serviceInterface = new ServiceInterface();
						ProductionFailureReport _failureReport = _serviceInterface
								.getFailureReport(_lastTesterReport);
						if (null != _failureReport) {
							if (2 == _failureReport.getState()) {
								/* Ok for verify process */
								_checkProcess = true;

								/* Restart process needed. */
								_lastTesterReport = null;
							} else {
								/*
								 * testerReport not in flow (because of
								 * _failureReport)
								 */
								_checkProcess = false;
							}
						} else {
							/* TODO : Pas de rapport de défaut => Exception ? */
							_checkProcess = false;
						}
					} else {
						/*
						 * TODO : Résultat non "Failed" ou "Passed" => Exception
						 * ?
						 */
						_checkProcess = false;
					}
				}
			}

			if (true == _checkProcess) {
				if ((product.getProductConf().getReference()
						.equals("PALTMES101"))
						|| (product.getProductConf().getReference()
								.equals("PALTMES102"))) {
					_inFlow = this.inFlowRATPProcess(product, testType,
							_lastTesterReport);
				} else {
					_inFlow = this.inFlowDefaultProcess(product, testType,
							_lastTesterReport);
				}
			} else {
				/* Process verification not needed. */
				_inFlow = true;
			}
		} else {
			/* Process verification not possible. */
			_inFlow = false;
		}

		return _inFlow;
	}

	/*
	 * Liste des rapports des testeurs d'un produits dans la base de
	 * donn&eacute;es.
	 * 
	 * @param product Produit.
	 * 
	 * @return Liste des rapports des testeurs d'un produits.
	 */
	private List<TesterReport> getInFlowTesterReport(Product product)
			throws SQLException {
		TesterReportDao _testerReportDao = new TesterReportDaoImpl(
				this.cnxProduct, this.cnxTester);

		List<TesterReport> _testerReports = _testerReportDao
				.getInFlowTesterReport(product);

		return _testerReports;
	}

	/*
	 * V&eacute;rifie que le produit respecte le process par défaut.
	 * 
	 * @param product Produit.
	 * 
	 * @param testType Type de test.
	 * 
	 * @param lastTesterReport Dernier rapport de testeur.
	 * 
	 * @return Respect du process.
	 */
	private boolean inFlowDefaultProcess(Product product, TestType testType,
			TesterReport lastTesterReport) {
		boolean _inFlow = false;

		String _testTypeName = testType.getName();
		String _lastTesterReportResult = "Failed";
		if (null != lastTesterReport) {
			_lastTesterReportResult = lastTesterReport.getResult();
		} else {
			_lastTesterReportResult = "Failed";
		}

		// TF1 si pas de test précédent ou test précédent "Failed"
		if ((_testTypeName.equals("Test fonctionnel 1"))
				&& ((null == lastTesterReport) || (_lastTesterReportResult
						.equals("Failed")))) {
			_inFlow = true;
		} else {
			if (null != lastTesterReport) {
				String _lastTestTypeName = lastTesterReport.getTestType()
						.getName().trim();

				// DIEL si TF1 "Passed"
				if ((_testTypeName.equals("Continuité des masses"))
						&& ((_lastTestTypeName.equals("Test fonctionnel 1")) || (_lastTesterReportResult
								.equals("Passed")))) {
					_inFlow = true;
				} else {
					// DYN si DIEL "Passed"
					if ((_testTypeName.equals("Déverminage Dynamique"))
							&& ((_lastTestTypeName
									.equals("Test isolement et tenue en tension")) || (_lastTesterReportResult
									.equals("Passed")))) {
						_inFlow = true;
					} else {
						// TF2 si DYN "Passed"
						if ((_testTypeName.equals("Test fonctionnel 2"))
								&& ((_lastTestTypeName
										.equals("Déverminage Dynamique")) || (_lastTesterReportResult
										.equals("Passed")))) {
							_inFlow = true;
						} else {
							// Contrôle final si TF2 "Passed"
							if ((_testTypeName.equals("Contrôle final"))
									&& ((_lastTestTypeName
											.equals("Test fonctionnel 2")) || (_lastTesterReportResult
											.equals("Passed")))) {
								_inFlow = true;
							} else {
								_inFlow = false;
							}
						}
					}
				}
			} else {
				// Pas de test précédent
				_inFlow = false;
			}
		}

		return _inFlow;
	}

	/*
	 * V&eacute;rifie que le produit respecte le process RATP.
	 * 
	 * @param product Produit.
	 * 
	 * @param testType Type de test.
	 * 
	 * @param lastTesterReport Dernier rapport de testeur.
	 * 
	 * @return Respect du process.
	 */
	private boolean inFlowRATPProcess(Product product, TestType testType,
			TesterReport lastTesterReport) {
		boolean _inFlow = false;

		String _testTypeName = testType.getName();
		String _lastTesterReportResult = "Failed";
		if (null != lastTesterReport) {
			_lastTesterReportResult = lastTesterReport.getResult();
		} else {
			_lastTesterReportResult = "Failed";
		}

		// TF1 si pas de test précédent ou test précédent "Failed"
		if ((_testTypeName.equals("Test fonctionnel 1"))
				&& ((null == lastTesterReport) || (_lastTesterReportResult
						.equals("Failed")))) {
			_inFlow = true;
		} else {
			if (null != lastTesterReport) {
				String _lastTestTypeName = lastTesterReport.getTestType()
						.getName().trim();

				// DIEL si TF1 "Passed"
				if ((_testTypeName.equals("Continuité des masses"))
						&& ((_lastTestTypeName.equals("Test fonctionnel 1")) || (_lastTesterReportResult
								.equals("Passed")))) {
					_inFlow = true;
				} else {
					// DYN si DIEL "Passed"
					if ((_testTypeName.equals("Déverminage Dynamique"))
							&& ((_lastTestTypeName
									.equals("Test isolement et tenue en tension")) || (_lastTesterReportResult
									.equals("Passed")))) {
						_inFlow = true;
					} else {
						// TF2 si DYN "Passed"
						if ((_testTypeName.equals("Test fonctionnel 2"))
								&& ((_lastTestTypeName
										.equals("Déverminage Dynamique")) || (_lastTesterReportResult
										.equals("Passed")))) {
							_inFlow = true;
						} else {
							_inFlow = false;
						}
					}
				}
			} else {
				// Pas de test précédent
				_inFlow = false;
			}
		}

		return _inFlow;
	}

	/**
	 * Enregistre un rapport de testeur dans la base de donn&eacute;s.
	 * 
	 * @param preTesterReport
	 *            Pr&eacute;-rapport.
	 * @param labviewTesterReport
	 *            Rapport testeur Labview.
	 * 
	 * @return Rapport testeur.
	 * 
	 * @throws ParseException
	 * @throws TesterReportException
	 * @throws TesterModuleException
	 * @throws IOException
	 * @throws ConfigFileReaderException
	 * @throws FailureModuleException
	 * @throws TesterReportDaoException
	 * @throws SQLException
	 * @throws JProductBaseException
	 * @throws NamingException
	 */
	protected TesterReport saveTesterReport(PreTesterReport preTesterReport,
			LabviewTesterReport labviewTesterReport) throws ParseException,
			SQLException, TesterReportDaoException, FailureModuleException,
			ConfigFileReaderException, IOException, TesterModuleException,
			TesterReportException, JProductBaseException, NamingException {
		TesterReport _testerReport = null;

		// Formatage de la date et heure
		SimpleDateFormat _dateFormat = new SimpleDateFormat(
				"yyyy-MM-dd hh:mm:ss");

		try {
			// Start transaction
			this.cnxTester.getCnx().setAutoCommit(false);

			for (ProductTest _productTest : preTesterReport.getProductTests()) {
				// TesterReport state
				int _state = this.getTesterReportState(
						preTesterReport.isResultConfirmation(),
						_productTest.isInFlow());
				// TesterReport date (timestamp)
				String _dateString = labviewTesterReport.getDate();
				if (labviewTesterReport.getHeure().equals("")) {
					// No time
					_dateString += " 00:00:00";
				} else {
					_dateString += " " + labviewTesterReport.getHeure();
				}
				Date _date = _dateFormat.parse(_dateString);
				Timestamp _testerReportDate = new Timestamp(_date.getTime());

				// TesterReport tester
				TesterModule _testerModule = new TesterModule(this.cnxProduct,
						this.cnxTester);
				Tester _tester = _testerModule.getTester(labviewTesterReport
						.getStationId());
				if (null == _tester) {
					// Create tester
					_testerModule.addTester(labviewTesterReport.getStationId());
				} else {
					// Known tester
				}

				// TesterReport testType
				// Retreive testType of pre-report (This test doesn't need
				// database access).
				LabviewTestType _labviewTestType = LabviewTestType
						.valueOf(_productTest.getLabviewTestType());
				TestTypeModule _testTypeModule = new TestTypeModule(
						this.cnxTester);
				TestType _testType = _testTypeModule
						.retreiveTestType(_labviewTestType);

				// TesterReport product
				ProductModule _productModule = new ProductModule(
						this.cnxProduct);
				Product _testerReportProduct = _productModule.retreiveProduct(
						_productTest.getProductConfReference(),
						_productTest.getProductSerialNumber(),
						_productTest.getProductDatecode());
				if (null != _testerReportProduct) {
					// Convert consos
					int _consoUmini = 0;
					if (null != labviewTesterReport.getConsoUmini()) {
						_consoUmini = Integer.parseInt(labviewTesterReport
								.getConsoUmini());
					} else {
						_consoUmini = 0;
					}

					int _consoUnomi = 0;
					if (null != labviewTesterReport.getConsoUnomi()) {
						_consoUnomi = Integer.parseInt(labviewTesterReport
								.getConsoUnomi());
					} else {
						_consoUnomi = 0;
					}

					int _consoUmaxi = 0;
					if (null != labviewTesterReport.getConsoUmaxi()) {
						_consoUmaxi = Integer.parseInt(labviewTesterReport
								.getConsoUmaxi());
					} else {
						_consoUmaxi = 0;
					}

					// Create testerReport
					_testerReport = new TesterReport(_state, _testerReportDate,
							labviewTesterReport.getVersionTest(),
							labviewTesterReport.getResultat(), _consoUmini,
							_consoUnomi, _consoUmaxi, _tester, _testType,
							_productTest.getOperatorCode(),
							_testerReportProduct,
							labviewTesterReport.getDefects());

					// Add testerReport in bdd
					_testerReport = this.addTesterReport(_testerReport,
							preTesterReport.getCustomerComment());

					// Implicit report
					this.setImplicitTesterReport(_testerReport);

					// Mac address
					String _macAddress = "";
					if (null != labviewTesterReport.getMacAdresse()) {
						_macAddress = labviewTesterReport.getMacAdresse();
					} else {
						_macAddress = "";
					}
					if (false == _macAddress.trim().equals("")) {
						// Update product
						Product _product = _productTest.getProduct();
						_product.setMacAddress(labviewTesterReport
								.getMacAdresse());
						_productModule.updateProduct(_product, _macAddress);
					} else {
						/* Nothing to do */
					}
				} else {
					throw new TesterModuleException(
							"Produit du rapport à intégrer inconnu.");
				}
			}

			// Commit
			this.cnxTester.getCnx().commit();
		} catch (Exception e) {
			this.cnxTester.getCnx().rollback();
			e.printStackTrace();
			throw new TesterModuleException(e.getMessage());
		}

		return _testerReport;
	}

	private void setImplicitTesterReport(TesterReport testerReport)
			throws ConfigFileReaderException, IOException, SQLException,
			TesterReportException, TesterModuleException,
			TesterReportDaoException, FailureModuleException,
			DefectDaoException, NamingException {
		/* Date du rapport */
		GregorianCalendar _calendar = new java.util.GregorianCalendar();
		_calendar.setTimeInMillis(testerReport.getDate().getTime());
		/* Delta entre les dates des rapports (en secondes) */
		int _deltaTesterReportDate = 1;

		int _state = testerReport.getState();
		if (1 == _state) {
			// Enregistrement des rapports implicites
			// Controle visuel "Passed" si TF1
			if (testerReport.getTestType().getName()
					.equals(LabviewTestType.TF1.getName())) {
				/*
				 * Date du rapport "TF1" - un delta pour que les rapports
				 * puissent être triés par date
				 */
				_calendar.add(Calendar.SECOND, -_deltaTesterReportDate);
				Timestamp _testerReportTimestamp = new Timestamp(
						_calendar.getTimeInMillis());

				// Ajoute un rapport de "Examen visuel"
				TesterReport _testerReport = this.addImplictTesterReport(
						"Examen visuel", _testerReportTimestamp, testerReport);

				// Update testerReport
				this.updateTesterReport(_testerReport, testerReport);
			}

			// Test isolement si DIEL Passed
			if ((testerReport.getTestType().getName()
					.equals(LabviewTestType.DIEL.getName()))
					&& (testerReport.getResult().equals("Passed"))) {
				/*
				 * Date du rapport "Test isolement" + un delta pour que les
				 * rapports puissent être triés par date
				 */
				_calendar.add(Calendar.SECOND, +_deltaTesterReportDate);
				Timestamp _testerReportTimestamp = new Timestamp(
						_calendar.getTimeInMillis());

				// Ajoute un rapport de "Test isolement et tenue en tension"
				TesterReport _testerReport = addImplictTesterReport(
						"Test isolement et tenue en tension",
						_testerReportTimestamp, testerReport);

				// Update testerReport
				this.updateTesterReport(testerReport, _testerReport);
			}
		}
	}

	private TesterReport addImplictTesterReport(String testTypeName,
			Timestamp date, TesterReport testerReport) throws SQLException,
			TesterReportDaoException, FailureModuleException,
			ConfigFileReaderException, IOException, TesterModuleException,
			TesterReportException, DefectDaoException, NamingException {
		TesterReport _testerReport = null;
		TestTypeModule _testTypeModule = new TestTypeModule(this.cnxTester);
		TestType _testType = _testTypeModule.getTestType(testTypeName);
		if (null != _testType) {
			// New testerReport
			_testerReport = new TesterReport(1, date, "Passed", _testType,
					testerReport.getOperatorCode(), testerReport.getProduct());
			// Add implicite testerReport
			_testerReport = this.addTesterReport(_testerReport);
		} else {
			throw new TesterReportException("Type de test (" + testTypeName
					+ ") inconnu.");
		}

		return _testerReport;
	}

	/*
	 * Liaison entre deux rapport testeur.
	 * 
	 * @param testerReport Rapport testeur à mettre à jour.
	 * 
	 * @param testerReportNext Rapport testeur suivant.
	 */
	private void updateTesterReport(TesterReport testerReport,
			TesterReport testerReportNext) throws SQLException {
		TesterReportDao _testerReportDao = new TesterReportDaoImpl(cnxProduct,
				cnxTester);
		_testerReportDao.updateTesterReport(testerReport, testerReportNext);
	}
}
