package fr.la.jproductbaseweb.beanmanaged.saventry;

import fr.la.jproductbase.metier.Product;
import fr.la.jproductbaseweb.beanmanaged.modeltable.ProductLazyList;

import java.io.IOException;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name = "gestSearchSAV")
@ViewScoped
public class GestSearchSAVBean implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private ProductLazyList products;
    private Product selectedObject;

    public GestSearchSAVBean() {
        this.products = new ProductLazyList(0);
    }

    /**
     * @return the products
     */
    public ProductLazyList getProducts() {
        return products;
    }

    /**
     * @param products the products to set
     */
    public void setProducts(ProductLazyList products) {
        this.products = products;
    }

    /**
     * @return the selectedObject
     */
    public Product getSelectedObject() {
        return selectedObject;
    }

    /**
     * @param selectedObject the selectedObject to set
     */
    public void setSelectedObject(Product selectedObject) {
        this.selectedObject = selectedObject;
    }
    
    public void redirection() throws IOException{
    	FacesContext.getCurrentInstance().getExternalContext().redirect("/jProductBaseWeb/entrySAV/detailIntervention.jsf?idIntervention=" + this.selectedObject.getIdProduct());
    }
}