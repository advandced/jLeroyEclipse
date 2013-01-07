package fr.la.jproductbase.dao;

import java.sql.SQLException;
import java.util.List;

import fr.la.jproductbase.metier.Product;
import fr.la.jproductbase.metier.ProductConf;
import fr.la.jproductbase.metier.Software;

/**
 * Interface dao de logiciel.
 * 
 * @author stc
 * 
 */
public interface SoftwareDao {
	/**
	 * Recherche les logiciels d'un produit de la base de donn&eacute;es.
	 * 
	 * @param product
	 *            Produit.
	 * 
	 * @return Liste des logiciels.
	 * 
	 * @throws SQLException
	 */
	public List<Software> getSoftwares(Product product) throws SQLException;

	/**
	 * Recherche les logiciels d'une configuration produit de la base de
	 * donn&eacute;es.
	 * 
	 * @param productConf
	 *            Configuration produit.
	 * 
	 * @return Liste des logiciels.
	 * 
	 * @throws SQLException
	 */
	public List<Software> getSoftwares(ProductConf productConf)
			throws SQLException;

	/**
	 * Recherche un logiciel de la base de donn&eacute;es.
	 * 
	 * @param name
	 *            Nom du logiciel.
	 * @param version
	 *            Version du logiciel.
	 * 
	 * @return software Logiciel.
	 * 
	 * @throws SQLException
	 */
	public Software getSoftware(String name, String version)
			throws SQLException;

	/**
	 * Ajout d'un logiciel dans la base de donn&eacute;es.
	 * 
	 * @param name
	 *            Nom du logiciel.
	 * @param version
	 *            Version du logiciel.
	 * 
	 * @return software Logiciel.
	 * 
	 * @throws SQLException
	 * @throws SoftwareDaoException
	 */
	public Software addSoftware(String name, String version)
			throws SQLException, SoftwareDaoException;

	/**
	 * Supprimer un logiciel d'un produit de la base de donn&eacute;es.
	 * 
	 * @param product
	 *            Produit.
	 * @param software
	 *            Logiciel.
	 * 
	 * @throws SQLException
	 */
	public void removeProductSoftware(Product product, Software software)
			throws SQLException;

	/**
	 * Ajouter un logiciel &agrave; un produit de la base de donn&eacute;es.
	 * 
	 * @param product
	 *            Produit.
	 * @param software
	 *            Logiciel.
	 * 
	 * @throws SQLException
	 * @throws SoftwareDaoException
	 */
	public void addProductSoftware(Product product, Software software)
			throws SQLException, SoftwareDaoException;

	/**
	 * Rechercher les logiciels d'une configuration produit de la base de
	 * donn&eacute;es.
	 * 
	 * @return Liste des Logiciels
	 * 
	 * @throws SQLException
	 */
	public List<Software> getSoftwares() throws SQLException;

	/**
	 * Ajout d'un logiciel dans la base de donn&eacute;es.
	 * 
	 * @param name
	 *            Nom du logiciel.
	 * @param version
	 *            Version du logiciel.
	 * 
	 * @return software Logiciel
	 * 
	 * @throws SQLException
	 * @throws SoftwareDaoException
	 */
	public Software addSoftware(int state, String name, String version)
			throws SQLException, SoftwareDaoException;

	/**
	 * Mettre à jour un Logiciel
	 * 
	 * @param softwareToUpdate
	 *            Logiciel à mettre à jour
	 * 
	 * @throws SQLException
	 * @throws SoftwareDaoException 
	 */
	void updateSoftware(Software softwareToUpdate) throws SQLException, SoftwareDaoException;

	/**
	 * Supprimer un Logiciel
	 * 
	 * @param softwareToDelete
	 *            Logiciel à supprimer
	 * 
	 * @throws SQLException
	 */
	void deleteSoftware(Software softwareToDelete) throws SQLException;

	/**
	 * Rechercher un Logiciel dans la base de données
	 * 
	 * @param idSoftware
	 * 
	 * @return le Logiciel rechercher
	 * 
	 * @throws SQLException
	 */
	Software getSoftware(int idSoftware) throws SQLException;
	
	/**
	 * Supprimer un logiciel d'une config produit de la base de donn&eacute;es.
	 * 
	 * @param productConf
	 *            Configuration produit.
	 * @param software
	 *            Logiciel.
	 * 
	 * @throws SQLException
	 */
	public void removeProductConfSoftware(ProductConf productConf, Software software)
			throws SQLException;

	/**
	 * Ajouter un logiciel &agrave; une config produit de la base de donn&eacute;es.
	 * 
	 * @param productConf
	 *            Configuration produit..
	 * @param software
	 *            Logiciel.
	 * 
	 * @throws SQLException
	 * @throws SoftwareDaoException
	 */
	public void addProductConfSoftware(ProductConf productConf, Software software)
			throws SQLException, SoftwareDaoException;

	/**
	 * Rechercher les logiciels actifs de la base de
	 * donn&eacute;es.
	 * 
	 * @return Liste des Logiciels
	 * 
	 * @throws SQLException
	 */
	public List<Software> getActiveSoftwares() throws SQLException;

}
