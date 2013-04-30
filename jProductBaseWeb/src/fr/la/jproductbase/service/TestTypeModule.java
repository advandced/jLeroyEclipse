package fr.la.jproductbase.service;

import java.util.List;

import fr.la.jproductbase.dao.TestTypeDao;
import fr.la.jproductbase.metier.LabviewTestType;
import fr.la.jproductbase.metier.TestType;

public class TestTypeModule {

	TestTypeDao _testTypeDao;
	
	public TestTypeModule(TestTypeDao testTypeDao) {
		_testTypeDao = testTypeDao;
	}

	// 18-01-12 : RMO : Création de la méthode
	public TestType addTestType(String name, int state, boolean needTester) {
		return _testTypeDao.addTestType(name, state, needTester);
	}

	public TestType getTestType(int idTestType) {
		return _testTypeDao.getTestType(idTestType);
	}

	public TestType getTestType(String name) {
		return _testTypeDao.getTestType(name);
	}

	public List<TestType> getTestTypes() {
		return _testTypeDao.getTestTypes();
	}

	public TestType retreiveTestType(LabviewTestType labviewTestType) {
		return this.getTestType(labviewTestType.getName());
	}

	public void updateTestType(TestType testType) {
		_testTypeDao.updateTestType(testType);
	}
}
