package fr.la.juserright.metier;

import java.io.Serializable;
import java.sql.SQLException;

public class UserRole implements Serializable {

	private static final long serialVersionUID = 1L;

	private User user;

	private Role role;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public void setIdrole(Role idrole) {
		this.setRole(idrole);
	}

	public UserRole() {
	}

	public UserRole(Role role) throws SQLException {
		this.role = role;
	}

	public UserRole(Role role, User user) throws SQLException {
		this.role = role;
		this.user = user;
	}
}