package fr.la.jproductbase.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import fr.la.jproductbase.metier.ApparentCause;
import fr.la.jproductbase.metier.ApparentCauseCustomer;

public class ApparentCauseDaoImpl implements ApparentCauseDao {
	private static String exceptionMsg = "Cause probable inconnu dans la base de données.";

	private ConnectionProduct cnxProduct;

	public ApparentCauseDaoImpl(ConnectionProduct cnxProduct) {
		this.cnxProduct = cnxProduct;
	}

	@Override
	public List<ApparentCause> getApparentCauses() throws SQLException {
		List<ApparentCause> _apparentCauses = new ArrayList<ApparentCause>();
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			_stmt = this.cnxProduct.getCnx().prepareStatement(
					"SELECT * FROM apparentCause ORDER BY description;");
			_rs = _stmt.executeQuery();

			while (_rs.next()) {
				ApparentCause _apparentCause = this.getApparentCause(_rs);
				_apparentCauses.add(_apparentCause);
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

		return _apparentCauses;
	}

	@Override
	public ApparentCause getApparentCause(int idApparentCause)
			throws SQLException {
		ApparentCause _apparentCause = null;
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			_stmt = this.cnxProduct.getCnx().prepareStatement(
					"SELECT * FROM apparentCause WHERE idApparentCause=?");
			_stmt.setInt(1, idApparentCause);
			_rs = _stmt.executeQuery();

			if (_rs.next()) {
				_apparentCause = this.getApparentCause(_rs);
			} else {
				_apparentCause = null;
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

		return _apparentCause;
	}

	@Override
	public ApparentCause addApparentCause(ApparentCause apparentCause)
			throws SQLException, ApparentCauseDaoException {
		ApparentCause _apparentCause = apparentCause;
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			_stmt = this.cnxProduct.getCnx().prepareStatement(
					"INSERT INTO apparentCause " + "(state, description, idApparentCauseCustomer) "
							+ "VALUES (?, ?, ?)");
			_stmt.setInt(1, apparentCause.getState());
			_stmt.setString(2, apparentCause.getDescription());
			_stmt.setInt(3, apparentCause.getApparentCauseCustomer()
					.getIdApparentCauseCustomer());
			_stmt.executeUpdate();

			_stmt = this.cnxProduct.getCnx().prepareStatement(
					"SELECT * FROM apparentCause " + "WHERE (state=?) "
							+ " AND (description=?) AND (idApparentCauseCustomer=?)");
			_stmt.setInt(1, apparentCause.getState());
			_stmt.setString(2, apparentCause.getDescription());
			_stmt.setInt(3, apparentCause.getApparentCauseCustomer().getIdApparentCauseCustomer());

			_rs = _stmt.executeQuery();
			if (_rs.next()) {
				_apparentCause = this.getApparentCause(_rs);
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

		return _apparentCause;
	}

	@Override
	public void updateApparentCause(ApparentCause apparentCause)
			throws SQLException, ApparentCauseDaoException {
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			_stmt = this.cnxProduct.getCnx().prepareStatement(
					"UPDATE apparentCause " + "SET state=?, description=?, idApparentCauseCustomer=? "
							+ " WHERE (idApparentCause=?)");
			System.out.println("dao updateApparentCause"+apparentCause.getApparentCauseCustomer().getIdApparentCauseCustomer()+" "+apparentCause.getIdApparentCause());
			_stmt.setInt(1, apparentCause.getState());
			_stmt.setString(2, apparentCause.getDescription());
			_stmt.setInt(3, apparentCause.getApparentCauseCustomer().getIdApparentCauseCustomer());
			_stmt.setInt(4, apparentCause.getIdApparentCause());
			_stmt.executeUpdate();

			// Update object
			_stmt = this.cnxProduct.getCnx().prepareStatement(
					"SELECT * FROM apparentCause"
							+ " WHERE (idApparentCause=?)");
			_stmt.setInt(1, apparentCause.getIdApparentCause());

			_rs = _stmt.executeQuery();
			if (_rs.next()) {
				this.getApparentCause(_rs);
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
	public void removeApparentCause(ApparentCause apparentCause)
			throws SQLException {
		PreparedStatement _stmt = null;

		try {
			_stmt = this.cnxProduct.getCnx().prepareStatement(
					"DELETE FROM apparentCause " + "WHERE (idApparentCause=?)");
			_stmt.setInt(1, apparentCause.getIdApparentCause());
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
		ApparentCauseCustomerDao _apparentCauseCustomerDao = new ApparentCauseCustomerDaoImpl(
				this.cnxProduct);
		ApparentCauseCustomer _apparentCauseCustomer = _apparentCauseCustomerDao
				.getApparentCauseCustomer(_idApparentCauseCustomer);

		ApparentCause _apparentCause = new ApparentCause(_idApparentCause,
				_timestamp, _state, _description, _apparentCauseCustomer);

		return _apparentCause;
	}
}
