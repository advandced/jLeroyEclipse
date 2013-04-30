package fr.la.jproductbaseweb.beanmanaged.saventry;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.naming.NamingException;

import fr.la.configfilereader.ConfigFileReaderException;
import fr.la.jproductbase.metier.AfterSaleCom;
import fr.la.jproductbase.service.ServiceInterface;
import fr.la.jproductbaseweb.beanmanaged.exception.ExpedSAVException;
import fr.la.jproductbaseweb.beanmanaged.modelForm.ExpedSAVForm;

@ManagedBean(name = "ExpedSAVBean")
@SessionScoped
public class ExpedSAVBean {

	private ServiceInterface moduleGlobal = ServiceInterface.getInstance();

	private List<AfterSaleCom> listAfterSaleCom;

	private AfterSaleCom[] selectedAfterSaleCom;
	
	private Boolean dataFound;
	
	private Boolean dataNotFound;
	
	public ExpedSAVBean() {
		this.refresh();
	}

	public void refresh() {
		this.listAfterSaleCom = moduleGlobal.getAfterSaleComExped();
		if(this.listAfterSaleCom.size() == 0){
			this.dataFound = false;
			this.dataNotFound = true;
		} else {
			this.dataFound = true;
			this.dataNotFound = false;
		}
	}

	public List<AfterSaleCom> getListAfterSaleCom() {
		return listAfterSaleCom;
	}

	public void setListAfterSaleCom(List<AfterSaleCom> listAfterSaleCom) {
		this.listAfterSaleCom = listAfterSaleCom;
	}

	public AfterSaleCom[] getSelectedAfterSaleReport() {
		return selectedAfterSaleCom;
	}

	public void setSelectedAfterSaleReport(AfterSaleCom[] selectedAfterSaleCom) {
		this.selectedAfterSaleCom = selectedAfterSaleCom;
	}
	
	public Boolean getDataFound() {
		return dataFound;
	}

	public void setDataFound(Boolean dataFound) {
		this.dataFound = dataFound;
	}

	public Boolean getDataNotFound() {
		return dataNotFound;
	}

	public void setDataNotFound(Boolean dataNotFound) {
		this.dataNotFound = dataNotFound;
	}

	public void Enregistrer() {
		ExpedSAVForm _exped = new ExpedSAVForm(this.selectedAfterSaleCom);
		this.refresh();
	}
}