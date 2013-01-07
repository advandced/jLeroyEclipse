package fr.la.juserright.verificationformulaire;

import java.sql.SQLException;

import fr.la.juserright.exception.ErrorException;
import fr.la.juserright.metier.Role;
import fr.la.juserright.service.ServiceUserRight;

public class CreateRole {

	private ServiceUserRight moduleGlobal = new ServiceUserRight();

	private String Role;

	public CreateRole(String role) throws ErrorException, SQLException {
		this.Role = role;
		Role _role = moduleGlobal.getRole(this.Role);
		if (null != _role) {
			throw new ErrorException("Role deja existant.");
		} else {
			_role = new Role(this.Role);
			moduleGlobal.createRole(_role);
		}
	}
}
