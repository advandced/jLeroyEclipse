package fr.la.jproductbaseweb.beanmanaged.param;

import java.sql.SQLException;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.component.html.HtmlPanelGrid;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.dialog.Dialog;
import org.primefaces.context.RequestContext;

import fr.la.jproductbase.metier.ApparentCause;
import fr.la.jproductbase.metier.ApparentCauseCustomer;
import fr.la.jproductbase.service.ServiceInterface;
import fr.la.jproductbaseweb.beanmanaged.exception.ApparentCauseException;
import fr.la.jproductbaseweb.beanmanaged.modelForm.ApparentCauseForm;
import fr.la.jproductbaseweb.converter.ApparentCauseClientConverter;
import java.io.Serializable;

@ManagedBean(name = "gestApparentCauseBean")
@ApplicationScoped
public class GestApparentCauseBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private String action;
    private List<ApparentCause> apparentCauseList;
    private List<ApparentCauseCustomer> apparentCauseClientList;
    private ServiceInterface moduleGlobal;
    private ApparentCause selectedApparentCause;
    private String descriptionApparentCause;
    private int stateApparentCause;
    private ApparentCauseCustomer apparentCauseCustomer;
    private ApparentCauseClientConverter apparentCauseClientConverter;
    /*private ApparentCause selectedApparentCause;
     private String descriptionApparentCause;
     private int stateApparentCause;
     private ApparentCauseCustomer apparentCauseCustomer;*/

    public GestApparentCauseBean() {
        this.moduleGlobal = ServiceInterface.getInstance();
        this.apparentCauseClientConverter = new ApparentCauseClientConverter();
        this.apparentCauseList = this.moduleGlobal.getApparentCauses();
        this.apparentCauseClientList = this.apparentCauseClientConverter.getApparentCauseList();
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

    public void modify() {

        this.action = "modify";
        this.apparentCauseClientConverter = new ApparentCauseClientConverter();
        this.apparentCauseClientList = this.apparentCauseClientConverter.getApparentCauseList();
        System.out.println(this.action);
        this.apparentCauseCustomer = getSelectedApparentCause().getApparentCauseCustomer();
        this.descriptionApparentCause = getSelectedApparentCause().getDescription();
        this.stateApparentCause = getSelectedApparentCause().getState();


    }

    public void newApparentCause() {
        this.moduleGlobal = ServiceInterface.getInstance();
        this.apparentCauseClientConverter = new ApparentCauseClientConverter();
        this.apparentCauseClientList = this.apparentCauseClientConverter.getApparentCauseList();
        this.action = "create";
        this.descriptionApparentCause = null;
        this.stateApparentCause = 1;

    }

    public void buttonFormApparentCause(ActionEvent event) {
        CommandButton _commandButton = (CommandButton) event.getSource();
        Dialog _dialog = getDialogToButton(_commandButton);
        if (this.action.equals("modify")) {


            try {
                updateApparentCause();
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, new FacesMessage("Modification Effectuee", "Logiciel " + this.descriptionApparentCause));
                hideDialog(_dialog);

            } catch (ApparentCauseException e) {
                // TODO Auto-generated catch block
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Erreur Formulaire", e.toString()));
            }


        } else {

            System.out.println("creation");

            try {
                createApparentCause();
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, new FacesMessage("Creation Effectuee", "Logiciel " + this.descriptionApparentCause));
                hideDialog(_dialog);
            } catch (ApparentCauseException e) {
                // TODO Auto-generated catch block
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Erreur Formulaire", e.toString()));
            }


            // TODO Auto-generated catch block



        }


    }

    private void createApparentCause() throws ApparentCauseException {

        ServiceInterface _serviceInterface = ServiceInterface.getInstance();
        // On crée un nouvelle cause probable avec les infos recup dans la
        // requete
        ApparentCauseForm _appaCauseForm = new ApparentCauseForm(this.descriptionApparentCause, this.stateApparentCause, this.apparentCauseCustomer);



        try {
            _serviceInterface.setApparentCause(null, _appaCauseForm.getState(),
                    _appaCauseForm.getDescription(), _appaCauseForm.getApparentCauseCustomer());
            this.apparentCauseList = this.moduleGlobal.getApparentCauses();

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


    }

    private void updateApparentCause() throws ApparentCauseException {

        ServiceInterface _serviceInterface = ServiceInterface.getInstance();
        // On crée un nouvelle cause probable avec les infos recup dans la
        // requete
        ApparentCauseForm _appaCauseForm = new ApparentCauseForm(this.descriptionApparentCause, this.stateApparentCause, this.apparentCauseCustomer);


        ApparentCause _apparentCause = new ApparentCause(this.selectedApparentCause.getIdApparentCause(), this.apparentCauseCustomer.getTimestamp(), _appaCauseForm.getState(), _appaCauseForm.getDescription(), null);


        try {
            _serviceInterface.setApparentCause(_apparentCause, _appaCauseForm.getState(),
                    _appaCauseForm.getDescription(), _appaCauseForm.getApparentCauseCustomer());
            this.apparentCauseList = this.moduleGlobal.getApparentCauses();
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

    public List<ApparentCause> getApparentCauseList() {
        return apparentCauseList;
    }

    public void setApparentCauseList(List<ApparentCause> apparentCauseList) {
        this.apparentCauseList = apparentCauseList;
    }

    public List<ApparentCauseCustomer> getApparentCauseClientList() {
        return apparentCauseClientList;
    }

    public void setApparentCauseClientList(
            List<ApparentCauseCustomer> apparentCauseClientList) {
        this.apparentCauseClientList = apparentCauseClientList;
    }

    public ApparentCause getSelectedApparentCause() {
        return selectedApparentCause;
    }

    public void setSelectedApparentCause(ApparentCause selectedApparentCause) {
        this.selectedApparentCause = selectedApparentCause;
    }

    public String getDescriptionApparentCause() {
        return descriptionApparentCause;
    }

    public void setDescriptionApparentCause(String descriptionApparentCause) {
        this.descriptionApparentCause = descriptionApparentCause;
    }

    public int getStateApparentCause() {
        return stateApparentCause;
    }

    public void setStateApparentCause(int stateApparentCause) {
        this.stateApparentCause = stateApparentCause;
    }

    public ApparentCauseCustomer getApparentCauseCustomer() {
        return apparentCauseCustomer;
    }

    public void setApparentCauseCustomer(ApparentCauseCustomer apparentCauseCustomer) {
        this.apparentCauseCustomer = apparentCauseCustomer;
    }
}
