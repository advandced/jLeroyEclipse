package fr.la.juserright.service;

import java.util.List;

import fr.la.juserright.dao.UserRoleDAO;
import fr.la.juserright.metier.UserRole;

public class UserroleModule {
	
	UserRoleDAO _userroleDao;

	public UserroleModule(UserRoleDAO userroleDao) {
		_userroleDao = userroleDao;
	}

	public void createUserRole(UserRole _userrole) {
		_userroleDao.create(_userrole);
	}

	public List<UserRole> getAllUserRole() {
		List<UserRole> _userrole = _userroleDao.readAll();
		return _userrole;
	}

	public void updateUserRole(UserRole _userrole) {
		_userroleDao.update(_userrole);
	}

	public void deleteUserRole(UserRole _userrole) {
		_userroleDao.delete(_userrole);
	}

	public List<UserRole> getUserRoleWithIdUser(int iduser) {
		List<UserRole> _userrole = _userroleDao.getUserRoleWithIdUser(iduser);
		return _userrole;
	}

	public List<UserRole> getUserRoleWithLogin(String login) {
		List<UserRole> _userrole = _userroleDao.getUserRoleWithLogin(login);
		return _userrole;
	}

}