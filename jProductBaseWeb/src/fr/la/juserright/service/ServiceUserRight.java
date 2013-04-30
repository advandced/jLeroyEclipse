package fr.la.juserright.service;

import java.io.Serializable;
import java.util.List;

import fr.la.jproductbase.service.ServiceManager;
import fr.la.juserright.dao.AutorisationDAO;
import fr.la.juserright.dao.ConnectionUserRight;
import fr.la.juserright.dao.PermissionDAO;
import fr.la.juserright.dao.RessourceDAO;
import fr.la.juserright.dao.Ressource_RessourceDAO;
import fr.la.juserright.dao.RoleDAO;
import fr.la.juserright.dao.UserDAO;
import fr.la.juserright.dao.UserRoleDAO;
import fr.la.juserright.metier.Autorisation;
import fr.la.juserright.metier.Permission;
import fr.la.juserright.metier.Ressource;
import fr.la.juserright.metier.Ressource_Ressource;
import fr.la.juserright.metier.Role;
import fr.la.juserright.metier.User;
import fr.la.juserright.metier.UserRole;

public class ServiceUserRight implements Serializable {

    private static final long serialVersionUID = 1L;
    //private ConnectionUserRight cnxUserRight;

    private static ServiceUserRight serviceUserRightInstance = null;
    ServiceManager sm;
    
    public static synchronized ServiceUserRight getInstance() {
    	if (serviceUserRightInstance == null) {
    		serviceUserRightInstance = new ServiceUserRight();
    	}
    	return serviceUserRightInstance;
    }
    
    private ServiceUserRight() {
		sm = ServiceManager.getServiceManagerInstance();

		sm.registerServiceAsUnique(AutorisationDAO.class , AutorisationDAO.class);
		sm.registerServiceAsUnique(ConnectionUserRight.class , ConnectionUserRight.class);
		sm.registerServiceAsUnique(PermissionDAO.class , PermissionDAO.class);
		sm.registerServiceAsUnique(Ressource_RessourceDAO.class , Ressource_RessourceDAO.class);
		sm.registerServiceAsUnique(RessourceDAO.class , RessourceDAO.class);
		sm.registerServiceAsUnique(RoleDAO.class , RoleDAO.class);
		sm.registerServiceAsUnique(UserDAO.class , UserDAO.class);
		sm.registerServiceAsUnique(UserRoleDAO.class , UserRoleDAO.class);
    	
		sm.registerServiceAsUnique(AutorisationModule.class , AutorisationModule.class);
		sm.registerServiceAsUnique(PermissionModule.class , PermissionModule.class);
		sm.registerServiceAsUnique(Ressource_RessourceModule.class , Ressource_RessourceModule.class);
		sm.registerServiceAsUnique(RessourceModule.class , RessourceModule.class);
		sm.registerServiceAsUnique(RoleModule.class , RoleModule.class);
		sm.registerServiceAsUnique(UserModule.class , UserModule.class);
		sm.registerServiceAsUnique(UserroleModule.class , UserroleModule.class);
    	
    }

    /*
     * Cree une Permission
     */
    public void createPermission(Permission _permission) {
    	PermissionModule _permissionModule = (PermissionModule) sm.getService(PermissionModule.class);
        _permissionModule.createPermission(_permission);
    }

    /*
     * Retourne la List de toutes les donnees de la table Permission
     */
    public List<Permission> getAllPermission() {
    	PermissionModule _permissionModule = (PermissionModule) sm.getService(PermissionModule.class);
        List<Permission> _permission = _permissionModule.getAllPermission();
        return _permission;
    }

    /*
     * Update une Permission
     */
    public void updatePermission(Permission _permission) {
    	PermissionModule _permissionModule = (PermissionModule) sm.getService(PermissionModule.class);
        _permissionModule.updatePermission(_permission);
    }

    /*
     * Supprime une Permission
     */
    public void deletePermission(Permission _permission) {
    	PermissionModule _permissionModule = (PermissionModule) sm.getService(PermissionModule.class);
        _permissionModule.deletePermission(_permission);
    }

    /*
     * Retourne une Permission en fonction de l'id
     */
    public Permission getPermission(int idpermission) {
    	PermissionModule _permissionModule = (PermissionModule) sm.getService(PermissionModule.class);
        Permission _permission = _permissionModule.getPermission(idpermission);
        return _permission;
    }

    /*
     * Retourne une Permission en fonction de son name
     */
    public Permission getPermission(String name) {
    	PermissionModule _permissionModule = (PermissionModule) sm.getService(PermissionModule.class);
        Permission _permission = _permissionModule.getPermission(name);
        return _permission;
    }

    /*
     * Cree une ressource
     */
    public void createRessource(Ressource _ressource) {
    	RessourceModule _ressourceModule = (RessourceModule) sm.getService(RessourceModule.class);
        _ressourceModule.createRessource(_ressource);
    }

    /*
     * Retourne tous les enregistrements de Ressource
     */
    public List<Ressource> getAllRessource() {
    	RessourceModule _ressourceModule = (RessourceModule) sm.getService(RessourceModule.class);
        List<Ressource> _ressource = _ressourceModule.getAllRessource();
        return _ressource;
    }

    /*
     * Update toutes une ressource en fonction de son id
     */
    public void updateRessource(Ressource _ressource) {
    	RessourceModule _ressourceModule = (RessourceModule) sm.getService(RessourceModule.class);
        _ressourceModule.updateRessource(_ressource);
    }

    /*
     * Supprime une ressource en fonction de son id
     */
    public void deleteRessource(Ressource _ressource) {
    	RessourceModule _ressourceModule = (RessourceModule) sm.getService(RessourceModule.class);
        _ressourceModule.delete(_ressource);
    }

    /*
     * Retourne une ressource en fonction de son id
     */
    public Ressource getRessource(int idressource) {
    	RessourceModule _ressourceModule = (RessourceModule) sm.getService(RessourceModule.class);
        Ressource _ressource = _ressourceModule.getRessource(idressource);
        return _ressource;
    }

    /*
     * Cree un role
     */
    public void createRole(Role _role) {
    	RoleModule _roleModule = (RoleModule) sm.getService(RoleModule.class);
        _roleModule.createRole(_role);
    }

    /*
     * Retourne tous le contenu de la table Role
     */
    public List<Role> getAllRole() {
    	RoleModule _roleModule = (RoleModule) sm.getService(RoleModule.class);
    	List<Role> _role = _roleModule.getAllRole();
        return _role;
    }

    /*
     * Update un Role
     */
    public void updateRole(Role _role) {
    	RoleModule _roleModule = (RoleModule) sm.getService(RoleModule.class);
        _roleModule.updateRole(_role);
    }

    /*
     * Update un Role si le nouveau Nom de celui-ci n'est pas present dans la
     * base.
     */
    public Role updateRoleIfNotExist(Role _role) {
    	RoleModule _roleModule = (RoleModule) sm.getService(RoleModule.class);
        Role __role = _roleModule.updateRoleIfNotExist(_role);
        return __role;
    }

    /*
     * Delete un role en fonction de leur id
     */
    public void deleteRole(Role _role) {
    	RoleModule _roleModule = (RoleModule) sm.getService(RoleModule.class);
        _roleModule.deleteRole(_role);
    }

    /*
     * Retourne un role en fonction de l'id
     */
    public Role getRole(int idrole) {
    	RoleModule _roleModule = (RoleModule) sm.getService(RoleModule.class);
        Role _role = _roleModule.getRole(idrole);
        return _role;
    }

    /*
     * Retourne un role en fonction de son nom
     */
    public Role getRole(String role) {
    	RoleModule _roleModule = (RoleModule) sm.getService(RoleModule.class);
        Role _role = _roleModule.getRole(role);
        return _role;
    }

    /*
     * cree un user
     */
    public void createUser(User _user) {
    	UserModule _userModule = (UserModule) sm.getService(UserModule.class);
        _userModule.createUser(_user);
    }

    /*
     * retourne tous le contenu de la table user
     */
    public List<User> getAllUser() {
    	UserModule _userModule = (UserModule) sm.getService(UserModule.class);
        List<User> _user = _userModule.getAllUser();
        return _user;
    }

    /*
     * Update un user
     */
    public void updateUser(User _user) {
    	UserModule _userModule = (UserModule) sm.getService(UserModule.class);
        _userModule.updateUser(_user);
    }

    /*
     * Delete un user
     */
    public void deleteUser(User _user) {
    	UserModule _userModule = (UserModule) sm.getService(UserModule.class);
        _userModule.deleteUser(_user);
    }

    /*
     * Retourne un user en fonction d'un ID
     */
    public User getUser(int idUser) {
    	UserModule _userModule = (UserModule) sm.getService(UserModule.class);
        User _user = _userModule.getUser(idUser);
        return _user;
    }

    /*
     * Retourne un user en fonction du login
     */
    public User getUser(String login) {
    	UserModule _userModule = (UserModule) sm.getService(UserModule.class);
        User _user = _userModule.getUser(login);
        return _user;
    }

    /*
     * Verifie dans la base si le login / mdp et correct
     */
    public User login(User user) {
    	UserModule _userModule = (UserModule) sm.getService(UserModule.class);
        User _user = _userModule.login(user);
        return _user;
    }

    /*
     * Cree un UserRole
     */
    public void createUserRole(UserRole _userrole) {
    	UserroleModule _userroleModule = (UserroleModule) sm.getService(UserroleModule.class);
        _userroleModule.createUserRole(_userrole);
    }

    /*
     * Retourne tous le contenu de la table userrole
     */
    public List<UserRole> getAllUserRole() {
    	UserroleModule _userroleModule = (UserroleModule) sm.getService(UserroleModule.class);
        List<UserRole> _userrole = _userroleModule.getAllUserRole();
        return _userrole;
    }

    /*
     * Update un UserRole
     */
    public void updateUserRole(UserRole _userrole) {
    	UserroleModule _userroleModule = (UserroleModule) sm.getService(UserroleModule.class);
        _userroleModule.updateUserRole(_userrole);
    }

    /*
     * delete un UserRole
     */
    public void deleteUserRole(UserRole _userrole) {
    	UserroleModule _userroleModule = (UserroleModule) sm.getService(UserroleModule.class);
        _userroleModule.deleteUserRole(_userrole);
    }

    /*
     * Retourne tous les userrole en fonction d'une iduser
     */
    public List<UserRole> getUserRoleWithIdUser(int iduser) {
    	UserroleModule _userroleModule = (UserroleModule) sm.getService(UserroleModule.class);
        List<UserRole> _userrole = _userroleModule.getUserRoleWithIdUser(iduser);
        return _userrole;
    }

    /*
     * Cree une autorisation
     */
    public void createAutorisation(Autorisation _autorisation) {
    	AutorisationModule _autorisationModule = (AutorisationModule) sm.getService(AutorisationModule.class);
        _autorisationModule.createAutorisation(_autorisation);
    }

    /*
     * Retourne toutes les autorisations ayant les meme idpermission
     */
    public List<Autorisation> getAutorisationWithIdPermission(int idpermission) {
    	AutorisationModule _autorisationModule = (AutorisationModule) sm.getService(AutorisationModule.class);
        List<Autorisation> _autorisation = _autorisationModule.getAutorisationWithIdPermission(idpermission);
        return _autorisation;
    }

    /*
     * Retourne toutes les autorisations ayant les meme idressource
     */
    public List<Autorisation> getAutorisationWithIdRessource(int idressource) {
    	AutorisationModule _autorisationModule = (AutorisationModule) sm.getService(AutorisationModule.class);
        List<Autorisation> _autorisation = _autorisationModule.getAutorisationWithIdRessource(idressource);
        return _autorisation;
    }

    /*
     * Retourne toutes les autorisations ayant les meme idrole
     */
    public List<Autorisation> getAutorisationWithIdRole(int idrole) {
    	AutorisationModule _autorisationModule = (AutorisationModule) sm.getService(AutorisationModule.class);
        List<Autorisation> _autorisation = _autorisationModule.getAutorisationWithIdRole(idrole);
        return _autorisation;
    }

    /*
     * Update une autorisation
     */
    public void updateAutorisation(Autorisation _autorisation) {
    	AutorisationModule _autorisationModule = (AutorisationModule) sm.getService(AutorisationModule.class);
        _autorisationModule.updateAutorisation(_autorisation);
    }

    /*
     * Delete une autorisation
     */
    public void deleteAutorisation(Autorisation _autorisation) {
    	AutorisationModule _autorisationModule = (AutorisationModule) sm.getService(AutorisationModule.class);
        _autorisationModule.deleteAutorisation(_autorisation);
    }

    /*
     * Retourne tous les users qui ne font pas parti de ce groupe
     */
    public List<User> getUserAddRole(int idrole) {
    	UserModule _userModule = (UserModule) sm.getService(UserModule.class);
        List<User> _user = _userModule.getUserAddRole(idrole);
        return _user;
    }

    /*
     * Retourne tous les users fesant parti de ce groupe
     */
    public List<User> getUserForARole(int idrole) {
    	UserModule _userModule = (UserModule) sm.getService(UserModule.class);
        List<User> _user = _userModule.getUserForARole(idrole);
        return _user;
    }

    /*
     * Retourne la valeur d'id MAX de la table permission afin que la nouvelle
     * valeur ajouter soit toujours d'id MAX + 1
     */
    public int maxIdPermission() {
    	PermissionModule _permissionModule = (PermissionModule) sm.getService(PermissionModule.class);
        int _idpermission = _permissionModule.maxIdPermission();
        return _idpermission;
    }

    /*
     * Renvoi toutes les Ressources qui n'ont pas encore de droit attribuer en
     * fonction de l'autorisation.
     */
    public List<Ressource> selectRessourceNotUsedByRole(List<Autorisation> _listAutorisation) {
    	RessourceModule _ressourceModule = (RessourceModule) sm.getService(RessourceModule.class);
        List<Ressource> _listRessource = _ressourceModule.selectRessourceNotUsedByRole(_listAutorisation);
        return _listRessource;
    }

    /*
     * Renvoi un objet autorisation si une autorisation existe deja en base avec
     * ces id ressource et role.
     */
    public Autorisation checkAutorisationExists(Autorisation _autorisation) {
    	AutorisationModule _autorisationModule = (AutorisationModule) sm.getService(AutorisationModule.class);
        Autorisation __autorisation = _autorisationModule.checkAutorisationExists(_autorisation);
        return __autorisation;
    }

    /*
     * Verifie lors de l'update si le login n'ai pas deja present en base.
     */
    public User updateUserIfLoginNotExist(User _user) {
    	UserModule _userModule = (UserModule) sm.getService(UserModule.class);
        User __user = _userModule.updateUserIfLoginNotExist(_user);
        return __user;
    }

    /*
     * Update le password en fonction de l'id User
     */
    public void updateUserPassword(User _user) {
    	UserModule _userModule = (UserModule) sm.getService(UserModule.class);
        _userModule.updateUserPassword(_user);
    }

    /*
     * Retourne une ressource en fonction de son Menu
     */
    public Ressource getRessource(Ressource object) {
    	RessourceModule _ressourceModule = (RessourceModule) sm.getService(RessourceModule.class);
        Ressource _ressource = _ressourceModule.getRessource(object);
        return _ressource;
    }

    /*
     * Retourne une ressource apres ca creation
     */
    public Ressource createRessourceWithRR(Ressource object) {
    	RessourceModule _ressourceModule = (RessourceModule) sm.getService(RessourceModule.class);
        Ressource _ressource = _ressourceModule.createRessourceWithRR(object);
        return _ressource;
    }

    /*
     * Retourne une ressource_ressource apres ca creation
     */
    public Ressource_Ressource createRessource_Ressource(int idressource) {
    	Ressource_RessourceModule _rrModule = (Ressource_RessourceModule) sm.getService(Ressource_RessourceModule.class);
        Ressource_Ressource _rr = _rrModule.createRessource_Ressource(idressource);
        return _rr;
    }

    /*
     * Update une ressource pour ajouter une Ressource_Ressource.
     */
    public void updateidRR(Ressource _ressource, Ressource_Ressource _rr) {
    	RessourceModule _ressourceModule = (RessourceModule) sm.getService(RessourceModule.class);
        _ressourceModule.updateidRR(_ressource, _rr);
    }

    /*
     * Retour une Ressource_Ressource en fonction de son ID
     */
    public Ressource_Ressource getRessource_Ressource(int idRessource_Ressource) {
    	Ressource_RessourceModule _rrModule = (Ressource_RessourceModule) sm.getService(Ressource_RessourceModule.class);
        Ressource_Ressource _rr = _rrModule.readRessource_Ressource(idRessource_Ressource);
        return _rr;
    }

    /*
     * Retour une Ressource_Ressource en fonction de son IDRessource (presente
     * dans l'object)
     */
    public Ressource_Ressource getRessource_Ressource(Ressource_Ressource _object) {
    	Ressource_RessourceModule _rrModule = (Ressource_RessourceModule) sm.getService(Ressource_RessourceModule.class);
        Ressource_Ressource _rr = _rrModule.readRessource_Ressource(_object);
        return _rr;
    }

    /*
     * Renvoi le menu d'une ressource en fonction de l'id ressource_ressource
     */
    public String getMenuWithRR(int idressource_ressource) {
    	RessourceModule _ressourceModule = (RessourceModule) sm.getService(RessourceModule.class);
        String menu = _ressourceModule.getMenuWithRR(idressource_ressource);
        return menu;
    }

    /*
     * Renvoi un objet Ressource en fonction de son Menu
     */
    public Ressource getRessourceByMenu(String Menu) {
    	RessourceModule _ressourceModule = (RessourceModule) sm.getService(RessourceModule.class);
        Ressource _ressource = _ressourceModule.getRessourceByMenu(Menu);
        return _ressource;
    }

    /*
     * Renvoi un objet Ressource en fonction de son Path
     */
    public Ressource getRessourceByPath(String Path) {
    	RessourceModule _ressourceModule = (RessourceModule) sm.getService(RessourceModule.class);
        Ressource _ressource = _ressourceModule.getRessourceByPath(Path);
        return _ressource;
    }

    /*
     * Verifie en fonction de son Path et de l'idressource si le Path et deja
     * present en base pour une autre ressource
     */
    public Ressource getRessourceByPathForUpdate(String Path, int idressource) {
    	RessourceModule _ressourceModule = (RessourceModule) sm.getService(RessourceModule.class);
        Ressource _ressource = _ressourceModule.getRessourceByPathForUpdate(Path, idressource);
        return _ressource;

    }

    /*
     * Verifie en fonction de son Menu et de l'idressource si le Menu et deja
     * present en base pour une autre ressource
     */
    public Ressource getRessourceByMenuForUpdate(String Menu, int idressource) {
    	RessourceModule _ressourceModule = (RessourceModule) sm.getService(RessourceModule.class);
        Ressource _ressource = _ressourceModule.getRessourceByMenuForUpdate(Menu, idressource);
        return _ressource;

    }

    /*
     * Delete une Ressource_Ressource en fonction de son ID ressource.
     */
    public void deleteRessource_RessourceByidRessource(Ressource_Ressource _object) {
    	Ressource_RessourceModule _rrModule = (Ressource_RessourceModule) sm.getService(Ressource_RessourceModule.class);
        _rrModule.deleteRessource_RessourceByidRessource(_object);
    }

    /*
     * reset les idRR des ressources dont les idRR ont etaient supprimer
     */
    public void updateToNullIDRR(int idressource_ressource) {
    	RessourceModule _ressourceModule = (RessourceModule) sm.getService(RessourceModule.class);
        _ressourceModule.updateToNullIDRR(idressource_ressource);
    }

    /*
     * retourne tous les enregistrements de la table Autorisation
     */
    public List<Autorisation> getAllAutorisation() {
    	AutorisationModule _autorisationModule = (AutorisationModule) sm.getService(AutorisationModule.class);
        List<Autorisation> __autorisation = _autorisationModule.getAllAutorisation();
        return __autorisation;
    }

    /*
     * Retourne les userrole en fonction d'un login user
     */
    public List<UserRole> getUserRoleWithLogin(String login) {
    	UserroleModule _userroleModule = (UserroleModule) sm.getService(UserroleModule.class);
        List<UserRole> _userrole = _userroleModule.getUserRoleWithLogin(login);
        return _userrole;
    }

    /*
     * Renvoi toutes les autorisations en fonction du login
     */
    public List<Autorisation> getAutorisationByLogin(String _login) {
    	AutorisationModule _autorisationModule = (AutorisationModule) sm.getService(AutorisationModule.class);
        List<Autorisation> _autorisation = _autorisationModule.getAutorisationByLogin(_login);
        return _autorisation;
    }
}