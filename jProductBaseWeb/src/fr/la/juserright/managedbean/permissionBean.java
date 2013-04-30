package fr.la.juserright.managedbean;

import fr.la.juserright.metier.Permission;
import fr.la.juserright.service.ServiceUserRight;
import java.sql.SQLException;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.primefaces.context.RequestContext;

@ManagedBean
@SessionScoped
public class permissionBean {

	ServiceUserRight moduleGlobal = ServiceUserRight.getInstance();

	private Permission permissionSelected = new Permission();

	private Permission newPermission = new Permission(
			moduleGlobal.maxIdPermission() + 1);

	private List<Permission> listPermission;

	public permissionBean() throws SQLException {
		this.refreshlist();
		
	}

	public Permission getPermissionSelected() {
		return permissionSelected;
	}

	public void setPermissionSelected(Permission permissionSelected) {
		this.permissionSelected = permissionSelected;
	}

	public void refreshlist() throws SQLException {
		this.setListPermission(moduleGlobal.getAllPermission());
	}

	public Permission getNewPermission() {
		return newPermission;
	}

	public void setNewPermission(Permission newPermission) {
		this.newPermission = newPermission;
	}

	public List<Permission> getListPermission() {
		return listPermission;
	}

	public void setListPermission(List<Permission> listPermission) {
		this.listPermission = listPermission;
	}

	public void addPermission() throws SQLException {
		moduleGlobal.createPermission(newPermission);
		this.closeAddPermission();
	}

	public void delPermission() throws SQLException {
		moduleGlobal.deletePermission(permissionSelected);
		this.closeEditPermission();
	}
	
	public void editPermission() throws SQLException {
		moduleGlobal.updatePermission(permissionSelected);
		this.closeEditPermission();
	}

	public void showConfirmDialog() {
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("confirmDialog.show()");
	}

	public void closeAddPermission() throws SQLException {
		this.newPermission = new Permission(moduleGlobal.maxIdPermission() + 1);
		this.refreshlist();
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("dialogAddPermission.hide()");
	}

	public void closeEditPermission() throws SQLException {
		this.newPermission = new Permission(moduleGlobal.maxIdPermission() + 1);
		this.refreshlist();
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("dialogEditPermission.hide()");
	}
}