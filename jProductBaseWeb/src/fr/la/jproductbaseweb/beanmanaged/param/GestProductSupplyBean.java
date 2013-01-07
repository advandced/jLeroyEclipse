package fr.la.jproductbaseweb.beanmanaged.param;

import java.sql.SQLException;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.naming.NamingException;

import fr.la.jproductbase.dao.ProductDaoException;
import fr.la.jproductbase.metier.ProductSupply;
import fr.la.jproductbaseweb.beanmanaged.exception.ProductSupplyException;
import fr.la.jproductbaseweb.beanmanaged.modelForm.ProductSuppplyForm;
import java.io.Serializable;

@ManagedBean(name = "gestProductSupplyBean")
@ApplicationScoped
public class GestProductSupplyBean extends GestFormAbstract<ProductSupply> implements Serializable {

    private static final long serialVersionUID = 1L;
    private String nameProductSupply;
    private int stateProductSupply;

    public GestProductSupplyBean() {
        super();
        try {
            this.objectList = this.moduleGolbal.getProductSupplies();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public void selectedCreate() {
        // TODO Auto-generated method stub
        super.selectedCreate();
        this.nameProductSupply = null;
        this.stateProductSupply = 1;
    }

    @Override
    public void selectedModify() {
        // TODO Auto-generated method stub
        super.selectedModify();
        this.nameProductSupply = getSelectedObject().getName();
        this.stateProductSupply = getSelectedObject().getState();
    }

    @Override
    protected void create() throws ProductSupplyException {
        // TODO Auto-generated method stub

        ProductSuppplyForm _productSupplyForm = new ProductSuppplyForm(this.nameProductSupply, this.stateProductSupply);


        try {
            this.moduleGolbal.addProductSupply(_productSupplyForm.getName(), _productSupplyForm.getState());
            this.objectList = this.moduleGolbal.getProductSupplies();
        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } catch (ProductDaoException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } catch (NamingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    @Override
    protected void update() throws ProductSupplyException {

        ProductSuppplyForm _productSupplyForm = new ProductSuppplyForm(this.nameProductSupply, this.stateProductSupply);

        ProductSupply _productSupply = new ProductSupply(getSelectedObject().getIdProductSupply(), null, _productSupplyForm.getState(), _productSupplyForm.getName());
        try {
            this.moduleGolbal.updateProductSupply(_productSupply);
            this.objectList = this.moduleGolbal.getProductSupplies();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NamingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


    }

    public String getNameProductSupply() {
        return nameProductSupply;
    }

    public void setNameProductSupply(String nameProductSupply) {
        this.nameProductSupply = nameProductSupply;
    }

    public int getStateProductSupply() {
        return stateProductSupply;
    }

    public void setStateProductSupply(int stateProductSupply) {
        this.stateProductSupply = stateProductSupply;
    }
}
