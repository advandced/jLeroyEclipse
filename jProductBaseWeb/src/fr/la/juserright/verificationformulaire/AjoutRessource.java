package fr.la.juserright.verificationformulaire;

import java.sql.SQLException;

import fr.la.juserright.exception.ErrorException;
import fr.la.juserright.metier.Ressource;
import fr.la.juserright.metier.Ressource_Ressource;
import fr.la.juserright.service.ServiceUserRight;

public class AjoutRessource {

	private ServiceUserRight moduleGlobal = ServiceUserRight.getInstance();

	private String path;

	private String menu;

	private String managedBean;

	private String description;

	private String idmere;

	private Ressource_Ressource ressource_ressource;

	private Ressource ressource;

	public AjoutRessource(String path, String menu, String managedBean,
			String description, String idmere) throws SQLException,
			ErrorException {
		this.path = path;
		this.menu = menu;
		this.managedBean = managedBean;
		this.description = description;
		this.idmere = idmere;
		this.ressource = moduleGlobal.getRessourceByPath(this.path);
		if (this.ressource != null) {
			throw new ErrorException("Path deja existant.");
		}
		this.ressource = moduleGlobal.getRessourceByMenu(this.menu);
		if (this.ressource != null) {
			throw new ErrorException("Menu deja existant.");
		}
		if (this.menu.length() < 3) {
			throw new ErrorException("Menu trop court.");
		}
		if (this.path.length() < 3) {
			throw new ErrorException("Path trop court.");
		}
		if (this.managedBean.length() == 0) {
			throw new ErrorException("Bean manquant.");
		}
		if (this.idmere != null) {
			this.ressource = moduleGlobal.getRessourceByMenu(this.idmere);
			this.ressource_ressource = moduleGlobal
					.getRessource_Ressource(new Ressource_Ressource(
							this.ressource));
			if (this.ressource_ressource != null) {
				Ressource _newRessource = moduleGlobal
						.createRessourceWithRR(new Ressource(this.path,
								this.menu, this.managedBean, this.description));
				moduleGlobal
						.updateidRR(_newRessource, this.ressource_ressource);
			} else {
				Ressource _newRessource = moduleGlobal
						.createRessourceWithRR(new Ressource(this.path,
								this.menu, this.managedBean, this.description,
								this.ressource));
				Ressource_Ressource _newRessource_Ressource = moduleGlobal
						.createRessource_Ressource(this.ressource
								.getIdressource());
				moduleGlobal.updateidRR(_newRessource, _newRessource_Ressource);
			}
		} else {
			moduleGlobal.createRessource(new Ressource(this.path, this.menu,
					this.managedBean, this.description));
		}
	}
}