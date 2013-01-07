package fr.la.juserright.service;

import java.sql.SQLException;
import java.util.List;

import fr.la.juserright.dao.ConnectionUserRight;
import fr.la.juserright.dao.FactoryDAO;
import fr.la.juserright.dao.PermissionDAO;
import fr.la.juserright.metier.Permission;

public class PermissionModule {
	private ConnectionUserRight cnxUserRight;

	protected PermissionModule(ConnectionUserRight cnxUserRight) {
		this.cnxUserRight = cnxUserRight;
	}
	
	protected void createPermission(Permission _permission) throws SQLException{
		
		PermissionDAO _permissionDao = FactoryDAO.getPermissionDAO(this.cnxUserRight);
		
		_permissionDao.create(_permission);
	}
	
	protected List<Permission> getAllPermission() throws SQLException {
		
		PermissionDAO _permissionDao = FactoryDAO.getPermissionDAO(this.cnxUserRight);
		
		List<Permission> _permission = _permissionDao.readAll();
		
		return _permission;
	}
	
	protected void updatePermission(Permission _permission) throws SQLException {
		
		PermissionDAO _permissionDao = FactoryDAO.getPermissionDAO(this.cnxUserRight);
		
		_permissionDao.update(_permission);
	}
	
	protected void deletePermission(Permission _permission) throws SQLException {
		
		PermissionDAO _permissionDao = FactoryDAO.getPermissionDAO(this.cnxUserRight);
		
		_permissionDao.delete(_permission);
	}
	
	protected Permission getPermission(int idpermission) throws SQLException {
		
		PermissionDAO _permissionDao = FactoryDAO.getPermissionDAO(this.cnxUserRight);
		
		Permission _permission = _permissionDao.read(idpermission);
		
		return _permission;
	}
	
	protected Permission getPermission(String name) throws SQLException {
		
		PermissionDAO _permissionDao = FactoryDAO.getPermissionDAO(this.cnxUserRight);
		
		Permission _permission = _permissionDao.read(name);
		
		return _permission;
	}
	
	protected int maxIdPermission() throws SQLException {
		
		PermissionDAO _permissionDao = FactoryDAO.getPermissionDAO(this.cnxUserRight);
		
		int _idpermission = _permissionDao.readAll().size();
		
		return _idpermission;
	}
}