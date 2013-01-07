package fr.la.jproductbase.service;

import fr.la.jproductbase.dao.ConnectionProduct;
import fr.la.jproductbase.dao.FollowingFormModelDao;
import fr.la.jproductbase.dao.FollowingFormModelDaoImpl;
import fr.la.jproductbase.metier.FollowingFormModel;
import java.sql.SQLException;
import java.util.List;

public class FollowingFormModule {

    private ConnectionProduct cnxProduct;

    protected FollowingFormModule(ConnectionProduct cnxProduct) {
        this.cnxProduct = cnxProduct;
    }

    protected List<FollowingFormModel> getAllActiveFollowingFormModel() throws SQLException {
        FollowingFormModelDao _followingFormModelDao = new FollowingFormModelDaoImpl(
                this.cnxProduct);

        List<FollowingFormModel> _followingFormModel = _followingFormModelDao.getAllActiveFollowingFormModel();

        return _followingFormModel;
    }

    protected FollowingFormModel getFollowingFormModel(int idFollowingFormModel) throws SQLException {
        FollowingFormModelDao _followingFormModelDao = new FollowingFormModelDaoImpl(
                this.cnxProduct);

        FollowingFormModel _followingFormModel = _followingFormModelDao.getFollowingFormModel(idFollowingFormModel);

        return _followingFormModel;
    }

    protected List<FollowingFormModel> getAllFollowingFormModel() throws SQLException {
        FollowingFormModelDao _followingFormModelDao = new FollowingFormModelDaoImpl(
                this.cnxProduct);

        List<FollowingFormModel> _followingFormModel = _followingFormModelDao.getAllFollowingFormModel();

        return _followingFormModel;
    }

    protected void addFollowingFormModel(FollowingFormModel followingformmodel) {
        FollowingFormModelDao _followingFormModelDao = new FollowingFormModelDaoImpl(
                this.cnxProduct);

        _followingFormModelDao.addFollowingFormModel(followingformmodel);

    }

    protected void updateFollowingFormModel(FollowingFormModel followingformmodel) throws SQLException {
        FollowingFormModelDao _followingFormModelDao = new FollowingFormModelDaoImpl(
                this.cnxProduct);

        _followingFormModelDao.updateFollowingFormModel(followingformmodel);

    }

    protected void deleteFollowingFormModel(int id) {
        FollowingFormModelDao _followingFormModelDao = new FollowingFormModelDaoImpl(
                this.cnxProduct);

        _followingFormModelDao.deleteFollowingFormModel(id);

    }
}
