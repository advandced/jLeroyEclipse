package fr.la.jproductbaseweb.beanmanaged.saventry;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.context.RequestContext;

import fr.la.jproductbase.metier.AfterSaleCom;
import fr.la.jproductbaseweb.beanmanaged.modelForm.RecapComForm;
import fr.la.jproductbaseweb.beanmanaged.modeltable.AfterSaleComLazy;

@ManagedBean(name = "RecapComBean")
@SessionScoped
public class RecapComBean {
	
	private AfterSaleComLazy listAfterSaleCom;

	private AfterSaleCom selectedAfterSaleCom;

	public RecapComBean() {
		this.refreshlistAfterSaleCom();
	}
	
	public AfterSaleComLazy getListAfterSaleCom() {
		return listAfterSaleCom;
	}

	public void setListAfterSaleCom(AfterSaleComLazy listAfterSaleCom) {
		this.listAfterSaleCom = listAfterSaleCom;
	}

	public AfterSaleCom getSelectedAfterSaleCom() {
		return selectedAfterSaleCom;
	}

	public void setSelectedAfterSaleCom(AfterSaleCom selectedAfterSaleCom) {
		this.selectedAfterSaleCom = selectedAfterSaleCom;
	}

	public void refreshlistAfterSaleCom() {
		this.listAfterSaleCom =  new AfterSaleComLazy();
	}

	public void showDetail() {
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("dialogDetailCom.show()");
	}

	public void hideDetail() {
		this.refreshlistAfterSaleCom();
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("dialogDetailCom.hide()");
	}

	public void showEdit() {
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("dialogEditCom.show()");
	}

	public void hideEdit() {
		this.refreshlistAfterSaleCom();
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("dialogEditCom.hide()");
	}

	public void update() throws Exception {
		if (this.selectedAfterSaleCom.getSavPrice() == null) {
			this.selectedAfterSaleCom.setSavPrice((float) 0);
		}

		@SuppressWarnings("unused")
		RecapComForm _recapComForm = new RecapComForm(
				this.selectedAfterSaleCom.getIdAfterSaleCom(),
				this.selectedAfterSaleCom.getQuotationNumber(),
				this.selectedAfterSaleCom.getQuotationDate(),
				this.selectedAfterSaleCom.getQuotationComment(),
				this.selectedAfterSaleCom.getSavPrice(),
				this.selectedAfterSaleCom.getSavOrderNumber(),
				this.selectedAfterSaleCom.getSavOrderDate(),
				this.selectedAfterSaleCom.getOrderComment());
		this.hideEdit();
	}
}