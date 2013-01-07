package fr.la.jproductbase.service;

import java.sql.SQLException;
import java.util.List;

import fr.la.jproductbase.dao.ConnectionProduct;
import fr.la.jproductbase.dao.ProductDocumentDao;
import fr.la.jproductbase.dao.ProductDocumentDaoException;
import fr.la.jproductbase.dao.ProductDocumentDaoImpl;
import fr.la.jproductbase.dao.ProductDocumentTypeDao;
import fr.la.jproductbase.dao.ProductDocumentTypeDaoException;
import fr.la.jproductbase.dao.ProductDocumentTypeDaoImpl;
import fr.la.jproductbase.metier.Product;
import fr.la.jproductbase.metier.ProductDocument;
import fr.la.jproductbase.metier.ProductDocumentType;

public class ProductDocumentModule {
	private ConnectionProduct cnxProduct;

	protected ProductDocumentModule(ConnectionProduct cnxProduct) {
		this.cnxProduct = cnxProduct;
	}

	// 20-06-12 : RMO : Creation des méthodes - ajout de documents et de types de document dans la BD
	protected ProductDocument addProductDocument(int state, String title, String link, ProductDocumentType productDocumentType, Product product)
			throws SQLException, ProductDocumentDaoException {
		ProductDocumentDao _productDocumentDao = new ProductDocumentDaoImpl(this.cnxProduct);

		ProductDocument _productDocument = _productDocumentDao.addProductDocument(state, title, link, productDocumentType, product);

		return _productDocument;
	}
	
	protected ProductDocumentType addProductDocumentType(int state, String name)
			throws SQLException, ProductDocumentTypeDaoException {
		ProductDocumentTypeDao _productDocumentTypeDao = new ProductDocumentTypeDaoImpl(this.cnxProduct);

		ProductDocumentType _productDocumentType = _productDocumentTypeDao.addProductDocumentType(state, name);

		return _productDocumentType;
	}
	
	// 20-06-12 : RMO : Creation des méthodes - mise à jour de documents et de types de document dans la BD
	protected ProductDocumentType updateProductDocumentType(ProductDocumentType productDocumentTypeToUpdate)
			throws SQLException, ProductDocumentTypeDaoException {
		ProductDocumentTypeDao _productDocumentTypeDao = new ProductDocumentTypeDaoImpl(this.cnxProduct);

		ProductDocumentType _productDocumentTypeUpdated = _productDocumentTypeDao.updateProductDocumentType(productDocumentTypeToUpdate);
		
		return _productDocumentTypeUpdated;
	}
	
	protected ProductDocument updateProductDocument(ProductDocument productDocumentToUpdate)
			throws SQLException, ProductDocumentDaoException {
		ProductDocumentDao _productDocumentDao = new ProductDocumentDaoImpl(this.cnxProduct);

		ProductDocument _productDocumentUpdated = _productDocumentDao.updateProductDocument(productDocumentToUpdate);
		
		return _productDocumentUpdated;
	}

	// 20-06-12 : RMO : Creation des méthodes - récup des documents et des types de document existants dans la BD
	protected ProductDocumentType getProductDocumentType(String name)
			throws SQLException {
		ProductDocumentTypeDao _productDocumentTypeDao = new ProductDocumentTypeDaoImpl(this.cnxProduct);

		ProductDocumentType _productDocumentType = _productDocumentTypeDao.getProductDocumentType(name);

		return _productDocumentType;
	}

	protected ProductDocumentType getProductDocumentType(int idProductDocumentType) throws SQLException {
		ProductDocumentTypeDao _productDocumentTypeDao = new ProductDocumentTypeDaoImpl(this.cnxProduct);

		ProductDocumentType _productDocumentType = _productDocumentTypeDao.getProductDocumentType(idProductDocumentType);

		return _productDocumentType;
	}

	protected List<ProductDocumentType> getProductDocumentTypes() throws SQLException {
		ProductDocumentTypeDao _productDocumentTypeDao = new ProductDocumentTypeDaoImpl(this.cnxProduct);

		List<ProductDocumentType> _productDocumentTypes = _productDocumentTypeDao.getProductDocumentTypes();

		return _productDocumentTypes;
	}

	protected List<ProductDocumentType> getActiveProductDocumentTypes() throws SQLException {
		ProductDocumentTypeDao _productDocumentTypeDao = new ProductDocumentTypeDaoImpl(this.cnxProduct);

		List<ProductDocumentType> _productDocumentTypes = _productDocumentTypeDao.getActiveProductDocumentTypes();

		return _productDocumentTypes;
	}
	
	protected ProductDocument getProductDocument(String title, String link)
			throws SQLException {
		ProductDocumentDao _productDocumentDao = new ProductDocumentDaoImpl(this.cnxProduct);

		ProductDocument _productDocument = _productDocumentDao.getProductDocument(title, link);

		return _productDocument;
	}

	protected ProductDocument getProductDocument(int idProductDocument) throws SQLException {
		ProductDocumentDao _productDocumentDao = new ProductDocumentDaoImpl(this.cnxProduct);

		ProductDocument _productDocument = _productDocumentDao.getProductDocument(idProductDocument);

		return _productDocument;
	}

	protected List<ProductDocument> getProductDocuments() throws SQLException {
		ProductDocumentDao _productDocumentDao = new ProductDocumentDaoImpl(this.cnxProduct);

		List<ProductDocument> _productDocuments = _productDocumentDao.getProductDocuments();

		return _productDocuments;
	}

	protected List<ProductDocument> getActiveProductDocuments() throws SQLException {
		ProductDocumentDao _productDocumentDao = new ProductDocumentDaoImpl(this.cnxProduct);

		List<ProductDocument> _productDocuments = _productDocumentDao.getActiveProductDocuments();

		return _productDocuments;
	}
	
	protected List<ProductDocument> getProductDocuments(Product product) throws SQLException {
		ProductDocumentDao _productDocumentDao = new ProductDocumentDaoImpl(this.cnxProduct);

		List<ProductDocument> _productDocuments = _productDocumentDao.getProductDocuments(product);

		return _productDocuments;
	}
	
	protected List<ProductDocument> getProductDocuments(Product product, ProductDocumentType productDocumentType) throws SQLException {
		ProductDocumentDao _productDocumentDao = new ProductDocumentDaoImpl(this.cnxProduct);

		List<ProductDocument> _productDocuments = _productDocumentDao.getProductDocuments(product, productDocumentType);

		return _productDocuments;
	}
	
	protected List<ProductDocument> getFEDDProductDocuments(Product product, ProductDocumentType productDocumentType) throws SQLException {
		ProductDocumentDao _productDocumentDao = new ProductDocumentDaoImpl(this.cnxProduct);

		List<ProductDocument> _productDocuments = _productDocumentDao.getFEDDProductDocuments(product, productDocumentType);

		return _productDocuments;
	}
	
}
