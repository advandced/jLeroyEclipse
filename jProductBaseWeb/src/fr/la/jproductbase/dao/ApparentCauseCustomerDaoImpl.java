package fr.la.jproductbase.dao;

import fr.la.jproductbase.metier.ApparentCauseCustomer;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;

public class ApparentCauseCustomerDaoImpl implements ApparentCauseCustomerDao {
	private static String exceptionMsg = "Cause probable client inconnu dans la base de données.";

	private ConnectionProduct cnxProduct;

	public ApparentCauseCustomerDaoImpl(ConnectionProduct cnxProduct) {
		this.cnxProduct = cnxProduct;
	}

	@Override
	public List<ApparentCauseCustomer> getActiveApparentCausesCustomer()
			throws SQLException {
		List<ApparentCauseCustomer> _apparentCausesCustomer = new ArrayList<ApparentCauseCustomer>();
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			_stmt = this.cnxProduct.getCnx().prepareStatement(
					"SELECT * FROM apparentCauseCustomer " +
						" WHERE (state=1)");
			_rs = _stmt.executeQuery();

			while (_rs.next()) {
				ApparentCauseCustomer _apparentCauseCustomer = this.getApparentCauseCustomer(_rs);
				_apparentCausesCustomer.add(_apparentCauseCustomer);
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

		return _apparentCausesCustomer;
	}
	
	@Override
	public List<ApparentCauseCustomer> getApparentCausesCustomer()
			throws SQLException {
		List<ApparentCauseCustomer> _apparentCausesCustomer = new ArrayList<ApparentCauseCustomer>();
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			_stmt = this.cnxProduct.getCnx().prepareStatement(
					"SELECT * FROM apparentCauseCustomer ");
			_rs = _stmt.executeQuery();

			while (_rs.next()) {
				ApparentCauseCustomer _apparentCauseCustomer = this.getApparentCauseCustomer(_rs);
				_apparentCausesCustomer.add(_apparentCauseCustomer);
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

		return _apparentCausesCustomer;
	}

	@Override
	public ApparentCauseCustomer getApparentCauseCustomer(int idApparentCauseCustomer) throws SQLException {
		ApparentCauseCustomer _apparentCauseCustomer = null;
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			_stmt = this.cnxProduct.getCnx().prepareStatement(
					"SELECT * FROM apparentCauseCustomer WHERE idApparentCauseCustomer=?");
			_stmt.setInt(1, idApparentCauseCustomer);
			_rs = _stmt.executeQuery();

			if (_rs.next()) {
				_apparentCauseCustomer = this.getApparentCauseCustomer(_rs);
			} else {
				_apparentCauseCustomer = null;
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

		return _apparentCauseCustomer;
	}

	@Override
	public ApparentCauseCustomer addApparentCauseCustomer(ApparentCauseCustomer apparentCauseCustomer)
			throws SQLException, ApparentCauseDaoException {
		ApparentCauseCustomer _apparentCauseCustomer = apparentCauseCustomer;
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			_stmt = this.cnxProduct
					.getCnx()
					.prepareStatement(
							"INSERT INTO apparentCauseCustomer "
									+ "(state, description) "
									+ "VALUES (?, ?)");
			_stmt.setInt(1, apparentCauseCustomer.getState());
			_stmt.setString(2, apparentCauseCustomer.getDescription());
			_stmt.executeUpdate();

			_stmt = this.cnxProduct.getCnx()
					.prepareStatement(
							"SELECT * FROM apparentCauseCustomer "
									+ "WHERE (state=?) "
									+ " AND (description=?)");
			_stmt.setInt(1, apparentCauseCustomer.getState());
			_stmt.setString(2, apparentCauseCustomer.getDescription());

			_rs = _stmt.executeQuery();
			if (_rs.next()) {
				_apparentCauseCustomer = this.getApparentCauseCustomer(_rs);
			} else {
				throw new ApparentCauseDaoException(exceptionMsg);
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

		return _apparentCauseCustomer;
	}

	@Override
	public void updateApparentCauseCustomer(ApparentCauseCustomer apparentCauseCustomer)
			throws SQLException, ApparentCauseDaoException {
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			_stmt = this.cnxProduct
					.getCnx()
					.prepareStatement(
							"UPDATE apparentCauseCustomer "
									+ "SET state=?, description=?"
									+ " WHERE (idApparentCauseCustomer=?)");
			_stmt.setInt(1, apparentCauseCustomer.getState());
			_stmt.setString(2, apparentCauseCustomer.getDescription());
			_stmt.setInt(3, apparentCauseCustomer.getIdApparentCauseCustomer());
			_stmt.executeUpdate();

			// Update object
			_stmt = this.cnxProduct.getCnx().prepareStatement(
					"SELECT * FROM apparentCauseCustomer" + " WHERE (idApparentCauseCustomer=?)");
			_stmt.setInt(1, apparentCauseCustomer.getIdApparentCauseCustomer());

			_rs = _stmt.executeQuery();
			if (_rs.next()) {
				this.getApparentCauseCustomer(_rs);
			} else {
				throw new ApparentCauseDaoException(exceptionMsg);
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
	}

	@Override
	public void removeApparentCauseCustomer(ApparentCauseCustomer apparentCauseCustomer) throws SQLException {
		PreparedStatement _stmt = null;

		try {
			_stmt = this.cnxProduct.getCnx().prepareStatement(
					"DELETE FROM apparentCauseCustomer " + "WHERE (idApparentCauseCustomer=?)");
			_stmt.setInt(1, apparentCauseCustomer.getIdApparentCauseCustomer());
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
