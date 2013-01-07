package fr.la.juserright.service;

import java.sql.SQLException;

import fr.la.juserright.dao.ConnectionUserRight;
import fr.la.juserright.dao.FactoryDAO;
import fr.la.juserright.dao.Ressource_RessourceDAO;
import fr.la.juserright.metier.Ressource_Ressource;

public class Ressource_RessourceModule {

	private ConnectionUserRight cnxUserRight;

	protected Ressource_RessourceModule(ConnectionUserRight cnxUserRight) {
		this.cnxUserRight = cnxUserRight;
	}

	protected Ressource_Ressource createRessource_Ressource(int idressource)
			throws SQLException {
		
		Ressource_RessourceDAO _rrDAO = FactoryDAO.getRessource_RessourceDAO(this.cnxUserRight);
		
		Ressource_Ressource _rr = _rrDAO.create(idressource);
		
		return _rr;
		
	}
	
	protected Ressource_Ressource readRessource_Ressource(int _idRessource_Ressource) throws SQLException{
		
		Ressource_RessourceDAO _rrDAO = FactoryDAO.getRessource_RessourceDAO(this.cnxUserRight);
		
		Ressource_Ressource _rr = _rrDAO.read(_idRessource_Ressource);
		
		return _rr;
				
	}
	
	protected Ressource_Ressource readRessource_Ressource(Ressource_Ressource _object) throws SQLException{
		
		Ressource_RessourceDAO _rrDAO = FactoryDAO.getRessource_RessourceDAO(this.cnxUserRight);
		
		Ressource_Ressource _rr = _rrDAO.read(_object);
		
		return _rr;
				
	}
	
	protected void deleteRessource_RessourceByidRessource(Ressource_Ressource _object) throws SQLException {
		
		Ressource_RessourceDAO _rrDAO = FactoryDAO.getRessource_RessourceDAO(this.cnxUserRight);
		
		_rrDAO.delete(_object);
		
	}
}