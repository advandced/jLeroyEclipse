package fr.la.jproductbase.dao;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.naming.NamingException;

import fr.la.configfilereader.ConfigFileReaderException;
import fr.la.jproductbase.metier.AfterSaleCom;
import fr.la.jproductbase.metier.AfterSaleReport;
import fr.la.jproductbase.service.ServiceInterface;
//import fr.la.jproductbase.metier.ApparentCause;
//import fr.la.jproductbase.metier.Product;

public class AfterSaleComImpl implements AfterSaleComDao {
	private static String exceptionMsg = "AfterSaleCom inconnu dans la base de données.";

	private ConnectionProduct cnxProduct;
	private ConnectionOperator cnxOperator;

	public AfterSaleComImpl(ConnectionProduct cnxProduct,
			ConnectionOperator cnxOperator) {
		this.cnxProduct = cnxProduct;
		this.cnxOperator = cnxOperator;
	}

	public List<AfterSaleCom> listAfterSaleCom() throws SQLException,
			ConfigFileReaderException, IOException {
		List<AfterSaleCom> _afterSaleCom = new ArrayList<AfterSaleCom>();
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			_stmt = this.cnxProduct.getCnx().prepareStatement(
					"SELECT * FROM afterSaleCom ORDER BY quotationNumber");
			_rs = _stmt.executeQuery();

			while (_rs.next()) {
				AfterSaleCom _afterSaleComRS = this.getAfterSaleCom(_rs);
				_afterSaleCom.add(_afterSaleComRS);
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
		return _afterSaleCom;
	}

	public void addDevisPrea(AfterSaleCom aftersalecom) throws SQLException,
			AfterSaleComDaoException {
		PreparedStatement _stmt = null;
		ResultSet _rs = null;
		try {
			_stmt = this.cnxProduct
					.getCnx()
					.prepareStatement(
							"SELECT idAfterSaleCom FROM afterSaleCom WHERE idAfterSaleCom = ?;");
			_stmt.setInt(1, aftersalecom.getIdAfterSaleCom());
			_rs = _stmt.executeQuery();
			_rs.last();
			if (_rs.getRow() == 0) {
				try {
					java.sql.Date _quotationdate = new java.sql.Date(
							aftersalecom.getQuotationDate().getTime());

					_stmt = this.cnxProduct
							.getCnx()
							.prepareStatement(
									"INSERT INTO afterSaleCom (quotationNumber,quotationDate,"
											+ "SAVPrice,quotationComment,idAfterSaleReport)"
											+ " VALUES (?, ?, ?, ?, ?);");

					_stmt.setString(1, aftersalecom.getQuotationNumber());
					_stmt.setDate(2, _quotationdate);
					_stmt.setFloat(3, aftersalecom.getSavPrice());
					_stmt.setString(4, aftersalecom.getQuotationComment());
					_stmt.setInt(5, aftersalecom.getAfterSaleReport()
							.getIdAfterSaleReport());

					_stmt.executeUpdate();

					_stmt = this.cnxProduct.getCnx().prepareStatement(
							"SELECT * FROM afterSaleCom "
									+ "WHERE (quotationNumber = ?) "
									+ "AND (quotationDate = ?) "
									+ "AND (SAVPrice = ? ) "
									+ "AND (quotationComment = ?) "
									+ "AND (idAfterSaleReport = ?);");

					_stmt.setString(1, aftersalecom.getQuotationNumber());
					_stmt.setDate(2, _quotationdate);
					_stmt.setFloat(3, aftersalecom.getSavPrice());
					_stmt.setString(4, aftersalecom.getQuotationComment());
					_stmt.setInt(5, aftersalecom.getAfterSaleReport()
							.getIdAfterSaleReport());

					_rs = _stmt.executeQuery();
					if (_rs.next()) {

					} else {
						throw new AfterSaleComDaoException(exceptionMsg);
					}
				} finally {
					if (null != _stmt) {
						_stmt.close();
					}
				}
			}
			if (_rs.getRow() == 1) {
				try {
					java.sql.Date _quotationdate = new java.sql.Date(
							aftersalecom.getQuotationDate().getTime());

					_stmt = this.cnxProduct.getCnx().prepareStatement(
							"UPDATE afterSaleCom "
									+ "SET quotationNumber = ?, "
									+ "quotationDate = ?, " + "SAVPrice = ?, "
									+ "quotationComment = ?, "
									+ "idAfterSaleReport = ? "
									+ "WHERE idAfterSaleCom = ?;");

					_stmt.setString(1, aftersalecom.getQuotationNumber());
					_stmt.setDate(2, _quotationdate);
					_stmt.setFloat(3, aftersalecom.getSavPrice());
					_stmt.setString(4, aftersalecom.getQuotationComment());
					_stmt.setInt(5, aftersalecom.getAfterSaleReport()
							.getIdAfterSaleReport());
					_stmt.setInt(6, aftersalecom.getIdAfterSaleCom());

					_stmt.executeUpdate();

					_stmt = this.cnxProduct.getCnx().prepareStatement(
							"SELECT * FROM afterSaleCom "
									+ "WHERE (idAfterSaleCom = ?);");

					_stmt.setInt(1, aftersalecom.getIdAfterSaleCom());

					_rs = _stmt.executeQuery();
					if (_rs.next()) {

					} else {
						throw new AfterSaleComDaoException(exceptionMsg);
					}
				} finally {
					if (null != _stmt) {
						_stmt.close();
					}
				}
			}
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (null != _stmt) {
				_stmt.close();
			}
			if (null != _rs) {
				_rs.close();
			}
		}
	}

	/*
	 * Renvoi les AfterSalecCom avec un SAVOrderNumber NULL
	 */
	public List<AfterSaleCom> rechercheNumCmd(String rechercher)
			throws SQLException, ConfigFileReaderException, IOException {
		List<AfterSaleCom> _afterSaleCom = new ArrayList<AfterSaleCom>();
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {

			String rqt = "SELECT *"
					+ " FROM aftersalecom"
					+ " WHERE SAVOrderNumber IS NULL AND SAVOrderDate IS NULL AND quotationNumber LIKE '%"
					+ rechercher + "%';";
			_stmt = this.cnxProduct.getCnx().prepareStatement(rqt);
			_rs = _stmt.executeQuery();

			while (_rs.next()) {
				AfterSaleCom _afterSaleComRS = this.getAfterSaleCom(_rs);
				_afterSaleCom.add(_afterSaleComRS);
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
		return _afterSaleCom;
	}

	/*
	 * 
	 */
	public void addCmd(AfterSaleCom aftersalecom) throws SQLException,
			AfterSaleComDaoException {
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {

			java.sql.Date _savorderdate = new java.sql.Date(aftersalecom
					.getSavOrderDate().getTime());

			_stmt = this.cnxProduct.getCnx().prepareStatement(
					"UPDATE aftersalecom "
							+ "SET SAVOrderNumber = ?, SAVOrderDate = ? "
							+ "WHERE idafterSaleCom = ?;");

			_stmt.setInt(1, aftersalecom.getSavOrderNumber());
			_stmt.setDate(2, _savorderdate);
			_stmt.setInt(3, aftersalecom.getIdAfterSaleCom());

			_stmt.executeUpdate();

			_stmt = this.cnxProduct.getCnx().prepareStatement(
					"SELECT * FROM afterSaleCom "
							+ "WHERE (idafterSaleCom = ?);");

			_stmt.setInt(1, aftersalecom.getIdAfterSaleCom());

			_rs = _stmt.executeQuery();
			if (_rs.next()) {

			} else {
				throw new AfterSaleComDaoException(exceptionMsg);
			}
		} catch (NamingException e) {
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

	public AfterSaleCom getAfterSaleCom(String idaftersalecom)
			throws SQLException, ConfigFileReaderException, IOException {

		AfterSaleCom _afterSaleCom = new AfterSaleCom();
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {

			String rqt = "SELECT *" + " FROM aftersalecom"
					+ " WHERE idafterSaleCom LIKE '%" + idaftersalecom + "%';";
			_stmt = this.cnxProduct.getCnx().prepareStatement(rqt);
			_rs = _stmt.executeQuery();

			while (_rs.next()) {
				_afterSaleCom = this.getAfterSaleCom(_rs);
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
		return _afterSaleCom;
	}

	public void updateCmd(AfterSaleCom _aftersalecom) throws SQLException,
			AfterSaleComDaoException {
		PreparedStatement _stmt = null;
		try {

			String rqt = "UPDATE aftersalecom SET ";
			if (rqt.length() == 24) {
				if (null != _aftersalecom.getQuotationNumber()) {
					if (rqt.length() == 24) {
						rqt += "quotationNumber = '"
								+ _aftersalecom.getQuotationNumber() + "'";
					}
				}
			}

			if (null != _aftersalecom.getQuotationDate()
					&& !_aftersalecom.getQuotationDate().equals("null")
					&& !_aftersalecom.getQuotationDate().equals("")) {

				java.sql.Date _quotationdate = new java.sql.Date(_aftersalecom
						.getQuotationDate().getTime());
				rqt += ", quotationDate = '" + _quotationdate + "'";
			} else {
				rqt += ", quotationDate = null";
			}
			if (null != _aftersalecom.getSavPrice()
					&& !_aftersalecom.getSavPrice().equals("null")
					&& !_aftersalecom.getSavPrice().equals("")) {
				rqt += ", SAVPrice = " + _aftersalecom.getSavPrice() + "";
			} else {
				rqt += ", SAVPrice = null";
			}
			if (null != _aftersalecom.getQuotationComment()
					&& !_aftersalecom.getQuotationComment().equals("null")
					&& !_aftersalecom.getQuotationComment().equals("")) {
				rqt += ", quotationComment = '"
						+ _aftersalecom.getQuotationComment() + "'";
			} else {
				rqt += ", quotationComment = null";
			}
			if (0 != _aftersalecom.getSavOrderNumber()) {
				rqt += ", SAVOrderNumber = "
						+ _aftersalecom.getSavOrderNumber() + "";
			} else {
				rqt += ", SAVOrderNumber = null";
			}
			if (null != _aftersalecom.getSavOrderDate()
					&& !_aftersalecom.getSavOrderDate().equals("null")
					&& !_aftersalecom.getSavOrderDate().equals("")) {

				java.sql.Date _savorderdate = new java.sql.Date(_aftersalecom
						.getSavOrderDate().getTime());

				rqt += ", SAVOrderDate = '" + _savorderdate + "'";
			} else {
				rqt += ", SAVOrderDate = null";
			}
			if (null != _aftersalecom.getOrderComment()
					&& !_aftersalecom.getOrderComment().equals("null")
					&& !_aftersalecom.getOrderComment().equals("")) {

				rqt += ", OrderComment = '" + _aftersalecom.getOrderComment()
						+ "'";
			} else {
				rqt += ", OrderComment = null";
			}
			if (null != _aftersalecom.getAfterSaleReport()) {
				rqt += ", idAfterSaleReport = "
						+ _aftersalecom.getAfterSaleReport()
								.getIdAfterSaleReport() + "";
			}

			rqt += " WHERE  idAfterSaleCom = "
					+ _aftersalecom.getIdAfterSaleCom() + ";";

			_stmt = this.cnxProduct.getCnx().prepareStatement(rqt);

			_stmt.executeUpdate();

			_stmt = this.cnxProduct.getCnx().prepareStatement(
					"SELECT * FROM afterSaleCom "
							+ "WHERE (idAfterSaleCom = ?);");

			_stmt.setInt(1, _aftersalecom.getIdAfterSaleCom());

			ResultSet _rs = null;

			_rs = _stmt.executeQuery();

			if (_rs.next()) {

			} else {
				throw new AfterSaleComDaoException(exceptionMsg);
			}
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (null != _stmt) {
				_stmt.close();
			}
		}
	}

	public List<AfterSaleCom> ListDevisPrea() throws SQLException,
			ConfigFileReaderException, IOException {
		List<AfterSaleCom> _afterSaleCom = new ArrayList<AfterSaleCom>();
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {

			String rqt = "SELECT R.idAfterSaleReport, C.*"
					+ " FROM afterSaleReport AS R LEFT JOIN afterSaleCom AS C ON R.idAfterSaleReport = C.idAfterSaleReport"
					+ " WHERE R.firstAnalyseDate IS NOT NULL AND R.reparationDate IS NULL AND C.quotationDate IS NULL;";

			_stmt = this.cnxProduct.getCnx().prepareStatement(rqt);
			_rs = _stmt.executeQuery();

			while (_rs.next()) {
				int _idAfterSaleCom = _rs.getInt("idAfterSaleCom");
				Timestamp _timestamp = _rs.getTimestamp("Timestamp");
				int _state = _rs.getInt("State");
				String _quotationNumber = _rs.getString("quotationNumber");
				Date _quotationDate = _rs.getDate("quotationDate");
				Float _savprice = _rs.getFloat("SAVPrice");
				String _quotationcomment = _rs.getString("quotationComment");
				int _savordernumber = _rs.getInt("SAVOrderNumber");
				Date _savorderdate = _rs.getDate("SAVOrderDate");
				String _ordercomment = _rs.getString("OrderComment");
				AfterSaleReportDao _afterSaleReportDao = new AfterSaleReportDaoImpl(
						this.cnxProduct, this.cnxOperator);
				AfterSaleReport _AfterSaleReport = _afterSaleReportDao
						.getAfterSaleReport(_rs.getInt("R.idAfterSaleReport"));

				AfterSaleCom _afterSaleComTmp = new AfterSaleCom(
						_idAfterSaleCom, _quotationNumber, _quotationDate,
						_savprice, _quotationcomment, _savordernumber,
						_savorderdate, _ordercomment, _timestamp, _state,
						_AfterSaleReport);

				_afterSaleCom.add(_afterSaleComTmp);
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
		return _afterSaleCom;
	}

	public List<AfterSaleCom> getAfterSaleComExpedSAV() throws SQLException,
			ConfigFileReaderException, IOException {
		List<AfterSaleCom> _afterSaleCom = new ArrayList<AfterSaleCom>();
		List<AfterSaleReport> _afterSaleReport = new ArrayList<AfterSaleReport>();
		PreparedStatement _stmt = null;
		ResultSet _rs = null;
		try {
			_stmt = this.cnxProduct
					.getCnx()
					.prepareStatement(
							"SELECT idAfterSaleReport "
									+ "FROM afterSaleReport "
									+ "WHERE reparationDate IS NOT NULL and qualityControlDate "
									+ "IS NOT NULL and expeditionDate IS NULL;");
			_rs = _stmt.executeQuery();
			ServiceInterface moduleGlobal = new ServiceInterface();
			while (_rs.next()) {
				AfterSaleReport __afterSaleReport = moduleGlobal
						.getAfterSaleReport(_rs.getInt("idAfterSaleReport"));
				_afterSaleReport.add(__afterSaleReport);
			}
		} catch (NamingException e) {
			e.printStackTrace();
		}
		try {
			for (AfterSaleReport report : _afterSaleReport) {
				_stmt = this.cnxProduct.getCnx().prepareStatement(
						"SELECT * " + "FROM afterSaleCom "
								+ "WHERE idAfterSaleReport = "
								+ report.getIdAfterSaleReport() + ";");
				_rs = _stmt.executeQuery();

				while (_rs.next()) {
					AfterSaleCom __afterSaleCom = this.getAfterSaleCom(_rs);
					_afterSaleCom.add(__afterSaleCom);
				}
			}

			for (AfterSaleReport report : _afterSaleReport) {
				int _found = 0;
				for (AfterSaleCom com : _afterSaleCom) {
					if (report.getIdAfterSaleReport() == com
							.getAfterSaleReport().getIdAfterSaleReport()) {
						_found++;
					}
				}
				if (_found == 0) {
					AfterSaleCom ___afterSaleCom = new AfterSaleCom(report);
					_afterSaleCom.add(___afterSaleCom);
				}
			}

		} catch (NamingException e) {
			e.printStackTrace();
		} finally {
			if (null != _rs) {
				_rs.close();
			}
			if (null != _stmt) {
				_stmt.close();
			}
		}

		return _afterSaleCom;
	}

	public List<AfterSaleCom> getLazyRecapCom(int limit, int maxperpage)
			throws SQLException {
		List<AfterSaleCom> _aftersalecom = new ArrayList<AfterSaleCom>();
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			_stmt = this.cnxProduct.getCnx().prepareStatement(
					"SELECT * FROM aftersalecom LIMIT ?, ?;");
			_stmt.setInt(1, limit);
			_stmt.setInt(2, maxperpage);
			_rs = _stmt.executeQuery();

			while (_rs.next()) {
				AfterSaleCom _aftersalecomtmp = this.getAfterSaleCom(_rs);
				_aftersalecom.add(_aftersalecomtmp);
			}
		} catch (Exception e) {

		} finally {
			if (null != _rs) {
				_rs.close();
			}
			if (null != _stmt) {
				_stmt.close();
			}
		}
		return _aftersalecom;
	}

	public int countRecapCom() throws SQLException {
		PreparedStatement _stmt = null;
		ResultSet _rs = null;
		int count = 0;

		try {
			_stmt = this.cnxProduct.getCnx().prepareStatement(
					"SELECT count(*) FROM aftersalecom;");
			_rs = _stmt.executeQuery();
			if (_rs.next()) {
				count = _rs.getInt(1);
			}
		} catch (Exception e) {

		} finally {
			if (null != _rs) {
				_rs.close();
			}
			if (null != _stmt) {
				_stmt.close();
			}
		}
		return count;
	}

	public List<AfterSaleCom> getRepairDatetoDate(Date debut, Date fin)
			throws SQLException, ConfigFileReaderException, IOException {
		List<AfterSaleCom> _afterSaleCom = new ArrayList<AfterSaleCom>();
		PreparedStatement _stmt = null;
		ResultSet _rs = null;
	    java.sql.Date _debut = new java.sql.Date(debut.getTime());
	    java.sql.Date _fin = new java.sql.Date(fin.getTime());
		try {
			_stmt = this.cnxProduct.getCnx().prepareStatement(
					"SELECT * FROM aftersalecom WHERE SAVOrderDate BETWEEN ? AND ?;");
			_stmt.setDate(1, _debut);
			_stmt.setDate(2, _fin);
			_rs = _stmt.executeQuery();

			while (_rs.next()) {
				AfterSaleCom _afterSaleComRS = this.getAfterSaleCom(_rs);
				_afterSaleCom.add(_afterSaleComRS);
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
		return _afterSaleCom;
	}

	private AfterSaleCom getAfterSaleCom(ResultSet rs) throws SQLException,
			ConfigFileReaderException, IOException {

		AfterSaleReportDao _afterSaleReportDao = new AfterSaleReportDaoImpl(
				this.cnxProduct, this.cnxOperator);
		AfterSaleReport _AfterSaleReport = _afterSaleReportDao
				.getAfterSaleReport(rs.getInt("idAfterSaleReport"));

		int _idAfterSaleCom = rs.getInt("idAfterSaleCom");
		Timestamp _timestamp = rs.getTimestamp("Timestamp");
		int _state = rs.getInt("State");
		String _quotationNumber = rs.getString("quotationNumber");
		Date _quotationDate = rs.getDate("quotationDate");
		Float _savprice = rs.getFloat("SAVPrice");
		String _quotationcomment = rs.getString("quotationComment");
		int _savordernumber = rs.getInt("SAVOrderNumber");
		Date _savorderdate = rs.getDate("SAVOrderDate");
		String _ordercomment = rs.getString("OrderComment");

		AfterSaleCom _afterSaleCom = new AfterSaleCom(_idAfterSaleCom,
				_quotationNumber, _quotationDate, _savprice, _quotationcomment,
				_savordernumber, _savorderdate, _ordercomment, _timestamp,
				_state, _AfterSaleReport);

		return _afterSaleCom;
	}
}