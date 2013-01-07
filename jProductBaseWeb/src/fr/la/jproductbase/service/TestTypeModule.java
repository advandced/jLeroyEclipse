package fr.la.jproductbase.service;

import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;

import fr.la.jproductbase.dao.ConnectionTester;
import fr.la.jproductbase.dao.TestTypeDao;
import fr.la.jproductbase.dao.TestTypeDaoException;
import fr.la.jproductbase.dao.TestTypeDaoImpl;
import fr.la.jproductbase.metier.LabviewTestType;
import fr.la.jproductbase.metier.TestType;

public class TestTypeModule {
	private ConnectionTester cnxTester;

	public TestTypeModule(ConnectionTester cnxTester) {
		this.cnxTester = cnxTester;
	}

	// 18-01-12 : RMO : Création de la méthode
	protected TestType addTestType(String name, int state, boolean needTester)
			throws SQLException, NamingException {
		TestTypeDao _testTypeDao = new TestTypeDaoImpl(this.cnxTester);

		TestType _testType = null;
		try {
			this.cnxTester.getCnx().setAutoCommit(false);
			_testType = _testTypeDao.addTestType(name, state, needTester);
			this.cnxTester.getCnx().commit();
		} catch (TestTypeDaoException e) {
			this.cnxTester.getCnx().rollback();
			e.printStackTrace();
		}

		return _testType;
	}

	protected TestType getTestType(int idTestType) throws SQLException {
		TestTypeDao _testTypeDao = new TestTypeDaoImpl(this.cnxTester);

		TestType _testType = _testTypeDao.getTestType(idTestType);

		return _testType;
	}

	protected TestType getTestType(String name) throws SQLException {
		TestTypeDao _testTypeDao = new TestTypeDaoImpl(this.cnxTester);

		TestType _testType = _testTypeDao.getTestType(name);

		return _testType;
	}

	protected List<TestType> getTestTypes() throws SQLException {
		TestTypeDao _testTypeDao = new TestTypeDaoImpl(this.cnxTester);

		List<TestType> _testTypes = _testTypeDao.getTestTypes();

		return _testTypes;
	}

	protected TestType retreiveTestType(LabviewTestType labviewTestType)
			throws SQLException {
		TestType _testType = this.getTestType(labviewTestType.getName());

		return _testType;
	}

	protected void updateTestType(TestType testType) throws SQLException,
			NamingException {
		TestTypeDao _testTypeDao = new TestTypeDaoImpl(this.cnxTester);

		try {
			this.cnxTester.getCnx().setAutoCommit(false);
			_testTypeDao.updateTestType(testType);
			this.cnxTester.getCnx().commit();
		} catch (TestTypeDaoException e) {
			this.cnxTester.getCnx().rollback();
			e.printStackTrace();
		}
	}
}
