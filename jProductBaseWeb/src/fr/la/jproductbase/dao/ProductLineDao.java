package fr.la.jproductbase.dao;

import java.sql.SQLException;

import fr.la.jproductbase.metier.ProductLine;

/**
 * Classe dao de gamme produit.
 * 
 * @author stc
 * 
 */
public interface ProductLineDao {
	/**
	 * Recherche une gamme produit de la base de donn&eacute;es.
	 * 
	 * @param idProductLine
	 *            Identifiant de la gamme produit.
	 * 
	 * @return Gamme produit.
	 * 
	 * @throws SQLException
	 */
	ProductLine getProductLine(int idProductLine) throws SQLException;
}
