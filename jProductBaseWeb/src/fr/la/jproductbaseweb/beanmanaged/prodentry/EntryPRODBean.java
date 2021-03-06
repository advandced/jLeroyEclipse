package fr.la.jproductbaseweb.beanmanaged.prodentry;

import fr.la.jproductbaseweb.userright.UserRightSession;
import fr.la.jproductbaseweb.view.bar.MenuWeb;
import java.awt.event.ActionEvent;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import org.primefaces.model.MenuModel;

@ManagedBean(name = "entryPRODBean")
@RequestScoped
public class EntryPRODBean {

    private MenuModel modelMenu;
    private MenuWeb menuWeb;
    private UserRightSession userRightSession;
    private String changePage;
    private ActionEvent changePageAction;

    public EntryPRODBean() {

        FacesContext context = FacesContext.getCurrentInstance();
        this.userRightSession = (UserRightSession) context
                .getApplication()
                .getExpressionFactory()
                .createValueExpression(context.getELContext(), "#{userRightSession}",
                UserRightSession.class).getValue(context.getELContext());

        this.menuWeb = new MenuWeb(this.userRightSession.getAutorisationList(), "entryPRODBean");
        this.modelMenu = this.menuWeb.getModelMenuBar();
    }

    public ActionEvent getChangePageAction() {
        return changePageAction;
    }

    public void setChangePageAction(ActionEvent changePageAction) {
        System.out.println(changePageAction.getSource());
        this.changePageAction = changePageAction;
    }

    public MenuModel getModelMenu() {
        return modelMenu;
    }

    public void setModelMenu(MenuModel modelMenu) {
        this.modelMenu = modelMenu;
    }

    public String getChangePage() {
        return changePage;
    }

    public void setChangePage(String changePage) {
        this.changePage = changePage;
    }
}