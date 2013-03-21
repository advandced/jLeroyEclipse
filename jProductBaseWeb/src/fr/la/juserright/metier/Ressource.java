package fr.la.juserright.metier;

import java.io.Serializable;
import java.util.List;

public class Ressource implements Serializable {

	private static final long serialVersionUID = 1L;

	private int idressource;
	
	private int idressource_ressource;

	private String path;

	private String menu;

	private String description;

	private String managedBean;
	
	private String ressourceBean;

	private Ressource ressourceMere;

	private List<Ressource> ressourcelist;

	private Ressource_Ressource ressource_Ressource;

	public int getIdressource() {
		return idressource;
	}

	public void setIdressource(int idressource) {
		this.idressource = idressource;
	}
	
	public int getIdressource_ressource() {
		return idressource_ressource;
	}

	public void setIdressource_ressource(int idressource_ressource) {
		this.idressource_ressource = idressource_ressource;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getManagedBean() {
		return managedBean;
	}

	public void setManagedBean(String managedBean) {
		this.managedBean = managedBean;
	}

	public Ressource getRessourceMere() {
		return ressourceMere;
	}

	public void setRessourceMere(Ressource ressourceMere) {
		this.ressourceMere = ressourceMere;
	}

	public String getMenu() {
		return menu;
	}

	public void setMenu(String menu) {
		this.menu = menu;
	}

	public List<Ressource> getRessourcelist() {
		return ressourcelist;
	}

	public void setRessourcelist(List<Ressource> ressourcelist) {
		this.ressourcelist = ressourcelist;
	}

	public Ressource_Ressource getRessource_Ressource() {
		return ressource_Ressource;
	}

	public void setRessource_Ressource(Ressource_Ressource ressource_Ressource) {
		this.ressource_Ressource = ressource_Ressource;
	}

	public Ressource() {

	}

	public Ressource(int idressource) {
		this.idressource = idressource;
	}

	public Ressource(String menu) {
		this.menu = menu;
	}
	
	public String getRessourceBean() {
		return ressourceBean;
	}

	public void setRessourceBean(String ressourceBean) {
		this.ressourceBean = ressourceBean;
	}

	public Ressource(String path, String menu, String managedBean,
			String description) {
		this.path = path;
		this.menu = menu;
		this.managedBean = managedBean;
		this.description = description;
	}

	public Ressource(String path, String menu, String managedBean,
			String description, Ressource Ressource) {
		this.path = path;
		this.menu = menu;
		this.managedBean = managedBean;
		this.description = description;
		this.ressourceMere = Ressource;
	}

	public Ressource(int idressource, String path, String menu,
			String description) {
		this.idressource = idressource;
		this.path = path;
		this.menu = menu;
		this.description = description;
	}

	public Ressource(int idressource, String path, String menu, String bean,
			String description) {
		this.idressource = idressource;
		this.path = path;
		this.menu = menu;
		this.managedBean = bean;
		this.description = description;
	}

	public Ressource(String path, String menu, String description,
			Ressource ressourceMere) {
		this.path = path;
		this.menu = menu;
		this.description = description;
		this.ressourceMere = ressourceMere;
	}

	public Ressource(int idressource, String path, String menu,
			String description, Ressource ressourceMere) {
		this.idressource = idressource;
		this.path = path;
		this.menu = menu;
		this.description = description;
		this.ressourceMere = ressourceMere;
	}

	public Ressource(int idressource, String path, String menu, String bean,
			String description, Ressource_Ressource ressource_ressource) {
		this.idressource = idressource;
		this.path = path;
		this.menu = menu;
		this.managedBean = bean;
		this.description = description;
		this.ressource_Ressource = ressource_ressource;
	}

	public Ressource(int idressource, String path, String menu,
			String description, List<Ressource> listRessource) {
		this.idressource = idressource;
		this.path = path;
		this.menu = menu;
		this.description = description;
		this.ressourcelist = listRessource;
	}

	public Ressource(int idressource, String path, String menu, String bean,
			String description, List<Ressource> listRessource) {
		this.idressource = idressource;
		this.path = path;
		this.menu = menu;
		this.managedBean = bean;
		this.description = description;
		this.ressourcelist = listRessource;
	}

	public Ressource(int idressource, int idressource_ressource,String path, String menu, String bean, 
			String description, Ressource ressourceMere) {
		this.idressource = idressource;
		this.idressource_ressource = idressource_ressource;
		this.path = path;
		this.managedBean = bean;
		this.menu = menu;
		this.description = description;
		this.ressourceMere = ressourceMere;
	}
	
	public Ressource(int idressource, String path, String menu, String bean, 
			String description, Ressource ressourceMere) {
		this.idressource = idressource;
		this.path = path;
		this.managedBean = bean;
		this.menu = menu;
		this.description = description;
		this.ressourceMere = ressourceMere;
	}

	public Ressource(int idressource, String path, String menu,
			String description, Ressource ressourceMere,
			List<Ressource> listRessource) {
		this.idressource = idressource;
		this.path = path;
		this.menu = menu;
		this.description = description;
		this.ressourceMere = ressourceMere;
		this.ressourcelist = listRessource;
	}
	
	public Ressource(int idressource, int idressource_ressource, String path, String menu,
			String description, Ressource ressourceMere,
			List<Ressource> listRessource) {
		this.idressource = idressource;
		this.idressource_ressource = idressource_ressource;
		this.path = path;
		this.menu = menu;
		this.description = description;
		this.ressourceMere = ressourceMere;
		this.ressourcelist = listRessource;
	}

	public Ressource(int idressource, String path, String menu, String bean,
			String description, String ressourceBean) {
		this.idressource = idressource;
		this.path = path;
		this.menu = menu;
		this.managedBean = bean;
		this.description = description;
		this.ressourceBean = ressourceBean;
	}
}