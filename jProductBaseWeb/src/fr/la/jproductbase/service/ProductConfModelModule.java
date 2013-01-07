package fr.la.jproductbase.service;

import fr.la.jproductbase.dao.ConnectionProduct;
import fr.la.jproductbase.dao.ProductConfModelDao;
import fr.la.jproductbase.dao.ProductConfModelDaoImpl;
import fr.la.jproductbase.metier.ProductConfModel;
import fr.la.jproductbase.metier.ProductType;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class ProductConfModelModule {

    private ConnectionProduct cnxProduct;

    protected ProductConfModelModule(ConnectionProduct cnxProduct) {
        this.cnxProduct = cnxProduct;
    }

    protected ProductConfModel getProductConfModel(int idProductConfModel)
            throws SQLException {
        ProductConfModelDao _productConfModelDao = new ProductConfModelDaoImpl(
                this.cnxProduct);

        ProductConfModel _productModelConf = _productConfModelDao
                .getProductConfModel(idProductConfModel);

        return _productModelConf;
    }

    protected ProductConfModel getProductConfModel(String reference)
            throws SQLException {
        ProductConfModelDao _productConfModelDao = new ProductConfModelDaoImpl(
                this.cnxProduct);

        ProductConfModel _productModelConf = _productConfModelDao
                .getProductConfModel(reference);

        return _productModelConf;
    }

    protected List<ProductConfModel> getActiveProductConfModels(
            ProductType productType) throws SQLException {
        ProductConfModelDao _productConfModelDao = new ProductConfModelDaoImpl(
                this.cnxProduct);

        List<ProductConfModel> _productModelConfs = _productConfModelDao
                .getActiveProductConfModels(productType);

        return _productModelConfs;
    }

    protected List<ProductConfModel> getActiveProductConfModels() throws SQLException {
        ProductConfModelDao _productConfModelDao = new ProductConfModelDaoImpl(
                this.cnxProduct);

        List<ProductConfModel> _productModelConfs = _productConfModelDao
                .getActiveProductConfModels();

        return _productModelConfs;
    }

    protected List<ProductConfModel> getProductConfModels() throws SQLException {
        ProductConfModelDao _productConfModelDao = new ProductConfModelDaoImpl(
                this.cnxProduct);

        List<ProductConfModel> _productModelConfs = _productConfModelDao
                .getProductConfModels();

        return _productModelConfs;
    }

    protected List<ProductConfModel> getProductConfModels(int type) throws SQLException {
        ProductConfModelDao _productConfModelDao = new ProductConfModelDaoImpl(
                this.cnxProduct);

        List<ProductConfModel> _productModelConfs = _productConfModelDao
                .getProductConfModels(type);

        return _productModelConfs;
    }

    protected void addProductConfModels(ProductConfModel _productConfModel) throws SQLException {
        ProductConfModelDao _productConfModelDao = new ProductConfModelDaoImpl(
                this.cnxProduct);

        _productConfModelDao.addProductConfModels(_productConfModel);
    }

    protected void delProductConfModels(int id) throws SQLException {
        ProductConfModelDao _productConfModelDao = new ProductConfModelDaoImpl(
                this.cnxProduct);

        _productConfModelDao.delProductConfModels(id);
    }

    protected void updateProductConfModels(ProductConfModel productConfModel) throws SQLException {
        ProductConfModelDao _productConfModelDao = new ProductConfModelDaoImpl(
                this.cnxProduct);

        _productConfModelDao.updateProductConfModels(productConfModel);
    }

    protected int countProductConfModel(Map<String, String> filters) throws SQLException {
        ProductConfModelDao _productConfModelDao = new ProductConfModelDaoImpl(
                this.cnxProduct);

        int i = _productConfModelDao.countProductConfModel(filters);

        return i;
    }

    protected List<ProductConfModel> getProductConfModelLazy(Map<String, String> filters, int limit, int maxperpage) throws SQLException {
        ProductConfModelDao _productConfModelDao = new ProductConfModelDaoImpl(
                this.cnxProduct);

        List<ProductConfModel> _productModelConfs = _productConfModelDao.getProductConfModelLazy(filters, limit, maxperpage);

        return _productModelConfs;
        
    }
}
