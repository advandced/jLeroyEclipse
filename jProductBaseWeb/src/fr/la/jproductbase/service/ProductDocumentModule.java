package fr.la.jproductbase.service;

import java.util.List;

import fr.la.jproductbase.dao.ProductDocumentDao;
import fr.la.jproductbase.dao.ProductDocumentTypeDao;
import fr.la.jproductbase.metier.Product;
import fr.la.jproductbase.metier.ProductDocument;
import fr.la.jproductbase.metier.ProductDocumentType;

public class ProductDocumentModule {
	
	ProductDocumentDao _productDocumentDao;
	ProductDocumentTypeDao _productDocumentTypeDao;

	public ProductDocumentModule(ProductDocumentDao productDocumentDao, ProductDocumentTypeDao productDocumentTypeDao) {
		_productDocumentDao = productDocumentDao;
		_productDocumentTypeDao = productDocumentTypeDao;
	}

	// 20-06-12 : RMO : Creation des méthodes - ajout de documents et de types de document dans la BD
	public ProductDocument addProductDocument(int state, String title, String link, ProductDocumentType productDocumentType, Product product) {
		return _productDocumentDao.addProductDocument(state, title, link, productDocumentType, product);
	}
	
	public ProductDocumentType addProductDocumentType(int state, String name) {
		return _productDocumentTypeDao.addProductDocumentType(state, name);
	}
	
	// 20-06-12 : RMO : Creation des méthodes - mise à jour de documents et de types de document dans la BD
	public ProductDocumentType updateProductDocumentType(ProductDocumentType productDocumentTypeToUpdate) {
		return _productDocumentTypeDao.updateProductDocumentType(productDocumentTypeToUpdate);
	}
	
	public ProductDocument updateProductDocument(ProductDocument productDocumentToUpdate) {
		return _productDocumentDao.updateProductDocument(productDocumentToUpdate);
	}

	// 20-06-12 : RMO : Creation des méthodes - récup des documents et des types de document existants dans la BD
	public ProductDocumentType getProductDocumentType(String name) {
		return _productDocumentTypeDao.getProductDocumentType(name);
	}

	public ProductDocumentType getProductDocumentType(int idProductDocumentType) {
		return _productDocumentTypeDao.getProductDocumentType(idProductDocumentType);
	}

	public List<ProductDocumentType> getProductDocumentTypes() {
		return _productDocumentTypeDao.getProductDocumentTypes();
	}

	public List<ProductDocumentType> getActiveProductDocumentTypes() {
		return _productDocumentTypeDao.getActiveProductDocumentTypes();
	}
	
	public ProductDocument getProductDocument(String title, String link) {
		return _productDocumentDao.getProductDocument(title, link);
	}

	public ProductDocument getProductDocument(int idProductDocument) {
		return _productDocumentDao.getProductDocument(idProductDocument);
	}

	public List<ProductDocument> getProductDocuments() {
		return _productDocumentDao.getProductDocuments();
	}

	public List<ProductDocument> getActiveProductDocuments() {
		return _productDocumentDao.getActiveProductDocuments();
	}
	
	public List<ProductDocument> getProductDocuments(Product product) {
		return _productDocumentDao.getProductDocuments(product);
	}
	
	public List<ProductDocument> getProductDocuments(Product product, ProductDocumentType productDocumentType) {
		return _productDocumentDao.getProductDocuments(product, productDocumentType);
	}
	
	public List<ProductDocument> getFEDDProductDocuments(Product product, ProductDocumentType productDocumentType) {
		return _productDocumentDao.getFEDDProductDocuments(product, productDocumentType);
	}
	
}
