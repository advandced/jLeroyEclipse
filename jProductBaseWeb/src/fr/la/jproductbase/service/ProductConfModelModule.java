package fr.la.jproductbase.service;

import java.util.List;
import java.util.Map;

import fr.la.jproductbase.dao.ProductConfModelDao;
import fr.la.jproductbase.metier.ProductConfModel;
import fr.la.jproductbase.metier.ProductType;

public class ProductConfModelModule {

	ProductConfModelDao _productConfModelDao;
	
    public ProductConfModelModule(ProductConfModelDao productConfModelDao) {
    	_productConfModelDao = productConfModelDao;
    }

    public ProductConfModel getProductConfModel(int idProductConfModel) {
        return  _productConfModelDao.getProductConfModel(idProductConfModel);
    }

    public ProductConfModel getProductConfModel(String reference) {
        return _productConfModelDao.getProductConfModel(reference);
    }

    public List<ProductConfModel> getActiveProductConfModels(ProductType productType) {
        return _productConfModelDao.getActiveProductConfModels(productType);
    }

    public List<ProductConfModel> getActiveProductConfModels() {
        return _productConfModelDao.getActiveProductConfModels();
    }

    public List<ProductConfModel> getProductConfModels() {
        return _productConfModelDao.getProductConfModels();
    }

    public List<ProductConfModel> getProductConfModels(int type) {
        return _productConfModelDao.getProductConfModels(type);
    }

    public void addProductConfModels(ProductConfModel _productConfModel) {
        _productConfModelDao.addProductConfModels(_productConfModel);
    }

    public void delProductConfModels(int id) {
        _productConfModelDao.delProductConfModels(id);
    }

    public void updateProductConfModels(ProductConfModel productConfModel) {
        _productConfModelDao.updateProductConfModels(productConfModel);
    }

    public int countProductConfModel(Map<String, String> filters) {
        return _productConfModelDao.countProductConfModel(filters);
    }

    public List<ProductConfModel> getProductConfModelLazy(Map<String, String> filters, int limit, int maxperpage) {
        return _productConfModelDao.getProductConfModelLazy(filters, limit, maxperpage);
    }
}
