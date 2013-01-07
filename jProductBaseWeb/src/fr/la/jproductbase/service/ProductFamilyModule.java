package fr.la.jproductbase.service;

import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;

import fr.la.jproductbase.dao.ConnectionProduct;
import fr.la.jproductbase.dao.ProductFamilyDao;
import fr.la.jproductbase.dao.ProductFamilyDaoException;
import fr.la.jproductbase.dao.ProductFamilyDaoImpl;
import fr.la.jproductbase.metier.ProductFamily;
import fr.la.jproductbase.metier.ProductType;

public class ProductFamilyModule {
	private ConnectionProduct cnxProduct;

	protected ProductFamilyModule(ConnectionProduct cnxProduct) {
		this.cnxProduct = cnxProduct;
	}

	protected ProductFamily addProductFamily(String name, int state,
			ProductType productType) throws SQLException,
			ProductFamilyDaoException {
		ProductFamilyDao _productFamilyDao = new ProductFamilyDaoImpl(
				this.cnxProduct);

		ProductFamily _productFamily = _productFamilyDao.addProductFamily(name,
				state, productType);

		return _productFamily;
	}

	protected ProductFamily getProductFamily(int idProductFamily)
			throws SQLException {
		ProductFamilyDao _productFamilyDao = new ProductFamilyDaoImpl(
				this.cnxProduct);

		ProductFamily _productFamily = _productFamilyDao
				.getProductFamily(idProductFamily);

		return _productFamily;
	}

	protected List<ProductFamily> getProductFamilies() throws SQLException {
		ProductFamilyDao _productFamilyDao = new ProductFamilyDaoImpl(
				this.cnxProduct);

		List<ProductFamily> _productFamilies = _productFamilyDao
				.getProductFamilies();

		return _productFamilies;
	}
	
	protected List<ProductFamily> getProductFamilies(int type) throws SQLException {
		ProductFamilyDao _productFamilyDao = new ProductFamilyDaoImpl(
				this.cnxProduct);

		List<ProductFamily> _productFamilies = _productFamilyDao
				.getProductFamilies(type);

		return _productFamilies;
	}

	protected List<ProductFamily> getActiveProductFamilies()
			throws SQLException {
		ProductFamilyDao _productFamilyDao = new ProductFamilyDaoImpl(
				this.cnxProduct);

		List<ProductFamily> _activeProductFamilies = _productFamilyDao
				.getActiveProductFamilies();

		return _activeProductFamilies;
	}

	// 15-12-11 : RMO : Creation de la méthode
	protected void updateProductFamily(ProductFamily productFamilyToUpdate)
			throws SQLException, NamingException {
		ProductFamilyDao _productFamilyDao = new ProductFamilyDaoImpl(
				this.cnxProduct);

		try {
			_productFamilyDao.updateProductFamily(productFamilyToUpdate);
			this.cnxProduct.getCnx().commit();
		} catch (ProductFamilyDaoException e) {
			this.cnxProduct.getCnx().rollback();
			e.printStackTrace();
		}
	}
}
