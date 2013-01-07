package fr.la.jproductbase.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import fr.la.jproductbase.metier.Product;
import fr.la.jproductbase.metier.ProductConf;
import fr.la.jproductbase.metier.Software;

public class SoftwareDaoImpl implements SoftwareDao {

    private static String exceptionMsg = "Logiciel inconnu dans la base de données.";
    private ConnectionProduct cnxProduct;

    public SoftwareDaoImpl(ConnectionProduct cnxProduct) {
        this.cnxProduct = cnxProduct;
    }

    @Override
    public List<Software> getSoftwares(Product product) throws SQLException {
        List<Software> _softwares = new ArrayList<Software>();
        PreparedStatement _stmt = null;
        ResultSet _rs = null;

        try {
            int _idProduct = product.getIdProduct();
            _stmt = this.cnxProduct.getCnx().prepareStatement(
                    "SELECT * FROM software "
                    + " WHERE (software.idSoftware IN"
                    + " (SELECT idSoftware FROM product_software"
                    + " WHERE product_software.idProduct=?))");
            _stmt.setInt(1, _idProduct);
            _rs = _stmt.executeQuery();

            while (_rs.next()) {
                Software _software = this.getSoftware(_rs);
                _softwares.add(_software);

                // Update product object
                product.addSoftware(_software);
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

        return _softwares;
    }

    @Override
    public List<Software> getSoftwares(ProductConf productConf)
            throws SQLException {
        List<Software> _softwares = new ArrayList<Software>();
        PreparedStatement _stmt = null;
        ResultSet _rs = null;

        try {
            int _idProductConf = productConf.getIdProductConf();
            _stmt = this.cnxProduct.getCnx().prepareStatement(
                    "SELECT * FROM software "
                    + " WHERE (software.idSoftware IN"
                    + " (SELECT idSoftware FROM productConf_software"
                    + " WHERE productConf_software.idProductConf=?))");
            _stmt.setInt(1, _idProductConf);
            _rs = _stmt.executeQuery();

            while (_rs.next()) {
                Software _software = this.getSoftware(_rs);
                _softwares.add(_software);

                // Update product object
                productConf.addSoftware(_software);
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

        return _softwares;
    }

    @Override
    public Software getSoftware(String name, String version)
            throws SQLException {
        Software _software = null;
        PreparedStatement _stmt = null;
        ResultSet _rs = null;

        try {
            _stmt = this.cnxProduct.getCnx().prepareStatement(
                    "SELECT * FROM software " + " WHERE (name=?)"
                    + " AND (version=?)");
            _stmt.setString(1, name);
            _stmt.setString(2, version);
            _rs = _stmt.executeQuery();

            while (_rs.next()) {
                _software = this.getSoftware(_rs);
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

        return _software;
    }

    @Override
    public Software addSoftware(String name, String version)
            throws SQLException, SoftwareDaoException {
        Software _software = null;
        PreparedStatement _stmt = null;
        ResultSet _rs = null;

        try {
            _stmt = this.cnxProduct.getCnx().prepareStatement(
                    "INSERT INTO software (state, name, version)"
                    + " VALUES (?, ?, ?)");
            _stmt.setInt(1, 1);
            _stmt.setString(2, name);
            _stmt.setString(3, version);
            _stmt.executeUpdate();

            // Retrieve product data
            _stmt = this.cnxProduct.getCnx().prepareStatement(
                    "SELECT * FROM software" + " WHERE (name=?)"
                    + " 	AND (version=?)");
            _stmt.setString(1, name);
            _stmt.setString(2, version);

            _rs = _stmt.executeQuery();
            if (!_rs.next()) {
                throw new SoftwareDaoException(exceptionMsg);
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

        return _software;
    }

    @Override
    public void addProductSoftware(Product product, Software software)
            throws SQLException, SoftwareDaoException {
        int _idProduct = product.getIdProduct();
        int _idSoftware = software.getIdSoftware();

        PreparedStatement _stmt = null;
        ResultSet _rs = null;

        try {
            _stmt = this.cnxProduct.getCnx().prepareStatement(
                    "INSERT INTO product_software (idProduct, idSoftware)"
                    + " VALUES (?, ?)");
            _stmt.setInt(1, _idProduct);
            _stmt.setInt(2, _idSoftware);
            _stmt.executeUpdate();

            // Retrieve product_product data
            _stmt = this.cnxProduct.getCnx().prepareStatement(
                    "SELECT * FROM product_software" + " WHERE (idProduct=?)"
                    + " AND (idSoftware=?)");
            _stmt.setInt(1, _idProduct);
            _stmt.setInt(2, _idSoftware);

            _rs = _stmt.executeQuery();
            if (!_rs.next()) {
                throw new SoftwareDaoException(exceptionMsg);
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
    public void removeProductSoftware(Product product, Software software)
            throws SQLException {
        if ((null != product) && (null != software)) {
            PreparedStatement _stmt = null;
            try {
                _stmt = this.cnxProduct.getCnx().prepareStatement(
                        "DELETE FROM product_software "
                        + "WHERE ((idProduct=?)"
                        + " AND (idSoftware=?))");
            } catch (NamingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            _stmt.setInt(1, product.getIdProduct());
            _stmt.setInt(2, software.getIdSoftware());
            _stmt.executeUpdate();

            // Update product
            product.removeSoftware(software);

            _stmt.close();
        } else {
            // Nothing to do
        }
    }

    /*
     * Cr&eacute;er un logiciel &agrave; partir d'un enregistrement de la base
     * de donn&eacute;es.
     * 
     * @param rs Enregistrement de la base de donn&eacute;es.
     * 
     * @return Logiciel.
     * 
     * @throws SQLException
     */
    private Software getSoftware(ResultSet rs) throws SQLException {
        // Software
        int _idSoftware = rs.getInt("idSoftware");
        Timestamp _timestamp = rs.getTimestamp("timestamp");
        int _state = rs.getInt("state");
        String _name = rs.getString("name");
        String _version = rs.getString("version");
        Software _software = new Software(_idSoftware, _timestamp, _state,
                _name, _version);

        return _software;
    }

    @Override
    public List<Software> getSoftwares() throws SQLException {
        List<Software> _softwares = new ArrayList<Software>();
        PreparedStatement _stmt = null;
        ResultSet _rs = null;

        try {
            _stmt = this.cnxProduct.getCnx().prepareStatement(
                    "SELECT * FROM software ");
            _rs = _stmt.executeQuery();

            while (_rs.next()) {
                Software _software = this.getSoftware(_rs);
                _softwares.add(_software);
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

        return _softwares;
    }

    @Override
    public List<Software> getActiveSoftwares() throws SQLException {
        List<Software> _softwares = new ArrayList<Software>();
        PreparedStatement _stmt = null;
        ResultSet _rs = null;

        try {
            _stmt = this.cnxProduct.getCnx().prepareStatement(
                    "SELECT * FROM software " + " WHERE (state=?) ORDER BY name;");
            _stmt.setInt(1, 1);
            _rs = _stmt.executeQuery();

            while (_rs.next()) {
                Software _software = this.getSoftware(_rs);
                _softwares.add(_software);
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
        return _softwares;
    }

    @Override
    public Software addSoftware(int state, String name, String version)
            throws SQLException, SoftwareDaoException {
        Software _software = null;
        PreparedStatement _stmt = null;
        ResultSet _rs = null;

        try {
            _stmt = this.cnxProduct.getCnx().prepareStatement(
                    "INSERT INTO software (state, name, version)"
                    + " VALUES (?, ?, ?)");
            _stmt.setInt(1, state);
            _stmt.setString(2, name);
            _stmt.setString(3, version);
            _stmt.executeUpdate();

            // Retrieve software data
            _stmt = this.cnxProduct.getCnx().prepareStatement(
                    "SELECT * FROM software" + " WHERE (name=?)"
                    + " 	AND (version=?)");
            _stmt.setString(1, name);
            _stmt.setString(2, version);

            _rs = _stmt.executeQuery();
            if (!_rs.next()) {
                throw new SoftwareDaoException(exceptionMsg);
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

        return _software;
    }

    @Override
    public void updateSoftware(Software softwareToUpdate) throws SQLException,
            SoftwareDaoException {

        PreparedStatement _stmt = null;

        try {
            _stmt = this.cnxProduct.getCnx().prepareStatement(
                    "UPDATE software " + "SET state=?, name=?, version=?"
                    + " WHERE (idSoftware=?)");
            _stmt.setInt(1, softwareToUpdate.getState());
            _stmt.setString(2, softwareToUpdate.getName());
            _stmt.setString(3, softwareToUpdate.getVersion());
            _stmt.setInt(4, softwareToUpdate.getIdSoftware());
            _stmt.executeUpdate();

            _stmt = this.cnxProduct.getCnx().prepareStatement(
                    "SELECT * FROM software" + " WHERE (idSoftware=?)");
            _stmt.setInt(1, softwareToUpdate.getIdSoftware());

            ResultSet _rs = _stmt.executeQuery();
            if (!_rs.next()) {
                throw new SoftwareDaoException(exceptionMsg);
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
    public void deleteSoftware(Software softwareToDelete) throws SQLException {

        PreparedStatement _stmt = null;

        try {
            _stmt = this.cnxProduct.getCnx().prepareStatement(
                    "DELETE FROM software " + " WHERE (idSoftware=?)");
            _stmt.setInt(1, softwareToDelete.getIdSoftware());
            _stmt.executeUpdate();
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
    public Software getSoftware(int idSoftware) throws SQLException {
        Software _software = null;
        PreparedStatement _stmt = null;
        ResultSet _rs = null;

        try {
            _stmt = this.cnxProduct.getCnx().prepareStatement(
                    "SELECT * FROM software " + " WHERE (idSoftware=?)");
            _stmt.setInt(1, idSoftware);
            _rs = _stmt.executeQuery();

            while (_rs.next()) {
                _software = this.getSoftware(_rs);
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

        return _software;
    }

    // 10-01-12 : RMO : Creation de la methode
    @Override
    public void removeProductConfSoftware(ProductConf productConf,
            Software software) throws SQLException {
        if ((null != productConf) && (null != software)) {
            PreparedStatement _stmt = null;
            try {
                _stmt = this.cnxProduct.getCnx().prepareStatement(
                        "DELETE FROM productConf_software "
                        + "WHERE ((idProductConf=?)"
                        + " AND (idSoftware=?))");
            } catch (NamingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            _stmt.setInt(1, productConf.getIdProductConf());
            _stmt.setInt(2, software.getIdSoftware());
            _stmt.executeUpdate();

            // Update product
            productConf.removeSoftware(software);

            _stmt.close();
        } else {
            // Nothing to do
        }

    }

    // 10-01-12 : RMO : Creation de la methode
    @Override
    public void addProductConfSoftware(ProductConf productConf,
            Software software) throws SQLException, SoftwareDaoException {
        int _idProductConf = productConf.getIdProductConf();
        int _idSoftware = software.getIdSoftware();

        PreparedStatement _stmt = null;
        ResultSet _rs = null;

        try {
            _stmt = this.cnxProduct.getCnx().prepareStatement(
                    "INSERT INTO productConf_software (idProductConf, idSoftware)"
                    + " VALUES (?, ?)");
            _stmt.setInt(1, _idProductConf);
            _stmt.setInt(2, _idSoftware);
            _stmt.executeUpdate();

            // Retrieve productConf_product data
            _stmt = this.cnxProduct.getCnx().prepareStatement(
                    "SELECT * FROM productConf_software"
                    + " WHERE (idProductConf=?)"
                    + " AND (idSoftware=?)");
            _stmt.setInt(1, _idProductConf);
            _stmt.setInt(2, _idSoftware);

            _rs = _stmt.executeQuery();
            if (!_rs.next()) {
                throw new SoftwareDaoException(exceptionMsg);
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
}
