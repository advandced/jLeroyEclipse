package fr.la.juserright.service;

import java.sql.SQLException;
import java.util.List;

import fr.la.juserright.dao.ConnectionUserRight;
import fr.la.juserright.dao.FactoryDAO;
import fr.la.juserright.dao.RoleDAO;
import fr.la.juserright.exception.ErrorException;
import fr.la.juserright.metier.Role;

public class RoleModule {
	private ConnectionUserRight cnxUserRight;

	protected RoleModule(ConnectionUserRight cnxUserRight) {
		this.cnxUserRight = cnxUserRight;
	}

	protected void createRole(Role _role) throws SQLException {

		RoleDAO _roleDao = FactoryDAO.getRoleDAO(this.cnxUserRight);

		_roleDao.create(_role);
	}

	protected List<Role> getAllRole() throws SQLException {

		RoleDAO _roleDao = FactoryDAO.getRoleDAO(this.cnxUserRight);

		List<Role> __roleDao = _roleDao.readAll();

		return __roleDao;
	}

	protected void updateRole(Role _role) throws SQLException {

		RoleDAO _roleDao = FactoryDAO.getRoleDAO(this.cnxUserRight);

		_roleDao.update(_role);
	}

	protected Role updateRoleIfNotExist(Role _role) throws SQLException, ErrorException {

		RoleDAO _roleDao = FactoryDAO.getRoleDAO(this.cnxUserRight);

		Role __role = _roleDao.updateRoleIfNotExist(_role);
	
		return __role;
		
	}

	protected void deleteRole(Role _role) throws SQLException {

		RoleDAO _roleDao = FactoryDAO.getRoleDAO(this.cnxUserRight);

		_roleDao.delete(_role);
	}

	protected Role getRole(int idrole) throws SQLException {

		RoleDAO _roleDao = FactoryDAO.getRoleDAO(this.cnxUserRight);

		Role _role = _roleDao.getRole(idrole);

		return _role;
	}

	protected Role getRole(String role) throws SQLException {

		RoleDAO _roleDao = FactoryDAO.getRoleDAO(this.cnxUserRight);

		Role _role = _roleDao.getRole(role);

		return _role;
	}
}