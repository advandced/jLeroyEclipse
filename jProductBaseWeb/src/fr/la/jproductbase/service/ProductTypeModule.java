package fr.la.jproductbase.service;

import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;

import fr.la.jproductbase.dao.ConnectionProduct;
import fr.la.jproductbase.dao.ProductDaoException;
import fr.la.jproductbase.dao.ProductTypeDao;
import fr.la.jproductbase.dao.ProductTypeDaoImpl;
import fr.la.jproductbase.metier.ProductType;

public class ProductTypeModule {
	private ConnectionProduct cnxProduct;

	protected ProductTypeModule(ConnectionProduct cnxProduct) {
		this.cnxProduct = cnxProduct;
	}

	// 15-12-11 : RMO : Creation de la méthode
	protected ProductType addProductType(String name, int state)
			throws SQLException, NamingException {
		ProductTypeDao _productTypeDao = new ProductTypeDaoImpl(this.cnxProduct);

		ProductType _productType = null;
		try {
			this.cnxProduct.getCnx().setAutoCommit(false);
			_productType = _productTypeDao.addProductType(name, state);
			this.cnxProduct.getCnx().commit();
		} catch (ProductDaoException e) {
			this.cnxProduct.getCnx().rollback();
			e.printStackTrace();
		}

		return _productType;
	}

	protected ProductType getProductType(int idProductType) throws SQLException {
		ProductTypeDao _productTypeDao = new ProductTypeDaoImpl(this.cnxProduct);

		ProductType _productType = _productTypeDao
				.getProductType(idProductType);

		return _productType;
	}

	protected ProductType getProductType(String productTypeName)
			throws SQLException {
		ProductTypeDao _productTypeDao = new ProductTypeDaoImpl(this.cnxProduct);

		ProductType _productType = _productTypeDao
				.getProductType(productTypeName);

		return _productType;
	}

	protected List<ProductType> getProductTypes() throws SQLException {
		ProductTypeDao _productTypeDao = new ProductTypeDaoImpl(this.cnxProduct);

		List<ProductType> _productTypes = _productTypeDao.getProductTypes();

		return _productTypes;
	}

	protected List<ProductType> getActiveProductTypes() throws SQLException {
		ProductTypeDao _productTypeDao = new ProductTypeDaoImpl(this.cnxProduct);

		List<ProductType> _activeProductTypes = _productTypeDao
				.getActiveProductTypes();

		return _activeProductTypes;
	}

	// 15-12-11 : RMO : Creation de la méthode
	protected void updateProductType(ProductType productType)
			throws SQLException, NamingException {
		ProductTypeDao _productTypeDao = new ProductTypeDaoImpl(this.cnxProduct);

		try {
			this.cnxProduct.getCnx().setAutoCommit(false);
			_productTypeDao.updateProductType(productType);
			this.cnxProduct.getCnx().commit();
		} catch (ProductDaoException e) {
			this.cnxProduct.getCnx().rollback();
			e.printStackTrace();
		}
	}
}
