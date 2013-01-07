package fr.la.jproductbaseweb.beanmanaged.param;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

@ManagedBean(name = "formOperatorBean")
@ViewScoped
public class FormOperatorBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private String action;
    private String firstName = "test2";
    private String lastName = "test";
    private String code;
    private String state;
    private GestOperatorBean gestOperBean;

    public FormOperatorBean() {

        FacesContext context = FacesContext.getCurrentInstance();
        this.gestOperBean = (GestOperatorBean) context
                .getApplication()
                .getExpressionFactory()
                .createValueExpression(context.getELContext(), "#{gestOperatorBean}",
                GestOperatorBean.class).getValue(context.getELContext());

        this.firstName = this.gestOperBean.getSelectedOp().getFirstName();
        this.lastName = this.gestOperBean.getSelectedOp().getLastName();

        System.out.println("First Name " + this.firstName + " Last Name " + this.lastName);
    }

    public void buttonForm(ActionEvent event) {

        action = (String) event.getComponent().getAttributes().get("test");

    }

    public void editAction() {
        FacesContext context = FacesContext.getCurrentInstance();
        this.gestOperBean = (GestOperatorBean) context
                .getApplication()
                .getExpressionFactory()
                .createValueExpression(context.getELContext(), "#{gestOperatorBean}",
                GestOperatorBean.class).getValue(context.getELContext());

        System.out.println(this.gestOperBean.getAction());
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public GestOperatorBean getGestOperBean() {
        return gestOperBean;
    }

    public void setGestOperBean(GestOperatorBean gestOperBean) {
        this.gestOperBean = gestOperBean;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}