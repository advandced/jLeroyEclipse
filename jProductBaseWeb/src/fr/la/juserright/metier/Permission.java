package fr.la.juserright.metier;

import java.io.Serializable;

public class Permission implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private int idpermission;
	
	private String name;

	public int getIdpermission() {
		return idpermission;
	}

	public void setIdpermission(int idpermission) {
		this.idpermission = idpermission;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Permission(){
		
	}
	
	public Permission(int idpermission){
		this.idpermission = idpermission;
	}
	
	public Permission(int idpermission, String name){
		this.idpermission = idpermission;
		this.name = name;
	}
}