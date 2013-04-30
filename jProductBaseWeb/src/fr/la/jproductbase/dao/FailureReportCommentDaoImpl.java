package fr.la.jproductbase.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import fr.la.jproductbase.metier.ProductionFailureReport;
import fr.la.jproductbase.metier.FailureReportComment;

public class FailureReportCommentDaoImpl extends GenericDao implements FailureReportCommentDao {

	ConnectionProduct cnxProduct;

	public FailureReportCommentDaoImpl(ConnectionProduct cnxProduct) {
		this.cnxProduct = cnxProduct;
	}

	@Override
	public FailureReportComment getFailureReportComment(ProductionFailureReport productionFailureReport) {
		FailureReportComment _failureReportComment = new FailureReportComment();
		Connection c = null;
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			c = this.cnxProduct.getCnx();
			_stmt = c.prepareStatement(
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

		} catch (SQLException e) {
			handleDAOException(e);
		} finally {
			close(_rs);
			close(_stmt);
			close(c);
		}
		

		return _failureReportComment;
	}

	@Override
	public FailureReportComment addFailureReportComment(ProductionFailureReport productionFailureReport, String comment) {
		Date _commentDate = new Date(new java.util.Date().getTime());
		FailureReportComment _failureReportComment = this.addFailureReportComment(productionFailureReport, comment, _commentDate);
		return _failureReportComment;
	}

	@Override
	public FailureReportComment addFailureReportComment(ProductionFailureReport productionFailureReport, String comment, Date commentDate) {
		FailureReportComment _failureReportComment = null;
		Connection c = null;
		PreparedStatement _stmt = null;
		ResultSet _rs = null;
		try {
			c = this.cnxProduct.getCnx();
			_stmt = c.prepareStatement(
					"INSERT INTO failureReportComment (comment, commentDate, idProductionFailureReport)"
							+ " VALUES (?, ?, ?)");
			_stmt.setString(1, comment);
			_stmt.setDate(2, commentDate);
			_stmt.setInt(3, productionFailureReport.getIdProductionFailureReport());
			_stmt.executeUpdate();

			// Retrieve failureReportComment data
			_stmt = c.prepareStatement(
					"SELECT * FROM failureReportComment"
							+ " WHERE (idProductionFailureReport=?)"
							+ " 	AND (comment=?)");
			_stmt.setInt(1, productionFailureReport.getIdProductionFailureReport());
			_stmt.setString(2, comment);

			_rs = _stmt.executeQuery();
			if (_rs.next()) {
				_failureReportComment = this.getFailureReportComment(_rs);
			} else {
				throw new IllegalStateException();
			}

			// Update failureReport object
			productionFailureReport.setFailureReportComment(_failureReportComment);
		} catch (SQLException e) {
			handleDAOException(e);
		} finally {
			close(_rs);
			close(_stmt);
			close(c);
		}

		return _failureReportComment;
	}

	@Override
	public void updateFailureReportComment(ProductionFailureReport productionFailureReport,	String comment) {
		Connection c = null;
		PreparedStatement _stmt = null;

		try {
			c = this.cnxProduct.getCnx();
			_stmt = c.prepareStatement(
					"UPDATE failureReportComment " + "SET comment=? "
							+ "WHERE (idProductionFailureReport=?)");
			_stmt.setString(1, comment);
			_stmt.setInt(2, productionFailureReport.getIdProductionFailureReport());
			_stmt.executeUpdate();

			_stmt = c.prepareStatement(
					"SELECT * FROM failureReportComment"
							+ " WHERE (idProductionFailureReport=?)");
			_stmt.setInt(1, productionFailureReport.getIdProductionFailureReport());

			ResultSet _rs = _stmt.executeQuery();
			if (_rs.next()) {
				
			} else {
				throw new IllegalStateException();
			}

			// Update object

		} catch (SQLException e) {
			handleDAOException(e);
		} finally {
			close(_stmt);
			close(c);
		}
	}

	@Override
	public void removeFailureReportComment(ProductionFailureReport productionFailureReport) {
		Connection c = null;
		PreparedStatement _stmt = null;

		try {
			c = this.cnxProduct.getCnx();
			_stmt = c.prepareStatement(
					"DELETE FROM failureReportComment"
							+ " WHERE (idProductionFailureReport=?)");
			_stmt.setInt(1, productionFailureReport.getIdProductionFailureReport());
			_stmt.executeUpdate();

			// Update failureReport object
			productionFailureReport.setFailureReportComment(null);

		} catch (SQLException e) {
			handleDAOException(e);
		} finally {
			close(_stmt);
			close(c);
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
	private FailureReportComment getFailureReportComment(ResultSet rs) throws SQLException {
		int _idFailureReportComment = rs.getInt("idFailureReportComment");
		Timestamp _timestamp = rs.getTimestamp("timestamp");
		String _comment = rs.getString("comment");
		Date _commentDate = rs.getDate("commentDate");
		FailureReportComment _failureReportComment = new FailureReportComment(
				_idFailureReportComment, _timestamp, _comment, _commentDate);

		return _failureReportComment;
	}
}
