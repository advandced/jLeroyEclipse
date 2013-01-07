package fr.la.juserright.dao;

public class FactoryDAO {

	public static AutorisationDAO getAutorisationDAO(ConnectionUserRight _cnxUserRight) {
		return new AutorisationDAO(_cnxUserRight);
	}
	
	public static PermissionDAO getPermissionDAO(ConnectionUserRight _cnxUserRight) {
		return new PermissionDAO(_cnxUserRight);
	}
	
	public static RessourceDAO getRessourceDAO(ConnectionUserRight _cnxUserRight) {
		return new RessourceDAO(_cnxUserRight);
	}
	
	public static Ressource_RessourceDAO getRessource_RessourceDAO(ConnectionUserRight _cnxUserRight) {
		return new Ressource_RessourceDAO(_cnxUserRight);
	}
	
	public static RoleDAO getRoleDAO(ConnectionUserRight _cnxUserRight) {
		return new RoleDAO(_cnxUserRight);
	}
	
	public static UserDAO getUserDAO(ConnectionUserRight _cnxUserRight) {
		return new UserDAO(_cnxUserRight);
	}
	
	public static UserRoleDAO getUserRoleDAO(ConnectionUserRight _cnxUserRight) {
		return new UserRoleDAO(_cnxUserRight);
	}
}