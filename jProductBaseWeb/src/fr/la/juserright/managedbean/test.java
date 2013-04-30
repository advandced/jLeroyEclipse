package fr.la.juserright.managedbean;

import java.sql.SQLException;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import fr.la.juserright.metier.User;
import fr.la.juserright.service.ServiceUserRight;

@ManagedBean
@SessionScoped
public class test {

	private ServiceUserRight moduleGlobal = ServiceUserRight.getInstance();
	
	private List<User> listUser;
	
	private User userSelected = new User();
	
    public test() throws SQLException {
    	this.setListUser(moduleGlobal.getAllUser());
    }

	public List<User> getListUser() {
		return listUser;
	}

	public void setListUser(List<User> listUser) {
		this.listUser = listUser;
	}

	public User getUserSelected() {
		return userSelected;
	}

	public void setUserSelected(User userSelected) {
		this.userSelected = userSelected;
	}

}
