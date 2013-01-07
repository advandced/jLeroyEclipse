package fr.la.jproductbaseweb.beanmanaged.listener;

import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionEvent;
import javax.faces.event.ActionListener;

import org.primefaces.component.menuitem.MenuItem;

import fr.la.jproductbaseweb.beanmanaged.param.ParametrageBean;

public class MenuActionListerner implements ActionListener {
private ParametrageBean param;
	@Override
	public void processAction(ActionEvent event)
			throws AbortProcessingException {
		MenuItem _selectedMenuItem = (MenuItem)event.getSource();
		FacesContext context = FacesContext.getCurrentInstance();
		this.param  = (ParametrageBean) context
				.getApplication()
				.getExpressionFactory()
				.createValueExpression(context.getELContext(), "#{parametrageBean}",
						ParametrageBean.class).getValue(context.getELContext());
		
		this.param.setChangePage(_selectedMenuItem.getAttributes().get("id").toString());
		this.param.getChangePage();
	}

}
