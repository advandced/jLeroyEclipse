package fr.la.juserright.verificationformulaire;

import java.sql.SQLException;
import java.util.regex.Pattern;

import fr.la.juserright.exception.ErrorException;
import fr.la.juserright.metier.User;
import fr.la.juserright.service.ServiceUserRight;

public class EditUser {

	private ServiceUserRight moduleGlobal = new ServiceUserRight();

	public EditUser(User userSelected) throws SQLException, ErrorException {

		int _error = 0;
 
		if (userSelected.getLogin().length() < 3) {
			_error++;
			throw new ErrorException("Login trop court.");
		}
		if (userSelected.getEmail().length() == 0) {
			_error++;
			throw new ErrorException("Adresse E-Mail manquante");
		}
		if (!Pattern.matches(
				"^[_a-z0-9-]+(\\.[_a-z0-9-]+)*@[a-z0-9-]+(\\.[a-z0-9-]+)+$",
				userSelected.getEmail())) {
			_error++;
			throw new ErrorException("Adresse E-Mail incorrect");
		}
		if (_error == 0) {
			if (this.moduleGlobal.updateUserIfLoginNotExist(userSelected) != null) {
				throw new ErrorException("Login deja existant");
			} else {
				moduleGlobal.updateUser(userSelected);
			}
		}
	}
}