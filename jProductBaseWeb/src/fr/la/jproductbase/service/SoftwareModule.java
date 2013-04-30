package fr.la.jproductbase.service;

import java.util.List;

import fr.la.jproductbase.dao.SoftwareDao;
import fr.la.jproductbase.metier.Software;

public class SoftwareModule {

	SoftwareDao _softwareDao;
	
	public SoftwareModule(SoftwareDao softwareDao) {
		_softwareDao = softwareDao;
	}

	// 20-12-11 : RMO : Creation de la méthode
	public Software addSoftware(int state, String name, String version) {
		return _softwareDao.addSoftware(state, name, version);
	}

	public Software addSoftware(String name, String version) {
		return _softwareDao.addSoftware(name, version);
	}

	public Software getSoftware(String name, String version) {
		return _softwareDao.getSoftware(name, version);
	}

	// 20-12-11 : RMO : Creation de la méthode
	public Software getSoftware(int idSoftware) {
		return _softwareDao.getSoftware(idSoftware);
	}

	public List<Software> getSoftwares() {
		return _softwareDao.getSoftwares();
	}

	public List<Software> getActiveSoftwares() {
		return _softwareDao.getActiveSoftwares();
	}

	// 20-12-11 : RMO : Creation de la méthode
	public void updateSoftware(Software softwareToUpdate) {
		_softwareDao.updateSoftware(softwareToUpdate);
	}
}
