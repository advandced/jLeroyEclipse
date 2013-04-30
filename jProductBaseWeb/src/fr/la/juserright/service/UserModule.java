package fr.la.juserright.service;

import java.util.List;

import fr.la.juserright.dao.UserDAO;
import fr.la.juserright.metier.User;

public class UserModule {
	
	UserDAO _userDao;

	public UserModule(UserDAO userDao) {
		_userDao = userDao;
	}

	public void createUser(User _user) {
		_userDao.create(_user);
	}
	
	public List<User> getAllUser() {
		List<User> _user = _userDao.readAll();
		return _user;
	}
	
	public void updateUser(User _user) {
		_userDao.update(_user);
	}
	
	public void deleteUser(User _user) {
		_userDao.delete(_user);		
	}
	
	public User getUser(int idUser) {
		User _user = _userDao.getUser(idUser);
		return _user;
	}
	
	public User getUser(String login) {
		User _user = _userDao.getUser(login);
		return _user;
	}
	
	public User login(User user) {
		User _user = _userDao.login(user);
		return _user;
	}
	
	public List<User> getUserAddRole(int idrole) {
		List<User> _user = _userDao.getUserAddRole(idrole);
		return _user;
	}
	
	public List<User> getUserForARole(int idrole) {
		List<User> _user = _userDao.getUserForARole(idrole);
		return _user;
	}
	
	public User updateUserIfLoginNotExist(User user) {
		User _user = _userDao.updateUserIfLoginNotExist(user);
		return _user;
	}
	
	public void updateUserPassword(User user) {
		_userDao.updateUserPassword(user);
	}
}