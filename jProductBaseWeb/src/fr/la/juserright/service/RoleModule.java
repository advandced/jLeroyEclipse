package fr.la.juserright.service;

import java.util.List;

import fr.la.juserright.dao.RoleDAO;
import fr.la.juserright.metier.Role;

public class RoleModule {

	RoleDAO _roleDao;
	
	public RoleModule(RoleDAO roleDao) {
		_roleDao = roleDao;
	}

	public void createRole(Role _role) {
		_roleDao.create(_role);
	}

	public List<Role> getAllRole() {
		List<Role> __roleDao = _roleDao.readAll();
		return __roleDao;
	}

	public void updateRole(Role _role) {
		_roleDao.update(_role);
	}

	public Role updateRoleIfNotExist(Role _role) {
		Role __role = _roleDao.updateRoleIfNotExist(_role);
		return __role;
	}

	public void deleteRole(Role _role) {
		_roleDao.delete(_role);
	}

	public Role getRole(int idrole) {
		Role _role = _roleDao.getRole(idrole);
		return _role;
	}

	public Role getRole(String role) {
		Role _role = _roleDao.getRole(role);
		return _role;
	}
}