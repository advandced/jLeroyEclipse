package fr.la.jproductbase.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import fr.la.jproductbase.metier.AfterSaleReport;
import fr.la.jproductbase.metier.ElementChanged;
import fr.la.jproductbase.metier.Failure;
import fr.la.jproductbase.metier.ProductionFailureReport;
import fr.la.jproductbase.metier.Operator;
import fr.la.jproductbase.metier.Product;

public class FailureDaoImpl implements FailureDao {
	private static String exceptionMsg = "Défaut inconnu dans la base de données.";

	private ConnectionProduct cnxProduct;
	private ConnectionOperator cnxOperator;

	public FailureDaoImpl(ConnectionProduct cnxProduct,
			ConnectionOperator cnxOperator) {
		this.cnxProduct = cnxProduct;
		this.cnxOperator = cnxOperator;
	}

	@Override
	public List<Failure> getFailures(ProductionFailureReport productionFailureReport)
			throws SQLException {
		List<Failure> _failures = new ArrayList<Failure>();
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			_stmt = this.cnxProduct.getCnx().prepareStatement(
					"SELECT * FROM failure " + "WHERE (idProductionFailureReport=?)");
			_stmt.setInt(1, productionFailureReport.getIdProductionFailureReport());
			_rs = _stmt.executeQuery();

			while (_rs.next()) {
				Failure _failure = this.getFailure(_rs);
				_failures.add(_failure);

				// Update failureReport object
				productionFailureReport.addFailure(_failure);
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

		return _failures;
	}
	
	@Override
	public List<Failure> getFailures(AfterSaleReport afterSaleReport)
			throws SQLException {
		List<Failure> _failures = new ArrayList<Failure>();
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			_stmt = this.cnxProduct.getCnx().prepareStatement(
					"SELECT * FROM failure " + "WHERE (idAfterSaleReport=?)");
			_stmt.setInt(1, afterSaleReport.getIdAfterSaleReport());
			_rs = _stmt.executeQuery();

			while (_rs.next()) {
				Failure _failure = this.getFailure(_rs);
				_failures.add(_failure);
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
		//afterSaleReport.setFailures(_failures);
		return _failures;
	}

	@Override
	public Failure getFailure(int idFailure) throws SQLException {
		Failure _failure = null;
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			_stmt = this.cnxProduct.getCnx().prepareStatement(
					"SELECT * FROM failure WHERE idFailure=?");
			_stmt.setInt(1, idFailure);
			_rs = _stmt.executeQuery();

			if (_rs.next()) {
				_failure = this.getFailure(_rs);
			} else {
				_failure = null;
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

		return _failure;
	}

	@Override
	public Failure addFailure(Failure failure, ProductionFailureReport productionFailureReport)
			throws SQLException, FailureDaoException {
		System.out.println("avant insertion "+failure.getProduct().getIdProduct());
		Failure _failure = failure;
		java.sql.Date _diagnosisDate = new java.sql.Date(failure
				.getDiagnosisDate().getTime());
		PreparedStatement _stmt = null;
		ResultSet _rs = null;
		if(failure.getNewFailureCardChanged()==null){
			Failure _failureDismantedCard = new Failure();
			_failureDismantedCard.setIdFailure(0);
			failure.setNewFailureCardChanged(_failureDismantedCard);
		}
		//System.out.println("avant insertion "+failure.getElementsChanged());
		try {
			_stmt = this.cnxProduct
					.getCnx()
					.prepareStatement(
							"INSERT INTO failure "
									+ "(state, diagnosisDate,failureCause, cardFace, manufacturingTechnique, failureCode, dismantleCard, idOperator, idProduct, idProductionFailureReport,idFailureDismantleCard) "
									+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)");
			_stmt.setInt(1, 1);
			_stmt.setDate(2, _diagnosisDate);
			_stmt.setString(3, failure.getFailureCause());
			_stmt.setString(4, failure.getCardFace());
			_stmt.setString(5, failure.getManufacturingTechnique());
			_stmt.setString(6, failure.getFailureCode());
			_stmt.setBoolean(7, failure.isDismantleCard());
			_stmt.setInt(8, failure.getOperator().getIdOperator());
			_stmt.setInt(9, failure.getProduct().getIdProduct());
			_stmt.setInt(10, productionFailureReport.getIdProductionFailureReport());
			_stmt.setInt(11, failure.getNewFailureCardChanged().getIdFailure());
			_stmt.executeUpdate();

			_stmt = this.cnxProduct.getCnx()
					.prepareStatement(
							"SELECT * FROM failure "
									+ "WHERE (diagnosisDate=?) "
									+ " AND (failureCode=?) "
									+ " AND (idOperator=?) "
									+ " AND (idProduct=?)"
									+ " AND (idProductionFailureReport=?)");
			_stmt.setDate(1, _diagnosisDate);
			_stmt.setString(2, failure.getFailureCode());
			_stmt.setInt(3, failure.getOperator().getIdOperator());
			_stmt.setInt(4, failure.getProduct().getIdProduct());
			_stmt.setInt(5, productionFailureReport.getIdProductionFailureReport());

			_rs = _stmt.executeQuery();
			if (_rs.next()) {
				_failure = this.getFailure(_rs);
			} else {
				throw new FailureDaoException(exceptionMsg);
			}
		//	System.out.println("apres insertion "+failure.getElementsChanged());
			_failure.setElementsChanged(failure.getElementsChanged());
			// Update failureReport object
			productionFailureReport.addFailure(_failure);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			System.out.println("erreur" +e);
			e.printStackTrace();
		} finally {
			if (null != _rs) {
				_rs.close();
			}
			if (null != _stmt) {
				_stmt.close();
			}
		}

		System.out.println("valuer failure après insertion" + _failure);
		return _failure;
	}
	
	@Override
	public Failure addFailure(Failure failure, AfterSaleReport afterSaleReport)
			throws SQLException, FailureDaoException {
		Failure _failure = failure;
		java.sql.Date _diagnosisDate = new java.sql.Date(failure
				.getDiagnosisDate().getTime());
		PreparedStatement _stmt = null;
		ResultSet _rs = null;
		
		int idOperator = 0;
		if (null != failure.getOperator()) {
			idOperator = failure.getOperator().getIdOperator();
		}
		if(failure.getNewFailureCardChanged()==null){
			Failure _failureDismantedCard = new Failure();
			_failureDismantedCard.setIdFailure(0);
			failure.setNewFailureCardChanged(_failureDismantedCard);
		}

		try {
			_stmt = this.cnxProduct
					.getCnx()
					.prepareStatement(
							"INSERT INTO failure "
									+ "(state, diagnosisDate, failureCause, cardFace, imputationCode, failureCode, dismantleCard, idOperator, idProduct,idProductionFailureReport, idAfterSaleReport,idFailureDismantleCard) "
									+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)");
			_stmt.setInt(1, 1);
			_stmt.setDate(2, _diagnosisDate);
			//_stmt.setString(3, failure.getTopoRef());
			_stmt.setString(3, failure.getFailureCause());
			_stmt.setString(4, failure.getCardFace());
			_stmt.setString(5, failure.getImputationCode());
			_stmt.setString(6, failure.getFailureCode());
			_stmt.setBoolean(7, failure.isDismantleCard());
			_stmt.setInt(8, idOperator);
			_stmt.setInt(9, failure.getProduct().getIdProduct());
			_stmt.setInt(10, 0);
			_stmt.setInt(11, afterSaleReport.getIdAfterSaleReport());
			_stmt.setInt(12, failure.getNewFailureCardChanged().getIdFailure());
			
			_stmt.executeUpdate();

			_stmt = this.cnxProduct.getCnx()
					.prepareStatement(
							"SELECT * FROM failure "
									+ "WHERE (diagnosisDate=?) "
									+ " AND (failureCode=?) "
									+ " AND (idOperator=?) "
									+ " AND (idProduct=?)"
									+ " AND (idAfterSaleReport=?)");
			_stmt.setDate(1, _diagnosisDate);
			_stmt.setString(2, failure.getFailureCode());
			_stmt.setInt(3, idOperator);
			_stmt.setInt(4, failure.getProduct().getIdProduct());
			_stmt.setInt(5, afterSaleReport.getIdAfterSaleReport());

			_rs = _stmt.executeQuery();
			if (_rs.next()) {
				_failure = this.getFailure(_rs);
			} else {
				throw new FailureDaoException(exceptionMsg);
			}

			// Update failureReport object
			// afterSaleReport.addFailure(_failure);
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

		return _failure;
	}

	@Override
	public void updateFailure(Failure failure, ProductionFailureReport failureReport)
			throws SQLException, FailureDaoException {
		java.sql.Date _diagnosisDate = new java.sql.Date(failure
				.getDiagnosisDate().getTime());
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		int idOperator = 0;
		if (null != failure.getOperator()) {idOperator = failure.getOperator().getIdOperator();}
		if(failure.getNewFailureCardChanged()==null){
			Failure _failure = new Failure();
			//_failure.setIdFailure(0);
			failure.setNewFailureCardChanged(_failure);
		}
		//System.out.println("methode updateFailure 294 "+ failure +"insertion de getnewFailureChanged / "+ failure.getNewFailureCardChanged().getIdFailure());
		try {
			_stmt = this.cnxProduct
					.getCnx()
					.prepareStatement(
							"UPDATE failure "
									+ "SET state=?, diagnosisDate=?, failureCause=?, cardFace=?, manufacturingTechnique=?, imputationCode=?, failureCode=?, dismantleCard=?, idOperator=?, idProduct=?,idFailureDismantleCard = ?"
									+ " WHERE (idFailure=?)");
			_stmt.setInt(1, failure.getState());
			_stmt.setDate(2, _diagnosisDate);
			_stmt.setString(3, failure.getFailureCause());
			_stmt.setString(4, failure.getCardFace());
			_stmt.setString(5, failure.getManufacturingTechnique());
			_stmt.setString(6, failure.getImputationCode());
			_stmt.setString(7, failure.getFailureCode());
			_stmt.setBoolean(8, failure.isDismantleCard());
			_stmt.setInt(9, idOperator);
			_stmt.setInt(10, failure.getProduct().getIdProduct());
			_stmt.setInt(11, failure.getNewFailureCardChanged().getIdFailure());
			_stmt.setInt(12, failure.getIdFailure());
			
			_stmt.executeUpdate();

			// Update object
			_stmt = this.cnxProduct.getCnx().prepareStatement(
					"SELECT * FROM failure" + " WHERE (idFailure=?)");
			_stmt.setInt(1, failure.getIdFailure());

			_rs = _stmt.executeQuery();
			if (_rs.next()) {
				this.getFailure(_rs);
			} else {
				throw new FailureDaoException(exceptionMsg);
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
	public void updateFailure(Failure failure)
			throws SQLException, FailureDaoException {
		this.updateFailure(failure, null);
	}

	@Override
	public void removeFailure(Failure failure) throws SQLException {
		PreparedStatement _stmt = null;

		try {
			_stmt = this.cnxProduct.getCnx().prepareStatement(
					"DELETE FROM failure " + "WHERE (idFailure=?)");
			_stmt.setInt(1, failure.getIdFailure());
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
	 * Cr&eacute;er un d&eacute;faut &agrave; partir d'un enregistrement de la
	 * base de donn&eacute;es.
	 * 
	 * @param rs Enregistrement de la base de donn&eacute;es.
	 * 
	 * @return D&eacute;faut.
	 * 
	 * @throws SQLException
	 */
	private Failure getFailure(ResultSet rs) throws SQLException {
		
		// Retreive operator
		OperatorDao _operatorDao = new OperatorDaoImpl(this.cnxOperator);
		Operator _operator = _operatorDao.getOperator(rs.getInt("idOperator"));

		// Retreive Product
		ProductDao _productDao = new ProductDaoImpl(this.cnxProduct);
		Product _product = _productDao.getProduct(rs.getInt("idProduct"));

		// Failure
		int _idFailure = rs.getInt("idFailure");
		Timestamp _timestamp = rs.getTimestamp("timestamp");
		int _state = rs.getInt("state");
		Date _diagnosisDate = rs.getDate("diagnosisDate");
		String _failureCause = rs.getString("failureCause");
		//String _topoRef = rs.getString("topoRef");
		String _cardFace = rs.getString("cardFace");
		String _manufacturingTechnique = rs.getString("manufacturingTechnique");
		String _failureCode = rs.getString("failureCode");
		String _imputationCode = rs.getString("imputationCode");
		//boolean _beyondRepair = rs.getBoolean("beyondRepair");
		boolean _dismantleCard = rs.getBoolean("dismantleCard");
		
		int _idFailureDismantleCard = rs.getInt("idFailureDismantleCard");
		Failure _failureDismantleCard  = new Failure();
		
		 _failureDismantleCard = getFailure(_idFailureDismantleCard);
		
		Failure _failure = new Failure(_idFailure, _timestamp, _state,
				_diagnosisDate, "", _failureCause, _cardFace,
				_manufacturingTechnique, _failureCode, _imputationCode,
				false, _operator, _product, _dismantleCard,_failureDismantleCard);

		// Retreive elementChanged
		ElementChangedDao _elementChangedDao = new ElementChangedDaoImpl(
				this.cnxProduct);
		List<ElementChanged> elementsChanged = _elementChangedDao.getElementsChanged(_failure);
		_failure.setElementsChanged(elementsChanged);

		return _failure;
	}
}
