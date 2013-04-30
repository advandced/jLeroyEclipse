package fr.la.jproductbaseweb.beanmanaged.param;

import java.io.Serializable;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import fr.la.jproductbase.metier.ProductSupply;
import fr.la.jproductbaseweb.beanmanaged.exception.ProductSupplyException;
import fr.la.jproductbaseweb.beanmanaged.modelForm.ProductSuppplyForm;

@ManagedBean(name = "gestProductSupplyBean")
@ApplicationScoped
public class GestProductSupplyBean extends GestFormAbstract<ProductSupply> implements Serializable {

    private static final long serialVersionUID = 1L;
    private String nameProductSupply;
    private int stateProductSupply;

    public GestProductSupplyBean() {
        super();
        this.objectList = this.moduleGolbal.getProductSupplies();
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
        ProductSuppplyForm _productSupplyForm = new ProductSuppplyForm(this.nameProductSupply, this.stateProductSupply);
        this.moduleGolbal.addProductSupply(_productSupplyForm.getName(), _productSupplyForm.getState());
        this.objectList = this.moduleGolbal.getProductSupplies();

    }

    @Override
    protected void update() throws ProductSupplyException {
        ProductSuppplyForm _productSupplyForm = new ProductSuppplyForm(this.nameProductSupply, this.stateProductSupply);
        ProductSupply _productSupply = new ProductSupply(getSelectedObject().getIdProductSupply(), null, _productSupplyForm.getState(), _productSupplyForm.getName());
        this.moduleGolbal.updateProductSupply(_productSupply);
        this.objectList = this.moduleGolbal.getProductSupplies();
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
