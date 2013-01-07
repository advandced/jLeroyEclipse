package fr.la.jproductbase.service;

import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;

import fr.la.jproductbase.dao.ConnectionOperator;
import fr.la.jproductbase.dao.OperatorDao;
import fr.la.jproductbase.dao.OperatorDaoException;
import fr.la.jproductbase.dao.OperatorDaoImpl;
import fr.la.jproductbase.metier.Operator;

public class OperatorModule {
	private ConnectionOperator cnxOperator;

	protected OperatorModule(ConnectionOperator cnxOperator) {
		this.cnxOperator = cnxOperator;
	}

	protected Operator getOperator(String code) throws SQLException {
		OperatorDao _operatorDao = new OperatorDaoImpl(this.cnxOperator);

		Operator _operator = _operatorDao.getOperator(code);

		return _operator;
	}

	// TODO resoudre pourquoi cette methode n'etait pas presente dans
	// operatorModule
	protected Operator getOperator(int idOperator) throws SQLException {
		OperatorDao _operatorDao = new OperatorDaoImpl(this.cnxOperator);

		Operator _operator = _operatorDao.getOperator(idOperator);

		return _operator;
	}

	protected List<Operator> getActiveOperators() throws SQLException {
		OperatorDao _operatorDao = new OperatorDaoImpl(this.cnxOperator);

		List<Operator> _operators = _operatorDao.getActiveOperators();

		return _operators;
	}

	// 13-12-11 : RMO ; Creation de la méthode
	protected List<Operator> getOperators() throws SQLException {
		OperatorDao _operatorDao = new OperatorDaoImpl(this.cnxOperator);

		List<Operator> _operators = _operatorDao.getOperators();

		return _operators;
	}

	// 12-12-11 : RMO : Creation de la méthode
	protected Operator addOperator(String firstName, String lastName,
			String code, int state) throws SQLException, NamingException {
		OperatorDao _operatorDao = new OperatorDaoImpl(this.cnxOperator);

		Operator _operator = null;
		try {
			this.cnxOperator.getCnx().setAutoCommit(false);
			_operator = _operatorDao.addOperator(firstName, lastName, code,
					state);
			this.cnxOperator.getCnx().commit();
		} catch (OperatorDaoException e) {
			this.cnxOperator.getCnx().rollback();
			e.printStackTrace();
		}

		return _operator;
	}

	// 12-12-11 : RMO : Creation de la méthode
	protected void updateOperator(Operator operatorToUpdate)
			throws SQLException, NamingException {
		OperatorDao _operatorDao = new OperatorDaoImpl(this.cnxOperator);

		try {
			this.cnxOperator.getCnx().setAutoCommit(false);
			_operatorDao.updateOperator(operatorToUpdate);
			this.cnxOperator.getCnx().commit();
		} catch (OperatorDaoException e) {
			this.cnxOperator.getCnx().rollback();
			e.printStackTrace();
		}
	}

	// 12-12-11 : RMO : Creation de la méthode
	protected void deleteOperator(Operator operatorToDelete)
			throws SQLException {
		OperatorDao _operatorDao = new OperatorDaoImpl(this.cnxOperator);

		_operatorDao.deleteOperator(operatorToDelete);
	}
}
