package fr.la.jproductbaseweb.beanmanaged;

import fr.la.jproductbaseweb.userright.UserRightSession;
import fr.la.jproductbaseweb.view.bar.MenuWeb;
import fr.la.juserright.metier.Autorisation;
import java.sql.SQLException;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.primefaces.model.MenuModel;

@ManagedBean(name = "index")
@SessionScoped
public class Index {

    private MenuModel modelMenu;
    private MenuWeb menuWeb;
    private UserRightSession userRightSession;
    private List<Autorisation> autorisation;

    public Index() throws SQLException {
        this.userRightSession = new UserRightSession();
        this.autorisation = this.userRightSession.getAutorisationList();
        if (this.autorisation != null) {
            this.menuWeb = new MenuWeb(this.userRightSession.getAutorisationList(), null);
            this.modelMenu = this.menuWeb.getModelMenuBar();
        }

    }

    public MenuModel getModelMenu() {
        return modelMenu;
    }
}
