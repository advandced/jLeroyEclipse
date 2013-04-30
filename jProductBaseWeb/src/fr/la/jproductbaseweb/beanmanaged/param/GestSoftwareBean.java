package fr.la.jproductbaseweb.beanmanaged.param;

import fr.la.jproductbase.metier.Software;
import fr.la.jproductbase.service.ServiceInterface;
import fr.la.jproductbaseweb.beanmanaged.exception.SoftwareException;
import fr.la.jproductbaseweb.beanmanaged.modelForm.SoftwareForm;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.html.HtmlPanelGrid;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.naming.NamingException;
import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.dialog.Dialog;
import org.primefaces.context.RequestContext;

@ManagedBean(name = "gestSoftwareBean")
@ViewScoped
public class GestSoftwareBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private String action;
    private ServiceInterface serviceInterface;
    private List<Software> listSoftware;
    private Software selectedSoftware;
    private String stateString;
    private String nameSoft;
    private String versionSoft;
    private int stateSoft;

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

    public GestSoftwareBean() {
        this.serviceInterface = ServiceInterface.getInstance();
        this.listSoftware = this.serviceInterface.getSoftwares();
    }

    public void modify() {
        this.action = "modify";
        this.nameSoft = getSelectedSoftware().getName();
        this.versionSoft = getSelectedSoftware().getVersion();
        this.stateSoft = getSelectedSoftware().getState();
    }

    public void newSoftware() {
        this.action = "create";
        this.nameSoft = null;
        this.versionSoft = null;
        this.stateSoft = 1;
    }

    public void buttonFormSoftware(ActionEvent event) throws NamingException {
        CommandButton _commandButton = (CommandButton) event.getSource();
        Dialog _dialog = getDialogToButton(_commandButton);

        if (this.action.equals("modify")) {

            try {
                updateSoftware();
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, new FacesMessage(
                        "Modification Effectuee", "Logiciel " + this.nameSoft));
                hideDialog(_dialog);
            } catch (SQLException e) {
            } catch (SoftwareException e) {
                // TODO Auto-generated catch block
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN,
                        "Erreur Formulaire", e.toString()));
            }

        } else {
            createSoftware();
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Creation Effectuee",
                    "Logiciel " + this.nameSoft));
            hideDialog(_dialog);
        }
    }

    private void createSoftware() {

        // Creation d'objet pour manipuler les donnees de la base
        ServiceInterface _serviceInterface = ServiceInterface.getInstance();
        // On crée un nouveau logiciel avec les infos recup dans la requete
        SoftwareForm _softForm = new SoftwareForm(this.nameSoft, this.versionSoft, this.stateSoft);

        _serviceInterface.addSoftware(_softForm.getState(),
                _softForm.getName(), _softForm.getVersion());

        this.listSoftware = _serviceInterface.getSoftwares();

    }

    private void updateSoftware() throws SQLException, SoftwareException,
            NamingException {

        ServiceInterface _serviceInterface = ServiceInterface.getInstance();
        // On recup le logiciel, on le modifie avec les infos recup dans la
        // requete
        Software softwareToBeUpdated = null;

        softwareToBeUpdated = _serviceInterface
                .getSoftware(getSelectedSoftware().getIdSoftware());

        softwareToBeUpdated.setState(this.stateSoft);
        softwareToBeUpdated.setName(this.nameSoft);
        softwareToBeUpdated.setVersion(this.versionSoft);

        SoftwareForm _softForm = new SoftwareForm(this.nameSoft,
                this.versionSoft, this.stateSoft);

        softwareToBeUpdated = _serviceInterface
                .getSoftware(getSelectedSoftware().getIdSoftware());

        softwareToBeUpdated.setState(_softForm.getState());
        softwareToBeUpdated.setName(_softForm.getName());
        softwareToBeUpdated.setVersion(_softForm.getVersion());

        _serviceInterface.updateSoftware(softwareToBeUpdated);

        this.listSoftware = _serviceInterface.getSoftwares();

    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public List<Software> getListSoftware() {
        return listSoftware;
    }

    public void setListSoftware(List<Software> listSoftware) {
        this.listSoftware = listSoftware;
    }

    public Software getSelectedSoftware() {
        return selectedSoftware;
    }

    public void setSelectedSoftware(Software selectedSoftware) {
        this.selectedSoftware = selectedSoftware;
    }

    public ServiceInterface getServiceInterface() {
        return serviceInterface;
    }

    public void setServiceInterface(ServiceInterface serviceInterface) {
        this.serviceInterface = serviceInterface;
    }

    public String getStateString() {
        return stateString;
    }

    public void setStateString(String stateString) {
        this.stateString = stateString;
    }

    public String getNameSoft() {
        return nameSoft;
    }

    public void setNameSoft(String nameSoft) {
        this.nameSoft = nameSoft;
    }

    public String getVersionSoft() {
        return versionSoft;
    }

    public void setVersionSoft(String versionSoft) {
        this.versionSoft = versionSoft;
    }

    public int getStateSoft() {
        return stateSoft;
    }

    public void setStateSoft(int stateSoft) {
        this.stateSoft = stateSoft;
    }
}