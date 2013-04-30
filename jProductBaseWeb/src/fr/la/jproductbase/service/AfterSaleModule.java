package fr.la.jproductbase.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import fr.la.jproductbase.dao.AfterSaleComDao;
import fr.la.jproductbase.dao.AfterSaleReportDao;
import fr.la.jproductbase.metier.AfterSaleCom;
import fr.la.jproductbase.metier.AfterSaleReport;
import fr.la.jproductbase.metier.ApparentCause;
import fr.la.jproductbase.metier.Failure;
import fr.la.jproductbase.metier.Product;

public class AfterSaleModule {

	AfterSaleReportDao _afterSaleReportDao;
	AfterSaleComDao _afterSaleComDao;
	FailureModule _failureModule;
	
	
	public AfterSaleModule(AfterSaleReportDao afterSaleReportDao, AfterSaleComDao afterSaleComDao, FailureModule failureModule) {
		_afterSaleReportDao = afterSaleReportDao;
		_afterSaleComDao = afterSaleComDao;
		_failureModule = failureModule;
	}

	public List<AfterSaleReport> getAfterSaleReports() {
		List<AfterSaleReport> _afterSaleReports = _afterSaleReportDao.listAfterSaleReport();
		return _afterSaleReports;
	}

	public List<AfterSaleReport> getAfterSaleReports(Product product) {
		List<AfterSaleReport> _afterSaleReports = _afterSaleReportDao.listAfterSaleReport(product);
		return _afterSaleReports;
	}

	public List<AfterSaleReport> getAfterSaleReports(int idproduct) {
		List<AfterSaleReport> _afterSaleReports = _afterSaleReportDao.listAfterSaleReport(idproduct);
		return _afterSaleReports;
	}

	public List<AfterSaleReport> getAfterSaleReports(Date fromDate,	Date toDate) {
		java.sql.Date _fromDate = new java.sql.Date(fromDate.getTime());
		java.sql.Date _toDate = new java.sql.Date(toDate.getTime());
		List<AfterSaleReport> _afterSaleReports = _afterSaleReportDao.listAfterSaleReport(_fromDate, _toDate);
		return _afterSaleReports;
	}

	public AfterSaleReport getAfterSaleReport(int idAfterSaleReport) {
		return _afterSaleReportDao.getAfterSaleReport(idAfterSaleReport);
	}

	public AfterSaleReport setAfterSaleReport(
			AfterSaleReport afterSaleReport, Date arrivalDate,
			String ecsNumber, String ncNature, Date firstAnalyseDate,
			Date materialInfoDate, Date reparationDate,
			Date qualityControlDate, Date expeditionDate, int functionnalTest,
			int visualControl, String asker, String intervenant,
			String InterventionSheetLink, String comment,
			List<Failure> failures, ApparentCause apparentCause,
			String majorIndexIn, String majorIndexOut, Product product) {

		AfterSaleReport _afterSaleReport = afterSaleReport;
		@SuppressWarnings("unused")
		List<Failure> _failures = new ArrayList<Failure>();
		_failures = _failureModule.updateFailures(product, failures);

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
	public AfterSaleReport addAfterSaleReport(Date arrivalDate,
			String ecsNumber, String ncNature, Date firstAnalyseDate,
			Date materialInfoDate, Date reparationDate,
			Date qualityControlDate, Date expeditionDate, int functionnalTest,
			int visualControl, String asker, String intervenant,
			String InterventionSheetLink, String comment,
			List<Failure> failures, ApparentCause apparentCause,
			String majorIndexIn, String majorIndexOut, Product product) {
		// Add afterSaleReport

		AfterSaleReport _afterSaleReport = null;

		_afterSaleReport = _afterSaleReportDao.addAfterSaleReport(
				arrivalDate, ecsNumber, ncNature, firstAnalyseDate,
				materialInfoDate, reparationDate, qualityControlDate,
				expeditionDate, functionnalTest, visualControl, asker,
				intervenant, InterventionSheetLink, comment, apparentCause,
				majorIndexIn, majorIndexOut, product);
		
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
	public void updateAfterSaleReport(AfterSaleReport afterSaleReport,
			Date arrivalDate, String ecsNumber, String ncNature,
			Date firstAnalyseDate, Date materialInfoDate, Date reparationDate,
			Date qualityControlDate, Date expeditionDate, int functionnalTest,
			int visualControl, String asker, String intervenant,
			String InterventionSheetLink, String comment,
			List<Failure> failures, ApparentCause apparentCause,
			String majorIndexIn, String majorIndexOut, Product product) {
		// Update failureReport
		_afterSaleReportDao.updateAfterSaleReport(afterSaleReport,
				arrivalDate, ecsNumber, ncNature, firstAnalyseDate,
				materialInfoDate, reparationDate, qualityControlDate,
				expeditionDate, functionnalTest, visualControl, asker,
				intervenant, InterventionSheetLink, comment, apparentCause,
				majorIndexIn, majorIndexOut, product);

		afterSaleReport.setFailures(failures);

		// Update failures
		if (null != failures) {
			this.updateFailures(afterSaleReport);
		}
	}

	/*
	 * Mise à jour des failures d'un afterSaleReport dans la bdd
	 */
	private void updateFailures(AfterSaleReport afterSaleReport) {
		Failure _failure = null;
		// recup des failures de la base (id existant)
		List<Failure> _baseFailures = _failureModule.getFailures(afterSaleReport);
		// recup des failures de l'objet placé en paramètre
		List<Failure> _objectFailures = afterSaleReport.getFailures();

		// list des failure avec une carte démontée
		List<Failure> listFailureWithCardDismantled = new ArrayList<Failure>();

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
				_failureModule.updateFailure(_objectFailures.get(i));
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
							System.out.println("modify id of new Card Dismantle with the true id inserted in data base");
							_failure.getNewFailureCardChanged().setIdFailure(failureWithCardDismantled.getIdFailure());
							_failure = _failureModule.addFailure(_failure,	afterSaleReport);

						} else {

						}
					}

				} else {
					System.out.println("failure null");
					if (_failure.isDismantleCard()) {
						System.out.println(" the card is dismantled, set the object in memory failure");

						_failure = _failureModule.addFailure(_failure,afterSaleReport);
						listFailureWithCardDismantled.add(_failure);
					} else {
						System.out.println("create normal failure");
						_failure = _failureModule.addFailure(_failure, afterSaleReport);
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
				_failureModule.removeFailure(_baseFailures.get(j));
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
	public void updateAfterSaleReportQualityControl(AfterSaleReport afterSaleReport) {
		// Update qualityControlDate
		_afterSaleReportDao.updateAfterSaleReportQualityControl(afterSaleReport);
		this.updateFailures(afterSaleReport);
	}

	/*
	 * Renvoi tous les Aftersalereport sans date de validControl
	 */
	public List<AfterSaleReport> getAfterSaleReportsValidControl() {
		List<AfterSaleReport> _afterSaleReports = _afterSaleReportDao.SelectAfterSaleReportValidControl();
		return _afterSaleReports;
	}

	/*
	 * Mise à jour de qualityControlDate dans la bdd
	 */
	public void updateAfterSaleReportExpedSAV(AfterSaleReport afterSaleReport) {
		// Update qualityControlDate
		_afterSaleReportDao.updateAfterSaleReportExpedSAV(afterSaleReport);
		// Update failures
		this.updateFailures(afterSaleReport);
	}

	/*
	 * Renvoi tous les Aftersalereport sans date de validControl
	 */
	public List<AfterSaleReport> getAfterSaleReportsExpedSAV() {
		List<AfterSaleReport> _afterSaleReports = _afterSaleReportDao.SelectAfterSaleReportExpedSAV();
		return _afterSaleReports;
	}

	/*
	 * Renvoi toutes les donn�es pr�sente dans la table AfterSaleCom
	 */
	public List<AfterSaleCom> getAfterSaleCom() {
		List<AfterSaleCom> _afterSaleCom = _afterSaleComDao.listAfterSaleCom();
		return _afterSaleCom;
	}

	/*
	 * Ajout dans AfterSaleCom des donn�es du formulaire Devis Prealable
	 */
	public void UpdateDevisPrealable(AfterSaleCom aftersalecom) {
		_afterSaleComDao.addDevisPrea(aftersalecom);
	}

	/*
	 * Renvoi les donn�es pour remplir le formulaire Devis Prealable
	 */
	public List<AfterSaleCom> getAfterSaleDevisPrea() {
		List<AfterSaleCom> _afterSaleReport = _afterSaleComDao.ListDevisPrea();
		return _afterSaleReport;
	}

	/*
	 * Renvoi les données pour remplir le formulaire Devis Reparation
	 */
	public List<AfterSaleReport> getAfterSaleDevisRepa() {
		List<AfterSaleReport> _afterSaleReport = _afterSaleReportDao.ListDevisRepa();
		return _afterSaleReport;
	}

	/*
	 * Renvoi le resultat de la recherche pour le formulaire numCommande.
	 */
	public List<AfterSaleCom> rechercheNumCmd(String recherche) {
		List<AfterSaleCom> _afterSaleCom = _afterSaleComDao.rechercheNumCmd(recherche);
		return _afterSaleCom;
	}

	/*
	 * Ajout du Numero et de la Date de Commande.
	 */
	public void addCmd(AfterSaleCom aftersalecom)  {
		_afterSaleComDao.addCmd(aftersalecom);
	}

	/*
	 * Retourne l'aftersalecom en fonction de l'id.
	 */
	public AfterSaleCom getAfterSaleCom(String idaftersalecom) {
		AfterSaleCom _afterSaleCom = new AfterSaleCom();
		_afterSaleCom = _afterSaleComDao.getAfterSaleCom(idaftersalecom);
		return _afterSaleCom;
	}

	/*
	 * Update l'aftersalecom en fonction de l'id.
	 */
	public void updateAfterSaleCom(AfterSaleCom _aftersalecom) {
		_afterSaleComDao.updateCmd(_aftersalecom);
	}

	/*
	 * Retourne tous les AfterSaleCom pour les expeditions SAV.
	 */
	public List<AfterSaleCom> getAfterSaleComExpedSAV() {
		List<AfterSaleCom> _aftersalecom = _afterSaleComDao.getAfterSaleComExpedSAV();
		return _aftersalecom;
	}

	/*
	 * Lazing pour RecapCom
	 */
	public List<AfterSaleCom> getLazyRecapCom(int limit, int maxperpage) {
		List<AfterSaleCom> _aftersalecom = _afterSaleComDao.getLazyRecapCom(limit, maxperpage);
		return _aftersalecom;
	}

	/*
	 * Count sur AfterSaleCom
	 */

	public int countRecapCom() {
		int count = _afterSaleComDao.countRecapCom();
		return count;

	}

	/*
	 * recupere les reparations faite entre deux dates
	 */

	public List<AfterSaleCom> getRepairDatetoDate(Date debut, Date fin) {
		List<AfterSaleCom> _aftersalecom = _afterSaleComDao.getRepairDatetoDate(debut, fin);
		return _aftersalecom;

	}
}