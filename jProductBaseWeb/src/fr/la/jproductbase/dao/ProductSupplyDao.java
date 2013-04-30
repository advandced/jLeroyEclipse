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
	ProductSupply getProductSupply(int idProductSupply);

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
	ProductSupply getProductSupply(String name);

	/**
	 * Recherche les alimentations de produit de la base de donn&eacute;es.
	 * 
	 * @return Liste de alimentations de produit.
	 * 
	 * @throws SQLException
	 */
	List<ProductSupply> getProductSupplies();

	/**
	 * Recherche les alimentations de produit actives de la base de
	 * donn&eacute;es.
	 * 
	 * @return Liste de alimentations de produit.
	 * 
	 * @throws SQLException
	 */
	List<ProductSupply> getActiveProductSupplies();

	// 15-12-11 : RMO : Création de 3 nouvelles méthodes //TODO RMO : JavaDoc
	ProductSupply addProductSupply(String name, int state);

	void updateProductSupply(ProductSupply productSupplyToUpdate);

	void deleteProductSupply(ProductSupply productSupplyToDelete);
}
