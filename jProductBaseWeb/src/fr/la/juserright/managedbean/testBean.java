package fr.la.juserright.managedbean;

import java.sql.SQLException;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import fr.la.juserright.metier.Ressource;
import fr.la.juserright.service.ServiceUserRight;

@ManagedBean(name="testBean")
@SessionScoped
public class testBean {

	private ServiceUserRight moduleGlobal = new ServiceUserRight();

	private List<Ressource> listRessource;

	private Ressource ressourceSelected = new Ressource();

	private List<String> listMenuRessource;

	private String path;

	private String menu;
	
	private String managedBean;

	private String description;

	private String idmereselect;

	public testBean() throws SQLException{
		this.refreshlist();
	}

	public List<Ressource> getListRessource() {
		return listRessource;
	}

	public void setListRessource(List<Ressource> listRessource) {
		this.listRessource = listRessource;
	}

	public Ressource getRessourceSelected() {
		return ressourceSelected;
	}

	public void setRessourceSelected(Ressource ressourceSelected) {
		this.ressourceSelected = ressourceSelected;
	}

	public List<String> getListMenuRessource() {
		return listMenuRessource;
	}

	public void setListMenuRessource(List<String> listMenuRessource) {
		this.listMenuRessource = listMenuRessource;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getMenu() {
		return menu;
	}

	public void setMenu(String menu) {
		this.menu = menu;
	}

	public String getManagedBean() {
		return managedBean;
	}

	public void setManagedBean(String managedBean) {
		this.managedBean = managedBean;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getIdmereselect() {
		return idmereselect;
	}

	public void setIdmereselect(String idmereselect) {
		this.idmereselect = idmereselect;
	}

	public void refreshlist() throws SQLException {
		this.listRessource = moduleGlobal.getAllRessource();
	}

	public void resetvar() {
		this.setPath(null);
		this.setMenu(null);
		this.setDescription(null);
		this.idmereselect = null;
	}
}