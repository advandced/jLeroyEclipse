package fr.la.jproductbaseweb.beanmanaged.modelForm;

import java.util.List;

import fr.la.jproductbase.metier.Product;
import fr.la.jproductbase.metier.Software;
import fr.la.jproductbaseweb.beanmanaged.exception.ProductModifyException;
import java.io.Serializable;

public class ProductModifyForm implements Serializable {

    private static final long serialVersionUID = 1L;
    private String serialNumber;
    private String dateCode;
    private String adressMac;
    private String supplierCode;
    private int state;
    private List<Product> listElements;
    private List<Software> listSoftwares;

    public ProductModifyForm(String serialNumber, String dateCode,
            String adressMac, String supplierCode, int state,
            List<Product> listElements, List<Software> listSoftwares) throws ProductModifyException {
        super();
        this.serialNumber = serialNumber;
        this.dateCode = dateCode;
        this.adressMac = adressMac;
        this.supplierCode = supplierCode;
        this.state = state;
        this.listElements = listElements;
        this.listSoftwares = listSoftwares;
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

    public List<Product> getListElements() {
        return listElements;
    }

    public void setListElements(List<Product> listElements) {
        this.listElements = listElements;
    }

    public List<Software> getListSoftwares() {
        return listSoftwares;
    }

    public void setListSoftwares(List<Software> listSoftwares) {
        this.listSoftwares = listSoftwares;
    }
}
