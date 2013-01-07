package fr.la.jproductbase.dao;

import java.sql.SQLException;
import java.util.List;

import fr.la.jproductbase.metier.ProductDocumentType;

/**
 * Interface dao de tyope document produit.
 * 
 * @author rmo
 * 
 */
public interface ProductDocumentTypeDao {
	
	/**
	 * Ajout d'un type de document dans la base de donn&eacute;es.
	 * 
	 * @param state
	 *            Etat du type de document.
	 * @param name
	 *            Nom du type de document.
	 * 
	 * @return productDocumentType Type de document.
	 * 
	 * @throws SQLException
	 * @throws ProductDocumentTypeDaoException
	 */
	public ProductDocumentType addProductDocumentType(int state, String name)
			throws SQLException, ProductDocumentTypeDaoException;
	
	/**
	 * Mise à jour d'un type de document dans la base de donn&eacute;es.
	 * 
	 * @param productDocumentType
	 *            Type de document à mettre à jour.
	 * 
	 * @return productDocumentType Type de document.
	 * 
	 * @throws SQLException
	 * @throws ProductDocumentTypeDaoException
	 */
	public ProductDocumentType updateProductDocumentType(ProductDocumentType productDocumentType)
			throws SQLException, ProductDocumentTypeDaoException;
	
	/**
	 * Recherche un type de document de la base de donn&eacute;es.
	 * 
	 * @param idProductDocumentType
	 *            Id du type de document.
	 * 
	 * @return ProductDocumentType Type de document.
	 * 
	 * @throws SQLException
	 */
	public ProductDocumentType getProductDocumentType(int idProductDocumentType)
			throws SQLException;
	
	/**
	 * Recherche un type de document de la base de donn&eacute;es.
	 * 
	 * @param name
	 *            Nom du type de document.
	 * 
	 * @return ProductDocumentType Type de document.
	 * 
	 * @throws SQLException
	 */
	public ProductDocumentType getProductDocumentType(String name)
			throws SQLException;
		
	/**
	 * Recherche les types de document d'un produit de la base de donn&eacute;es.
	 * 
	 * @param product
	 *            Produit.
	 * 
	 * @return Liste des types de document.
	 * 
	 * @throws SQLException
	 */
	/*public List<ProductDocumentType> getProductDocumentTypes(Product product) throws SQLException;*/

	/**
	 * Recherche les types de document de la base de donn&eacute;es.
	 * 
	 * @return Liste des types de document.
	 * 
	 * @throws SQLException
	 */
	public List<ProductDocumentType> getProductDocumentTypes()
			throws SQLException;



	/**
	 * Rechercher les types de document actifs de la base de
	 * donn&eacute;es.
	 * 
	 * @return Liste des types de document
	 * 
	 * @throws SQLException
	 */
	public List<ProductDocumentType> getActiveProductDocumentTypes() throws SQLException;

}
