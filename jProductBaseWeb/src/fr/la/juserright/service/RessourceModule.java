package fr.la.juserright.service;

import java.sql.SQLException;
import java.util.List;

import fr.la.juserright.dao.ConnectionUserRight;
import fr.la.juserright.dao.FactoryDAO;
import fr.la.juserright.dao.RessourceDAO;
import fr.la.juserright.metier.Autorisation;
import fr.la.juserright.metier.Ressource;
import fr.la.juserright.metier.Ressource_Ressource;

public class RessourceModule {
	
	private ConnectionUserRight cnxUserRight;

	protected RessourceModule(ConnectionUserRight cnxUserRight) {
		this.cnxUserRight = cnxUserRight;
	}
	
	protected void createRessource(Ressource _ressource) throws SQLException{
		
		RessourceDAO _ressourceDao = FactoryDAO.getRessourceDAO(this.cnxUserRight);
		
		_ressourceDao.create(_ressource);
		
	}
	
	protected List<Ressource> getAllRessource() throws SQLException {
		
		RessourceDAO _ressourceDao = FactoryDAO.getRessourceDAO(this.cnxUserRight);
		
		List<Ressource> _ressource = _ressourceDao.readAll();
		
		return _ressource;
	}
	
	protected void updateRessource(Ressource _ressource) throws SQLException{
		
		RessourceDAO _ressourceDao = FactoryDAO.getRessourceDAO(this.cnxUserRight);
		
		_ressourceDao.update(_ressource);		
	}
	
	
	protected void delete(Ressource _ressource) throws SQLException{
		
		RessourceDAO _ressourceDao = FactoryDAO.getRessourceDAO(this.cnxUserRight);
		
		_ressourceDao.delete(_ressource);
	}

	
	protected Ressource getRessource(int idressource) throws SQLException{
		
		RessourceDAO _ressourceDao = FactoryDAO.getRessourceDAO(this.cnxUserRight);
		
		Ressource _ressource = _ressourceDao.read(idressource);
		
		return _ressource;
	}

	
	protected Ressource getRessource(Ressource object) throws SQLException{
		
		RessourceDAO _ressourceDao = FactoryDAO.getRessourceDAO(this.cnxUserRight);
		
		Ressource _ressource = _ressourceDao.read(object);
		
		return _ressource;
	}

	
	protected List<Ressource> selectRessourceNotUsedByRole(List<Autorisation> _listAutorisation) throws SQLException{
		
		RessourceDAO _ressourceDao = FactoryDAO.getRessourceDAO(this.cnxUserRight);
		
		List<Ressource> _ressource = _ressourceDao.selectRessourceNotUsedByRole(_listAutorisation);
		
		return _ressource;
	}
	


	protected Ressource createRessourceWithRR(Ressource _ressource) throws SQLException {

		RessourceDAO _ressourceDao = FactoryDAO.getRessourceDAO(this.cnxUserRight);

		Ressource __ressource = _ressourceDao.createWithRR(_ressource);
		
		return __ressource;

	}
	
	
	
	protected void updateidRR(Ressource _ressource, Ressource_Ressource _rr) throws SQLException{
		
		RessourceDAO _ressourceDao = FactoryDAO.getRessourceDAO(this.cnxUserRight);
		
		_ressourceDao.updateidRR(_ressource, _rr);
		
	}
	


	protected String getMenuWithRR(int idressource_ressource) throws SQLException {

		RessourceDAO _ressourceDao = FactoryDAO.getRessourceDAO(this.cnxUserRight);

		String _menu = _ressourceDao.getMenuWithRR(idressource_ressource);
		
		return _menu;

	}
	


	protected Ressource getRessourceByPath(String Path) throws SQLException {

		RessourceDAO _ressourceDao = FactoryDAO.getRessourceDAO(this.cnxUserRight);

		Ressource __ressource = _ressourceDao.getRessourceByPath(Path);
		
		return __ressource;

	}
	


	protected Ressource getRessourceByMenu(String Menu) throws SQLException {

		RessourceDAO _ressourceDao = FactoryDAO.getRessourceDAO(this.cnxUserRight);

		Ressource __ressource = _ressourceDao.getRessourceByMenu(Menu);
		
		return __ressource;

	}
	


	protected Ressource getRessourceByMenuForUpdate(String Menu, int idressource) throws SQLException {

		RessourceDAO _ressourceDao = FactoryDAO.getRessourceDAO(this.cnxUserRight);

		Ressource __ressource = _ressourceDao.getRessourceByMenuForUpdate(Menu, idressource);
		
		return __ressource;

	}
	


	protected Ressource getRessourceByPathForUpdate(String Path, int idressource) throws SQLException {

		RessourceDAO _ressourceDao = FactoryDAO.getRessourceDAO(this.cnxUserRight);

		Ressource __ressource = _ressourceDao.getRessourceByPathForUpdate(Path, idressource);
		
		return __ressource;

	}
	
	protected void updateToNullIDRR(int idressource_ressource) throws SQLException {

		RessourceDAO _ressourceDao = FactoryDAO.getRessourceDAO(this.cnxUserRight);

		_ressourceDao.updateToNullIDRR(idressource_ressource);

	}
}