package fr.la.jproductbaseweb.beanmanaged.modelForm;

import fr.la.jproductbaseweb.beanmanaged.exception.ProductTypeException;
import java.io.Serializable;

public class ProductTypeForm implements Serializable {

    private static final long serialVersionUID = 1L;
    private String name;
    private int state;

    public ProductTypeForm(String name, int state) throws ProductTypeException {
        super();
        this.name = name;
        this.state = state;
        if (name.isEmpty()) {

            throw new ProductTypeException();

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
