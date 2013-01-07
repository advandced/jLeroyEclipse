package fr.la.jproductbaseweb.beanmanaged.param;

import java.sql.SQLException;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.naming.NamingException;

import fr.la.jproductbase.dao.ProductDaoException;
import fr.la.jproductbase.metier.ProductType;
import fr.la.jproductbaseweb.beanmanaged.exception.ProductTypeException;
import fr.la.jproductbaseweb.beanmanaged.modelForm.ProductTypeForm;
import java.io.Serializable;

@ManagedBean(name = "gestProductTypeBean")
@ApplicationScoped
public class GestProductTypeBean extends GestFormAbstract<ProductType> implements Serializable {

    private static final long serialVersionUID = 1L;
    private String nameProductType;
    private int stateProductType;

    public GestProductTypeBean() {
        super();
        try {
            this.objectList = this.moduleGolbal.getProductTypes();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    @Override
    public void selectedModify() {
        // TODO Auto-generated method stub
        super.selectedModify();
        this.nameProductType = getSelectedObject().getName();
        this.stateProductType = getSelectedObject().getState();
    }

    @Override
    public void selectedCreate() {
        // TODO Auto-generated method stub
        super.selectedCreate();
        this.nameProductType = null;
        this.stateProductType = 1;
    }

    @Override
    protected void create() throws ProductTypeException {
        // TODO Auto-generated method stub
        ProductTypeForm _productTypeForm = new ProductTypeForm(this.nameProductType, this.stateProductType);
        try {
            this.moduleGolbal.addProductType(_productTypeForm.getName(), _productTypeForm.getState());
            this.objectList = this.moduleGolbal.getProductTypes();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ProductDaoException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NamingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    @Override
    protected void update() throws ProductTypeException {
        // TODO Auto-generated method stub

        ProductTypeForm _productTypeForm = new ProductTypeForm(this.nameProductType, this.stateProductType);

        ProductType _productType = new ProductType(getSelectedObject().getIdProductType(), null, _productTypeForm.getState(), _productTypeForm.getName());
        try {
            this.moduleGolbal.updateProductType(_productType);
            this.objectList = this.moduleGolbal.getProductTypes();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NamingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


    }

    public String getNameProductType() {
        return nameProductType;
    }

    public void setNameProductType(String nameProductType) {
        this.nameProductType = nameProductType;
    }

    public int getStateProductType() {
        return stateProductType;
    }

    public void setStateProductType(int stateProductType) {
        this.stateProductType = stateProductType;
    }
}
