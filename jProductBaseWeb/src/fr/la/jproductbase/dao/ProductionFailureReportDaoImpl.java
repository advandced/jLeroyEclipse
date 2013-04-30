package fr.la.jproductbase.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import fr.la.jproductbase.metier.Product;
import fr.la.jproductbase.metier.ProductionFailureReport;
import fr.la.jproductbase.metier.TesterReport;

public class ProductionFailureReportDaoImpl extends GenericDao implements ProductionFailureReportDao {

	ConnectionProduct cnxProduct;

	ProductDao _productDao;
	TesterReportDao _testerReportDao;
	FailureDao _failureDao;
	CustomerCommentDao _customerCommentDao;
	FailureReportCommentDao _failureReportCommentDao;
	
	public ProductionFailureReportDaoImpl(ConnectionProduct cnxProduct, ProductDao productDao, TesterReportDao testerReportDao, FailureDao failureDao, CustomerCommentDao customerCommentDao, FailureReportCommentDao failureReportCommentDao) {
		this.cnxProduct = cnxProduct;
		
		_productDao = productDao;
		_testerReportDao = testerReportDao;
		_failureDao = failureDao;
		_customerCommentDao = customerCommentDao;
		_failureReportCommentDao = failureReportCommentDao;
	}

	@Override
	public List<ProductionFailureReport> listProductionFailureReport() {
		List<ProductionFailureReport> _failureReports = new ArrayList<ProductionFailureReport>();
		Connection c = null;
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			c = this.cnxProduct.getCnx();
			_stmt = c.prepareStatement("SELECT * FROM productionFailureReport");
			_rs = _stmt.executeQuery();

			while (_rs.next()) {
				ProductionFailureReport _failureReport = this.getProductionFailureReport(_rs);
				_failureReports.add(_failureReport);
			}
		} catch (SQLException e) {
			handleDAOException(e);
		} finally {
			close(_rs);
			close(_stmt);
			close(c);
		}

		return _failureReports;
	}

	@Override
	public List<ProductionFailureReport> listProductionFailureReport(Product product) {
		List<ProductionFailureReport> _failureReports = new ArrayList<ProductionFailureReport>();
		Connection c = null;
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			c = this.cnxProduct.getCnx();
			_stmt = c.prepareStatement("SELECT * FROM productionFailureReport" + " WHERE (idProduct=?)");
			_stmt.setInt(1, product.getIdProduct());
			_rs = _stmt.executeQuery();

			while (_rs.next()) {
				ProductionFailureReport _failureReport = this.getProductionFailureReport(_rs);
				_failureReports.add(_failureReport);
			}
		} catch (SQLException e) {
			handleDAOException(e);
		} finally {
			close(_rs);
			close(_stmt);
			close(c);
		}

		return _failureReports;
	}

	@Override
	public List<ProductionFailureReport> listProductionFailureReport(Date fromDate, Date toDate) {
		List<ProductionFailureReport> _failureReports = new ArrayList<ProductionFailureReport>();
		Connection c = null;
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			c = this.cnxProduct.getCnx();
			_stmt = c.prepareStatement("SELECT * FROM productionFailureReport WHERE (registrationDate BETWEEN ? AND ?)");
			_stmt.setDate(1, fromDate);
			_stmt.setDate(2, toDate);
			_rs = _stmt.executeQuery();

			while (_rs.next()) {
				ProductionFailureReport _failureReport = this.getProductionFailureReport(_rs);
				_failureReports.add(_failureReport);
			}
		} catch (SQLException e) {
			handleDAOException(e);
		} finally {
			close(_rs);
			close(_stmt);
			close(c);
		}
		return _failureReports;
	}

	@Override
	public List<ProductionFailureReport> unclosedProductionFailureReport() {
		List<ProductionFailureReport> _failureReports = new ArrayList<ProductionFailureReport>();
		Connection c = null;
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			c = this.cnxProduct.getCnx();
			_stmt = c.prepareStatement(
					"SELECT * FROM productionFailureReport " + "WHERE (state=1)");
			_rs = _stmt.executeQuery();

			while (_rs.next()) {
				ProductionFailureReport _failureReport = this.getProductionFailureReport(_rs);
				
				_failureReports.add(_failureReport);
			}
		} catch (SQLException e) {
			handleDAOException(e);
		} finally {
			close(_rs);
			close(_stmt);
			close(c);
		}

		return _failureReports;
	}

	@Override
	public ProductionFailureReport addProductionFailureReport(Date registrationDate, Product product, TesterReport testerReport, String failureCode) {
		ProductionFailureReport _failureReport = null;
		Connection c = null;
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			c = this.cnxProduct.getCnx();
			_stmt = c.prepareStatement(
							"INSERT INTO productionFailureReport (state, registrationDate, failureCode, idProduct, idTesterReport)"
									+ " VALUES (?, ?, ?, ?, ?)");
			_stmt.setInt(1, 1);
			_stmt.setDate(2, registrationDate);
			_stmt.setString(3, failureCode);
			_stmt.setInt(4, product.getIdProduct());
			_stmt.setInt(5, testerReport.getIdTesterReport());
			_stmt.executeUpdate();

			_stmt = c.prepareStatement(
							"SELECT * FROM productionFailureReport"
									+ " WHERE (idTesterReport=?)");
			_stmt.setInt(1, testerReport.getIdTesterReport());

			_rs = _stmt.executeQuery();
			if (_rs.next()) {
				_failureReport = this.getProductionFailureReport(_rs);
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

		return _failureReport;
	}

	@Override
	public void updateProductionFailureReport(ProductionFailureReport productionFailureReport,
											Date registrationDate, 
											Product product, 
											String failureCode) {
		Connection c = null;
		PreparedStatement _stmt = null;
		ResultSet _rs = null;
		
		try {
			c = this.cnxProduct.getCnx();
			_stmt = c.prepareStatement(
							"UPDATE productionFailureReport "
									+ "SET registrationDate=?, failureCode=?, idProduct=?, state=? "
									+ "WHERE (idProductionFailureReport=?)");
			_stmt.setDate(1, registrationDate);
			_stmt.setString(2, failureCode);
			_stmt.setInt(3, product.getIdProduct());
			_stmt.setInt(4, productionFailureReport.getState());
			_stmt.setInt(5, productionFailureReport.getIdProductionFailureReport());
			_stmt.executeUpdate();

			// Update object
			_stmt = c.prepareStatement(
					"SELECT * FROM productionFailureReport"
							+ " WHERE (idProductionFailureReport=?)");
			_stmt.setInt(1, productionFailureReport.getIdProductionFailureReport());

			_rs = _stmt.executeQuery();
			if (_rs.next()) {
				this.getProductionFailureReport(_rs);
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
	public void closeProductionFailureReport(ProductionFailureReport productionFailureReport) {
		Connection c = null;
		PreparedStatement _stmt = null;

		try {
			c = this.cnxProduct.getCnx();
			_stmt = c.prepareStatement(
					"UPDATE productionFailureReport " + "SET state=? "
							+ "WHERE (idProductionFailureReport=?)");
			_stmt.setInt(1, 2);
			_stmt.setInt(2, productionFailureReport.getIdProductionFailureReport());
			_stmt.executeUpdate();

			// Update object
			productionFailureReport.setState(2);
		} catch (SQLException e) {
			handleDAOException(e);
		} finally {
			close(_stmt);
			close(c);
		}
	}

	@Override
	public ProductionFailureReport getProductionFailureReport(int idProductionFailureReport) {
		ProductionFailureReport _failureReport = null;
		Connection c = null;
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			c = this.cnxProduct.getCnx();
			_stmt = c.prepareStatement(
					"SELECT * FROM productionFailureReport"
							+ " WHERE (idProductionFailureReport=?)");
			_stmt.setInt(1, idProductionFailureReport);
			_rs = _stmt.executeQuery();
			if (_rs.next()) {
				_failureReport = this.getProductionFailureReport(_rs);
			} else {
				_failureReport = null;
			}
		} catch (SQLException e) {
			handleDAOException(e);
		} finally {
			close(_rs);
			close(_stmt);
			close(c);
		}

		return _failureReport;
	}

	@Override
	public ProductionFailureReport getProductionFailureReport(TesterReport testerReport) {
		ProductionFailureReport _failureReport = null;
		Connection c = null;
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			int _idTesterReport = testerReport.getIdTesterReport();
			c = this.cnxProduct.getCnx();
			_stmt = c.prepareStatement(
							"SELECT * FROM productionFailureReport"
									+ " WHERE (idTesterReport=?)");
			_stmt.setInt(1, _idTesterReport);
			_rs = _stmt.executeQuery();
			if (_rs.next()) {
				_failureReport = this.getProductionFailureReport(_rs);
			} else {
				_failureReport = null;
			}
		} catch (SQLException e) {
			handleDAOException(e);
		} finally {
			close(_rs);
			close(_stmt);
			close(c);
		}

		return _failureReport;
	}

	/*
	 * Cr&eacute;er un rapport de d&eacute;faut &agrave; partir d'un
	 * enregistrement de la base de donn&eacute;es.
	 * 
	 * @param rs Enregistrement de la base de donn&eacute;es.
	 * 
	 * @return Rapport de d&eacute;fauts.
	 */
	private ProductionFailureReport getProductionFailureReport(ResultSet rs) throws SQLException {
		// Retreive product
		Product _product = _productDao.getProduct(rs.getInt("idProduct"));

		// Retreive testerReport
		TesterReport _testerReport = _testerReportDao.getTesterReport(rs.getInt("idTesterReport"));

		// FailureReport
		int _idProductionFailureReport = rs.getInt("idProductionFailureReport");
		Timestamp _timestamp = rs.getTimestamp("timestamp");
		int _state = rs.getInt("state");
		Date _registrationDate = rs.getDate("registrationDate");
		String _failureCode = rs.getString("failureCode");
		ProductionFailureReport _productionFailureReport = new ProductionFailureReport(_idProductionFailureReport,
																						_timestamp, 
																						_state, 
																						_registrationDate, 
																						_failureCode, 
																						_product,
																						_testerReport);

		// Retreive failures
		_failureDao.getFailures(_productionFailureReport);

		// Retreive customer comment
		_customerCommentDao.getCustomerComment(_productionFailureReport);

		// Retreive failureReport comment
		_failureReportCommentDao.getFailureReportComment(_productionFailureReport);

		return _productionFailureReport;
	}
}
