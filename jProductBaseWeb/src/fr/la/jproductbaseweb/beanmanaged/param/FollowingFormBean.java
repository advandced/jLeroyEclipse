/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.la.jproductbaseweb.beanmanaged.param;

import fr.la.jproductbase.metier.FollowingFormModel;
import fr.la.jproductbase.service.ServiceInterface;
import fr.la.jproductbaseweb.beanmanaged.exception.FollowingFormException;
import fr.la.jproductbaseweb.beanmanaged.modelForm.FollowingForm;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Joff
 */
@ManagedBean(name = "followingFormBean")
@SessionScoped
public class FollowingFormBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private List<FollowingFormModel> listFollowingForm;
    private FollowingFormModel selectedFollowingForm = new FollowingFormModel();
    private FollowingFormModel newFollowingForm = new FollowingFormModel(1);
    private ServiceInterface moduleGlobal = new ServiceInterface();

    public FollowingFormBean() throws SQLException {
        this.refresh();
    }

    private void refresh() throws SQLException {
        this.listFollowingForm = moduleGlobal.getAllFollowingFormModel();
    }

    public void createopenpopup() {
        this.newFollowingForm = new FollowingFormModel(1);
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("FollowingFormModelDialog.show()");
    }

    public void editopenpopup() {
        this.newFollowingForm = this.selectedFollowingForm;
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("FollowingFormModelDialog.show()");
    }

    public void exitpopup() throws SQLException {
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("FollowingFormModelDialog.hide()");
        this.refresh();
    }

    public void createFollowing() {
        try {
            @SuppressWarnings("unused")
			FollowingForm _addFollow = new FollowingForm(this.newFollowingForm);
            this.exitpopup();
        } catch (SQLException ex) {
            Logger.getLogger(FollowingFormBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FollowingFormException ex) {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN, "", ex.getMessage()));
        }
    }

    public void deleteFollowing() throws SQLException {
        moduleGlobal.deleteFollowingFormModel(this.selectedFollowingForm.getIdFollowingFormmodel());
        this.refresh();
    }

    public List<FollowingFormModel> getListFollowingForm() {
        return listFollowingForm;
    }

    public void setListFollowingForm(List<FollowingFormModel> listFollowingForm) {
        this.listFollowingForm = listFollowingForm;
    }

    public FollowingFormModel getSelectedFollowingForm() {
        return selectedFollowingForm;
    }

    public void setSelectedFollowingForm(FollowingFormModel selectedFollowingForm) {
        this.selectedFollowingForm = selectedFollowingForm;
    }

    public FollowingFormModel getNewFollowingForm() {
        return newFollowingForm;
    }

    public void setNewFollowingForm(FollowingFormModel newFollowingForm) {
        this.newFollowingForm = newFollowingForm;
    }
}