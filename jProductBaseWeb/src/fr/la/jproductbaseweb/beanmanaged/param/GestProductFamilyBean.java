package fr.la.jproductbaseweb.beanmanaged.param;

import java.sql.SQLException;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.naming.NamingException;

import fr.la.jproductbase.dao.ProductFamilyDaoException;
import fr.la.jproductbase.metier.ProductFamily;
import fr.la.jproductbase.metier.ProductType;
import fr.la.jproductbaseweb.beanmanaged.exception.ProductFamilyException;
import fr.la.jproductbaseweb.beanmanaged.modelForm.FamilyProductForm;
import java.io.Serializable;

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
        try {
            this.objectList = this.moduleGolbal.getProductFamilies();
            this.productTypeList = this.moduleGolbal.getActiveProductTypes();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

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


        @SuppressWarnings("unused")
        FamilyProductForm _familyProductForm = new FamilyProductForm(this.nameProductFamily, this.stateProductFamily, this.selectedProductType);



        // TODO Auto-generated method stub
        try {
            this.moduleGolbal.addProductFamily(this.nameProductFamily, this.stateProductFamily, this.selectedProductType);
            this.objectList = this.moduleGolbal.getProductFamilies();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ProductFamilyDaoException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    @Override
    protected void update() throws ProductFamilyException {

        @SuppressWarnings("unused")
        FamilyProductForm _familyProductForm = new FamilyProductForm(this.nameProductFamily, this.stateProductFamily, this.selectedProductType);


        ProductFamily _productFamily = new ProductFamily(getSelectedObject().getIdProductFamily(), null, this.stateProductFamily, this.nameProductFamily, getSelectedProductType());
        System.out.println(this.stateProductFamily);

        try {
            this.moduleGolbal.updateProductFamily(_productFamily);
            this.objectList = this.moduleGolbal.getProductFamilies();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NamingException e) {
            e.printStackTrace();
        }


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
