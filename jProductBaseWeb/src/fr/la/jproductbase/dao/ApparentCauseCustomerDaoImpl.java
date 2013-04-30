package fr.la.jproductbase.dao;

import fr.la.jproductbase.metier.ApparentCauseCustomer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class ApparentCauseCustomerDaoImpl extends GenericDao implements ApparentCauseCustomerDao {

	ConnectionProduct cnxProduct;

	public ApparentCauseCustomerDaoImpl(ConnectionProduct cnxProduct) {
		this.cnxProduct = cnxProduct;
	}

	@Override
	public List<ApparentCauseCustomer> getActiveApparentCausesCustomer() {
		List<ApparentCauseCustomer> _apparentCausesCustomer = new ArrayList<ApparentCauseCustomer>();
		Connection c = null;
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			c = cnxProduct.getCnx();
			_stmt = c.prepareStatement(
					"SELECT * FROM apparentCauseCustomer " +
						" WHERE (state=1)");
			_rs = _stmt.executeQuery();

			while (_rs.next()) {
				ApparentCauseCustomer _apparentCauseCustomer = this.getApparentCauseCustomer(_rs);
				_apparentCausesCustomer.add(_apparentCauseCustomer);
			}
		} catch (SQLException e) {
			handleDAOException(e);
		} finally {
			close(_rs);
			close(_stmt);
			close(c);
		}

		return _apparentCausesCustomer;
	}
	
	@Override
	public List<ApparentCauseCustomer> getApparentCausesCustomer() {
		List<ApparentCauseCustomer> _apparentCausesCustomer = new ArrayList<ApparentCauseCustomer>();
		Connection c = null;
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			c = cnxProduct.getCnx();
			_stmt = c.prepareStatement(
					"SELECT * FROM apparentCauseCustomer ");
			_rs = _stmt.executeQuery();

			while (_rs.next()) {
				ApparentCauseCustomer _apparentCauseCustomer = this.getApparentCauseCustomer(_rs);
				_apparentCausesCustomer.add(_apparentCauseCustomer);
			}
		} catch (SQLException e) {
			handleDAOException(e);
		} finally {
			close(_rs);
			close(_stmt);
			close(c);
		}

		return _apparentCausesCustomer;
	}

	@Override
	public ApparentCauseCustomer getApparentCauseCustomer(int idApparentCauseCustomer) {
		ApparentCauseCustomer _apparentCauseCustomer = null;
		Connection c = null;
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			c = cnxProduct.getCnx();
			_stmt = c.prepareStatement(
					"SELECT * FROM apparentCauseCustomer WHERE idApparentCauseCustomer=?");
			_stmt.setInt(1, idApparentCauseCustomer);
			_rs = _stmt.executeQuery();

			if (_rs.next()) {
				_apparentCauseCustomer = this.getApparentCauseCustomer(_rs);
			} else {
				_apparentCauseCustomer = null;
			}
		} catch (SQLException e) {
			handleDAOException(e);
		} finally {
			close(_rs);
			close(_stmt);
			close(c);
		}

		return _apparentCauseCustomer;
	}

	@Override
	public ApparentCauseCustomer addApparentCauseCustomer(ApparentCauseCustomer apparentCauseCustomer) {
		ApparentCauseCustomer _apparentCauseCustomer = apparentCauseCustomer;
		Connection c = null;
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			c = cnxProduct.getCnx();
			_stmt = c.prepareStatement(
							"INSERT INTO apparentCauseCustomer "
									+ "(state, description) "
									+ "VALUES (?, ?)");
			_stmt.setInt(1, apparentCauseCustomer.getState());
			_stmt.setString(2, apparentCauseCustomer.getDescription());
			_stmt.executeUpdate();

			_stmt = c.prepareStatement(
							"SELECT * FROM apparentCauseCustomer "
									+ "WHERE (state=?) "
									+ " AND (description=?)");
			_stmt.setInt(1, apparentCauseCustomer.getState());
			_stmt.setString(2, apparentCauseCustomer.getDescription());

			_rs = _stmt.executeQuery();
			if (_rs.next()) {
				_apparentCauseCustomer = this.getApparentCauseCustomer(_rs);
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

		return _apparentCauseCustomer;
	}

	@Override
	public void updateApparentCauseCustomer(ApparentCauseCustomer apparentCauseCustomer) {
		Connection c = null;
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			c = cnxProduct.getCnx();
			_stmt = c.prepareStatement(
							"UPDATE apparentCauseCustomer "
									+ "SET state=?, description=?"
									+ " WHERE (idApparentCauseCustomer=?)");
			_stmt.setInt(1, apparentCauseCustomer.getState());
			_stmt.setString(2, apparentCauseCustomer.getDescription());
			_stmt.setInt(3, apparentCauseCustomer.getIdApparentCauseCustomer());
			_stmt.executeUpdate();

			// Update object
			_stmt = c.prepareStatement(
					"SELECT * FROM apparentCauseCustomer" + " WHERE (idApparentCauseCustomer=?)");
			_stmt.setInt(1, apparentCauseCustomer.getIdApparentCauseCustomer());

			_rs = _stmt.executeQuery();
			if (_rs.next()) {
				this.getApparentCauseCustomer(_rs);
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
	}

	@Override
	public void removeApparentCauseCustomer(ApparentCauseCustomer apparentCauseCustomer) {
		Connection c = null;
		PreparedStatement _stmt = null;

		try {
			c = cnxProduct.getCnx();
			_stmt = c.prepareStatement(
					"DELETE FROM apparentCauseCustomer " + "WHERE (idApparentCauseCustomer=?)");
			_stmt.setInt(1, apparentCauseCustomer.getIdApparentCauseCustomer());
			_stmt.executeUpdate();
		} catch (SQLException e) {
			handleDAOException(e);
		} finally {
			close(_stmt);
			close(c);
		}
	}

	/*
	 * Cr&eacute;er une cause probable client &agrave; partir d'un enregistrement de la
	 * base de donn&eacute;es.
	 * 
	 * @param rs Enregistrement de la base de donn&eacute;es.
	 * 
	 * @return Cause probable client.
	 * 
	 * @throws SQLException
	 */
	private ApparentCauseCustomer getApparentCauseCustomer(ResultSet rs) throws SQLException {

		// ApparentCauseCustomer
		int _idApparentCauseCustomer = rs.getInt("idApparentCauseCustomer");
		Timestamp _timestamp = rs.getTimestamp("timestamp");
		int _state = rs.getInt("state");
		String _description = rs.getString("description");

		ApparentCauseCustomer _apparentCauseCustomer = new ApparentCauseCustomer(_idApparentCauseCustomer, _timestamp, _state,
				_description);

		return _apparentCauseCustomer;
	}
}
