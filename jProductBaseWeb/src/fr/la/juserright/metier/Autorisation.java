package fr.la.juserright.metier;

import java.io.Serializable;

public class Autorisation implements Serializable {

	private static final long serialVersionUID = 1L;

	private int idautorisation;

	private Permission permission;

	private Ressource ressource;

	private Role role;

	public int getIdautorisation() {
		return idautorisation;
	}

	public void setIdautorisation(int idautorisation) {
		this.idautorisation = idautorisation;
	}

	public Permission getPermission() {
		return permission;
	}

	public void setPermission(Permission permission) {
		this.permission = permission;
	}

	public Ressource getRessource() {
		return ressource;
	}

	public void setRessource(Ressource ressource) {
		this.ressource = ressource;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Autorisation() {

	}

	public Autorisation(Ressource ressource, Role role) {
		this.ressource = ressource;
		this.role = role;
	}

	public Autorisation(Permission permission, Ressource ressource, Role role) {
		this.permission = permission;
		this.ressource = ressource;
		this.role = role;
	}

	public Autorisation(int idautorisation, Permission permission, Ressource ressource, Role role)  {
		this.idautorisation = idautorisation;
		this.permission = permission;
		this.ressource = ressource;
		this.role = role;
	}
}