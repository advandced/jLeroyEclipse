package fr.la.juserright.managedbean;

import java.sql.SQLException;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;

import fr.la.juserright.exception.ErrorException;
import fr.la.juserright.metier.User;
import fr.la.juserright.service.ServiceUserRight;
import fr.la.juserright.verificationformulaire.AjoutUtilisateur;
import fr.la.juserright.verificationformulaire.EditUser;
import fr.la.juserright.verificationformulaire.EditUserPW;

@ManagedBean
@SessionScoped
public class utilisateurBean {

	private ServiceUserRight moduleGlobal = new ServiceUserRight();

	private List<User> userList;

	private User userSelected = new User();

	private User userSelected2 = new User();

	private String login;

	private String password1;

	private String password2;

	private int actif;

	private int admin;

	private String nom;

	private String prenom;

	private String email;

	public utilisateurBean() throws SQLException {
		this.refreshuserList();
	}

	public void refreshuserList() throws SQLException {
		this.userList = moduleGlobal.getAllUser();
	}

	public User getuserSelected() {
		return this.userSelected;
	}

	public void setuserSelected(User _userSelected) {
		this.userSelected = _userSelected;
	}

	public User getUserSelected2() {
		return userSelected2;
	}

	public void setUserSelected2(User userSelected2) {
		this.userSelected2 = userSelected2;
	}

	public List<User> getUserList() {
		return userList;
	}

	public String getlogin() {
		return login;
	}

	public void setlogin(String _login) {
		this.login = _login;
	}

	public String getpassword1() {
		return password1;
	}

	public void setpassword1(String _password1) {
		this.password1 = _password1;
	}

	public String getPassword2() {
		return password2;
	}

	public void setPassword2(String password2) {
		this.password2 = password2;
	}

	public int getAdmin() {
		return admin;
	}

	public void setAdmin(int admin) {
		this.admin = admin;
	}

	public int getActif() {
		return actif;
	}

	public void setActif(int actif) {
		this.actif = actif;
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

	protected void refreshUserList() throws SQLException {
		this.userList = moduleGlobal.getAllUser();
	}

	protected void resetVariableUser() {
		this.login = null;
		this.password1 = null;
		this.password2 = null;
		this.actif = 0;
		this.admin = 0;
		this.nom = null;
		this.prenom = null;
		this.email = null;
	}

	public void UpdateUser() throws SQLException {
		try {
			@SuppressWarnings("unused")
			EditUser _editUser = new EditUser(this.userSelected);
			this.refreshuserList();
			this.userSelected = new User();
			this.CloseEditUser();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ErrorException e) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "", e
							.getMessage()));
		}
	}

	public void CreateUser() throws SQLException {
		try {
			@SuppressWarnings("unused")
			AjoutUtilisateur _verifAddUser = new AjoutUtilisateur(this.login,
					this.password1, this.password2, this.actif, this.admin,
					this.nom, this.prenom, this.email);
			this.CloseCreateUser();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ErrorException e) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "", e
							.getMessage()));
		}
	}

	public void CloseCreateUser() throws SQLException {
		this.refreshuserList();
		this.resetVariableUser();
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("utilisateurDialogAdd.hide()");
	}

	public void CloseEditUser() throws SQLException {
		this.refreshuserList();
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("utilisateurDialogEdit.hide()");
		this.CloseEditPW();
	}

	public void ShowEditPW() {
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("utilisateurDialogEditPW.show()");
	}

	public void CloseEditPW() {
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("utilisateurDialogEditPW.hide()");
	}

	public void UpdatePassword() {
		try { 
				@SuppressWarnings("unused")
				EditUserPW _editUserPW = new EditUserPW(this.userSelected.getIduser(), this.password1, this.password2);
				this.moduleGlobal.updateUserPassword(this.userSelected2);
				this.CloseEditPW();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ErrorException e) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "", e
							.getMessage()));
		}
	}
}