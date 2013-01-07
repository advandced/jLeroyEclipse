package fr.la.jproductbase.service;

import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;

import fr.la.jproductbase.dao.ConnectionProduct;
import fr.la.jproductbase.dao.SoftwareDao;
import fr.la.jproductbase.dao.SoftwareDaoException;
import fr.la.jproductbase.dao.SoftwareDaoImpl;
import fr.la.jproductbase.metier.Software;

public class SoftwareModule {
	private ConnectionProduct cnxProduct;

	protected SoftwareModule(ConnectionProduct cnxProduct) {
		this.cnxProduct = cnxProduct;
	}

	// 20-12-11 : RMO : Creation de la méthode
	protected Software addSoftware(int state, String name, String version)
			throws SQLException, NamingException {
		SoftwareDao _softwareDao = new SoftwareDaoImpl(this.cnxProduct);

		Software _software = null;
		try {
			this.cnxProduct.getCnx().setAutoCommit(false);
			_software = _softwareDao.addSoftware(state, name, version);
			this.cnxProduct.getCnx().commit();
		} catch (SoftwareDaoException e) {
			this.cnxProduct.getCnx().rollback();
			e.printStackTrace();
		}

		return _software;
	}

	protected Software addSoftware(String name, String version)
			throws SQLException, NamingException {
		SoftwareDao _softwareDao = new SoftwareDaoImpl(this.cnxProduct);

		Software _software = null;
		try {
			this.cnxProduct.getCnx().setAutoCommit(false);
			_software = _softwareDao.addSoftware(name, version);
			this.cnxProduct.getCnx().commit();
		} catch (SoftwareDaoException e) {
			this.cnxProduct.getCnx().rollback();
			e.printStackTrace();
		}

		return _software;
	}

	protected Software getSoftware(String name, String version)
			throws SQLException {
		SoftwareDao _softwareDao = new SoftwareDaoImpl(this.cnxProduct);

		Software _software = _softwareDao.getSoftware(name, version);

		return _software;
	}

	// 20-12-11 : RMO : Creation de la méthode
	protected Software getSoftware(int idSoftware) throws SQLException {
		SoftwareDao _softwareDao = new SoftwareDaoImpl(this.cnxProduct);

		Software _software = _softwareDao.getSoftware(idSoftware);

		return _software;
	}

	protected List<Software> getSoftwares() throws SQLException {
		SoftwareDao _softwareDao = new SoftwareDaoImpl(this.cnxProduct);

		List<Software> _software = _softwareDao.getSoftwares();

		return _software;
	}

	protected List<Software> getActiveSoftwares() throws SQLException {
		SoftwareDao _softwareDao = new SoftwareDaoImpl(this.cnxProduct);

		List<Software> _softwares = _softwareDao.getActiveSoftwares();

		return _softwares;
	}

	// 20-12-11 : RMO : Creation de la méthode
	protected void updateSoftware(Software softwareToUpdate)
			throws SQLException, NamingException {
		SoftwareDao _softwareDao = new SoftwareDaoImpl(this.cnxProduct);

		try {
			this.cnxProduct.getCnx().setAutoCommit(false);
			_softwareDao.updateSoftware(softwareToUpdate);
			this.cnxProduct.getCnx().commit();
		} catch (SoftwareDaoException e) {
			this.cnxProduct.getCnx().rollback();
			e.printStackTrace();
		}
	}
}
