package fr.la.jproductbaseweb.beanmanaged.modelForm;

import fr.la.jproductbaseweb.beanmanaged.exception.ProductModifyException;
import java.io.Serializable;

public class CardModifyForm implements Serializable {

    private static final long serialVersionUID = 1L;
    private String serialNumber;
    private String dateCode;
    private String adressMac;
    private String supplierCode;
    private int state;

    public CardModifyForm(String serialNumber, String dateCode,
            String adressMac, String supplierCode, int state)
            throws ProductModifyException {
        super();
        this.serialNumber = serialNumber;
        this.dateCode = dateCode;
        this.adressMac = adressMac;
        this.supplierCode = supplierCode;
        this.state = state;

        if (serialNumber.isEmpty() || dateCode.isEmpty()) {

            throw new ProductModifyException();

        }

    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getDateCode() {
        return dateCode;
    }

    public void setDateCode(String dateCode) {
        this.dateCode = dateCode;
    }

    public String getAdressMac() {
        return adressMac;
    }

    public void setAdressMac(String adressMac) {
        this.adressMac = adressMac;
    }

    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
