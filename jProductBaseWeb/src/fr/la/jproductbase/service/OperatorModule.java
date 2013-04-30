package fr.la.jproductbase.service;

import java.util.List;

import fr.la.jproductbase.dao.OperatorDao;
import fr.la.jproductbase.metier.Operator;

public class OperatorModule {

	OperatorDao _operatorDao;
	
	public OperatorModule(OperatorDao operatorDao) {
		_operatorDao = operatorDao;
	}

	public Operator getOperator(String code) {
		Operator _operator = _operatorDao.getOperator(code);
		return _operator;
	}

	// TODO resoudre pourquoi cette methode n'etait pas presente dans
	// operatorModule
	public Operator getOperator(int idOperator) {
		Operator _operator = _operatorDao.getOperator(idOperator);
		return _operator;
	}

	public List<Operator> getActiveOperators() {
		List<Operator> _operators = _operatorDao.getActiveOperators();
		return _operators;
	}

	// 13-12-11 : RMO ; Creation de la méthode
	public List<Operator> getOperators() {
		List<Operator> _operators = _operatorDao.getOperators();
		return _operators;
	}

	// 12-12-11 : RMO : Creation de la méthode
	public Operator addOperator(String firstName, String lastName, String code, int state) {
		return _operatorDao.addOperator(firstName, lastName, code, state);
	}

	// 12-12-11 : RMO : Creation de la méthode
	public void updateOperator(Operator operatorToUpdate) {
		_operatorDao.updateOperator(operatorToUpdate);
	}

	// 12-12-11 : RMO : Creation de la méthode
	public void deleteOperator(Operator operatorToDelete) {
		_operatorDao.deleteOperator(operatorToDelete);
	}
}
