package fr.la.jproductbase.service;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.naming.NamingException;

import fr.la.configfilereader.ConfigFileReaderException;
import fr.la.jproductbase.dao.AfterSaleComDao;
import fr.la.jproductbase.dao.AfterSaleComDaoException;
import fr.la.jproductbase.dao.AfterSaleComImpl;
import fr.la.jproductbase.dao.AfterSaleReportDao;
import fr.la.jproductbase.dao.AfterSaleReportDaoException;
import fr.la.jproductbase.dao.AfterSaleReportDaoImpl;
import fr.la.jproductbase.dao.ConnectionOperator;
import fr.la.jproductbase.dao.ConnectionProduct;
import fr.la.jproductbase.dao.CustomerCommentDaoException;
import fr.la.jproductbase.dao.ElementChangedDaoException;
import fr.la.jproductbase.dao.FailureDao;
import fr.la.jproductbase.dao.FailureDaoException;
import fr.la.jproductbase.dao.FailureDaoImpl;
import fr.la.jproductbase.dao.FailureReportCommentDaoException;
import fr.la.jproductbase.dao.ProductDaoException;
import fr.la.jproductbase.metier.AfterSaleCom;
import fr.la.jproductbase.metier.AfterSaleReport;
import fr.la.jproductbase.metier.ApparentCause;
import fr.la.jproductbase.metier.Failure;
import fr.la.jproductbase.metier.JProductBaseException;
import fr.la.jproductbase.metier.Product;

public class AfterSaleModule {
	private ConnectionProduct cnxProduct;
	private ConnectionOperator cnxOperator;

	protected AfterSaleModule(ConnectionProduct cnxProduct,
			ConnectionOperator cnxOperator) {
		this.cnxProduct = cnxProduct;
		this.cnxOperator = cnxOperator;
	}

	protected List<AfterSaleReport> getAfterSaleReports() throws SQLException,
			ConfigFileReaderException, IOException {
		AfterSaleReportDao _afterSaleReportDao = new AfterSaleReportDaoImpl(
				this.cnxProduct, this.cnxOperator);

		List<AfterSaleReport> _afterSaleReports = _afterSaleReportDao
				.listAfterSaleReport();

		return _afterSaleReports;
	}

	protected List<AfterSaleReport> getAfterSaleReports(Product product)
			throws SQLException, ConfigFileReaderException, IOException {
		AfterSaleReportDao _afterSaleReportDao = new AfterSaleReportDaoImpl(
				this.cnxProduct, this.cnxOperator);

		List<AfterSaleReport> _afterSaleReports = _afterSaleReportDao
				.listAfterSaleReport(product);

		return _afterSaleReports;
	}

	protected List<AfterSaleReport> getAfterSaleReports(int idproduct)
			throws SQLException, ConfigFileReaderException, IOException {
		AfterSaleReportDao _afterSaleReportDao = new AfterSaleReportDaoImpl(
				this.cnxProduct, this.cnxOperator);

		List<AfterSaleReport> _afterSaleReports = _afterSaleReportDao
				.listAfterSaleReport(idproduct);

		return _afterSaleReports;
	}

	protected List<AfterSaleReport> getAfterSaleReports(Date fromDate,
			Date toDate) throws SQLException, ConfigFileReaderException,
			IOException {
		AfterSaleReportDao _afterSaleReportDao = new AfterSaleReportDaoImpl(
				this.cnxProduct, this.cnxOperator);

		java.sql.Date _fromDate = new java.sql.Date(fromDate.getTime());
		java.sql.Date _toDate = new java.sql.Date(toDate.getTime());

		List<AfterSaleReport> _afterSaleReports = _afterSaleReportDao
				.listAfterSaleReport(_fromDate, _toDate);

		return _afterSaleReports;
	}

	protected AfterSaleReport getAfterSaleReport(int idAfterSaleReport)
			throws SQLException, ConfigFileReaderException, IOException {
		AfterSaleReportDao _afterSaleReportDao = new AfterSaleReportDaoImpl(
				this.cnxProduct, this.cnxOperator);

		AfterSaleReport _afterSaleReport = _afterSaleReportDao
				.getAfterSaleReport(idAfterSaleReport);

		return _afterSaleReport;
	}

	/*
	 * Enregistre une intervention SAV dans la base de donn&eacute;s.
	 * 
	 * @param afterSaleReport Intervention SAV.
	 * 
	 * ...
	 * 
	 * @param comment Commentaire.
	 * 
	 * @param failures
	 * 
	 * @return afterSaleReport intervention SAV.
	 * 
	 * @throws SQLException
	 * 
	 * @throws FailureModuleException
	 */
	protected AfterSaleReport setAfterSaleReport(
			AfterSaleReport afterSaleReport, Date arrivalDate,
			String ecsNumber, String ncNature, Date firstAnalyseDate,
			Date materialInfoDate, Date reparationDate,
			Date qualityControlDate, Date expeditionDate, int functionnalTest,
			int visualControl, String asker, String intervenant,
			String InterventionSheetLink, String comment,
			List<Failure> failures, ApparentCause apparentCause,
			String majorIndexIn, String majorIndexOut, Product product)
			throws SQLException, FailureModuleException,
			AfterSaleReportDaoException, FailureDaoException,
			ElementChangedDaoException, FailureReportCommentDaoException,
			CustomerCommentDaoException, ConfigFileReaderException,
			IOException, ParseException, JProductBaseException,
			ProductDaoException, NamingException {

		AfterSaleReport _afterSaleReport = afterSaleReport;
		@SuppressWarnings("unused")
		List<Failure> _failures = new ArrayList<Failure>();
		ServiceInterface _serviceInterface = new ServiceInterface();
		_failures = _serviceInterface.updateFailure(product, failures);
		System.out.println(afterSaleReport);

		if (null == _afterSaleReport) {
			// New afterSale report

			// Add failureReport
			_afterSaleReport = this.addAfterSaleReport(arrivalDate, ecsNumber,
					ncNature, firstAnalyseDate, materialInfoDate,
					reparationDate, qualityControlDate, expeditionDate,
					functionnalTest, visualControl, asker, intervenant,
					InterventionSheetLink, comment, failures, apparentCause,
					majorIndexIn, majorIndexOut, product);

		} else {
			// Existing afterSale report

			// Update afterSaleReport
			System.out.println("update afterSaleReport " + afterSaleReport);
			this.updateAfterSaleReport(_afterSaleReport, arrivalDate,
					ecsNumber, ncNature, firstAnalyseDate, materialInfoDate,
					reparationDate, qualityControlDate, expeditionDate,
					functionnalTest, visualControl, asker, intervenant,
					InterventionSheetLink, comment, failures, apparentCause,
					majorIndexIn, majorIndexOut, product);

		}

		return _afterSaleReport;
	}

	/*
	 * Ajout d'un afterSaleReport dans la bdd
	 */
	protected AfterSaleReport addAfterSaleReport(Date arrivalDate,
			String ecsNumber, String ncNature, Date firstAnalyseDate,
			Date materialInfoDate, Date reparationDate,
			Date qualityControlDate, Date expeditionDate, int functionnalTest,
			int visualControl, String asker, String intervenant,
			String InterventionSheetLink, String comment,
			List<Failure> failures, ApparentCause apparentCause,
			String majorIndexIn, String majorIndexOut, Product product)
			throws SQLException, FailureDaoException,
			ElementChangedDaoException, FailureReportCommentDaoException,
			CustomerCommentDaoException, ConfigFileReaderException,
			IOException, NamingException {
		// Add afterSaleReport
		AfterSaleReportDao _afterSaleReportDao = new AfterSaleReportDaoImpl(
				this.cnxProduct, this.cnxOperator);

		AfterSaleReport _afterSaleReport = null;
		try {
			this.cnxProduct.getCnx().setAutoCommit(false);
			_afterSaleReport = _afterSaleReportDao.addAfterSaleReport(
					arrivalDate, ecsNumber, ncNature, firstAnalyseDate,
					materialInfoDate, reparationDate, qualityControlDate,
					expeditionDate, functionnalTest, visualControl, asker,
					intervenant, InterventionSheetLink, comment, apparentCause,
					majorIndexIn, majorIndexOut, product);
			this.cnxProduct.getCnx().commit();
		} catch (AfterSaleReportDaoException e) {
			this.cnxProduct.getCnx().rollback();
			e.printStackTrace();
		}
		_afterSaleReport.setFailures(failures);

		// Add failures
		// serviceInterface.addFailures(_afterSaleReport);

		if (null != failures) {
			this.updateFailures(_afterSaleReport);
		}
		return _afterSaleReport;
	}

	/*
	 * Mise à jour d'un afterSaleReport dans la bdd
	 */
	protected void updateAfterSaleReport(AfterSaleReport afterSaleReport,
			Date arrivalDate, String ecsNumber, String ncNature,
			Date firstAnalyseDate, Date materialInfoDate, Date reparationDate,
			Date qualityControlDate, Date expeditionDate, int functionnalTest,
			int visualControl, String asker, String intervenant,
			String InterventionSheetLink, String comment,
			List<Failure> failures, ApparentCause apparentCause,
			String majorIndexIn, String majorIndexOut, Product product)
			throws SQLException, FailureDaoException,
			ElementChangedDaoException, NamingException {
		// Update failureReport
		AfterSaleReportDao _afterSaleReportDao = new AfterSaleReportDaoImpl(
				this.cnxProduct, this.cnxOperator);

		try {

			this.cnxProduct.getCnx().setAutoCommit(false);
			_afterSaleReportDao.updateAfterSaleReport(afterSaleReport,
					arrivalDate, ecsNumber, ncNature, firstAnalyseDate,
					materialInfoDate, reparationDate, qualityControlDate,
					expeditionDate, functionnalTest, visualControl, asker,
					intervenant, InterventionSheetLink, comment, apparentCause,
					majorIndexIn, majorIndexOut, product);
			this.cnxProduct.getCnx().commit();
		} catch (AfterSaleReportDaoException e) {
			this.cnxProduct.getCnx().rollback();
			e.printStackTrace();
		}
		afterSaleReport.setFailures(failures);

		// Update failures
		if (null != failures) {
			this.updateFailures(afterSaleReport);
		}
	}

	/*
	 * Mise à jour des failures d'un afterSaleReport dans la bdd
	 */
	private void updateFailures(AfterSaleReport afterSaleReport)
			throws SQLException, FailureDaoException,
			ElementChangedDaoException {

		FailureDao _failureFailureDao = new FailureDaoImpl(this.cnxProduct,
				this.cnxOperator);
		Failure _failure = null;
		// recup des failures de la base (id existant)
		List<Failure> _baseFailures = _failureFailureDao
				.getFailures(afterSaleReport);
		// recup des failures de l'objet placé en paramètre
		List<Failure> _objectFailures = afterSaleReport.getFailures();

		// list des failure avec une carte démontée
		List<Failure> listFailureWithCardDismantled = new ArrayList<Failure>();

		ServiceInterface serviceInterface = new ServiceInterface();
		boolean presence = false;
		for (int i = 0; i < _objectFailures.size(); i++) {
			presence = false;
			_failure = _objectFailures.get(i);
			for (int ii = 0; ii < _baseFailures.size(); ii++) {
				if (_baseFailures.get(ii).getIdFailure() == _objectFailures
						.get(i).getIdFailure()) {
					presence = true;
				}
			}
			if (presence) {
				serviceInterface.updateFailure(_objectFailures.get(i));
			} else {

				// Add failure
				System.out.println("*** failure a inserer" + _failure);
				if (_failure.getNewFailureCardChanged() != null) {

					for (Failure failureWithCardDismantled : listFailureWithCardDismantled) {
						System.out.println(failureWithCardDismantled
								.getProduct().getProductConf().getReference()
								+ " == "
								+ _failure.getProduct().getProductConf()
										.getReference()
								+ (failureWithCardDismantled.getProduct()
										.getDatecode() + " == " + _failure
										.getProduct().getDatecode()));
						System.out.println((failureWithCardDismantled
								.getProduct().getProductConf().getReference()
								.equals(_failure.getProduct().getProductConf()
										.getReference()))
								+ " && "
								+ (failureWithCardDismantled
										.getProduct()
										.getDatecode()
										.equals(_failure
												.getNewFailureCardChanged()
												.getProduct().getDatecode())
										+ " && " + (failureWithCardDismantled
										.getProduct().getSerialNumber()
										+ " == " + _failure
										.getNewFailureCardChanged()
										.getProduct().getSerialNumber())));
						if ((failureWithCardDismantled.getProduct()
								.getProductConf().getReference()
								.equals(_failure.getProduct().getProductConf()
										.getReference()))
								&& (failureWithCardDismantled
										.getProduct()
										.getDatecode()
										.equals(_failure
												.getNewFailureCardChanged()
												.getProduct().getDatecode()) && (failureWithCardDismantled
										.getProduct().getSerialNumber()
										.equals(_failure
												.getNewFailureCardChanged()
												.getProduct().getSerialNumber())))) {
							System.out
									.println("modify id of new Card Dismantle with the true id inserted in data base");
							_failure.getNewFailureCardChanged().setIdFailure(
									failureWithCardDismantled.getIdFailure());
							_failure = serviceInterface.addFailure(_failure,
									afterSaleReport);

						} else {

						}
					}

				} else {
					System.out.println("failure null");
					if (_failure.isDismantleCard()) {
						System.out
								.println(" the card is dismantled, set the object in memory failure");

						_failure = serviceInterface.addFailure(_failure,
								afterSaleReport);
						listFailureWithCardDismantled.add(_failure);
					} else {
						System.out.println("create normal failure");
						_failure = serviceInterface.addFailure(_failure,
								afterSaleReport);
					}
				}
				// serviceInterface.addFailure(_objectFailures.get(i),
				// afterSaleReport);
			}
		}
		for (int j = 0; j < _baseFailures.size(); j++) {
			presence = false;
			for (int jj = 0; jj < _objectFailures.size(); jj++) {
				if (_objectFailures.get(jj).getIdFailure() == _baseFailures
						.get(j).getIdFailure()) {
					presence = true;
				}
			}
			if (!presence) {
				serviceInterface.removeFailure(_baseFailures.get(j));
			}
		}

		/*
		 * ServiceInterface serviceInterface = new ServiceInterface();
		 * 
		 * //Ajout des failures de l'objet non présentes en base /*for (int i =
		 * 0; i < _objectFailures.size(); i++) { if
		 * (!_baseFailures.contains(_objectFailures.get(i))) {
		 * serviceInterface.addFailure(_objectFailures.get(i), afterSaleReport);
		 * } } //retrait des failures non présentes sur l'objet for (int i = 0;
		 * i < _baseFailures.size(); i++) { if
		 * (!_objectFailures.contains(_baseFailures.get(i))) {
		 * serviceInterface.removeFailure(_objectFailures.get(i)); } }
		 */
	}

	/*
	 * Mise à jour de qualityControlDate dans la bdd
	 */
	protected void updateAfterSaleReportQualityControl(
			AfterSaleReport afterSaleReport) throws SQLException,
			FailureDaoException, ElementChangedDaoException, NamingException {
		// Update qualityControlDate
		AfterSaleReportDao _afterSaleReportDao = new AfterSaleReportDaoImpl(
				this.cnxProduct, this.cnxOperator);

		try {
			this.cnxProduct.getCnx().setAutoCommit(false);
			_afterSaleReportDao
					.updateAfterSaleReportQualityControl(afterSaleReport);
			this.cnxProduct.getCnx().commit();
		} catch (AfterSaleReportDaoException e) {
			this.cnxProduct.getCnx().rollback();
			e.printStackTrace();
		}

		// Update failures
		this.updateFailures(afterSaleReport);
	}

	/*
	 * Renvoi tous les Aftersalereport sans date de validControl
	 */
	protected List<AfterSaleReport> getAfterSaleReportsValidControl()
			throws SQLException, ConfigFileReaderException, IOException,
			AfterSaleReportDaoException {
		AfterSaleReportDao _afterSaleReportDao = new AfterSaleReportDaoImpl(
				this.cnxProduct, this.cnxOperator);

		List<AfterSaleReport> _afterSaleReports = _afterSaleReportDao
				.SelectAfterSaleReportValidControl();

		return _afterSaleReports;
	}

	/*
	 * Mise à jour de qualityControlDate dans la bdd
	 */
	protected void updateAfterSaleReportExpedSAV(AfterSaleReport afterSaleReport)
			throws SQLException, FailureDaoException,
			ElementChangedDaoException, NamingException {
		// Update qualityControlDate
		AfterSaleReportDao _afterSaleReportDao = new AfterSaleReportDaoImpl(
				this.cnxProduct, this.cnxOperator);

		try {
			this.cnxProduct.getCnx().setAutoCommit(false);
			_afterSaleReportDao.updateAfterSaleReportExpedSAV(afterSaleReport);
			this.cnxProduct.getCnx().commit();
		} catch (AfterSaleReportDaoException e) {
			this.cnxProduct.getCnx().rollback();
			e.printStackTrace();
		}

		// Update failures
		this.updateFailures(afterSaleReport);
	}

	/*
	 * Renvoi tous les Aftersalereport sans date de validControl
	 */
	protected List<AfterSaleReport> getAfterSaleReportsExpedSAV()
			throws SQLException, ConfigFileReaderException, IOException,
			AfterSaleReportDaoException {
		AfterSaleReportDao _afterSaleReportDao = new AfterSaleReportDaoImpl(
				this.cnxProduct, this.cnxOperator);

		List<AfterSaleReport> _afterSaleReports = _afterSaleReportDao
				.SelectAfterSaleReportExpedSAV();

		return _afterSaleReports;
	}

	/*
	 * Renvoi toutes les donn�es pr�sente dans la table AfterSaleCom
	 */
	protected List<AfterSaleCom> getAfterSaleCom() throws SQLException,
			ConfigFileReaderException, IOException, AfterSaleReportDaoException {
		AfterSaleComDao _afterSaleComDao = new AfterSaleComImpl(
				this.cnxProduct, this.cnxOperator);

		List<AfterSaleCom> _afterSaleCom = _afterSaleComDao.listAfterSaleCom();

		return _afterSaleCom;
	}

	/*
	 * Ajout dans AfterSaleCom des donn�es du formulaire Devis Prealable
	 */
	protected void UpdateDevisPrealable(AfterSaleCom aftersalecom)
			throws Exception {
		AfterSaleComDao _afterSaleComDao = new AfterSaleComImpl(
				this.cnxProduct, this.cnxOperator);

		try {
			this.cnxProduct.getCnx().setAutoCommit(false);
			_afterSaleComDao.addDevisPrea(aftersalecom);
			this.cnxProduct.getCnx().commit();
		} catch (AfterSaleComDaoException e) {
			this.cnxProduct.getCnx().rollback();
			throw new Exception(e.getMessage());
		}
	}

	/*
	 * Renvoi les donn�es pour remplir le formulaire Devis Prealable
	 */
	protected List<AfterSaleCom> getAfterSaleDevisPrea() throws SQLException,
			ConfigFileReaderException, IOException, AfterSaleReportDaoException {
		AfterSaleComDao _afterSaleComDao = new AfterSaleComImpl(
				this.cnxProduct, this.cnxOperator);

		List<AfterSaleCom> _afterSaleReport = _afterSaleComDao.ListDevisPrea();

		return _afterSaleReport;
	}

	/*
	 * Renvoi les données pour remplir le formulaire Devis Reparation
	 */
	protected List<AfterSaleReport> getAfterSaleDevisRepa()
			throws SQLException, ConfigFileReaderException, IOException,
			AfterSaleReportDaoException {
		AfterSaleReportDao _afterSaleReportDao = new AfterSaleReportDaoImpl(
				this.cnxProduct, this.cnxOperator);

		List<AfterSaleReport> _afterSaleReport = _afterSaleReportDao
				.ListDevisRepa();

		return _afterSaleReport;
	}

	/*
	 * Renvoi le resultat de la recherche pour le formulaire numCommande.
	 */
	protected List<AfterSaleCom> rechercheNumCmd(String recherche)
			throws SQLException, ConfigFileReaderException, IOException {
		AfterSaleComDao _afterSaleComDao = new AfterSaleComImpl(
				this.cnxProduct, this.cnxOperator);

		List<AfterSaleCom> _afterSaleCom = _afterSaleComDao
				.rechercheNumCmd(recherche);

		return _afterSaleCom;
	}

	/*
	 * Ajout du Numero et de la Date de Commande.
	 */
	protected void addCmd(AfterSaleCom aftersalecom) throws SQLException,
			ConfigFileReaderException, IOException, NamingException {
		AfterSaleComDao _afterSaleComDao = new AfterSaleComImpl(
				this.cnxProduct, this.cnxOperator);

		try {
			this.cnxProduct.getCnx().setAutoCommit(false);

			_afterSaleComDao.addCmd(aftersalecom);
			this.cnxProduct.getCnx().commit();
		} catch (AfterSaleComDaoException e) {
			e.printStackTrace();
			this.cnxProduct.getCnx().rollback();
		}

	}

	/*
	 * Retourne l'aftersalecom en fonction de l'id.
	 */
	protected AfterSaleCom getAfterSaleCom(String idaftersalecom)
			throws SQLException, ConfigFileReaderException, IOException {
		AfterSaleComDao _afterSaleComDao = new AfterSaleComImpl(
				this.cnxProduct, this.cnxOperator);

		AfterSaleCom _afterSaleCom = new AfterSaleCom();

		_afterSaleCom = _afterSaleComDao.getAfterSaleCom(idaftersalecom);

		return _afterSaleCom;
	}

	/*
	 * Update l'aftersalecom en fonction de l'id.
	 */
	protected void updateAfterSaleCom(AfterSaleCom _aftersalecom)
			throws Exception {
		AfterSaleComDao _afterSaleComDao = new AfterSaleComImpl(
				this.cnxProduct, this.cnxOperator);

		try {
			this.cnxProduct.getCnx().setAutoCommit(false);
			_afterSaleComDao.updateCmd(_aftersalecom);
			this.cnxProduct.getCnx().commit();
		} catch (AfterSaleComDaoException e) {
			this.cnxProduct.getCnx().rollback();
			throw new Exception(e.getMessage());
		}

	}

	/*
	 * Retourne tous les AfterSaleCom pour les expeditions SAV.
	 */
	protected List<AfterSaleCom> getAfterSaleComExpedSAV() throws SQLException,
			ConfigFileReaderException, IOException {
		AfterSaleComDao _afterSaleComDao = new AfterSaleComImpl(
				this.cnxProduct, this.cnxOperator);

		List<AfterSaleCom> _aftersalecom = _afterSaleComDao
				.getAfterSaleComExpedSAV();

		return _aftersalecom;
	}

	/*
	 * Lazing pour RecapCom
	 */
	protected List<AfterSaleCom> getLazyRecapCom(int limit, int maxperpage)
			throws SQLException {
		AfterSaleComDao _afterSaleComDao = new AfterSaleComImpl(
				this.cnxProduct, this.cnxOperator);

		List<AfterSaleCom> _aftersalecom = _afterSaleComDao.getLazyRecapCom(
				limit, maxperpage);

		return _aftersalecom;
	}

	/*
	 * Count sur AfterSaleCom
	 */

	protected int countRecapCom() throws SQLException {
		AfterSaleComDao _afterSaleComDao = new AfterSaleComImpl(
				this.cnxProduct, this.cnxOperator);

		int count = _afterSaleComDao.countRecapCom();

		return count;

	}

	/*
	 * recupere les reparations faite entre deux dates
	 */

	protected List<AfterSaleCom> getRepairDatetoDate(Date debut, Date fin)
			throws SQLException, ConfigFileReaderException, IOException {
		AfterSaleComDao _afterSaleComDao = new AfterSaleComImpl(
				this.cnxProduct, this.cnxOperator);

		List<AfterSaleCom> _aftersalecom = _afterSaleComDao
				.getRepairDatetoDate(debut, fin);

		return _aftersalecom;

	}
}