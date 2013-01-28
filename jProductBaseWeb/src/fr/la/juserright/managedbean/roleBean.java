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
import fr.la.juserright.metier.Role;
import fr.la.juserright.metier.User;
import fr.la.juserright.metier.UserRole;
import fr.la.juserright.service.ServiceUserRight;
import fr.la.juserright.verificationformulaire.CreateRole;
import fr.la.juserright.verificationformulaire.EditRole;

@ManagedBean
@SessionScoped
public class roleBean {

	private ServiceUserRight moduleGlobal = new ServiceUserRight();

	private List<Role> listRole;

	private Role roleSelected = new Role();

	private String Role;
	
	private User userSelected = new User();

	private List<String> listUserAdd;

	private String userAdd;

	private List<User> listUserForRole;

	private boolean dataFound = true;

	private boolean msgError = false;

	private boolean userFound = true;

	public roleBean() throws SQLException {
		this.refreshrolelist();
	}

	public List<Role> getlistRole() {
		return listRole;
	}

	public void setlistRole(List<Role> userRole) {
		this.listRole = userRole;
	}

	public Role getRoleSelected() {
		return roleSelected;
	}

	public void setRoleSelected(Role roleSelected) throws SQLException {
		this.roleSelected = roleSelected;
	}

	public void refreshrolelist() throws SQLException {
		this.listRole = moduleGlobal.getAllRole();
	}

	public String getRole() {
		return Role;
	}

	public void setRole(String role) {
		Role = role;
	}

	public List<String> getListUserAdd() throws SQLException {
		return listUserAdd;
	}

	public void setListUserAdd(List<String> listUserAdd) {
		this.listUserAdd = listUserAdd;
	}

	public User getUserSelected() {
		return userSelected;
	}

	public void setUserSelected(User userSelected) {
		this.userSelected = userSelected;
	}

	public String getUserAdd() {
		return userAdd;
	}

	public void setUserAdd(String userAdd) {
		this.userAdd = userAdd;
	}

	public List<User> getListUserForRole() throws SQLException {
		return listUserForRole;
	}

	public void setListUserForRole(List<User> listUserForRole) {
		this.listUserForRole = listUserForRole;
	}

	public boolean isDataFound() throws SQLException {
		return dataFound;
	}

	public void setDataFound(boolean dataFound) {
		this.dataFound = dataFound;
	}

	public boolean isMsgError() throws SQLException {
		return msgError;
	}

	public void setMsgError(boolean msgError) {
		this.msgError = msgError;
	}

	public boolean isUserFound() throws SQLException {
		return userFound;
	}

	public void setUserFound(boolean userFound) {
		this.userFound = userFound;
	}

	public void showAddUser() throws SQLException {
		this.refreshlistUserAdd();
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("dialogAddUtilisateur.show()");
	}

	public void closeAddUser() {
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("dialogAddUtilisateur.hide()");
	}

	public void showEditUser() throws SQLException {
		this.refreshlistUserForRole();
		if (this.listUserForRole.size() == 0) {
			this.dataFound = false;
			this.msgError = true;
		} else {
			this.dataFound = true;
			this.msgError = false;
		}
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("dialogEditUser.show()");
	}

	public void closeEditUser() {
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("dialogEditUser.hide()");
	}

	public void showAddRole() {
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("dialogAddRole.show()");
	}

	public void closeAddRole() throws SQLException {
		this.Role = null;
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("dialogAddRole.hide()");
		this.refreshlistUserAdd();
	}

	public void showEditRole() {
		this.Role = this.roleSelected.getName();
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("dialogEditRole.show()");
	}

	public void closeEditRole() {
		this.Role = null;
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("dialogEditRole.hide()");
	}

	public void addRole() throws SQLException, ErrorException {
		try {
			@SuppressWarnings("unused")
			CreateRole _createRole = new CreateRole(this.Role);
			this.refreshrolelist();
			this.closeAddRole();
		} catch (SQLException e) {
			e.printStackTrace();
		}  catch (ErrorException e) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "", e
							.getMessage()));
		}
	}

	public void addUserRole() throws SQLException {
		User _user = moduleGlobal.getUser(this.userAdd);
		Role _role = moduleGlobal.getRole(this.roleSelected.getIdrole());
		UserRole _userrole = new UserRole(_role, _user);
		moduleGlobal.createUserRole(_userrole);
		this.refreshlistUserAdd();
		if (this.listUserAdd.size() == 0) {
			this.closeAddUser();
		}
		this.refreshrolelist();
	}

	public void delRole() throws SQLException {
		moduleGlobal.deleteRole(this.roleSelected);
		this.closeEditRole();
		this.refreshrolelist();
	}

	public void refreshlistUserForRole() throws SQLException {
		this.listUserForRole = this.moduleGlobal
				.getUserForARole(this.roleSelected.getName());
	}

	public void refreshlistUserAdd() throws SQLException {
		this.listUserAdd = this.converterlistUser(this.roleSelected);
		if (this.listUserAdd.size() == 0) {
			this.msgError = true;
			this.userFound = false;
		} else {
			this.msgError = false;
			this.userFound = true;
		}
	}

	public List<String> converterlistUser(Role _role) throws SQLException {
		List<User> listUserAdd = moduleGlobal.getUserAddRole(_role.getName());
		List<String> listString = new ArrayList<String>();
		for (User p : listUserAdd) {
			listString.add(p.getLogin());
		}
		return listString;
	}

	public void delUserFromRole() {
		try {
			UserRole _userrole = new UserRole(this.roleSelected,
					this.userSelected);
			moduleGlobal.deleteUserRole(_userrole);
			this.refreshrolelist();
			this.refreshlistUserForRole();
			if (this.listUserForRole.size() == 0) {
				this.closeEditUser();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateRole() throws SQLException, ErrorException {
		try {
			@SuppressWarnings("unused")
			EditRole _editRole = new EditRole(this.roleSelected.getIdrole(),this.Role);
			this.closeEditRole();
		} catch (ErrorException e) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "", e
							.getMessage()));
		}
		this.refreshrolelist();
	}
}