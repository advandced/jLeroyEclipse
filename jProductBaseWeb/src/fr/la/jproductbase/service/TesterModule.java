package fr.la.jproductbase.service;

import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;

import fr.la.jproductbase.dao.ConnectionProduct;
import fr.la.jproductbase.dao.ConnectionTester;
import fr.la.jproductbase.dao.TesterDao;
import fr.la.jproductbase.dao.TesterDaoException;
import fr.la.jproductbase.dao.TesterDaoImpl;
import fr.la.jproductbase.dao.TesterReportDao;
import fr.la.jproductbase.dao.TesterReportDaoImpl;
import fr.la.jproductbase.metier.Product;
import fr.la.jproductbase.metier.TestType;
import fr.la.jproductbase.metier.Tester;
import fr.la.jproductbase.metier.TesterReport;

public class TesterModule {
	private ConnectionProduct cnxProduct;
	private ConnectionTester cnxTester;

	public TesterModule(ConnectionProduct cnxProduct, ConnectionTester cnxTester) {
		this.cnxProduct = cnxProduct;
		this.cnxTester = cnxTester;
	}

	protected Tester addTester(String name) throws SQLException,
			NamingException {
		TesterDao _testerDao = new TesterDaoImpl(this.cnxTester);

		Tester _tester = null;
		try {
			this.cnxTester.getCnx().setAutoCommit(false);
			_tester = _testerDao.addTester(name);
			this.cnxTester.getCnx().commit();
		} catch (TesterDaoException e) {
			this.cnxTester.getCnx().rollback();
			e.printStackTrace();
		}

		return _tester;
	}

	// 18-01-12 : RMO : Création de la méthode
	protected Tester addTester(String name, int state) throws SQLException,
			NamingException {
		TesterDao _testerDao = new TesterDaoImpl(this.cnxTester);

		Tester _tester = null;
		try {
			this.cnxTester.getCnx().setAutoCommit(false);
			_tester = _testerDao.addTester(name, state);
			this.cnxTester.getCnx().commit();
		} catch (TesterDaoException e) {
			this.cnxTester.getCnx().rollback();
			e.printStackTrace();
		}

		return _tester;
	}

	protected Tester getTester(String name) throws SQLException {
		TesterDao _testerDao = new TesterDaoImpl(this.cnxTester);

		Tester _tester = null;
		if (!name.trim().equals("")) {
			_tester = _testerDao.getTester(name);
		} else {
			// No tester
		}

		return _tester;
	}

	protected Tester getTester(int idTester) throws SQLException {
		TesterDao _testerDao = new TesterDaoImpl(this.cnxTester);

		Tester _tester = null;
		_tester = _testerDao.getTester(idTester);

		return _tester;
	}

	// 17-01-12 : RMO : Création de la méthode
	protected List<Tester> getTesters() throws SQLException {
		TesterDao _testerDao = new TesterDaoImpl(this.cnxTester);

		List<Tester> _testers = _testerDao.getTesters();

		return _testers;
	}

	protected List<Tester> getActiveTesters() throws SQLException {
		TesterDao _testerDao = new TesterDaoImpl(this.cnxTester);

		List<Tester> _testers = _testerDao.getActiveTesters();

		return _testers;
	}

	/**
	 * Recherche les rapports de testeurs dont le r&eacute;sultat est "Failed"
	 * et comptant dans le flux.
	 * 
	 * @return Rapports de testeurs.
	 * 
	 * @throws SQLException
	 */
	protected List<TesterReport> getTesterReportsFailedInflow()
			throws SQLException {
		TesterReportDao _testerReportDao = new TesterReportDaoImpl(
				this.cnxProduct, this.cnxTester);

		List<TesterReport> _testerReports = _testerReportDao
				.getTesterReportsFailedInflow();

		return _testerReports;
	}

	/**
	 * Recherche les rapports de testeurs devant apparaitre sur le fiche
	 * suiveuse d'un produit.
	 * 
	 * @param product
	 *            Produit.
	 * 
	 * @return Rapports de testeurs.
	 * 
	 * @throws SQLException
	 */
	protected List<TesterReport> getTesterReportsFollowingForm(Product product)
			throws SQLException {
		TesterReportDao _testerReportDao = new TesterReportDaoImpl(
				this.cnxProduct, this.cnxTester);

		List<TesterReport> _testerReports = _testerReportDao
				.getTesterReportsFollowingForm(product);

		return _testerReports;
	}

	// 18-01-12 : RMO : Création de la méthode
	protected Tester setTester(Tester tester, String name, int state)
			throws Exception {
		Tester _tester = tester;

		try {
			this.cnxTester.getCnx().setAutoCommit(false);

			if (null == _tester) {
				// New tester
				// Add
				_tester = this.addTester(name, state);
			} else {
				// Existing tester
				_tester.setState(state);
				_tester.setName(name);
				// Update
				this.updateTester(_tester);
			}

			// Commit
			this.cnxProduct.getCnx().commit();
		} catch (SQLException e) {
			this.cnxProduct.getCnx().rollback();
			throw new Exception(e.getMessage());
		}

		return _tester;
	}

	/*
	 * Mise a jour d'un tester dans la bdd
	 */
	protected void updateTester(Tester tester) throws SQLException,
			NamingException {
		TesterDao _testerDao = new TesterDaoImpl(this.cnxTester);

		try {
			this.cnxTester.getCnx().setAutoCommit(false);
			_testerDao.updateTester(tester);
			this.cnxTester.getCnx().commit();
		} catch (TesterDaoException e) {
			this.cnxTester.getCnx().rollback();
			e.printStackTrace();
		}
	}

	// 18-01-12 : RMO : Création de la méthode
	protected TestType setTestType(TestType testType, String name, int state,
			boolean needTester) throws Exception {
		TestType _testType = testType;

		try {
			this.cnxTester.getCnx().setAutoCommit(false);

			TestTypeModule _testTypeModule = new TestTypeModule(this.cnxTester);
			if (null == _testType) {
				// New testType
				// Add
				_testType = _testTypeModule
						.addTestType(name, state, needTester);
			} else {
				// Existing testTyp
				_testType.setState(state);
				_testType.setName(name);
				_testType.setNeedTester(needTester);
				// Update
				_testTypeModule.updateTestType(_testType);
			}

			// Commit
			this.cnxProduct.getCnx().commit();
		} catch (SQLException e) {
			this.cnxProduct.getCnx().rollback();
			throw new Exception(e.getMessage());
		}

		return _testType;
	}
}
