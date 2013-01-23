package fr.la.jproductbaseweb.beanmanaged.param;

import fr.la.configfilereader.ConfigFileReaderException;
import fr.la.jproductbase.dao.OperatorDaoException;
import fr.la.jproductbase.metier.Operator;
import fr.la.jproductbase.service.ServiceInterface;
import fr.la.jproductbaseweb.beanmanaged.exception.OperatorException;
import fr.la.jproductbaseweb.beanmanaged.modelForm.OperatorForm;
import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.html.HtmlPanelGrid;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.naming.NamingException;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.dialog.Dialog;
import org.primefaces.context.RequestContext;

@ManagedBean(name = "gestOperatorBean")
@ViewScoped
public class GestOperatorBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private ServiceInterface moduleGlobal;
    private String firstNameOP;
    private String lastNameOP;
    private String codeOP;
    private int stateOP;
    private List<Operator> operatorList;
    private Operator selectedOp;
    private String action;
    @SuppressWarnings("unused")
    private String valueAction;

    public GestOperatorBean() {

        this.moduleGlobal = new ServiceInterface();
        this.selectedOp = new Operator();
        this.action = null;
        this.operatorList = new ArrayList<>();
        try {
            this.operatorList = moduleGlobal.getOperators();
            // System.out.println("la taille"+_listeOperateursBD.size()+" le nom "+
            // _listeOperateursBD.get(0).getFirstName());
        } catch (SQLException e) {
        }
    }

    public void modify() {
        this.action = "modify";
        System.out.println("click modify" + getSelectedOp().getFirstName()
                + " value action " + this.action);
        this.firstNameOP = getSelectedOp().getFirstName();
        this.lastNameOP = getSelectedOp().getLastName();
        this.codeOP = getSelectedOp().getCode();
        this.stateOP = getSelectedOp().getState();
    }

    public void newOperator() {
        this.action = "create";
        this.firstNameOP = null;
        this.lastNameOP = null;
        this.codeOP = null;
        this.stateOP = 0;
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

    public void postProcessXLS(Object document) {
        HSSFWorkbook wb = (HSSFWorkbook) document;
        HSSFSheet sheet = wb.getSheetAt(0);
        HSSFRow header = sheet.getRow(0);

        HSSFCellStyle cellStyle = wb.createCellStyle();
        cellStyle.setFillForegroundColor(HSSFColor.GREEN.index);
        cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

        for (int i = 0; i < header.getPhysicalNumberOfCells(); i++) {
            HSSFCell cell = header.getCell(i);

            cell.setCellStyle(cellStyle);
        }
    }

    public List<Operator> getOperatorList() {
        return operatorList;
    }

    public void setOperatorList(List<Operator> operatorList) {
        this.operatorList = operatorList;
    }

    public Operator getSelectedOp() {
        return selectedOp;
    }

    public void setSelectedOp(Operator selectedOp) {

        if (selectedOp != null) {
            this.selectedOp = selectedOp;
            @SuppressWarnings("unused")
            FormOperatorBean formOp = new FormOperatorBean();
        }

    }

    public void buttonFormOperator(ActionEvent event) throws NamingException {
        CommandButton _commandButton = (CommandButton) event.getSource();

        Dialog _dialog = getDialogToButton(_commandButton);

        System.out.println(this.action);

        if (this.action.equals("modify")) {
            try {
                updateOperatorGOpe();
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null,
                        new FacesMessage("Modification effectué", "Opérateur "
                        + this.lastNameOP));
                hideDialog(_dialog);
            } catch (ConfigFileReaderException | IOException | SQLException e) {
            } catch (OperatorException e) {
                // TODO Auto-generated catch block
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, new FacesMessage(
                        FacesMessage.SEVERITY_WARN, "Erreur Formulaire",
                        "tous les champs sont obligatoires"));
            }
        } else {
            try {
                createOperatorGOpe();
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, new FacesMessage(
                        "Enregistrement Effectué", "Opérateur "
                        + this.lastNameOP));
                hideDialog(_dialog);
            } catch (ConfigFileReaderException | IOException | SQLException | OperatorDaoException e) {
            } catch (OperatorException e) {
                // TODO Auto-generated catch block
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, new FacesMessage(
                        FacesMessage.SEVERITY_WARN, "Erreur Formulaire",
                        "tous les champs sont obligatoires"));
            }
        }
    }

    private void createOperatorGOpe() throws ConfigFileReaderException,
            IOException, SQLException, OperatorDaoException, OperatorException, NamingException {
        // Creation d'objet pour manipuler les donnees de la base
        System.out.println("creation d'un opérateur");
        ServiceInterface _serviceInterface = new ServiceInterface();


        OperatorForm _opForm = new OperatorForm(this.lastNameOP, this.firstNameOP,
                this.codeOP, this.stateOP);

        _serviceInterface.addOperator(_opForm.getSurName(), _opForm.getName(),
                _opForm.getCode(), _opForm.getState());
        this.operatorList = moduleGlobal.getOperators();

    }

    @SuppressWarnings("unused")
    private void updateOperatorGOpe() throws ConfigFileReaderException,
            IOException, SQLException, OperatorException, NamingException {
        // Creation d'objet pour manipuler les donnees de la base
        ServiceInterface _serviceInterface = new ServiceInterface();
        // On recup l'operateur, on le modifie avec les infos recup dans la
        // requete
        int idOperatorToUpdate = getSelectedOp().getIdOperator();
        String firstNameOperatorToUpdate = this.firstNameOP;
        String lastNameOperatorToUpdate = this.lastNameOP;
        String codeOperatorToUpdate = this.codeOP;
        int stateOperatorToUpdate = this.stateOP;

        OperatorForm _opForm = new OperatorForm(this.lastNameOP,
                this.firstNameOP, this.codeOP, this.stateOP);

        Operator operatorToBeUpdated = _serviceInterface
                .getOperator(idOperatorToUpdate);
        operatorToBeUpdated.setFirstName(_opForm.getSurName());
        operatorToBeUpdated.setLastName(_opForm.getName());
        operatorToBeUpdated.setCode(_opForm.getCode());
        operatorToBeUpdated.setState(_opForm.getState());

        _serviceInterface.updateOperator(operatorToBeUpdated);
        this.operatorList = moduleGlobal.getOperators();
        setSelectedOp(null);

    }

    public String getFirstNameOP() {
        return firstNameOP;
    }

    public void setFirstNameOP(String firstNameOP) {
        this.firstNameOP = firstNameOP;
    }

    public ServiceInterface getModuleGlobal() {
        return moduleGlobal;
    }

    public void setModuleGlobal(ServiceInterface moduleGlobal) {
        this.moduleGlobal = moduleGlobal;
    }

    public String getLastNameOP() {
        return lastNameOP;
    }

    public void setLastNameOP(String lastNameOP) {
        this.lastNameOP = lastNameOP;
    }

    public String getCodeOP() {
        return codeOP;
    }

    public void setCodeOP(String codeOP) {
        this.codeOP = codeOP;
    }

    public int getStateOP() {
        return stateOP;
    }

    public void setStateOP(int stateOP) {
        this.stateOP = stateOP;
    }

    public String getAction() {
        System.out.println("recup action value " + this.action);
        return action;
    }

    public void setAction(String action) {
        System.out.println("setting value action " + action);
        this.action = action;
    }

    public void setValueAction(String valueAction) {
        System.out.println(valueAction);
        this.valueAction = valueAction;}
}