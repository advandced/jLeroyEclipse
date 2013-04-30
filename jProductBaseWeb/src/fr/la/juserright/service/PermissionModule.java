package fr.la.juserright.service;

import java.util.List;

import fr.la.juserright.dao.PermissionDAO;
import fr.la.juserright.metier.Permission;

public class PermissionModule {
	
	PermissionDAO _permissionDao;

	public PermissionModule(PermissionDAO permissionDao) {
		_permissionDao = permissionDao;
	}
	
	public void createPermission(Permission _permission) {
		_permissionDao.create(_permission);
	}
	
	public List<Permission> getAllPermission() {
		List<Permission> _permission = _permissionDao.readAll();
		return _permission;
	}
	
	public void updatePermission(Permission _permission) {
		_permissionDao.update(_permission);
	}
	
	public void deletePermission(Permission _permission) {
		_permissionDao.delete(_permission);
	}
	
	public Permission getPermission(int idpermission) {
		Permission _permission = _permissionDao.read(idpermission);
		return _permission;
	}
	
	public Permission getPermission(String name) {
		Permission _permission = _permissionDao.read(name);
		return _permission;
	}
	
	public int maxIdPermission() {
		int _idpermission = _permissionDao.readAll().size();
		return _idpermission;
	}
}