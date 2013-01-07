package fr.la.juserright.service;

import java.sql.SQLException;
import java.util.List;

import fr.la.juserright.dao.AutorisationDAO;
import fr.la.juserright.dao.ConnectionUserRight;
import fr.la.juserright.dao.FactoryDAO;
import fr.la.juserright.metier.Autorisation;

public class AutorisationModule {
	
	private ConnectionUserRight cnxUserRight;

	protected AutorisationModule(ConnectionUserRight cnxUserRight) {
		this.cnxUserRight = cnxUserRight;
	}
	
	protected void createAutorisation(Autorisation _autorisation) throws SQLException {
		
		AutorisationDAO _autorisationDao = FactoryDAO.getAutorisationDAO(this.cnxUserRight);
		
		_autorisationDao.create(_autorisation);
		
	}
	
	protected List<Autorisation> getAllAutorisation() throws SQLException {
		
		AutorisationDAO _autorisationDao = FactoryDAO.getAutorisationDAO(this.cnxUserRight);
		
		List<Autorisation> _autorisation = _autorisationDao.readAll();
		
		return _autorisation;
		
	}
	
	protected List<Autorisation> getAutorisationWithIdPermission(int idpermission) throws SQLException {
		
		AutorisationDAO _autorisationDao = FactoryDAO.getAutorisationDAO(this.cnxUserRight);
		
		List<Autorisation> _autorisation = _autorisationDao.getAutorisationWithIdPermission(idpermission);
		
		return _autorisation;
		
	}
	
	protected List<Autorisation> getAutorisationWithIdRessource(int idressource) throws SQLException {
		
		AutorisationDAO _autorisationDao = FactoryDAO.getAutorisationDAO(this.cnxUserRight);
		
		List<Autorisation> _autorisation = _autorisationDao.getAutorisationWithIdRessource(idressource);
		
		return _autorisation;
		
	}

	protected List<Autorisation> getAutorisationWithIdRole(int idrole) throws SQLException {
		
		AutorisationDAO _autorisationDao = FactoryDAO.getAutorisationDAO(this.cnxUserRight);
		
		List<Autorisation> _autorisation = _autorisationDao.getAutorisationWithIdRole(idrole);
		
		return _autorisation;
		
	}
	
	protected void updateAutorisation(Autorisation _autorisation) throws SQLException {
		
		AutorisationDAO _autorisationDao = FactoryDAO.getAutorisationDAO(this.cnxUserRight);
		
		_autorisationDao.update(_autorisation);
		
	}
	
	protected void deleteAutorisation(Autorisation _autorisation) throws SQLException {
		
		AutorisationDAO _autorisationDao = FactoryDAO.getAutorisationDAO(this.cnxUserRight);
		
		_autorisationDao.delete(_autorisation);
		
	}
	
	protected Autorisation checkAutorisationExists(Autorisation _autorisation) throws SQLException {
		
		AutorisationDAO _autorisationDao = FactoryDAO.getAutorisationDAO(this.cnxUserRight);
		
		Autorisation __autorisation = _autorisationDao.checkAutorisationExists(_autorisation);
		
		return __autorisation;
		
	}
	
	protected List<Autorisation> getAutorisationByLogin(String _login) throws SQLException{

		AutorisationDAO _autorisationDao = FactoryDAO.getAutorisationDAO(this.cnxUserRight);
		
		List<Autorisation> __autorisation = _autorisationDao.getAutorisationByLogin(_login);
		
		return __autorisation;
		
	}
}