package fr.la.jproductbase.service;

import java.util.List;

import fr.la.jproductbase.dao.ProductFamilyDao;
import fr.la.jproductbase.metier.ProductFamily;
import fr.la.jproductbase.metier.ProductType;

public class ProductFamilyModule {

	ProductFamilyDao _productFamilyDao;
	
	public ProductFamilyModule(ProductFamilyDao productFamilyDao) {
		_productFamilyDao = productFamilyDao;
	}

	public ProductFamily addProductFamily(String name, int state, ProductType productType) {
		return _productFamilyDao.addProductFamily(name, state, productType);
	}

	public ProductFamily getProductFamily(int idProductFamily) {
		return _productFamilyDao.getProductFamily(idProductFamily);
	}

	public List<ProductFamily> getProductFamilies() {
		return _productFamilyDao.getProductFamilies();
	}
	
	public List<ProductFamily> getProductFamilies(int type) {
		return _productFamilyDao.getProductFamilies(type);
	}

	public List<ProductFamily> getActiveProductFamilies() {
		return _productFamilyDao.getActiveProductFamilies();
	}

	// 15-12-11 : RMO : Creation de la méthode
	public void updateProductFamily(ProductFamily productFamilyToUpdate) {
		_productFamilyDao.updateProductFamily(productFamilyToUpdate);
	}
}
