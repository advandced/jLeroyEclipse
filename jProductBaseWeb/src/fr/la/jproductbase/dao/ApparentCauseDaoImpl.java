package fr.la.jproductbase.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import fr.la.jproductbase.metier.ApparentCause;
import fr.la.jproductbase.metier.ApparentCauseCustomer;

public class ApparentCauseDaoImpl extends GenericDao implements ApparentCauseDao {

	ConnectionProduct cnxProduct;
	
	ApparentCauseCustomerDao _apparentCauseCustomerDao;

	public ApparentCauseDaoImpl(ConnectionProduct cnxProduct, ApparentCauseCustomerDao apparentCauseCustomerDao) {
		this.cnxProduct = cnxProduct;
		_apparentCauseCustomerDao = apparentCauseCustomerDao;
	}

	@Override
	public List<ApparentCause> getApparentCauses() {
		List<ApparentCause> _apparentCauses = new ArrayList<ApparentCause>();
		Connection c = null;
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			c = cnxProduct.getCnx();
			_stmt = c.prepareStatement("SELECT * FROM apparentCause ORDER BY description;");
			_rs = _stmt.executeQuery();

			while (_rs.next()) {
				ApparentCause _apparentCause = this.getApparentCause(_rs);
				_apparentCauses.add(_apparentCause);
			}
		} catch (SQLException e) {
			handleDAOException(e);
		} finally {
			close(_rs);
			close(_stmt);
			close(c);
		}

		return _apparentCauses;
	}

	@Override
	public ApparentCause getApparentCause(int idApparentCause) {
		ApparentCause _apparentCause = null;
		Connection c = null;
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			c = cnxProduct.getCnx();
			_stmt = c.prepareStatement("SELECT * FROM apparentCause WHERE idApparentCause=?");
			_stmt.setInt(1, idApparentCause);
			_rs = _stmt.executeQuery();

			if (_rs.next()) {
				_apparentCause = this.getApparentCause(_rs);
			} else {
				_apparentCause = null;
			}

		} catch (SQLException e) {
			handleDAOException(e);
		} finally {
			close(_rs);
			close(_stmt);
			close(c);
		}

		return _apparentCause;
	}

	@Override
	public ApparentCause addApparentCause(ApparentCause apparentCause) {
		ApparentCause _apparentCause = apparentCause;
		Connection c = null;
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			c = cnxProduct.getCnx();
			_stmt = c.prepareStatement(
					"INSERT INTO apparentCause " + "(state, description, idApparentCauseCustomer) "
							+ "VALUES (?, ?, ?)");
			_stmt.setInt(1, apparentCause.getState());
			_stmt.setString(2, apparentCause.getDescription());
			_stmt.setInt(3, apparentCause.getApparentCauseCustomer()
					.getIdApparentCauseCustomer());
			_stmt.executeUpdate();

			_stmt = c.prepareStatement(
					"SELECT * FROM apparentCause " + "WHERE (state=?) "
							+ " AND (description=?) AND (idApparentCauseCustomer=?)");
			_stmt.setInt(1, apparentCause.getState());
			_stmt.setString(2, apparentCause.getDescription());
			_stmt.setInt(3, apparentCause.getApparentCauseCustomer().getIdApparentCauseCustomer());

			_rs = _stmt.executeQuery();
			if (_rs.next()) {
				_apparentCause = this.getApparentCause(_rs);
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

		return _apparentCause;
	}

	@Override
	public void updateApparentCause(ApparentCause apparentCause) {
		Connection c = null;
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			c = cnxProduct.getCnx();
			_stmt = c.prepareStatement(
					"UPDATE apparentCause " + "SET state=?, description=?, idApparentCauseCustomer=? "
							+ " WHERE (idApparentCause=?)");
			System.out.println("dao updateApparentCause"+apparentCause.getApparentCauseCustomer().getIdApparentCauseCustomer()+" "+apparentCause.getIdApparentCause());
			_stmt.setInt(1, apparentCause.getState());
			_stmt.setString(2, apparentCause.getDescription());
			_stmt.setInt(3, apparentCause.getApparentCauseCustomer().getIdApparentCauseCustomer());
			_stmt.setInt(4, apparentCause.getIdApparentCause());
			_stmt.executeUpdate();

			// Update object
			_stmt = c.prepareStatement(
					"SELECT * FROM apparentCause"
							+ " WHERE (idApparentCause=?)");
			_stmt.setInt(1, apparentCause.getIdApparentCause());

			_rs = _stmt.executeQuery();
			if (_rs.next()) {
				this.getApparentCause(_rs);
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
	public void removeApparentCause(ApparentCause apparentCause) {
		Connection c = null;
		PreparedStatement _stmt = null;

		try {
			c = cnxProduct.getCnx();
			_stmt = c.prepareStatement("DELETE FROM apparentCause " + "WHERE (idApparentCause=?)");
			_stmt.setInt(1, apparentCause.getIdApparentCause());
			_stmt.executeUpdate();

		} catch (SQLException e) {
			handleDAOException(e);
		} finally {
			close(_stmt);
			close(c);
		}
	}

	/*
	 * Cr&eacute;er une cause probable &agrave; partir d'un enregistrement de la
	 * base de donn&eacute;es.
	 * 
	 * @param rs Enregistrement de la base de donn&eacute;es.
	 * 
	 * @return Cause probable.
	 * 
	 * @throws SQLException
	 */
	private ApparentCause getApparentCause(ResultSet rs) throws SQLException {

		// ApparentCause
		int _idApparentCause = rs.getInt("idApparentCause");
		Timestamp _timestamp = rs.getTimestamp("timestamp");
		int _state = rs.getInt("state");
		String _description = rs.getString("description");
		// Retreive ApparentCauseCustomer
		int _idApparentCauseCustomer = rs.getInt("idApparentCauseCustomer");
		ApparentCauseCustomer _apparentCauseCustomer = _apparentCauseCustomerDao.getApparentCauseCustomer(_idApparentCauseCustomer);
		ApparentCause _apparentCause = new ApparentCause(_idApparentCause,_timestamp, _state, _description, _apparentCauseCustomer);
		return _apparentCause;
	}
}
