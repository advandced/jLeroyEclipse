package fr.la.jproductbaseweb.beanmanaged.prodentry;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.dialog.Dialog;

import fr.la.jproductbase.metier.Product;
import fr.la.jproductbase.metier.ProductFamily;
import fr.la.jproductbaseweb.beanmanaged.modelForm.CardModifyForm;
import fr.la.jproductbaseweb.beanmanaged.modeltable.ProductLazyList;

@ManagedBean(name = "gestSearchCard")
@ApplicationScoped
public class GestSearchCard extends GestFormSearchAbstract<Product> {

    private ProductLazyList products;

    public GestSearchCard() {
        this.products = new ProductLazyList(1);
    }

    @Override
    public void detailAction() {
        this.reference = getSelectedObject().getProductConf().getReference();
        this.serialNumber = getSelectedObject().getSerialNumber();
        this.macAdress = getSelectedObject().getMacAddress();
        this.dateCode = getSelectedObject().getDatecode();
        this.supplierCode = getSelectedObject().getProviderCode();
        this.state = getSelectedObject().getState();
        this.configuration = this.reference + "~"
                + getSelectedObject().getProductConf().getMajorIndex() + "~"
                + getSelectedObject().getProductConf().getMinorIndex();
    }

    @Override
    public void detailFedd() {
        this.reference = getSelectedObject().getProductConf().getReference();
        this.serialNumber = getSelectedObject().getSerialNumber();
        this.macAdress = getSelectedObject().getMacAddress();
        this.dateCode = getSelectedObject().getDatecode();
        this.supplierCode = getSelectedObject().getProviderCode();
        this.state = getSelectedObject().getState();
        this.configuration = this.reference + "~"
                + getSelectedObject().getProductConf().getMajorIndex() + "~"
                + getSelectedObject().getProductConf().getMinorIndex();
    }

    @Override
    public void getFamiliesListProduct() {
        // TODO Auto-generated method stub
        List<ProductFamily> _prodList = new ArrayList<ProductFamily>();
        _prodList = this.moduleGlobale.getActiveProductFamilies();
        this.productFamilies = new ArrayList<ProductFamily>();
        for (ProductFamily productFamily : _prodList) {
            if (productFamily.getProductType().getName().equals("Carte")) {
                this.productFamilies.add(productFamily);
            }
        }
    }

    @Override
    public void searchProduct() {
        this.products = new ProductLazyList(1);

    }

    @Override
    public void modifyProduct(ActionEvent event) {
        CommandButton _commandButton = (CommandButton) event.getSource();
        Dialog _dialog = getDialogToButton(_commandButton);
        CardModifyForm _crdModForm = new CardModifyForm(this.serialNumber,
                this.dateCode, this.macAdress, this.supplierCode,
                this.state);
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Modification Reussie"));
        this.moduleGlobale.setProduct(this.selectedObject,
                this.selectedObject.getProductConf(), this.serialNumber,
                this.dateCode, this.macAdress, this.supplierCode,
                this.state, moduleGlobale.getProductComponents(this.selectedObject),
                this.selectedObject.getProductSoftwares());
        hideDialog(_dialog);
            // TODO Auto-generated catch block
        	/*
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                    "Erreur Formulaire", e1.toString()));
            */
    }

    public ProductLazyList getProducts() {
        return products;
    }

    public void setProducts(ProductLazyList products) {
        this.products = products;
    }
}