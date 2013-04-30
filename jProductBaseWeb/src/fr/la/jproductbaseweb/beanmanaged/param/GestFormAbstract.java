package fr.la.jproductbaseweb.beanmanaged.param;

import fr.la.jproductbase.service.ServiceInterface;
import fr.la.jproductbaseweb.beanmanaged.exception.FormException;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.component.html.HtmlPanelGrid;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.naming.NamingException;
import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.dialog.Dialog;
import org.primefaces.context.RequestContext;

public abstract class GestFormAbstract<T> {

    protected String action;
    protected ServiceInterface moduleGolbal;
    protected T selectedObject;
    protected List<T> objectList;

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

    public GestFormAbstract() {

        this.moduleGolbal = ServiceInterface.getInstance();

    }

    public void selectedModify() {

        this.moduleGolbal = ServiceInterface.getInstance();
        this.action = "modify";

    }

    public void selectedCreate() {
        this.moduleGolbal = ServiceInterface.getInstance();
        this.action = "create";

    }

    public void buttonForm(ActionEvent event) throws NamingException {
        CommandButton _commandButton = (CommandButton) event.getSource();
        Dialog _dialog = getDialogToButton(_commandButton);
        System.out.println("++++++++++++++++++++++++++++++++++++ ACTION ++++++++++++++++++++++++++++++++++++++++ : " + this.action);
        if (this.action != null) {
            if (this.action.equals("modify")) {

                try {
                    update();
                    FacesContext context = FacesContext.getCurrentInstance();
                    context.addMessage(null, new FacesMessage(
                            "Modification Reussie"));
                    hideDialog(_dialog);
                } catch (FormException e) {
                    FacesContext context = FacesContext.getCurrentInstance();
                    context.addMessage(null,
                            new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "Erreur Formulaire", e.toString()));
                }
            }
            if (this.action.equals("create")) {

                try {
                    create();
                    FacesContext context = FacesContext.getCurrentInstance();
                    context.addMessage(null, new FacesMessage("Creation Reussie"));
                    hideDialog(_dialog);
                } catch (FormException e) {
                    FacesContext context = FacesContext.getCurrentInstance();
                    context.addMessage(null,
                            new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "Erreur Formulaire", e.toString()));
                }
            }
        }
    }

    protected abstract void create() throws FormException, NamingException;

    protected abstract void update() throws FormException;

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        System.out.println("-------------------------------------------------------------------------- " + action + " -------------------------------------------------------------------------------");
        this.action = action;
    }

    public T getSelectedObject() {
        return selectedObject;
    }

    public void setSelectedObject(T selected) {
        this.selectedObject = selected;
    }

    public List<T> getObjectList() {
        return objectList;
    }

    public void setObjectList(List<T> objectList) {
        this.objectList = objectList;
    }
}
