package fr.la.jproductbaseweb.beanmanaged.modelForm;

import fr.la.jproductbaseweb.beanmanaged.exception.OperatorException;
import java.io.Serializable;

public class OperatorForm implements Serializable {

    private static final long serialVersionUID = 1L;
    private String surName;
    private String name;
    private String code;
    private int state;

    public OperatorForm(String surName, String name, String code, int state) throws OperatorException {
        super();
        this.surName = surName;
        this.name = name;
        this.code = code;
        this.state = state;

        if (this.surName.isEmpty() || this.name.isEmpty() || this.code.isEmpty()) {

            throw new OperatorException();

        }
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
