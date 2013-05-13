package fr.la.jproductbaseweb.beanmanaged.saventry;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import fr.la.jproductbase.metier.AfterSaleCom;
import fr.la.jproductbase.service.ServiceInterface;
import fr.la.jproductbaseweb.beanmanaged.exception.DevisPrealableException;
import fr.la.jproductbaseweb.beanmanaged.modelForm.DevisPrealableForm;

@ManagedBean(name = "DevisPrealableBean")
@SessionScoped
public class DevisPrealableBean {

	private ServiceInterface moduleGlobal = ServiceInterface.getInstance();

	private List<AfterSaleCom> listAfterSaleCom;

	private Boolean datafound;

	public DevisPrealableBean() {
		this.refresh();
	}

	public List<AfterSaleCom> getListAfterSaleCom() {
		return listAfterSaleCom;
	}

	public void setListAfterSaleCom(List<AfterSaleCom> listAfterSaleCom) {
		this.listAfterSaleCom = listAfterSaleCom;
	}

	public Boolean getDatafound() {
		return datafound;
	}

	public void setDatafound(Boolean datafound) {
		this.datafound = datafound;
	}

	public void refresh() {
		this.listAfterSaleCom = moduleGlobal.getDevisPrea();
		if (this.listAfterSaleCom.size() != 0) {
			this.datafound = true;
		} else {
			this.datafound = false;
		}
	}

	public void Enregistrer() throws Exception {

		try {
			@SuppressWarnings("unused")
			DevisPrealableForm _devisPrealableForm = new DevisPrealableForm(
					this.listAfterSaleCom);
		} catch (DevisPrealableException e) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "", e
							.getMessage()));
		}
		this.refresh();
	}
}