package fr.la.juserright.verificationformulaire;

import java.sql.SQLException;

import fr.la.juserright.exception.ErrorException;
import fr.la.juserright.metier.User;
import fr.la.juserright.service.ServiceUserRight;

public class EditUserPW {

	private ServiceUserRight moduleGlobal = ServiceUserRight.getInstance();

	private int id;

	private String pw1;

	private String pw2;

	public EditUserPW(int id, String pw1, String pw2) throws ErrorException,
			SQLException {
		this.id = id;

		this.pw1 = pw1;

		this.pw2 = pw2;

		int _error = 0;

		if ((this.pw1.length() < 3)) {
			_error++;
			throw new ErrorException("Password trop court");
		}
		if (!(this.pw1.equals(this.pw2))) {
			_error++;
			throw new ErrorException("Password different");
		}
		if (_error == 0) {
			User _user = new User(this.id, this.pw1);
			this.moduleGlobal.updateUserPassword(_user);
		}
	}
}