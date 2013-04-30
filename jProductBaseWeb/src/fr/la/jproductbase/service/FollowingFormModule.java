package fr.la.jproductbase.service;

import java.util.List;

import fr.la.jproductbase.dao.FollowingFormModelDao;
import fr.la.jproductbase.metier.FollowingFormModel;

public class FollowingFormModule {
	
	FollowingFormModelDao _followingFormModelDao;

	public FollowingFormModule(FollowingFormModelDao followingFormModelDao) {
    	_followingFormModelDao = followingFormModelDao;
    }

    public List<FollowingFormModel> getAllActiveFollowingFormModel() {
        List<FollowingFormModel> _followingFormModel = _followingFormModelDao.getAllActiveFollowingFormModel();
        return _followingFormModel;
    }

    public FollowingFormModel getFollowingFormModel(int idFollowingFormModel) {
        FollowingFormModel _followingFormModel = _followingFormModelDao.getFollowingFormModel(idFollowingFormModel);
        return _followingFormModel;
    }

    public List<FollowingFormModel> getAllFollowingFormModel() {
        List<FollowingFormModel> _followingFormModel = _followingFormModelDao.getAllFollowingFormModel();
        return _followingFormModel;
    }

    public void addFollowingFormModel(FollowingFormModel followingformmodel) {
        _followingFormModelDao.addFollowingFormModel(followingformmodel);
    }

    public void updateFollowingFormModel(FollowingFormModel followingformmodel) {
        _followingFormModelDao.updateFollowingFormModel(followingformmodel);
    }

    public void deleteFollowingFormModel(int id) {
        _followingFormModelDao.deleteFollowingFormModel(id);
    }
}
