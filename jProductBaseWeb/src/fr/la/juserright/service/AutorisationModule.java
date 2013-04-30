package fr.la.juserright.service;

import java.util.List;

import fr.la.juserright.dao.AutorisationDAO;
import fr.la.juserright.metier.Autorisation;

public class AutorisationModule {
	
	AutorisationDAO _autorisationDao;

	public AutorisationModule(AutorisationDAO autorisationDao) {
		_autorisationDao = autorisationDao;
	}
	
	public void createAutorisation(Autorisation _autorisation) {
		_autorisationDao.create(_autorisation);
	}
	
	public List<Autorisation> getAllAutorisation() {
		List<Autorisation> _autorisation = _autorisationDao.readAll();
		return _autorisation;
	}
	
	public List<Autorisation> getAutorisationWithIdPermission(int idpermission) {
		List<Autorisation> _autorisation = _autorisationDao.getAutorisationWithIdPermission(idpermission);
		return _autorisation;
	}
	
	public List<Autorisation> getAutorisationWithIdRessource(int idressource) {
		List<Autorisation> _autorisation = _autorisationDao.getAutorisationWithIdRessource(idressource);
		return _autorisation;
	}

	public List<Autorisation> getAutorisationWithIdRole(int idrole) {
		List<Autorisation> _autorisation = _autorisationDao.getAutorisationWithIdRole(idrole);
		return _autorisation;
	}
	
	public void updateAutorisation(Autorisation _autorisation) {
		_autorisationDao.update(_autorisation);
	}
	
	public void deleteAutorisation(Autorisation _autorisation) {
		_autorisationDao.delete(_autorisation);
	}
	
	public Autorisation checkAutorisationExists(Autorisation _autorisation) {
		Autorisation __autorisation = _autorisationDao.checkAutorisationExists(_autorisation);
		return __autorisation;
	}
	
	public List<Autorisation> getAutorisationByLogin(String _login) {
		List<Autorisation> __autorisation = _autorisationDao.getAutorisationByLogin(_login);
		return __autorisation;
	}
}