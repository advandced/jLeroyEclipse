package fr.la.juserright.metier;

import java.io.Serializable;

public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	private int iduser;
	
	private String login;
	
	private String password;

	private int admin;
	
	private String nom;
	
	private String prenom;
	
	private String email;
	
	private int actif;
	
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getIduser() {
		return iduser;
	}

	public void setIduser(int iduser) {
		this.iduser = iduser;
	}

	public int getAdmin() {
		return admin;
	}

	public void setAdmin(int admin) {
		this.admin = admin;
	}
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public int getActif() {
		return actif;
	}

	public void setActif(int actif) {
		this.actif = actif;
	}

	
	
	
	public User(int iduser, String login, String password, int admin, String nom, String prenom, String email, int actif){
		this.iduser = iduser;
		this.login = login;
		this.password = password;
		this.admin = admin;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.actif = actif;
	}

	public User(String login, String password, int admin, String nom, String prenom, String email, int actif){
		this.login = login;
		this.password = password;
		this.admin = admin;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.actif = actif;
	}

	public User(int id, String login, int admin, String nom, String prenom, String email, int actif){
		this.iduser = id;
		this.login = login;
		this.admin = admin;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.actif = actif;
	}

	public User(int iduser, String login, String password, int admin){
		this.iduser = iduser;
		this.login = login;
		this.password = password;
		this.admin = admin;
	}
		
	public User(int iduser, String login, String password){
		this.iduser = iduser;
		this.login = login;
		this.password = password;
	}
	
	public User(String login, String password){
		this.login = login;
		this.password = password;
	}
	
	public User(int id, String password){
		this.iduser = id;
		this.password = password;
	}
	
	public User(int iduser){
		this.iduser = iduser;
	}
	
	public User(){
		
	}

    @Override
    public String toString() {
        return "User{" + "iduser=" + iduser + ", login=" + login + ", password=" + password + ", admin=" + admin + ", nom=" + nom + ", prenom=" + prenom + ", email=" + email + ", actif=" + actif + '}';
    }

	
	
}
