package fr.la.jproductbaseweb.beanmanaged.modelForm;

import fr.la.jproductbaseweb.beanmanaged.exception.ProductSupplyException;
import java.io.Serializable;

public class ProductSuppplyForm implements Serializable {

    private static final long serialVersionUID = 1L;
    private String name;
    private int state;

    public ProductSuppplyForm(String name, int state) throws ProductSupplyException {
        super();
        this.name = name;
        this.state = state;
        if (name.isEmpty()) {

            throw new ProductSupplyException();


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
}
