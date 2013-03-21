package fr.la.jproductbaseweb.beanmanaged;

import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import fr.la.juserright.metier.Autorisation;
import fr.la.juserright.metier.User;
import fr.la.juserright.service.ServiceUserRight;

@ManagedBean(name="loginBean")
@SessionScoped
public class LoginBean {
	private ServiceUserRight moduleGlobal = new ServiceUserRight();
	private String login;
	private String password;
	private String redirectFrom;
	private String userlogin;
	private int useradmin;
	private int userid;
	private boolean userconnected = false;
	public List<Autorisation> permList = new ArrayList<Autorisation>();

	public LoginBean() throws IOException {
	}

	public LoginBean(String url) {
		this.redirectFrom = url;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) throws NoSuchAlgorithmException {
		this.password = md5(password);
	}

	public String getRedirectFrom() {
		return redirectFrom;
	}

	public void setRedirectFrom(String redirectFrom) {
		this.redirectFrom = redirectFrom;
	}

	public int getUseradmin() {
		return useradmin;
	}

	public void setUseradmin(int useradmin) {
		this.useradmin = useradmin;
	}

	public String getUserlogin() {
		return userlogin;
	}

	public void setUserlogin(String userlogin) {
		this.userlogin = userlogin;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public boolean isUserconnected() {
		return userconnected;
	}

	public void setUserconnected(boolean userconnected) {
		this.userconnected = userconnected;
	}

	public boolean loginAction() throws SQLException, IOException {
		if (moduleGlobal.login(new User(login, password)) != null) {
			User userfind = moduleGlobal.getUser(login);
			
			userlogin = login;
			useradmin = userfind.getAdmin();
			userid = userfind.getIduser();
			setUserconnected(true);
			
			if (userlogin != null && isUserconnected()) {
			
					try {
						if (permList.isEmpty()) {
						permList = moduleGlobal.getAutorisationByLogin(login);
						} 
						
						
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

			}
			FacesContext.getCurrentInstance().getExternalContext()
					.redirect("/jProductBaseWeb/panel.jsf");
			return true;
		} else {
			FacesMessage message = new FacesMessage("Identifiants incorrects !");
			FacesContext.getCurrentInstance().addMessage(null, message);
			return false;
		}
	}

	public void logout() throws IOException {
		
		userlogin = null; useradmin = 0; userid = 0; setUserconnected(false);
		 
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
				.getExternalContext().getSession(false);
		if (session != null) {
			session.invalidate();
		}
		FacesContext.getCurrentInstance().getExternalContext()
				.redirect("/jProductBaseWeb/");
		return;
	}

	public String md5(String input) {
		String md5 = null;
		if (null == input)
			return null;

		try {
			MessageDigest digest = MessageDigest.getInstance("MD5");
			digest.update(input.getBytes(), 0, input.length());
			md5 = new BigInteger(1, digest.digest()).toString(16);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return md5;
	}
}