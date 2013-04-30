package fr.la.jproductbase.service;

import java.util.List;

import fr.la.jproductbase.dao.ProductTypeDao;
import fr.la.jproductbase.metier.ProductType;

public class ProductTypeModule {

	ProductTypeDao _productTypeDao;
	
	public ProductTypeModule(ProductTypeDao productTypeDao) {
		_productTypeDao = productTypeDao;
	}

	// 15-12-11 : RMO : Creation de la méthode
	public ProductType addProductType(String name, int state) {
		return _productTypeDao.addProductType(name, state);
	}

	public ProductType getProductType(int idProductType) {
		return _productTypeDao.getProductType(idProductType);
	}

	public ProductType getProductType(String productTypeName) {
		return _productTypeDao.getProductType(productTypeName);
	}

	public List<ProductType> getProductTypes() {
		return _productTypeDao.getProductTypes();
	}

	public List<ProductType> getActiveProductTypes() {
		return _productTypeDao.getActiveProductTypes();
	}

	// 15-12-11 : RMO : Creation de la méthode
	public void updateProductType(ProductType productType) {
		_productTypeDao.updateProductType(productType);
	}
}
