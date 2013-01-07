package fr.la.jproductbase.dao;

import fr.la.jproductbase.metier.Product;
import fr.la.jproductbase.metier.ProductDocument;
import fr.la.jproductbase.metier.ProductDocumentType;
import java.sql.SQLException;
import java.util.List;

/**
 * Interface dao de tyope document produit.
 *
 * @author rmo
 *
 */
public interface ProductDocumentDao {

    /**
     * Ajout d'un document dans la base de donn&eacute;es.
     *
     * @param state Etat du document.
     * @param name Nom du document.
     *
     * @return productDocument Document.
     *
     * @throws SQLException
     * @throws ProductDocumentDaoException
     */
    public ProductDocument addProductDocument(int state, String title, String link, ProductDocumentType productDocumentType, Product product)
            throws SQLException, ProductDocumentDaoException;

    /**
     * Mise à jour d'un document dans la base de donn&eacute;es.
     *
     * @param productDocument Document à mettre à jour.
     *
     * @return productDocument Document.
     *
     * @throws SQLException
     * @throws ProductDocumentDaoException
     */
    public ProductDocument updateProductDocument(ProductDocument productDocument)
            throws SQLException, ProductDocumentDaoException;

    /**
     * Recherche un document de la base de donn&eacute;es.
     *
     * @param idProductDocument Id du document.
     *
     * @return ProductDocument Document.
     *
     * @throws SQLException
     */
    public ProductDocument getProductDocument(int idProductDocument)
            throws SQLException;

    /**
     * Recherche un document de la base de donn&eacute;es.
     *
     * @param name Nom du document.
     *
     * @return ProductDocument Document.
     *
     * @throws SQLException
     */
    public ProductDocument getProductDocument(String title, String link)
            throws SQLException;

    /**
     * Recherche les documents d'un produit de la base de donn&eacute;es.
     *
     * @param product Produit.
     *
     * @return Liste des documents.
     *
     * @throws SQLException
     */
    public List<ProductDocument> getProductDocuments(Product product) throws SQLException;

    /**
     * Recherche les documents en fonction d'un type de document de la base de
     * donn&eacute;es.
     *
     * @param productDocumentType Type de documentt.
     *
     * @return Liste des documents.
     *
     * @throws SQLException
     */
    public List<ProductDocument> getProductDocuments(ProductDocumentType productDocumentType) throws SQLException;

    /**
     * Recherche les documents d'un produit de la base de donn&eacute;es pour un
     * type de document donn&eacute;.
     *
     * @param product Produit.
     * @param productDocumentType Type de produit.
     *
     * @return Liste des documents.
     *
     * @throws SQLException
     */
    public List<ProductDocument> getProductDocuments(Product product, ProductDocumentType productDocumentType) throws SQLException;

    /**
     * Recherche les documents d'un produit de la base de donn&eacute;es de FEDD
     * pour un type de document donn&eacute;.
     *
     * @param product Produit.
     * @param productDocumentType Type de produit.
     *
     * @return Liste des documents.
     *
     * @throws SQLException
     */
    public List<ProductDocument> getFEDDProductDocuments(Product product, ProductDocumentType productDocumentType) throws SQLException;

    /**
     * Recherche les documents de la base de donn&eacute;es.
     *
     * @return Liste des documents.
     *
     * @throws SQLException
     */
    public List<ProductDocument> getProductDocuments()   throws SQLException;

    /**
     * Rechercher les documents actifs de la base de donn&eacute;es.
     *
     * @return Liste des documents
     *
     * @throws SQLException
     */
    public List<ProductDocument> getActiveProductDocuments() throws SQLException;
}
