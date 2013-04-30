package fr.la.jproductbaseweb.beanmanaged.param;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import fr.la.jproductbase.metier.ProductFamily;
import fr.la.jproductbase.metier.ProductType;
import fr.la.jproductbaseweb.beanmanaged.exception.ProductFamilyException;

@ManagedBean(name = "gestProductFamilyBean")
@ApplicationScoped
public class GestProductFamilyBean extends GestFormAbstract<ProductFamily> implements Serializable {

    private static final long serialVersionUID = 1L;
    private String nameProductFamily;
    private int stateProductFamily;
    private ProductType productTypeProductFamily;
    private List<ProductType> productTypeList;
    private ProductType selectedProductType;

    public GestProductFamilyBean() {
        super();
        this.objectList = this.moduleGolbal.getProductFamilies();
        this.productTypeList = this.moduleGolbal.getActiveProductTypes();
    }

    @Override
    public void selectedCreate() {
        // TODO Auto-generated method stub
        super.selectedCreate();
        this.nameProductFamily = null;
        this.stateProductFamily = 1;

    }

    @Override
    public void selectedModify() {
        // TODO Auto-generated method stub
        super.selectedModify();
        this.nameProductFamily = getSelectedObject().getName();
        this.stateProductFamily = getSelectedObject().getState();
        this.selectedProductType = getSelectedObject().getProductType();
    }

    @Override
    protected void create() throws ProductFamilyException {
        this.moduleGolbal.addProductFamily(this.nameProductFamily, this.stateProductFamily, this.selectedProductType);
        this.objectList = this.moduleGolbal.getProductFamilies();

    }

    @Override
    protected void update() throws ProductFamilyException {
        ProductFamily _productFamily = new ProductFamily(getSelectedObject().getIdProductFamily(), null, this.stateProductFamily, this.nameProductFamily, getSelectedProductType());
        this.moduleGolbal.updateProductFamily(_productFamily);
        this.objectList = this.moduleGolbal.getProductFamilies();
    }

    public String getNameProductFamily() {
        return nameProductFamily;
    }

    public void setNameProductFamily(String nameProductFamily) {
        this.nameProductFamily = nameProductFamily;
    }

    public int getStateProductFamily() {
        return stateProductFamily;
    }

    public void setStateProductFamily(int stateProductFamily) {
        this.stateProductFamily = stateProductFamily;
    }

    public ProductType getProductTypeProductFamily() {
        return productTypeProductFamily;
    }

    public void setProductTypeProductFamily(ProductType productTypeProductFamily) {
        this.productTypeProductFamily = productTypeProductFamily;
    }

    public List<ProductType> getProductTypeList() {
        return productTypeList;
    }

    public void setProductTypeList(List<ProductType> productTypeList) {
        this.productTypeList = productTypeList;
    }

    public ProductType getSelectedProductType() {
        return selectedProductType;
    }

    public void setSelectedProductType(ProductType selectedProductType) {
        this.selectedProductType = selectedProductType;
    }
}
