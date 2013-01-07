package fr.la.juserright.managedbean;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;

import fr.la.juserright.exception.ErrorException;
import fr.la.juserright.metier.Ressource;
import fr.la.juserright.metier.Ressource_Ressource;
import fr.la.juserright.service.ServiceUserRight;
import fr.la.juserright.verificationformulaire.AjoutRessource;
import fr.la.juserright.verificationformulaire.EditRessource;

@ManagedBean
@SessionScoped
public class ressourceBean {

	private ServiceUserRight moduleGlobal = new ServiceUserRight();

	private List<Ressource> listRessource;

	private Ressource ressourceSelected = new Ressource();

	private List<String> listMenuRessource;

	private String path;

	private String menu;
	
	private String managedBean;

	private String description;

	private String idmereselect;

	public ressourceBean() throws SQLException {
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

	public void addRessource() throws SQLException, ErrorException {
		try {
			@SuppressWarnings("unused")
			AjoutRessource _ajoutRessource = new AjoutRessource(this.path,
					this.menu, this.managedBean, this.description, this.idmereselect);
			this.refreshlist();
			this.closeAddRessource();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ErrorException e) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "", e
							.getMessage()));
		}
	}

	public void delRessource() throws SQLException {
		moduleGlobal.deleteRessource(ressourceSelected);
		if(moduleGlobal.getRessource_Ressource(new Ressource_Ressource(this.ressourceSelected)) != null){
			Ressource_Ressource _rr = moduleGlobal.getRessource_Ressource(new Ressource_Ressource(this.ressourceSelected));
			moduleGlobal.deleteRessource_RessourceByidRessource(new Ressource_Ressource(this.ressourceSelected));
			moduleGlobal.updateToNullIDRR(_rr.getIdressource_ressource());
		}
		this.refreshlist();
		this.closeEditRessource();
	}

	public void editRessource() throws SQLException, ErrorException {
		try {
			@SuppressWarnings("unused")
			EditRessource _editressource = new EditRessource(
					this.ressourceSelected.getIdressource(),
					this.ressourceSelected.getPath(),
					this.ressourceSelected.getMenu(),
					this.ressourceSelected.getManagedBean(),
					this.ressourceSelected.getDescription(), this.idmereselect);
			this.idmereselect = null;
			this.closeEditRessource();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ErrorException e) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "", e
							.getMessage()));
		}
		this.refreshlist();
	}

	public void showAddRessource() {
		this.refreshlistMenuRessource();
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("dialogAddRessource.show()");
	}

	public void resetvar() {
		this.setPath(null);
		this.setMenu(null);
		this.setDescription(null);
		this.idmereselect = null;
	}

	public void closeAddRessource() throws SQLException {
		this.resetvar();
		this.refreshlist();
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("dialogAddRessource.hide()");
	}

	public void showEditRessource() throws SQLException {
		if (this.ressourceSelected.getRessourceMere() != null) {
			this.idmereselect = ressourceSelected.getRessourceMere().getMenu();
		}
		this.refreshlistMenuRessourceForEdit();
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("dialogEditRessource.show()");
	}

	public void closeEditRessource() throws SQLException {
		this.refreshlist();
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("dialogEditRessource.hide()");
	}

	public void refreshlistMenuRessource() {
		this.listMenuRessource = new ArrayList<String>();
		for (Ressource r : this.listRessource) {
			this.listMenuRessource.add(r.getMenu());
		}
	}

	public void refreshlistMenuRessourceForEdit() {
		this.listMenuRessource = new ArrayList<String>();
		for (Ressource r : this.listRessource) {
			if (r.getIdressource() != this.ressourceSelected.getIdressource()) {
				this.listMenuRessource.add(r.getMenu());
			}
		}
	}
}