/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.la.jproductbaseweb.beanmanaged.modelForm;

import fr.la.jproductbase.metier.FollowingFormModel;
import fr.la.jproductbase.service.ServiceInterface;
import fr.la.jproductbaseweb.beanmanaged.exception.FollowingFormException;
import java.io.Serializable;
import java.sql.SQLException;

/**
 *
 * @author Joff
 */
public class FollowingForm implements Serializable {

    private static final long serialVersionUID = 1L;
    private ServiceInterface moduleGlobal = ServiceInterface.getInstance();
    private FollowingFormModel followingFormModel;

    public FollowingForm(FollowingFormModel _followingFormModel) throws SQLException, FollowingFormException {
        this.followingFormModel = _followingFormModel;
        String error = "";
        try {
            if (this.followingFormModel.getVersion().equals("") || this.followingFormModel.getVersion() == null) {
                error = error + "Version ";
            }
            if (this.followingFormModel.getName().equals("") || this.followingFormModel.getName() == null) {
                error = error + "Name ";
            }
            if (this.followingFormModel.getJasperReport().equals("") || this.followingFormModel.getJasperReport() == null) {
                error = error + "JasperReport ";
            }
            if (!error.equals("")) {
                error = error + "manquant.";
                throw new FollowingFormException(error);
            } else {
                if (_followingFormModel.getIdFollowingFormmodel() != 0) {
                    moduleGlobal.updateFollowingFormModel(_followingFormModel);
                } else {
                    moduleGlobal.addFollowingFormModel(_followingFormModel);
                }
            }
        } catch (FollowingFormException e) {
            throw new FollowingFormException(error);
        }
    }
}
