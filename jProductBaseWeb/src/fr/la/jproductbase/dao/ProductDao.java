package fr.la.jproductbase.dao;

import fr.la.configfilereader.ConfigFileReaderException;
import fr.la.jproductbase.metier.Product;
import fr.la.jproductbase.metier.ProductConf;
import fr.la.jproductbase.metier.ProductConfModel;
import fr.la.jproductbase.metier.ProductType;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Classe dao de produit.
 *
 * @author stc
 *
 */
public interface ProductDao {

    /**
     * Compte le nombre de résultat pour une requête
     *
     * @return le nombre de resultat
     * @throws SQLException
     */
    public int countProducts(Map<String, String> filters, int type);

    /**
     * Recherche les produits de la base de donn&eacute;es.
     *
     * @return Liste des produits.
     *
     * @throws SQLException
     */
    public List<Product> getProducts();

    /**
     * Recherche les produits de la base de donn&eacute;es en fonction du type
     * de produit.
     *
     * @param productType Type de produit.
     *
     * @return Liste des produits.
     *
     * @throws SQLException
     */
    public List<Product> getProducts(ProductType productType);

    /**
     * Recherche les produits de la base de donn&eacute;es en fonction du
     * mod&eacute;le de configuration produit.
     *
     * @param productConfModel Mod&eacute;le de configuration produit.
     *
     * @return Liste des produits.
     *
     * @throws SQLException
     */
    public List<Product> getProducts(ProductConfModel productConfModel);

    /**
     * Recherche les produits de la base de donn&eacute;es en fonction de la
     * configuration produit.
     *
     * @param productConf Configuration produit.
     *
     * @return Liste des produits.
     *
     * @throws SQLException
     */
    public List<Product> getProducts(ProductConf productConf);

    /**
     * Recherche les cartes disponibles donc non rattachés à un produit de la
     * base de donn&eacute;es en fonction de la reference de la configuration
     * produit de la carte.
     *
     * @param reference Reference de la config produit d'une carte.
     *
     * @return Liste des produits.
     *
     * @throws SQLException
     */
    public List<Product> getProductsEnables(int idproduct, String reference);

    /**
     * Recherche les produits enregistrables donc présents dans la base FEDD
     * avec un contôle final ok et non-présents dans la abse LAI. Possibilité de
     * filter sur un modèle (T101, T102, etc.).
     *
     * @param modele Modèle de produit.
     *
     * @return Liste des produits.
     *
     * @throws SQLException
     */
    public List<Product> getProductsRecordables(String modele);

    /**
     * Recherche les produits de la base de donn&eacute;es en fonction des
     * critÃ¨res selectionÃ©s sur l'ecran de recherche produits.
     *
     * @param modele Modèle du produit.
     * @param refConf Reference de la configuration du produit.
     * @param majorIndexConf Indice majeur de la configuration du produit.
     * @param minorIndexConf Indice mineur de la configuration du produit.
     * @param dateCode Date code du produit.
     * @param serialNumber NumÃ©ro de sÃ©rie du produit.
     * @param idType Id du Type du produit.
     * @param idFamily Id de la famille du produit.
     * @param macAddress Adresse Mac du produit.
     * @param providerCode Code fournisseur du produit.
     * @param state Etat du produit.
     *
     * @return Liste des produits.
     *
     * @throws SQLException
     * @throws IOException
     * @throws ConfigFileReaderException
     */
    public List<Product> getProductsSearch(int startingAt, int maxPerPage, Map<String, String> filters, int type);

    /**
     * Recherche les produits en fonction du num&eacute;ro de s&eacute;rie et di
     * datecode de la base de donn&eacute;es.
     *
     * @param serialNumber Num&eacute;ro de s&eacute;rie.
     * @param datecode Datecode.
     *
     * @return Liste des produits.
     *
     * @throws SQLException
     */
    public List<Product> getProducts(String serialNumber, String datecode);

    /**
     * Recherche un produit de la base de donn&eacute;es &agrave; partir de son
     * identifiant.
     *
     * @param idProduct Identifiant produit.
     *
     * @return Produit.
     *
     * @throws SQLException
     */
    public Product getProduct(int idProduct);

    /**
     * Recherche un produit de la base de donn&eacute;es.
     *
     * @param productConf Configuration produit.
     * @param serialNumber Num&eacute;ro de s&eacute;rie du produit.
     * @param datecode Datecode du produit.
     *
     * @return Produit.
     *
     * @throws SQLException
     */
    public Product getProduct(ProductConf productConf, String serialNumber, String datecode);

    /**
     * Recherche un produit de la base de donn&eacute;es.
     *
     * @param productConfReference Configuration produit.
     * @param serialNumber Num&eacute;ro de s&eacute;rie du produit.
     * @param datecode Datecode du produit.
     *
     * @return Produit.
     *
     * @throws SQLException
     */
    public Product getProduct(String productConfReference, String serialNumber, String datecode);

    /**
     * Recherche le produit principal auquel une carte appartient.
     *
     * @param carte carte de laquelle on cherche le produit principal.
     *
     * @return Produit.
     *
     * @throws SQLException
     */
    public Product getMainProduct(Product carte);

    /**
     * Ajouter un produit dans la base de donn&eacute;s.
     *
     * @param productConf Configuration produit.
     * @param serialNumber Num&eacute;ro de s&eacute;rie.
     * @param datecode Datecode.
     * @param macAddress Adresse Mac.
     * @param providerCode Code du fournisseur.
     *
     * @return Produit.
     *
     * @throws SQLException
     * @throws ProductDaoException
     */
    public Product addProduct(ProductConf productConf, String serialNumber,  String datecode, String macAddress, String providerCode);

    /**
     * Mise &agrave; jour d'un produit dans la base de donn&eacute;s.
     *
     * @param product Produit.
     *
     * @throws SQLException
     * @throws ProductDaoException
     */
    public void updateProduct(Product product);

    /**
     * Mise &agrave; jour d'un produit dans la base de donn&eacute;s.
     *
     * @param product Produit a mettre a jour.
     * @param productConf Configuration du produit.
     * @param serialNumber Numero de serie du produit.
     * @param dateCode Date code du produit.
     * @param macAddress Adresse Mac du produit.
     * @param providerCode Code fournisseur du produit.
     * @param state Etat du produit.
     *
     * @throws SQLException
     * @throws ProductDaoException
     */
    public void updateProduct(Product product, ProductConf productConf,
            String serialNumber, String dateCode, String macAddress,
            String providerCode, int state);

    /**
     * Mise &agrave; jour d'un produit dans la base de donn&eacute;s.
     *
     * @param product Produit a mettre a jour.
     * @param macAddress Adresse Mac du produit.
     *
     * @throws SQLException
     * @throws ProductDaoException
     */
    public void updateProduct(Product product, String macAddress);

    /**
     * Cr&eacute;tion du lien entre un produit et un composant.
     *
     * @param product Produit.
     * @param productComponent Composant.
     *
     * @throws SQLException
     * @throws ProductDaoException
     */
    public void addProductComponent(Product product, Product productComponent);

    /**
     * D&eacute;mont&eacute; un composant d'un produit de la base de
     * donn&eacute;es.
     *
     * @param product Produit.
     * @param productComponent Composant.
     *
     * @throws SQLException
     */
    public void removeProductComponent(Product product, Product productComponent);

    /**
     * Recherche les composants d'un produit de la base de donnees.
     * @param product Produit auquel appartiennent les composants.
     * @return Composants du produit.
     * @throws SQLException
     */
    public List<Product> getProductComponents(Product product);
 
    /**
     * Recherche les composants d'un produit de la base de donnees de FEDD.
     * @param product Produit FEDD auquel appartiennent les composants.
     * @return Composants du produit FEDD.
     * @throws SQLException
     */
 	public List<Product> getFeddProductComponents(Product selectedObject);

	/**
     * Recherche les produits devant faire l'objet d'une demande de
     * d&eacute;rogation.
     *
     * @return Produits devant faire l'objet d'une demande de d&eacute;rogation.
     *
     * @throws SQLException
     */
    public List<Product> getProductsDispensationNeeded();

    /**
     * V&eacute;rifie si un produit doit faire l'objet d'une demande de
     * d&eacute;rogation.
     *
     * @return Le produits doit faire l'objet d'une demande de
     * d&eacute;rogation.
     *
     * @throws SQLException
     */
    public boolean isNeedDispensation(Product product);

    /**
     * 20-04-12 : RMO : Création de la méthode Méthode qui va enregistrer dans
     * la base LAI un produit de la base FEDD retrouvé dans la base FEDD grâce à
     * l'id placé en paramètre, et qui va également insérer toutes les données
     * des tables annexes de ce produits donc insertiond ans les tables product,
     * product_software, product_product, productComment, failureReport,
     * customerComment, failure, failureReportComment, elementChanged
     *
     * @param idProductFEDD id du produit dans la base FEDD.
     *
     * @throws SQLException
     * @throws ProductDaoException
     */
    public Product setProductFEDDtoLAI(int idProductFEDD, ProductConf config, String serialNumber, String datecode);

    /*
     * Retourne la list des differents produits avec la carte ou produit sur lequels ils sont montés.
     */
    public List<Product> getProductWithMother(int startingAt, int maxPerPage, Map<String, String> filters);
    
    public Product getProductWithProductConfRef(String reference);

}
