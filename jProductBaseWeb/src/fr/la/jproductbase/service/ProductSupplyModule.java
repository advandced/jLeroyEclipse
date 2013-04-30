package fr.la.jproductbase.service;

import java.util.List;

import fr.la.jproductbase.dao.ProductSupplyDao;
import fr.la.jproductbase.metier.ProductSupply;

public class ProductSupplyModule {

	ProductSupplyDao _productSupplyDao;
	
	public ProductSupplyModule(ProductSupplyDao productSupplyDao) {
		_productSupplyDao = productSupplyDao;
	}

	public ProductSupply addProductSupply(String name, int state) {
		return _productSupplyDao.addProductSupply(name, state);
	}

	public ProductSupply getProductSupply(int idProductSupply) {
		return _productSupplyDao.getProductSupply(idProductSupply);
	}

	public List<ProductSupply> getProductSupplies() {
		return _productSupplyDao.getProductSupplies();
	}

	public List<ProductSupply> getActiveProductSupplies() {
		return _productSupplyDao.getActiveProductSupplies();
	}

	public void updateProductSupply(ProductSupply productSupplyToUpdate) {
		_productSupplyDao.updateProductSupply(productSupplyToUpdate);
	}
}
