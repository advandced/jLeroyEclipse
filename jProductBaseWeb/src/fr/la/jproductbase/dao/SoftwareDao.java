package fr.la.jproductbase.dao;

import java.sql.SQLException;
import java.util.List;

import fr.la.jproductbase.metier.Product;
import fr.la.jproductbase.metier.ProductConf;
import fr.la.jproductbase.metier.Software;

/**
 * Interface dao de logiciel.
 * @author stc
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
	public List<Software> getSoftwares(Product product);

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
	public List<Software> getSoftwares(ProductConf productConf);

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
	public Software getSoftware(String name, String version);

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
	public Software addSoftware(String name, String version);

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
	public void removeProductSoftware(Product product, Software software);

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
	public void addProductSoftware(Product product, Software software);

	/**
	 * Rechercher les logiciels d'une configuration produit de la base de
	 * donn&eacute;es.
	 * 
	 * @return Liste des Logiciels
	 * 
	 * @throws SQLException
	 */
	public List<Software> getSoftwares();

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
	public Software addSoftware(int state, String name, String version);

	/**
	 * Mettre à jour un Logiciel
	 * 
	 * @param softwareToUpdate
	 *            Logiciel à mettre à jour
	 * 
	 * @throws SQLException
	 * @throws SoftwareDaoException 
	 */
	void updateSoftware(Software softwareToUpdate);

	/**
	 * Supprimer un Logiciel
	 * 
	 * @param softwareToDelete
	 *            Logiciel à supprimer
	 * 
	 * @throws SQLException
	 */
	void deleteSoftware(Software softwareToDelete);

	/**
	 * Rechercher un Logiciel dans la base de données
	 * 
	 * @param idSoftware
	 * 
	 * @return le Logiciel rechercher
	 * 
	 * @throws SQLException
	 */
	Software getSoftware(int idSoftware);
	
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
	public void removeProductConfSoftware(ProductConf productConf, Software software);

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
	public void addProductConfSoftware(ProductConf productConf, Software software);

	/**
	 * Rechercher les logiciels actifs de la base de
	 * donn&eacute;es.
	 * 
	 * @return Liste des Logiciels
	 * 
	 * @throws SQLException
	 */
	public List<Software> getActiveSoftwares();

}
