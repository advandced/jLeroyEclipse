package fr.la.jproductbase.dao;

import fr.la.jproductbase.metier.Product;
import fr.la.jproductbase.metier.ProductDocument;
import fr.la.jproductbase.metier.ProductDocumentType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class ProductDocumentDaoImpl extends GenericDao implements ProductDocumentDao {

	ConnectionProduct cnxProduct;
	
	ProductDao _productDao;
	ProductDocumentTypeDao _productDocumentTypeDao;

	public ProductDocumentDaoImpl(ConnectionProduct cnxProduct, ProductDao productDao, ProductDocumentTypeDao productDocumentTypeDao) {
		this.cnxProduct = cnxProduct;
		
		_productDao = productDao;
		_productDocumentTypeDao = productDocumentTypeDao;
	}
	
	
	@Override
	public ProductDocument addProductDocument(int state, String title, String link, ProductDocumentType productDocumentType, Product product) {
		ProductDocument _productDocument = null;
		Connection c = null;
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
			c = this.cnxProduct.getCnx();
			_stmt = c.prepareStatement(
					"INSERT INTO productDocumentType (state, title, link, idProductDocumentType, idProduct)"
							+ " VALUES (?, ?, ?, ?, ?)");
			_stmt.setInt(1, state);
			_stmt.setString(2, title);
			_stmt.setString(3, link);
			_stmt.setInt(4, _idProductDocumentType);
			_stmt.setInt(5, _idProduct);
			_stmt.executeUpdate();

			// Retrieve ProductDocumentType data
			_stmt = c.prepareStatement(
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
				throw new IllegalStateException();
			}
		} catch (SQLException e) {
			handleDAOException(e);
		} finally {
			close(_rs);
			close(_stmt);
			close(c);
		}

		return _productDocument;
	}

	@Override
	public ProductDocument updateProductDocument(ProductDocument productDocument) {
		ProductDocument _productDocument = null;
		Connection c = null;
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
			c = this.cnxProduct.getCnx();
			_stmt = c.prepareStatement(
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
			_stmt = c.prepareStatement(
					"SELECT * FROM productDocument WHERE (idProductDocument=?)");
			_stmt.setInt(1, productDocument.getIdProductDocument());

			_rs = _stmt.executeQuery();
			if (_rs.next()) {
				_productDocument = this.getProductDocument(_rs);
			} else {
				throw new IllegalStateException();
			}
		} catch (SQLException e) {
			handleDAOException(e);
		} finally {
			close(_rs);
			close(_stmt);
			close(c);
		}

		return _productDocument;
	}

	@Override
	public ProductDocument getProductDocument(int idProductDocument) {
		ProductDocument _productDocument = null;
		Connection c = null;
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			c = this.cnxProduct.getCnx();
			_stmt = c.prepareStatement("SELECT * FROM productDocument WHERE (idProductDocument=?)");
			_stmt.setInt(1, idProductDocument);
			_rs = _stmt.executeQuery();

			while (_rs.next()) {
				_productDocument = this.getProductDocument(_rs);
			}

		} catch (SQLException e) {
			handleDAOException(e);
		} finally {
			close(_rs);
			close(_stmt);
			close(c);
		}

		return _productDocument;
	}

	@Override
	public ProductDocument getProductDocument(String title, String link) {
		ProductDocument _productDocument = null;
		Connection c = null;
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			c = this.cnxProduct.getCnx();
			_stmt = c.prepareStatement("SELECT * FROM productDocument " + " WHERE (title=?) AND (link=?)");
			_stmt.setString(1, title);
			_stmt.setString(2, link);
			_rs = _stmt.executeQuery();

			while (_rs.next()) {
				_productDocument = this.getProductDocument(_rs);
			}
		} catch (SQLException e) {
			handleDAOException(e);
		} finally {
			close(_rs);
			close(_stmt);
			close(c);
		}

		return _productDocument;
	}

	@Override
	public List<ProductDocument> getProductDocuments(Product product) {
		List<ProductDocument> _productDocuments = new ArrayList<ProductDocument>();
		Connection c = null;
		PreparedStatement _stmt = null;
		ResultSet _rs = null;
		int _idProduct =0;
		if (null != product) {
			_idProduct = product.getIdProduct();
		}

		try {
			c = this.cnxProduct.getCnx();
			_stmt = c.prepareStatement("SELECT * FROM productDocument " + " WHERE (idProduct=?)");
			_stmt.setInt(1, _idProduct);
			_rs = _stmt.executeQuery();

			while (_rs.next()) {
				ProductDocument _productDocument = this.getProductDocument(_rs);
				_productDocuments.add(_productDocument);
			}

		} catch (SQLException e) {
			handleDAOException(e);
		} finally {
			close(_rs);
			close(_stmt);
			close(c);
		}

		return _productDocuments;
	}
	
	@Override
	public List<ProductDocument> getProductDocuments(ProductDocumentType productDocumentType) {
		List<ProductDocument> _productDocuments = new ArrayList<ProductDocument>();
		Connection c = null;
		PreparedStatement _stmt = null;
		ResultSet _rs = null;
		int _idProductDocumentType =0;
		if (null != productDocumentType) {
			_idProductDocumentType = productDocumentType.getIdProductDocumentType();
		}

		try {
			c = this.cnxProduct.getCnx();
			_stmt = c.prepareStatement("SELECT * FROM productDocument " + " WHERE (idProductDocumentType=?)");
			_stmt.setInt(1, _idProductDocumentType);
			_rs = _stmt.executeQuery();

			while (_rs.next()) {
				ProductDocument _productDocument = this.getProductDocument(_rs);
				_productDocuments.add(_productDocument);
			}
		} catch (SQLException e) {
			handleDAOException(e);
		} finally {
			close(_rs);
			close(_stmt);
			close(c);
		}

		return _productDocuments;
	}

	@Override
	public List<ProductDocument> getProductDocuments() {
		List<ProductDocument> _productDocuments = new ArrayList<ProductDocument>();
		Connection c = null;
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			c = this.cnxProduct.getCnx();
			_stmt = c.prepareStatement("SELECT * FROM productDocument ");
			_rs = _stmt.executeQuery();

			while (_rs.next()) {
				ProductDocument _productDocument = this.getProductDocument(_rs);
				_productDocuments.add(_productDocument);
			}
		} catch (SQLException e) {
			handleDAOException(e);
		} finally {
			close(_rs);
			close(_stmt);
			close(c);
		}

		return _productDocuments;
	}

	@Override
	public List<ProductDocument> getActiveProductDocuments() {
		List<ProductDocument> _productDocuments = new ArrayList<ProductDocument>();
		Connection c = null;
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			c = this.cnxProduct.getCnx();
			_stmt = c.prepareStatement("SELECT * FROM productDocument WHERE (state=?)");
			_stmt.setInt(1, 1);
			_rs = _stmt.executeQuery();

			while (_rs.next()) {
				ProductDocument _productDocument = this.getProductDocument(_rs);
				_productDocuments.add(_productDocument);
			}
		} catch (SQLException e) {
			handleDAOException(e);
		} finally {
			close(_rs);
			close(_stmt);
			close(c);
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
		ProductDocumentType _productDocumentType = _productDocumentTypeDao.getProductDocumentType(rs.getInt("idProductDocumentType"));
		Product _product = _productDao.getProduct(rs.getInt("idProduct"));
		ProductDocument _productDocument = new ProductDocument(_idProductDocument, _timestamp, _state, _title, _link, _productDocumentType, _product);

		return _productDocument;
	}


	@Override
	public List<ProductDocument> getProductDocuments(Product product,ProductDocumentType productDocumentType) {
		List<ProductDocument> _productDocuments = new ArrayList<ProductDocument>();
		Connection c = null;
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
			c = this.cnxProduct.getCnx();
			_stmt = c.prepareStatement("SELECT * FROM productDocument " + " WHERE (idProductDocumentType=?) AND (idProduct=?)");
			_stmt.setInt(1, _idProductDocumentType);
			_stmt.setInt(2, _idProduct);
			_rs = _stmt.executeQuery();

			while (_rs.next()) {
				ProductDocument _productDocument = this.getProductDocument(_rs);
				_productDocuments.add(_productDocument);
			}
		} catch (SQLException e) {
			handleDAOException(e);
		} finally {
			close(_rs);
			close(_stmt);
			close(c);
		}

		return _productDocuments;
	}
	
	@Override
	public List<ProductDocument> getFEDDProductDocuments(Product product,ProductDocumentType productDocumentType) {
		List<ProductDocument> _productDocuments = new ArrayList<ProductDocument>();
		Connection c = null;
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
			c = this.cnxProduct.getCnx();
			_stmt = c.prepareStatement("SELECT * FROM `FEDDproductBase`.`productDocument` WHERE (idProductDocumentType=?) AND (idProduct=?)");
			_stmt.setInt(1, _idProductDocumentType);
			_stmt.setInt(2, _idProduct);
			_rs = _stmt.executeQuery();

			while (_rs.next()) {
				ProductDocument _productDocument = this.getProductDocument(_rs);
				_productDocuments.add(_productDocument);
			}
		} catch (SQLException e) {
			handleDAOException(e);
		} finally {
			close(_rs);
			close(_stmt);
			close(c);
		}

		return _productDocuments;
	}
	
}
