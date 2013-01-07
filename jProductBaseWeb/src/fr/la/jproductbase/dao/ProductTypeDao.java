package fr.la.jproductbase.dao;

import java.sql.SQLException;
import java.util.List;

import fr.la.jproductbase.metier.ProductType;

/**
 * Classe dao de mod&eacute;le de produit.
 * 
 * @author stc
 * 
 */
public interface ProductTypeDao {
	/**
	 * Recherche un mod&eacute;le de produit de la base de donn&eacute;es.
	 * 
	 * @param idProductType
	 *            Identifiant du mod&eacute;le de produit.
	 * 
	 * @return Mod&eacute;le de produit.
	 * 
	 * @throws SQLException
	 */
	ProductType getProductType(int idProductType) throws SQLException;

	/**
	 * Recherche un mod&eacute;le de produit de la base de donn&eacute;es.
	 * 
	 * @param name
	 *            Nom du mod&eacute;le de produit.
	 * 
	 * @return Mod&eacute;le de produit.
	 * 
	 * @throws SQLException
	 */
	ProductType getProductType(String name) throws SQLException;

	/**
	 * Recherche les mod&eacute;les de produit de la base de donn&eacute;es.
	 * 
	 * @return Liste des mod&eacute;les de produit.
	 * 
	 * @throws SQLException
	 */
	List<ProductType> getProductTypes() throws SQLException;

	/**
	 * Recherche les mod&eacute;les de produit actifs de la base de
	 * donn&eacute;es.
	 * 
	 * @return Liste des mod&eacute;les de produit.
	 * 
	 * @throws SQLException
	 */
	List<ProductType> getActiveProductTypes() throws SQLException;

	// 15-12-11 : RMO : Création de 3 nouvelles méthodes //TODO RMO : JavaDoc
	ProductType addProductType(String name, int state) throws SQLException,
			ProductDaoException;

	void updateProductType(ProductType productTypeToUpdate)
			throws SQLException, ProductDaoException;

	void deleteProductType(ProductType productTypeToDelete) throws SQLException;

}
