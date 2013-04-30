package fr.la.juserright.service;

import java.util.List;

import fr.la.juserright.dao.RessourceDAO;
import fr.la.juserright.metier.Autorisation;
import fr.la.juserright.metier.Ressource;
import fr.la.juserright.metier.Ressource_Ressource;

public class RessourceModule {
	
	RessourceDAO _ressourceDao;

	public RessourceModule(RessourceDAO ressourceDao) {
		_ressourceDao = ressourceDao;
	}
	
	public void createRessource(Ressource _ressource) {
		_ressourceDao.create(_ressource);
	}
	
	public List<Ressource> getAllRessource() {
		List<Ressource> _ressource = _ressourceDao.readAll();
		return _ressource;
	}
	
	public void updateRessource(Ressource _ressource) {
		_ressourceDao.update(_ressource);		
	}
	
	public void delete(Ressource _ressource) {
		_ressourceDao.delete(_ressource);
	}

	public Ressource getRessource(int idressource) {
		Ressource _ressource = _ressourceDao.read(idressource);
		return _ressource;
	}
	
	public Ressource getRessource(Ressource object) {
		Ressource _ressource = _ressourceDao.read(object);
		return _ressource;
	}
	
	public List<Ressource> selectRessourceNotUsedByRole(List<Autorisation> _listAutorisation) {
		List<Ressource> _ressource = _ressourceDao.selectRessourceNotUsedByRole(_listAutorisation);
		return _ressource;
	}
	
	public Ressource createRessourceWithRR(Ressource _ressource) {
		Ressource __ressource = _ressourceDao.createWithRR(_ressource);
		return __ressource;

	}
	
	public void updateidRR(Ressource _ressource, Ressource_Ressource _rr) {
		_ressourceDao.updateidRR(_ressource, _rr);
	}
	
	public String getMenuWithRR(int idressource_ressource) {
		String _menu = _ressourceDao.getMenuWithRR(idressource_ressource);
		return _menu;
	}

	public Ressource getRessourceByPath(String Path) {
		Ressource __ressource = _ressourceDao.getRessourceByPath(Path);
		return __ressource;
	}
	
	public Ressource getRessourceByMenu(String Menu) {
		Ressource __ressource = _ressourceDao.getRessourceByMenu(Menu);
		return __ressource;
	}
	
	public Ressource getRessourceByMenuForUpdate(String Menu, int idressource) {
		Ressource __ressource = _ressourceDao.getRessourceByMenuForUpdate(Menu, idressource);
		return __ressource;
	}

	public Ressource getRessourceByPathForUpdate(String Path, int idressource) {
		Ressource __ressource = _ressourceDao.getRessourceByPathForUpdate(Path, idressource);
		return __ressource;
	}
	
	public void updateToNullIDRR(int idressource_ressource) {
		_ressourceDao.updateToNullIDRR(idressource_ressource);
	}
}