package fr.la.jproductbaseweb.beanmanaged.general;

import java.sql.SQLException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "loginBean")
@SessionScoped
public class LoginBean {

    private String login;
    private String password;
    private String redirectFrom;

    public LoginBean() {
    }

    public LoginBean(String url) {
        this.redirectFrom = url;
    }

    public void loginAction() throws SQLException {
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

    public String getRedirectFrom() {
        return redirectFrom;
    }

    public void setRedirectFrom(String redirectFrom) {
        this.redirectFrom = redirectFrom;
    }
}