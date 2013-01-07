package fr.la.juserright.managedbean;

import fr.la.juserright.metier.User;
import java.io.IOException;
import java.sql.SQLException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.naming.NamingException;

@ManagedBean
@SessionScoped
public class loginBean {

    //private ServiceUserRight moduleGlobal = new ServiceUserRight();
    private String login;
    private String password;
    private User user;

    public loginBean() {
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void loginAction() throws SQLException, IOException, NamingException {
    }
}