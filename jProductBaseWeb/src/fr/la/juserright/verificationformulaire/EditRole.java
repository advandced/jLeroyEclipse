package fr.la.juserright.verificationformulaire;

import java.sql.SQLException;

import fr.la.juserright.exception.ErrorException;
import fr.la.juserright.metier.Role;
import fr.la.juserright.service.ServiceUserRight;

public class EditRole {
	private ServiceUserRight moduleGlobal = new ServiceUserRight();

	private int id;
	
	private String Role;

	public EditRole(int id, String role) throws ErrorException, SQLException {
		this.id = id;
		this.Role = role;
		Role _editRole = new Role(this.id, this.Role);
		Role _role = moduleGlobal.updateRoleIfNotExist(_editRole);
		if (null != _role) {
			throw new ErrorException("Role deja existant.");
		} else {
			moduleGlobal.updateRole(_editRole);
		}
	}
}