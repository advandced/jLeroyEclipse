/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.la.jproductbaseweb.beanmanaged.modelForm;

import fr.la.jproductbase.metier.ProductConfModel;
import fr.la.jproductbase.service.ServiceInterface;
import fr.la.jproductbaseweb.beanmanaged.exception.ProductConfModelFormException;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Joff
 */
public class ProductConfModelForm implements Serializable {

    private static final long serialVersionUID = 1L;
    private ServiceInterface moduleGlobal = new ServiceInterface();
    private ProductConfModel productConfModel;

    public ProductConfModelForm(ProductConfModel productConfModel) throws ProductConfModelFormException {
        this.productConfModel = productConfModel;
        String error = "";
        try {
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
                    try {
                        moduleGlobal.updateProductConfModel(this.productConfModel);
                    } catch (SQLException ex) {
                        Logger.getLogger(ProductConfModelForm.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    try {
                        moduleGlobal.addProductConfModels(this.productConfModel);
                    } catch (SQLException ex) {
                        Logger.getLogger(ProductConfModelForm.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        } catch (ProductConfModelFormException e) {
            throw new ProductConfModelFormException(error);
        }
    }
}