package fr.la.jproductbase.dao;

import fr.la.jproductbase.metier.Product;
import fr.la.jproductbase.metier.ProductDocument;
import fr.la.jproductbase.metier.ProductDocumentType;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;

public class ProductDocumentDaoImpl implements ProductDocumentDao {
	private static String exceptionMsg = "Document produit inconnu dans la base de données.";

	private ConnectionProduct cnxProduct;

	public ProductDocumentDaoImpl(ConnectionProduct cnxProduct) {
		this.cnxProduct = cnxProduct;
	}
	
	
	@Override
	public ProductDocument addProductDocument(int state, String title, String link, ProductDocumentType productDocumentType, Product product)
			throws SQLException, ProductDocumentDaoException {
		ProductDocument _productDocument = null;
		PreparedStatement _stmt = null;
		ResultSet _rs = null;
		int _idProductDocumentType = 0;
		int _idProduct = 0;
		if (null != productDocumentType) {
			_idProductDocumentType = productDocumentType.getIdProductDocumentType();
		}
		if (null != product) {
			_idProduct = product.getIdProduct();
		}

		try {
			_stmt = this.cnxProduct.getCnx().prepareStatement(
					"INSERT INTO productDocumentType (state, title, link, idProductDocumentType, idProduct)"
							+ " VALUES (?, ?, ?, ?, ?)");
			_stmt.setInt(1, state);
			_stmt.setString(2, title);
			_stmt.setString(3, link);
			_stmt.setInt(4, _idProductDocumentType);
			_stmt.setInt(5, _idProduct);
			_stmt.executeUpdate();

			// Retrieve ProductDocumentType data
			_stmt = this.cnxProduct.getCnx().prepareStatement(
					"SELECT * FROM productDocument" + " WHERE (title=?) " +
							" AND (link=?) AND (idProductDocumentType=?) AND (idProduct=?)");
			_stmt.setString(1, title);
			_stmt.setString(2, link);
			_stmt.setInt(3, _idProductDocumentType);
			_stmt.setInt(4, _idProduct);

			_rs = _stmt.executeQuery();
			if (_rs.next()) {
				_productDocument = this.getProductDocument(_rs);
			} else {
				throw new ProductDocumentDaoException(exceptionMsg);
			}
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (null != _rs) {
				_rs.close();
			}
			if (null != _stmt) {
				_stmt.close();
			}
		}

		return _productDocument;
	}

	@Override
	public ProductDocument updateProductDocument(
			ProductDocument productDocument) throws SQLException,
			ProductDocumentDaoException {
		ProductDocument _productDocument = null;
		PreparedStatement _stmt = null;
		ResultSet _rs = null;
		int _idProductDocumentType = 0;
		int _idProduct = 0;
		if (null != productDocument.getDocumentType()) {
			_idProductDocumentType = productDocument.getDocumentType().getIdProductDocumentType();
		}
		if (null != productDocument.getProduct()) {
			_idProduct = productDocument.getProduct().getIdProduct();
		}

		try {
			_stmt = this.cnxProduct.getCnx().prepareStatement(
					"UPDATE productDocument "
							+ "SET state=?, title=?, link=?,"
							+ " idProductDocumentType=?, idProduct=?"
							+ " WHERE (idProductDocumentType=?)");
			_stmt.setInt(1, productDocument.getState());
			_stmt.setString(2, productDocument.getTitle());
			_stmt.setString(3, productDocument.getLink());
			_stmt.setInt(4, _idProductDocumentType);
			_stmt.setInt(5, _idProduct);
			_stmt.setInt(6, productDocument.getIdProductDocument());
			_stmt.executeUpdate();
			
			// Retrieve ProductDocument data
			_stmt = this.cnxProduct.getCnx().prepareStatement(
					"SELECT * FROM productDocument" + " WHERE (idProductDocument=?)");
			_stmt.setInt(1, productDocument.getIdProductDocument());

			_rs = _stmt.executeQuery();
			if (_rs.next()) {
				_productDocument = this.getProductDocument(_rs);
			} else {
				throw new ProductDocumentDaoException(exceptionMsg);
			}
						
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (null != _stmt) {
				_stmt.close();
			}
		}

		return _productDocument;
	}

	@Override
	public ProductDocument getProductDocument(int idProductDocument)
			throws SQLException {
		ProductDocument _productDocument = null;
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			_stmt = this.cnxProduct.getCnx().prepareStatement(
					"SELECT * FROM productDocument " + " WHERE (idProductDocument=?)");
			_stmt.setInt(1, idProductDocument);
			_rs = _stmt.executeQuery();

			while (_rs.next()) {
				_productDocument = this.getProductDocument(_rs);
			}
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (null != _rs) {
				_rs.close();
			}
			if (null != _stmt) {
				_stmt.close();
			}
		}

		return _productDocument;
	}

	@Override
	public ProductDocument getProductDocument(String title, String link)
			throws SQLException {
		ProductDocument _productDocument = null;
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			_stmt = this.cnxProduct.getCnx().prepareStatement(
					"SELECT * FROM productDocument " + " WHERE (title=?) AND (link=?)");
			_stmt.setString(1, title);
			_stmt.setString(2, link);
			_rs = _stmt.executeQuery();

			while (_rs.next()) {
				_productDocument = this.getProductDocument(_rs);
			}
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (null != _rs) {
				_rs.close();
			}
			if (null != _stmt) {
				_stmt.close();
			}
		}

		return _productDocument;
	}

	@Override
	public List<ProductDocument> getProductDocuments(Product product)
			throws SQLException {
		List<ProductDocument> _productDocuments = new ArrayList<ProductDocument>();
		PreparedStatement _stmt = null;
		ResultSet _rs = null;
		int _idProduct =0;
		if (null != product) {
			_idProduct = product.getIdProduct();
		}

		try {
			_stmt = this.cnxProduct.getCnx().prepareStatement(
					"SELECT * FROM productDocument " + " WHERE (idProduct=?)");
			_stmt.setInt(1, _idProduct);
			_rs = _stmt.executeQuery();

			while (_rs.next()) {
				ProductDocument _productDocument = this.getProductDocument(_rs);
				_productDocuments.add(_productDocument);
			}
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (null != _rs) {
				_rs.close();
			}
			if (null != _stmt) {
				_stmt.close();
			}
		}

		return _productDocuments;
	}
	
	@Override
	public List<ProductDocument> getProductDocuments(ProductDocumentType productDocumentType)
			throws SQLException {
		List<ProductDocument> _productDocuments = new ArrayList<ProductDocument>();
		PreparedStatement _stmt = null;
		ResultSet _rs = null;
		int _idProductDocumentType =0;
		if (null != productDocumentType) {
			_idProductDocumentType = productDocumentType.getIdProductDocumentType();
		}

		try {
			_stmt = this.cnxProduct.getCnx().prepareStatement(
					"SELECT * FROM productDocument " + " WHERE (idProductDocumentType=?)");
			_stmt.setInt(1, _idProductDocumentType);
			_rs = _stmt.executeQuery();

			while (_rs.next()) {
				ProductDocument _productDocument = this.getProductDocument(_rs);
				_productDocuments.add(_productDocument);
			}
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (null != _rs) {
				_rs.close();
			}
			if (null != _stmt) {
				_stmt.close();
			}
		}

		return _productDocuments;
	}

	@Override
	public List<ProductDocument> getProductDocuments()
			throws SQLException {
		List<ProductDocument> _productDocuments = new ArrayList<ProductDocument>();
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			_stmt = this.cnxProduct.getCnx().prepareStatement(
					"SELECT * FROM productDocument ");
			_rs = _stmt.executeQuery();

			while (_rs.next()) {
				ProductDocument _productDocument = this.getProductDocument(_rs);
				_productDocuments.add(_productDocument);
			}
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (null != _rs) {
				_rs.close();
			}
			if (null != _stmt) {
				_stmt.close();
			}
		}

		return _productDocuments;
	}

	@Override
	public List<ProductDocument> getActiveProductDocuments()
			throws SQLException {
		List<ProductDocument> _productDocuments = new ArrayList<ProductDocument>();
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			_stmt = this.cnxProduct.getCnx().prepareStatement(
					"SELECT * FROM productDocument " + " WHERE (state=?)");
			_stmt.setInt(1, 1);
			_rs = _stmt.executeQuery();

			while (_rs.next()) {
				ProductDocument _productDocument = this.getProductDocument(_rs);
				_productDocuments.add(_productDocument);
			}
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (null != _rs) {
				_rs.close();
			}
			if (null != _stmt) {
				_stmt.close();
			}
		}

		return _productDocuments;
	}
	
	/*
	 * Cr&eacute;er un document &agrave; partir d'un enregistrement de la base
	 * de donn&eacute;es.
	 * 
	 * @param rs Enregistrement de la base de donn&eacute;es.
	 * 
	 * @return Document.
	 * 
	 * @throws SQLException
	 */
	private ProductDocument getProductDocument(ResultSet rs) throws SQLException {
		// ProductDocument
		int _idProductDocument = rs.getInt("idProductDocument");
		Timestamp _timestamp = rs.getTimestamp("timestamp");
		int _state = rs.getInt("state");
		String _title = rs.getString("title");
		String _link = rs.getString("link");
		/*int _idProductDocumentType = rs.getInt("idProductDocumentType");
		int _idProduct = rs.getInt("idProduct");*/
		ProductDocumentTypeDao _productDocumentTypeDao = new ProductDocumentTypeDaoImpl(this.cnxProduct);
		ProductDocumentType _productDocumentType = _productDocumentTypeDao.getProductDocumentType(rs.getInt("idProductDocumentType"));
		ProductDao _productDao = new ProductDaoImpl(this.cnxProduct);
		Product _product = _productDao.getProduct(rs.getInt("idProduct"));
		
		ProductDocument _productDocument = new ProductDocument(_idProductDocument, _timestamp, _state,
				_title, _link, _productDocumentType, _product);

		return _productDocument;
	}


	@Override
	public List<ProductDocument> getProductDocuments(Product product,
			ProductDocumentType productDocumentType) throws SQLException {
		List<ProductDocument> _productDocuments = new ArrayList<ProductDocument>();
		PreparedStatement _stmt = null;
		ResultSet _rs = null;
		int _idProductDocumentType =0;
		if (null != productDocumentType) {
			_idProductDocumentType = productDocumentType.getIdProductDocumentType();
		}
		int _idProduct =0;
		if (null != product) {
			_idProduct = product.getIdProduct();
		}

		try {
			_stmt = this.cnxProduct.getCnx().prepareStatement(
					"SELECT * FROM productDocument " + " WHERE (idProductDocumentType=?) AND (idProduct=?)");
			_stmt.setInt(1, _idProductDocumentType);
			_stmt.setInt(2, _idProduct);
			_rs = _stmt.executeQuery();

			while (_rs.next()) {
				ProductDocument _productDocument = this.getProductDocument(_rs);
				_productDocuments.add(_productDocument);
			}
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (null != _rs) {
				_rs.close();
			}
			if (null != _stmt) {
				_stmt.close();
			}
		}

		return _productDocuments;
	}
	
	@Override
	public List<ProductDocument> getFEDDProductDocuments(Product product,
			ProductDocumentType productDocumentType) throws SQLException {
		List<ProductDocument> _productDocuments = new ArrayList<ProductDocument>();
		PreparedStatement _stmt = null;
		ResultSet _rs = null;
		int _idProductDocumentType =0;
		if (null != productDocumentType) {
			_idProductDocumentType = productDocumentType.getIdProductDocumentType();
		}
		int _idProduct =0;
		if (null != product) {
			_idProduct = product.getIdProduct();
		}

		try {
			_stmt = this.cnxProduct.getCnx().prepareStatement(
					"SELECT * FROM `FEDDproductBase`.`productDocument` " + " WHERE (idProductDocumentType=?) AND (idProduct=?)");
			_stmt.setInt(1, _idProductDocumentType);
			_stmt.setInt(2, _idProduct);
			_rs = _stmt.executeQuery();

			while (_rs.next()) {
				ProductDocument _productDocument = this.getProductDocument(_rs);
				_productDocuments.add(_productDocument);
			}
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (null != _rs) {
				_rs.close();
			}
			if (null != _stmt) {
				_stmt.close();
			}
		}

		return _productDocuments;
	}
	
}
