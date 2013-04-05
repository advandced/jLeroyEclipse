package fr.la.juserright.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import fr.la.juserright.metier.Role;
import fr.la.juserright.metier.User;
import fr.la.juserright.metier.UserRole;
import fr.la.juserright.service.ServiceUserRight;

public class UserRoleDAO implements ModelDAO<UserRole> {

	private ConnectionUserRight cnxUserRight;

	private ServiceUserRight moduleGlobal = new ServiceUserRight();

	public UserRoleDAO(ConnectionUserRight cnxUserRight) {
		this.cnxUserRight = cnxUserRight;
	}

	public void create(UserRole _object) throws SQLException {
		PreparedStatement _stmt = null;

		try {
			_stmt = this.cnxUserRight.getCnx().prepareStatement(
					"INSERT INTO userrole (iduser, idrole) " + "VALUES ("
							+ _object.getUser().getIduser() + "" + ", "
							+ _object.getRole().getIdrole() + ");");
			_stmt.executeUpdate();

		} catch (NamingException e) {
			e.printStackTrace();
		} finally {
			if (null != _stmt) {
				_stmt.close();
			}
			try {
				this.cnxUserRight.getCnx().close();
			} catch (NamingException e) {
				e.printStackTrace();
			}
		}
	}

	public UserRole read(UserRole _object) throws SQLException {
		return null;
	}

	public List<UserRole> readAll() throws SQLException {
		List<UserRole> _userrole = new ArrayList<UserRole>();
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			_stmt = this.cnxUserRight.getCnx().prepareStatement(
					"SELECT * FROM UserRole");
			_rs = _stmt.executeQuery();
			while (_rs.next()) {
				UserRole _userroletmp = this.getUserRole(_rs);
				_userrole.add(_userroletmp);

			}
		} catch (NamingException e) {
			e.printStackTrace();
		} finally {
			try{
	            if(_rs != null) _rs.close();
	        }
	        catch(SQLException sqlEx){

	        }   


	        try{
	           if(_stmt != null) _stmt.close();
	        }
	        catch(SQLException sqlEx){

	        }   
		}
		return _userrole;
	}

	public void update(UserRole _object) throws SQLException {
		this.delete(_object);
		this.create(_object);
	}

	public void delete(UserRole _object) throws SQLException {
		PreparedStatement _stmt = null;

		try {
			_stmt = this.cnxUserRight.getCnx()
					.prepareStatement(
							"DELETE FROM userrole " + "WHERE iduser = "
									+ _object.getUser().getIduser() + " AND "
									+ "idrole = "
									+ _object.getRole().getIdrole() + ";");
			_stmt.executeUpdate();

		} catch (NamingException e) {
			e.printStackTrace();
		} finally {
			if (null != _stmt) {
				_stmt.close();
			}
			try {
				this.cnxUserRight.getCnx().close();
			} catch (NamingException e) {
				e.printStackTrace();
			}
		}
	}

	public List<UserRole> getUserRoleWithIdUser(int iduser) throws SQLException {
		List<UserRole> _userrole = new ArrayList<UserRole>();
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			_stmt = this.cnxUserRight.getCnx().prepareStatement(
					"SELECT * FROM userrole" + " WHERE iduser = ?;");
			_stmt.setInt(1, iduser);
			_rs = _stmt.executeQuery();
			while (_rs.next()) {
				UserRole _userroletmp = this.getUserRole(_rs);
				_userrole.add(_userroletmp);
			}
		} catch (NamingException e) {
			e.printStackTrace();
		} finally {
			try{
	            if(_rs != null) _rs.close();
	        }
	        catch(SQLException sqlEx){

	        }   


	        try{
	           if(_stmt != null) _stmt.close();
	        }
	        catch(SQLException sqlEx){
	        }   


		}
		return _userrole;
	}

	public List<UserRole> getUserRoleWithLogin(String _login)
			throws SQLException {
		List<UserRole> _userrole = new ArrayList<UserRole>();
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			_stmt = this.cnxUserRight.getCnx().prepareStatement("SELECT * FROM userrole WHERE iduser = (SELECT iduser FROM user WHERE login = ?);");
			_stmt.setString(1, _login);
			_rs = _stmt.executeQuery();
			while (_rs.next()) {
				UserRole _userroletmp = this.getUserRole(_rs);
				_userrole.add(_userroletmp);
			}
			_stmt.setFetchSize(100);
		} catch (NamingException e) {
			e.printStackTrace();
		} finally {
			try{
	            if(_rs != null) _rs.close();
	        }
	        catch(SQLException sqlEx){

	        }   
	        try{
	           if(_stmt != null) _stmt.close();
	        }
	        catch(SQLException sqlEx){

	        }   
		}
		return _userrole;
	}

	private UserRole getUserRole(ResultSet _rs) throws SQLException {
		Role _role = moduleGlobal.getRole(_rs.getInt("idrole"));
		User _user = moduleGlobal.getUser(_rs.getInt("iduser"));

		UserRole _userrole = new UserRole(_role, _user);
		return _userrole;
	}

}