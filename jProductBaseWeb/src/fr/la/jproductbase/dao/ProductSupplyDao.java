package fr.la.jproductbase.dao;

import java.sql.SQLException;
import java.util.List;

import fr.la.jproductbase.metier.ProductSupply;

/**
 * Classe dao d'alimentation produit.
 * 
 * @author stc
 * 
 */
public interface ProductSupplyDao {
	/**
	 * Recherche une alimentation produit de la base de donn&eacute;es.
	 * 
	 * @param idProductSupply
	 *            Identifiant de l'alimentation produit.
	 * 
	 * @return Alimentation produit.
	 * 
	 * @throws SQLException
	 */
	ProductSupply getProductSupply(int idProductSupply) throws SQLException;

	/**
	 * Recherche une alimentation produit de la base de donn&eacute;es.
	 * 
	 * @param name
	 *            Nom de l'alimentation produit.
	 * 
	 * @return Alimentation produit.
	 * 
	 * @throws SQLException
	 */
	ProductSupply getProductSupply(String name) throws SQLException;

	/**
	 * Recherche les alimentations de produit de la base de donn&eacute;es.
	 * 
	 * @return Liste de alimentations de produit.
	 * 
	 * @throws SQLException
	 */
	List<ProductSupply> getProductSupplies() throws SQLException;

	/**
	 * Recherche les alimentations de produit actives de la base de
	 * donn&eacute;es.
	 * 
	 * @return Liste de alimentations de produit.
	 * 
	 * @throws SQLException
	 */
	List<ProductSupply> getActiveProductSupplies() throws SQLException;

	// 15-12-11 : RMO : Création de 3 nouvelles méthodes //TODO RMO : JavaDoc
	ProductSupply addProductSupply(String name, int state) throws SQLException,
			ProductDaoException;

	void updateProductSupply(ProductSupply productSupplyToUpdate)
			throws SQLException, ProductDaoException;

	void deleteProductSupply(ProductSupply productSupplyToDelete)
			throws SQLException;
}
