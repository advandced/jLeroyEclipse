package fr.la.jproductbaseweb.beanmanaged.prodentry;

import fr.la.jproductbase.metier.Product;
import fr.la.jproductbase.metier.ProductFamily;
import fr.la.jproductbase.service.ServiceInterface;
import java.sql.SQLException;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.dialog.Dialog;
import org.primefaces.context.RequestContext;

public abstract class GestFormSearchAbstract<T> {

    protected String reference;
    protected String majorIndex;
    protected String minorIndex;
    protected String dateCode;
    protected String serialNumber;
    protected String model;
    protected String macAdress;
    protected String supplierCode;
    protected String configuration;
    protected int state;
    protected String pathLoadingPage;
    protected Product Mother;
    protected List<ProductFamily> productFamilies;
    protected List<T> productObjectList;
    protected ServiceInterface moduleGlobale;
    protected ProductFamily selectedProductFamily;
    protected T selectedObject;
    protected FacesContext context;

    public GestFormSearchAbstract() {

        this.moduleGlobale = new ServiceInterface();
        this.context = FacesContext.getCurrentInstance();
        try {
            getFamiliesListProduct();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


    }

    @PostConstruct
    protected void loadedPage() {
        System.out.println("page Loaded");

        this.pathLoadingPage = this.context.getViewRoot().getViewId();

        System.out.println("path" + this.pathLoadingPage);

    }

    protected Dialog getDialogToButton(CommandButton commandButton) {

        Dialog _dialog = null;

        _dialog = (Dialog) commandButton.getParent();

        return _dialog;
    }

    protected void hideDialog(Dialog dialog) {

        RequestContext _context = RequestContext.getCurrentInstance();

        _context.execute(dialog.getWidgetVar() + ".hide()");

    }

    public void quit(ActionEvent event) {

        CommandButton _buttonAnnul = (CommandButton) event.getSource();
        Dialog _dialog = getDialogToButton(_buttonAnnul);

        hideDialog(_dialog);
        // return "/param/operator.jsf";

    }

    public abstract void detailAction();

    public abstract void getFamiliesListProduct() throws SQLException;

    public abstract void searchProduct();

    public abstract void modifyProduct(ActionEvent event);

    public String getMajorIndex() {
        return majorIndex;
    }

    public void setMajorIndex(String majorIndex) {
        this.majorIndex = majorIndex;
    }

    public String getMinorIndex() {
        return minorIndex;
    }

    public void setMinorIndex(String minorIndex) {
        this.minorIndex = minorIndex;
    }

    public String getDateCode() {
        return dateCode;
    }

    public void setDateCode(String dateCode) {
        this.dateCode = dateCode;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getMacAdress() {
        return macAdress;
    }

    public void setMacAdress(String macAdress) {
        this.macAdress = macAdress;
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

    public List<ProductFamily> getProductFamilies() {
        return productFamilies;
    }

    public void setProductFamilies(List<ProductFamily> productFamilies) {
        this.productFamilies = productFamilies;
    }

    public List<T> getProductObjectList() {
        return productObjectList;
    }

    public void setProductObjectList(List<T> productObjectList) {
        this.productObjectList = productObjectList;
    }

    public ProductFamily getSelectedProductFamily() {
        return selectedProductFamily;
    }

    public void setSelectedProductFamily(ProductFamily selectedProductFamily) {
        this.selectedProductFamily = selectedProductFamily;
    }

    public T getSelectedObject() {
        return selectedObject;
    }

    public void setSelectedObject(T selectedObject) {
        this.selectedObject = selectedObject;
    }

    public String getConfiguration() {
        return configuration;
    }

    public void setConfiguration(String configuration) {
        this.configuration = configuration;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }
}
