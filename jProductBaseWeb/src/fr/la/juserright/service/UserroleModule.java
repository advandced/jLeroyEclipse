package fr.la.juserright.service;

import java.sql.SQLException;
import java.util.List;

import fr.la.juserright.dao.ConnectionUserRight;
import fr.la.juserright.dao.FactoryDAO;
import fr.la.juserright.dao.UserRoleDAO;
import fr.la.juserright.metier.UserRole;

public class UserroleModule {
	private ConnectionUserRight cnxUserRight;

	protected UserroleModule(ConnectionUserRight cnxUserRight) {
		this.cnxUserRight = cnxUserRight;
	}

	public void createUserRole(UserRole _userrole) throws SQLException {
		
		UserRoleDAO _userroleDao = FactoryDAO.getUserRoleDAO(this.cnxUserRight);

		_userroleDao.create(_userrole);
	}

	public List<UserRole> getAllUserRole() throws SQLException {
		
		UserRoleDAO _userroleDao = FactoryDAO.getUserRoleDAO(this.cnxUserRight);

		List<UserRole> _userrole = _userroleDao.readAll();

		return _userrole;
	}

	public void updateUserRole(UserRole _userrole) throws SQLException {
		
		UserRoleDAO _userroleDao = FactoryDAO.getUserRoleDAO(this.cnxUserRight);

		_userroleDao.update(_userrole);
	}

	public void deleteUserRole(UserRole _userrole) throws SQLException {
		
		UserRoleDAO _userroleDao = FactoryDAO.getUserRoleDAO(this.cnxUserRight);

		_userroleDao.delete(_userrole);
	}

	public List<UserRole> getUserRoleWithIdUser(int iduser) throws SQLException {
		
		UserRoleDAO _userroleDao = FactoryDAO.getUserRoleDAO(this.cnxUserRight);

		List<UserRole> _userrole = _userroleDao.getUserRoleWithIdUser(iduser);

		return _userrole;
	}

	public List<UserRole> getUserRoleWithLogin(String login) throws SQLException {
		
		UserRoleDAO _userroleDao = FactoryDAO.getUserRoleDAO(this.cnxUserRight);

		List<UserRole> _userrole = _userroleDao.getUserRoleWithLogin(login);

		return _userrole;
	}

}