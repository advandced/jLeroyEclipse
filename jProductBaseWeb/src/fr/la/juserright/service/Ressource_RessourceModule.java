package fr.la.juserright.service;

import fr.la.juserright.dao.Ressource_RessourceDAO;
import fr.la.juserright.metier.Ressource_Ressource;

public class Ressource_RessourceModule {

	Ressource_RessourceDAO _rrDAO;

	public Ressource_RessourceModule(Ressource_RessourceDAO rrDAO) {
		_rrDAO = rrDAO;
	}

	public Ressource_Ressource createRessource_Ressource(int idressource) {
		Ressource_Ressource _rr = _rrDAO.create(idressource);
		return _rr;
	}
	
	public Ressource_Ressource readRessource_Ressource(int _idRessource_Ressource) {
		Ressource_Ressource _rr = _rrDAO.read(_idRessource_Ressource);
		return _rr;
	}
	
	public Ressource_Ressource readRessource_Ressource(Ressource_Ressource _object) {
		Ressource_Ressource _rr = _rrDAO.read(_object);
		return _rr;
	}
	
	public void deleteRessource_RessourceByidRessource(Ressource_Ressource _object) {
		_rrDAO.delete(_object);
	}
}