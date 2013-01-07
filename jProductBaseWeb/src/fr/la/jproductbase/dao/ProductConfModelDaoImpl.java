package fr.la.jproductbase.dao;

import fr.la.jproductbase.metier.ProductConfModel;
import fr.la.jproductbase.metier.ProductType;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;

public class ProductConfModelDaoImpl implements ProductConfModelDao {
    // private static String exceptionMsg =
    // "Modéle de produit inconnu dans la base de données.";

    private ConnectionProduct cnxProduct;

    public ProductConfModelDaoImpl(ConnectionProduct cnxProduct) {
        this.cnxProduct = cnxProduct;
    }

    @Override
    public ProductConfModel getProductConfModel(int idProductConfModel)
            throws SQLException {
        ProductConfModel _productConfModel = null;
        PreparedStatement _stmt = null;
        ResultSet _rs = null;

        try {
            _stmt = this.cnxProduct
                    .getCnx()
                    .prepareStatement(
                    "SELECT * FROM productConfModel WHERE idProductConfModel=?");
            _stmt.setInt(1, idProductConfModel);
            _rs = _stmt.executeQuery();

            if (_rs.next()) {
                _productConfModel = this.getProductConfModel(_rs);
            } else {
                _productConfModel = null;
            }
        } catch (NamingException e) {
        } finally {
            if (null != _rs) {
                _rs.close();
            }
            if (null != _stmt) {
                _stmt.close();
            }
        }

        return _productConfModel;
    }

    @Override
    public ProductConfModel getProductConfModel(String reference)
            throws SQLException {
        ProductConfModel _productConfModel = null;
        PreparedStatement _stmt = null;
        ResultSet _rs = null;

        try {
            _stmt = this.cnxProduct.getCnx().prepareStatement(
                    "SELECT * FROM productConfModel WHERE reference=?");
            _stmt.setString(1, reference);
            _rs = _stmt.executeQuery();

            if (_rs.next()) {
                _productConfModel = this.getProductConfModel(_rs);
            } else {
                _productConfModel = null;
            }
        } catch (NamingException e) {
        } finally {
            if (null != _rs) {
                _rs.close();
            }
            if (null != _stmt) {
                _stmt.close();
            }
        }

        return _productConfModel;
    }

    @Override
    public List<ProductConfModel> getActiveProductConfModels(
            ProductType productType) throws SQLException {
        List<ProductConfModel> _productConfModels = new ArrayList<ProductConfModel>();
        PreparedStatement _stmt = null;
        ResultSet _rs = null;

        try {
            _stmt = this.cnxProduct
                    .getCnx()
                    .prepareStatement(
                    "SELECT * FROM productConfModel, productConf, productFamily, productType"
                    + " WHERE (productConfModel.idProductConfModel = productConf.idProductConfModel)"
                    + " AND (productConf.idProductFamily = productFamily.idProductFamily)"
                    + " AND (productFamily.idProductType = productType.idProductType)"
                    + " AND (productType.idProductType = ?)"
                    + " AND (productConfModel.state=1)"
                    + " ORDER BY productConfModel.reference");
            _stmt.setInt(1, productType.getIdProductType());
            _rs = _stmt.executeQuery();

            while (_rs.next()) {
                ProductConfModel _productConfModel = this
                        .getProductConfModel(_rs);
                _productConfModels.add(_productConfModel);
            }
        } catch (NamingException e) {
        } finally {
            if (null != _rs) {
                _rs.close();
            }
            if (null != _stmt) {
                _stmt.close();
            }
        }

        return _productConfModels;
    }

    @Override
    public List<ProductConfModel> getActiveProductConfModels() throws SQLException {
        List<ProductConfModel> _productConfModels = new ArrayList<ProductConfModel>();
        PreparedStatement _stmt = null;
        ResultSet _rs = null;

        try {
            _stmt = this.cnxProduct
                    .getCnx()
                    .prepareStatement(
                    "SELECT * FROM productConfModel WHERE state = 1 Order by reference desc;");
            //ORDER BY productConfModel.reference");
            _rs = _stmt.executeQuery();

            while (_rs.next()) {
                ProductConfModel _productConfModel = this
                        .getProductConfModel(_rs);
                _productConfModels.add(_productConfModel);
            }
        } catch (NamingException e) {
        } finally {
            if (null != _rs) {
                _rs.close();
            }
            if (null != _stmt) {
                _stmt.close();
            }
        }

        return _productConfModels;
    }

    @Override
    public List<ProductConfModel> getProductConfModels() throws SQLException {
        List<ProductConfModel> _productConfModels = new ArrayList<ProductConfModel>();
        PreparedStatement _stmt = null;
        ResultSet _rs = null;

        try {
            _stmt = this.cnxProduct
                    .getCnx()
                    .prepareStatement(
                    "SELECT * FROM productConfModel");
            _rs = _stmt.executeQuery();

            while (_rs.next()) {
                ProductConfModel _productConfModel = this
                        .getProductConfModel(_rs);
                _productConfModels.add(_productConfModel);
            }
        } catch (NamingException e) {
        } finally {
            if (null != _rs) {
                _rs.close();
            }
            if (null != _stmt) {
                _stmt.close();
            }
        }

        return _productConfModels;
    }

    @Override
    public List<ProductConfModel> getProductConfModels(int type) throws SQLException {
        List<ProductConfModel> _productConfModels = new ArrayList<ProductConfModel>();
        PreparedStatement _stmt = null;
        ResultSet _rs = null;

        try {
            _stmt = this.cnxProduct
                    .getCnx()
                    .prepareStatement(
                    "SELECT distinct pcm.* FROM productConfModel pcm, productConf pc, productFamily pf "
                    + "WHERE pcm.idProductConfModel = pc.idProductConfModel "
                    + "AND pc.idProductFamily = pf.idProductFamily "
                    + "AND pf.idProductType = ? "
                    + "ORDER BY pcm.idProductConfModel");
            _stmt.setInt(1, type);
            _rs = _stmt.executeQuery();

            while (_rs.next()) {
                ProductConfModel _productConfModel = this
                        .getProductConfModel(_rs);
                _productConfModels.add(_productConfModel);
            }
        } catch (NamingException e) {
        } finally {
            if (null != _rs) {
                _rs.close();
            }
            if (null != _stmt) {
                _stmt.close();
            }
        }

        return _productConfModels;
    }

    @Override
    public void addProductConfModels(ProductConfModel _productConfModel) throws SQLException {
        PreparedStatement _stmt = null;
        try {
            _stmt = this.cnxProduct
                    .getCnx()
                    .prepareStatement(
                    "INSERT INTO productconfmodel (state, reference, designation) VALUES (?, ?, ?);");
            _stmt.setInt(1, _productConfModel.getState());
            _stmt.setString(2, _productConfModel.getReference());
            _stmt.setString(3, _productConfModel.getDesignation());
            _stmt.executeUpdate();
        } catch (NamingException ex) {
            Logger.getLogger(FollowingFormModelDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delProductConfModels(int id) throws SQLException {
        PreparedStatement _stmt = null;

        try {
            _stmt = this.cnxProduct.getCnx().prepareStatement("DELETE FROM productconfmodel WHERE idproductconfmodel = ?;");
            _stmt.setInt(1, id);
            _stmt.executeUpdate();
        } catch (NamingException ex) {
        }
    }

    @Override
    public void updateProductConfModels(ProductConfModel productConfModel) throws SQLException {
        PreparedStatement _stmt = null;
        try {
            _stmt = this.cnxProduct.getCnx().prepareStatement("UPDATE productconfmodel SET state = ?, reference = ?, designation = ? WHERE idProductConfModel = ?;");
            _stmt.setInt(1, productConfModel.getState());
            _stmt.setString(2, productConfModel.getReference());
            _stmt.setString(3, productConfModel.getDesignation());
            _stmt.setInt(4, productConfModel.getIdProductConfModel());
            _stmt.executeUpdate();
        } catch (NamingException ex) {
            Logger.getLogger(FollowingFormModelDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /*
     * Cr&eacute;er un mod&eacute;le produit &agrave; partir d'un enregistement
     * de la base de donn&eacute;es.
     * 
     * @param rs Enregistement de la base de donn&eacute;es.
     * 
     * @return Mod&eacute;le produit.
     */
    private ProductConfModel getProductConfModel(ResultSet rs)
            throws SQLException {
        int _idProductConfModel = rs.getInt("idProductConfModel");
        Timestamp _timestamp = rs.getTimestamp("timestamp");
        int _state = rs.getInt("state");
        String _reference = rs.getString("reference");
        String _designation = rs.getString("designation");
        ProductConfModel _productConfModel = new ProductConfModel(
                _idProductConfModel, _timestamp, _state, _reference,
                _designation);

        return _productConfModel;
    }

    @Override
    public List<ProductConfModel> getProductConfModelLazy(Map<String, String> filters, int limit, int maxperpage)
            throws SQLException {
        List<ProductConfModel> _productConf = new ArrayList<ProductConfModel>();
        PreparedStatement _stmt = null;
        ResultSet _rs = null;
        System.out.println("FILTRE CONF" + filters.toString());

        try {
            _stmt = this.cnxProduct.getCnx().prepareStatement(
                    "SELECT * FROM productconfmodel LIMIT ?, ?;");
            _stmt.setInt(1, limit);
            _stmt.setInt(2, maxperpage);
            _rs = _stmt.executeQuery();

            while (_rs.next()) {
                ProductConfModel _productConftmp = this.getProductConfModel(_rs);
                _productConf.add(_productConftmp);
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
        return _productConf;
    }

    @Override
    public int countProductConfModel(Map<String, String> filters) throws SQLException {
        PreparedStatement _stmt = null;
        ResultSet _rs = null;
        int count = 0;

        try {
            _stmt = this.cnxProduct.getCnx().prepareStatement(
                    "SELECT count(*) FROM productconfmodel;");
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
        return count;
    }
}