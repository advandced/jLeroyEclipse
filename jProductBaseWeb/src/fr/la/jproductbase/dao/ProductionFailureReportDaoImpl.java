package fr.la.jproductbase.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import fr.la.jproductbase.metier.Product;
import fr.la.jproductbase.metier.ProductionFailureReport;
import fr.la.jproductbase.metier.TesterReport;

public class ProductionFailureReportDaoImpl implements ProductionFailureReportDao {
	private static String exceptionMsg = "Rapport de défauts inconnu dans la base de données.";

	private ConnectionProduct cnxProduct;
	private ConnectionOperator cnxOperator;
	private ConnectionTester cnxTester;

	public ProductionFailureReportDaoImpl(ConnectionProduct cnxProduct,
			ConnectionOperator cnxOperator, ConnectionTester cnxTester) {
		this.cnxProduct = cnxProduct;
		this.cnxOperator = cnxOperator;
		this.cnxTester = cnxTester;
	}

	@Override
	public List<ProductionFailureReport> listProductionFailureReport() throws SQLException {
		List<ProductionFailureReport> _failureReports = new ArrayList<ProductionFailureReport>();
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			_stmt = this.cnxProduct.getCnx().prepareStatement(
					"SELECT * FROM productionFailureReport");
			_rs = _stmt.executeQuery();

			while (_rs.next()) {
				ProductionFailureReport _failureReport = this.getProductionFailureReport(_rs);
				_failureReports.add(_failureReport);
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

		return _failureReports;
	}

	@Override
	public List<ProductionFailureReport> listProductionFailureReport(Product product)
			throws SQLException {
		List<ProductionFailureReport> _failureReports = new ArrayList<ProductionFailureReport>();
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			_stmt = this.cnxProduct.getCnx().prepareStatement(
					"SELECT * FROM productionFailureReport" + " WHERE (idProduct=?)");
			_stmt.setInt(1, product.getIdProduct());
			_rs = _stmt.executeQuery();

			while (_rs.next()) {
				ProductionFailureReport _failureReport = this.getProductionFailureReport(_rs);
				_failureReports.add(_failureReport);
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

		return _failureReports;
	}

	@Override
	public List<ProductionFailureReport> listProductionFailureReport(Date fromDate, Date toDate)
			throws SQLException {
		List<ProductionFailureReport> _failureReports = new ArrayList<ProductionFailureReport>();
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			_stmt = this.cnxProduct.getCnx().prepareStatement(
					"SELECT * FROM productionFailureReport"
							+ " WHERE (registrationDate BETWEEN ? AND ?)");
			_stmt.setDate(1, fromDate);
			_stmt.setDate(2, toDate);
			_rs = _stmt.executeQuery();

			while (_rs.next()) {
				ProductionFailureReport _failureReport = this.getProductionFailureReport(_rs);
				_failureReports.add(_failureReport);
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
		return _failureReports;
	}

	@Override
	public List<ProductionFailureReport> unclosedProductionFailureReport() throws SQLException {
		List<ProductionFailureReport> _failureReports = new ArrayList<ProductionFailureReport>();
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			_stmt = this.cnxProduct.getCnx().prepareStatement(
					"SELECT * FROM productionFailureReport " + "WHERE (state=1)");
			_rs = _stmt.executeQuery();

			while (_rs.next()) {
				ProductionFailureReport _failureReport = this.getProductionFailureReport(_rs);
				
				_failureReports.add(_failureReport);
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

		return _failureReports;
	}

	@Override
	public ProductionFailureReport addProductionFailureReport(Date registrationDate,
			Product product, TesterReport testerReport, String failureCode)
			throws SQLException, ProductionFailureReportDaoException {
		ProductionFailureReport _failureReport = null;
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			_stmt = this.cnxProduct
					.getCnx()
					.prepareStatement(
							"INSERT INTO productionFailureReport (state, registrationDate, failureCode, idProduct, idTesterReport)"
									+ " VALUES (?, ?, ?, ?, ?)");
			_stmt.setInt(1, 1);
			_stmt.setDate(2, registrationDate);
			_stmt.setString(3, failureCode);
			_stmt.setInt(4, product.getIdProduct());
			_stmt.setInt(5, testerReport.getIdTesterReport());
			_stmt.executeUpdate();

			_stmt = this.cnxProduct.getCnx()
					.prepareStatement(
							"SELECT * FROM productionFailureReport"
									+ " WHERE (idTesterReport=?)");
			_stmt.setInt(1, testerReport.getIdTesterReport());

			_rs = _stmt.executeQuery();
			if (_rs.next()) {
				_failureReport = this.getProductionFailureReport(_rs);
			} else {
				throw new ProductionFailureReportDaoException(exceptionMsg);
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

		return _failureReport;
	}

	@Override
	public void updateProductionFailureReport(ProductionFailureReport productionFailureReport,
			Date registrationDate, Product product, String failureCode)
			throws SQLException, ProductionFailureReportDaoException {
		PreparedStatement _stmt = null;
		ResultSet _rs = null;
		
		try {
			_stmt = this.cnxProduct
					.getCnx()
					.prepareStatement(
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
			_stmt = this.cnxProduct.getCnx().prepareStatement(
					"SELECT * FROM productionFailureReport"
							+ " WHERE (idProductionFailureReport=?)");
			_stmt.setInt(1, productionFailureReport.getIdProductionFailureReport());

			_rs = _stmt.executeQuery();
			if (_rs.next()) {
				this.getProductionFailureReport(_rs);
			} else {
				throw new ProductionFailureReportDaoException(exceptionMsg);
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
	public void closeProductionFailureReport(ProductionFailureReport productionFailureReport)
			throws SQLException {
		PreparedStatement _stmt = null;

		try {
			_stmt = this.cnxProduct.getCnx().prepareStatement(
					"UPDATE productionFailureReport " + "SET state=? "
							+ "WHERE (idProductionFailureReport=?)");
			_stmt.setInt(1, 2);
			_stmt.setInt(2, productionFailureReport.getIdProductionFailureReport());
			_stmt.executeUpdate();

			// Update object
			productionFailureReport.setState(2);
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
	public ProductionFailureReport getProductionFailureReport(int idProductionFailureReport)
			throws SQLException {
		ProductionFailureReport _failureReport = null;
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			_stmt = this.cnxProduct.getCnx().prepareStatement(
					"SELECT * FROM productionFailureReport"
							+ " WHERE (idProductionFailureReport=?)");
			_stmt.setInt(1, idProductionFailureReport);
			_rs = _stmt.executeQuery();
			if (_rs.next()) {
				_failureReport = this.getProductionFailureReport(_rs);
			} else {
				_failureReport = null;
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

		return _failureReport;
	}

	@Override
	public ProductionFailureReport getProductionFailureReport(TesterReport testerReport)
			throws SQLException {
		ProductionFailureReport _failureReport = null;
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			int _idTesterReport = testerReport.getIdTesterReport();
			_stmt = this.cnxProduct.getCnx()
					.prepareStatement(
							"SELECT * FROM productionFailureReport"
									+ " WHERE (idTesterReport=?)");
			_stmt.setInt(1, _idTesterReport);
			_rs = _stmt.executeQuery();
			if (_rs.next()) {
				_failureReport = this.getProductionFailureReport(_rs);
			} else {
				_failureReport = null;
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
		ProductDao _productDao = new ProductDaoImpl(this.cnxProduct);
		Product _product = _productDao.getProduct(rs.getInt("idProduct"));

		// Retreive testerReport
		TesterReportDao _testerReportDao = new TesterReportDaoImpl(
				this.cnxProduct, this.cnxTester);
		TesterReport _testerReport = _testerReportDao.getTesterReport(rs
				.getInt("idTesterReport"));

		// FailureReport
		int _idProductionFailureReport = rs.getInt("idProductionFailureReport");
		Timestamp _timestamp = rs.getTimestamp("timestamp");
		int _state = rs.getInt("state");
		Date _registrationDate = rs.getDate("registrationDate");
		String _failureCode = rs.getString("failureCode");
		ProductionFailureReport _productionFailureReport = new ProductionFailureReport(_idProductionFailureReport,
				_timestamp, _state, _registrationDate, _failureCode, _product,
				_testerReport);

		// Retreive failures
		FailureDao _failureDao = new FailureDaoImpl(this.cnxProduct,
				this.cnxOperator);
		_failureDao.getFailures(_productionFailureReport);

		// Retreive customer comment
		CustomerCommentDao _customerCommentDao = new CustomerCommentDaoImpl(
				this.cnxProduct);
		_customerCommentDao.getCustomerComment(_productionFailureReport);

		// Retreive failureReport comment
		FailureReportCommentDao _failureReportCommentDao = new FailureReportCommentDaoImpl(
				this.cnxProduct);
		_failureReportCommentDao.getFailureReportComment(_productionFailureReport);

		return _productionFailureReport;
	}
}
