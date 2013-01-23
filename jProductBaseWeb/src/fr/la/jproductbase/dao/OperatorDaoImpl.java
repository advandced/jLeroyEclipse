package fr.la.jproductbase.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import fr.la.jproductbase.metier.Operator;

public class OperatorDaoImpl implements OperatorDao {
	private static String exceptionMsg = "Opérateur inconnu dans la base de données.";

	private ConnectionOperator cnxOperator;

	public OperatorDaoImpl(ConnectionOperator cnxOperator) {
		this.cnxOperator = cnxOperator;
	}

	@Override
	public Operator getOperator(int idOperator) throws SQLException {
		Operator _operator = null;
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			try {
				_stmt = this.cnxOperator.getCnx().prepareStatement(
						"SELECT * FROM operator WHERE idOperator=?");
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			_stmt.setInt(1, idOperator);
			_rs = _stmt.executeQuery();

			if (_rs.next()) {
				_operator = this.getOperator(_rs);
			} else {
				_operator = null;
			}
		} finally {
			if (null != _rs) {
				_rs.close();
			}
			if (null != _stmt) {
				_stmt.close();
			}
		}

		return _operator;
	}

	@Override
	public Operator getOperator(String code) throws SQLException {
		Operator _operator = null;
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			_stmt = this.cnxOperator.getCnx().prepareStatement(
					"SELECT * FROM operator WHERE code=?");
			_stmt.setString(1, code);
			_rs = _stmt.executeQuery();

			if (_rs.next()) {
				_operator = this.getOperator(_rs);
			} else {
				_operator = null;
			}
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (null != _rs) {
				_rs.close();
			}
			if (null != _stmt) {
				_stmt.close();
			}
		}

		return _operator;
	}

	@Override
	public List<Operator> getActiveOperators() throws SQLException {
		List<Operator> _operators = new ArrayList<Operator>();
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			_stmt = this.cnxOperator.getCnx().prepareStatement(
					"SELECT * FROM operator" + " WHERE (state=1)");
			_rs = _stmt.executeQuery();

			while (_rs.next()) {
				Operator _operator = this.getOperator(_rs);
				_operators.add(_operator);
			}
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (null != _rs) {
				_rs.close();
			}
			if (null != _stmt) {
				_stmt.close();
			}
		}

		return _operators;
	}

	/*
	 * Cr&eacute;er un op&eacute;rateur &agrave; partir d'un enregistement de la
	 * base de donn&eacute;es.
	 * 
	 * @param rs Enregistrement de la base de donn&eacute;es.
	 * 
	 * @return Op&eacute;rateur.
	 */
	private Operator getOperator(ResultSet rs) throws SQLException {
		int _idOperator = rs.getInt("idOperator");
		Timestamp _timestamp = rs.getTimestamp("timestamp");
		int _state = rs.getInt("state");
		String _code = rs.getString("code");
		String _lastName = rs.getString("lastName");
		String _firstName = rs.getString("firstName");
		Operator _operator = new Operator(_idOperator, _timestamp, _state,
				_code, _lastName, _firstName);

		return _operator;
	}

	@Override
	public Operator addOperator(String firstName, String lastName, String code,
			int state) throws SQLException, OperatorDaoException {
		Operator _operator = null;
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			_stmt = this.cnxOperator.getCnx().prepareStatement(
					"INSERT INTO operator (firstName, lastName, code, state)"
							+ " VALUES (?, ?, ?, ?)");
			_stmt.setString(1, firstName);
			_stmt.setString(2, lastName);
			_stmt.setString(3, code);
			_stmt.setInt(4, state);
			_stmt.executeUpdate();

			// Retrieve operator data
			_stmt = this.cnxOperator.getCnx().prepareStatement(
					"SELECT * FROM operator" + " WHERE (firstName=?)"
							+ " 	AND (lastName=?)" + " 	AND (code=?)"
							+ " 	AND (state=?)");
			_stmt.setString(1, firstName);
			_stmt.setString(2, lastName);
			_stmt.setString(3, code);
			_stmt.setInt(4, state);

			_rs = _stmt.executeQuery();
			if (_rs.next()) {
				_operator = this.getOperator(_rs);
			} else {
				throw new OperatorDaoException(exceptionMsg);
			}
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (null != _rs) {
				_rs.close();
			}
			if (null != _stmt) {
				_stmt.close();
			}
		}

		return _operator;
	}

	@Override
	public void updateOperator(Operator operatorToUpdate) throws SQLException,
			OperatorDaoException {
		PreparedStatement _stmt = null;

		try {
			_stmt = this.cnxOperator.getCnx().prepareStatement(
					"UPDATE operator "
							+ "SET firstName=?, lastName=?, code=?, state=?"
							+ " WHERE (idOperator=?)");
			_stmt.setString(1, operatorToUpdate.getFirstName());
			_stmt.setString(2, operatorToUpdate.getLastName());
			_stmt.setString(3, operatorToUpdate.getCode());
			_stmt.setInt(4, operatorToUpdate.getState());
			_stmt.setInt(5, operatorToUpdate.getIdOperator());
			_stmt.executeUpdate();
			
			_stmt = this.cnxOperator.getCnx().prepareStatement(
					"SELECT state, code, lastName, firstName FROM operator" 
							+ " WHERE (idOperator=?)");
			_stmt.setInt(1, operatorToUpdate.getIdOperator());

			System.out.println(operatorToUpdate.getIdOperator());
			
			ResultSet _rs = _stmt.executeQuery();
			if (_rs.next()) {
			
			} else {
				throw new OperatorDaoException(exceptionMsg);
			}
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (null != _stmt) {
				_stmt.close();
			}
		}
	}

	@Override
	public void deleteOperator(Operator operatorToDelete) throws SQLException {
		PreparedStatement _stmt = null;

		try {
			_stmt = this.cnxOperator.getCnx().prepareStatement(
					"DELETE FROM operator " + " WHERE (idOperator=?)");
			_stmt.setInt(1, operatorToDelete.getIdOperator());
			_stmt.executeUpdate();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (null != _stmt) {
				_stmt.close();
			}
		}
	}

	@Override
	public List<Operator> getOperators() throws SQLException {
		List<Operator> _operators = new ArrayList<Operator>();
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			_stmt = this.cnxOperator.getCnx().prepareStatement(
					"SELECT * FROM operator");
			_rs = _stmt.executeQuery();

			while (_rs.next()) {
				Operator _operator = this.getOperator(_rs);
				_operators.add(_operator);
			}
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (null != _rs) {
				_rs.close();
			}
			if (null != _stmt) {
				_stmt.close();
			}
		}

		return _operators;
	}
}
