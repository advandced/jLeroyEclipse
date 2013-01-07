package fr.la.jproductbase.service;

import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;

import fr.la.jproductbase.dao.ConnectionProduct;
import fr.la.jproductbase.dao.ProductDaoException;
import fr.la.jproductbase.dao.ProductSupplyDao;
import fr.la.jproductbase.dao.ProductSupplyDaoImpl;
import fr.la.jproductbase.metier.ProductSupply;

public class ProductSupplyModule {
	private ConnectionProduct cnxProduct;

	protected ProductSupplyModule(ConnectionProduct cnxProduct) {
		this.cnxProduct = cnxProduct;
	}

	protected ProductSupply addProductSupply(String name, int state)
			throws SQLException, NamingException {
		ProductSupplyDao _productSupplyDao = new ProductSupplyDaoImpl(
				this.cnxProduct);

		ProductSupply _productSupply = null;
		try {
			this.cnxProduct.getCnx().setAutoCommit(false);
			_productSupply = _productSupplyDao.addProductSupply(name,
					state);
			this.cnxProduct.getCnx().commit();
		} catch (ProductDaoException e) {
			this.cnxProduct.getCnx().rollback();
			e.printStackTrace();
		}

		return _productSupply;
	}

	protected ProductSupply getProductSupply(int idProductSupply)
			throws SQLException {
		ProductSupplyDao _productSupplyDao = new ProductSupplyDaoImpl(
				this.cnxProduct);

		ProductSupply _productSupply = _productSupplyDao
				.getProductSupply(idProductSupply);

		return _productSupply;
	}

	protected List<ProductSupply> getProductSupplies() throws SQLException {
		ProductSupplyDao _productSupplyDao = new ProductSupplyDaoImpl(
				this.cnxProduct);

		List<ProductSupply> _productSupplies = _productSupplyDao
				.getProductSupplies();

		return _productSupplies;
	}

	protected List<ProductSupply> getActiveProductSupplies()
			throws SQLException {
		ProductSupplyDao _productSupplyDao = new ProductSupplyDaoImpl(
				this.cnxProduct);

		List<ProductSupply> _activeProductSupplies = _productSupplyDao
				.getActiveProductSupplies();

		return _activeProductSupplies;
	}

	protected void updateProductSupply(ProductSupply productSupplyToUpdate)
			throws SQLException, NamingException {
		ProductSupplyDao _productSupplyDao = new ProductSupplyDaoImpl(
				this.cnxProduct);

		try {
			this.cnxProduct.getCnx().setAutoCommit(false);
			_productSupplyDao.updateProductSupply(productSupplyToUpdate);
			this.cnxProduct.getCnx().commit();
		} catch (ProductDaoException e) {
			this.cnxProduct.getCnx().rollback();
			e.printStackTrace();
		}
	}
}
