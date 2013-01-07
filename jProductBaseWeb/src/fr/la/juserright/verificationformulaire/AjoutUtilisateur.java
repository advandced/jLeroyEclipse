package fr.la.juserright.verificationformulaire;

import java.sql.SQLException;
import java.util.regex.Pattern;

import fr.la.juserright.exception.ErrorException;
import fr.la.juserright.metier.User;
import fr.la.juserright.service.ServiceUserRight;

public class AjoutUtilisateur {

	private ServiceUserRight moduleGlobal = new ServiceUserRight();

	private String _login;

	private String _password1;

	private String _password2;

	private int _actif;

	private int _admin;

	private String _nom;

	private String _prenom;

	private String _email;

	public AjoutUtilisateur(String login, String password1, String password2,
			int actif, int admin, String nom, String prenom, String email)
			throws ErrorException, SQLException {

		this._login = login;

		this._password1 = password1;

		this._password2 = password2;

		this._actif = actif;

		this._admin = admin;

		this._nom = nom;

		this._prenom = prenom;

		this._email = email;

		int _error = 0;

		if (moduleGlobal.getUser(this._login) != null) {
			_error++;
			throw new ErrorException("Login deja existant.");
		}
		if (this._login.length() < 3) {
			_error++;
			throw new ErrorException("Login trop court.");
		}
		if ((this._password1.length() < 3)) {
			_error++;
			throw new ErrorException("Password trop court");
		}
		if (!this._password1.equals(this._password2)) {
			_error++;
			throw new ErrorException("Password different");
		}
		if (this._email.length() == 0) {
			_error++;
			throw new ErrorException("Adresse E-Mail manquante");
		}
		if (!Pattern.matches(
				"^[_a-z0-9-]+(\\.[_a-z0-9-]+)*@[a-z0-9-]+(\\.[a-z0-9-]+)+$",
				this._email)) {
			_error++;
			throw new ErrorException("Adresse E-Mail incorrect");
		}
		if (_error == 0) {
			User _newUser = new User(this._login, this._password1, this._admin,
					this._nom, this._prenom, this._email, this._actif);
			this.moduleGlobal.createUser(_newUser);
		}
	}
}