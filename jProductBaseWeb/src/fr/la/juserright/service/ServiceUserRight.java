package fr.la.juserright.service;

import fr.la.configfilereader.ConfigFileReaderException;
import fr.la.juserright.dao.ConnectionUserRight;
import fr.la.juserright.exception.ErrorException;
import fr.la.juserright.metier.Autorisation;
import fr.la.juserright.metier.Permission;
import fr.la.juserright.metier.Ressource;
import fr.la.juserright.metier.Ressource_Ressource;
import fr.la.juserright.metier.Role;
import fr.la.juserright.metier.User;
import fr.la.juserright.metier.UserRole;
import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

public class ServiceUserRight implements Serializable {

    private static final long serialVersionUID = 1L;
    private ConnectionUserRight cnxUserRight;

    public ServiceUserRight() {
        try {
            this.cnxUserRight = new ConnectionUserRight();
        } catch (ConfigFileReaderException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /*
     * Cree une Permission
     */
    public void createPermission(Permission _permission) throws SQLException {

        PermissionModule _permissionModule = new PermissionModule(
                this.cnxUserRight);

        _permissionModule.createPermission(_permission);

    }

    /*
     * Retourne la List de toutes les donnees de la table Permission
     */
    public List<Permission> getAllPermission() throws SQLException {

        PermissionModule _permissionModule = new PermissionModule(
                this.cnxUserRight);

        List<Permission> _permission = _permissionModule.getAllPermission();

        return _permission;

    }

    /*
     * Update une Permission
     */
    public void updatePermission(Permission _permission) throws SQLException {

        PermissionModule _permissionModule = new PermissionModule(
                this.cnxUserRight);

        _permissionModule.updatePermission(_permission);

    }

    /*
     * Supprime une Permission
     */
    public void deletePermission(Permission _permission) throws SQLException {

        PermissionModule _permissionModule = new PermissionModule(
                this.cnxUserRight);

        _permissionModule.deletePermission(_permission);

    }

    /*
     * Retourne une Permission en fonction de l'id
     */
    public Permission getPermission(int idpermission) throws SQLException {

        PermissionModule _permissionModule = new PermissionModule(
                this.cnxUserRight);

        Permission _permission = _permissionModule.getPermission(idpermission);

        return _permission;

    }

    /*
     * Retourne une Permission en fonction de son name
     */
    public Permission getPermission(String name) throws SQLException {

        PermissionModule _permissionModule = new PermissionModule(
                this.cnxUserRight);

        Permission _permission = _permissionModule.getPermission(name);

        return _permission;

    }

    /*
     * Cree une ressource
     */
    public void createRessource(Ressource _ressource) throws SQLException {

        RessourceModule _ressourceModule = new RessourceModule(
                this.cnxUserRight);

        _ressourceModule.createRessource(_ressource);

    }

    /*
     * Retourne tous les enregistrements de Ressource
     */
    public List<Ressource> getAllRessource() throws SQLException {

        RessourceModule _ressourceModule = new RessourceModule(this.cnxUserRight);

        List<Ressource> _ressource = _ressourceModule.getAllRessource();
        
        return _ressource;

    }

    /*
     * Update toutes une ressource en fonction de son id
     */
    public void updateRessource(Ressource _ressource) throws SQLException {

        RessourceModule _ressourceModule = new RessourceModule(
                this.cnxUserRight);

        _ressourceModule.updateRessource(_ressource);

    }

    /*
     * Supprime une ressource en fonction de son id
     */
    public void deleteRessource(Ressource _ressource) throws SQLException {

        RessourceModule _ressourceModule = new RessourceModule(
                this.cnxUserRight);

        _ressourceModule.delete(_ressource);

    }

    /*
     * Retourne une ressource en fonction de son id
     */
    public Ressource getRessource(int idressource) throws SQLException {

        RessourceModule _ressourceModule = new RessourceModule(
                this.cnxUserRight);

        Ressource _ressource = _ressourceModule.getRessource(idressource);

        return _ressource;

    }

    /*
     * Cree un role
     */
    public void createRole(Role _role) throws SQLException {

        RoleModule _roleModule = new RoleModule(this.cnxUserRight);

        _roleModule.createRole(_role);

    }

    /*
     * Retourne tous le contenu de la table Role
     */
    public List<Role> getAllRole() throws SQLException {

        RoleModule _roleModule = new RoleModule(this.cnxUserRight);

        List<Role> _role = _roleModule.getAllRole();

        return _role;

    }

    /*
     * Update un Role
     */
    public void updateRole(Role _role) throws SQLException {

        RoleModule _roleModule = new RoleModule(this.cnxUserRight);

        _roleModule.updateRole(_role);

    }

    /*
     * Update un Role si le nouveau Nom de celui-ci n'est pas present dans la
     * base.
     */
    public Role updateRoleIfNotExist(Role _role) throws SQLException,
            ErrorException {

        RoleModule _roleModule = new RoleModule(this.cnxUserRight);

        Role __role = _roleModule.updateRoleIfNotExist(_role);

        return __role;
    }

    /*
     * Delete un role en fonction de leur id
     */
    public void deleteRole(Role _role) throws SQLException {

        RoleModule _roleModule = new RoleModule(this.cnxUserRight);

        _roleModule.deleteRole(_role);

    }

    /*
     * Retourne un role en fonction de l'id
     */
    public Role getRole(int idrole) throws SQLException {

        RoleModule _roleModule = new RoleModule(this.cnxUserRight);

        Role _role = _roleModule.getRole(idrole);

        return _role;

    }

    /*
     * Retourne un role en fonction de son nom
     */
    public Role getRole(String role) throws SQLException {

        RoleModule _roleModule = new RoleModule(this.cnxUserRight);

        Role _role = _roleModule.getRole(role);

        return _role;

    }

    /*
     * cree un user
     */
    public void createUser(User _user) throws SQLException {

        UserModule _userModule = new UserModule(this.cnxUserRight);

        _userModule.createUser(_user);

    }

    /*
     * retourne tous le contenu de la table user
     */
    public List<User> getAllUser() throws SQLException {

        UserModule _userModule = new UserModule(this.cnxUserRight);

        List<User> _user = _userModule.getAllUser();

        return _user;

    }

    /*
     * Update un user
     */
    public void updateUser(User _user) throws SQLException {

        UserModule _userModule = new UserModule(this.cnxUserRight);

        _userModule.updateUser(_user);

    }

    /*
     * Delete un user
     */
    public void deleteUser(User _user) throws SQLException {

        UserModule _userModule = new UserModule(this.cnxUserRight);

        _userModule.deleteUser(_user);

    }

    /*
     * Retourne un user en fonction d'un ID
     */
    public User getUser(int idUser) throws SQLException {

        UserModule _userModule = new UserModule(this.cnxUserRight);

        User _user = _userModule.getUser(idUser);

        return _user;

    }

    /*
     * Retourne un user en fonction du login
     */
    public User getUser(String login) throws SQLException {

        UserModule _userModule = new UserModule(this.cnxUserRight);

        User _user = _userModule.getUser(login);

        return _user;

    }

    /*
     * Verifie dans la base si le login / mdp et correct
     */
    public User login(User user) throws SQLException {

        UserModule _userModule = new UserModule(this.cnxUserRight);

        User _user = _userModule.login(user);

        return _user;

    }

    /*
     * Cree un UserRole
     */
    public void createUserRole(UserRole _userrole) throws SQLException {

        UserroleModule _userroleModule = new UserroleModule(this.cnxUserRight);

        _userroleModule.createUserRole(_userrole);

    }

    /*
     * Retourne tous le contenu de la table userrole
     */
    public List<UserRole> getAllUserRole() throws SQLException {

        UserroleModule _userroleModule = new UserroleModule(this.cnxUserRight);

        List<UserRole> _userrole = _userroleModule.getAllUserRole();

        return _userrole;

    }

    /*
     * Update un UserRole
     */
    public void updateUserRole(UserRole _userrole) throws SQLException {

        UserroleModule _userroleModule = new UserroleModule(this.cnxUserRight);

        _userroleModule.updateUserRole(_userrole);

    }

    /*
     * delete un UserRole
     */
    public void deleteUserRole(UserRole _userrole) throws SQLException {

        UserroleModule _userroleModule = new UserroleModule(this.cnxUserRight);

        _userroleModule.deleteUserRole(_userrole);

    }

    /*
     * Retourne tous les userrole en fonction d'une iduser
     */
    public List<UserRole> getUserRoleWithIdUser(int iduser) throws SQLException {

        UserroleModule _userroleModule = new UserroleModule(this.cnxUserRight);

        List<UserRole> _userrole = _userroleModule
                .getUserRoleWithIdUser(iduser);

        return _userrole;

    }

    /*
     * Cree une autorisation
     */
    public void createAutorisation(Autorisation _autorisation)
            throws SQLException {

        AutorisationModule _autorisationModule = new AutorisationModule(
                this.cnxUserRight);

        _autorisationModule.createAutorisation(_autorisation);

    }

    /*
     * Retourne toutes les autorisations ayant les meme idpermission
     */
    public List<Autorisation> getAutorisationWithIdPermission(int idpermission)
            throws SQLException {

        AutorisationModule _autorisationModule = new AutorisationModule(
                this.cnxUserRight);

        List<Autorisation> _autorisation = _autorisationModule
                .getAutorisationWithIdPermission(idpermission);

        return _autorisation;

    }

    /*
     * Retourne toutes les autorisations ayant les meme idressource
     */
    public List<Autorisation> getAutorisationWithIdRessource(int idressource)
            throws SQLException {

        AutorisationModule _autorisationModule = new AutorisationModule(
                this.cnxUserRight);

        List<Autorisation> _autorisation = _autorisationModule
                .getAutorisationWithIdRessource(idressource);

        return _autorisation;

    }

    /*
     * Retourne toutes les autorisations ayant les meme idrole
     */
    public List<Autorisation> getAutorisationWithIdRole(int idrole)
            throws SQLException {

        AutorisationModule _autorisationModule = new AutorisationModule(
                this.cnxUserRight);

        List<Autorisation> _autorisation = _autorisationModule
                .getAutorisationWithIdRole(idrole);

        return _autorisation;

    }

    /*
     * Update une autorisation
     */
    public void updateAutorisation(Autorisation _autorisation)
            throws SQLException {

        AutorisationModule _autorisationModule = new AutorisationModule(
                this.cnxUserRight);

        _autorisationModule.updateAutorisation(_autorisation);

    }

    /*
     * Delete une autorisation
     */
    public void deleteAutorisation(Autorisation _autorisation)
            throws SQLException {

        AutorisationModule _autorisationModule = new AutorisationModule(
                this.cnxUserRight);

        _autorisationModule.deleteAutorisation(_autorisation);

    }

    /*
     * Retourne tous les users qui ne font pas parti de ce groupe
     */
    public List<User> getUserAddRole(int idrole) throws SQLException {

        UserModule _userModule = new UserModule(this.cnxUserRight);

        List<User> _user = _userModule.getUserAddRole(idrole);

        return _user;

    }

    /*
     * Retourne tous les users fesant parti de ce groupe
     */
    public List<User> getUserForARole(int idrole) throws SQLException {

        UserModule _userModule = new UserModule(this.cnxUserRight);

        List<User> _user = _userModule.getUserForARole(idrole);

        return _user;

    }

    /*
     * Retourne la valeur d'id MAX de la table permission afin que la nouvelle
     * valeur ajouter soit toujours d'id MAX + 1
     */
    public int maxIdPermission() throws SQLException {

        PermissionModule _permissionModule = new PermissionModule(
                this.cnxUserRight);

        int _idpermission = _permissionModule.maxIdPermission();

        return _idpermission;

    }

    /*
     * Renvoi toutes les Ressources qui n'ont pas encore de droit attribuer en
     * fonction de l'autorisation.
     */
    public List<Ressource> selectRessourceNotUsedByRole(
            List<Autorisation> _listAutorisation) throws SQLException {

        RessourceModule _ressourceModule = new RessourceModule(
                this.cnxUserRight);

        List<Ressource> _listRessource = _ressourceModule
                .selectRessourceNotUsedByRole(_listAutorisation);

        return _listRessource;

    }

    /*
     * Renvoi un objet autorisation si une autorisation existe deja en base avec
     * ces id ressource et role.
     */
    public Autorisation checkAutorisationExists(Autorisation _autorisation)
            throws SQLException {

        AutorisationModule _autorisationModule = new AutorisationModule(
                this.cnxUserRight);

        Autorisation __autorisation = _autorisationModule
                .checkAutorisationExists(_autorisation);

        return __autorisation;

    }

    /*
     * Verifie lors de l'update si le login n'ai pas deja present en base.
     */
    public User updateUserIfLoginNotExist(User _user) throws SQLException,
            ErrorException {

        UserModule _userModule = new UserModule(this.cnxUserRight);

        User __user = _userModule.updateUserIfLoginNotExist(_user);

        return __user;
    }

    /*
     * Update le password en fonction de l'id User
     */
    public void updateUserPassword(User _user) throws SQLException,
            ErrorException {

        UserModule _userModule = new UserModule(this.cnxUserRight);

        _userModule.updateUserPassword(_user);

    }

    /*
     * Retourne une ressource en fonction de son Menu
     */
    public Ressource getRessource(Ressource object) throws SQLException {

        RessourceModule _ressourceModule = new RessourceModule(
                this.cnxUserRight);

        Ressource _ressource = _ressourceModule.getRessource(object);

        return _ressource;
    }

    /*
     * Retourne une ressource apres ca creation
     */
    public Ressource createRessourceWithRR(Ressource object)
            throws SQLException {

        RessourceModule _ressourceModule = new RessourceModule(
                this.cnxUserRight);

        Ressource _ressource = _ressourceModule.createRessourceWithRR(object);

        return _ressource;
    }

    /*
     * Retourne une ressource_ressource apres ca creation
     */
    public Ressource_Ressource createRessource_Ressource(int idressource)
            throws SQLException {

        Ressource_RessourceModule _rrModule = new Ressource_RessourceModule(
                this.cnxUserRight);

        Ressource_Ressource _rr = _rrModule
                .createRessource_Ressource(idressource);

        return _rr;
    }

    /*
     * Update une ressource pour ajouter une Ressource_Ressource.
     */
    public void updateidRR(Ressource _ressource, Ressource_Ressource _rr)
            throws SQLException {

        RessourceModule _ressourceModule = new RessourceModule(
                this.cnxUserRight);

        _ressourceModule.updateidRR(_ressource, _rr);

    }

    /*
     * Retour une Ressource_Ressource en fonction de son ID
     */
    public Ressource_Ressource getRessource_Ressource(int idRessource_Ressource)
            throws SQLException {

        Ressource_RessourceModule _rrModule = new Ressource_RessourceModule(
                this.cnxUserRight);

        Ressource_Ressource _rr = _rrModule
                .readRessource_Ressource(idRessource_Ressource);

        return _rr;
    }

    /*
     * Retour une Ressource_Ressource en fonction de son IDRessource (presente
     * dans l'object)
     */
    public Ressource_Ressource getRessource_Ressource(
            Ressource_Ressource _object) throws SQLException {

        Ressource_RessourceModule _rrModule = new Ressource_RessourceModule(
                this.cnxUserRight);

        Ressource_Ressource _rr = _rrModule.readRessource_Ressource(_object);

        return _rr;
    }

    /*
     * Renvoi le menu d'une ressource en fonction de l'id ressource_ressource
     */
    public String getMenuWithRR(int idressource_ressource) throws SQLException {

        RessourceModule _ressourceModule = new RessourceModule(
                this.cnxUserRight);

        String menu = _ressourceModule.getMenuWithRR(idressource_ressource);

        return menu;

    }

    /*
     * Renvoi un objet Ressource en fonction de son Menu
     */
    public Ressource getRessourceByMenu(String Menu) throws SQLException {

        RessourceModule _ressourceModule = new RessourceModule(
                this.cnxUserRight);

        Ressource _ressource = _ressourceModule.getRessourceByMenu(Menu);

        return _ressource;

    }

    /*
     * Renvoi un objet Ressource en fonction de son Path
     */
    public Ressource getRessourceByPath(String Path) throws SQLException {

        RessourceModule _ressourceModule = new RessourceModule(
                this.cnxUserRight);

        Ressource _ressource = _ressourceModule.getRessourceByPath(Path);

        return _ressource;

    }

    /*
     * Verifie en fonction de son Path et de l'idressource si le Path et deja
     * present en base pour une autre ressource
     */
    public Ressource getRessourceByPathForUpdate(String Path, int idressource)
            throws SQLException {

        RessourceModule _ressourceModule = new RessourceModule(
                this.cnxUserRight);

        Ressource _ressource = _ressourceModule.getRessourceByPathForUpdate(
                Path, idressource);

        return _ressource;

    }

    /*
     * Verifie en fonction de son Menu et de l'idressource si le Menu et deja
     * present en base pour une autre ressource
     */
    public Ressource getRessourceByMenuForUpdate(String Menu, int idressource)
            throws SQLException {

        RessourceModule _ressourceModule = new RessourceModule(
                this.cnxUserRight);

        Ressource _ressource = _ressourceModule.getRessourceByMenuForUpdate(
                Menu, idressource);

        return _ressource;

    }

    /*
     * Delete une Ressource_Ressource en fonction de son ID ressource.
     */
    public void deleteRessource_RessourceByidRessource(
            Ressource_Ressource _object) throws SQLException {

        Ressource_RessourceModule _rrModule = new Ressource_RessourceModule(
                this.cnxUserRight);

        _rrModule.deleteRessource_RessourceByidRessource(_object);

    }

    /*
     * reset les idRR des ressources dont les idRR ont etaient supprimer
     */
    public void updateToNullIDRR(int idressource_ressource) throws SQLException {

        RessourceModule _ressourceModule = new RessourceModule(
                this.cnxUserRight);

        _ressourceModule.updateToNullIDRR(idressource_ressource);

    }

    /*
     * retourne tous les enregistrements de la table Autorisation
     */
    public List<Autorisation> getAllAutorisation() throws SQLException {

        AutorisationModule _autorisationModule = new AutorisationModule(
                this.cnxUserRight);

        List<Autorisation> __autorisation = _autorisationModule
                .getAllAutorisation();

        return __autorisation;

    }

    /*
     * Retourne les userrole en fonction d'un login user
     */
    public List<UserRole> getUserRoleWithLogin(String login)
            throws SQLException {

        UserroleModule _userroleModule = new UserroleModule(this.cnxUserRight);

        List<UserRole> _userrole = _userroleModule.getUserRoleWithLogin(login);

        return _userrole;

    }

    /*
     * Renvoi toutes les autorisations en fonction du login
     */
    public List<Autorisation> getAutorisationByLogin(String _login)
            throws SQLException {

        AutorisationModule _autorisationModule = new AutorisationModule(
                cnxUserRight);

        List<Autorisation> _autorisation = _autorisationModule
                .getAutorisationByLogin(_login);

        return _autorisation;

    }
}