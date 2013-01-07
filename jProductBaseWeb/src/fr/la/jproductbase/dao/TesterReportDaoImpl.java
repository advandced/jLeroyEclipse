package fr.la.jproductbase.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import fr.la.jproductbase.metier.Defect;
import fr.la.jproductbase.metier.ProductionFailureReport;
import fr.la.jproductbase.metier.Product;
import fr.la.jproductbase.metier.TestType;
import fr.la.jproductbase.metier.Tester;
import fr.la.jproductbase.metier.TesterReport;

public class TesterReportDaoImpl implements TesterReportDao {
	private static String exceptionMsg = "Rapport de test inconnu dans la base de données.";

	private ConnectionProduct cnxProduct;
	private ConnectionTester cnxTester;

	public TesterReportDaoImpl(ConnectionProduct cnxProduct,
			ConnectionTester cnxTester) {
		this.cnxProduct = cnxProduct;
		this.cnxTester = cnxTester;
	}

	@Override
	public TesterReport getTesterReport(int idTesterReport) throws SQLException {
		TesterReport _testerReport = null;
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			_stmt = this.cnxTester.getCnx().prepareStatement(
					"SELECT * FROM testerReport WHERE idTesterReport=?");
			_stmt.setInt(1, idTesterReport);
			_rs = _stmt.executeQuery();

			if (_rs.next()) {
				_testerReport = this.getTesterReport(_rs);
			} else {
				_testerReport = null;
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

		return _testerReport;
	}

	@Override
	public TesterReport getTesterReport(Timestamp reportDate,
			TestType testType, Product product) throws SQLException {
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
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			_stmt = this.cnxTester.getCnx().prepareStatement(
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

		return _testerReport;
	}

	@Override
	public TesterReport getTesterReport(Timestamp reportDate,
			TestType testType, Tester tester, Product product)
			throws SQLException {
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
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			_stmt = this.cnxTester.getCnx().prepareStatement(
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

		return _testerReport;
	}

	@Override
	public TesterReport addTesterReport(TesterReport testerReport)
			throws SQLException, TesterReportDaoException {
		TesterReport _testerReport = null;
		if (null != testerReport) {
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
				_stmt = this.cnxTester
						.getCnx()
						.prepareStatement(
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
				_stmt = this.cnxTester.getCnx().prepareStatement(
						"SELECT * FROM testerReport" + " WHERE (date=?)"
								+ "	AND (idTestType=?)" + "	AND (idProduct=?)");
				_stmt.setTimestamp(1, _date);
				_stmt.setInt(2, _idTestType);
				_stmt.setInt(3, idProduct);

				_rs = _stmt.executeQuery();
				if (_rs.next()) {
					_testerReport = this.getTesterReport(_rs);
				} else {
					throw new TesterReportDaoException(exceptionMsg);
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
		} else {
			// No testerReport
		}

		return _testerReport;
	}

	@Override
	public TesterReport addTesterReport(TestType testType, Tester tester,
			Timestamp date, String operatorCode, Product product)
			throws SQLException, TesterReportDaoException {
		TesterReport _testerReport = null;
		int _idTester = 0;
		if (null != tester) {
			_idTester = tester.getIdTester();
		} else {
			// No tester
		}
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			_stmt = this.cnxTester
					.getCnx()
					.prepareStatement(
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
			_stmt = this.cnxTester.getCnx().prepareStatement(
					"SELECT * FROM testerReport" + " WHERE (date=?)"
							+ "	AND (idTester=?)" + "	AND (idProduct=?)");
			_stmt.setTimestamp(1, date);
			_stmt.setInt(2, _idTester);
			_stmt.setInt(3, product.getIdProduct());

			_rs = _stmt.executeQuery();
			if (_rs.next()) {
				_testerReport = this.getTesterReport(_rs);
			} else {
				throw new TesterReportDaoException(exceptionMsg);
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

		return _testerReport;
	}

	@Override
	public TesterReport addTesterReport(int state, TestType testType,
			Timestamp date, String operatorCode, Product product, String result)
			throws SQLException, TesterReportDaoException {
		TesterReport _testerReport = null;
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			int _idProduct = product.getIdProduct();
			int _idTestType = testType.getIdTestType();

			_stmt = this.cnxTester
					.getCnx()
					.prepareStatement(
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
			_stmt = this.cnxTester.getCnx().prepareStatement(
					"SELECT * FROM testerReport" + " WHERE (date=?)"
							+ "	AND (idTestType=?)" + "	AND (idProduct=?)");
			_stmt.setTimestamp(1, date);
			_stmt.setInt(2, _idTestType);
			_stmt.setInt(3, _idProduct);

			_rs = _stmt.executeQuery();
			if (_rs.next()) {
				_testerReport = this.getTesterReport(_rs);
			} else {
				throw new TesterReportDaoException(exceptionMsg);
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

		return _testerReport;
	}

	@Override
	public void updateTesterReport(TesterReport testerReport,
			TesterReport testerReportNext) throws SQLException {
		if (null != testerReport) {
			PreparedStatement _stmt = null;

			try {
				_stmt = this.cnxTester.getCnx().prepareStatement(
						"UPDATE testerReport " + "SET idTesterReportNext=? "
								+ "WHERE (idTesterReport=?)");
				if (null != testerReportNext) {
					_stmt.setInt(1, testerReportNext.getIdTesterReport());
				} else {
					_stmt.setNull(1, java.sql.Types.INTEGER);
				}
				_stmt.setInt(2, testerReport.getIdTesterReport());
				_stmt.executeUpdate();

				// Update object
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				if (null != _stmt) {
					_stmt.close();
				}
			}
		} else {
			// Nothing to do
		}
	}

	@Override
	public void updateTesterReport(ProductionFailureReport failureReport,
			TestType testType, Tester tester, Date reportDate,
			String operatorCode, Product product) throws SQLException,
			TesterReportDaoException {
		int _idTester = 0;
		if (null != tester) {
			_idTester = tester.getIdTester();
		} else {
			// No tester
		}
		PreparedStatement _stmt = null;

		try {
			_stmt = this.cnxTester
					.getCnx()
					.prepareStatement(
							"UPDATE testerReport "
									+ "SET idTestType=?, idTester=?, operatorCode=?, idProduct=? "
									+ "WHERE (idTesterReport=?)");
			_stmt.setInt(1, testType.getIdTestType());
			_stmt.setInt(2, _idTester);
			_stmt.setString(3, operatorCode);
			_stmt.setInt(4, product.getIdProduct());
			_stmt.setInt(5, failureReport.getTesterReport().getIdTesterReport());
			_stmt.executeUpdate();

			_stmt = this.cnxTester.getCnx().prepareStatement(
					"SELECT * FROM testerReport WHERE (idTesterReport = ?);");

			_stmt.setInt(1, failureReport.getTesterReport().getIdTesterReport());

			ResultSet _rs = _stmt.executeQuery();
			if (!_rs.next()) {
				throw new TesterReportDaoException(exceptionMsg);
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
	public List<TesterReport> getInFlowTesterReport(Product product)
			throws SQLException {
		List<TesterReport> _testerReports = new ArrayList<TesterReport>();
		if (null != product) {
			PreparedStatement _stmt = null;
			ResultSet _rs = null;

			try {
				int _idProduct = product.getIdProduct();

				_stmt = this.cnxTester.getCnx().prepareStatement(
						"SELECT * FROM testerReport " + "WHERE (state = 1)"
								+ " AND (idProduct=?)");
				_stmt.setInt(1, _idProduct);
				_rs = _stmt.executeQuery();

				while (_rs.next()) {
					TesterReport _testerReport = this.getTesterReport(_rs);
					_testerReports.add(_testerReport);
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
		} else {
			// Unknown product
		}

		return _testerReports;
	}

	@Override
	public List<TesterReport> getTesterReportsFollowingForm(Product product)
			throws SQLException {
		List<TesterReport> _testerReports = new ArrayList<TesterReport>();
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			int _idProduct = product.getIdProduct();

			_stmt = this.cnxTester.getCnx().prepareStatement(
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

		return _testerReports;
	}

	@Override
	public List<TesterReport> getTesterReportsFailedInflow()
			throws SQLException {
		List<TesterReport> _testerReports = new ArrayList<TesterReport>();
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			_stmt = this.cnxTester.getCnx().prepareStatement(
					"SELECT * FROM testerReport " + "WHERE (state = 1)"
							+ " AND (result=?)");
			_stmt.setString(1, "Failed");
			_rs = _stmt.executeQuery();

			while (_rs.next()) {
				TesterReport _testerReport = this.getTesterReport(_rs);
				_testerReports.add(_testerReport);
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
		TestTypeDao _testTypeDao = new TestTypeDaoImpl(this.cnxTester);
		TestType _testType = _testTypeDao.getTestType(_idTestType);

		// Retreive tester
		int _idTester = rs.getInt("idTester");
		Tester _tester = null;
		if (0 < _idTester) {
			TesterDao _testerDao = new TesterDaoImpl(this.cnxTester);
			_tester = _testerDao.getTester(_idTester);
		} else {
			// No tester
		}

		// Retreive operator
		String _operatorCode = rs.getString("operatorCode");

		// Retreive product
		int _idProduct = rs.getInt("idProduct");
		ProductDao _productDao = new ProductDaoImpl(this.cnxProduct);
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
		DefectDao _defectDao = new DefectDaoImpl(this.cnxTester);
		List<Defect> _defects = _defectDao.getDefects(_testerReport);

		_testerReport.setDefects(_defects);

		return _testerReport;
	}
}
