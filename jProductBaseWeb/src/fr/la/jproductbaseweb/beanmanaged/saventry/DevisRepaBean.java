package fr.la.jproductbaseweb.beanmanaged.saventry;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import fr.la.configfilereader.ConfigFileReaderException;
import fr.la.jproductbase.metier.AfterSaleCom;
import fr.la.jproductbase.metier.AfterSaleReport;
import fr.la.jproductbase.service.ServiceInterface;
import fr.la.jproductbaseweb.beanmanaged.exception.DevisPrealableException;
import fr.la.jproductbaseweb.beanmanaged.modelForm.DevisPrealableForm;

@ManagedBean(name = "DevisRepaBean")
@SessionScoped
public class DevisRepaBean {

	private ServiceInterface moduleGlobal = ServiceInterface.getInstance();

	private List<AfterSaleCom> listAfterSaleCom = new ArrayList<AfterSaleCom>();

	private Boolean datafound;

	public List<AfterSaleCom> getListAfterSaleCom() {
		return listAfterSaleCom;
	}

	public void setListAfterSaleCom(List<AfterSaleCom> listAfterSaleCom) {
		this.listAfterSaleCom = listAfterSaleCom;
	}

	public DevisRepaBean() {
		this.refresh();
	}

	public Boolean getDatafound() {
		return datafound;
	}

	public void setDatafound(Boolean datafound) {
		this.datafound = datafound;
	}

	public void refresh() {
		this.listAfterSaleCom = new ArrayList<AfterSaleCom>();
		List<AfterSaleReport> _listAfterSaleReport = moduleGlobal.getDevisRepa();
		for (AfterSaleReport a : _listAfterSaleReport) {
			this.listAfterSaleCom.add(new AfterSaleCom(a));
		}
		if (this.listAfterSaleCom.size() != 0) {
			this.datafound = true;
		} else {
			this.datafound = false;
		}
	}

	public void Enregistrer() throws Exception {
		try {
			System.out.println(1);
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
