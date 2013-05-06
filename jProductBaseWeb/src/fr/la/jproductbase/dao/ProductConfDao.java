package fr.la.jproductbase.dao;

import fr.la.jproductbase.metier.FollowingFormModel;
import fr.la.jproductbase.metier.ProductConf;
import fr.la.jproductbase.metier.ProductConfModel;
import fr.la.jproductbase.metier.ProductFamily;
import fr.la.jproductbase.metier.ProductSupply;
import fr.la.jproductbase.metier.ProductType;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Classe dao du mod&eacute;le de produit.
 *
 * @author stc
 *
 */
public interface ProductConfDao {

    /**
     * Recherche une configuration produit de la base de donn&eacute;es.
     *
     * @param idProductConf Identifiant de la configuration produit.
     *
     * @return Configuration produit.
     *
     * @throws SQLException
     */
    public ProductConf getProductConf(int idProductConf);

    /**
     * Recherche une configuration produit de la base de donn&eacute;es.
     *
     * @param reference R&eacute;f&eacute;rence de la configuration produit.
     * @param majorIndex Indice majeur de la configuration produit.
     * @param minorIndex Indice mineur de la configuration produit.
     *
     * @return Configuration produit.
     *
     * @throws SQLException
     */
    public ProductConf getProductConf(String reference, String majorIndex, String minorIndex);

    /**
     * Recherche la derni&eagrave;re configuration produit active de la base de
     * donn&eacute;es.
     *
     * @param reference R&eacute;f&eacute;rence de la configuration;
     * @param majorIndex Indice majeur de la configuration;
     * @param minorIndex Indice mineur de la configuration;
     *
     * @return Derni&eagrave;re configuration produit active.
     *
     * @throws SQLException
     */
    public ProductConf getLastActiveProductConf(String reference, String majorIndex, String minorIndex);

    /**
     * Recherche les configurations produit de la base de donn&eacute;es.
     *
     * @return Liste des configurations produit.
     *
     * @throws SQLException
     */
    public List<ProductConf> getProductConfs();

    /**
     * Recherche les configurations produit de la base de donn&eacute;es par
     * rapport à un type de conf donné.
     *
     * @param type Type de configuration
     *
     * @return Liste des configurations produit.
     *
     * @throws SQLException
     */
    public List<ProductConf> getProductConfs(int type);

    /**
     * Recherche les configurations produit en fonction d'une
     * r&eacute;f&eacute;rence de la base de donn&eacute;es.
     *
     * @param reference R&eacute;f&eacute;rence recherch&eacute;e.
     *
     * @return Liste des configurations produit.
     *
     * @throws SQLException
     */
    public List<ProductConf> getProductConfs(String reference);

    /**
     * Recherche les configurations produit actives en fonction d'un type de
     * produit de la base de donn&eacute;es.
     *
     * @param productType Type de produit recherch&eacute;e.
     *
     * @return Liste des configurations produit actives.
     *
     * @throws SQLException
     */
    public List<ProductConf> getActiveProductConfs(ProductType productType);

    /**
     * Recherche les configurations produit actives de la base de
     * donn&eacute;es.
     *
     * @return Liste des configurations produit actives.
     *
     * @throws SQLException
     */
    public List<ProductConf> getActiveProductConfs();

    /**
     * Ajouter une configuration produit dans la base de donn&eacute;s.
     *
     * @param reference R&eacute;f&eacute;rence de la configuration produit.
     * @param majorIndex Indice majeur de la configuration produit.
     * @param minorIndex Indice mineur de la configuration produit.
     * @param designation D&eacute;signation de la configuration produit.
     * @param state Etat de l'enregistrement.
     * @param productFamily Famille de la configuration produit.
     * @param productSupply Alimentation de la configuration produit.
     *
     * @return Configuration produit.
     *
     * @throws SQLException
     * @throws ProductConfDaoException
     */
    public ProductConf addProductConf(String reference, String majorIndex,
            String minorIndex, String designation, int state,
            ProductFamily productFamily, ProductSupply productSupply);

    /**
     * Met &agrave; jour une configuration produit dans la base de
     * donn&eacute;s.
     *
     * @param productConf Identifiant de la configuration produit.
     * @param reference R&eacute;f&eacute;rence de la configuration produit.
     * @param majorIndex Indice majeur de la configuration produit.
     * @param minorIndex Indice mineur de la configuration produit.
     * @param designation D&eacute;signation de la configuration produit.
     * @param state Etat de l'enregistrement.
     * @param productFamily Famille du mod&eacute;le de produit.
     * @param productSupply Alimentation de la configuration produit.
     *
     * @throws SQLException
     * @throws ProductConfDaoException
     */
    public void updateProductConf(ProductConf productConf, String reference,
            String majorIndex, String minorIndex, String designation,
            int state, ProductFamily productFamily, ProductSupply productSupply);

    /**
     * Recherche les composants d'une de configuration produit de la base de
     * donn&eacute;es.
     *
     * @param productConf Configuration produit.
     *
     * @return Liste des composants d'une configuration produit.
     *
     * @throws SQLException
     */
    public List<ProductConf> getProductConfComponents(ProductConf productConf);

    /**
     * Recherche la version active d'une configuration produit de la base de
     * donn&eacute;es &agrave; partir de sa r&eacute;f&eacute;rence.
     *
     * @param reference R&eacute;f&eacute;rence du composant.
     *
     * @return Configurations produit actives.
     *
     * @throws SQLException
     */
    public List<ProductConf> getActiveProductConfs(String reference);

    /**
     * Supprimer une carte d'une config produit de la base de donn&eacute;es.
     *
     * @param productConf Configuration produit.
     * @param component Carte.
     *
     * @throws SQLException
     */
    public void removeProductConfComponent(ProductConf productConf, ProductConf component);

    /**
     * Ajouter une carte &agrave; une config produit de la base de
     * donn&eacute;es.
     *
     * @param productConf Configuration produit.
     * @param component Carte.
     *
     * @throws SQLException
     * @throws ProductConfDaoException
     */
    public void addProductConfComponent(ProductConf productConf, ProductConf component);

    /**
     * Ajouter une configuration produit dans la base de donn&eacute;s.
     *
     * @param reference R&eacute;f&eacute;rence de la configuration produit.
     * @param majorIndex Indice majeur de la configuration produit.
     * @param minorIndex Indice mineur de la configuration produit.
     * @param productConfModel Modele de la configuration produit.
     * @param identifiable configuration identifiable ou pas.
     * @param state Etat de l'enregistrement.
     * @param productFamily Famille de la configuration produit.
     * @param productSupply Alimentation de la configuration produit.
     * @param followingForm Modele de fiche suiveuse de la configuration
     * produit.
     *
     * @return Configuration produit.
     *
     * @throws SQLException
     * @throws ProductConfDaoException
     */
    public ProductConf addProductConf(String reference, String majorIndex,
            String minorIndex, ProductConfModel productConfModel, boolean identifiable,
            int state, ProductFamily productFamily,
            ProductSupply productSupply, FollowingFormModel followingForm);

    /**
     * Met &agrave; jour une configuration produit dans la base de
     * donn&eacute;s.
     *
     * @param productConf Identifiant de la configuration produit.
     * @param reference R&eacute;f&eacute;rence de la configuration produit.
     * @param majorIndex Indice majeur de la configuration produit.
     * @param minorIndex Indice mineur de la configuration produit.
     * @param productConfModel Modele de la configuration produit.
     * @param identifiable configuration identifiable ou pas.
     * @param state Etat de l'enregistrement.
     * @param productFamily Famille du mod&eacute;le de produit.
     * @param productSupply Alimentation de la configuration produit.
     * @param followingFormModel Modele de fiche suiveuse de la configuration
     * produit.
     *
     * @throws SQLException
     * @throws ProductConfDaoException
     */
    public void updateProductConf(ProductConf productConf, String reference,
            String majorIndex, String minorIndex, ProductConfModel productConfModel,
            Boolean identifiable, int state, ProductFamily productFamily,
            ProductSupply productSupply, FollowingFormModel followingFormModel);

    public List<ProductConf> getProductConfLazy(Map<String, String> filters, int limit, int maxperpage);

    public int countProductConf(Map<String, String> filters);

	public ProductConf getFEDDProductConf(int idProductConf);
}
