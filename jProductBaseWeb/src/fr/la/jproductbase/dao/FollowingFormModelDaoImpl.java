package fr.la.jproductbase.dao;

import fr.la.jproductbase.metier.FollowingFormModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class FollowingFormModelDaoImpl extends GenericDao implements FollowingFormModelDao {

    ConnectionProduct cnxProduct;

    public FollowingFormModelDaoImpl(ConnectionProduct cnxProduct) {
        this.cnxProduct = cnxProduct;
    }

    @Override
    public FollowingFormModel getFollowingFormModel(int idFollowingFormModel) {
        FollowingFormModel _followingFormModel = null;
        Connection c = null;
        PreparedStatement _stmt = null;
        ResultSet _rs = null;

        try {
        	c = cnxProduct.getCnx();
            _stmt = c.prepareStatement(
                    "SELECT * FROM followingFormModel WHERE idFollowingFormModel=?");
            _stmt.setInt(1, idFollowingFormModel);
            _rs = _stmt.executeQuery();

            if (_rs.next()) {
                _followingFormModel = this.getFollowingFormModel(_rs);
            } else {
                _followingFormModel = null;
            }
        } catch (SQLException e) {
        	handleDAOException(e);
        } finally {
        	close(_rs);
        	close(_stmt);
        	close(c);
        }

        return _followingFormModel;
    }

    @Override
    public List<FollowingFormModel> getAllActiveFollowingFormModel() {
        List<FollowingFormModel> _followingFormModels = new ArrayList<FollowingFormModel>();
        Connection c = null;
        PreparedStatement _stmt = null;
        ResultSet _rs = null;

        try {
        	c = cnxProduct.getCnx();
            _stmt = c.prepareStatement("SELECT * FROM followingFormModel WHERE (state=1);");
            _rs = _stmt.executeQuery();

            while (_rs.next()) {
                FollowingFormModel _followingFormModel = this
                        .getFollowingFormModel(_rs);
                _followingFormModels.add(_followingFormModel);
            }
        } catch (SQLException e) {
        	handleDAOException(e);
        } finally {
        	close(_rs);
        	close(_stmt);
        	close(c);
        }

        return _followingFormModels;
    }

    @Override
    public List<FollowingFormModel> getAllFollowingFormModel() {
        List<FollowingFormModel> _followingFormModels = new ArrayList<FollowingFormModel>();
        Connection c = null;
        PreparedStatement _stmt = null;
        ResultSet _rs = null;

        try {
        	c = cnxProduct.getCnx();
            _stmt = c.prepareStatement(
                    "SELECT * FROM followingFormModel;");
            _rs = _stmt.executeQuery();

            while (_rs.next()) {
                FollowingFormModel _followingFormModel = this
                        .getFollowingFormModel(_rs);
                _followingFormModels.add(_followingFormModel);
            }
        } catch (SQLException e) {
        	handleDAOException(e);
        } finally {
        	close(_rs);
        	close(_stmt);
        	close(c);
        }

        return _followingFormModels;
    }

    @Override
    public void addFollowingFormModel(FollowingFormModel followingformmodel) {
    	Connection c = null;
        PreparedStatement _stmt = null;
        try {
	        c = cnxProduct.getCnx();
	        _stmt = c.prepareStatement(
	                "INSERT INTO followingformmodel (state, name, version, jasperReport)"
	                + "VALUES (?, ?, ?, ?);");
	        _stmt.setInt(1, followingformmodel.getState());
	        _stmt.setString(2, followingformmodel.getName());
	        _stmt.setString(3, followingformmodel.getVersion());
	        _stmt.setString(4, followingformmodel.getJasperReport());
	        _stmt.execute();
        } catch (SQLException e) {
        	handleDAOException(e);
        } finally {
        	close(_stmt);
        	close(c);
        }
    }

    @Override
    public void deleteFollowingFormModel(int id) {
    	Connection c = null;
        PreparedStatement _stmt = null;
        try {
	        c = cnxProduct.getCnx();
            _stmt = c.prepareStatement("DELETE FROM followingformmodel WHERE idFollowingFormModel = ?;");
            _stmt.setInt(1, id);
            _stmt.executeUpdate();
	    } catch (SQLException e) {
	    	handleDAOException(e);
	    } finally {
	    	close(_stmt);
	    	close(c);
	    }
    }

    @Override
    public void updateFollowingFormModel(FollowingFormModel followingformmodel) {
    	Connection c = null;
        PreparedStatement _stmt = null;
        try {
	        c = cnxProduct.getCnx();
            _stmt = c.prepareStatement("UPDATE followingformmodel SET state = ?, name = ?, version = ?, jasperReport = ? WHERE idFollowingFormModel = ?;");
            _stmt.setInt(1, followingformmodel.getState());
            _stmt.setString(2, followingformmodel.getName());
            _stmt.setString(3, followingformmodel.getVersion());
            _stmt.setString(4, followingformmodel.getJasperReport());
            _stmt.setInt(5, followingformmodel.getIdFollowingFormmodel());
            _stmt.executeUpdate();
	    } catch (SQLException e) {
	    	handleDAOException(e);
	    } finally {
	    	close(_stmt);
	    	close(c);
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

    private FollowingFormModel getFollowingFormModel(ResultSet rs) throws SQLException {
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
