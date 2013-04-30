package fr.la.jproductbase.dao;

import java.sql.SQLException;
import java.util.List;

import fr.la.jproductbase.metier.ProductFamily;
import fr.la.jproductbase.metier.ProductType;

/**
 * Classe dao de famille de produit.
 * 
 * @author stc
 * 
 */
public interface ProductFamilyDao {
	/**
	 * Recherche une famille de produit de la base de donn&eacute;es.
	 * 
	 * @param idProductFamily
	 *            Identifiant de la famille de produit.
	 * 
	 * @return Famille de produit.
	 * 
	 * @throws SQLException
	 */
	ProductFamily getProductFamily(int idProductFamily);

	/**
	 * Recherche une famille de produit de la base de donn&eacute;es.
	 * 
	 * @param name
	 *            Nom de la famille de produit.
	 * 
	 * @return Famille de produit.
	 * 
	 * @throws SQLException
	 */
	ProductFamily getProductFamily(String name);

	/**
	 * Recherche les familles de produit de la base de donn&eacute;es.
	 * 
	 * @return Liste de familles de produit.
	 * 
	 * @throws SQLException
	 */
	List<ProductFamily> getProductFamilies();
	
	/**
	 * Recherche les familles de produit de la base de donn&eacute;es
	 * pour un type de produits donn&eacute;.
	 * 
	 * @param type Type de produits (Produits, Cartes...)
	 * 
	 * @return Liste de familles de produit.
	 * 
	 * @throws SQLException
	 */
	List<ProductFamily> getProductFamilies(int type);

	/**
	 * Recherche les familles de produit actives de la base de donn&eacute;es.
	 * 
	 * @return Liste de familles de produit.
	 * 
	 * @throws SQLException
	 */
	List<ProductFamily> getActiveProductFamilies();

	// 15-12-11 : RMO : Création de 3 nouvelles méthodes //TODO RMO : JavaDoc
	ProductFamily addProductFamily(String name, int state, ProductType productType);

	void updateProductFamily(ProductFamily productFamilyToUpdate);

	void deleteProductFamily(ProductFamily productFamilyToDelete);
}
