package fr.la.juserright.metier;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Role implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private int idrole;

	private String name;

	private List<User> listUser = new ArrayList<User>();

	public int getIdrole() {
		return idrole;
	}

	public void setIdrole(int idrole) {
		this.idrole = idrole;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<User> getListUser() {
		return listUser;
	}

	public void setListUser(List<User> listUser) {
		this.listUser = listUser;
	}

	public Role(int idrole, String name, List<User> listUser) throws SQLException {
		this.setIdrole(idrole);
		this.name = name;
		this.listUser = listUser;
	}

	public Role(int idrole, String name) throws SQLException {
		this.setIdrole(idrole);
		this.name = name;
	}

	public Role(int idrole) throws SQLException {
		this.setIdrole(idrole);
	}

	public Role(String name) throws SQLException {
		this.name = name;
	}

	public Role() {

	}
}
