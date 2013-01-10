package fr.la.jproductbase.service;

import fr.la.configfilereader.ConfigFileReaderException;
import fr.la.jproductbase.dao.ConnectionProduct;
import fr.la.jproductbase.dao.ProductDao;
import fr.la.jproductbase.dao.ProductDaoException;
import fr.la.jproductbase.dao.ProductDaoImpl;
import fr.la.jproductbase.dao.ProductFamilyDao;
import fr.la.jproductbase.dao.ProductFamilyDaoImpl;
import fr.la.jproductbase.dao.ProductSupplyDao;
import fr.la.jproductbase.dao.ProductSupplyDaoImpl;
import fr.la.jproductbase.dao.ProductTypeDao;
import fr.la.jproductbase.dao.ProductTypeDaoImpl;
import fr.la.jproductbase.dao.SoftwareDao;
import fr.la.jproductbase.dao.SoftwareDaoException;
import fr.la.jproductbase.dao.SoftwareDaoImpl;
import fr.la.jproductbase.metier.JProductBaseException;
import fr.la.jproductbase.metier.Product;
import fr.la.jproductbase.metier.ProductConf;
import fr.la.jproductbase.metier.ProductConfModel;
import fr.la.jproductbase.metier.ProductFamily;
import fr.la.jproductbase.metier.ProductSupply;
import fr.la.jproductbase.metier.ProductType;
import fr.la.jproductbase.metier.Software;
import fr.la.jproductbase.metier.ToolsProduct;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.naming.NamingException;

/**
 * @author Gary-pro
 *
 */
public class ProductModule {

    private ConnectionProduct cnxProduct;

    protected ProductModule(ConnectionProduct cnxProduct) {
        this.cnxProduct = cnxProduct;
    }

    /**
     * Recherche les produits de la base de donn&eacute;es.
     *
     * @return Liste des produits.
     *
     * @throws SQLException
     */
    protected List<Product> getProducts() throws SQLException {
        ProductDao _productDao = new ProductDaoImpl(this.cnxProduct);

        List<Product> _products = _productDao.getProducts();

        return _products;
    }

    protected List<Product> getProducts(ProductType productType)
            throws SQLException {
        ProductDao _productDao = new ProductDaoImpl(this.cnxProduct);

        List<Product> _products = _productDao.getProducts(productType);

        return _products;
    }

    protected List<Product> getProducts(ProductConfModel productConfModel)
            throws SQLException {
        ProductDao _productDao = new ProductDaoImpl(this.cnxProduct);

        List<Product> _products = _productDao.getProducts(productConfModel);

        return _products;
    }

    protected List<Product> getProducts(ProductConf productConf)
            throws SQLException {
        ProductDao _productDao = new ProductDaoImpl(this.cnxProduct);

        List<Product> _products = _productDao.getProducts(productConf);

        return _products;
    }

    protected List<Product> getProductsEnables(int idproduct, String reference)
            throws SQLException {
        ProductDao _productDao = new ProductDaoImpl(this.cnxProduct);
        List<Product> _products = _productDao.getProductsEnables(idproduct,
                reference);
        return _products;
    }

    protected List<Product> getProductsRecordables(String modele)
            throws SQLException {
        ProductDao _productDao = new ProductDaoImpl(this.cnxProduct);
        List<Product> _products = _productDao.getProductsRecordables(modele);
        return _products;
    }
    
        public List<Product> getProductsSearch(int startingAt, int maxPerPage, Map<String, String> filters, int type) throws SQLException{
        ProductDao _productDao = new ProductDaoImpl(this.cnxProduct);
        List<Product> _products = _productDao.getProductsSearch(startingAt, maxPerPage, filters, type);
        return _products;
    }

    protected List<Product> getProducts(String serialNumber, String datecode)
            throws SQLException {
        ProductDao _productDao = new ProductDaoImpl(this.cnxProduct);

        String _serialNumber = ToolsProduct.deleteAhead("0", serialNumber);

        List<Product> _products = _productDao.getProducts(_serialNumber,
                datecode);

        return _products;
    }

    protected Product getProduct(int idProduct) throws SQLException {
        ProductDao _productDao = new ProductDaoImpl(this.cnxProduct);

        Product _product = _productDao.getProduct(idProduct);

        return _product;
    }

    protected Product getProduct(ProductConf productConf, String serialNumber,
            String datecode) throws SQLException {
        ProductDao _productDao = new ProductDaoImpl(this.cnxProduct);

        String _serialNumber = ToolsProduct.deleteAhead("0", serialNumber);

        Product _product = _productDao.getProduct(productConf, _serialNumber,
                datecode);

        return _product;
    }

    protected Product getProduct(String productConfReference,
            String serialNumber, String datecode) throws SQLException {
        ProductDao _productDao = new ProductDaoImpl(this.cnxProduct);

        String _serialNumber = ToolsProduct.deleteAhead("0", serialNumber);

        Product _product = _productDao.getProduct(productConfReference,
                _serialNumber, datecode);

        return _product;
    }

    // 09-05-12 : RMO : Création de la méthode
    protected Product getMainProduct(Product carte) throws SQLException {
        ProductDao _productDao = new ProductDaoImpl(this.cnxProduct);

        Product _product = _productDao.getMainProduct(carte);

        return _product;
    }

    /**
     * méthode permettant de retourner le nombre de résultat d'une requete
     *
     * @param idModele
     * @param refConf
     * @param majorIndexConf
     * @param minorIndexConf
     * @param dateCode
     * @param serialNumber
     * @param idType
     * @param idFamily
     * @param macAddress
     * @param providerCode
     * @param state
     * @return le nombre de résultat
     * @throws SQLException
     */
    protected int countQueryResult(Map<String, String> filters, int type) throws SQLException {
        
        ProductDao _productDAO = new ProductDaoImpl(this.cnxProduct);
        
        int count = _productDAO.countProducts(filters, type);
        return count;
    }

    /*
     * Sauvegarde un produit.
     * 
     * @param product Produit &grave; enregistr&eacute;.
     * 
     * @param productConf Configuration du produit.
     * 
     * @param serialNumber Num&eacute;ro de s&eacute;rie du produit.
     * 
     * @param datecode Datecode du produit.
     * 
     * @param productComponents Composants du produit.
     * 
     * @param productSoftwares Logiciels du produit.
     * 
     * @return Produit sauvegard&eacute;.
     * 
     * @throws Exception
     */
    private Product setProduct(Product product, ProductConf productConf,
            String serialNumber, String datecode,
            List<Product> productComponents, List<Software> productSoftwares)
            throws SQLException, ProductDaoException, SoftwareDaoException,
            NamingException {
        Product _product = product;
        if (null == product) {
            // New product
            _product = this.addProduct(productConf, serialNumber, datecode);
        } else {
            // Existing product
            product.setProductConf(productConf);
            product.setSerialNumber(serialNumber);
            product.setDatecode(datecode);
            product.setProductComponents(productComponents);
            product.setProductSoftwares(productSoftwares);

            try {
                this.updateProduct(product);
                this.cnxProduct.getCnx().commit();
            } catch (NamingException e) {
                e.printStackTrace();
                this.cnxProduct.getCnx().rollback();
            }
        }

        // Update link between product and components
        this.updateProductComponents(_product, productComponents);

        // Update link between product and softwares
        this.updateProductSoftwares(_product, productSoftwares);

        return _product;
    }

    protected Product setProduct(Product product) throws SQLException,
            ProductDaoException, ConfigFileReaderException, IOException,
            SoftwareDaoException, JProductBaseException, NamingException {
        Product _product = null;
        try {
            // Start transaction
            this.cnxProduct.getCnx().setAutoCommit(false);

            // Retreive product
            if (product.getIdProduct() != 0) {
                _product = this.getProduct(product.getIdProduct());
            } else {
                _product = this.getProduct(product.getProductConf(),
                        product.getSerialNumber(), product.getDatecode());
            }

            // Update product components
            List<Product> _productComponents = this
                    .updateProductComponents(product.getProductComponents());

            // Update product softwares
            List<Software> _productSoftwares = this
                    .updateProductSoftwares(product.getProductSoftwares());

            // Save product
            _product = this.setProduct(_product, product.getProductConf(),
                    product.getSerialNumber(), product.getDatecode(),
                    _productComponents, _productSoftwares);

            // Commit
            this.cnxProduct.getCnx().commit();
        } catch (Exception e) {
            this.cnxProduct.getCnx().rollback();
            e.printStackTrace();
            throw new JProductBaseException(e.getMessage());
        }

        return _product;
    }

    protected Product setProduct(int idProduct, String productConfReference,
            String productConfMajorIndex, String productConfMinorIndex,
            String serialNumber, String datecode, String[][] productComponents,
            String[][] productSoftwares) throws SQLException,
            JProductBaseException, ConfigFileReaderException, IOException,
            NamingException {
        Product _product = null;

        // Retrieve productConf
        ProductConfModule _productConfModule = new ProductConfModule(
                this.cnxProduct);
        ProductConf _productConf = _productConfModule.getProductConf(
                productConfReference, productConfMajorIndex,
                productConfMinorIndex);
        if (null != _productConf) {
            String _serialNumber = ToolsProduct.deleteAhead("0", serialNumber);

            // Retreive product
            _product = this.getProduct(idProduct);
            if (null != _product) {
                // Update product object
                _product.setProductConf(_productConf);

                _product.setDatecode(datecode);
                _product.setSerialNumber(_serialNumber);
            } else {
                _product = new Product(_productConf, datecode, _serialNumber,
                        "");
            }
            this.cnxProduct.getCnx().setAutoCommit(false);
            // Update product components
            try {
                _product.updateProductComponents(productComponents);
                this.cnxProduct.getCnx().commit();
            } catch (ProductDaoException e) {
                this.cnxProduct.getCnx().rollback();
                e.printStackTrace();
            }

            // Update product softwares
            try {
                _product.updateProductSoftwares(productSoftwares);
                this.cnxProduct.getCnx().commit();
            } catch (SoftwareDaoException e) {
                this.cnxProduct.getCnx().rollback();
                e.printStackTrace();
            }

            // Save product
            try {
                _product = this.setProduct(_product);
                this.cnxProduct.getCnx().commit();
            } catch (ProductDaoException e) {
                this.cnxProduct.getCnx().rollback();
                e.printStackTrace();
            } catch (SoftwareDaoException e) {
                this.cnxProduct.getCnx().rollback();
                e.printStackTrace();
            }
        } else {
            throw new JProductBaseException("Configuration produit inconnue.");
        }

        return _product;
    }

    protected Product setProduct(Product product, ProductConf productConf,
            String serialNumber, String dateCode, String macAddress,
            String providerCode, int state, List<Product> productComponents,
            List<Software> productSoftwares) throws Exception {
        Product _product = product;
        try {
            this.cnxProduct.getCnx().setAutoCommit(false);
            if (null == _product) {
                // New product

                // Add
                _product = this.addProduct(productConf, serialNumber, dateCode,
                        macAddress, providerCode);
            } else {
                // Existing product

                // Update
                this.updateProduct(_product, productConf, serialNumber,
                        dateCode, macAddress, providerCode, state);
            }

            // Update link between product and components
            if ((productComponents != null)) {
                /* && (productConfComponents.isEmpty() == false)) { */
                this.updateProductComponents(_product, productComponents);
            } else {
                // rien
            }

            // Update link between productConf and softwares in database
            if ((productSoftwares != null)) {
                /* && (productConfSoftwares.isEmpty() == false)) { */
                this.updateProductSoftwares(_product, productSoftwares);
            } else {
                // rien
            }

            // Commit
            this.cnxProduct.getCnx().commit();
        } catch (SQLException e) {
            this.cnxProduct.getCnx().rollback();
            throw new Exception(e.getMessage());
        }

        return _product;
    }

    /*
     * Création de la liste des composants du produit à partir d'une liste de
     * composants.
     * 
     * @param productComponents Liste des composants.
     */
    private List<Product> updateProductComponents(
            List<Product> productComponents) throws SQLException,
            ProductDaoException, NamingException {
        List<Product> _productComponents = new ArrayList<Product>();

        if (null != productComponents) {
            ProductConf _productConf = null;
            String _datecode;
            String _serialNumber;
            String _provider;
            for (Product _productComponent : productComponents) {
                _productConf = _productComponent.getProductConf();
                _serialNumber = _productComponent.getSerialNumber();
                _datecode = _productComponent.getDatecode();
                _provider = _productComponent.getProviderCode();

                // Retreive component
                _productComponent = this.getProduct(_productConf,
                        _serialNumber, _datecode);
                if (null == _productComponent) {
                    // New component
                    _productComponent = this.addProduct(_productConf,
                            _serialNumber, _datecode, _provider);
                } else {
                    // Exiting component
                    _productComponent.setDatecode(_datecode);
                    _productComponent.setSerialNumber(_serialNumber);
                    _productComponent.setProviderCode(_provider);
                    try {
                        this.cnxProduct.getCnx().setAutoCommit(false);
                        this.updateProduct(_productComponent);
                        this.cnxProduct.getCnx().commit();
                    } catch (NamingException e) {
                        this.cnxProduct.getCnx().rollback();
                        e.printStackTrace();
                    }
                }

                _productComponents.add(_productComponent);
            }
        }

        return _productComponents;
    }

    /*
     * Création de la liste des logiciels du produit à partir d'une liste de
     * logiciels.
     * 
     * @param productSoftwares Liste des logiciels.
     */
    private List<Software> updateProductSoftwares(
            List<Software> productSoftwares) throws SQLException,
            ConfigFileReaderException, IOException, NamingException {
        List<Software> _productSoftwares = new ArrayList<Software>();

        if (null != productSoftwares) {
            String _name;
            String _version;
            for (Software _productSoftware : productSoftwares) {
                _name = _productSoftware.getName();
                _version = _productSoftware.getVersion();

                // Retreive software
                SoftwareModule _softwareModule = new SoftwareModule(
                        this.cnxProduct);
                _productSoftware = _softwareModule.getSoftware(_name, _version);
                if (null == _productSoftware) {
                    // New software
                    _productSoftware = _softwareModule.addSoftware(_name,
                            _version);
                } else {
                    // Exiting component
                }

                _productSoftwares.add(_productSoftware);
            }
        }

        return _productSoftwares;
    }

    protected Product addProduct(ProductConf productConf, String serialNumber,
            String datecode, String macAddress, String providerCode)
            throws SQLException, NamingException {
        ProductDao _productDao = new ProductDaoImpl(this.cnxProduct);

        String _serialNumber = ToolsProduct.deleteAhead("0", serialNumber);

        Product _product = null;
        try {
            this.cnxProduct.getCnx().setAutoCommit(false);
            _product = _productDao.addProduct(productConf, _serialNumber,
                    datecode, macAddress, providerCode);
            this.cnxProduct.getCnx().commit();
        } catch (ProductDaoException e) {
            this.cnxProduct.getCnx().rollback();
            e.printStackTrace();
        }

        return _product;
    }

    protected Product addProduct(ProductConf productConf, String serialNumber,
            String datecode, String providerCode) throws SQLException,
            ProductDaoException, NamingException {
        Product _product = this.addProduct(productConf, serialNumber, datecode,
                "", providerCode);

        return _product;
    }

    protected Product addProduct(ProductConf productConf, String serialNumber,
            String datecode) throws SQLException, ProductDaoException,
            NamingException {
        Product _product = this.addProduct(productConf, serialNumber, datecode,
                "", "");

        return _product;
    }

    /**
     * Mise &agrave; jour d'un produit dans la base de donn&eacute;es.
     *
     * @param product Produit.
     * @throws NamingException
     */
    protected void updateProduct(Product product) throws SQLException,
            NamingException {
        ProductDao _productDao = new ProductDaoImpl(this.cnxProduct);

        try {
            this.cnxProduct.getCnx().setAutoCommit(false);
            _productDao.updateProduct(product);
            this.cnxProduct.getCnx().commit();
        } catch (ProductDaoException e) {
            this.cnxProduct.getCnx().rollback();
            e.printStackTrace();
        }
    }

    /*
     * Mise &agrave; jour d'un produit dans la base de donn&eacute;s.
     * 
     * @param product Produit a mettre a jour.
     * 
     * @param productConf Configuration du produit.
     * 
     * @param serialNumber Numero de serie du produit.
     * 
     * @param dateCode Date code du produit.
     * 
     * @param macAddress Adresse Mac du produit.
     * 
     * @param providerCode Code fournisseur du produit.
     * 
     * @param state Etat du produit.
     * 
     * @throws SQLException
     * 
     * @throws ProductDaoException
     */
    private void updateProduct(Product product, ProductConf productConf,
            String serialNumber, String dateCode, String macAddress,
            String providerCode, int state) throws SQLException,
            NamingException {
        ProductDao _productDao = new ProductDaoImpl(this.cnxProduct);

        try {
            this.cnxProduct.getCnx().setAutoCommit(false);
            _productDao.updateProduct(product, productConf, serialNumber,
                    dateCode, macAddress, providerCode, state);
            this.cnxProduct.getCnx().commit();
        } catch (ProductDaoException e) {
            this.cnxProduct.getCnx().rollback();
            e.printStackTrace();
        }
    }

    /**
     * Mise &agrave; jour d'un produit dans la base de donn&eacute;s.
     *
     * @param product Produit a mettre a jour.
     * @param macAddress Adresse Mac du produit.
     *
     * @throws SQLException
     * @throws ProductDaoException
     */
    protected void updateProduct(Product product, String macAddress)
            throws SQLException, ProductDaoException {
        ProductDao _productDao = new ProductDaoImpl(this.cnxProduct);

        _productDao.updateProduct(product, macAddress);
    }

    /*
     * Mise à jour des liens entre un produit et des composants
     */
    private void updateProductComponents(Product product,
            List<Product> productComponents) throws SQLException,
            NamingException {
        // Suppression des anciens composants
        List<Product> _previousProductComponents = this
                .getProductComponents(product);
        for (Product _productComponent : _previousProductComponents) {
            try {
                this.cnxProduct.getCnx().setAutoCommit(false);
                this.removeProductComponent(product, _productComponent);
                this.cnxProduct.getCnx().commit();
            } catch (ProductDaoException e) {
                this.cnxProduct.getCnx().rollback();
                e.printStackTrace();
            }
        }

        // Création de la composition
        for (Product _productComponent : productComponents) {
            this.addProductComponent(product, _productComponent);
        }
    }

    /*
     * Mise à jour des liens entre un produit et des logiciels
     */
    private void updateProductSoftwares(Product product,
            List<Software> productSoftwares) throws SQLException,
            SoftwareDaoException, NamingException {
        // Suppression des anciens logiciels
        List<Software> _previousProductSoftwares = this
                .getProductSoftwares(product);
        for (Software _productSoftware : _previousProductSoftwares) {
            this.removeProductSoftware(product, _productSoftware);
        }

        // Création de la liste des logiciels
        for (Software _productSoftware : productSoftwares) {
            this.addProductSoftware(product, _productSoftware);
        }
    }

    /**
     * Suppression du lien entre un produit et un composant
     *
     * @param product Produit.
     * @param productComponent Composant &agrave; supprimer.
     *
     * @throws SQLException
     * @throws ProductDaoException
     */
    protected void removeProductComponent(Product product,
            Product productComponent) throws SQLException, ProductDaoException {
        ProductDao _productDao = new ProductDaoImpl(this.cnxProduct);

        _productDao.removeProductComponent(product, productComponent);
    }

    /**
     * Cr&eacute;ation d'un lien entre un produit et un composant
     *
     * @param product Produit.
     * @param productComponent Composant &agrave; supprimer.
     *
     * @throws SQLException
     * @throws NamingException
     * @throws ProductDaoException
     */
    protected void addProductComponent(Product product, Product productComponent)
            throws SQLException, NamingException {
        ProductDao _productDao = new ProductDaoImpl(this.cnxProduct);

        try {
            this.cnxProduct.getCnx().setAutoCommit(false);
            _productDao.addProductComponent(product, productComponent);
            this.cnxProduct.getCnx().commit();
        } catch (ProductDaoException e) {
            this.cnxProduct.getCnx().rollback();
            e.printStackTrace();
        }
    }

    protected List<Product> getProductComponents(Product product)
            throws SQLException {
        ProductDao _productDao = new ProductDaoImpl(this.cnxProduct);

        List<Product> _productComponents = _productDao
                .getProductComponents(product);

        return _productComponents;
    }

    protected List<Product> getProductsDispensationNeeded() throws SQLException {
        ProductDao _productDao = new ProductDaoImpl(this.cnxProduct);

        List<Product> _products = _productDao.getProductsDispensationNeeded();

        return _products;
    }

    protected boolean isNeedDispensation(Product product) throws SQLException {
        boolean _isNeedDispensation = true;

        ProductDao _productDao = new ProductDaoImpl(this.cnxProduct);

        _isNeedDispensation = _productDao.isNeedDispensation(product);

        return _isNeedDispensation;
    }

    /**
     * Recherche les logiciels d'un produit de la base de donn&eacute;es.
     *
     * @param product Produit auquel appartiennent les logiciels.
     *
     * @return Logiciels du produit.
     *
     * @throws SQLException
     */
    protected List<Software> getProductSoftwares(Product product)
            throws SQLException {
        SoftwareDao _softwareDao = new SoftwareDaoImpl(this.cnxProduct);

        List<Software> _productSoftwares = _softwareDao.getSoftwares(product);

        return _productSoftwares;
    }

    /*
     * Suppression du lien entre un produit et un logiciel
     */
    private void removeProductSoftware(Product product, Software software)
            throws SQLException {
        SoftwareDao _softwareDao = new SoftwareDaoImpl(this.cnxProduct);

        _softwareDao.removeProductSoftware(product, software);
    }

    /*
     * Création d'un lien entre un produit et un logiciel
     */
    private void addProductSoftware(Product product, Software software)
            throws SQLException, NamingException {
        SoftwareDao _softwareDao = new SoftwareDaoImpl(this.cnxProduct);

        try {
            this.cnxProduct.getCnx().setAutoCommit(false);
            _softwareDao.addProductSoftware(product, software);
            this.cnxProduct.getCnx().commit();
        } catch (SoftwareDaoException e) {
            this.cnxProduct.getCnx().rollback();
            e.printStackTrace();
        }
    }

    // 15-12-11 : RMO : Creation de la méthode
	/*
     * Suppression d'une alimentation présente dans la base
     * 
     * @param productSupplyToDelete l'alimentation à supprimer
     */
    protected void removeProductSupply(ProductSupply productSupplyToDelete)
            throws SQLException {
        ProductSupplyDao _productSupplyDao = new ProductSupplyDaoImpl(
                this.cnxProduct);

        _productSupplyDao.deleteProductSupply(productSupplyToDelete);
    }

    // 15-12-11 : RMO : Creation de la méthode
	/*
     * Suppression d'une famille de produits présent dans la base
     * 
     * @param productFamilyToDelete la famille de produits à supprimer
     */
    protected void removeProductFamily(ProductFamily productFamilyToDelete)
            throws SQLException {
        ProductFamilyDao _productFamilyDao = new ProductFamilyDaoImpl(
                this.cnxProduct);

        _productFamilyDao.deleteProductFamily(productFamilyToDelete);
    }

    // 15-12-11 : RMO : Creation de la méthode
	/*
     * Suppression d'un produit présent dans la base
     * 
     * @param productTypeToDelete le produit à supprimer
     */
    protected void removeProductType(ProductType productTypeToDelete)
            throws SQLException {
        ProductTypeDao _productTypeDao = new ProductTypeDaoImpl(this.cnxProduct);

        _productTypeDao.deleteProductType(productTypeToDelete);
    }

    // 20-12-11 : RMO : Creation de la méthode
	/*
     * Suppression d'un software présent dans la base
     * 
     * @param softwareToDelete le produit à supprimer
     */
    protected void removeSoftware(Software softwareToDelete)
            throws SQLException {
        SoftwareDao _softwareDao = new SoftwareDaoImpl(this.cnxProduct);

        _softwareDao.deleteSoftware(softwareToDelete);
    }

    protected List<Product> getProductWithMother(int startingAt, int maxPerPage, Map<String, String> filters) throws SQLException {

        ProductDao _productDao = new ProductDaoImpl(cnxProduct);

        List<Product> _product = _productDao.getProductWithMother(startingAt, maxPerPage, filters);

        return _product;

    }

    protected Product getProductWithProductConfRef(String reference) throws SQLException {

        ProductDao _productDao = new ProductDaoImpl(cnxProduct);

        Product _product = _productDao.getProductWithProductConfRef(reference);

        return _product;

    }

    // 20-04-12 : RMO : Creation de la méthode
	/*
     * Enregistre un produit de la base FEDD dans la base LAI Donc insertion du
     * produit et de tout ce qui lui fait référence.
     * 
     * Ici, on a pris le aprti de faire q'une méthode qui va insérer toutes les
     * infos sur toutes les tables concernés, car c'est l'ajout du produit dans
     * sa globalité (toutes les infos rattachées à ce dernier) qui nous
     * intéresse. Les tables concernées seront product, product_software,
     * product_product, product_comment, failureReport, customerComment,
     * failure, failureReportComment, elementChanged
     * 
     * @param idProductFEDD l'id du produit dans la base FEDD
     */
    protected Product setProductFEDDtoLAI(int idProductFEDD,
            ProductConf config, String serialNumber, String datecode)
            throws SQLException, ProductDaoException,
            ConfigFileReaderException, IOException, SoftwareDaoException,
            JProductBaseException, NamingException {
        Product _product = null;
        try {
            this.cnxProduct.getCnx().setAutoCommit(false);

            ProductDao _productDao = new ProductDaoImpl(this.cnxProduct);

            // Enregistre le produit
            _product = _productDao.setProductFEDDtoLAI(idProductFEDD, config,
                    serialNumber, datecode);

            // Commit
            this.cnxProduct.getCnx().commit();
        } catch (Exception e) {
            this.cnxProduct.getCnx().rollback();
            e.printStackTrace();
            throw new JProductBaseException(e.getMessage());
        }

        return _product;
    }

    protected Product retreiveProduct(String productConfReference,
            String serialNumber, String datecode)
            throws ConfigFileReaderException, IOException, SQLException,
            JProductBaseException {
        Product _product = null;

        // Retreive productConf
        ProductConfModule _productConfModule = new ProductConfModule(
                this.cnxProduct);
        List<ProductConf> _productConfs = _productConfModule
                .getActiveProductConfs(productConfReference);
        Product _selectedProduct = null;
        for (ProductConf _productConf : _productConfs) {
            String _serialNumber = ToolsProduct.deleteAhead("0", serialNumber);

            // Retreive product
            _selectedProduct = this.getProduct(_productConf, _serialNumber,
                    datecode);
            if (null != _selectedProduct) {
                _product = _selectedProduct;
            }

        }

        return _product;
    }
}
