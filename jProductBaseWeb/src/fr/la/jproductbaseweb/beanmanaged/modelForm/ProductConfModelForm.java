/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.la.jproductbaseweb.beanmanaged.modelForm;

import java.io.Serializable;

import fr.la.jproductbase.metier.ProductConfModel;
import fr.la.jproductbase.service.ServiceInterface;
import fr.la.jproductbaseweb.beanmanaged.exception.ProductConfModelFormException;

/**
 *
 * @author Joff
 */
public class ProductConfModelForm implements Serializable {

    private static final long serialVersionUID = 1L;
    private ServiceInterface moduleGlobal = ServiceInterface.getInstance();
    private ProductConfModel productConfModel;

    public ProductConfModelForm(ProductConfModel productConfModel) throws ProductConfModelFormException {
        this.productConfModel = productConfModel;
        String error = "";
        if (this.productConfModel.getDesignation().equals("") || this.productConfModel.getDesignation() == null) {
            error = error + "Designation";
        }
        if (this.productConfModel.getReference().equals("") || this.productConfModel.getReference() == null) {
            error = error + "Reference";
        }
        if (!error.equals("")) {
            error = error + " manquant.";
            throw new ProductConfModelFormException(error);
        } else {
            if (this.productConfModel.getIdProductConfModel() != 0) {
            	moduleGlobal.updateProductConfModel(this.productConfModel);
            } else {
                moduleGlobal.addProductConfModels(this.productConfModel);
            }
        }
    }
}