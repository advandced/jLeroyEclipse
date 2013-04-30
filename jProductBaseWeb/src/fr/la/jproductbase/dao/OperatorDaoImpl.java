package fr.la.jproductbase.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import fr.la.jproductbase.metier.Operator;

public class OperatorDaoImpl extends GenericDao implements OperatorDao {

	ConnectionOperator cnxOperator;

	public OperatorDaoImpl(ConnectionOperator cnxOperator) {
		this.cnxOperator = cnxOperator;
	}

	@Override
	public Operator getOperator(int idOperator) {
		Operator _operator = null;
		Connection c = null;
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			c = this.cnxOperator.getCnx();
			_stmt = c.prepareStatement("SELECT * FROM operator WHERE idOperator=?");

			_stmt.setInt(1, idOperator);
			_rs = _stmt.executeQuery();

			if (_rs.next()) {
				_operator = this.getOperator(_rs);
			} else {
				_operator = null;
			}
		} catch (SQLException e) {
			handleDAOException(e);
		} finally {
			close(_rs);
			close(_stmt);
			close(c);
		}

		return _operator;
	}

	@Override
	public Operator getOperator(String code) {
		Operator _operator = null;
		Connection c = null;
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			c = this.cnxOperator.getCnx();
			_stmt = c.prepareStatement("SELECT * FROM operator WHERE code=?");
			_stmt.setString(1, code);
			_rs = _stmt.executeQuery();

			if (_rs.next()) {
				_operator = this.getOperator(_rs);
			} else {
				_operator = null;
			}
		} catch (SQLException e) {
			handleDAOException(e);
		} finally {
			close(_rs);
			close(_stmt);
			close(c);
		}

		return _operator;
	}

	@Override
	public List<Operator> getActiveOperators() {
		List<Operator> _operators = new ArrayList<Operator>();
		Connection c = null;
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			c = this.cnxOperator.getCnx();
			_stmt = c.prepareStatement("SELECT * FROM operator" + " WHERE (state=1)");
			_rs = _stmt.executeQuery();

			while (_rs.next()) {
				Operator _operator = this.getOperator(_rs);
				_operators.add(_operator);
			}
		} catch (SQLException e) {
			handleDAOException(e);
		} finally {
			close(_rs);
			close(_stmt);
			close(c);
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
	public Operator addOperator(String firstName, String lastName, String code,	int state) {
		Operator _operator = null;
		Connection c = null;
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			c = this.cnxOperator.getCnx();
			_stmt = c.prepareStatement(
					"INSERT INTO operator (firstName, lastName, code, state)"
							+ " VALUES (?, ?, ?, ?)");
			_stmt.setString(1, firstName);
			_stmt.setString(2, lastName);
			_stmt.setString(3, code);
			_stmt.setInt(4, state);
			_stmt.executeUpdate();

			// Retrieve operator data
			_stmt = c.prepareStatement(
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
				throw new IllegalStateException();
			}
		} catch (SQLException e) {
			handleDAOException(e);
		} finally {
			close(_rs);
			close(_stmt);
			close(c);
		}

		return _operator;
	}

	@Override
	public void updateOperator(Operator operatorToUpdate)  {
		Connection c = null;
		PreparedStatement _stmt = null;

		try {
			c = this.cnxOperator.getCnx();
			_stmt = c.prepareStatement(
					"UPDATE operator "
							+ "SET firstName=?, lastName=?, code=?, state=?"
							+ " WHERE (idOperator=?)");
			_stmt.setString(1, operatorToUpdate.getFirstName());
			_stmt.setString(2, operatorToUpdate.getLastName());
			_stmt.setString(3, operatorToUpdate.getCode());
			_stmt.setInt(4, operatorToUpdate.getState());
			_stmt.setInt(5, operatorToUpdate.getIdOperator());
			_stmt.executeUpdate();
			
			_stmt = c.prepareStatement(
					"SELECT state, code, lastName, firstName FROM operator" 
							+ " WHERE (idOperator=?)");
			_stmt.setInt(1, operatorToUpdate.getIdOperator());

			ResultSet _rs = _stmt.executeQuery();
			if (_rs.next()) {
			
			} else {
				throw new IllegalStateException();
			}
		} catch (SQLException e) {
			handleDAOException(e);
		} finally {
			close(_stmt);
			close(c);
		}
	}

	@Override
	public void deleteOperator(Operator operatorToDelete) {
		Connection c = null;
		PreparedStatement _stmt = null;

		try {
			c = this.cnxOperator.getCnx();
			_stmt = c.prepareStatement("DELETE FROM operator WHERE (idOperator=?)");
			_stmt.setInt(1, operatorToDelete.getIdOperator());
			_stmt.executeUpdate();
		} catch (SQLException e) {
			handleDAOException(e);
		} finally {
			close(_stmt);
			close(c);
		}
	}

	@Override
	public List<Operator> getOperators() {
		List<Operator> _operators = new ArrayList<Operator>();
		Connection c = null;
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			c = this.cnxOperator.getCnx();
			_stmt = c.prepareStatement("SELECT * FROM operator");
			_rs = _stmt.executeQuery();

			while (_rs.next()) {
				Operator _operator = this.getOperator(_rs);
				_operators.add(_operator);
			}
		} catch (SQLException e) {
			handleDAOException(e);
		} finally {
			close(_rs);
			close(_stmt);
			close(c);
		}

		return _operators;
	}
}
