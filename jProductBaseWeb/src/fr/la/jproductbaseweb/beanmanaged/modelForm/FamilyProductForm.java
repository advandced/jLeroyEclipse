package fr.la.jproductbaseweb.beanmanaged.modelForm;

import fr.la.jproductbase.metier.ProductType;
import fr.la.jproductbaseweb.beanmanaged.exception.ProductFamilyException;
import java.io.Serializable;

public class FamilyProductForm implements Serializable {

    private static final long serialVersionUID = 1L;
    private String name;
    private int state;
    private ProductType productType;

    public FamilyProductForm(String name, int state, ProductType productType) throws ProductFamilyException {
        super();
        this.name = name;
        this.state = state;
        this.productType = productType;

        if (name.isEmpty() || productType == null) {

            throw new ProductFamilyException();

        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }
}
