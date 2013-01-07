package fr.la.jproductbase.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import javax.naming.NamingException;

import fr.la.jproductbase.metier.ProductionFailureReport;
import fr.la.jproductbase.metier.FailureReportComment;

public class FailureReportCommentDaoImpl implements FailureReportCommentDao {
	private static String exceptionMsg = "Commentaire de rapport de défauts inconnu dans la base de données.";

	private ConnectionProduct cnxProduct;

	public FailureReportCommentDaoImpl(ConnectionProduct cnxProduct) {
		this.cnxProduct = cnxProduct;
	}

	@Override
	public FailureReportComment getFailureReportComment(
			ProductionFailureReport productionFailureReport) throws SQLException {
		FailureReportComment _failureReportComment = new FailureReportComment();
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			_stmt = this.cnxProduct
					.getCnx()
					.prepareStatement(
							"SELECT * FROM failureReportComment WHERE idProductionFailureReport=?");
			_stmt.setInt(1, productionFailureReport.getIdProductionFailureReport());
			_rs = _stmt.executeQuery();

			if (_rs.next()) {
				_failureReportComment = this.getFailureReportComment(_rs);
			} else {
				// Pas de commentaire pour ce rapport de défauts
			}

			// Update failureReport object
			productionFailureReport.setFailureReportComment(_failureReportComment);
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
		

		return _failureReportComment;
	}

	@Override
	public FailureReportComment addFailureReportComment(
			ProductionFailureReport productionFailureReport, String comment) throws SQLException,
			FailureReportCommentDaoException {
		System.out.println("valuer de comment dans addFailureReportComment" + comment);
		Date _commentDate = new Date(new java.util.Date().getTime());
		FailureReportComment _failureReportComment = this
				.addFailureReportComment(productionFailureReport, comment, _commentDate);

		return _failureReportComment;
	}

	@Override
	public FailureReportComment addFailureReportComment(
			ProductionFailureReport productionFailureReport, String comment, Date commentDate)
			throws SQLException, FailureReportCommentDaoException {
		FailureReportComment _failureReportComment = null;
		PreparedStatement _stmt = null;
		ResultSet _rs = null;
		System.out.println("insertion de la valeur comment"+comment);
		try {
			_stmt = this.cnxProduct.getCnx().prepareStatement(
					"INSERT INTO failureReportComment (comment, commentDate, idProductionFailureReport)"
							+ " VALUES (?, ?, ?)");
			_stmt.setString(1, comment);
			_stmt.setDate(2, commentDate);
			_stmt.setInt(3, productionFailureReport.getIdProductionFailureReport());
			_stmt.executeUpdate();

			// Retrieve failureReportComment data
			_stmt = this.cnxProduct.getCnx().prepareStatement(
					"SELECT * FROM failureReportComment"
							+ " WHERE (idProductionFailureReport=?)"
							+ " 	AND (comment=?)");
			_stmt.setInt(1, productionFailureReport.getIdProductionFailureReport());
			_stmt.setString(2, comment);

			_rs = _stmt.executeQuery();
			if (_rs.next()) {
				_failureReportComment = this.getFailureReportComment(_rs);
			} else {
				throw new FailureReportCommentDaoException(exceptionMsg);
			}

			// Update failureReport object
			productionFailureReport.setFailureReportComment(_failureReportComment);
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

		return _failureReportComment;
	}

	@Override
	public void updateFailureReportComment(ProductionFailureReport productionFailureReport,
			String comment) throws SQLException, FailureReportCommentDaoException {
		PreparedStatement _stmt = null;

		try {
			_stmt = this.cnxProduct.getCnx().prepareStatement(
					"UPDATE failureReportComment " + "SET comment=? "
							+ "WHERE (idProductionFailureReport=?)");
			_stmt.setString(1, comment);
			_stmt.setInt(2, productionFailureReport.getIdProductionFailureReport());
			_stmt.executeUpdate();

			_stmt = this.cnxProduct.getCnx().prepareStatement(
					"SELECT * FROM failureReportComment"
							+ " WHERE (idProductionFailureReport=?)");
			_stmt.setInt(1, productionFailureReport.getIdProductionFailureReport());

			ResultSet _rs = _stmt.executeQuery();
			if (_rs.next()) {
				
			} else {
				throw new FailureReportCommentDaoException(exceptionMsg);
			}

			// Update object
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
	public void removeFailureReportComment(ProductionFailureReport productionFailureReport)
			throws SQLException {
		PreparedStatement _stmt = null;

		try {
			_stmt = this.cnxProduct.getCnx().prepareStatement(
					"DELETE FROM failureReportComment"
							+ " WHERE (idProductionFailureReport=?)");
			_stmt.setInt(1, productionFailureReport.getIdProductionFailureReport());
			_stmt.executeUpdate();

			// Update failureReport object
			productionFailureReport.setFailureReportComment(null);
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
	 * Cr&eacute;er un commentaire de rapport de d&eacute;fauts &agrave; partir
	 * d'un enregistrement de la base de donn&eacute;es.
	 * 
	 * @param rs Enregistrement de la base de donn&eacute;es.
	 * 
	 * @return Commentaire de rapport de d&eacute;fauts.
	 */
	private FailureReportComment getFailureReportComment(ResultSet rs)
			throws SQLException {
		int _idFailureReportComment = rs.getInt("idFailureReportComment");
		Timestamp _timestamp = rs.getTimestamp("timestamp");
		String _comment = rs.getString("comment");
		Date _commentDate = rs.getDate("commentDate");
		FailureReportComment _failureReportComment = new FailureReportComment(
				_idFailureReportComment, _timestamp, _comment, _commentDate);

		return _failureReportComment;
	}
}
