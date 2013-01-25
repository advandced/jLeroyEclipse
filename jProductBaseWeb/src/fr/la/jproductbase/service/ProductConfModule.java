package fr.la.jproductbase.service;

import java.sql.SQLException;
import java.util.List;

import fr.la.jproductbase.dao.ConnectionProduct;
import fr.la.jproductbase.dao.ProductConfDao;
import fr.la.jproductbase.dao.ProductConfDaoException;
import fr.la.jproductbase.dao.ProductConfDaoImpl;
import fr.la.jproductbase.dao.SoftwareDao;
import fr.la.jproductbase.dao.SoftwareDaoException;
import fr.la.jproductbase.dao.SoftwareDaoImpl;
import fr.la.jproductbase.metier.FollowingFormModel;
import fr.la.jproductbase.metier.ProductConf;
import fr.la.jproductbase.metier.ProductConfModel;
import fr.la.jproductbase.metier.ProductFamily;
import fr.la.jproductbase.metier.ProductSupply;
import fr.la.jproductbase.metier.ProductType;
import fr.la.jproductbase.metier.Software;
import java.util.Map;

public class ProductConfModule {

    private ConnectionProduct cnxProduct;

    protected ProductConfModule(ConnectionProduct cnxProduct) {
        this.cnxProduct = cnxProduct;
    }

    protected ProductConf getProductConf(int idProductConf) throws SQLException {
        ProductConfDao _productConfDao = new ProductConfDaoImpl(this.cnxProduct);

        ProductConf _productConf = _productConfDao
                .getProductConf(idProductConf);

        return _productConf;
    }

    protected ProductConf getProductConf(String reference, String majorIndex,
            String minorIndex) throws SQLException {
        ProductConfDao _productConfDao = new ProductConfDaoImpl(this.cnxProduct);

        ProductConf _productConf = _productConfDao.getProductConf(reference,
                majorIndex, minorIndex);

        return _productConf;
    }

    protected ProductConf getLastActiveProductConf(String reference,
            String majorIndex, String minorIndex) throws SQLException {
        ProductConfDao _productConfDao = new ProductConfDaoImpl(this.cnxProduct);

        ProductConf _productConf = _productConfDao.getLastActiveProductConf(
                reference, majorIndex, minorIndex);

        return _productConf;
    }

    protected List<ProductConf> getProductConfs() throws SQLException {
        ProductConfDao _productConfDao = new ProductConfDaoImpl(this.cnxProduct);

        List<ProductConf> _productConfs = _productConfDao.getProductConfs();

        return _productConfs;
    }

    protected List<ProductConf> getProductConfs(int type) throws SQLException {
        ProductConfDao _productConfDao = new ProductConfDaoImpl(this.cnxProduct);

        List<ProductConf> _productConfs = _productConfDao.getProductConfs(type);

        return _productConfs;
    }

    protected List<ProductConf> getProductConfs(String reference)
            throws SQLException {
        ProductConfDao _productConfDao = new ProductConfDaoImpl(this.cnxProduct);

        List<ProductConf> _productConfs = _productConfDao
                .getProductConfs(reference);

        return _productConfs;
    }

    protected List<ProductConf> getActiveProductConfs() throws SQLException {
        ProductConfDao _productConfDao = new ProductConfDaoImpl(this.cnxProduct);

        List<ProductConf> _productConfs = _productConfDao
                .getActiveProductConfs();

        return _productConfs;
    }

    protected List<ProductConf> getActiveProductConfs(ProductType productType)
            throws SQLException {
        ProductConfDao _productConfDao = new ProductConfDaoImpl(this.cnxProduct);

        List<ProductConf> _productConfs = _productConfDao
                .getActiveProductConfs(productType);

        return _productConfs;
    }

    protected List<ProductConf> getActiveProductConfs(
            String productConfReference) throws SQLException {
        ProductConfDao _productConfDao = new ProductConfDaoImpl(this.cnxProduct);

        List<ProductConf> _productConfs = _productConfDao
                .getActiveProductConfs(productConfReference);

        return _productConfs;
    }

    protected List<ProductConf> getProductConfComponents(ProductConf productConf)
            throws SQLException {
        ProductConfDao _productConfDao = new ProductConfDaoImpl(this.cnxProduct);
        List<ProductConf> _productConfComponents = _productConfDao
                .getProductConfComponents(productConf);

        return _productConfComponents;
    }

    protected ProductConf setProductConf(ProductConf productConf,
            String reference, String majorIndex, String minorIndex,
            ProductConfModel productConfModel, ProductFamily productFamily,
            ProductSupply productSupply, boolean identifiable, int state,
            FollowingFormModel followingFormModel,
            List<ProductConf> productConfComponents,
            List<Software> productConfSoftwares) throws Exception {
        ProductConf _productConf = productConf;

        try {
            this.cnxProduct.getCnx().setAutoCommit(false);

            if (null == _productConf) {
                // New productConf

                // Add
                _productConf = this.addProductConf(reference, majorIndex,
                        minorIndex, productConfModel, identifiable, state,
                        productFamily, productSupply, followingFormModel);
            } else {
                // Existing productconf
                System.out.println("product Conf update");
                // Update
                this.updateProductConf(_productConf, reference, majorIndex,
                        minorIndex, productConfModel, identifiable, state,
                        productFamily, productSupply, followingFormModel);
            }

            // Update link between product and components
            if ((productConfComponents != null)) {
                /* && (productConfComponents.isEmpty() == false)) { */
                this.updateProductConfComponents(_productConf,
                        productConfComponents);
            } else {
                // rien
            }

            // Update link between productConf and softwares in database
            if ((productConfSoftwares != null)) {

                /* && (productConfSoftwares.isEmpty() == false)) { */
                this.updateProductConfSoftwares(_productConf,
                        productConfSoftwares);
            } else {
                // rien
            }

            // Commit
            this.cnxProduct.getCnx().commit();
        } catch (SQLException e) {
            this.cnxProduct.getCnx().rollback();
            throw new Exception(e.getMessage());
        }

        return _productConf;
    }

    private ProductConf addProductConf(String reference, String majorIndex,
            String minorIndex, ProductConfModel productConfModel, boolean identifiable,
            int state, ProductFamily productFamily,
            ProductSupply productSupply, FollowingFormModel followingForm)
            throws SQLException, ProductConfDaoException {
        // Add productType
        ProductConfDao _productConfDao = new ProductConfDaoImpl(this.cnxProduct);

        ProductConf _productConf = _productConfDao.addProductConf(reference,
                majorIndex, minorIndex, productConfModel, identifiable, state,
                productFamily, productSupply, followingForm);

        return _productConf;
    }

    private void updateProductConf(ProductConf productConf, String reference,
            String majorIndex, String minorIndex, ProductConfModel productConfModel,
            Boolean identifiable, int state, ProductFamily productFamily,
            ProductSupply productSupply, FollowingFormModel followingFormModel)
            throws SQLException, ProductConfDaoException {
        ProductConfDao _productConfDao = new ProductConfDaoImpl(this.cnxProduct);

        _productConfDao.updateProductConf(productConf, reference, majorIndex,
                minorIndex, productConfModel, identifiable, state, productFamily,
                productSupply, followingFormModel);
    }

    // 17-01-12 : RMO : Creation de la methode
	/*
     * Mise à jour des liens entre un produit et des cartes (ProductConf)
     */
    private void updateProductConfComponents(ProductConf productConf,
            List<ProductConf> productConfComponents) throws SQLException,
            ProductConfDaoException {
        // Suppression des anciens logiciels
        ProductConfModule _productConfModule = new ProductConfModule(
                this.cnxProduct);
        List<ProductConf> _previousProductConfComponents = _productConfModule
                .getProductConfComponents(productConf);
        for (ProductConf _productConfProductConf : _previousProductConfComponents) {
            this.removeProductConfComponent(productConf,
                    _productConfProductConf);
        }

        // Création de la liste des logiciels
        for (ProductConf _productConfComponent : productConfComponents) {
            this.addProductConfComponent(productConf, _productConfComponent);
        }
    }

    // 17-01-12 : RMO : Creation de la methode
	/*
     * Suppression du lien entre une config produit et une carte (ProductConf)
     */
    private void removeProductConfComponent(ProductConf productConf,
            ProductConf component) throws SQLException {
        ProductConfDao _productConfDao = new ProductConfDaoImpl(this.cnxProduct);

        _productConfDao.removeProductConfComponent(productConf, component);
    }

    // 17-01-12 : RMO : Creation de la methode
	/*
     * Création d'un lien entre une config produit et une carte (ProductConf)
     */
    private void addProductConfComponent(ProductConf productConf,
            ProductConf component) throws SQLException, ProductConfDaoException {
        ProductConfDao _productConfDao = new ProductConfDaoImpl(this.cnxProduct);

        _productConfDao.addProductConfComponent(productConf, component);
    }

    // 10-01-12 : RMO : Creation de la methode
	/*
     * Mise à jour des liens entre un produit et des logiciels
     */
    private void updateProductConfSoftwares(ProductConf productConf,
            List<Software> productConfSoftwares) throws SQLException,
            SoftwareDaoException {
        // Suppression des anciens logiciels
        List<Software> _previousProductConfSoftwares = this
                .getProductConfSoftwares(productConf);
        for (Software _productConfSoftware : _previousProductConfSoftwares) {
            this.removeProductConfSoftware(productConf, _productConfSoftware);
        }

        // Création de la liste des logiciels
        for (Software _productConfSoftware : productConfSoftwares) {
            this.addProductConfSoftware(productConf, _productConfSoftware);
        }
    }

    // 10-01-12 : RMO : Creation de la methode
	/*
     * Suppression du lien entre une config produit et un logiciel
     */
    private void removeProductConfSoftware(ProductConf productConf,
            Software software) throws SQLException {
        SoftwareDao _softwareDao = new SoftwareDaoImpl(this.cnxProduct);

        _softwareDao.removeProductConfSoftware(productConf, software);
    }

    // 10-01-12 : RMO : Creation de la methode
	/*
     * Création d'un lien entre une config produit et un logiciel
     */
    private void addProductConfSoftware(ProductConf productConf,
            Software software) throws SQLException, SoftwareDaoException {
        SoftwareDao _softwareDao = new SoftwareDaoImpl(this.cnxProduct);

        _softwareDao.addProductConfSoftware(productConf, software);
    }

    // 10-01-12 : RMO : Creation de la methode
    /**
     * Recherche les logiciels d'un produit de la base de donn&eacute;es.
     *
     * @param productConf Configuration produit auquel appartiennent les
     * logiciels.
     *
     * @return Logiciels de la configuration produit.
     *
     * @throws SQLException
     */
    protected List<Software> getProductConfSoftwares(ProductConf productConf)
            throws SQLException {
        SoftwareDao _softwareDao = new SoftwareDaoImpl(this.cnxProduct);

        List<Software> _productConfSoftwares = _softwareDao
                .getSoftwares(productConf);

        return _productConfSoftwares;
    }

    protected List<ProductConf> getProductConfLazy(Map<String, String> filters, int limit, int maxperpage) throws SQLException {

        ProductConfDao _productConfDao = new ProductConfDaoImpl(this.cnxProduct);

        List<ProductConf> _productConf = _productConfDao.getProductConfLazy(filters, limit, maxperpage);

        return _productConf;

    }

    protected int countProductConf(Map<String, String> filters) throws SQLException {

        ProductConfDao _productConfDao = new ProductConfDaoImpl(this.cnxProduct);

        int i = _productConfDao.countProductConf(filters);

        return i;

    }
}
