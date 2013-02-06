package fr.la.juserright.managedbean;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.context.RequestContext;

import fr.la.juserright.metier.Autorisation;
import fr.la.juserright.metier.Permission;
import fr.la.juserright.metier.Ressource;
import fr.la.juserright.metier.Role;
import fr.la.juserright.service.ServiceUserRight;
import fr.la.juserright.verificationformulaire.EditAutorisation;

@ManagedBean
@SessionScoped
public class autorisationBean {

	private ServiceUserRight moduleGlobal = new ServiceUserRight();

	private String RoleSelected;

	private String PermissionSelected;

	private Autorisation autorisationSelected = new Autorisation();

	private List<String> listRole = new ArrayList<String>();

	private List<String> listPermission = new ArrayList<String>();

	private Boolean afficherTableau = false;

	private List<Autorisation> listAutorisation = new ArrayList<Autorisation>();

	private List<Ressource> listRessource = new ArrayList<Ressource>();

	public String getRoleSelected() {
		return RoleSelected;
	}

	public void setRoleSelected(String roleSelected) {
		RoleSelected = roleSelected;
	}

	public String getPermissionSelected() {
		return PermissionSelected;
	}

	public void setPermissionSelected(String permissionSelected) {
		PermissionSelected = permissionSelected;
	}

	public Autorisation getAutorisationSelected() {
		return autorisationSelected;
	}

	public void setAutorisationSelected(Autorisation autorisationSelected) {
		this.autorisationSelected = autorisationSelected;
	}

	public List<String> getListRole() {
		return listRole;
	}

	public void setListRole(List<String> listRole) {
		this.listRole = listRole;
	}

	public List<String> getListPermission() {
		return listPermission;
	}

	public void setListPermission(List<String> listPermission) {
		this.listPermission = listPermission;
	}

	public Boolean getAfficherTableau() {
		return afficherTableau;
	}

	public void setAfficherTableau(Boolean afficherTableau) {
		this.afficherTableau = afficherTableau;
	}

	public List<Autorisation> getListAutorisation() {
		return listAutorisation;
	}

	public void setListAutorisation(List<Autorisation> listAutorisation) {
		this.listAutorisation = listAutorisation;
	}

	public List<Ressource> getListRessource() {
		return listRessource;
	}

	public void setListRessource(List<Ressource> listRessource) {
		this.listRessource = listRessource;
	}

	public autorisationBean() throws SQLException {
		this.refreshlistPerm();
		this.refreshlistRole();
	}

	public void refreshlistRole() throws SQLException {
		List<Role> _allrole = moduleGlobal.getAllRole();
		for (Role r : _allrole) {
			if (r.getName() != null) {
				this.listRole.add(r.getName());
			}
		}
	}

	public void refreshlistPerm() throws SQLException {
		this.listPermission = new ArrayList<String>();
		List<Permission> _allperm = moduleGlobal.getAllPermission();
		for (Permission p : _allperm) {
			this.listPermission.add(p.getName());
			System.out.println(p.getName());
		}
		
	}

	public void RoleChange() throws SQLException {
		if (RoleSelected != null && !RoleSelected.equals("")) {
			this.refreshTab();
			this.afficherTableau = true;
		} else {
			this.afficherTableau = false;
		}
	}

	public void refreshTab() throws SQLException {
		Role _role = moduleGlobal.getRole(RoleSelected);
		this.listAutorisation = moduleGlobal.getAutorisationWithIdRole(_role
				.getIdrole());
		this.listRessource = moduleGlobal
				.selectRessourceNotUsedByRole(this.listAutorisation);
		Permission _perm = moduleGlobal.getPermission(3);
		for (Ressource re : this.listRessource) {
			this.listAutorisation.add(new Autorisation(_perm, re, _role));
		}
		int _idauto = 0;
		for (Autorisation _auto : this.listAutorisation) {
			_auto.setIdautorisation(_idauto);
			_idauto++;
		}
		this.refreshlistPerm();
	}

	public void showDetailAutorisation() {
		if (this.autorisationSelected.getPermission().getName() != null) {
			this.PermissionSelected = this.autorisationSelected.getPermission()
					.getName();
		}
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("dialogDetail.show()");
	}

	public void hideDetailAutorisation() {
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("dialogDetail.hide()");
	}

	public void EditAutorisation() throws SQLException {
		@SuppressWarnings("unused")
		EditAutorisation _editAuto = new EditAutorisation(
				this.autorisationSelected.getRessource().getMenu(),
				this.RoleSelected, this.PermissionSelected);
		this.refreshTab();
		this.hideDetailAutorisation();
	}
}