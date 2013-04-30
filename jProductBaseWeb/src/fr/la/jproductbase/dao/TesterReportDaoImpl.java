package fr.la.jproductbase.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import fr.la.jproductbase.metier.Defect;
import fr.la.jproductbase.metier.ProductionFailureReport;
import fr.la.jproductbase.metier.Product;
import fr.la.jproductbase.metier.TestType;
import fr.la.jproductbase.metier.Tester;
import fr.la.jproductbase.metier.TesterReport;

public class TesterReportDaoImpl extends GenericDao implements TesterReportDao {
	
	ConnectionTester cnxTester;
	
	TestTypeDao _testTypeDao;
	TesterDao _testerDao;
	ProductDao _productDao;
	DefectDao _defectDao;
	
	public TesterReportDaoImpl(ConnectionTester cnxTester, TestTypeDao testTypeDao, TesterDao testerDao, ProductDao productDao, DefectDao defectDao) {
		this.cnxTester = cnxTester;
		
		_testTypeDao = testTypeDao;
		_testerDao = testerDao;
		_productDao = productDao;
		_defectDao = defectDao; 
	}

	@Override
	public TesterReport getTesterReport(int idTesterReport) {
		TesterReport _testerReport = null;
		Connection c = null;
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			c = this.cnxTester.getCnx();
			_stmt = c.prepareStatement(
					"SELECT * FROM testerReport WHERE idTesterReport=?");
			_stmt.setInt(1, idTesterReport);
			_rs = _stmt.executeQuery();

			if (_rs.next()) {
				_testerReport = this.getTesterReport(_rs);
			} else {
				_testerReport = null;
			}
		} catch (SQLException e) {
			handleDAOException(e);
		} finally {
			close(_rs);
			close(_stmt);
			close(c);
		}

		return _testerReport;
	}

	@Override
	public TesterReport getTesterReport(Timestamp reportDate, TestType testType, Product product) {
		TesterReport _testerReport = null;

		// Date
		SimpleDateFormat _reportDateFormatted = new SimpleDateFormat(
				"yyyy-MM-dd");

		// TestType
		int _idTestType = 0;
		if (null != testType) {
			_idTestType = testType.getIdTestType();
		} else {
			// No tester
		}

		// Product
		int _idProduct = 0;
		if (null != product) {
			_idProduct = product.getIdProduct();
		} else {
			// No tester
		}
		Connection c = null;
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			c = this.cnxTester.getCnx();
			_stmt = c.prepareStatement(
					"SELECT * FROM testerReport" + " WHERE (date LIKE '"
							+ _reportDateFormatted.format(reportDate) + "%')"
							+ " AND (idTestType=?)" + " AND (idProduct=?)");
			_stmt.setInt(1, _idTestType);
			_stmt.setInt(2, _idProduct);
			_rs = _stmt.executeQuery();

			if (_rs.next()) {
				_testerReport = this.getTesterReport(_rs);
			} else {
				_testerReport = null;
			}
		} catch (SQLException e) {
			handleDAOException(e);
		} finally {
			close(_rs);
			close(_stmt);
			close(c);
		}

		return _testerReport;
	}

	@Override
	public TesterReport getTesterReport(Timestamp reportDate, TestType testType, Tester tester, Product product) {
		TesterReport _testerReport = null;

		// TestType
		int _idTestType = 0;
		if (null != testType) {
			_idTestType = testType.getIdTestType();
		} else {
			// No tester
		}

		// Tester
		int _idTester = 0;
		if (null != tester) {
			_idTester = tester.getIdTester();
		} else {
			// No tester
		}

		// Product
		int _idProduct = 0;
		if (null != product) {
			_idProduct = product.getIdProduct();
		} else {
			// No tester
		}
		Connection c = null;
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			c = this.cnxTester.getCnx();
			_stmt = c.prepareStatement(
					"SELECT * FROM testerReport" + " WHERE (date=?)"
							+ " AND (idTestType=?)" + " AND (idTester=?)"
							+ " AND (idProduct=?)");
			_stmt.setTimestamp(1, reportDate);
			_stmt.setInt(2, _idTestType);
			_stmt.setInt(3, _idTester);
			_stmt.setInt(4, _idProduct);
			_rs = _stmt.executeQuery();

			if (_rs.next()) {
				_testerReport = this.getTesterReport(_rs);
			} else {
				_testerReport = null;
			}
		} catch (SQLException e) {
			handleDAOException(e);
		} finally {
			close(_rs);
			close(_stmt);
			close(c);
		}

		return _testerReport;
	}

	@Override
	public TesterReport addTesterReport(TesterReport testerReport) {
		TesterReport _testerReport = null;
		if (null != testerReport) {
			Connection c = null;
			PreparedStatement _stmt = null;
			ResultSet _rs = null;

			Timestamp _date = new Timestamp(testerReport.getDate().getTime());
			int _idTester = 0;
			if (null != testerReport.getTester()) {
				_idTester = testerReport.getTester().getIdTester();
			} else {
				_idTester = 0;
			}
			int _idTestType = testerReport.getTestType().getIdTestType();
			int idProduct = testerReport.getProduct().getIdProduct();
			try {
				c = this.cnxTester.getCnx();
				_stmt = c.prepareStatement(
								"INSERT INTO testerReport (state, date, testVersion, result, consoUmini, consoUnomi, consoUmaxi, idTestType, idTester, operatorCode, idProduct)"
										+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
				_stmt.setInt(1, testerReport.getState());
				_stmt.setTimestamp(2, _date);
				_stmt.setString(3, testerReport.getTestVersion());
				_stmt.setString(4, testerReport.getResult());
				_stmt.setInt(5, testerReport.getConsoUmini());
				_stmt.setInt(6, testerReport.getConsoUnomi());
				_stmt.setInt(7, testerReport.getConsoUmaxi());
				_stmt.setInt(8, _idTestType);
				_stmt.setInt(9, _idTester);
				_stmt.setString(10, testerReport.getOperatorCode());
				_stmt.setInt(11, idProduct);
				_stmt.executeUpdate();

				// Retrieve testerReport data
				_stmt = c.prepareStatement(
						"SELECT * FROM testerReport" + " WHERE (date=?)"
								+ "	AND (idTestType=?)" + "	AND (idProduct=?)");
				_stmt.setTimestamp(1, _date);
				_stmt.setInt(2, _idTestType);
				_stmt.setInt(3, idProduct);

				_rs = _stmt.executeQuery();
				if (_rs.next()) {
					_testerReport = this.getTesterReport(_rs);
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
		} else {
			// No testerReport
		}

		return _testerReport;
	}

	@Override
	public TesterReport addTesterReport(TestType testType, Tester tester, Timestamp date, String operatorCode, Product product) {
		TesterReport _testerReport = null;
		int _idTester = 0;
		if (null != tester) {
			_idTester = tester.getIdTester();
		} else {
			// No tester
		}
		Connection c = null;
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			c = this.cnxTester.getCnx();
			_stmt = c.prepareStatement(
							"INSERT INTO testerReport (state, date, result, idTestType, idTester, operatorCode, idProduct)"
									+ " VALUES (?, ?, ?, ?, ?, ?, ?)");
			_stmt.setInt(1, 1);
			_stmt.setTimestamp(2, date);
			_stmt.setString(3, "Failed");
			_stmt.setInt(4, testType.getIdTestType());
			_stmt.setInt(5, _idTester);
			_stmt.setString(6, operatorCode);
			_stmt.setInt(7, product.getIdProduct());
			_stmt.executeUpdate();

			// Retrieve testerReport data
			_stmt = c.prepareStatement(
					"SELECT * FROM testerReport" + " WHERE (date=?)"
							+ "	AND (idTester=?)" + "	AND (idProduct=?)");
			_stmt.setTimestamp(1, date);
			_stmt.setInt(2, _idTester);
			_stmt.setInt(3, product.getIdProduct());

			_rs = _stmt.executeQuery();
			if (_rs.next()) {
				_testerReport = this.getTesterReport(_rs);
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

		return _testerReport;
	}

	@Override
	public TesterReport addTesterReport(int state, TestType testType, Timestamp date, String operatorCode, Product product, String result) {
		TesterReport _testerReport = null;
		Connection c = null;
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			int _idProduct = product.getIdProduct();
			int _idTestType = testType.getIdTestType();

			c = this.cnxTester.getCnx();
			_stmt = c.prepareStatement(
							"INSERT INTO testerReport (state, date, result, idTestType, operatorCode, idProduct)"
									+ " VALUES (?, ?, ?, ?, ?, ?)");
			_stmt.setInt(1, state);
			_stmt.setTimestamp(2, date);
			_stmt.setString(3, result);
			_stmt.setInt(4, _idTestType);
			_stmt.setString(5, operatorCode);
			_stmt.setInt(6, _idProduct);
			_stmt.executeUpdate();

			// Retrieve testerReport data
			_stmt = c.prepareStatement(
					"SELECT * FROM testerReport" + " WHERE (date=?)"
							+ "	AND (idTestType=?)" + "	AND (idProduct=?)");
			_stmt.setTimestamp(1, date);
			_stmt.setInt(2, _idTestType);
			_stmt.setInt(3, _idProduct);

			_rs = _stmt.executeQuery();
			if (_rs.next()) {
				_testerReport = this.getTesterReport(_rs);
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

		return _testerReport;
	}

	@Override
	public void updateTesterReport(TesterReport testerReport, TesterReport testerReportNext) {
		if (null != testerReport) {
			Connection c = null;
			PreparedStatement _stmt = null;

			try {
				c = this.cnxTester.getCnx();
				_stmt = c.prepareStatement(
						"UPDATE testerReport " + "SET idTesterReportNext=? "
								+ "WHERE (idTesterReport=?)");
				if (null != testerReportNext) {
					_stmt.setInt(1, testerReportNext.getIdTesterReport());
				} else {
					_stmt.setNull(1, java.sql.Types.INTEGER);
				}
				_stmt.setInt(2, testerReport.getIdTesterReport());
				_stmt.executeUpdate();

			} catch (SQLException e) {
				handleDAOException(e);
			} finally {
				close(_stmt);
				close(c);
			}
		} else {
			// Nothing to do
		}
	}

	@Override
	public void updateTesterReport(ProductionFailureReport failureReport,
			TestType testType, Tester tester, Date reportDate,
			String operatorCode, Product product) {
		int _idTester = 0;
		if (null != tester) {
			_idTester = tester.getIdTester();
		} else {
			// No tester
		}
		Connection c = null;
		PreparedStatement _stmt = null;

		try {
			c = this.cnxTester.getCnx();
			_stmt = c.prepareStatement(
							"UPDATE testerReport "
									+ "SET idTestType=?, idTester=?, operatorCode=?, idProduct=? "
									+ "WHERE (idTesterReport=?)");
			_stmt.setInt(1, testType.getIdTestType());
			_stmt.setInt(2, _idTester);
			_stmt.setString(3, operatorCode);
			_stmt.setInt(4, product.getIdProduct());
			_stmt.setInt(5, failureReport.getTesterReport().getIdTesterReport());
			_stmt.executeUpdate();

			_stmt = c.prepareStatement(
					"SELECT * FROM testerReport WHERE (idTesterReport = ?);");

			_stmt.setInt(1, failureReport.getTesterReport().getIdTesterReport());

			ResultSet _rs = _stmt.executeQuery();
			if (!_rs.next()) {
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
	public List<TesterReport> getInFlowTesterReport(Product product) {
		List<TesterReport> _testerReports = new ArrayList<TesterReport>();
		if (null != product) {
			Connection c = null;
			PreparedStatement _stmt = null;
			ResultSet _rs = null;

			try {
				int _idProduct = product.getIdProduct();
				c = this.cnxTester.getCnx();
				_stmt = c.prepareStatement(
						"SELECT * FROM testerReport " + "WHERE (state = 1)"
								+ " AND (idProduct=?)");
				_stmt.setInt(1, _idProduct);
				_rs = _stmt.executeQuery();

				while (_rs.next()) {
					TesterReport _testerReport = this.getTesterReport(_rs);
					_testerReports.add(_testerReport);
				}
			} catch (SQLException e) {
				handleDAOException(e);
			} finally {
				close(_rs);
				close(_stmt);
				close(c);
			}
		} else {
			// Unknown product
		}

		return _testerReports;
	}

	@Override
	public List<TesterReport> getTesterReportsFollowingForm(Product product) {
		List<TesterReport> _testerReports = new ArrayList<TesterReport>();
		Connection c = null;
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			int _idProduct = product.getIdProduct();
			c = this.cnxTester.getCnx();
			_stmt = c.prepareStatement(
					"SELECT *" + " FROM testerBase.testerReport AS TR"
							+ " WHERE (TR.state=1)" + " AND (TR.idProduct=?)"
							+ " AND ((TR.idTesterReport=("
							+ "SELECT idTesterReport"
							+ " FROM productBase.failureReport AS FR"
							+ " WHERE (TR.idTesterReport=FR.idTesterReport)"
							+ " AND (FR.failureCode!='U00')))"
							+ " OR (TR.idTesterReport NOT IN("
							+ "SELECT idTesterReport"
							+ " FROM productBase.failureReport AS FR)))");
			_stmt.setInt(1, _idProduct);
			_rs = _stmt.executeQuery();

			while (_rs.next()) {
				TesterReport _testerReport = this.getTesterReport(_rs);
				_testerReports.add(_testerReport);
			}
		} catch (SQLException e) {
			handleDAOException(e);
		} finally {
			close(_rs);
			close(_stmt);
			close(c);
		}

		return _testerReports;
	}

	@Override
	public List<TesterReport> getTesterReportsFailedInflow() {
		List<TesterReport> _testerReports = new ArrayList<TesterReport>();
		Connection c = null;
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			c = this.cnxTester.getCnx();
			_stmt = c.prepareStatement(
					"SELECT * FROM testerReport " + "WHERE (state = 1)"
							+ " AND (result=?)");
			_stmt.setString(1, "Failed");
			_rs = _stmt.executeQuery();

			while (_rs.next()) {
				TesterReport _testerReport = this.getTesterReport(_rs);
				_testerReports.add(_testerReport);
			}
		} catch (SQLException e) {
			handleDAOException(e);
		} finally {
			close(_rs);
			close(_stmt);
			close(c);
		}

		return _testerReports;
	}

	/*
	 * Cr&eacute;er un rapport de tes &agrave; partir d'un enregistement de la
	 * base de donn&eacute;es.
	 * 
	 * @param rs Enregistement de la base de donn&eacute;es.
	 * 
	 * @return Rapport de test.
	 */
	private TesterReport getTesterReport(ResultSet rs) throws SQLException {
		// Retreive testType
		int _idTestType = rs.getInt("idTestType");
		TestType _testType = _testTypeDao.getTestType(_idTestType);

		// Retreive tester
		int _idTester = rs.getInt("idTester");
		Tester _tester = null;
		if (0 < _idTester) {
			_tester = _testerDao.getTester(_idTester);
		} else {
			// No tester
		}

		// Retreive operator
		String _operatorCode = rs.getString("operatorCode");

		// Retreive product
		int _idProduct = rs.getInt("idProduct");
		Product _product = _productDao.getProduct(_idProduct);

		int _idTesterReport = rs.getInt("idTesterReport");
		Timestamp _timestamp = rs.getTimestamp("timestamp");
		int _state = rs.getInt("state");
		Timestamp _date = rs.getTimestamp("date");
		String _testVersion = rs.getString("testVersion");
		String _result = rs.getString("result");
		int _idTesterReportNext = rs.getInt("idTesterReportNext");

		TesterReport _testerReport = new TesterReport(_idTesterReport,
				_timestamp, _state, _date, _testVersion, _result, _tester,
				_testType, _operatorCode, _product, _idTesterReportNext);

		// Retreive defects
		List<Defect> _defects = _defectDao.getDefects(_testerReport);

		_testerReport.setDefects(_defects);

		return _testerReport;
	}
}
