package fr.la.jproductbaseweb.view.bar;

import fr.la.jproductbaseweb.beanmanaged.listener.MenuActionListerner;
import fr.la.juserright.metier.Autorisation;
import fr.la.juserright.metier.Ressource;
import java.io.Serializable;
import java.util.List;
import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import org.primefaces.component.menuitem.MenuItem;
import org.primefaces.component.submenu.Submenu;
import org.primefaces.model.DefaultMenuModel;
import org.primefaces.model.MenuModel;

public class MenuWeb implements MenuModel, Serializable {

    private static final long serialVersionUID = 1L;
    // private UserRightSession userRightSession;
    // private MenuModel modelMenuBar;
    private List<Autorisation> autorisations;
    private Ressource resource;
    private MenuModel modelMenu;

    public MenuWeb(List<Autorisation> autorisations, String nameResource) {
        this.modelMenu = new DefaultMenuModel();
        this.autorisations = autorisations;
        if (nameResource == null) {
            for (Autorisation autorisation : this.autorisations) {
                MenuItem _menuItem = new MenuItem();
                if (autorisation.getRessource().getRessourceMere()
                        .getIdressource() == 0) {
                    _menuItem.setValue(autorisation.getRessource().getMenu());
                    _menuItem.setUrl(autorisation.getRessource().getPath());
                    this.modelMenu.addMenuItem(_menuItem);
                }
            }
        } else {
            for (Autorisation autorisation : this.autorisations) {
                MenuItem _menuItem = new MenuItem();
                if (autorisation.getRessource().getRessourceMere()
                        .getManagedBean() != null) {
                    if (autorisation.getRessource().getRessourceMere()
                            .getManagedBean().equals(nameResource)) {
                        FacesContext _facesContext = FacesContext
                                .getCurrentInstance();
                        ELContext _elContext = _facesContext.getELContext();
                        ExpressionFactory _expFact = _facesContext
                                .getApplication().getExpressionFactory();
                        _menuItem.setValue(autorisation.getRessource()
                                .getMenu());
                        _menuItem.addActionListener(new MenuActionListerner());
                        _menuItem.getAttributes().put("itemPath",
                                autorisation.getRessource().getPath());
                        _menuItem.setActionExpression(_expFact
                                .createMethodExpression(_elContext,
                                autorisation.getRessource().getPath(),
                                String.class, new Class[0]));
                        _menuItem.setAjax(false);
                        this.modelMenu.addMenuItem(_menuItem);
                    }
                }
            }
        }
    }

    public MenuModel getModelMenuBar() {
        return modelMenu;
    }

    public void setModelMenuBar(MenuModel modelMenuBar) {
        this.modelMenu = modelMenuBar;
    }

    public List<Autorisation> getAuthorizations() {
        return autorisations;
    }

    public void setAuthorizations(List<Autorisation> authorizations) {
        this.autorisations = authorizations;
    }

    public Ressource getResource() {
        return resource;
    }

    public void setResource(Ressource resource) {
        this.resource = resource;
    }

    @Override
    public void addMenuItem(MenuItem arg0) {
        // TODO Auto-generated method stub
    }

    @Override
    public void addSubmenu(Submenu arg0) {
        // TODO Auto-generated method stub
    }

    @Override
    public List<UIComponent> getContents() {
        // TODO Auto-generated method stub
        return null;
    }
}
