package fr.la.jproductbase.service;

import java.util.List;

import fr.la.jproductbase.dao.ApparentCauseCustomerDao;
import fr.la.jproductbase.dao.ApparentCauseDao;
import fr.la.jproductbase.metier.ApparentCause;
import fr.la.jproductbase.metier.ApparentCauseCustomer;

public class ApparentCauseModule {
	
	ApparentCauseCustomerDao _apparentCauseCustomerDao;
	ApparentCauseDao _apparentCauseDao;
	
	public ApparentCauseModule(ApparentCauseCustomerDao apparentCauseCustomerDao , ApparentCauseDao apparentCauseDao) {
		_apparentCauseCustomerDao = apparentCauseCustomerDao;
		_apparentCauseDao = apparentCauseDao;
	}

	public List<ApparentCauseCustomer> getApparentCausesCustomer() {
		List<ApparentCauseCustomer> _apparentCausesCustomer = _apparentCauseCustomerDao.getApparentCausesCustomer();
		return _apparentCausesCustomer;
	}

	public List<ApparentCauseCustomer> getActiveApparentCausesCustomer() {
		List<ApparentCauseCustomer> _apparentCausesCustomer = _apparentCauseCustomerDao.getActiveApparentCausesCustomer();
		return _apparentCausesCustomer;
	}

	public ApparentCauseCustomer getApparentCauseCustomer(int idApparentCauseCustomer) {
		ApparentCauseCustomer _apparentCauseCustomer = _apparentCauseCustomerDao.getApparentCauseCustomer(idApparentCauseCustomer);
		return _apparentCauseCustomer;
	}

	public ApparentCauseCustomer setApparentCauseCustomer(ApparentCauseCustomer apparentCauseCustomer, int state, String description) {
		ApparentCauseCustomer _apparentCauseCustomer = apparentCauseCustomer;

		if (null == _apparentCauseCustomer) {
			// New apparentCauseCustomer
			// Add
			_apparentCauseCustomer = new ApparentCauseCustomer(0, null,
					state, description);
			_apparentCauseCustomer = this.addApparentCauseCustomer(_apparentCauseCustomer);
		} else {
			// Existing apparentCauseCustomer
			_apparentCauseCustomer.setState(state);
			_apparentCauseCustomer.setDescription(description);
			// Update
			this.updateApparentCauseCustomer(_apparentCauseCustomer);
		}

		return _apparentCauseCustomer;
	}

	/*
	 * Ajout d'un apparentCauseCustomer dans la bdd
	 */
	private ApparentCauseCustomer addApparentCauseCustomer(ApparentCauseCustomer apparentCauseCustomer) {
		ApparentCauseCustomer _acc = null;
		_acc = _apparentCauseCustomerDao.addApparentCauseCustomer(apparentCauseCustomer);
		return _acc;
	}

	/*
	 * Mise a jour d'un apparentCauseCustomer dans la bdd
	 */
	private void updateApparentCauseCustomer(ApparentCauseCustomer apparentCauseCustomer) {
		_apparentCauseCustomerDao.updateApparentCauseCustomer(apparentCauseCustomer);
	}

	/*
	 * Suppression d'un apparentCauseCustomer dans la bdd
	 */
	public void removeApparentCauseCustomer(ApparentCauseCustomer apparentCauseCustomer) {
		_apparentCauseCustomerDao.removeApparentCauseCustomer(apparentCauseCustomer);
	}

	public List<ApparentCause> getApparentCauses() {
		List<ApparentCause> _apparentCauses = _apparentCauseDao.getApparentCauses();
		return _apparentCauses;
	}

	public ApparentCause getApparentCause(int idApparentCause) {
		ApparentCause _apparentCause = _apparentCauseDao.getApparentCause(idApparentCause);
		return _apparentCause;
	}

	/*
	 * Enregistrement d'un apparentCauseCustomer dans la bdd
	 */
	public ApparentCause setApparentCause(ApparentCause apparentCause, int state, String description, ApparentCauseCustomer apparentCauseCustomer) {
		ApparentCause _apparentCause = apparentCause;
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
		return _apparentCause;
	}

	/*
	 * Ajout d'un apparentCause dans la bdd
	 */
	private ApparentCause addApparentCause(ApparentCause apparentCause) {
		ApparentCause _apparent = _apparentCauseDao.addApparentCause(apparentCause);
		return _apparent;
	}

	/*
	 * Mise a jour d'un apparentCause dans la bdd
	 */
	private void updateApparentCause(ApparentCause apparentCause) {
		_apparentCauseDao.updateApparentCause(apparentCause);
	}

	/*
	 * Suppression d'un apparentCause dans la bdd
	 */
	public void removeApparentCause(ApparentCause apparentCause) {
		_apparentCauseDao.removeApparentCause(apparentCause);
	}

}
