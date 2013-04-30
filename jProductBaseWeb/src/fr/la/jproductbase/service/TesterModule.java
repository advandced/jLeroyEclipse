package fr.la.jproductbase.service;

import java.sql.SQLException;
import java.util.List;

import fr.la.jproductbase.dao.TesterDao;
import fr.la.jproductbase.dao.TesterReportDao;
import fr.la.jproductbase.metier.Product;
import fr.la.jproductbase.metier.TestType;
import fr.la.jproductbase.metier.Tester;
import fr.la.jproductbase.metier.TesterReport;

public class TesterModule {

	TesterDao _testerDao;
	TesterReportDao _testerReportDao;
	
	TestTypeModule _testTypeModule;
	
	public TesterModule(TesterDao testerDao, TesterReportDao testerReportDao, TestTypeModule testTypeModule) {
		_testerDao = testerDao;
		_testerReportDao = testerReportDao;
		
		_testTypeModule = testTypeModule;
	}

	public Tester addTester(String name) {
		return _testerDao.addTester(name);
	}

	// 18-01-12 : RMO : Création de la méthode
	public Tester addTester(String name, int state) {
		return _testerDao.addTester(name, state);
	}

	public Tester getTester(String name) {
		Tester _tester = null;
		if (!name.trim().equals("")) {
			_tester = _testerDao.getTester(name);
		}
		return _tester;
	}

	public Tester getTester(int idTester) {
		return _testerDao.getTester(idTester);
	}

	// 17-01-12 : RMO : Création de la méthode
	public List<Tester> getTesters() {
		return _testerDao.getTesters();
	}

	public List<Tester> getActiveTesters() {
		return _testerDao.getActiveTesters();
	}

	/**
	 * Recherche les rapports de testeurs dont le r&eacute;sultat est "Failed"
	 * et comptant dans le flux.
	 * 
	 * @return Rapports de testeurs.
	 * 
	 * @throws SQLException
	 */
	public List<TesterReport> getTesterReportsFailedInflow() {
		return _testerReportDao.getTesterReportsFailedInflow();
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
	public List<TesterReport> getTesterReportsFollowingForm(Product product) {
		return _testerReportDao.getTesterReportsFollowingForm(product);
	}

	// 18-01-12 : RMO : Création de la méthode
	public Tester setTester(Tester tester, String name, int state) {
		Tester _tester = tester;

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

		return _tester;
	}

	/*
	 * Mise a jour d'un tester dans la bdd
	 */
	public void updateTester(Tester tester) {
		_testerDao.updateTester(tester);
	}

	// 18-01-12 : RMO : Création de la méthode
	public TestType setTestType(TestType testType, String name, int state, boolean needTester) {
		TestType _testType = testType;

		if (null == _testType) {
			// New testType
			// Add
			_testType = _testTypeModule.addTestType(name, state, needTester);
		} else {
			// Existing testTyp
			_testType.setState(state);
			_testType.setName(name);
			_testType.setNeedTester(needTester);
			// Update
			_testTypeModule.updateTestType(_testType);
		}

		return _testType;
	}
}
