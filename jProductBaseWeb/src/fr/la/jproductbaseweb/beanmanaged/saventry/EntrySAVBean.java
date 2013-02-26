package fr.la.jproductbaseweb.beanmanaged.saventry;

import fr.la.jproductbaseweb.userright.UserRightSession;
import fr.la.jproductbaseweb.view.bar.MenuWeb;
import fr.la.juserright.metier.Autorisation;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import org.primefaces.model.MenuModel;

@ManagedBean(name = "entrySAVBean")
@RequestScoped
public class EntrySAVBean {

	private MenuModel modelMenu;
	private MenuWeb menuWeb;
	private UserRightSession userRightSession;
	private String changePage;
	private ActionEvent changePageAction;
	private List<Autorisation> autorisation;

	public EntrySAVBean() throws SQLException {
		this.userRightSession = new UserRightSession();
		this.autorisation = this.userRightSession.getAutorisationList();
		this.menuWeb = new MenuWeb(this.autorisation, "entrySAVBean");
		this.modelMenu = this.menuWeb.getModelMenuBar();

	}

	public ActionEvent getChangePageAction() {
		return changePageAction;
	}

	public void setChangePageAction(ActionEvent changePageAction) {
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

	public List<Autorisation> getautorisation() {
		return autorisation;
	}

	public void setautorisation(List<Autorisation> _autorisation) {
		this.autorisation = _autorisation;
	}

}