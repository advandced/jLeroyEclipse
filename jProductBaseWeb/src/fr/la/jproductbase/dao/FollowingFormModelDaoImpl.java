package fr.la.jproductbase.dao;

import fr.la.jproductbase.metier.FollowingFormModel;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;

public class FollowingFormModelDaoImpl implements FollowingFormModelDao {
    // private static String exceptionMsg =
    // "Modéle de fiche suiveuse inconnu dans la base de données.";

    private ConnectionProduct cnxProduct;

    public FollowingFormModelDaoImpl(ConnectionProduct cnxProduct) {
        this.cnxProduct = cnxProduct;
    }

    @Override
    public FollowingFormModel getFollowingFormModel(int idFollowingFormModel)
            throws SQLException {
        FollowingFormModel _followingFormModel = null;
        PreparedStatement _stmt = null;
        ResultSet _rs = null;

        try {
            _stmt = this.cnxProduct
                    .getCnx()
                    .prepareStatement(
                    "SELECT * FROM followingFormModel WHERE idFollowingFormModel=?");
            _stmt.setInt(1, idFollowingFormModel);
            _rs = _stmt.executeQuery();

            if (_rs.next()) {
                _followingFormModel = this.getFollowingFormModel(_rs);
            } else {
                _followingFormModel = null;
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

        return _followingFormModel;
    }

    @Override
    public List<FollowingFormModel> getAllActiveFollowingFormModel() throws SQLException {
        List<FollowingFormModel> _followingFormModels = new ArrayList<FollowingFormModel>();
        PreparedStatement _stmt = null;
        ResultSet _rs = null;

        try {
            _stmt = this.cnxProduct.getCnx().prepareStatement(
                    "SELECT * FROM followingFormModel WHERE (state=1);");
            _rs = _stmt.executeQuery();

            while (_rs.next()) {
                FollowingFormModel _followingFormModel = this
                        .getFollowingFormModel(_rs);
                _followingFormModels.add(_followingFormModel);
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

        return _followingFormModels;
    }

    @Override
    public List<FollowingFormModel> getAllFollowingFormModel() throws SQLException {
        List<FollowingFormModel> _followingFormModels = new ArrayList<FollowingFormModel>();
        PreparedStatement _stmt = null;
        ResultSet _rs = null;

        try {
            _stmt = this.cnxProduct.getCnx().prepareStatement(
                    "SELECT * FROM followingFormModel;");
            _rs = _stmt.executeQuery();

            while (_rs.next()) {
                FollowingFormModel _followingFormModel = this
                        .getFollowingFormModel(_rs);
                _followingFormModels.add(_followingFormModel);
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

        return _followingFormModels;
    }

    @Override
    public void addFollowingFormModel(FollowingFormModel followingformmodel) {
        PreparedStatement _stmt = null;
        try {
            _stmt = this.cnxProduct
                    .getCnx()
                    .prepareStatement(
                    "INSERT INTO followingformmodel (state, name, version, jasperReport)"
                    + "VALUES (?, ?, ?, ?);");
            _stmt.setInt(1, followingformmodel.getState());
            _stmt.setString(2, followingformmodel.getName());
            _stmt.setString(3, followingformmodel.getVersion());
            _stmt.setString(4, followingformmodel.getJasperReport());
            _stmt.execute();
        } catch (NamingException ex) {
            Logger.getLogger(FollowingFormModelDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(FollowingFormModelDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void deleteFollowingFormModel(int id) {
        PreparedStatement _stmt = null;

        try {
            _stmt = this.cnxProduct.getCnx().prepareStatement("DELETE FROM followingformmodel WHERE idFollowingFormModel = ?;");
            _stmt.setInt(1, id);
            _stmt.executeUpdate();
        } catch (NamingException ex) {
            Logger.getLogger(FollowingFormModelDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
        }
    }

    @Override
    public void updateFollowingFormModel(FollowingFormModel followingformmodel) throws SQLException {
        PreparedStatement _stmt = null;
        try {
            _stmt = this.cnxProduct.getCnx().prepareStatement("UPDATE followingformmodel SET state = ?, name = ?, version = ?, jasperReport = ? WHERE idFollowingFormModel = ?;");
            _stmt.setInt(1, followingformmodel.getState());
            _stmt.setString(2, followingformmodel.getName());
            _stmt.setString(3, followingformmodel.getVersion());
            _stmt.setString(4, followingformmodel.getJasperReport());
            _stmt.setInt(5, followingformmodel.getIdFollowingFormmodel());
            _stmt.executeUpdate();
        } catch (NamingException ex) {
            Logger.getLogger(FollowingFormModelDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /*
     * Cr&eacute;er un mod&eacute;le de fiche suiveuse &agrave; partir d'un
     * enregistement de la base de donn&eacute;es.
     * 
     * @param rs Enregistrement de la base de donn&eacute;es.
     * 
     * @return Mod&eacute;lde de fiche suiveuse.
     */

    private FollowingFormModel getFollowingFormModel(ResultSet rs)
            throws SQLException {
        int _idFollowingFormModel = rs.getInt("idFollowingFormModel");
        Timestamp _timestamp = rs.getTimestamp("timestamp");
        int _state = rs.getInt("state");
        String _name = rs.getString("name");
        String _version = rs.getString("version");
        String _jasperReport = rs.getString("jasperReport");
        FollowingFormModel _followingFormModel = new FollowingFormModel(
                _idFollowingFormModel, _timestamp, _state, _name, _version,
                _jasperReport);

        return _followingFormModel;
    }
}
