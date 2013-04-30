package fr.la.jproductbaseweb.beanmanaged.saventry;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.naming.NamingException;

import fr.la.configfilereader.ConfigFileReaderException;
import fr.la.jproductbase.metier.AfterSaleCom;
import fr.la.jproductbase.service.ServiceInterface;
import fr.la.jproductbaseweb.beanmanaged.exception.NumCommandeException;
import fr.la.jproductbaseweb.beanmanaged.modelForm.NumCommandeForm;

@ManagedBean(name = "NumCommandeBean")
@SessionScoped
public class NumCommandeBean {

	private ServiceInterface moduleGlobal = ServiceInterface.getInstance();

	private String search;

	private String oldsearch;
	
	private List<AfterSaleCom> listAfterSaleCom;

	private Boolean result;

	public NumCommandeBean() {
		this.search = null;
		this.listAfterSaleCom = null;
		this.result = false;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public List<AfterSaleCom> getListAfterSaleCom() {
		return listAfterSaleCom;
	}

	public void setListAfterSaleCom(List<AfterSaleCom> listAfterSaleCom) {
		this.listAfterSaleCom = listAfterSaleCom;
	}

	public Boolean getResult() {
		return result;
	}

	public void setResult(Boolean result) {
		this.result = result;
	}

	public void refresh() throws SQLException, ConfigFileReaderException, IOException {
		this.listAfterSaleCom = moduleGlobal.RechercheNumCmd(this.oldsearch);
	}
	
	public void recherche() throws SQLException, ConfigFileReaderException,
			IOException, NumCommandeException {
		try {
			this.result = false;
			@SuppressWarnings("unused")
			NumCommandeForm _NumCommandeForm = new NumCommandeForm(this.search);
			this.listAfterSaleCom = moduleGlobal.RechercheNumCmd(this.search);
			if (this.listAfterSaleCom.size() != 0) {
				this.result = true;
			} else {
				throw new NumCommandeException("Aucun Devis ne correspond a ce numero.");
			}
			this.oldsearch = this.search;
		} catch (NumCommandeException e) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "", e
							.getMessage()));
		}
	}

	public void Enregistrer() throws SQLException, ConfigFileReaderException,
			IOException, NamingException {
		try {
		//	this.listAfterSaleCom.get(0).getAfterSaleReport().getProduct().getProductConf().getpro
			@SuppressWarnings("unused")
			NumCommandeForm _NumCommandeForm = new NumCommandeForm(
					this.listAfterSaleCom);
		} catch (NumCommandeException e) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "", e
							.getMessage()));
		}
		this.refresh();
	}
}