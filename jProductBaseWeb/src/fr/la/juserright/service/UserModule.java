package fr.la.juserright.service;

import java.sql.SQLException;
import java.util.List;

import fr.la.juserright.dao.ConnectionUserRight;
import fr.la.juserright.dao.FactoryDAO;
import fr.la.juserright.dao.UserDAO;
import fr.la.juserright.exception.ErrorException;
import fr.la.juserright.metier.User;

public class UserModule {
	private ConnectionUserRight cnxUserRight;

	protected UserModule(ConnectionUserRight cnxUserRight) {
		this.cnxUserRight = cnxUserRight;
	}

	public void createUser(User _user) throws SQLException {
		
		UserDAO _userDao = FactoryDAO.getUserDAO(this.cnxUserRight);
		
		_userDao.create(_user);
		
	}
	
	public List<User> getAllUser() throws SQLException {
		
		UserDAO _userDao = FactoryDAO.getUserDAO(this.cnxUserRight);
		
		List<User> _user = _userDao.readAll();
		
		return _user;
	}
	
	public void updateUser(User _user) throws SQLException {
		
		UserDAO _userDao = FactoryDAO.getUserDAO(this.cnxUserRight);
		
		_userDao.update(_user);
	}
	
	public void deleteUser(User _user) throws SQLException {
		
		UserDAO _userDao = FactoryDAO.getUserDAO(this.cnxUserRight);
		
		_userDao.delete(_user);		
	}
	
	public User getUser(int idUser) throws SQLException {
		
		UserDAO _userDao = FactoryDAO.getUserDAO(this.cnxUserRight);
		
		User _user = _userDao.getUser(idUser);
		
		return _user;
	}
	
	public User getUser(String login) throws SQLException {
		
		UserDAO _userDao = FactoryDAO.getUserDAO(this.cnxUserRight);
		
		User _user = _userDao.getUser(login);
		
		return _user;
	}
	
	public User login(User user) throws SQLException {
		
		UserDAO _userDao = FactoryDAO.getUserDAO(this.cnxUserRight);
		
		User _user = _userDao.login(user);
		
		return _user;
	}
	
	public List<User> getUserAddRole(String nom_role) throws SQLException {
		
		UserDAO _userDao = FactoryDAO.getUserDAO(this.cnxUserRight);
		
		List<User> _user = _userDao.getUserAddRole(nom_role);
		
		return _user;
	}
	
	public List<User> getUserForARole(String nom_role) throws SQLException {
		
		UserDAO _userDao = FactoryDAO.getUserDAO(this.cnxUserRight);
		
		List<User> _user = _userDao.getUserForARole(nom_role);
		
		return _user;
	}
	
	public User updateUserIfLoginNotExist(User user) throws SQLException, ErrorException {
		
		UserDAO _userDao = FactoryDAO.getUserDAO(this.cnxUserRight);
		
		User _user = _userDao.updateUserIfLoginNotExist(user);
	
		return _user;
	}
	
	public void updateUserPassword(User user) throws SQLException, ErrorException {
		
		UserDAO _userDao = FactoryDAO.getUserDAO(this.cnxUserRight);
		
		_userDao.updateUserPassword(user);
	}
}