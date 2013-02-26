package fr.la.juserright.verificationformulaire;

import java.sql.SQLException;

import fr.la.juserright.metier.Autorisation;
import fr.la.juserright.metier.Permission;
import fr.la.juserright.metier.Ressource;
import fr.la.juserright.metier.Role;
import fr.la.juserright.service.ServiceUserRight;

public class EditAutorisation {

	private ServiceUserRight moduleGlobal = new ServiceUserRight();

	private String Menu;

	private String Name;

	private String Permission;

	public String getMenu() {
		return Menu;
	}

	public void setMenu(String menu) {
		Menu = menu;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getPermission() {
		return Permission;
	}

	public void setPermission(String permission) {
		Permission = permission;
	}

	public EditAutorisation(String Menu, String Name, String Permission)
			throws SQLException {
		this.setMenu(Menu);
		this.setName(Name);
		this.setPermission(Permission);
		Ressource _ressource = this.moduleGlobal.getRessourceByMenu(this
				.getMenu());
		Role _role = this.moduleGlobal.getRole(this.getName());
		Permission _permission = this.moduleGlobal.getPermission(this
				.getPermission());
		Autorisation _autorisation = moduleGlobal
				.checkAutorisationExists(new Autorisation(_permission,
						_ressource, _role));
		/*if (_permission.getIdpermission() == 3) {
			moduleGlobal.deleteAutorisation(_autorisation);
		} else {*/
			if (_autorisation != null) {
				moduleGlobal.updateAutorisation(_autorisation);
			} else {
				moduleGlobal.createAutorisation(new Autorisation(_permission, _ressource, _role));
			}
		/*}*/
	}
}