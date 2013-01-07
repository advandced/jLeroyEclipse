/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.la.jproductbaseweb.beanmanaged.modelForm;

import fr.la.jproductbaseweb.beanmanaged.exception.LoginException;

/**
 *
 * @author Joff
 */
public class LoginForm {

   // @EJB
   // private LoginRemote ejblogin;
    private String login;
    private String password;

    public LoginForm(String login, String password) {
        this.setLogin(login);
        this.setPassword(password);
    }

    public void check() throws LoginException {
  
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

	public void setPassword(String password) {
		this.password = password;
	}
}
