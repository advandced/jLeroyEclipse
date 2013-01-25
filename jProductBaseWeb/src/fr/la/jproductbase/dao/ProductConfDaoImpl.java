package fr.la.jproductbase.dao;

import fr.la.jproductbase.metier.FollowingFormModel;
import fr.la.jproductbase.metier.ProductConf;
import fr.la.jproductbase.metier.ProductConfModel;
import fr.la.jproductbase.metier.ProductFamily;
import fr.la.jproductbase.metier.ProductLine;
import fr.la.jproductbase.metier.ProductSupply;
import fr.la.jproductbase.metier.ProductType;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.naming.NamingException;

public class ProductConfDaoImpl implements ProductConfDao {

	private static String exceptionMsg = "Configuration produit inconnue dans la base de données.";
	private ConnectionProduct cnxProduct;

	public ProductConfDaoImpl(ConnectionProduct cnxProduct) {
		this.cnxProduct = cnxProduct;
	}

	@Override
	public ProductConf getProductConf(int idProductConf) throws SQLException {
		ProductConf _productConf = null;
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			_stmt = this.cnxProduct.getCnx().prepareStatement(
					"SELECT * FROM productConf" + " WHERE (idProductConf=?)");
			_stmt.setInt(1, idProductConf);
			_rs = _stmt.executeQuery();
			System.out.println("prout");
			if (_rs.next()) {
				_productConf = this.getProductConf(_rs);
			} else {
				_productConf = null;
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
		return _productConf;
	}

	@Override
	public ProductConf getProductConf(String reference, String majorIndex,
			String minorIndex) throws SQLException {
		ProductConf _productConf = null;
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			_stmt = this.cnxProduct.getCnx().prepareStatement(
					"SELECT * FROM productConf" + " WHERE (reference=?)"
							+ " AND (majorIndex=?)" + " AND (minorIndex=?)");
			_stmt.setString(1, reference);
			_stmt.setString(2, majorIndex);
			_stmt.setString(3, minorIndex);
			_rs = _stmt.executeQuery();

			if (_rs.next()) {
				_productConf = this.getProductConf(_rs);
			} else {
				_productConf = null;
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

		return _productConf;
	}

	@Override
	public ProductConf getLastActiveProductConf(String reference,
			String majorIndex, String minorIndex) throws SQLException {
		ProductConf _productConf = null;
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			String _queryString = "(reference=?)";
			int _paramIndex = 1;

			// majorIndex
			int _majorIndexInd = 0;
			if (false == majorIndex.trim().equals("")) {
				_queryString += " AND (majorIndex=?)";
				_paramIndex++;
				_majorIndexInd = _paramIndex;
			} else {
				// No major index
			}

			// minorIndex
			int _minorIndexInd = 0;
			if (false == minorIndex.trim().equals("")) {
				_queryString += " AND (minorIndex=?)";
				_paramIndex++;
				_minorIndexInd = _paramIndex;
			} else {
				// No major index
			}

			_stmt = this.cnxProduct.getCnx().prepareStatement(
					"SELECT * FROM productConf" + " WHERE " + _queryString
							+ " ORDER BY majorIndex DESC, minorIndex DESC");
			_stmt.setString(1, reference);
			if (0 != _majorIndexInd) {
				_stmt.setString(_majorIndexInd, majorIndex);
			}
			if (0 != _minorIndexInd) {
				_stmt.setString(_minorIndexInd, minorIndex);
			}
			_rs = _stmt.executeQuery();

			if (_rs.next()) {
				_productConf = this.getProductConf(_rs);
			} else {
				_productConf = null;
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

		return _productConf;
	}

	@Override
	public List<ProductConf> getProductConfs() throws SQLException {
		List<ProductConf> _productConfs = new ArrayList<ProductConf>();
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			_stmt = this.cnxProduct
					.getCnx()
					.prepareStatement(
							"SELECT * FROM productConf pc, productFamily pf "
									+ "WHERE pc.idProductFamily = pf.idProductFamily "
									+ "ORDER BY pf.idProductType DESC, pc.reference ASC, "
									+ "pc.majorIndex ASC, pc.minorIndex ASC ");
			_rs = _stmt.executeQuery();

			while (_rs.next()) {
				ProductConf _productConf = this.getProductConf(_rs);
				_productConfs.add(_productConf);
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

		return _productConfs;
	}

	@Override
	public List<ProductConf> getProductConfs(int type) throws SQLException {
		List<ProductConf> _productConfs = new ArrayList<ProductConf>();
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			_stmt = this.cnxProduct
					.getCnx()
					.prepareStatement(
							"SELECT pc.* FROM productConf pc, productFamily pf "
									+ "WHERE pc.idProductFamily = pf.idProductFamily AND (pf.idProductType=?) "
									+ "ORDER BY pc.reference;");
			_stmt.setInt(1, type);
			_rs = _stmt.executeQuery();

			while (_rs.next()) {
				ProductConf _productConf = this.getProductConf(_rs);
				_productConfs.add(_productConf);
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

		return _productConfs;
	}

	@Override
	public List<ProductConf> getProductConfs(String reference)
			throws SQLException {
		List<ProductConf> _productConfs = new ArrayList<ProductConf>();
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			_stmt = this.cnxProduct.getCnx().prepareStatement(
					"SELECT * FROM productConf WHERE (reference=?)");
			_stmt.setString(1, reference);
			_rs = _stmt.executeQuery();

			while (_rs.next()) {
				ProductConf _productConf = this.getProductConf(_rs);
				_productConfs.add(_productConf);
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

		return _productConfs;
	}

	@Override
	public List<ProductConf> getActiveProductConfs() throws SQLException {
		List<ProductConf> _productConfs = new ArrayList<ProductConf>();
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			_stmt = this.cnxProduct.getCnx().prepareStatement(
					"SELECT * FROM productConf"
							+ " WHERE (state=?) ORDER BY reference");
			_stmt.setInt(1, 1);
			_rs = _stmt.executeQuery();

			while (_rs.next()) {
				ProductConf _productConf = this.getProductConf(_rs);
				_productConfs.add(_productConf);
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

		return _productConfs;
	}

	@Override
	public List<ProductConf> getProductConfComponents(ProductConf productConf)
			throws SQLException {
		List<ProductConf> _productConfComponents = new ArrayList<ProductConf>();

		if (null != productConf) {
			PreparedStatement _stmt = null;
			ResultSet _rs = null;

			try {
				_stmt = this.cnxProduct
						.getCnx()
						.prepareStatement(
								"SELECT * FROM productConf "
										+ " WHERE (productConf.idProductConf IN"
										+ " (SELECT idProductConfComponent FROM productConf_productConf"
										+ " WHERE productConf_productConf.idProductConf=?))");
				_stmt.setInt(1, productConf.getIdProductConf());
				_rs = _stmt.executeQuery();

				while (_rs.next()) {
					ProductConf _productConfComponent = this
							.getProductConf(_rs);
					_productConfComponents.add(_productConfComponent);
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
		} else {
			// Nothing to do
		}

		return _productConfComponents;
	}

	@Override
	public List<ProductConf> getActiveProductConfs(String reference)
			throws SQLException {
		List<ProductConf> _productConfs = new ArrayList<ProductConf>();
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			_stmt = this.cnxProduct.getCnx().prepareStatement(
					"SELECT * FROM productConf" + " WHERE (reference=?)"
							+ " AND (state=1)");
			_stmt.setString(1, reference);
			_rs = _stmt.executeQuery();

			while (_rs.next()) {
				ProductConf _productConf = this.getProductConf(_rs);
				_productConfs.add(_productConf);
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

		return _productConfs;
	}

	@Override
	public List<ProductConf> getActiveProductConfs(ProductType productType)
			throws SQLException {
		List<ProductConf> _productConfs = new ArrayList<ProductConf>();
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			_stmt = this.cnxProduct
					.getCnx()
					.prepareStatement(
							"SELECT * FROM productConf, productFamily, productType"
									+ " WHERE (productConf.idProductFamily = productFamily.idProductFamily)"
									+ " AND (productFamily.idProductType = productType.idProductType)"
									+ " AND (productType.idProductType = ?)"
									+ " AND (productConf.state=1)"
									+ "  ORDER BY reference");
			_stmt.setInt(1, productType.getIdProductType());
			_rs = _stmt.executeQuery();

			while (_rs.next()) {
				ProductConf _productConf = this.getProductConf(_rs);
				_productConfs.add(_productConf);
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

		return _productConfs;
	}

	@Override
	public ProductConf addProductConf(String reference, String majorIndex,
			String minorIndex, String designation, int state,
			ProductFamily productFamily, ProductSupply productSupply)
			throws SQLException, ProductConfDaoException {
		ProductConf _productConf = null;
		PreparedStatement _stmt = null;
		ResultSet _rs = null;
		int _idProductSupply = 0;
		if (null != productSupply) {
			_idProductSupply = productSupply.getIdProductSupply();
		} else {
			_idProductSupply = 0;
		}
		try {
			_stmt = this.cnxProduct
					.getCnx()
					.prepareStatement(
							"INSERT INTO productConf (reference, majorIndex, minorIndex, designation, state, idProductFamily, idProductSupply)"
									+ " VALUES (?, ?, ?, ?, ?, ?, ?)");
			_stmt.setString(1, reference);
			_stmt.setString(2, majorIndex);
			_stmt.setString(3, minorIndex);
			_stmt.setString(4, designation);
			_stmt.setInt(5, state);
			_stmt.setInt(6, productFamily.getIdProductFamily());
			_stmt.setInt(7, _idProductSupply);
			_stmt.executeUpdate();

			_stmt = this.cnxProduct.getCnx().prepareStatement(
					"SELECT * FROM productConf" + " WHERE (reference=?) "
							+ "AND (majorIndex=?) AND (minorIndex=?) ");
			_stmt.setString(1, reference);
			_stmt.setString(2, majorIndex);
			_stmt.setString(3, minorIndex);

			_rs = _stmt.executeQuery();
			if (_rs.next()) {
				_productConf = this.getProductConf(_rs);
			} else {
				throw new ProductConfDaoException(exceptionMsg);
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

		return _productConf;
	}

	@Override
	public void updateProductConf(ProductConf productConf, String reference,
			String majorIndex, String minorIndex, String designation,
			int state, ProductFamily productFamily, ProductSupply productSupply)
			throws SQLException, ProductConfDaoException {
		int _idProductSupply = 0;
		if (null != productSupply) {
			_idProductSupply = productSupply.getIdProductSupply();
		} else {
			_idProductSupply = 0;
		}
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			_stmt = this.cnxProduct
					.getCnx()
					.prepareStatement(
							"UPDATE productConf "
									+ "SET reference=?, majorIndex=?, minorIndex=?, designation=?, state=?, idProductFamily=?, idProductSupply=?"
									+ " WHERE (idProductConf=?)");
			_stmt.setString(1, reference);
			_stmt.setString(2, majorIndex);
			_stmt.setString(3, minorIndex);
			_stmt.setString(4, designation);
			_stmt.setInt(5, state);
			_stmt.setInt(6, productFamily.getIdProductFamily());
			_stmt.setInt(7, _idProductSupply);
			_stmt.setInt(8, productConf.getIdProductConf());
			_stmt.executeUpdate();

			// Update object
			_stmt = this.cnxProduct.getCnx().prepareStatement(
					"SELECT * FROM productConf" + " WHERE (idProductConf=?)");
			_stmt.setInt(1, productConf.getIdProductConf());

			_rs = _stmt.executeQuery();
			if (_rs.next()) {
				this.getProductConf(_rs);
			} else {
				throw new ProductConfDaoException(exceptionMsg);
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
	}

	/*
	 * Cr&eacute;er une configuration produit &agrave; partir d'un enregistement
	 * de la base de donn&eacute;es.
	 * 
	 * @param rs Enregistement de la base de donn&eacute;es.
	 * 
	 * @return Configuration produit.
	 */
	private ProductConf getProductConf(ResultSet rs) throws SQLException {
		// Retreive productSupply
		int _idProductSupply = rs.getInt("idProductSupply");
		ProductSupplyDao _productSupplyDao = new ProductSupplyDaoImpl(
				this.cnxProduct);
		ProductSupply _productSupply = _productSupplyDao
				.getProductSupply(_idProductSupply);
		// Retreive productFamily
		int _idProductFamily = rs.getInt("idProductFamily");
		ProductFamilyDao _productFamilyDao = new ProductFamilyDaoImpl(
				this.cnxProduct);
		ProductFamily _productFamily = _productFamilyDao
				.getProductFamily(_idProductFamily);
		// Retreive followingFormModel
		int _idFollowingFormModel = rs.getInt("idFollowingFormModel");
		FollowingFormModelDao _followingFormModelDao = new FollowingFormModelDaoImpl(
				this.cnxProduct);
		FollowingFormModel _followingFormModel = _followingFormModelDao
				.getFollowingFormModel(_idFollowingFormModel);
		// Retreive productLine
		int _idProductLine = rs.getInt("idProductLine");
		ProductLineDao _productLineDao = new ProductLineDaoImpl(this.cnxProduct);
		ProductLine _productLine = _productLineDao
				.getProductLine(_idProductLine);
		// Retreive productConfModel
		int _idProductConfModel = rs.getInt("idProductConfModel");
		ProductConfModelDao _productConfModelDao = new ProductConfModelDaoImpl(
				this.cnxProduct);
		ProductConfModel _productConfModel = _productConfModelDao
				.getProductConfModel(_idProductConfModel);

		int _idProductConf = rs.getInt("idProductConf");
		Timestamp _timestamp = rs.getTimestamp("timestamp");
		int _state = rs.getInt("state");
		String _reference = rs.getString("reference");
		String _majorIndex = rs.getString("majorIndex");
		String _minorIndex = rs.getString("minorIndex");
		boolean _identifiable = rs.getBoolean("identifiable");
		ProductConf _productConf = new ProductConf(_idProductConf, _timestamp,
				_state, _reference, _majorIndex, _minorIndex, _identifiable,
				_productSupply, _productFamily, _followingFormModel,
				_productLine, _productConfModel);

		// Retreive softwares
		SoftwareDao _softwareDao = new SoftwareDaoImpl(this.cnxProduct);
		_softwareDao.getSoftwares(_productConf);

		return _productConf;
	}

	// 17-01-12 : RMO : Creation de la methode
	@Override
	public void removeProductConfComponent(ProductConf productConf,
			ProductConf component) throws SQLException {
		if ((null != productConf) && (null != component)) {
			PreparedStatement _stmt = null;
			try {
				_stmt = this.cnxProduct.getCnx().prepareStatement(
						"DELETE FROM productConf_productConf "
								+ "WHERE ((idProductConf=?)"
								+ " AND (idProductConfComponent=?))");
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			_stmt.setInt(1, productConf.getIdProductConf());
			_stmt.setInt(2, component.getIdProductConf());
			_stmt.executeUpdate();

			// Update product
			/* productConf.removeProductConfComponent(component); */

			_stmt.close();
		} else {
			// Nothing to do
		}

	}

	// 10-01-12 : RMO : Creation de la methode
	@Override
	public void addProductConfComponent(ProductConf productConf,
			ProductConf component) throws SQLException, ProductConfDaoException {
		int _idProductConf = productConf.getIdProductConf();
		int _idComponent = component.getIdProductConf();

		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			_stmt = this.cnxProduct.getCnx().prepareStatement(
					"INSERT INTO productConf_productConf (idProductConf, idProductConfComponent)"
							+ " VALUES (?, ?)");
			_stmt.setInt(1, _idProductConf);
			_stmt.setInt(2, _idComponent);
			_stmt.executeUpdate();

			// Retrieve productConf_product data
			_stmt = this.cnxProduct.getCnx().prepareStatement(
					"SELECT * FROM productConf_productConf"
							+ " WHERE (idProductConf=?)"
							+ " AND (idProductConfComponent=?)");
			_stmt.setInt(1, _idProductConf);
			_stmt.setInt(2, _idComponent);

			_rs = _stmt.executeQuery();
			if (!_rs.next()) {
				throw new ProductConfDaoException(exceptionMsg);
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
	}

	@Override
	public ProductConf addProductConf(String reference, String majorIndex,
			String minorIndex, ProductConfModel productConfModel,
			boolean identifiable, int state, ProductFamily productFamily,
			ProductSupply productSupply, FollowingFormModel followingForm)
			throws SQLException, ProductConfDaoException {
		ProductConf _productConf = null;
		PreparedStatement _stmt = null;
		ResultSet _rs = null;
		int _idProductConfModel = 0;
		if (null != productConfModel) {
			_idProductConfModel = productConfModel.getIdProductConfModel();
		} else {
			_idProductConfModel = 0;
		}
		int _idProductSupply = 0;
		if (null != productSupply) {
			_idProductSupply = productSupply.getIdProductSupply();
		} else {
			_idProductSupply = 0;
		}
		int _idFollowingFormModel = 0;
		if (null != followingForm) {
			_idFollowingFormModel = followingForm.getIdFollowingFormmodel();
		} else {
			_idFollowingFormModel = 0;
		}
		try {
			_stmt = this.cnxProduct
					.getCnx()
					.prepareStatement(
							"INSERT INTO productConf (reference, majorIndex, minorIndex, idProductConfModel, identifiable, state, idProductFamily, idProductSupply, idFollowingFormModel)"
									+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
			_stmt.setString(1, reference);
			_stmt.setString(2, majorIndex);
			_stmt.setString(3, minorIndex);
			_stmt.setInt(4, _idProductConfModel);
			_stmt.setBoolean(5, identifiable);
			_stmt.setInt(6, state);
			_stmt.setInt(7, productFamily.getIdProductFamily());
			_stmt.setInt(8, _idProductSupply);
			_stmt.setInt(9, _idFollowingFormModel);
			_stmt.executeUpdate();

			_stmt = this.cnxProduct.getCnx().prepareStatement(
					"SELECT * FROM productConf" + " WHERE (reference=?) "
							+ "AND (majorIndex=?) AND (minorIndex=?) ");
			_stmt.setString(1, reference);
			_stmt.setString(2, majorIndex);
			_stmt.setString(3, minorIndex);

			_rs = _stmt.executeQuery();
			if (_rs.next()) {
				_productConf = this.getProductConf(_rs);
			} else {
				throw new ProductConfDaoException(exceptionMsg);
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

		return _productConf;
	}

	@SuppressWarnings("unused")
	@Override
	public void updateProductConf(ProductConf productConf, String reference,
			String majorIndex, String minorIndex,
			ProductConfModel productConfModel, Boolean identifiable, int state,
			ProductFamily productFamily, ProductSupply productSupply,
			FollowingFormModel followingFormModel) throws SQLException,
			ProductConfDaoException {
		int _idProductConfModel = 0;
		if (null != productConfModel) {
			_idProductConfModel = productConfModel.getIdProductConfModel();
		} else {
			_idProductConfModel = 0;
		}
		System.out.println("ProductConf: " + productConf.toString());
		System.out.println("reference : " + reference);
		System.out.println("majorIndex  : " + majorIndex);
		System.out.println("minorIndex   : " + minorIndex);
		System.out.println("ProductConfModel : " + productConfModel.toString());
		System.out.println("identifiable : " + identifiable);
		System.out.println("state : " + state);
		System.out.println("ProductFamily : " + productFamily.toString());
		System.out.println("ProductSupply : " + productSupply.toString());
		System.out.println("FollowingFormModel : "
				+ followingFormModel.toString());
		int _idProductSupply = 0;
		if (null != productSupply) {
			_idProductSupply = productSupply.getIdProductSupply();
		} else {
			_idProductSupply = 0;
		}
		int _idFollowingFormModel = 0;
		if (null != followingFormModel) {
			_idFollowingFormModel = followingFormModel
					.getIdFollowingFormmodel();
		} else {
			_idFollowingFormModel = 0;
		}
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		try {
			_stmt = this.cnxProduct
					.getCnx()
					.prepareStatement(
							"UPDATE productConf "
									+ "SET reference=?, majorIndex=?, minorIndex=?, idProductConfModel=?, identifiable=?, state=?, idProductFamily=?, idProductSupply=?, idFollowingFormModel=?"
									+ " WHERE (idProductConf=?)");
			_stmt.setString(1, reference);
			_stmt.setString(2, majorIndex);
			_stmt.setString(3, minorIndex);
			_stmt.setInt(4, _idProductConfModel);
			_stmt.setBoolean(5, identifiable);
			_stmt.setInt(6, state);
			_stmt.setInt(7, productFamily.getIdProductFamily());
			_stmt.setInt(8, _idProductSupply);
			_stmt.setInt(9, _idFollowingFormModel);
			_stmt.setInt(10, productConf.getIdProductConf());
			_stmt.executeUpdate();

			// Update object
			_stmt = this.cnxProduct.getCnx().prepareStatement(
					"SELECT * FROM productConf" + " WHERE (idProductConf=?)");
			_stmt.setInt(1, productConf.getIdProductConf());

			_rs = _stmt.executeQuery();
			if (_rs.next()) {
				this.getProductConf(_rs);
			} else {
				throw new ProductConfDaoException(exceptionMsg);
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
	}

	@Override
	public List<ProductConf> getProductConfLazy(Map<String, String> filters,
			int limit, int maxperpage) throws SQLException {
		List<ProductConf> _productConf = new ArrayList<ProductConf>();
		PreparedStatement _stmt = null;
		ResultSet _rs = null;

		String rqt1 = "SELECT * FROM productConf pc, productFamily pf WHERE pc.idProductFamily = pf.idProductFamily ";
		String rqt2 = " ORDER BY pc.reference ASC, pc.majorIndex ASC, pc.minorIndex ASC ";
		if (filters.containsKey("minorIndex") == true) {
			rqt1 += "AND pc.minorIndex LIKE '%" + filters.get("minorIndex")
					+ "%' ";

		}
		if (filters.containsKey("majorIndex") == true) {
			rqt1 += "AND pc.majorIndex LIKE '%" + filters.get("majorIndex")
					+ "%' ";

		}
		if (filters.containsKey("state") == true) {
			rqt1 += "AND pc.state LIKE '%" + filters.get("state") + "%' ";

		}
		if (filters.containsKey("reference") == true) {
			rqt1 += "AND pc.reference LIKE '%" + filters.get("reference")
					+ "%' ";

		}

		if (filters.containsKey("productFamily") == true) {
			rqt1 += "AND pf.name LIKE '%" + filters.get("productFamily") + "%' ";

		}
		if (filters.containsKey("productSupply") == true) {
			rqt1 += "AND ps.name LIKE '%" + filters.get("productSupply") + "%' ";
		}
		
		String rqt = rqt1 + rqt2;
		
		System.out.println("REQUETE : " + rqt + "LIMIT " + limit + ","
				+ maxperpage + ";");
		int i = 0;
		try {
			_stmt = this.cnxProduct.getCnx().prepareStatement(
					rqt + "LIMIT ?, ?;");
			_stmt.setInt(1, limit);
			_stmt.setInt(2, maxperpage);
			_rs = _stmt.executeQuery();
			while (_rs.next()) {
				ProductConf _productConftmp = this.getProductConf(_rs);
				_productConf.add(_productConftmp);
				i++;
			}
		} catch (Exception e) {
		} finally {
			if (null != _rs) {
				_rs.close();
			}
			if (null != _stmt) {
				_stmt.close();
			}
		}
		System.out.println("Taille productConf " + _productConf.size() + "ET i = " + i);
		return _productConf;
	}

	@Override
	public int countProductConf(Map<String, String> filters)
			throws SQLException {
		PreparedStatement _stmt = null;
		ResultSet _rs = null;
		int count = 0;

		String rqt = "SELECT COUNT(*) FROM productConf pc, productFamily pf WHERE pc.idProductFamily = pf.idProductFamily ";
		if (filters.containsKey("minorIndex") == true) {
			rqt += "AND pc.minorIndex LIKE '%" + filters.get("minorIndex")
					+ "%' ";

		}
		if (filters.containsKey("majorIndex") == true) {
			rqt += "AND pc.majorIndex LIKE '%" + filters.get("majorIndex")
					+ "%' ";

		}
		if (filters.containsKey("state") == true) {
			rqt += "AND pc.state LIKE '%" + filters.get("state") + "%' ";

		}
		if (filters.containsKey("reference") == true) {
			rqt += "AND pc.reference LIKE '%" + filters.get("reference")
					+ "%' ";

		}

		if (filters.containsKey("productFamily") == true) {
			rqt += "AND pf.name LIKE '%" + filters.get("productFamily") + "%' ";

		}
		if (filters.containsKey("productSupply") == true) {
			rqt += "AND ps.name LIKE '%" + filters.get("productSupply") + "%' ";
		}
		System.out.println("REQUETE : " + rqt);
		try {
			_stmt = this.cnxProduct.getCnx().prepareStatement(rqt);
			_rs = _stmt.executeQuery();
			if (_rs.next()) {
				count = _rs.getInt(1);
			}
		} catch (Exception e) {
		} finally {
			if (null != _rs) {
				_rs.close();
			}
			if (null != _stmt) {
				_stmt.close();
			}
		}
		System.out.println("COUNT " + count);
		return count;
	}
}