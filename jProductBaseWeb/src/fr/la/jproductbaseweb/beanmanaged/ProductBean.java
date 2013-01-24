package fr.la.jproductbaseweb.beanmanaged;

import fr.la.jproductbase.metier.Product;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "productBean")
@SessionScoped
public class ProductBean extends Product {

    private static final long serialVersionUID = 1L;
    private boolean selectedProduct;

    public ProductBean() {
        super();
    }

    public boolean isSelectedProduct() {
        return selectedProduct;
    }

    public void setSelectedProduct(boolean selectedProduct) {
        this.selectedProduct = selectedProduct;
    }

    @Override
    public String toString() {
        return "ProductBean [selectedProduct=" + selectedProduct
                + ", idProduct=" + idProduct + ", timestamp=" + timestamp
                + ", state=" + state + ", serialNumber=" + serialNumber
                + ", datecode=" + datecode + ", macAddress=" + macAddress
                + ", providerCode=" + providerCode + ", productConf="
                + productConf + ", productComponents=" + productComponents
                + ", productSoftwares=" + productSoftwares + "]";
    }
}