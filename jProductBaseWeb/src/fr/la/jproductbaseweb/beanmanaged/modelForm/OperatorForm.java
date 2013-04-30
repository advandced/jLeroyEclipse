package fr.la.jproductbaseweb.beanmanaged.modelForm;

import java.io.Serializable;

public class OperatorForm implements Serializable {

    private static final long serialVersionUID = 1L;
    private String surName;
    private String name;
    private String code;
    private int state;

    public OperatorForm(String surName, String name, String code, int state) {
        super();
        this.surName = surName;
        this.name = name;
        this.code = code;
        this.state = state;

        if (this.surName.isEmpty() || this.name.isEmpty() || this.code.isEmpty()) {
        	//"Les champs sont obligatoires"
            throw new IllegalArgumentException();

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
