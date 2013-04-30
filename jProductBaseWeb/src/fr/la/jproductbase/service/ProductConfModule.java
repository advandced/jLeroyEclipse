package fr.la.jproductbase.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import fr.la.jproductbase.dao.ProductConfDao;
import fr.la.jproductbase.dao.SoftwareDao;
import fr.la.jproductbase.metier.FollowingFormModel;
import fr.la.jproductbase.metier.ProductConf;
import fr.la.jproductbase.metier.ProductConfModel;
import fr.la.jproductbase.metier.ProductFamily;
import fr.la.jproductbase.metier.ProductSupply;
import fr.la.jproductbase.metier.ProductType;
import fr.la.jproductbase.metier.Software;

public class ProductConfModule {

	ProductConfDao _productConfDao;
	SoftwareDao _softwareDao;

   public ProductConfModule(ProductConfDao productConfDao, SoftwareDao softwareDao) {
    	_productConfDao = productConfDao;
    	_softwareDao = softwareDao;
    }

    public ProductConf getProductConf(int idProductConf) {
    	return _productConfDao.getProductConf(idProductConf);
    }

    public ProductConf getProductConf(String reference, String majorIndex, String minorIndex)  {
        return _productConfDao.getProductConf(reference, majorIndex, minorIndex);
    }

    public ProductConf getLastActiveProductConf(String reference, String majorIndex, String minorIndex) {
        return _productConfDao.getLastActiveProductConf(reference, majorIndex, minorIndex);
    }

    public List<ProductConf> getProductConfs() {
    	return _productConfDao.getProductConfs();
    }

    public List<ProductConf> getProductConfs(int type) {
        return _productConfDao.getProductConfs(type);
    }

    public List<ProductConf> getProductConfs(String reference) {
        return _productConfDao.getProductConfs(reference);
    }

    public List<ProductConf> getActiveProductConfs() {
        return _productConfDao.getActiveProductConfs();
    }

    public List<ProductConf> getActiveProductConfs(ProductType productType) {
        return _productConfDao.getActiveProductConfs(productType);
    }

    public List<ProductConf> getActiveProductConfs(String productConfReference) {
        return _productConfDao.getActiveProductConfs(productConfReference);
    }

    public List<ProductConf> getProductConfComponents(ProductConf productConf) {
        return _productConfDao.getProductConfComponents(productConf);
    }

    public ProductConf setProductConf(ProductConf productConf,
            String reference, String majorIndex, String minorIndex,
            ProductConfModel productConfModel, ProductFamily productFamily,
            ProductSupply productSupply, boolean identifiable, int state,
            FollowingFormModel followingFormModel,
            List<ProductConf> productConfComponents,
            List<Software> productConfSoftwares) {
        ProductConf _productConf = productConf;


        if (null == _productConf) {
            // New productConf

            // Add
            _productConf = this.addProductConf(reference, majorIndex,
                    minorIndex, productConfModel, identifiable, state,
                    productFamily, productSupply, followingFormModel);
        } else {
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

        return _productConf;
    }

    private ProductConf addProductConf(String reference, String majorIndex,
            String minorIndex, ProductConfModel productConfModel, boolean identifiable,
            int state, ProductFamily productFamily,
            ProductSupply productSupply, FollowingFormModel followingForm) {
        // Add productType
        return _productConfDao.addProductConf(reference,
                majorIndex, minorIndex, productConfModel, identifiable, state,
                productFamily, productSupply, followingForm);
    }

    private void updateProductConf(ProductConf productConf, String reference,
            String majorIndex, String minorIndex, ProductConfModel productConfModel,
            Boolean identifiable, int state, ProductFamily productFamily,
            ProductSupply productSupply, FollowingFormModel followingFormModel) {

        _productConfDao.updateProductConf(productConf, reference, majorIndex,
                minorIndex, productConfModel, identifiable, state, productFamily,
                productSupply, followingFormModel);
    }

    // 17-01-12 : RMO : Creation de la methode
	/*
     * Mise à jour des liens entre un produit et des cartes (ProductConf)
     */
    private void updateProductConfComponents(ProductConf productConf, List<ProductConf> productConfComponents) {
        // Suppression des anciens logiciels
        List<ProductConf> _previousProductConfComponents = this.getProductConfComponents(productConf);
        for (ProductConf _productConfProductConf : _previousProductConfComponents) {
            this.removeProductConfComponent(productConf, _productConfProductConf);
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
    private void removeProductConfComponent(ProductConf productConf, ProductConf component) {
        _productConfDao.removeProductConfComponent(productConf, component);
    }

    // 17-01-12 : RMO : Creation de la methode
	/*
     * Création d'un lien entre une config produit et une carte (ProductConf)
     */
    private void addProductConfComponent(ProductConf productConf, ProductConf component) {
        _productConfDao.addProductConfComponent(productConf, component);
    }

    // 10-01-12 : RMO : Creation de la methode
	/*
     * Mise à jour des liens entre un produit et des logiciels
     */
    private void updateProductConfSoftwares(ProductConf productConf, List<Software> productConfSoftwares) {
        // Suppression des anciens logiciels
        List<Software> _previousProductConfSoftwares = this.getProductConfSoftwares(productConf);
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
    private void removeProductConfSoftware(ProductConf productConf, Software software) {
        _softwareDao.removeProductConfSoftware(productConf, software);
    }

    // 10-01-12 : RMO : Creation de la methode
	/*
     * Création d'un lien entre une config produit et un logiciel
     */
    private void addProductConfSoftware(ProductConf productConf, Software software) {
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
    public List<Software> getProductConfSoftwares(ProductConf productConf) {
    	return _softwareDao.getSoftwares(productConf);
    }

    public List<ProductConf> getProductConfLazy(Map<String, String> filters, int limit, int maxperpage) {
        return _productConfDao.getProductConfLazy(filters, limit, maxperpage);
    }

    public int countProductConf(Map<String, String> filters) {
        return _productConfDao.countProductConf(filters);
    }
}
