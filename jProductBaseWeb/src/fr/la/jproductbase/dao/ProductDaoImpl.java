package fr.la.jproductbase.dao;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import fr.la.configfilereader.ConfigFileReaderException;
import fr.la.jproductbase.metier.Product;
import fr.la.jproductbase.metier.ProductConf;
import fr.la.jproductbase.metier.ProductConfModel;
import fr.la.jproductbase.metier.ProductType;
import java.util.Map;

public class ProductDaoImpl implements ProductDao {

    private static String exceptionMsg = "Produit inconnu dans la base de donnÃ©es.";
    private ConnectionProduct cnxProduct;

    public ProductDaoImpl(ConnectionProduct cnxProduct) {
        this.cnxProduct = cnxProduct;
    }

    @Override
    public Product getProduct(int idProduct) throws SQLException {
        Product _product = null;
        PreparedStatement _stmt = null;
        ResultSet _rs = null;

        try {
            _stmt = this.cnxProduct.getCnx().prepareStatement(
                    "SELECT * FROM product WHERE idProduct=?");
            _stmt.setInt(1, idProduct);
            _rs = _stmt.executeQuery();

            if (_rs.next()) {
                _product = this.getProduct(_rs);
            } else {
                _product = null;
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

        return _product;
    }

    @Override
    public Product getProduct(ProductConf productConf, String serialNumber,
            String datecode) throws SQLException {
        Product _product = null;
        PreparedStatement _stmt = null;
        ResultSet _rs = null;

        System.out.println(" get Product " + productConf + " " + serialNumber
                + " " + datecode);

        int idConf = 0;
        if (null != productConf) {
            idConf = productConf.getIdProductConf();
        }

        try {
            _stmt = this.cnxProduct.getCnx().prepareStatement(
                    "SELECT * FROM product" + " WHERE (idProductConf=?)"
                    + " AND (serialNumber=?)" + " AND (datecode=?)");
            _stmt.setInt(1, idConf);
            _stmt.setString(2, serialNumber);
            _stmt.setString(3, datecode);
            _rs = _stmt.executeQuery();
            if (_rs.next()) {
                _product = this.getProduct(_rs);
            } else {
                _product = null;
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

        System.out.println("produit  " + _product);
        return _product;
    }

    @Override
    public Product getProduct(String productConfReference, String serialNumber,
            String datecode) throws SQLException {
        Product _product = null;
        PreparedStatement _stmt = null;
        ResultSet _rs = null;

        try {
            _stmt = this.cnxProduct
                    .getCnx()
                    .prepareStatement(
                    "SELECT * FROM product "
                    + "INNER JOIN productConf ON (product.idProductConf = productConf.idProductConf) "
                    + "WHERE (productConf.reference=?) AND (product.serialNumber=?) AND (product.datecode=?)");
            _stmt.setString(1, productConfReference);
            _stmt.setString(2, serialNumber);
            _stmt.setString(3, datecode);
            _rs = _stmt.executeQuery();
            if (_rs.next()) {
                _product = this.getProduct(_rs);
            } else {
                _product = null;
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

        return _product;
    }

    @Override
    public Product getMainProduct(Product carte) throws SQLException {
        Product _product = null;
        PreparedStatement _stmt = null;
        ResultSet _rs = null;

        try {
            _stmt = this.cnxProduct.getCnx().prepareStatement(
                    "SELECT p.* FROM product p, product c, product_product pp"
                    + " WHERE (c.idProduct=?)"
                    + " AND (p.idProduct = pp.idProduct)"
                    + " AND (pp.idProductComponent = c.idProduct)");
            _stmt.setInt(1, carte.getIdProduct());
            _rs = _stmt.executeQuery();
            if (_rs.next()) {
                _product = this.getProduct(_rs);
            } else {
                _product = null;
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

        return _product;
    }

    @Override
    public List<Product> getProducts() throws SQLException {
        List<Product> _products = new ArrayList<Product>();
        PreparedStatement _stmt = null;
        ResultSet _rs = null;

        try {
            _stmt = this.cnxProduct.getCnx().prepareStatement(
                    "SELECT * FROM product");
            _rs = _stmt.executeQuery();

            while (_rs.next()) {
                Product _product = this.getProduct(_rs);
                _products.add(_product);
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

        return _products;
    }

    @Override
    public List<Product> getProducts(ProductType productType)
            throws SQLException {
        List<Product> _products = new ArrayList<Product>();
        PreparedStatement _stmt = null;
        ResultSet _rs = null;

        try {
            _stmt = this.cnxProduct
                    .getCnx()
                    .prepareStatement(
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

        return _products;
    }

    @Override
    public List<Product> getProducts(ProductConfModel productConfModel)
            throws SQLException {
        List<Product> _products = new ArrayList<Product>();
        PreparedStatement _stmt = null;
        ResultSet _rs = null;

        try {
            _stmt = this.cnxProduct
                    .getCnx()
                    .prepareStatement(
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

        return _products;
    }

    @Override
    public List<Product> getProducts(ProductConf productConf)
            throws SQLException {
        List<Product> _products = new ArrayList<Product>();
        PreparedStatement _stmt = null;
        ResultSet _rs = null;

        try {
            _stmt = this.cnxProduct
                    .getCnx()
                    .prepareStatement(
                    "SELECT * FROM product, productConf"
                    + " WHERE (product.idProductConf=productConf.idProductConf)"
                    + " AND (productConf.idProductConf=?)");
            _stmt.setInt(1, productConf.getIdProductConf());
            _rs = _stmt.executeQuery();

            while (_rs.next()) {
                Product _product = this.getProduct(_rs);
                _products.add(_product);
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

        return _products;
    }

    @Override
    public List<Product> getProductsEnables(int idproduct, String reference)
            throws SQLException {
        List<Product> _products = new ArrayList<Product>();
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
            _stmt = this.cnxProduct.getCnx().prepareStatement(_requete);
            _stmt.setInt(1, idproduct);
            if (null != reference && !reference.equals("")) {
                _stmt.setInt(2, idproduct);
            }
            _rs = _stmt.executeQuery();

            while (_rs.next()) {
                Product _product = this.getProduct(_rs);
                _products.add(_product);
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

        return _products;
    }

    @Override
    public List<Product> getProductsRecordables(String modele)
            throws SQLException {
        List<Product> _products = new ArrayList<Product>();
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
            _stmt = this.cnxProduct.getCnx().prepareStatement(_requete);
            _rs = _stmt.executeQuery();

            while (_rs.next()) {
                Product _product = this.getProduct(_rs);
                _products.add(_product);
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

        return _products;
    }

    @Override
    public List<Product> getProductsSearch(int startingAt, int maxPerPage, Map<String, String> filters, int type) throws SQLException {
        System.out.println("FILTRE : " + filters.toString());
        List<Product> _products = new ArrayList<Product>();
        PreparedStatement _stmt = null;
        ResultSet _rs = null;

        String rqt = "SELECT * FROM product p , productConf pc, productFamily pf, productConfModel pcm "
                + "WHERE p.idProductConf = pc.idProductConf AND pc.idProductFamily = pf.idProductFamily AND pc.idProductConfModel = pcm.idProductConfModel ";
        if (filters.containsKey("productConf.productConfModel.reference") == true) {
            rqt += "AND pcm.reference LIKE '%" + filters.get("productConf.productConfModel.reference") + "%' ";
        }
        if (filters.containsKey("productConf.productFamily.name") == true) {
            rqt += "AND pf.name LIKE  '%" + filters.get("productConf.productFamily.name") + "%' ";
        }
        if (filters.containsKey("productConf.reference") == true) {
            rqt += "AND pc.reference LIKE '%" + filters.get("productConf.reference") + "%' ";
        }
        if (filters.containsKey("productConf.majorIndex") == true) {
            rqt += "AND pc.majorindex LIKE '%" + filters.get("productConf.majorIndex") + "%' ";
        }
        if (filters.containsKey("productConf.minorIndex") == true) {
            rqt += "AND pc.minorIndex LIKE '%" + filters.get("productConf.minorIndex") + "%' ";
        }
        if (filters.containsKey("datecode") == true) {
            rqt += "AND p.datecode LIKE '%" + filters.get("datecode") + "%' ";
        }
        if (filters.containsKey("serialNumber") == true) {
            rqt += "AND p.serialNumber LIKE '%" + filters.get("serialNumber") + "%' ";
        }
        if (filters.containsKey("state") == true) {
            rqt += "AND p.state LIKE '%" + filters.get("state") + "%' ";
        }
        if (filters.containsKey("macAddress") == true) {
            rqt += "AND p.macAddress LIKE '%" + filters.get("macAddress") + "%' ";
        }
        if (filters.containsKey("providerCode") == true) {
            rqt += "AND p.providerCode LIKE '%" + filters.get("providerCode") + "%' ";
        }
        if (type != 0) {
            rqt += "AND pf.idProductType = '" + type + "' ";
        }
        System.out.println("REQUETE " + rqt);
        try {
            _stmt = this.cnxProduct.getCnx().prepareStatement(rqt + " LIMIT ?, ?;");
            _stmt.setInt(1, startingAt);
            _stmt.setInt(2, maxPerPage);
            _rs = _stmt.executeQuery();

            while (_rs.next()) {
                Product _product = this.getProduct(_rs);
                _products.add(_product);
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
        return _products;
    }

    @Override
    public int countProducts(Map<String, String> filters, int type) throws SQLException {

        PreparedStatement _stmt = null;
        ResultSet _rs = null;
        int count = 0;
        String rqt = "SELECT COUNT(*) FROM product p , productConf pc, productFamily pf, productConfModel pcm "
                + "WHERE p.idProductConf = pc.idProductConf AND pc.idProductFamily = pf.idProductFamily AND pc.idProductConfModel = pcm.idProductConfModel ";
        if (filters.containsKey("productConf.productConfModel.reference") == true) {
            rqt += "AND pcm.reference LIKE '%" + filters.get("productConf.productConfModel.reference") + "%' ";
        }
        if (filters.containsKey("productConf.productFamily.name") == true) {
            rqt += "AND pf.name LIKE  '%" + filters.get("productConf.productFamily.name") + "%' ";
        }
        if (filters.containsKey("productConf.reference") == true) {
            rqt += "AND pc.reference LIKE '%" + filters.get("productConf.reference") + "%' ";
        }
        if (filters.containsKey("productConf.majorIndex") == true) {
            rqt += "AND pc.majorindex LIKE '%" + filters.get("productConf.majorIndex") + "%' ";
        }
        if (filters.containsKey("productConf.minorIndex") == true) {
            rqt += "AND pc.minorIndex LIKE '%" + filters.get("productConf.minorIndex") + "%' ";
        }
        if (filters.containsKey("datecode") == true) {
            rqt += "AND p.datecode LIKE '%" + filters.get("datecode") + "%' ";
        }
        if (filters.containsKey("serialNumber") == true) {
            rqt += "AND p.serialNumber LIKE '%" + filters.get("serialNumber") + "%' ";
        }
        if (filters.containsKey("state") == true) {
            rqt += "AND p.state LIKE '%" + filters.get("state") + "%' ";
        }
        if (filters.containsKey("macAddress") == true) {
            rqt += "AND p.macAddress LIKE '%" + filters.get("macAddress") + "%' ";
        }
        if (filters.containsKey("providerCode") == true) {
            rqt += "AND p.providerCode LIKE '%" + filters.get("providerCode") + "%' ";
        }
        if (type != 0) {
            rqt += "AND pf.idProductType = '" + type + "' ";
        }
        try {
            _stmt = this.cnxProduct.getCnx().prepareStatement(rqt + ";");
            _rs = _stmt.executeQuery();

            while (_rs.next()) {
                count = _rs.getInt(1);
            }
        } catch (NamingException | SQLException e) {
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

    @Override
    public List<Product> getProducts(String serialNumber, String datecode)
            throws SQLException {
        List<Product> _products = new ArrayList<Product>();
        PreparedStatement _stmt = null;
        ResultSet _rs = null;

        try {
            _stmt = this.cnxProduct
                    .getCnx()
                    .prepareStatement(
                    "SELECT * FROM product WHERE (serialNumber=?) AND (datecode=?)");
            _stmt.setString(1, serialNumber);
            _stmt.setString(2, datecode);
            _rs = _stmt.executeQuery();

            while (_rs.next()) {
                Product _product = this.getProduct(_rs);
                _products.add(_product);
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

        return _products;
    }

    @Override
    public boolean isNeedDispensation(Product product) throws SQLException {
        boolean _isNeedDispensation = false;

        if (null != product) {
            PreparedStatement _stmt = null;
            ResultSet _rs = null;

            try {
                _stmt = this.cnxProduct
                        .getCnx()
                        .prepareStatement(
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

        return _isNeedDispensation;
    }

    @Override
    public List<Product> getProductsDispensationNeeded() throws SQLException {
        List<Product> _products = new ArrayList<Product>();
        PreparedStatement _stmt = null;
        ResultSet _rs = null;

        try {
            _stmt = this.cnxProduct
                    .getCnx()
                    .prepareStatement(
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

        return _products;
    }

    @Override
    public Product addProduct(ProductConf productConf, String serialNumber,
            String datecode, String macAddress, String providerCode)
            throws SQLException, ProductDaoException {
        Product _product = null;
        PreparedStatement _stmt = null;
        ResultSet _rs = null;

        if (null != productConf) {
            try {
                _stmt = this.cnxProduct
                        .getCnx()
                        .prepareStatement(
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
                _stmt = this.cnxProduct.getCnx()
                        .prepareStatement(
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
                    throw new ProductDaoException(exceptionMsg);
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
            throw new ProductDaoException("Configuration produit inconnue.");
        }

        return _product;
    }

    @Override
    public void updateProduct(Product product) throws SQLException,
            ProductDaoException {
        PreparedStatement _stmt = null;

        try {
            _stmt = this.cnxProduct
                    .getCnx()
                    .prepareStatement(
                    "UPDATE product "
                    + "SET serialNumber=?, datecode=?, idProductConf=?, providerCode=?"
                    + " WHERE (idProduct=?)");
            _stmt.setString(1, product.getSerialNumber());
            _stmt.setString(2, product.getDatecode());
            _stmt.setInt(3, product.getProductConf().getIdProductConf());
            _stmt.setString(4, product.getProviderCode());
            _stmt.setInt(5, product.getIdProduct());
            _stmt.executeUpdate();

            _stmt = this.cnxProduct.getCnx().prepareStatement(
                    "SELECT * FROM product" + " WHERE (idProduct=?)");
            _stmt.setInt(1, product.getIdProduct());

            ResultSet _rs = _stmt.executeQuery();
            if (_rs.next()) {
                this.getProduct(_rs);
            } else {
                throw new ProductDaoException(exceptionMsg);
            }
        } catch (NamingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if (null != _stmt) {
                _stmt.close();
            }
        }
    }

    @Override
    public void updateProduct(Product product, ProductConf productConf,
            String serialNumber, String dateCode, String macAddress,
            String providerCode, int state) throws SQLException,
            ProductDaoException {

        int _idProductConf = 0;
        if (null != productConf) {
            _idProductConf = productConf.getIdProductConf();
        } else {
            _idProductConf = 0;
        }

        PreparedStatement _stmt = null;
        ResultSet _rs = null;

        try {
            _stmt = this.cnxProduct
                    .getCnx()
                    .prepareStatement(
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
            _stmt = this.cnxProduct.getCnx().prepareStatement(
                    "SELECT * FROM product" + " WHERE (idProduct=?)");
            _stmt.setInt(1, product.getIdProduct());

            _rs = _stmt.executeQuery();
            if (_rs.next()) {
                this.getProduct(_rs);
            } else {
                throw new ProductDaoException(exceptionMsg);
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
    public void updateProduct(Product product, String macAddress)
            throws SQLException, ProductDaoException {

        PreparedStatement _stmt = null;
        ResultSet _rs = null;

        try {
            _stmt = this.cnxProduct.getCnx().prepareStatement(
                    "UPDATE product " + "SET macAddress=?"
                    + " WHERE (idProduct=?)");
            _stmt.setString(1, macAddress);
            _stmt.setInt(2, product.getIdProduct());
            _stmt.executeUpdate();

            // Update object
            _stmt = this.cnxProduct.getCnx().prepareStatement(
                    "SELECT * FROM product" + " WHERE (idProduct=?)");
            _stmt.setInt(1, product.getIdProduct());

            _rs = _stmt.executeQuery();
            if (_rs.next()) {
                this.getProduct(_rs);
            } else {
                throw new ProductDaoException(exceptionMsg);
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
    public List<Product> getProductComponents(Product product)
            throws SQLException {
        List<Product> _productComponents = new ArrayList<Product>();

        if (null != product) {
            PreparedStatement _stmt = null;
            ResultSet _rs = null;

            try {
                _stmt = this.cnxProduct
                        .getCnx()
                        .prepareStatement(
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

        return _productComponents;
    }

    @Override
    public void addProductComponent(Product product, Product productComponent)
            throws SQLException, ProductDaoException {
        int _idProduct = product.getIdProduct();
        int _idProductComponent = productComponent.getIdProduct();
        PreparedStatement _stmt = null;
        ResultSet _rs = null;
        System.out.println("rajour d'une carte" + _idProduct + " / "
                + _idProductComponent);
        try {
            _stmt = this.cnxProduct.getCnx().prepareStatement(
                    "INSERT INTO product_product (idProduct, idProductComponent)"
                    + " VALUES (?, ?)");
            _stmt.setInt(1, _idProduct);
            _stmt.setInt(2, _idProductComponent);
            _stmt.executeUpdate();

            // Retrieve product_product data
            _stmt = this.cnxProduct.getCnx().prepareStatement(
                    "SELECT * FROM product_product" + " WHERE (idProduct=?)"
                    + " AND (idProductComponent=?)");
            _stmt.setInt(1, _idProduct);
            _stmt.setInt(2, _idProductComponent);

            _rs = _stmt.executeQuery();
            if (!_rs.next()) {
                throw new ProductDaoException(exceptionMsg);
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
    public void removeProductComponent(Product product, Product productComponent)
            throws SQLException {
        if ((null != product) && (null != productComponent)) {
            System.out.println("suppression" + product.getIdProduct() + " / "
                    + productComponent.getIdProduct());
            try {
                System.out.println("get product components "
                        + product.getProductComponents());
            } catch (ConfigFileReaderException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            PreparedStatement _stmt = null;
            try {
                _stmt = this.cnxProduct.getCnx().prepareStatement(
                        "DELETE FROM product_product " + "WHERE ((idProduct=?)"
                        + " AND (idProductComponent=?))");
            } catch (NamingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            _stmt.setInt(1, product.getIdProduct());
            _stmt.setInt(2, productComponent.getIdProduct());
            _stmt.executeUpdate();

            // Update product

            product.removeProductComponent(productComponent);

            _stmt.close();
        } else {
            // Nothing to do
        }
    }

    @Override
    public List<Product> getProductWithMother(int startingAt, int maxPerPage, Map<String, String> filters) throws SQLException {

        List<Product> _product = this.getProductsSearch(startingAt, maxPerPage, filters, 1);
        for (Product _p : _product) {
            ResultSet _rs = null;
            PreparedStatement _stmt = null;
            try {
                // String sql = "SELECT * FROM product WHERE idproduct = (SELECT idproduct FROM product_product WHERE idProductComponent = " + _p.getIdProduct() + ");";
                String sql = "SELECT count(idproduct) as total FROM product_product WHERE idProductComponent = " + _p.getIdProduct() + ";";
                _stmt = this.cnxProduct.getCnx().prepareStatement(sql);
                _rs = _stmt.executeQuery();
                int i = 0;
                while (_rs.next()) {
                    i = _rs.getInt("total");
                }
                if (i == 1) {
                    String sql2 = "SELECT * FROM product WHERE idproduct = (SELECT idproduct FROM product_product WHERE idProductComponent = " + _p.getIdProduct() + ");";
                    _stmt = this.cnxProduct.getCnx().prepareStatement(sql2);
                    _rs = _stmt.executeQuery();
                    while (_rs.next()) {
                        _p.setMother(this.getProduct(_rs));
                    }
                }
            } catch (NamingException e) {
            }
        }
        return _product;
    }

    @Override
    public Product getProductWithProductConfRef(String reference) throws SQLException{
    	Product product = null;
    	 PreparedStatement _stmt = null;
         ResultSet _rs = null;

         try {
             _stmt = this.cnxProduct
                     .getCnx()
                     .prepareStatement("select * from product where idproductconf = (select idproductconf from productconf where reference = ?);");
             _stmt.setString(1, reference);
             _rs = _stmt.executeQuery();

            if (_rs.next()) {
                 product = this.getProduct(_rs);
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
        ProductConfDao _productConfDao = new ProductConfDaoImpl(this.cnxProduct);
        int _idProductConf = rs.getInt("idProductConf");
        ProductConf _productConf = _productConfDao
                .getProductConf(_idProductConf);

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
        SoftwareDao _softwareDao = new SoftwareDaoImpl(this.cnxProduct);
        _softwareDao.getSoftwares(_product);

        return _product;
    }

    /* 20-04-12 : RMO : CrÃ©ation de la mÃ©thode */
    @Override
    public Product setProductFEDDtoLAI(int idProductFEDD, ProductConf config,
            String serialNumber, String datecode) throws SQLException,
            ProductDaoException {
        System.out.println("id" + idProductFEDD + " " + config + " "
                + serialNumber + " " + datecode);
        Product _product = null;
        PreparedStatement _stmt = null;
        ResultSet _rs = null;
        String ordreInsertion = "";
        String ordreSelect = "";

        try {
            /* 1 +++++++++++++++++++++++++++++++++++++ */
            /* Partie insertion dans la table product */
            /* -------------------------------------- */
            ordreInsertion = "INSERT INTO `productBase`.`product` "
                    + "(timeStamp, state, serialNumber, datecode, macAddress, providerCode, idProductConf) "
                    + "SELECT p.timestamp, p.state, p.serialNumber, p.datecode, p.macAddress, p.providerCode, p.idProductConf "
                    + "FROM `FEDDproductBase`.`product` p "
                    + "WHERE p.idProduct = ? "
                    + "UNION "
                    + "SELECT p2.timestamp, p2.state, p2.serialNumber, p2.datecode, p2.macAddress, p2.providerCode, p2.idProductConf "
                    + "FROM `FEDDproductBase`.`product` p1, `FEDDproductBase`.`product` p2, `FEDDproductBase`.`product_product` pp "
                    + "WHERE p1.idProduct = pp.idProduct AND pp.idProductComponent = p2.idProduct "
                    + "AND p1.idProduct = ? "
                    + "AND p2.idProduct NOT IN (1, 2, 3)";
            _stmt = this.cnxProduct.getCnx().prepareStatement(ordreInsertion);
            _stmt.setInt(1, idProductFEDD);
            _stmt.setInt(2, idProductFEDD);
            _stmt.executeUpdate();

            int idProductLAI = this.getProduct(config, serialNumber, datecode)
                    .getIdProduct();

            System.out.println("prod : SN/ " + serialNumber + " -- DC/ "
                    + datecode + " -- CONF/ " + config.getIdProductConf());
            System.out.println("ID FEDD ::: " + idProductFEDD + " <<==>> "
                    + idProductLAI + " ::: ID LAI");

            /* 2 +++++++++++++++++++++++++++++++++++++++++++++ */
            /* Partie insertion dans la table product_product */
            /* ---------------------------------------------- */
            ordreInsertion = "INSERT INTO `productBase`.`product_product` "
                    + "(idProduct, idProductComponent) "
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
                    + "AND pp.idProductComponent = p4.idProduct";
            _stmt = this.cnxProduct.getCnx().prepareStatement(ordreInsertion);
            _stmt.setInt(1, idProductFEDD);
            _stmt.executeUpdate();

            /* 3 ++++++++++++++++++++++++++++++++++++++++++++++ */
            /* Partie insertion dans la table product_software */
            /* ----------------------------------------------- */
            ordreInsertion = "INSERT INTO `productBase`.`product_software` "
                    + "(idProduct, idSoftware) " + "SELECT ?, ps.idSoftware "
                    + "FROM `FEDDproductBase`.`product_software` ps "
                    + "WHERE ps.idProduct = ?";
            _stmt = this.cnxProduct.getCnx().prepareStatement(ordreInsertion);
            _stmt.setInt(1, idProductLAI);
            _stmt.setInt(2, idProductFEDD);
            _stmt.executeUpdate();

            /* 4 ++++++++++++++++++++++++++++++++++++++++++++ */
            /* Partie laissÃ©e en stand by car non testÃ©e faute de donnÃ©es. */
            /* Partie insertion dans la table productComment */
            /* --------------------------------------------- */
            ordreInsertion = "INSERT INTO `productBase`.`productComment` "
                    + "(timestamp, comment, idProduct) "
                    + "SELECT pc.timestamp, pc.comment, ? "
                    + "FROM `FEDDproductBase`.`productComment` pc "
                    + "WHERE pc.idproduct = ? ";
            _stmt = this.cnxProduct.getCnx().prepareStatement(ordreInsertion);
            _stmt.setInt(1, idProductLAI);
            _stmt.setInt(2, idProductFEDD);
            _stmt.executeUpdate();

            /* 5 ++++++++++++++++++++++++++++++++++++ */
            /* Partie insertion dans la table tester */
            /* ------------------------------------- */
            ordreInsertion = "INSERT INTO `testerBase`.`tester` (timestamp, state, name) "
                    + "SELECT tfedd.timestamp, tfedd.state, tfedd.name "
                    + "FROM `FEDDtesterBase`.`tester` tfedd, `FEDDtesterBase`.`testerReport` tr "
                    + "WHERE tfedd.idTester = tr.idTester "
                    + "AND tr. idProduct = ? AND tfedd.name NOT IN "
                    + "(SELECT tlai.name FROM `testerBase`.`tester` tlai) "
                    + "GROUP BY tfedd.name";
            _stmt = this.cnxProduct.getCnx().prepareStatement(ordreInsertion);
            _stmt.setInt(1, idProductFEDD);
            _stmt.executeUpdate();

            /*
             * //////////////////////////////////////////////////////////////////
             * /
             * /////////////////////////////////////////////////////////////////
             * ///////////////////////////////////////////////////////////////
             */
            /* 6 ++++++++++++++++++++++++++++++++++++++++++ */
            /* Il faudra changer l'idTesterReportNext */
            /* Partie insertion dans la table testerReport */
            /* ------------------------------------------- */

            /*
             * Ajout de colonnes dans les tables testerReport et
             * ProductionFailureReport pour conserver le lien entre les
             * enregistrement de FEDD et de LAI. Ces colonnes une fois les
             * donnÃ©es enregistrÃ©es chez LAI seront supprimÃ©es
             */
            String modifTempTable = "ALTER TABLE `testerBase`.`testerReport` ADD COLUMN idTesterReportFEDD int(10)";
            _stmt = this.cnxProduct.getCnx().prepareStatement(modifTempTable);
            _stmt.executeUpdate();
            modifTempTable = "ALTER TABLE `productBase`.`ProductionFailureReport` ADD COLUMN idProductionFailureReportFEDD int(10)";
            _stmt = this.cnxProduct.getCnx().prepareStatement(modifTempTable);
            _stmt.executeUpdate();
            modifTempTable = "ALTER TABLE `productBase`.`failure` ADD COLUMN idFailureFEDD int(10)";
            _stmt = this.cnxProduct.getCnx().prepareStatement(modifTempTable);
            _stmt.executeUpdate();

            ordreSelect = "SELECT tr.timestamp, tr.state, tr.date, tr.testVersion, tr.result, tr.consoUmini, tr.consoUnomi, "
                    + "tr.consoUmaxi, tr.idTestType, tlai.idTester, tr.operatorCode, ?, tr.idTesterReportNext, tr.idTesterReport "
                    + "FROM `FEDDtesterBase`.`testerReport` tr, `FEDDtesterBase`.`tester` tfedd, `testerBase`.`tester` tlai "
                    + "WHERE tr.idTester = tfedd.idTester AND tfedd.name = tlai.name AND tr.idProduct = ? "
                    + "UNION "
                    + "SELECT tr2.timestamp, tr2.state, tr2.date, tr2.testVersion, tr2.result, tr2.consoUmini, tr2.consoUnomi, "
                    + "tr2.consoUmaxi, tr2.idTestType, 0, tr2.operatorCode, ?, tr2.idTesterReportNext, tr2.idTesterReport "
                    + "FROM `FEDDtesterBase`.`testerReport` tr2 "
                    + "WHERE tr2.idTester = 0 AND tr2.idProduct = ? ";
            ordreInsertion = "INSERT INTO `testerBase`.TesterReport "
                    + "(timestamp, state, date, testVersion, result, consoUmini, consoUnomi, "
                    + "consoUmaxi, idTestType, idTester, operatorCode, idProduct, idTesterReportNext, idTesterReportFEDD) "
                    + ordreSelect;

            _stmt = this.cnxProduct.getCnx().prepareStatement(ordreInsertion);
            _stmt.setInt(1, idProductLAI);
            _stmt.setInt(2, idProductFEDD);
            _stmt.setInt(3, idProductLAI);
            _stmt.setInt(4, idProductFEDD);
            _stmt.executeUpdate();

            _stmt = this.cnxProduct
                    .getCnx()
                    .prepareStatement(
                    "SELECT tr1.idTesterReport, tr2.idTesterReport "
                    + "FROM `testerBase`.`testerReport` tr1, `testerBase`.`testerReport` tr2 "
                    + "WHERE tr1.idTesterReportNext = tr2.idTesterReportFEDD "
                    + "AND tr1.idTesterReportNext IS NOT NULL");
            _rs = _stmt.executeQuery();
            while (_rs.next()) {
                int record = _rs.getInt(1);
                int recordNext = _rs.getInt(2);

                /*
                 * _stmt = this.cnxProduct.getCnx().prepareStatement(
                 * "SELECT idTesterReport FROM `testerBase`.testerReport WHERE idTesterReport = ? "
                 * ); _rs = _stmt.executeQuery();
                 */

                String ordreUpdate = "UPDATE `testerBase`.`testerReport` SET idTesterReportNext = ? "
                        + "WHERE idTesterReport = ?";
                _stmt = this.cnxProduct.getCnx().prepareStatement(ordreUpdate);
                _stmt.setInt(1, recordNext);
                _stmt.setInt(2, record);
                _stmt.executeUpdate();
            }

            /* /////////////////////////////////////////////////////////////// */

            /* 7 +++++++++++++++++++++++++++++++++++ */
            /* Partie laissÃ©e en stand by car non testÃ©e faute de donnÃ©es. */
            /* Partie insertion dans la table defect */
            /* ------------------------------------- */
            ordreInsertion = "INSERT INTO `testerBase`.`defect` "
                    + "(timestamp, state, sequence, testName, function, value, idTesterReport) "
                    + "SELECT dFEDD.timestamp, dFEDD.state, dFEDD.sequence, dFEDD.testName, "
                    + "dFEDD.function, dFEDD.value, tr.idTesterReport "
                    + "FROM `testerBase`.`testerReport` tr, `FEDDtesterBase`.`defect` dFEDD "
                    + "WHERE tr.idproduct = ? "
                    + "AND tr.idTesterReportFEDD = dFEDD.idTesterReport ";
            _stmt = this.cnxProduct.getCnx().prepareStatement(ordreInsertion);
            _stmt.setInt(1, idProductLAI);
            _stmt.executeUpdate();

            /* /////////////////////////////////////////////////////////////// */

            /* 8 ++++++++++++++++++++++++++++++++++++++++++ */
            /* Partie insertion dans la table ProductionFailureReport */
            /* -------------------------------------------- */
            ordreInsertion = "INSERT INTO ProductionFailureReport "
                    + "(timestamp, state, registrationDate, repairDate, "
                    + "failureCode, idProduct, idTesterReport, idProductionFailureReportFEDD) "
                    + "SELECT frFEDD.timestamp, frFEDD.state, frFEDD.registrationDate, frFEDD.repairDate, "
                    + "frFEDD.failureCode, ?, trLAI.idTesterReport, frFEDD.idProductionFailureReport "
                    + "FROM `FEDDproductBase`.`ProductionFailureReport` frFEDD, `testerBase`.`testerReport` trLAI "
                    + "WHERE frFEDD.idTesterReport = trLAI.idTesterReportFEDD ";
            _stmt = this.cnxProduct.getCnx().prepareStatement(ordreInsertion);
            _stmt.setInt(1, idProductLAI);
            // _stmt.setInt(2, idProductFEDD);
            _stmt.executeUpdate();

            /* /////////////////////////////////////////////////////////////// */

            /* 9 ++++++++++++++++++++++++++++++++++++++++++++ */
            /* Partie laissÃ©e en stand by car non testÃ©e faute de donnÃ©es. */
            /* Partie insertion dans la table customerComment */
            /* ---------------------------------------------- */
            ordreInsertion = "INSERT INTO `productBase`.`customerComment` "
                    + "(timestamp, comment, idProductionFailureReport) "
                    + "SELECT cc.timestamp, cc.comment, fr.idProductionFailureReport "
                    + "FROM `productBase`.`ProductionFailureReport` fr, `FEDDproductBase`.`customerComment` cc "
                    + "WHERE fr.idproduct = ? "
                    + "AND fr.idProductionFailureReportFEDD = cc.idProductionFailureReport ";
            _stmt = this.cnxProduct.getCnx().prepareStatement(ordreInsertion);
            _stmt.setInt(1, idProductLAI);
            _stmt.executeUpdate();

            /* /////////////////////////////////////////////////////////////// */

            /* 10 ++++++++++++++++++++++++++++++++++++++++++++++++ */
            /* Partie laissÃ©e en stand by car non testÃ©e faute de donnÃ©es. */
            /* Partie insertion dans la table failureReportComment */
            /* --------------------------------------------------- */
            ordreInsertion = "INSERT INTO `productBase`.`failureReportComment` "
                    + "(timestamp, comment, commentDate, idProductionFailureReport) "
                    + "SELECT frc.timestamp, frc.comment, frc.commentDate, fr.idProductionFailureReport "
                    + "FROM `productBase`.`ProductionFailureReport` fr, `FEDDproductBase`.`failureReportComment` frc "
                    + "WHERE fr.idproduct = ? "
                    + "AND fr.idProductionFailureReportFEDD = frc.idProductionFailureReport ";
            _stmt = this.cnxProduct.getCnx().prepareStatement(ordreInsertion);
            _stmt.setInt(1, idProductLAI);
            _stmt.executeUpdate();

            /* /////////////////////////////////////////////////////////////// */

            /* 10 bis ++++++++++++++++++++++++++++++++ */
            /* Partie laissÃ©e en stand by car non testÃ©e faute de donnÃ©es. */
            /* Partie insertion dans la table operator */
            /* --------------------------------------- */
            ordreInsertion = "INSERT INTO `operatorBase`.`operator` "
                    + "(timestamp, state, code, lastName, firstName) "
                    + "SELECT ofedd.timestamp, ofedd.state, ofedd.code, ofedd.lastName, ofedd.firstName "
                    + "FROM `FEDDoperatorBase`.`operator` ofedd, `FEDDproductBase`.`failure` f "
                    + "WHERE ofedd.idOperator = f.idOperator "
                    + "AND f. idProduct = ? AND (ofedd.code, ofedd.firstName, ofedd.lastName) NOT IN "
                    + "(SELECT olai.code, olai.firstName, olai.lastName FROM `operatorBase`.`operator` olai) "
                    + "GROUP BY ofedd.code, ofedd.firstName, ofedd.lastName";
            _stmt = this.cnxProduct.getCnx().prepareStatement(ordreInsertion);
            _stmt.setInt(1, idProductFEDD);
            _stmt.executeUpdate();

            /* /////////////////////////////////////////////////////////////// */

            /* 11 +++++++++++++++++++++++++++++++++++ */
            /* Partie laissÃ©e en stand by car non testÃ©e faute de donnÃ©es. */
            /* Partie insertion dans la table failure */
            /* -------------------------------------- */
            ordreInsertion = "INSERT INTO `productBase`.`failure` "
                    + "(timestamp, state, diagnosisDate, failureCause, cardFace, manufacturingTechnique, failureCode, "
                    + "imputationCode, dismantleCard, idOperator, idProduct, idProductionFailureReport, idFailureFEDD) "
                    + "SELECT fFEDD.timestamp, fFEDD.state, fFEDD.diagnosisDate, fFEDD.failureCause, fFEDD.cardFace, "
                    + "fFEDD.manufacturingTechnique, fFEDD.failureCode, fFEDD.imputationCode, fFEDD.dismantleCard, "
                    + "olai.idOperator, ?, fr.idProductionFailureReport, fFEDD.idFailure "
                    + "FROM `productBase`.`ProductionFailureReport` fr, `FEDDproductBase`.`failure` fFEDD, "
                    + " `FEDDoperatorBase`.`operator` ofedd, `operatorBase`.`operator` olai "
                    + "WHERE fFEDD.idOperator = ofedd.idOperator "
                    + "AND (ofedd.code = olai.code AND ofedd.firstName = olai.firstName AND ofedd.lastName = olai.lastName) "
                    + "AND fFEDD.idProduct = ? "
                    + "AND fr.idproduct = ? "
                    + "AND fr.idProductionFailureReportFEDD = fFEDD.idProductionFailureReport ";
            _stmt = this.cnxProduct.getCnx().prepareStatement(ordreInsertion);
            _stmt.setInt(1, idProductLAI);
            _stmt.setInt(2, idProductFEDD);
            _stmt.setInt(3, idProductLAI);
            _stmt.executeUpdate();

            /* /////////////////////////////////////////////////////////////// */

            /* 12 ++++++++++++++++++++++++++++++++++++++++++ */
            /* Partie laissÃ©e en stand by car non testÃ©e faute de donnÃ©es. */
            /* Partie insertion dans la table elementChanged */
            /* --------------------------------------------- */
            ordreInsertion = "INSERT INTO `productBase`.`elementChanged` "
                    + "(timestamp, topoRef, idFailure) "
                    + "SELECT ec.timestamp, ec.topoRef, f.idFailure "
                    + "FROM `productBase`.`failure` f, `FEDDproductBase`.`elementChanged` ec "
                    + "WHERE f.idproduct = ? "
                    + "AND f.idFailureFEDD = ec.idFailure ";
            _stmt = this.cnxProduct.getCnx().prepareStatement(ordreInsertion);
            _stmt.setInt(1, idProductLAI);
            _stmt.executeUpdate();

            /* /////////////////////////////////////////////////////////////// */

            /* 13 +++++++++++++++++++++++++++++++++++++++++++ */
            /* Partie insertion dans la table productDocument */
            /* ---------------------------------------------- */
            ordreInsertion = "INSERT INTO `productBase`.`productDocument` "
                    + "(timestamp, state, title, link, idProductDocumentType, idProduct) "
                    + "SELECT pd.timestamp, pd.state, pd.title, pd.link, pd.idProductDocumentType, "
                    + idProductLAI + " "
                    + "FROM `FEDDproductBase`.`productDocument` pd "
                    + "WHERE pd.idproduct = ? ";
            _stmt = this.cnxProduct.getCnx().prepareStatement(ordreInsertion);
            _stmt.setInt(1, idProductFEDD);
            _stmt.executeUpdate();

            /* /////////////////////////////////////////////////////////////// */

            modifTempTable = "ALTER TABLE `productBase`.`failure` DROP COLUMN idFailureFEDD";
            _stmt = this.cnxProduct.getCnx().prepareStatement(modifTempTable);
            _stmt.executeUpdate();
            modifTempTable = "ALTER TABLE `productBase`.`ProductionFailureReport` DROP COLUMN idProductionFailureReportFEDD";
            _stmt = this.cnxProduct.getCnx().prepareStatement(modifTempTable);
            _stmt.executeUpdate();
            modifTempTable = "ALTER TABLE `testerBase`.`testerReport` DROP COLUMN idTesterReportFEDD";
            _stmt = this.cnxProduct.getCnx().prepareStatement(modifTempTable);
            _stmt.executeUpdate();

            /*
             * //////////////////////////////////////////////////////////////////
             * /
             * /////////////////////////////////////////////////////////////////
             * ///////////////////////////////////////////////////////////////
             */

            // Retrieve product data
            _stmt = this.cnxProduct.getCnx().prepareStatement(
                    "SELECT * FROM product" + " WHERE (idProduct=?)");
            _stmt.setInt(1, idProductLAI);

            _rs = _stmt.executeQuery();
            if (_rs.next()) {
                _product = this.getProduct(_rs);
            } else {
                throw new ProductDaoException(exceptionMsg);
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

        return _product;
    }
}
