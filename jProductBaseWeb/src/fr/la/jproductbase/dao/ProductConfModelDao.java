package fr.la.jproductbase.dao;

import java.sql.SQLException;
import java.util.List;

import fr.la.jproductbase.metier.ProductConfModel;
import fr.la.jproductbase.metier.ProductType;
import java.util.Map;

/**
 * Classe dao de mod&eacute;le produit.
 *
 * @author stc
 *
 */
public interface ProductConfModelDao {

    /**
     * Recherche un mod&eacute;le de configuration produit de la base de
     * donn&eacute;es.
     *
     * @param idProductConfModel Identifiant du mod&eacute;le de configuration
     * produit.
     *
     * @return Mod&eacute;le produit.
     *
     * @throws SQLException
     */
    ProductConfModel getProductConfModel(int idProductConfModel)
            throws SQLException;

    /**
     * Recherche un mod&eacute;le de configuration produit de la base de
     * donn&eacute;es.
     *
     * @param reference R&eacute;f&eacute;rence du mod&eacute;le de
     * configuration produit.
     *
     * @return Mod&eacute;le produit.
     *
     * @throws SQLException
     */
    ProductConfModel getProductConfModel(String reference) throws SQLException;

    /**
     * Recherche les mod&eacute;les de configurations produit actives de la base
     * de donn&eacute;es.
     *
     * @param productType Type de produit recherch&eacute;e.
     *
     * @return Liste des mod&eacute;les de configurations produit actives.
     *
     * @throws SQLException
     */
    public List<ProductConfModel> getActiveProductConfModels(
            ProductType productType) throws SQLException;

    /**
     * Recherche les mod&eacute;les de configurations produit actives de la base
     * de donn&eacute;es.
     *
     * @return Liste des mod&eacute;les de configurations produit actives.
     *
     * @throws SQLException
     */
    public List<ProductConfModel> getActiveProductConfModels() throws SQLException;

    /**
     * Recherche tous les mod&eacute;les de configuration produit de la base de
     * donn&eacute;es.
     *
     * return Liste de mod&eacute;les de configuration produit.
     *
     * @throws SQLException
     */
    public List<ProductConfModel> getProductConfModels() throws SQLException;

    /**
     * Recherche tous les mod&eacute;les de configuration produit de la base de
     * donn&eacute;es pour un type de produit donn&eacute;.
     *
     * @param type Type de produit
     *
     * @return Liste de mod&eacute;les de configuration produit.
     *
     * @throws SQLException
     */
    public List<ProductConfModel> getProductConfModels(int type) throws SQLException;

    public void addProductConfModels(ProductConfModel _productConfModel) throws SQLException;

    public void delProductConfModels(int id) throws SQLException;

    public void updateProductConfModels(ProductConfModel productConfModel) throws SQLException;

    public List<ProductConfModel> getProductConfModelLazy(Map<String, String> filters, int limit, int maxperpage) throws SQLException;

    public int countProductConfModel(Map<String, String> filters) throws SQLException;
}
