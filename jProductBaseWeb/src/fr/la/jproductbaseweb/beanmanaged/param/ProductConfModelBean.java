/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.la.jproductbaseweb.beanmanaged.param;

import fr.la.jproductbase.metier.ProductConfModel;
import fr.la.jproductbase.service.ServiceInterface;
import fr.la.jproductbaseweb.beanmanaged.exception.ProductConfModelFormException;
import fr.la.jproductbaseweb.beanmanaged.modelForm.ProductConfModelForm;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Joff
 */
@ManagedBean(name = "productConfModelBean")
@ViewScoped
public class ProductConfModelBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private List<ProductConfModel> listProductConfModel;
    private ProductConfModel selectedProductConfModel = new ProductConfModel();
    private ProductConfModel newProductConfModel = new ProductConfModel();
    private ServiceInterface moduleGlobal = new ServiceInterface();

    public ProductConfModelBean() throws SQLException {
        this.refresh();
    }

    public void refresh() throws SQLException {
        this.listProductConfModel = moduleGlobal.getProductConfModels();
    }

    public void createopenpopup() {
        this.setNewProductConfModel(new ProductConfModel());
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("ProductConfModelDialog.show()");
    }

    public void editopenpopup() {
        this.setNewProductConfModel(this.getSelectedProductConfModel());
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("ProductConfModelDialog.show()");
    }

    public void exitpopup() throws SQLException {
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("ProductConfModelDialog.hide()");
        this.refresh();
    }

    public void createProductConfModel() throws SQLException {
        try {
            @SuppressWarnings("unused")
			ProductConfModelForm _productConfModelForm = new ProductConfModelForm(this.newProductConfModel);
            this.exitpopup();
        } catch (ProductConfModelFormException ex) {
            this.refresh();
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN, "", ex.getMessage()));
        }
    }

    public void deleteProductConfModel() throws SQLException {
        moduleGlobal.delProductConfModels(this.selectedProductConfModel.getIdProductConfModel());
        this.refresh();
    }

    public List<ProductConfModel> getListProductConfModel() {
        return listProductConfModel;
    }

    public void setListProductConfModel(List<ProductConfModel> listProductConfModel) {
        this.listProductConfModel = listProductConfModel;
    }

    public ProductConfModel getSelectedProductConfModel() {
        return selectedProductConfModel;
    }

    public void setSelectedProductConfModel(ProductConfModel selectedProductConfModel) {
        this.selectedProductConfModel = selectedProductConfModel;
    }

    public ProductConfModel getNewProductConfModel() {
        return newProductConfModel;
    }

    public void setNewProductConfModel(ProductConfModel newProductConfModel) {
        this.newProductConfModel = newProductConfModel;
    }

    public ServiceInterface getModuleGlobal() {
        return moduleGlobal;
    }

    public void setModuleGlobal(ServiceInterface moduleGlobal) {
        this.moduleGlobal = moduleGlobal;
    }
}
