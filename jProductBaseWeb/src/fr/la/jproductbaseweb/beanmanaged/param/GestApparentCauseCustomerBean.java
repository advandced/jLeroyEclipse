package fr.la.jproductbaseweb.beanmanaged.param;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.html.HtmlPanelGrid;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.dialog.Dialog;
import org.primefaces.context.RequestContext;

import fr.la.jproductbase.metier.ApparentCauseCustomer;
import fr.la.jproductbase.service.ServiceInterface;
import fr.la.jproductbaseweb.beanmanaged.exception.ApparentCauseException;
import fr.la.jproductbaseweb.beanmanaged.modelForm.ApparentCauseCustomerForm;

@ManagedBean(name = "gestApparentCauseCustomerBean")
@SessionScoped
public class GestApparentCauseCustomerBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private String action;
    private String descriptionApparentCauseCustomer;
    private int stateApparentCauseCustomer;
    private List<ApparentCauseCustomer> apparentCauseCustomerList;
    private ServiceInterface module;
    private ApparentCauseCustomer selectedApparentCustomerCause;

    public GestApparentCauseCustomerBean() {
        this.module = ServiceInterface.getInstance();
        this.apparentCauseCustomerList = this.module.getApparentCausesCustomer();
    }

    private Dialog getDialogToButton(CommandButton commandButton) {

        Dialog _dialog = null;
        HtmlPanelGrid _panelForm = (HtmlPanelGrid) commandButton.getParent();
        _dialog = (Dialog) _panelForm.getParent();

        return _dialog;
    }

    private void hideDialog(Dialog dialog) {

        RequestContext _context = RequestContext.getCurrentInstance();

        _context.execute(dialog.getWidgetVar() + ".hide()");

    }

    public void quit(ActionEvent event) {

        CommandButton _buttonAnnul = (CommandButton) event.getSource();
        Dialog _dialog = getDialogToButton(_buttonAnnul);

        hideDialog(_dialog);
        // return "/param/operator.jsf";

    }

    public void selectedModify() {
        this.action = "modify";

        this.descriptionApparentCauseCustomer = getSelectedApparentCustomerCause()
                .getDescription();
        this.stateApparentCauseCustomer = getSelectedApparentCustomerCause()
                .getState();
    }

    public void selectedCreate() {
        this.action = "create";
        this.descriptionApparentCauseCustomer = null;
        this.stateApparentCauseCustomer = 1;

    }

    public void buttonFormApparentCauseCustomer(ActionEvent event) {
        CommandButton _commandButton = (CommandButton) event.getSource();
        Dialog _dialog = getDialogToButton(_commandButton);
        if (this.action.equals("modify")) {


            try {
                update();
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, new FacesMessage("Modification Effectuee", "Cause Probables Client " + this.descriptionApparentCauseCustomer));
                hideDialog(_dialog);
            } catch (ApparentCauseException e) {
                // TODO Auto-generated catch block
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Erreur Formulaire", e.toString()));
            }


        } else {

            try {
                create();
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, new FacesMessage("Creation Effectuee", "Cause Probables Client " + this.descriptionApparentCauseCustomer));
                hideDialog(_dialog);
            } catch (ApparentCauseException e) {
                // TODO Auto-generated catch block
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Erreur Formulaire", e.toString()));
            }

        }

    }

    private void update() throws ApparentCauseException {

        ApparentCauseCustomerForm _apparentCauseCustomerForm = new ApparentCauseCustomerForm(this.descriptionApparentCauseCustomer, this.stateApparentCauseCustomer);


        try {
            this.module.setApparentCauseCustomer(
                    getSelectedApparentCustomerCause(),
                    _apparentCauseCustomerForm.getState(),
                    _apparentCauseCustomerForm.getDescription());
            this.apparentCauseCustomerList = this.module
                    .getApparentCausesCustomer();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }



    }

    private void create() throws ApparentCauseException {

        ApparentCauseCustomerForm _apparentCauseCustomerForm = new ApparentCauseCustomerForm(this.descriptionApparentCauseCustomer, this.stateApparentCauseCustomer);



        try {
            this.module.setApparentCauseCustomer(null,
                    _apparentCauseCustomerForm.getState(),
                    _apparentCauseCustomerForm.getDescription());
            this.apparentCauseCustomerList = this.module
                    .getApparentCausesCustomer();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public List<ApparentCauseCustomer> getApparentCauseCustomerList() {
        return apparentCauseCustomerList;
    }

    public void setApparentCauseCustomerList(
            List<ApparentCauseCustomer> apparentCauseCustomerList) {
        this.apparentCauseCustomerList = apparentCauseCustomerList;
    }

    public ApparentCauseCustomer getSelectedApparentCustomerCause() {
        return selectedApparentCustomerCause;
    }

    public void setSelectedApparentCustomerCause(
            ApparentCauseCustomer selectedApparentCustomerCause) {
        this.selectedApparentCustomerCause = selectedApparentCustomerCause;
    }

    public String getDescriptionApparentCauseCustomer() {
        return descriptionApparentCauseCustomer;
    }

    public void setDescriptionApparentCauseCustomer(
            String descriptionApparentCauseCustomer) {
        this.descriptionApparentCauseCustomer = descriptionApparentCauseCustomer;
    }

    public int getStateApparentCauseCustomer() {
        return stateApparentCauseCustomer;
    }

    public void setStateApparentCauseCustomer(int stateApparentCauseCustomer) {
        this.stateApparentCauseCustomer = stateApparentCauseCustomer;
    }
}
