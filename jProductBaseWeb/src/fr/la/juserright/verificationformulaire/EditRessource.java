package fr.la.juserright.verificationformulaire;

import java.sql.SQLException;

import fr.la.juserright.exception.ErrorException;
import fr.la.juserright.metier.Ressource;
import fr.la.juserright.metier.Ressource_Ressource;
import fr.la.juserright.service.ServiceUserRight;

public class EditRessource {
	
	private ServiceUserRight moduleGlobal = ServiceUserRight.getInstance();

	private int idressource;

	private String Path;

	private String Menu;

	private String Bean;
	
	private String Desc;

	private String idmere;

	private Ressource_Ressource ressource_ressource;

	private Ressource ressource;

	public EditRessource(int idressource, String Path, String Menu, String bean,
			String Desc, String idmere) throws ErrorException, SQLException {
		this.idressource = idressource;
		this.Path = Path;
		this.Menu = Menu;
		this.Bean = bean;
		this.Desc = Desc;
		this.idmere = idmere;
		this.ressource = moduleGlobal.getRessourceByPathForUpdate(this.Path,
				this.idressource);
		if (this.ressource != null) {
			throw new ErrorException("Path deja existant.");
		}
		this.ressource = moduleGlobal.getRessourceByMenuForUpdate(this.Menu,
				this.idressource);
		if (this.ressource != null) {
			throw new ErrorException("Menu deja existant.");
		}
		if (this.Menu.length() < 3) {
			throw new ErrorException("Menu trop court.");
		}
		if (this.Path.length() < 3) {
			throw new ErrorException("Path trop court.");
		}
		if(this.Bean.length() == 0){
			throw new ErrorException("Bean manquant.");
		}
		this.ressource = moduleGlobal.getRessource(this.idressource);
		if (this.idmere != null) {
			this.ressource = moduleGlobal.getRessourceByMenu(this.idmere);
			this.ressource_ressource = moduleGlobal
					.getRessource_Ressource(new Ressource_Ressource(
							this.ressource));
			if (this.ressource_ressource != null) {
				moduleGlobal.updateRessource(new Ressource(this.idressource,
						this.Path, this.Menu, this.Bean, this.Desc,
						this.ressource_ressource));
			} else {
				this.ressource_ressource = moduleGlobal
						.createRessource_Ressource(this.ressource.getIdressource());
				moduleGlobal.updateRessource(new Ressource(this.idressource,
						this.Path, this.Menu, this.Bean, this.Desc,
						this.ressource_ressource));
			}
		} else {
			moduleGlobal.updateRessource(new Ressource(this.idressource,
					this.Path, this.Menu, this.Bean, this.Desc));
		}
	}
}
