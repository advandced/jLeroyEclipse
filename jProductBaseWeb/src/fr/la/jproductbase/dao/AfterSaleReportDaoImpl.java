package fr.la.jproductbase.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import fr.la.jproductbase.metier.AfterSaleReport;
import fr.la.jproductbase.metier.ApparentCause;
import fr.la.jproductbase.metier.Product;

public class AfterSaleReportDaoImpl extends GenericDao implements AfterSaleReportDao {

	ConnectionProduct cnxProduct;

	ProductDao _productDao;
	ApparentCauseDao _apparentCauseDao;

	public AfterSaleReportDaoImpl(ConnectionProduct cnxProduct, ProductDao productDao, ApparentCauseDao apparentCauseDao) {
		this.cnxProduct = cnxProduct;
		this._productDao = productDao;
		this._apparentCauseDao = apparentCauseDao;
	}

	@Override
	public List<AfterSaleReport> listAfterSaleReport() {
		List<AfterSaleReport> _afterSaleReports = new ArrayList<AfterSaleReport>();
		Connection c = null;
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			c = cnxProduct.getCnx();
			_stmt = c.prepareStatement(
					"SELECT * FROM afterSaleReport");
			_rs = _stmt.executeQuery();

			while (_rs.next()) {
				AfterSaleReport _afterSaleReport = this.getAfterSaleReport(_rs);
				_afterSaleReports.add(_afterSaleReport);
			}
		} catch (SQLException e) {
			handleDAOException(e);
		} finally {
			close(_rs);
			close(_stmt);
			close(c);
		}

		return _afterSaleReports;
	}

	@Override
	public List<AfterSaleReport> listAfterSaleReport(Product product) {
		List<AfterSaleReport> _afterSaleReports = new ArrayList<AfterSaleReport>();
		Connection c = null;
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			c = cnxProduct.getCnx();
			_stmt = c.prepareStatement(
					"SELECT * FROM afterSaleReport" + " WHERE (idProduct=?) "
							+ "ORDER BY arrivalDate");
			_stmt.setInt(1, product.getIdProduct());
			_rs = _stmt.executeQuery();

			while (_rs.next()) {
				AfterSaleReport _afterSaleReport = this.getAfterSaleReport(_rs);
				_afterSaleReports.add(_afterSaleReport);
			}
		} catch (SQLException e) {
			handleDAOException(e);
		} finally {
			close(_rs);
			close(_stmt);
			close(c);
		}

		return _afterSaleReports;
	}


	@Override
	public List<AfterSaleReport> listAfterSaleReport(int idproduct) {
		List<AfterSaleReport> _afterSaleReports = new ArrayList<AfterSaleReport>();
		Connection c = null;
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			c = cnxProduct.getCnx();
			_stmt = c.prepareStatement(
					"SELECT * FROM afterSaleReport WHERE idProduct=?;");
			_stmt.setInt(1,idproduct);
			_rs = _stmt.executeQuery();

			while (_rs.next()) {
				AfterSaleReport _afterSaleReport = this.getAfterSaleReport(_rs);
				_afterSaleReports.add(_afterSaleReport);
			}
		} catch (SQLException e) {
			handleDAOException(e);
		} finally {
			close(_rs);
			close(_stmt);
			close(c);
		}
		return _afterSaleReports;
	}

	@Override
	public List<AfterSaleReport> listAfterSaleReport(Date fromDate, Date toDate) {
		List<AfterSaleReport> _afterSaleReports = new ArrayList<AfterSaleReport>();
		Connection c = null;
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		java.sql.Date _fromDate = new java.sql.Date(fromDate.getTime());
		java.sql.Date _toDate = new java.sql.Date(fromDate.getTime());

		try {
			c = cnxProduct.getCnx();
			_stmt = c.prepareStatement(
					"SELECT * FROM afterSaleReport"
							+ " WHERE (arrivalDate BETWEEN ? AND ?)");
			_stmt.setDate(1, _fromDate);
			_stmt.setDate(2, _toDate);
			_rs = _stmt.executeQuery();

			while (_rs.next()) {
				AfterSaleReport _afterSaleReport = this.getAfterSaleReport(_rs);
				_afterSaleReports.add(_afterSaleReport);
			}
		} catch (SQLException e) {
			handleDAOException(e);
		} finally {
			close(_rs);
			close(_stmt);
			close(c);
		}

		return _afterSaleReports;
	}

	@Override
	public AfterSaleReport addAfterSaleReport(Date arrivalDate,
			String ecsNumber, String ncNature, Date firstAnalyseDate,
			Date materialInfoDate, Date reparationDate,
			Date qualityControlDate, Date expeditionDate, int functionnalTest,
			int visualControl, String asker, String intervenant,
			String InterventionSheetLink, String comment,
			ApparentCause apparentCause, String majorIndexIn,
			String majorIndexOut, Product product)  {

		// System.out.println("produit" + product.getIdProduct());
		AfterSaleReport _afterSaleReport = null;
		Connection c = null;
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		java.sql.Date _arrivalDate = null;
		if (null != arrivalDate) {
			_arrivalDate = new java.sql.Date(arrivalDate.getTime());
		}
		// java.sql.Date _firstAnalyseDate = new
		// java.sql.Date(firstAnalyseDate.getTime());
		java.sql.Date _firstAnalyseDate = null;
		// java.sql.Date _materialInfoDate = new
		// java.sql.Date(materialInfoDate.getTime());
		java.sql.Date _materialInfoDate = null;
		java.sql.Date _reparationDate = null;
		if (null != reparationDate) {
			_reparationDate = new java.sql.Date(reparationDate.getTime());
		}
		java.sql.Date _qualityControlDate = null;
		if (null != qualityControlDate) {
			_qualityControlDate = new java.sql.Date(
					qualityControlDate.getTime());
		}
		java.sql.Date _expeditionDate = null;
		if (null != expeditionDate) {
			_expeditionDate = new java.sql.Date(expeditionDate.getTime());
		}

		int idApparentCause = 0;
		if (null != apparentCause) {
			idApparentCause = apparentCause.getIdApparentCause();
		}

		try {
			c = cnxProduct.getCnx();
			_stmt = c.prepareStatement(
							"INSERT INTO afterSaleReport (state, arrivalDate, "
									+ "ecsNumber, ncNature, firstAnalyseDate, "
									+ "materialInfoDate, reparationDate, qualityControlDate, "
									+ "expeditionDate, functionnalTest, visualControl, "
									+ "asker, intervenant, interventionSheetLink, "
									+ "comment, idApparentCause, majorIndexIn, majorIndexOut, idProduct) "
									+ " VALUES "
									+ "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			_stmt.setInt(1, 1);
			_stmt.setDate(2, _arrivalDate);
			_stmt.setString(3, ecsNumber);
			_stmt.setString(4, ncNature);
			_stmt.setDate(5, _firstAnalyseDate);
			_stmt.setDate(6, _materialInfoDate);
			_stmt.setDate(7, _reparationDate);
			_stmt.setDate(8, _qualityControlDate);
			_stmt.setDate(9, _expeditionDate);
			_stmt.setInt(10, functionnalTest);
			_stmt.setInt(11, visualControl);
			_stmt.setString(12, asker);
			_stmt.setString(13, intervenant);
			_stmt.setString(14, InterventionSheetLink);
			_stmt.setString(15, comment);
			_stmt.setInt(16, idApparentCause);
			_stmt.setString(17, majorIndexIn);
			_stmt.setString(18, majorIndexOut);
			_stmt.setInt(19, product.getIdProduct());
			_stmt.executeUpdate();

			_stmt = c.prepareStatement(
					"SELECT * FROM afterSaleReport" + " WHERE (idProduct=?)"
							+ " AND (arrivalDate=?)");
			_stmt.setInt(1, product.getIdProduct());
			_stmt.setDate(2, _arrivalDate);

			_rs = _stmt.executeQuery();
			if (_rs.next()) {
				_afterSaleReport = this.getAfterSaleReport(_rs);
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

		return _afterSaleReport;
	}

	@Override
	public AfterSaleReport updateAfterSaleReport(AfterSaleReport afterSaleReport) {
		return this.updateAfterSaleReport(afterSaleReport,
				afterSaleReport.getArrivalDate(),
				afterSaleReport.getEcsNumber(), afterSaleReport.getNcNature(),
				afterSaleReport.getFirstAnalyseDate(),
				afterSaleReport.getMaterialInfoDate(),
				afterSaleReport.getReparationDate(),
				afterSaleReport.getQualityControlDate(),
				afterSaleReport.getExpeditionDate(),
				afterSaleReport.getFunctionnalTest(),
				afterSaleReport.getVisualControl(), afterSaleReport.getAsker(),
				afterSaleReport.getIntervenant(),
				afterSaleReport.getInterventionSheetLink(),
				afterSaleReport.getComment(),
				afterSaleReport.getApparentCause(),
				afterSaleReport.getMajorIndexIn(),
				afterSaleReport.getMajorIndexOut(),
				afterSaleReport.getProduct());
	}

	@Override
	public AfterSaleReport updateAfterSaleReport(
			AfterSaleReport afterSaleReport, Date arrivalDate,
			String ecsNumber, String ncNature, Date firstAnalyseDate,
			Date materialInfoDate, Date reparationDate,
			Date qualityControlDate, Date expeditionDate, int functionnalTest,
			int visualControl, String asker, String intervenant,
			String interventionSheetLink, String comment,
			ApparentCause apparentCause, String majoriIndexIn,
			String majorIndexOut, Product product) {
		Connection c = null;
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		AfterSaleReport _afterSaleReport = null;

		/*
		 * java.sql.Date _arrivalDate = new
		 * java.sql.Date(arrivalDate.getTime()); java.sql.Date _firstAnalyseDate
		 * = new java.sql.Date(firstAnalyseDate.getTime()); java.sql.Date
		 * _materialInfoDate = new java.sql.Date(materialInfoDate.getTime());
		 * java.sql.Date _reparationDate = new
		 * java.sql.Date(reparationDate.getTime()); java.sql.Date
		 * _qualityControlDate = new
		 * java.sql.Date(qualityControlDate.getTime()); java.sql.Date
		 * _expeditionDate = new java.sql.Date(expeditionDate.getTime());
		 */

		java.sql.Date _arrivalDate = null;
		if (null != arrivalDate) {
			_arrivalDate = new java.sql.Date(arrivalDate.getTime());
		}
		// java.sql.Date _firstAnalyseDate = new
		// java.sql.Date(firstAnalyseDate.getTime());
		java.sql.Date _firstAnalyseDate = null;
		// java.sql.Date _materialInfoDate = new
		// java.sql.Date(materialInfoDate.getTime());
		java.sql.Date _materialInfoDate = null;
		java.sql.Date _reparationDate = null;
		if (null != reparationDate) {
			_reparationDate = new java.sql.Date(reparationDate.getTime());
		}
		java.sql.Date _qualityControlDate = null;
		if (null != qualityControlDate) {
			_qualityControlDate = new java.sql.Date(
					qualityControlDate.getTime());
		}
		java.sql.Date _expeditionDate = null;
		if (null != expeditionDate) {
			_expeditionDate = new java.sql.Date(expeditionDate.getTime());
		}

		int idApparentCause = 0;
		if (null != apparentCause) {
			idApparentCause = apparentCause.getIdApparentCause();
		}

		try {
			c = cnxProduct.getCnx();
			_stmt = c.prepareStatement(
							"UPDATE afterSaleReport "
									+ "SET arrivalDate = ?, "
									+ "ecsNumber = ?, ncNature = ?, firstAnalyseDate = ?, "
									+ "materialInfoDate = ?, reparationDate = ?, qualityControlDate = ?, "
									+ "expeditionDate = ?, functionnalTest = ?, visualControl = ?, "
									+ "asker = ?, intervenant = ?, interventionSheetLink = ?, "
									+ "comment = ?, idApparentCause = ?, majorIndexIn = ?, "
									+ "majorIndexOut = ?, idProduct = ? "
									+ "WHERE (idAfterSaleReport=?)");
			_stmt.setDate(1, _arrivalDate);
			_stmt.setString(2, ecsNumber);
			_stmt.setString(3, ncNature);
			_stmt.setDate(4, _firstAnalyseDate);
			_stmt.setDate(5, _materialInfoDate);
			_stmt.setDate(6, _reparationDate);
			_stmt.setDate(7, _qualityControlDate);
			_stmt.setDate(8, _expeditionDate);
			_stmt.setInt(9, functionnalTest);
			_stmt.setInt(10, visualControl);
			_stmt.setString(11, asker);
			_stmt.setString(12, intervenant);
			_stmt.setString(13, interventionSheetLink);
			_stmt.setString(14, comment);
			_stmt.setInt(15, idApparentCause);
			_stmt.setString(16, majoriIndexIn);
			_stmt.setString(17, majorIndexOut);
			_stmt.setInt(18, product.getIdProduct());
			_stmt.setInt(19, afterSaleReport.getIdAfterSaleReport());
			_stmt.executeUpdate();

			// Update object
			_stmt = c.prepareStatement(
					"SELECT * FROM afterSaleReport" + " WHERE (idProduct=?)"
							+ " AND (arrivalDate=?)");
			_stmt.setInt(1, product.getIdProduct());
			_stmt.setDate(2, _arrivalDate);

			_rs = _stmt.executeQuery();
			if (_rs.next()) {
				_afterSaleReport = this.getAfterSaleReport(_rs);
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
		return _afterSaleReport;
	}

	@Override
	public AfterSaleReport getAfterSaleReport(int idAfterSaleReport) {
		AfterSaleReport _afterSaleReport = null;
		Connection c = null;
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			c = cnxProduct.getCnx();
			_stmt = c.prepareStatement(
					"SELECT * FROM afterSaleReport"
							+ " WHERE (idAfterSaleReport=?)");
			_stmt.setInt(1, idAfterSaleReport);
			_rs = _stmt.executeQuery();
			if (_rs.next()) {
				_afterSaleReport = this.getAfterSaleReport(_rs);
			} else {
				_afterSaleReport = null;
			}
		} catch (SQLException e) {
			handleDAOException(e);
		} finally {
			close(_rs);
			close(_stmt);
			close(c);
		}

		return _afterSaleReport;
	}

	/*
	 * Mise a jour du quality control date en fonction de l'id
	 */

	public void updateAfterSaleReportQualityControl(AfterSaleReport afterSaleReport)  {
		Connection c = null;
		ResultSet _rs = null;
		PreparedStatement _stmt = null;
		java.sql.Date _qualityControlDate = new java.sql.Date(afterSaleReport
				.getQualityControlDate().getTime());
		try {
			c = cnxProduct.getCnx();
			_stmt = c.prepareStatement(
					"UPDATE afterSaleReport " + "SET qualityControlDate = ? "
							+ "WHERE (idAfterSaleReport = ?)");

			_stmt.setDate(1, _qualityControlDate);
			_stmt.setInt(2, afterSaleReport.getIdAfterSaleReport());
			_stmt.executeUpdate();

			_stmt = c.prepareStatement(
					"SELECT * " + "FROM afterSaleReport "
							+ "WHERE (idAfterSaleReport = ?);");

			_stmt.setInt(1, afterSaleReport.getIdAfterSaleReport());

			_rs = _stmt.executeQuery();
			if (_rs.next()) {

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

	public List<AfterSaleReport> SelectAfterSaleReportValidControl() {
		List<AfterSaleReport> _afterSaleReports = new ArrayList<AfterSaleReport>();
		Connection c = null;
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			c = cnxProduct.getCnx();
			_stmt = c.prepareStatement(
					"SELECT * " + "FROM afterSaleReport "
							+ "WHERE reparationDate IS NOT NULL "
							+ "AND qualityControlDate IS NULL "
							+ "AND expeditionDate IS NULL;");

			_rs = _stmt.executeQuery();

			while (_rs.next()) {
				AfterSaleReport _afterSaleReport = this.getAfterSaleReport(_rs);
				_afterSaleReports.add(_afterSaleReport);
			}
		} catch (SQLException e) {
			handleDAOException(e);
		} finally {
			close(_rs);
			close(_stmt);
			close(c);
		}

		return _afterSaleReports;
	}

	public List<AfterSaleReport> SelectAfterSaleReportExpedSAV() {
		List<AfterSaleReport> _afterSaleReports = new ArrayList<AfterSaleReport>();
		Connection c = null;
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			c = cnxProduct.getCnx();
			_stmt = c.prepareStatement(
							"SELECT * "
									+ "FROM afterSaleReport "
									+ "WHERE reparationDate IS NOT NULL and qualityControlDate IS NOT NULL and expeditionDate IS NULL");

			_rs = _stmt.executeQuery();

			while (_rs.next()) {
				AfterSaleReport _afterSaleReport = this.getAfterSaleReport(_rs);
				_afterSaleReports.add(_afterSaleReport);
			}
		} catch (SQLException e) {
			handleDAOException(e);
		} finally {
			close(_rs);
			close(_stmt);
			close(c);
		}

		return _afterSaleReports;
	}

	public void updateAfterSaleReportExpedSAV(AfterSaleReport afterSaleReport) {
		Connection c = null;
		PreparedStatement _stmt = null;
		ResultSet _rs = null;
		java.sql.Date _qualityControlDate = new java.sql.Date(afterSaleReport
				.getQualityControlDate().getTime());
		try {
			c = cnxProduct.getCnx();
			_stmt = c.prepareStatement(
					"UPDATE afterSaleReport " + "SET expeditionDate = ? "
							+ "WHERE (idAfterSaleReport = ?)");

			_stmt.setDate(1, _qualityControlDate);
			_stmt.setInt(2, afterSaleReport.getIdAfterSaleReport());
			_stmt.executeUpdate();

			_stmt = c.prepareStatement(
					"SELECT * " + "FROM afterSaleReport "
							+ "WHERE (idAfterSaleReport = ?);");

			_stmt.setInt(1, afterSaleReport.getIdAfterSaleReport());

			_rs = _stmt.executeQuery();
			if (_rs.next()) {

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

	public List<AfterSaleReport> ListDevisRepa() {
		List<AfterSaleReport> _afterSaleReport = new ArrayList<AfterSaleReport>();
		Connection c = null;
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {

			String rqt = "SELECT *"
					+ " FROM afterSaleReport AS R LEFT JOIN afterSaleCom AS C ON R.idAfterSaleReport = C.idAfterSaleReport"
					+ " WHERE R.reparationDate IS NOT NULL AND R.qualityControlDate IS NOT NULL AND R.expeditionDate IS NULL AND C.quotationNumber IS NULL";
			c = cnxProduct.getCnx();
			_stmt = c.prepareStatement(rqt);
			_rs = _stmt.executeQuery();

			while (_rs.next()) {
				AfterSaleReport _afterSaleReporttmp = this
						.getAfterSaleReport(_rs);
				_afterSaleReport.add(_afterSaleReporttmp);
			}
		} catch (SQLException e) {
			handleDAOException(e);
		} finally {
			close(_rs);
			close(_stmt);
			close(c);
		}
		return _afterSaleReport;
	}

	private AfterSaleReport getAfterSaleReport(ResultSet rs) throws SQLException {
		// Retreive product
		Product _product = _productDao.getProduct(rs.getInt("idProduct"));

		// Retreive product
		ApparentCause _apparentCause = _apparentCauseDao.getApparentCause(rs.getInt("idApparentCause"));

		// FailureReport
		int _idAfterSaleReport = rs.getInt("idAfterSaleReport");
		Timestamp _timestamp = rs.getTimestamp("timestamp");
		int _state = rs.getInt("state");
		Date _arrivalDate = rs.getDate("arrivalDate");
		String _ecsNumber = rs.getString("ecsNumber");
		String _ncNature = rs.getString("ncNature");
		Date _firstAnalyseDate = rs.getDate("firstAnalyseDate");
		Date _materialInfoDate = rs.getDate("materialInfoDate");
		Date _reparationDate = rs.getDate("reparationDate");
		Date _qualityControlDate = rs.getDate("qualityControlDate");
		Date _expeditionDate = rs.getDate("expeditionDate");
		int _functionnalTest = rs.getInt("functionnalTest");
		int _visualControl = rs.getInt("visualControl");
		String _asker = rs.getString("asker");
		String _intervenant = rs.getString("intervenant");
		String _interventionSheetLink = rs.getString("interventionSheetLink");
		String _comment = rs.getString("comment");
		String _majorIndexIn = rs.getString("majorIndexIn");
		String _majorIndexOut = rs.getString("majorIndexOut");

		AfterSaleReport _afterSaleReport = new AfterSaleReport(
				_idAfterSaleReport, _timestamp, _state, _arrivalDate,
				_ecsNumber, _ncNature, _firstAnalyseDate, _materialInfoDate,
				_reparationDate, _qualityControlDate, _expeditionDate,
				_functionnalTest, _visualControl, _asker, _intervenant,
				_interventionSheetLink, _comment, null, _apparentCause,
				_product);
		_afterSaleReport.setMajorIndexIn(_majorIndexIn);
		_afterSaleReport.setMajorIndexOut(_majorIndexOut);

		// Retreive failures
		//FailureDao _failureDao = new FailureDaoImpl(this.cnxProduct, this.cnxOperator);
		//List<Failure> _failures = _failureDao.getFailures(_afterSaleReport);
		//_afterSaleReport.setFailures(_failures);

		return _afterSaleReport;
	}
}