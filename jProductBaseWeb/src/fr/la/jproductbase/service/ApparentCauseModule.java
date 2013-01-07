package fr.la.jproductbase.service;

import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;

import fr.la.jproductbase.dao.ApparentCauseCustomerDao;
import fr.la.jproductbase.dao.ApparentCauseCustomerDaoImpl;
import fr.la.jproductbase.dao.ApparentCauseDao;
import fr.la.jproductbase.dao.ApparentCauseDaoException;
import fr.la.jproductbase.dao.ApparentCauseDaoImpl;
import fr.la.jproductbase.dao.ConnectionProduct;
import fr.la.jproductbase.metier.ApparentCause;
import fr.la.jproductbase.metier.ApparentCauseCustomer;

public class ApparentCauseModule {
	private ConnectionProduct cnxProduct;

	protected ApparentCauseModule(ConnectionProduct cnxProduct) {
		this.cnxProduct = cnxProduct;
	}

	protected List<ApparentCauseCustomer> getApparentCausesCustomer()
			throws SQLException {
		ApparentCauseCustomerDao _apparentCauseCustomerDao = new ApparentCauseCustomerDaoImpl(
				this.cnxProduct);

		List<ApparentCauseCustomer> _apparentCausesCustomer = _apparentCauseCustomerDao
				.getApparentCausesCustomer();

		return _apparentCausesCustomer;
	}

	protected List<ApparentCauseCustomer> getActiveApparentCausesCustomer()
			throws SQLException {
		ApparentCauseCustomerDao _apparentCauseCustomerDao = new ApparentCauseCustomerDaoImpl(
				this.cnxProduct);

		List<ApparentCauseCustomer> _apparentCausesCustomer = _apparentCauseCustomerDao
				.getActiveApparentCausesCustomer();

		return _apparentCausesCustomer;
	}

	protected ApparentCauseCustomer getApparentCauseCustomer(
			int idApparentCauseCustomer) throws SQLException {
		ApparentCauseCustomerDao _apparentCauseCustomerDao = new ApparentCauseCustomerDaoImpl(
				this.cnxProduct);

		ApparentCauseCustomer _apparentCauseCustomer = _apparentCauseCustomerDao
				.getApparentCauseCustomer(idApparentCauseCustomer);

		return _apparentCauseCustomer;
	}

	protected ApparentCauseCustomer setApparentCauseCustomer(
			ApparentCauseCustomer apparentCauseCustomer, int state,
			String description) throws Exception {
		ApparentCauseCustomer _apparentCauseCustomer = apparentCauseCustomer;

		try {
			this.cnxProduct.getCnx().setAutoCommit(false);

			if (null == _apparentCauseCustomer) {
				// New apparentCauseCustomer
				// Add
				_apparentCauseCustomer = new ApparentCauseCustomer(0, null,
						state, description);
				_apparentCauseCustomer = this
						.addApparentCauseCustomer(_apparentCauseCustomer);
			} else {
				// Existing apparentCauseCustomer
				_apparentCauseCustomer.setState(state);
				_apparentCauseCustomer.setDescription(description);
				// Update
				this.updateApparentCauseCustomer(_apparentCauseCustomer);
			}

			// Commit
			this.cnxProduct.getCnx().commit();
		} catch (SQLException e) {
			this.cnxProduct.getCnx().rollback();
			throw new Exception(e.getMessage());
		}

		return _apparentCauseCustomer;
	}

	/*
	 * Ajout d'un apparentCauseCustomer dans la bdd
	 */
	private ApparentCauseCustomer addApparentCauseCustomer(
			ApparentCauseCustomer apparentCauseCustomer) throws SQLException,
			NamingException {
		ApparentCauseCustomerDao _apparentCauseCustomerDao = new ApparentCauseCustomerDaoImpl(
				this.cnxProduct);

		ApparentCauseCustomer _acc = null;
		try {
			this.cnxProduct.getCnx().setAutoCommit(false);
			_acc = _apparentCauseCustomerDao
					.addApparentCauseCustomer(apparentCauseCustomer);
			this.cnxProduct.getCnx().commit();
		} catch (ApparentCauseDaoException e) {
			this.cnxProduct.getCnx().rollback();
			e.printStackTrace();
		}

		return _acc;
	}

	/*
	 * Mise a jour d'un apparentCauseCustomer dans la bdd
	 */
	private void updateApparentCauseCustomer(
			ApparentCauseCustomer apparentCauseCustomer) throws SQLException,
			NamingException {
		ApparentCauseCustomerDao _apparentCauseCustomerDao = new ApparentCauseCustomerDaoImpl(
				this.cnxProduct);

		try {
			this.cnxProduct.getCnx().setAutoCommit(false);
			_apparentCauseCustomerDao
					.updateApparentCauseCustomer(apparentCauseCustomer);
			this.cnxProduct.getCnx().commit();
		} catch (ApparentCauseDaoException e) {
			this.cnxProduct.getCnx().rollback();
			e.printStackTrace();
		}
	}

	/*
	 * Suppression d'un apparentCauseCustomer dans la bdd
	 */
	protected void removeApparentCauseCustomer(
			ApparentCauseCustomer apparentCauseCustomer) throws SQLException {
		ApparentCauseCustomerDao _apparentCauseCustomerDao = new ApparentCauseCustomerDaoImpl(
				this.cnxProduct);

		_apparentCauseCustomerDao
				.removeApparentCauseCustomer(apparentCauseCustomer);
	}

	protected List<ApparentCause> getApparentCauses() throws SQLException {
		ApparentCauseDao _apparentCauseDao = new ApparentCauseDaoImpl(
				this.cnxProduct);

		List<ApparentCause> _apparentCauses = _apparentCauseDao
				.getApparentCauses();

		return _apparentCauses;
	}

	protected ApparentCause getApparentCause(int idApparentCause)
			throws SQLException {
		ApparentCauseDao _apparentCauseDao = new ApparentCauseDaoImpl(
				this.cnxProduct);

		ApparentCause _apparentCause = _apparentCauseDao
				.getApparentCause(idApparentCause);

		return _apparentCause;
	}

	/*
	 * Enregistrement d'un apparentCauseCustomer dans la bdd
	 */
	protected ApparentCause setApparentCause(ApparentCause apparentCause,
			int state, String description,
			ApparentCauseCustomer apparentCauseCustomer) throws Exception {
		ApparentCause _apparentCause = apparentCause;

		try {
			this.cnxProduct.getCnx().setAutoCommit(false);

			if (null == _apparentCause) {
				// New apparentCause
				// Add
				_apparentCause = new ApparentCause(0, null, state, description,
						apparentCauseCustomer);
				_apparentCause = this.addApparentCause(_apparentCause);
			} else {
				// Existing apparentCause
				System.out.println("modifier");
				_apparentCause.setState(state);
				_apparentCause.setDescription(description);
				_apparentCause.setApparentCauseCustomer(apparentCauseCustomer);
				// Update
				this.updateApparentCause(_apparentCause);
			}

			// Commit
			this.cnxProduct.getCnx().commit();
		} catch (SQLException e) {
			this.cnxProduct.getCnx().rollback();
			throw new Exception(e.getMessage());
		}

		return _apparentCause;
	}

	/*
	 * Ajout d'un apparentCause dans la bdd
	 */
	private ApparentCause addApparentCause(ApparentCause apparentCause)
			throws SQLException, NamingException {
		ApparentCauseDao _apparentCauseDao = new ApparentCauseDaoImpl(
				this.cnxProduct);
		ApparentCause _apparent = null;
		try {
			this.cnxProduct.getCnx().setAutoCommit(false);
			_apparent = _apparentCauseDao.addApparentCause(apparentCause);
			this.cnxProduct.getCnx().commit();
		} catch (ApparentCauseDaoException e) {
			this.cnxProduct.getCnx().rollback();
			e.printStackTrace();
		}
		return _apparent;
	}

	/*
	 * Mise a jour d'un apparentCause dans la bdd
	 */
	private void updateApparentCause(ApparentCause apparentCause)
			throws SQLException, ApparentCauseDaoException {
		ApparentCauseDao _apparentCauseDao = new ApparentCauseDaoImpl(
				this.cnxProduct);

		_apparentCauseDao.updateApparentCause(apparentCause);
	}

	/*
	 * Suppression d'un apparentCause dans la bdd
	 */
	protected void removeApparentCause(ApparentCause apparentCause)
			throws SQLException {
		ApparentCauseDao _apparentCauseDao = new ApparentCauseDaoImpl(
				this.cnxProduct);

		_apparentCauseDao.removeApparentCause(apparentCause);
	}

}
