package fr.la.jproductbase.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import fr.la.jproductbase.metier.Product;
import fr.la.jproductbase.metier.ProductConf;
import fr.la.jproductbase.metier.ProductConfModel;
import fr.la.jproductbase.metier.ProductType;

public class ProductDaoImpl extends GenericDao implements ProductDao {

	ConnectionProduct cnxProduct;
	ConnectionInfoSchema cnxInfoSchema;

	ProductConfDao _productConfDao;
	SoftwareDao _softwareDao;
	
	public ProductDaoImpl(ConnectionProduct cnxProduct, ConnectionInfoSchema cnxInfoSchema, ProductConfDao productConfDao, SoftwareDao softwareDao) {
		this.cnxProduct = cnxProduct;
		this.cnxInfoSchema = cnxInfoSchema;
		
		_productConfDao = productConfDao;
		_softwareDao = softwareDao;
	}

	@Override
	public Product getProduct(int idProduct) {
		Product _product = null;
		Connection c = null;
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			c = this.cnxProduct.getCnx();
			_stmt = c.prepareStatement(
					"SELECT * FROM product WHERE idProduct=?");
			_stmt.setInt(1, idProduct);
			_rs = _stmt.executeQuery();

			if (_rs.next()) {
				_product = this.getProduct(_rs);
			} 
			
		} catch (SQLException e) {
			handleDAOException(e);
		} finally {
			close(_rs);
			close(_stmt);
			close(c);
		}

		return _product;
	}

	@Override
	public Product getProduct(ProductConf productConf, String serialNumber,	String datecode)  {
		Product _product = null;
		Connection c = null;
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		int idConf = 0;
		if (null != productConf) {
			idConf = productConf.getIdProductConf();
		}

		try {
			c = this.cnxProduct.getCnx();
			_stmt = c.prepareStatement(
					"SELECT * FROM product" + " WHERE (idProductConf=?)"
							+ " AND (serialNumber=?)" + " AND (datecode=?)");
			_stmt.setInt(1, idConf);
			_stmt.setString(2, serialNumber);
			_stmt.setString(3, datecode);
			_rs = _stmt.executeQuery();
			if (_rs.next()) {
				_product = this.getProduct(_rs);
			} 
			
		} catch (SQLException e) {
			handleDAOException(e);
		} finally {
			close(_rs);
			close(_stmt);
			close(c);
		}

		return _product;
	}

	@Override
	public Product getProduct(String productConfReference, String serialNumber,	String datecode) {
		Product _product = null;
		Connection c = null;
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			c = this.cnxProduct.getCnx();
			_stmt = c.prepareStatement(
							"SELECT * FROM product "
									+ "INNER JOIN productConf ON (product.idProductConf = productConf.idProductConf) "
									+ "WHERE (productConf.reference=?) AND (product.serialNumber=?) AND (product.datecode=?)");
			_stmt.setString(1, productConfReference);
			_stmt.setString(2, serialNumber);
			_stmt.setString(3, datecode);
			_rs = _stmt.executeQuery();
			if (_rs.next()) {
				_product = this.getProduct(_rs);
			}
			
		} catch (SQLException e) {
			handleDAOException(e);
		} finally {
			close(_rs);
			close(_stmt);
			close(c);
		}

		return _product;
	}

	@Override
	public Product getMainProduct(Product carte) {
		Product _product = null;
		Connection c = null;
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			c = this.cnxProduct.getCnx();
			_stmt = c.prepareStatement(
					"SELECT p.* FROM product p, product c, product_product pp"
							+ " WHERE (c.idProduct=?)"
							+ " AND (p.idProduct = pp.idProduct)"
							+ " AND (pp.idProductComponent = c.idProduct)");
			_stmt.setInt(1, carte.getIdProduct());
			_rs = _stmt.executeQuery();
			if (_rs.next()) {
				_product = this.getProduct(_rs);
			}		
		} catch (SQLException e) {
			handleDAOException(e);
		} finally {
			close(_rs);
			close(_stmt);
			close(c);
		}

		return _product;
	}

	@Override
	public List<Product> getProducts() {
		List<Product> _products = new ArrayList<Product>();
		Connection c = null;
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			c = this.cnxProduct.getCnx();
			_stmt = c.prepareStatement("SELECT * FROM product");
			_rs = _stmt.executeQuery();

			while (_rs.next()) {
				Product _product = this.getProduct(_rs);
				_products.add(_product);
			}

		} catch (SQLException e) {
			handleDAOException(e);
		} finally {
			close(_rs);
			close(_stmt);
			close(c);
		}

		return _products;
	}

	@Override
	public List<Product> getProducts(ProductType productType) {
		List<Product> _products = new ArrayList<Product>();
		Connection c = null;
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			c = this.cnxProduct.getCnx();
			_stmt = c.prepareStatement(
							"SELECT * FROM product, productConf, productFamily, productType"
									+ " WHERE (product.idProductConf=productConf.idProductConf)"
									+ " AND (productConf.idProductFamily=productFamily.idProductFamily)"
									+ " AND (productFamily.idProductType=productType.idProductType)"
									+ " AND (productType.idProductType=?)");
			_stmt.setInt(1, productType.getIdProductType());
			_rs = _stmt.executeQuery();

			while (_rs.next()) {
				Product _product = this.getProduct(_rs);
				_products.add(_product);
			}
		} catch (SQLException e) {
			handleDAOException(e);
		} finally {
			close(_rs);
			close(_stmt);
			close(c);
		}

		return _products;
	}

	@Override
	public List<Product> getProducts(ProductConfModel productConfModel) {
		List<Product> _products = new ArrayList<Product>();
		Connection c = null;
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			c = this.cnxProduct.getCnx();
			_stmt = c.prepareStatement(
							"SELECT * FROM product, productConf, productConfModel"
									+ " WHERE (product.idProductConf=productConf.idProductConf)"
									+ " AND (productConf.idProductConfModel=productConfModel.idProductConfModel)"
									+ " AND (productConfModel.idProductConfModel=?)");
			_stmt.setInt(1, productConfModel.getIdProductConfModel());
			_rs = _stmt.executeQuery();

			while (_rs.next()) {
				Product _product = this.getProduct(_rs);
				_products.add(_product);
			}
		} catch (SQLException e) {
			handleDAOException(e);
		} finally {
			close(_rs);
			close(_stmt);
			close(c);
		}

		return _products;
	}

	@Override
	public List<Product> getProducts(ProductConf productConf) {
		List<Product> _products = new ArrayList<Product>();
		Connection c = null;
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			c = this.cnxProduct.getCnx();
			_stmt = c.prepareStatement(
							"SELECT * FROM product, productConf"
									+ " WHERE (product.idProductConf=productConf.idProductConf)"
									+ " AND (productConf.idProductConf=?)");
			_stmt.setInt(1, productConf.getIdProductConf());
			_rs = _stmt.executeQuery();

			while (_rs.next()) {
				Product _product = this.getProduct(_rs);
				_products.add(_product);
			}
		} catch (SQLException e) {
			handleDAOException(e);
		} finally {
			close(_rs);
			close(_stmt);
			close(c);
		}

		return _products;
	}

	@Override
	public List<Product> getProductsEnables(int idproduct, String reference) {
		List<Product> _products = new ArrayList<Product>();
		Connection c = null;
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		String _requete = "SELECT p2.* FROM product p2 WHERE p2.idProductConf in (50, 51, 52) "
				+ "UNION "
				+ "SELECT p3.* FROM product p3, product_product pp2 "
				+ "WHERE p3.idProduct = pp2.idProductComponent "
				+ "AND pp2.idProduct = ? "
				+ "UNION "
				+ "SELECT p.* FROM product p, productConf pc, productFamily pf "
				+ "WHERE p.idProduct not in (select pp.idProductComponent from product_product pp) "
				+ "AND p.idProductConf = pc.idProductConf "
				+ "AND pc.idProductFamily = pf.idProductFamily "
				+ "AND pf.idProductType = 1 ";
		if (null != reference && !reference.equals("")) {
			_requete = _requete + "AND pc.reference = ?";
		}

		try {
			c = this.cnxProduct.getCnx();
			_stmt = c.prepareStatement(_requete);
			_stmt.setInt(1, idproduct);
			if (null != reference && !reference.equals("")) {
				_stmt.setInt(2, idproduct);
			}
			_rs = _stmt.executeQuery();

			while (_rs.next()) {
				Product _product = this.getProduct(_rs);
				_products.add(_product);
			}

		} catch (SQLException e) {
			handleDAOException(e);
		} finally {
			close(_rs);
			close(_stmt);
			close(c);
		}

		return _products;
	}

	@Override
	public List<Product> getProductsRecordables(String modele) {
		List<Product> _products = new ArrayList<Product>();
		Connection c = null;
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		String _requete = "SELECT ap.* "
				+ "FROM `FEDDproductBase`.`product` ap, `FEDDproductBase`.`productConf` apc, "
				+ "`FEDDproductBase`.`productConfModel` apcf, `FEDDproductBase`.`productFamily` apf,  "
				+ "`FEDDtesterBase`.`testerReport` btr  "
				+ "WHERE ap.idProductConf = apc.idProductConf AND apc.idProductFamily = apf.idProductFamily AND apf.idProductType = 2  "
				+ "AND apc.idProductconfModel = apcf.idProductConfModel AND ap.idProduct = btr.idProduct  "
				+ "AND btr.idTestType = 8 AND btr.result = 'Passed'  "
				+ "AND (ap.serialNumber, ap.dateCode, apcf.reference) NOT IN (  "
				+ "SELECT p2.serialNumber, p2.dateCode, pcf2.reference "
				+ "FROM `productBase`.`product` p2, `productBase`.`productConf` pc2, `productBase`.`productConfModel` pcf2 "
				+ "WHERE p2.idProductConf = pc2.idProductConf AND pc2.idProductConfModel = pcf2.idProductConfModel) "
				+ "ORDER BY apcf.reference;";
		/*
		 * String _requete =
		 * "SELECT p2.* FROM product p2 WHERE p2.idProductConf in (50, 51, 52) "
		 * + "UNION " +
		 * "SELECT p.* FROM product p, productConf pc, productFamily pf, productConfModel pcm "
		 * +
		 * "WHERE p.idProduct not in (select pp.idProductComponent from product_product pp) "
		 * + "AND p.idProductConf = pc.idProductConf " +
		 * "AND pc.idProductFamily = pf.idProductFamily " +
		 * "AND pc.idProductConfModel = pcm.idProductConfModel " +
		 * "AND pf.idProductType = 1 "; if (null != modele &&
		 * !modele.equals("")) { _requete = _requete + "AND pcm.reference = ?";
		 * }
		 */

		try {
			c = this.cnxProduct.getCnx();
			_stmt = c.prepareStatement(_requete);
			_rs = _stmt.executeQuery();

			while (_rs.next()) {
				Product _product = this.getProduct(_rs);
				_products.add(_product);
			}
		} catch (SQLException e) {
			handleDAOException(e);
		} finally {
			close(_rs);
			close(_stmt);
			close(c);
		}

		return _products;
	}

	@Override
	public List<Product> getProductsSearch(int startingAt, int maxPerPage,	Map<String, String> filters, int type) {
		
		List<Product> _products = new ArrayList<Product>();
		Connection c = null;
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		String rqt = "SELECT * FROM product p , productConf pc, productFamily pf, productConfModel pcm "
				+ "WHERE p.idProductConf = pc.idProductConf AND pc.idProductFamily = pf.idProductFamily AND pc.idProductConfModel = pcm.idProductConfModel ";
		if (filters.containsKey("productConf.productConfModel.reference") == true) {
			rqt += "AND pcm.reference LIKE '%"
					+ filters.get("productConf.productConfModel.reference")
					+ "%' ";
		}
		if (filters.containsKey("productConf.productFamily.name") == true) {
			rqt += "AND pf.name LIKE  '%"
					+ filters.get("productConf.productFamily.name") + "%' ";
		}
		if (filters.containsKey("productConf.reference") == true) {
			rqt += "AND pc.reference LIKE '%"
					+ filters.get("productConf.reference") + "%' ";
		}
		if (filters.containsKey("productConf.majorIndex") == true) {
			rqt += "AND pc.majorindex LIKE '%"
					+ filters.get("productConf.majorIndex") + "%' ";
		}
		if (filters.containsKey("productConf.minorIndex") == true) {
			rqt += "AND pc.minorIndex LIKE '%"
					+ filters.get("productConf.minorIndex") + "%' ";
		}
		if (filters.containsKey("datecode") == true) {
			rqt += "AND p.datecode LIKE '%" + filters.get("datecode") + "%' ";
		}
		if (filters.containsKey("serialNumber") == true) {
			rqt += "AND p.serialNumber LIKE '%" + filters.get("serialNumber")
					+ "%' ";
		}
		if (filters.containsKey("state") == true) {
			rqt += "AND p.state LIKE '%" + filters.get("state") + "%' ";
		}
		if (filters.containsKey("macAddress") == true) {
			rqt += "AND p.macAddress LIKE '%" + filters.get("macAddress")
					+ "%' ";
		}
		if (filters.containsKey("providerCode") == true) {
			rqt += "AND p.providerCode LIKE '%" + filters.get("providerCode")
					+ "%' ";
		}
		if (type != 0) {
			rqt += "AND pf.idProductType = '" + type + "' ";
		}
		
		try {
			c = this.cnxProduct.getCnx();
			_stmt = c.prepareStatement(rqt + " LIMIT ?, ?;");
			_stmt.setInt(1, startingAt);
			_stmt.setInt(2, maxPerPage);
			_rs = _stmt.executeQuery();

			while (_rs.next()) {
				Product _product = this.getProduct(_rs);
				_products.add(_product);
			}
		} catch (SQLException e) {
			handleDAOException(e);
		} finally {
			close(_rs);
			close(_stmt);
			close(c);
		}
		return _products;
	}

	@Override
	public int countProducts(Map<String, String> filters, int type) {
		Connection c = null;
		PreparedStatement _stmt = null;
		ResultSet _rs = null;
		int count = 0;
		String rqt = "SELECT COUNT(*) FROM product p , productConf pc, productFamily pf, productConfModel pcm "
				+ "WHERE p.idProductConf = pc.idProductConf AND pc.idProductFamily = pf.idProductFamily AND pc.idProductConfModel = pcm.idProductConfModel ";
		if (filters.containsKey("productConf.productConfModel.reference") == true) {
			rqt += "AND pcm.reference LIKE '%"
					+ filters.get("productConf.productConfModel.reference")
					+ "%' ";
		}
		if (filters.containsKey("productConf.productFamily.name") == true) {
			rqt += "AND pf.name LIKE  '%"
					+ filters.get("productConf.productFamily.name") + "%' ";
		}
		if (filters.containsKey("productConf.reference") == true) {
			rqt += "AND pc.reference LIKE '%"
					+ filters.get("productConf.reference") + "%' ";
		}
		if (filters.containsKey("productConf.majorIndex") == true) {
			rqt += "AND pc.majorindex LIKE '%"
					+ filters.get("productConf.majorIndex") + "%' ";
		}
		if (filters.containsKey("productConf.minorIndex") == true) {
			rqt += "AND pc.minorIndex LIKE '%"
					+ filters.get("productConf.minorIndex") + "%' ";
		}
		if (filters.containsKey("datecode") == true) {
			rqt += "AND p.datecode LIKE '%" + filters.get("datecode") + "%' ";
		}
		if (filters.containsKey("serialNumber") == true) {
			rqt += "AND p.serialNumber LIKE '%" + filters.get("serialNumber")
					+ "%' ";
		}
		if (filters.containsKey("state") == true) {
			rqt += "AND p.state LIKE '%" + filters.get("state") + "%' ";
		}
		if (filters.containsKey("macAddress") == true) {
			rqt += "AND p.macAddress LIKE '%" + filters.get("macAddress")
					+ "%' ";
		}
		if (filters.containsKey("providerCode") == true) {
			rqt += "AND p.providerCode LIKE '%" + filters.get("providerCode")
					+ "%' ";
		}
		if (type != 0) {
			rqt += "AND pf.idProductType = '" + type + "' ";
		}
		try {
			c = this.cnxProduct.getCnx();
			_stmt = c.prepareStatement(rqt + ";");
			_rs = _stmt.executeQuery();

			while (_rs.next()) {
				count = _rs.getInt(1);
			}
		} catch (SQLException e) {
			handleDAOException(e);
		} finally {
			close(_rs);
			close(_stmt);
			close(c);
		}
		return count;
	}

	@Override
	public List<Product> getProducts(String serialNumber, String datecode) {
		List<Product> _products = new ArrayList<Product>();
		Connection c = null;
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			c = this.cnxProduct.getCnx();
			_stmt = c.prepareStatement("SELECT * FROM product WHERE (serialNumber=?) AND (datecode=?)");
			_stmt.setString(1, serialNumber);
			_stmt.setString(2, datecode);
			_rs = _stmt.executeQuery();

			while (_rs.next()) {
				Product _product = this.getProduct(_rs);
				_products.add(_product);
			}
		} catch (SQLException e) {
			handleDAOException(e);
		} finally {
			close(_rs);
			close(_stmt);
			close(c);
		}

		return _products;
	}

	@Override
	public boolean isNeedDispensation(Product product) {
		boolean _isNeedDispensation = false;

		if (null != product) {
			Connection c = null;
			PreparedStatement _stmt = null;
			ResultSet _rs = null;

			try {
				c = this.cnxProduct.getCnx();
				_stmt = c.prepareStatement(
								"SELECT * FROM productBase.product AS PR"
										+ " WHERE (0=(SELECT count(idTesterReport)"
										+ " FROM testerBase.testerReport AS TR, testerBase.testType AS TT"
										+ " WHERE (TR.state=1)"
										+ " AND (TR.idProduct=PR.idProduct)"
										+ " AND (TT.idTestType=TR.idTestType)"
										+ " AND (TT.name='ContrÃ´le final')"
										+ " AND (TR.result='Passed')))"
										+ " AND (3<(SELECT count(idTesterReport)"
										+ " FROM testerBase.testerReport AS TR, testerBase.testType AS TT"
										+ " WHERE (TR.state=1)"
										+ " AND (TR.idProduct=PR.idProduct)"
										+ " AND (TT.idTestType=TR.idTestType)"
										+ " AND (TT.name='Examen visuel')))"
										+ " AND (idProduct=?)");
				_stmt.setInt(1, product.getIdProduct());
				_rs = _stmt.executeQuery();
				if (_rs.next()) {
					_isNeedDispensation = true;
				} else {
					_isNeedDispensation = false;
				}
			} catch (SQLException e) {
				handleDAOException(e);
			} finally {
				close(_rs);
				close(_stmt);
				close(c);
			}
		}

		return _isNeedDispensation;
	}

	@Override
	public List<Product> getProductsDispensationNeeded() {
		List<Product> _products = new ArrayList<Product>();
		Connection c = null;
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			c = this.cnxProduct.getCnx();
			_stmt = c.prepareStatement(
							"SELECT * FROM productBase.product AS PR"
									+ " WHERE (0=(SELECT count(idTesterReport)"
									+ " FROM testerBase.testerReport AS TR, testerBase.testType AS TT"
									+ " WHERE (TR.state=1)"
									+ " AND (TR.idProduct=PR.idProduct)"
									+ " AND (TT.idTestType=TR.idTestType)"
									+ " AND (TT.name='ContrÃ´le final')"
									+ " AND (TR.result='Passed')))"
									+ " AND (0<(SELECT count(idTesterReport)"
									+ " FROM testerBase.testerReport AS TR, testerBase.testType AS TT"
									+ " WHERE (TR.state=1)"
									+ " AND (TR.idProduct=PR.idProduct)"
									+ " AND (TT.idTestType=TR.idTestType)"
									+ " AND (TT.name='Test fonctionnel 2')"
									+ " AND (TR.result='Passed')))"
									+ " AND (3<(SELECT count(idTesterReport)"
									+ " FROM testerBase.testerReport AS TR, testerBase.testType AS TT"
									+ " WHERE (TR.state=1)"
									+ " AND (TR.idProduct=PR.idProduct)"
									+ " AND (TT.idTestType=TR.idTestType)"
									+ " AND (TT.name='Examen visuel')))");
			_rs = _stmt.executeQuery();

			while (_rs.next()) {
				Product _product = this.getProduct(_rs);
				_products.add(_product);
			}
		} catch (SQLException e) {
			handleDAOException(e);
		} finally {
			close(_rs);
			close(_stmt);
			close(c);
		}

		return _products;
	}

	@Override
	public Product addProduct(ProductConf productConf, String serialNumber,	String datecode, String macAddress, String providerCode) {
		Product _product = null;
		Connection c = null;
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		if (productConf == null) 
			throw new IllegalArgumentException();
		
		try {
			c = this.cnxProduct.getCnx();
			_stmt = c.prepareStatement(
							"INSERT INTO product (state, serialNumber, datecode, macAddress, providerCode, idProductConf)"
									+ " VALUES (?, ?, ?, ?, ?, ?)");
			_stmt.setInt(1, 1);
			_stmt.setString(2, serialNumber);
			_stmt.setString(3, datecode);
			_stmt.setString(4, macAddress);
			_stmt.setString(5, providerCode);
			_stmt.setInt(6, productConf.getIdProductConf());
			_stmt.executeUpdate();

			// Retrieve product data
			_stmt = c.prepareStatement(
							"SELECT * FROM product"
									+ " WHERE (idProductConf=?)"
									+ " AND (serialNumber=?)"
									+ " AND (datecode=?)");
			_stmt.setInt(1, productConf.getIdProductConf());
			_stmt.setString(2, serialNumber);
			_stmt.setString(3, datecode);

			_rs = _stmt.executeQuery();
			if (_rs.next()) {
				_product = this.getProduct(_rs);
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


		return _product;
	}

	@Override
	public void updateProduct(Product product) {
		Connection c = null;
		PreparedStatement _stmt = null;
		ResultSet _rs = null;
		try {
			c = this.cnxProduct.getCnx();
			_stmt = c.prepareStatement(
							"UPDATE product "
									+ "SET serialNumber=?, datecode=?, idProductConf=?, providerCode=?"
									+ " WHERE (idProduct=?)");
			_stmt.setString(1, product.getSerialNumber());
			_stmt.setString(2, product.getDatecode());
			_stmt.setInt(3, product.getProductConf().getIdProductConf());
			_stmt.setString(4, product.getProviderCode());
			_stmt.setInt(5, product.getIdProduct());
			_stmt.executeUpdate();

			_stmt = c.prepareStatement(
					"SELECT * FROM product" + " WHERE (idProduct=?)");
			_stmt.setInt(1, product.getIdProduct());

			_rs = _stmt.executeQuery();
			if (_rs.next()) {
				this.getProduct(_rs);
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
	}

	@Override
	public void updateProduct(Product product, ProductConf productConf,
			String serialNumber, String dateCode, String macAddress,
			String providerCode, int state) {

		int _idProductConf = 0;
		if (null != productConf) {
			_idProductConf = productConf.getIdProductConf();
		} else {
			_idProductConf = 0;
		}
		Connection c = null;
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			c = this.cnxProduct.getCnx();
			_stmt = c.prepareStatement(
							"UPDATE product "
									+ "SET serialNumber=?, datecode=?, macAddress=?, providerCode=?, state=?, idProductConf=?"
									+ " WHERE (idProduct=?)");
			_stmt.setString(1, serialNumber);
			_stmt.setString(2, dateCode);
			_stmt.setString(3, macAddress);
			_stmt.setString(4, providerCode);
			_stmt.setInt(5, state);
			_stmt.setInt(6, _idProductConf);
			_stmt.setInt(7, product.getIdProduct());
			_stmt.executeUpdate();

			// Update object
			_stmt = c.prepareStatement(
					"SELECT * FROM product" + " WHERE (idProduct=?)");
			_stmt.setInt(1, product.getIdProduct());

			_rs = _stmt.executeQuery();
			if (_rs.next()) {
				this.getProduct(_rs);
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
	}

	@Override
	public void updateProduct(Product product, String macAddress) {
		Connection c = null;
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			c = this.cnxProduct.getCnx();
			_stmt = c.prepareStatement(
					"UPDATE product " + "SET macAddress=?"
							+ " WHERE (idProduct=?)");
			_stmt.setString(1, macAddress);
			_stmt.setInt(2, product.getIdProduct());
			_stmt.executeUpdate();

			// Update object
			_stmt = c.prepareStatement(
					"SELECT * FROM product" + " WHERE (idProduct=?)");
			_stmt.setInt(1, product.getIdProduct());

			_rs = _stmt.executeQuery();
			if (_rs.next()) {
				this.getProduct(_rs);
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
	}

	@Override
	public List<Product> getProductComponents(Product product) {
		List<Product> _productComponents = new ArrayList<Product>();

		if (product == null) 
			throw new IllegalArgumentException();

		Connection c = null;
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			c = this.cnxProduct.getCnx();
			_stmt = c.prepareStatement(
							"SELECT * FROM product "
									+ " WHERE (product.idProduct IN"
									+ " (SELECT idProductComponent FROM product_product"
									+ " WHERE product_product.idProduct=?))");
			_stmt.setInt(1, product.getIdProduct());
			_rs = _stmt.executeQuery();

			while (_rs.next()) {
				Product _productComponent = this.getProduct(_rs);
				_productComponents.add(_productComponent);
			}
		} catch (SQLException e) {
			handleDAOException(e);
		} finally {
			close(_rs);
			close(_stmt);
			close(c);
		}

		return _productComponents;
	}

	@Override
	public void addProductComponent(Product product, Product productComponent) {
		int _idProduct = product.getIdProduct();
		int _idProductComponent = productComponent.getIdProduct();
		Connection c = null;
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			c = this.cnxProduct.getCnx();
			_stmt = c.prepareStatement(
					"INSERT INTO product_product (idProduct, idProductComponent)"
							+ " VALUES (?, ?)");
			_stmt.setInt(1, _idProduct);
			_stmt.setInt(2, _idProductComponent);
			_stmt.executeUpdate();

			// Retrieve product_product data
			_stmt = c.prepareStatement(
					"SELECT * FROM product_product" + " WHERE (idProduct=?)"
							+ " AND (idProductComponent=?)");
			_stmt.setInt(1, _idProduct);
			_stmt.setInt(2, _idProductComponent);

			_rs = _stmt.executeQuery();
			if (!_rs.next()) {
				throw new IllegalStateException();
			}
		} catch (SQLException e) {
			handleDAOException(e);
		} finally {
			close(_rs);
			close(_stmt);
			close(c);
		}
	}

	@Override
	public void removeProductComponent(Product product, Product productComponent) {
		if (product == null || productComponent == null)
				throw new IllegalArgumentException();
		Connection c = null;
		PreparedStatement _stmt = null;

		try {
			c = this.cnxProduct.getCnx();
			_stmt = c.prepareStatement(
						"DELETE FROM product_product " + "WHERE ((idProduct=?)"
								+ " AND (idProductComponent=?))");
			_stmt.setInt(1, product.getIdProduct());
			_stmt.setInt(2, productComponent.getIdProduct());
			_stmt.executeUpdate();
			product.removeProductComponent(productComponent);
		} catch (SQLException e) {
			handleDAOException(e);
		} finally {
			close(_stmt);
			close(c);
		}
	}

	@Override
	public List<Product> getProductWithMother(int startingAt, int maxPerPage, Map<String, String> filters) {
		List<Product> _product = this.getProductsSearch(startingAt, maxPerPage,	filters, 1);
		for (Product _p : _product) {
			Connection c = null;
			ResultSet _rs = null;
			PreparedStatement _stmt = null;
			try {
				// String sql =
				// "SELECT * FROM product WHERE idproduct = (SELECT idproduct FROM product_product WHERE idProductComponent = "
				// + _p.getIdProduct() + ");";
				String sql = "SELECT count(idproduct) as total FROM product_product WHERE idProductComponent = " + _p.getIdProduct() + ";";
				c = this.cnxProduct.getCnx();
				_stmt = c.prepareStatement(sql);
				_rs = _stmt.executeQuery();
				int i = 0;
				while (_rs.next()) {
					i = _rs.getInt("total");
				}
				if (i == 1) {
					String sql2 = "SELECT * FROM product WHERE idproduct = (SELECT idproduct FROM product_product WHERE idProductComponent = "
							+ _p.getIdProduct() + ");";
					_stmt = c.prepareStatement(sql2);
					_rs = _stmt.executeQuery();
					while (_rs.next()) {
						_p.setMother(this.getProduct(_rs));
					}
				}
			} catch (SQLException e) {
				handleDAOException(e);
			} finally {
				close(_rs);
				close(_stmt);
				close(c);
			}
		}
		return _product;
	}

	@Override
	public Product getProductWithProductConfRef(String reference) {
		Product product = null;
		Connection c = null;
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			c = this.cnxProduct.getCnx();
			_stmt = c.prepareStatement(
							"select * from product where idproductconf = (select idproductconf from productconf where reference = ?);");
			_stmt.setString(1, reference);
			_rs = _stmt.executeQuery();

			if (_rs.next()) {
				product = this.getProduct(_rs);
			}
		} catch (SQLException e) {
			handleDAOException(e);
		} finally {
			close(_rs);
			close(_stmt);
			close(c);
		}
		return product;
	}

	/*
	 * 
	 * Cr&eacute;er un produit &agrave; partir d'un enregistrement de la base de
	 * donn&eacute;es.
	 * 
	 * @param rs Enregistrement de la base de donn&eacute;es.
	 * 
	 * @return Produit.
	 */
	private Product getProduct(ResultSet rs) throws SQLException {
		// Retreive productConf
		int _idProductConf = rs.getInt("idProductConf");
		ProductConf _productConf = _productConfDao.getProductConf(_idProductConf);

		int _idProduct = rs.getInt("idProduct");
		Timestamp _timestamp = rs.getTimestamp("timestamp");
		int _state = rs.getInt("state");
		String _serialNumber = rs.getString("serialNumber");
		String _datecode = rs.getString("datecode");
		String _macAddress = rs.getString("macAddress");
		String _providerCode = rs.getString("providerCode");
		Product _product = new Product(_idProduct, _timestamp, _state,
				_serialNumber, _datecode, _macAddress, _providerCode,
				_productConf);

		// Retreive softwares
		_softwareDao.getSoftwares(_product);

		return _product;
	}

	// JB : methode permettant de copier les produits selectionnes de la base FEDD sur la base LAI
	@Override
	public Product setProductFEDDtoLAI(int idProductFEDD, ProductConf config, String serialNumber, String datecode)  {

		Product _product = null;
		Connection c = null;
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try { // je prends la connexion pour cette longue transaction
			c = this.cnxProduct.getCnx();

			/*
			 * Ajout de colonnes dans des tables pour conserver le lien entre les enregistrements de FEDD et de LAI.
			 * Ces colonnes une fois les donnees enregistrees chez LAI seront supprimees
			 */
			
			String modifTempTable = "";
			
			if (!this.ColumnAlreadyExist("testerBase", "testerReport", "idTesterReportFEDD")) {
				System.out.println ("je cree la colonne idTesterReportFEDD");
				modifTempTable = "ALTER TABLE `testerBase`.`testerReport` ADD COLUMN idTesterReportFEDD int(10)";
				_stmt = c.prepareStatement(modifTempTable);
				_stmt.executeUpdate();
			}
			if (!this.ColumnAlreadyExist("productBase", "ProductionFailureReport", "idProductionFailureReportFEDD")) {
				System.out.println ("je cree la colonne idProductionFailureReportFEDD");
				modifTempTable = "ALTER TABLE `productBase`.`ProductionFailureReport` ADD COLUMN idProductionFailureReportFEDD int(10)";
				_stmt = c.prepareStatement(modifTempTable);
				_stmt.executeUpdate();
			}
			if (!this.ColumnAlreadyExist("productBase", "failure", "idFailureFEDD")) {
				System.out.println ("je cree la colonne idFailureFEDD");
				modifTempTable = "ALTER TABLE `productBase`.`failure` ADD COLUMN idFailureFEDD int(10)";
				_stmt = c.prepareStatement(modifTempTable);
				_stmt.executeUpdate();
			}

			/* 1 ++++++++++++++++++++++++++++++++++++ */
			/* Partie insertion dans la table product */
			/* -------------------------------------- */
			_stmt = c.prepareStatement("INSERT INTO `productBase`.`product` "
					+ "(timeStamp, state, serialNumber, datecode, macAddress, providerCode, idProductConf) "
					+ "SELECT fp.timestamp, fp.state, fp.serialNumber, fp.datecode, fp.macAddress, fp.providerCode, fp.idProductConf "
					+ "FROM `FEDDproductBase`.`product` fp "
					+ "WHERE fp.idProduct = ? OR fp.idProduct IN (SELECT idProductComponent from `FEDDproductBase`.`product_product` fpp where fpp.idProduct = ? AND idProductComponent NOT IN (1, 2, 3))");
			_stmt.setInt(1, idProductFEDD);
			_stmt.setInt(2, idProductFEDD);
			_stmt.executeUpdate();
			System.out.println (_stmt);

			// JB : permet de recuperer idProduct de la base LAI (qui est un autoincrement) apres insertion depuis la base FEDD
			int idProductLAI = this.getProduct(config, serialNumber, datecode).getIdProduct();
			System.out.println (idProductLAI);

			/* 2 ++++++++++++++++++++++++++++++++++++++++++++ */
			/* Partie insertion dans la table product_product */
			/* ---------------------------------------------- */
			/*ordreInsertion = "INSERT INTO `productBase`.`product_product` (idProduct, idProductComponent) "
					+ "SELECT p1.idProduct as idprod_LAI, p2.idProduct as idprodcomp_LAI "
					+ "FROM `productBase`.`product` p1, `productBase`.`product` p2, "
					+ "`FEDDproductBase`.`product` p3, `FEDDproductBase`.`product` p4, "
					+ "`FEDDproductBase`.`product_product` pp "
					+ "WHERE p1.serialNumber = p3.serialNumber "
					+ "AND p1.datecode = p3.datecode "
					+ "AND p1.idProductConf = p3.idProductConf "
					+ "AND p3.idproduct = ? "
					+ "AND p2.serialNumber = p4.serialNumber "
					+ "AND p2.datecode = p4.datecode "
					+ "AND p2.idProductConf = p4.idProductConf "
					+ "AND p3.idProduct = pp.idProduct "
					+ "AND pp.idProductComponent = p4.idProduct"; */
			_stmt = c.prepareStatement("INSERT INTO `productBase`.`product_product` (idProduct, idProductComponent) "
					+ "SELECT "+idProductLAI+", p.idProduct "
					+ "FROM `productBase`.`product` p, `FEDDproductBase`.`product` fp, `FEDDproductBase`.`product_product` fpp "
					+ "WHERE p.serialNumber = fp.serialNumber"
					+ "  AND p.datecode = fp.datecode"
					+ "  AND p.idProductConf = fp.idProductConf"
					+ "  AND fp.idProduct = fpp.idProductComponent"
					+ "  AND fpp.idproduct = ? ");
			_stmt.setInt(1, idProductFEDD);
			_stmt.executeUpdate();
			System.out.println (_stmt);

			/* 3 +++++++++++++++++++++++++++++++++++++++++++++ */
			/* Partie insertion dans la table product_software */
			/* ----------------------------------------------- */
			_stmt = c.prepareStatement("INSERT INTO `productBase`.`product_software` (idProduct, idSoftware) "
					+ "SELECT "+idProductLAI+", ps.idSoftware "
					+ "FROM `FEDDproductBase`.`product_software` ps "
					+ "WHERE ps.idProduct = ?");
			_stmt.setInt(1, idProductFEDD);
			_stmt.executeUpdate();
			System.out.println (_stmt);

			/* 4 +++++++++++++++++++++++++++++++++++++++++++ */
			/* Partie insertion dans la table productComment */
			/* --------------------------------------------- */
			_stmt = c.prepareStatement("INSERT INTO `productBase`.`productComment` (timestamp, comment, idProduct) "
					+ "SELECT pc.timestamp, pc.comment, "+idProductLAI
					+ " FROM `FEDDproductBase`.`productComment` pc "
					+ "WHERE pc.idproduct = ? ");
			_stmt.setInt(1, idProductFEDD);
			_stmt.executeUpdate();
			System.out.println (_stmt);

			/* 5 +++++++++++++++++++++++++++++++++++ */
			/* Partie insertion dans la table tester */ //JB : s il n existe pas deja dans la base LAI 
			/* ------------------------------------- */
			_stmt = c.prepareStatement("INSERT INTO `testerBase`.`tester` (timestamp, state, name) "
					+ "SELECT ft.timestamp, ft.state, ft.name "
					+ "FROM `FEDDtesterBase`.`tester` ft, `FEDDtesterBase`.`testerReport` ftr "
					+ "WHERE ft.idTester = ftr.idTester "
					+ "  AND ftr. idProduct = ? "
					+ "  AND ft.name NOT IN (SELECT name FROM `testerBase`.`tester`) "
					+ "GROUP BY ft.name");
			_stmt.setInt(1, idProductFEDD);
			_stmt.executeUpdate();
			System.out.println (_stmt);

			/* 6 +++++++++++++++++++++++++++++++++++++++++ */
			/* Partie insertion dans la table testerReport */
			/* ------------------------------------------- */
			/* Il faudra changer l'idTesterReportNext */

/*			
			ordreInsertion = "INSERT INTO `testerBase`.TesterReport "
					+ "(timestamp, state, date, testVersion, result, consoUmini, consoUnomi, "
					+ "consoUmaxi, idTestType, idTester, operatorCode, idProduct, idTesterReportNext, idTesterReportFEDD) "
					+ "SELECT tr.timestamp, tr.state, tr.date, tr.testVersion, tr.result, tr.consoUmini, tr.consoUnomi, "
					+ "tr.consoUmaxi, tr.idTestType, tlai.idTester, tr.operatorCode, ?, tr.idTesterReportNext, tr.idTesterReport "
					+ "FROM `FEDDtesterBase`.`testerReport` tr, `FEDDtesterBase`.`tester` tfedd, `testerBase`.`tester` tlai "
					+ "WHERE tr.idTester = tfedd.idTester AND tfedd.name = tlai.name AND tr.idProduct = ? "
					+ "UNION "
					+ "SELECT tr2.timestamp, tr2.state, tr2.date, tr2.testVersion, tr2.result, tr2.consoUmini, tr2.consoUnomi, "
					+ "tr2.consoUmaxi, tr2.idTestType, 0, tr2.operatorCode, ?, tr2.idTesterReportNext, tr2.idTesterReport "
					+ "FROM `FEDDtesterBase`.`testerReport` tr2 "
					+ "WHERE tr2.idTester = 0 AND tr2.idProduct = ? ";
*/
			// TODO : � faire confirmer par Stephan
			_stmt = c.prepareStatement("INSERT INTO `testerBase`.TesterReport (timestamp, state, date, testVersion, result, consoUmini, consoUnomi, "
					+ "consoUmaxi, idTestType, idTester, operatorCode, idProduct, idTesterReportNext, idTesterReportFEDD) "
					+ "SELECT ftr.timestamp, ftr.state, ftr.date, ftr.testVersion, ftr.result, ftr.consoUmini, ftr.consoUnomi, "
					+ "ftr.consoUmaxi, ftr.idTestType, tlai.idTester, ftr.operatorCode, "+idProductLAI+", ftr.idTesterReportNext, ftr.idTesterReport "
					+ "FROM `FEDDtesterBase`.`testerReport` ftr, `FEDDtesterBase`.`tester` ft, `testerBase`.`tester` tlai "
					+ "WHERE ftr.idTester = ft.idTester AND ft.name = tlai.name AND ftr.idProduct = ? ");
			_stmt.setInt(1, idProductFEDD);
			_stmt.executeUpdate();
			System.out.println (_stmt);

			// JB : mise a jour de la relation idTesterReportNext avec idTesterReport apres transcodification de INSERT
			_stmt = c.prepareStatement("SELECT tr1.idTesterReport, tr2.idTesterReport "
					+ "FROM `testerBase`.`testerReport` tr1, `testerBase`.`testerReport` tr2 "
					+ "WHERE tr1.idTesterReportNext = tr2.idTesterReportFEDD "
					+ "AND tr1.idTesterReportNext IS NOT NULL");
			_rs = _stmt.executeQuery();

			while (_rs.next()) {
				int record = _rs.getInt(1);
				int recordNext = _rs.getInt(2);

				_stmt = c.prepareStatement("UPDATE `testerBase`.`testerReport` SET idTesterReportNext = ? WHERE idTesterReport = ?");
				_stmt.setInt(1, recordNext);
				_stmt.setInt(2, record);
				_stmt.executeUpdate();
				System.out.println (_stmt);
			}

			/* 7 +++++++++++++++++++++++++++++++++++ */
			/* Partie insertion dans la table defect */
			/* ------------------------------------- */
			_stmt = c.prepareStatement("INSERT INTO `testerBase`.`defect` (timestamp, state, sequence, testName, function, value, idTesterReport) "
					+ "SELECT fd.timestamp, fd.state, fd.sequence, fd.testName, fd.function, fd.value, tr.idTesterReport "
					+ "FROM `FEDDtesterBase`.`defect` fd, `testerBase`.`testerReport` tr "
					+ "WHERE fd.idTesterReport = tr.idTesterReportFEDD "
					+ "AND tr.idproduct = ? ");
			_stmt.setInt(1, idProductLAI);
			_stmt.executeUpdate();
			System.out.println (_stmt);

			/* 8 ++++++++++++++++++++++++++++++++++++++++++++++++++++ */
			/* Partie insertion dans la table ProductionFailureReport */
			/* ------------------------------------------------------ */
			_stmt = c.prepareStatement("INSERT INTO productionFailureReport "
					+ "(timestamp, state, registrationDate, repairDate, failureCode, idProduct, idTesterReport, idProductionFailureReportFEDD) "
					+ "SELECT fpfr.timestamp, fpfr.state, fpfr.registrationDate, fpfr.repairDate, "
					+ "fpfr.failureCode, ?, tr.idTesterReport, fpfr.idProductionFailureReport "
					+ "FROM `FEDDproductBase`.`productionFailureReport` fpfr, `testerBase`.`testerReport` tr "
					+ "WHERE fpfr.idTesterReport = tr.idTesterReportFEDD ");
			_stmt.setInt(1, idProductLAI);
			_stmt.executeUpdate();
			System.out.println (_stmt);

			/* 9 ++++++++++++++++++++++++++++++++++++++++++++ */
			/* Partie insertion dans la table customerComment */
			/* ---------------------------------------------- */
			_stmt = c.prepareStatement("INSERT INTO `productBase`.`customerComment` (timestamp, comment, idProductionFailureReport) "
					+ "SELECT fcc.timestamp, fcc.comment, pfr.idProductionFailureReport "
					+ "FROM `FEDDproductBase`.`customerComment` fcc, `productBase`.`productionFailureReport` pfr "
					+ "WHERE fcc.idProductionFailureReport = pfr.idProductionFailureReportFEDD "
					+ "AND pfr.idproduct = ? ");
			_stmt.setInt(1, idProductLAI);
			_stmt.executeUpdate();
			System.out.println (_stmt);

			/* 10 ++++++++++++++++++++++++++++++++++++++++++++++++ */
			/* Partie insertion dans la table failureReportComment */
			/* --------------------------------------------------- */
			_stmt = c.prepareStatement("INSERT INTO `productBase`.`failureReportComment` (timestamp, comment, commentDate, idProductionFailureReport) "
					+ "SELECT ffrc.timestamp, ffrc.comment, ffrc.commentDate, pfr.idProductionFailureReport "
					+ "FROM `FEDDproductBase`.`failureReportComment` ffrc, `productBase`.`productionFailureReport` pfr  "
					+ "WHERE ffrc.idProductionFailureReport = pfr.idProductionFailureReportFEDD "
					+ "AND pfr.idproduct = ? ");
			_stmt.setInt(1, idProductLAI);
			_stmt.executeUpdate();
			System.out.println (_stmt);

			/* 11 ++++++++++++++++++++++++++++++++ */
			/* Partie insertion dans la table operator */
			/* --------------------------------------- */
			_stmt = c.prepareStatement("INSERT INTO `operatorBase`.`operator` (timestamp, state, code, lastName, firstName) "
					+ "SELECT fo.timestamp, fo.state, fo.code, fo.lastName, fo.firstName "
					+ "FROM `FEDDoperatorBase`.`operator` fo, `FEDDproductBase`.`failure` ff "
					+ "WHERE fo.idOperator = ff.idOperator "
					+ "AND ff. idProduct = ? "
					+ "AND (fo.code, fo.firstName, fo.lastName) NOT IN (SELECT o.code, o.firstName, o.lastName FROM `operatorBase`.`operator` o) "
					+ "GROUP BY 3, 4, 5 ");
			_stmt.setInt(1, idProductFEDD);
			_stmt.executeUpdate();
			System.out.println (_stmt);

			/* 12 +++++++++++++++++++++++++++++++++++ */
			/* Partie insertion dans la table failure */
			/* -------------------------------------- */
			_stmt = c.prepareStatement("INSERT INTO `productBase`.`failure` "
					+ "(timestamp, state, diagnosisDate, failureCause, cardFace, manufacturingTechnique, failureCode, "
					+ "imputationCode, dismantleCard, idOperator, idProduct, idProductionFailureReport, idFailureFEDD) "
					+ "SELECT ff.timestamp, ff.state, ff.diagnosisDate, ff.failureCause, ff.cardFace, ff.manufacturingTechnique, ff.failureCode, "
					+ "ff.imputationCode, ff.dismantleCard, o.idOperator, ?, pfr.idProductionFailureReport, ff.idFailure "
					+ "FROM `FEDDproductBase`.`failure` ff, `productBase`.`productionFailureReport` pfr, "
					+ " `FEDDoperatorBase`.`operator` fo, `operatorBase`.`operator` o "
					+ "WHERE ff.idOperator = fo.idOperator "
					+ "AND (fo.code = o.code AND fo.firstName = o.firstName AND fo.lastName = o.lastName) "
					+ "AND pfr.idProductionFailureReportFEDD = ff.idProductionFailureReport "
					+ "AND ff.idProduct = ? "
					+ "AND pfr.idProduct = ? ");
			_stmt.setInt(1, idProductLAI);
			_stmt.setInt(2, idProductFEDD);
			_stmt.setInt(3, idProductLAI);
			_stmt.executeUpdate();
			System.out.println (_stmt);

			/* 13 ++++++++++++++++++++++++++++++++++++++++++ */
			/* Partie insertion dans la table elementChanged */
			/* --------------------------------------------- */
			_stmt = c.prepareStatement("INSERT INTO `productBase`.`elementChanged` (timestamp, topoRef, idFailure) "
					+ "SELECT fec.timestamp, fec.topoRef, f.idFailure "
					+ "FROM `FEDDproductBase`.`elementChanged` fec, `productBase`.`failure` f "
					+ "WHERE fec.idFailure = f.idFailureFEDD "
					+ "AND f.idProduct = ? ");
			_stmt.setInt(1, idProductLAI);
			_stmt.executeUpdate();
			System.out.println (_stmt);

			/* 14 +++++++++++++++++++++++++++++++++++++++++++ */
			/* Partie insertion dans la table productDocument */
			/* ---------------------------------------------- */
			_stmt = c.prepareStatement("INSERT INTO `productBase`.`productDocument` (timestamp, state, title, link, idProductDocumentType, idProduct) "
					+ "SELECT fpd.timestamp, fpd.state, fpd.title, fpd.link, fpd.idProductDocumentType, ? "
					+ "FROM `FEDDproductBase`.`productDocument` fpd "
					+ "WHERE fpd.idproduct = ? ");
			_stmt.setInt(1, idProductLAI);
			_stmt.setInt(2, idProductFEDD);
			_stmt.executeUpdate();
			System.out.println (_stmt);

			
			// suppression des colonnes temporaires
			modifTempTable = "ALTER TABLE `productBase`.`failure` DROP COLUMN idFailureFEDD";
			_stmt = c.prepareStatement(modifTempTable);
			_stmt.executeUpdate();
			modifTempTable = "ALTER TABLE `productBase`.`productionFailureReport` DROP COLUMN idProductionFailureReportFEDD";
			_stmt = c.prepareStatement(modifTempTable);
			_stmt.executeUpdate();
			modifTempTable = "ALTER TABLE `testerBase`.`testerReport` DROP COLUMN idTesterReportFEDD";
			_stmt = c.prepareStatement(modifTempTable);
			_stmt.executeUpdate();


			// Retrieve product data // JB : pourquoi ?
			_stmt = c.prepareStatement("SELECT * FROM product WHERE (idProduct=?)");
			_stmt.setInt(1, idProductLAI);
			_rs = _stmt.executeQuery();

			if (_rs.next()) {
				_product = this.getProduct(_rs);
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
		return _product;
	}

	private Boolean ColumnAlreadyExist(String db, String table, String column) {
		Boolean retour = false;
		String result = "";
		Connection c = null;
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			c = cnxInfoSchema.getCnx();
			_stmt = c.prepareStatement(
							"SELECT COLUMN_NAME FROM information_schema.COLUMNS WHERE TABLE_SCHEMA = ? AND TABLE_NAME = ? AND COLUMN_NAME = ?;");
			_stmt.setString(1, db);
			_stmt.setString(2, table);
			_stmt.setString(3, column);
			_rs = _stmt.executeQuery();

			if (_rs.next()) {
				result = _rs.getString("COLUMN_NAME");
			}
			if (result == column) {
				retour = true;
			}
		} catch (SQLException e) {
			handleDAOException(e);
		} finally {
			close(_rs);
			close(_stmt);
			close(c);
		}
		return retour;
	}
}
