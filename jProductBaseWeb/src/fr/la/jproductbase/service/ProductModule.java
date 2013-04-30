package fr.la.jproductbase.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.naming.NamingException;

import fr.la.jproductbase.dao.ProductDao;
import fr.la.jproductbase.dao.ProductFamilyDao;
import fr.la.jproductbase.dao.ProductSupplyDao;
import fr.la.jproductbase.dao.ProductTypeDao;
import fr.la.jproductbase.dao.SoftwareDao;
import fr.la.jproductbase.metier.Product;
import fr.la.jproductbase.metier.ProductConf;
import fr.la.jproductbase.metier.ProductConfModel;
import fr.la.jproductbase.metier.ProductFamily;
import fr.la.jproductbase.metier.ProductSupply;
import fr.la.jproductbase.metier.ProductType;
import fr.la.jproductbase.metier.Software;
import fr.la.jproductbase.metier.ToolsProduct;

public class ProductModule {

	ProductDao _productDao;
	SoftwareDao _softwareDao;
	ProductTypeDao _productTypeDao;
	ProductFamilyDao _productFamilyDao;
	ProductSupplyDao _productSupplyDao;
	
	ProductConfModule _productConfModule;
	SoftwareModule _softwareModule;

    public ProductModule(ProductConfModule productConfModule, SoftwareModule softwareModule, ProductDao productDao, SoftwareDao softwareDao, ProductTypeDao productTypeDao, ProductFamilyDao productFamilyDao, ProductSupplyDao productSupplyDao) {
    	_productDao = productDao;
    	_softwareDao = softwareDao;
    	_productTypeDao = productTypeDao;
    	_productFamilyDao = productFamilyDao;
    	_productSupplyDao = productSupplyDao;
    	
    	_productConfModule = productConfModule;
    	_softwareModule = softwareModule;
    }

    public List<Product> getProducts() {
        return _productDao.getProducts();
    }

    public List<Product> getProducts(ProductType productType) {
        return _productDao.getProducts(productType);
    }

    public List<Product> getProducts(ProductConfModel productConfModel) {
        return _productDao.getProducts(productConfModel);
    }

    public List<Product> getProducts(ProductConf productConf) {
        return _productDao.getProducts(productConf);
    }

    public List<Product> getProductsEnables(int idproduct, String reference) {
        return _productDao.getProductsEnables(idproduct, reference);
    }

    public List<Product> getProductsRecordables(String modele) {
        return _productDao.getProductsRecordables(modele);
    }
    
    public List<Product> getProductsSearch(int startingAt, int maxPerPage, Map<String, String> filters, int type) {
        return _productDao.getProductsSearch(startingAt, maxPerPage, filters, type);
    }

    public List<Product> getProducts(String serialNumber, String datecode) {
        String _serialNumber = ToolsProduct.deleteAhead("0", serialNumber);
        return _productDao.getProducts(_serialNumber, datecode);
    }

    public Product getProduct(int idProduct) {
        Product _product = _productDao.getProduct(idProduct);

        return _product;
    }

    public Product getProduct(ProductConf productConf, String serialNumber, String datecode) {

        String _serialNumber = ToolsProduct.deleteAhead("0", serialNumber);

        Product _product = _productDao.getProduct(productConf, _serialNumber, datecode);

        return _product;
    }

    public Product getProduct(String productConfReference, String serialNumber, String datecode) {


        String _serialNumber = ToolsProduct.deleteAhead("0", serialNumber);

        Product _product = _productDao.getProduct(productConfReference,
                _serialNumber, datecode);

        return _product;
    }

    // 09-05-12 : RMO : Création de la méthode
    public Product getMainProduct(Product carte) {

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
    public int countQueryResult(Map<String, String> filters, int type) {
     
        int count = _productDao.countProducts(filters, type);
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
            List<Product> productComponents, List<Software> productSoftwares) {
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

            this.updateProduct(product);

        }

        // Update link between product and components
        this.updateProductComponents(_product, productComponents);

        // Update link between product and softwares
        this.updateProductSoftwares(_product, productSoftwares);

        return _product;
    }

    public Product setProduct(Product product) {
        Product _product = null;

        // Retreive product
        if (product.getIdProduct() != 0) {
            _product = this.getProduct(product.getIdProduct());
        } else {
            _product = this.getProduct(product.getProductConf(),
                    product.getSerialNumber(), product.getDatecode());
        }

        // Update product components
        List<Product> _productComponents = this.updateProductComponents(this.getProductComponents(product));

        // Update product softwares
        List<Software> _productSoftwares = this.updateProductSoftwares(product.getProductSoftwares());

        // Save product
        _product = this.setProduct(_product, product.getProductConf(),
                product.getSerialNumber(), product.getDatecode(),
                _productComponents, _productSoftwares);


        return _product;
    }

    public Product setProduct(int idProduct, String productConfReference,
            String productConfMajorIndex, String productConfMinorIndex,
            String serialNumber, String datecode, String[][] productComponents,
            String[][] productSoftwares) {
        Product _product = null;

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
             updateProductComponents(_product, productComponents);

            // Update product softwares
             updateProductSoftwares(_product, productSoftwares);

            // Save product
            _product = this.setProduct(_product);

        } else {
            //throw new JProductBaseException("Configuration produit inconnue.");
        }

        return _product;
    }

	private void updateProductComponents(Product _product , String[][] productComponents) {
		_product.setProductComponents( new ArrayList<Product>() );

		if (null != productComponents) {
			Product _productComponent;

			int _nbRow = productComponents[0].length;
			int _idProductConf = 0;
			String _productConfReference;
			String _productConfMajorIndex;
			String _productConfMinorIndex;
			String _datecode;
			String _serialNumber;
			String _provider;
			for (int _componentRow = 0; _componentRow < _nbRow; _componentRow++) {
				if (productComponents[0][_componentRow].equals("")) {
					_idProductConf = Integer
							.parseInt(productComponents[0][_componentRow]);
				}
				_productConfReference = productComponents[1][_componentRow];
				_productConfMajorIndex = productComponents[2][_componentRow];
				_productConfMinorIndex = productComponents[3][_componentRow];
				_datecode = productComponents[5][_componentRow];
				_serialNumber = productComponents[6][_componentRow];
				_provider = productComponents[7][_componentRow];

				//ServiceInterface _serviceInterface = new ServiceInterface();
				// Retreive productConf
				ProductConf _productConf = null;
				if (0 == _idProductConf) {
					_productConf = _productConfModule.getProductConf(_productConfReference, _productConfMajorIndex, _productConfMinorIndex);
				} else {
					_productConf = _productConfModule.getProductConf(_idProductConf);
				}

				if (null != _productConf) {
					// Retreive component
					_productComponent = getProduct(_productConf,_serialNumber, _datecode);
					if (null == _productComponent) {
						// New component
						_productComponent = addProduct(_productConf, _serialNumber, _datecode, _provider);
					} else {
						// Exiting component
					}

					_product.getProductComponents().add(_productComponent);
				} else {
					throw new IllegalStateException("Configuration produit inconnue.");
				}
			}
		}
	}
    
	private void updateProductSoftwares(Product _product , String[][] productSoftwares) {
		_product.setProductSoftwares( new ArrayList<Software>() );

		if (null != productSoftwares) {
			Software _software;

			int _nbRow = productSoftwares[0].length;
			String _name;
			String _version;
			for (int _softwareRow = 0; _softwareRow < _nbRow; _softwareRow++) {
				_name = productSoftwares[1][_softwareRow];
				_version = productSoftwares[2][_softwareRow];

				//ServiceInterface _serviceInterface = new ServiceInterface();
				// Retreive software
				_software = _softwareModule.getSoftware(_name, _version);
				if (null == _software) {
					// New software
					_software = _softwareModule.addSoftware(_name, _version);
				} else {
					// Exiting component
				}

				_product.getProductSoftwares().add(_software);
			}
		}
	}
    
    public Product setProduct(Product product, ProductConf productConf,
            String serialNumber, String dateCode, String macAddress,
            String providerCode, int state, List<Product> productComponents,
            List<Software> productSoftwares) {
        Product _product = product;

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


        return _product;
    }

    /*
     * Création de la liste des composants du produit à partir d'une liste de
     * composants.
     * 
     * @param productComponents Liste des composants.
     */
    private List<Product> updateProductComponents(List<Product> productComponents) {
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
                     this.updateProduct(_productComponent);
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
            List<Software> productSoftwares) {
        List<Software> _productSoftwares = new ArrayList<Software>();

        if (null != productSoftwares) {
            String _name;
            String _version;
            for (Software _productSoftware : productSoftwares) {
                _name = _productSoftware.getName();
                _version = _productSoftware.getVersion();

                // Retreive software
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

    public Product addProduct(ProductConf productConf, String serialNumber, String datecode, String macAddress, String providerCode) {
        String _serialNumber = ToolsProduct.deleteAhead("0", serialNumber);
        return _productDao.addProduct(productConf, _serialNumber, datecode, macAddress, providerCode);
    }

    public Product addProduct(ProductConf productConf, String serialNumber, String datecode, String providerCode)  {
        return this.addProduct(productConf, serialNumber, datecode, "", providerCode);
    }

    public Product addProduct(ProductConf productConf, String serialNumber, String datecode) {
        return this.addProduct(productConf, serialNumber, datecode,  "", "");
    }

    /**
     * Mise &agrave; jour d'un produit dans la base de donn&eacute;es.
     *
     * @param product Produit.
     * @throws NamingException
     */
    public void updateProduct(Product product) {
        _productDao.updateProduct(product);
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
            String providerCode, int state) {
        _productDao.updateProduct(product, productConf, serialNumber, dateCode, macAddress, providerCode, state);
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
    public void updateProduct(Product product, String macAddress) {
        _productDao.updateProduct(product, macAddress);
    }

    /*
     * Mise à jour des liens entre un produit et des composants
     */
    private void updateProductComponents(Product product, List<Product> productComponents) {
        // Suppression des anciens composants
        List<Product> _previousProductComponents = this.getProductComponents(product);
        for (Product _productComponent : _previousProductComponents) {
              this.removeProductComponent(product, _productComponent);
        }

        // Création de la composition
        for (Product _productComponent : productComponents) {
            this.addProductComponent(product, _productComponent);
        }
    }

    /*
     * Mise à jour des liens entre un produit et des logiciels
     */
    private void updateProductSoftwares(Product product, List<Software> productSoftwares) {
        // Suppression des anciens logiciels
        List<Software> _previousProductSoftwares = this.getProductSoftwares(product);
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
    public void removeProductComponent(Product product, Product productComponent) {
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
    public void addProductComponent(Product product, Product productComponent) {
        _productDao.addProductComponent(product, productComponent);
    }

    public List<Product> getProductComponents(Product product) {
        return _productDao.getProductComponents(product);
    }

    public List<Product> getProductsDispensationNeeded() {
        return _productDao.getProductsDispensationNeeded();
    }

    public boolean isNeedDispensation(Product product) {
        return _productDao.isNeedDispensation(product);
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
    public List<Software> getProductSoftwares(Product product) {
        return _softwareDao.getSoftwares(product);
    }

    /*
     * Suppression du lien entre un produit et un logiciel
     */
    private void removeProductSoftware(Product product, Software software) {
        _softwareDao.removeProductSoftware(product, software);
    }

    /*
     * Création d'un lien entre un produit et un logiciel
     */
    private void addProductSoftware(Product product, Software software) {
    	_softwareDao.addProductSoftware(product, software);
    }

    // 15-12-11 : RMO : Creation de la méthode
	/*
     * Suppression d'une alimentation présente dans la base
     * 
     * @param productSupplyToDelete l'alimentation à supprimer
     */
    public void removeProductSupply(ProductSupply productSupplyToDelete) {
        _productSupplyDao.deleteProductSupply(productSupplyToDelete);
    }

    // 15-12-11 : RMO : Creation de la méthode
	/*
     * Suppression d'une famille de produits présent dans la base
     * 
     * @param productFamilyToDelete la famille de produits à supprimer
     */
    public void removeProductFamily(ProductFamily productFamilyToDelete) {
        _productFamilyDao.deleteProductFamily(productFamilyToDelete);
    }

    // 15-12-11 : RMO : Creation de la méthode
	/*
     * Suppression d'un produit présent dans la base
     * 
     * @param productTypeToDelete le produit à supprimer
     */
    public void removeProductType(ProductType productTypeToDelete) {
        _productTypeDao.deleteProductType(productTypeToDelete);
    }

    // 20-12-11 : RMO : Creation de la méthode
	/*
     * Suppression d'un software présent dans la base
     * 
     * @param softwareToDelete le produit à supprimer
     */
    public void removeSoftware(Software softwareToDelete) {
        _softwareDao.deleteSoftware(softwareToDelete);
    }

    public List<Product> getProductWithMother(int startingAt, int maxPerPage, Map<String, String> filters) {
        return _productDao.getProductWithMother(startingAt, maxPerPage, filters);
    }

    public Product getProductWithProductConfRef(String reference) {
        return _productDao.getProductWithProductConfRef(reference);
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
    public Product setProductFEDDtoLAI(int idProductFEDD, ProductConf config, String serialNumber, String datecode) {
        return _productDao.setProductFEDDtoLAI(idProductFEDD, config, serialNumber, datecode);
    }

    public Product retreiveProduct(String productConfReference, String serialNumber, String datecode) {
        Product _product = null;

        // Retreive productConf
        List<ProductConf> _productConfs = _productConfModule.getActiveProductConfs(productConfReference);
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
