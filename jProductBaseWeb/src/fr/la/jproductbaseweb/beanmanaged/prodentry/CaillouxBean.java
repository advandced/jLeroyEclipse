package fr.la.jproductbaseweb.beanmanaged.prodentry;

import fr.la.configfilereader.ConfigFileReaderException;
import fr.la.jproductbase.metier.AfterSaleCom;
import fr.la.jproductbase.service.ServiceInterface;
import fr.la.jproductbaseweb.beanmanaged.exception.CaillouxException;
import fr.la.jproductbaseweb.beanmanaged.modelForm.CaillouxForm;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;



@ManagedBean(name = "CaillouxBean")
@SessionScoped
public class CaillouxBean {

	private List<AfterSaleCom> listAfterSaleCom;

	private Date debut;

	private Date fin;

	private Boolean result = false;

	public CaillouxBean() {
	}

	public List<AfterSaleCom> getListAfterSaleCom() {
		return listAfterSaleCom;
	}

	public void setListAfterSaleCom(List<AfterSaleCom> listAfterSaleCom) {
		this.listAfterSaleCom = listAfterSaleCom;
	}

	public Date getDebut() {
		return debut;
	}

	public void setDebut(Date debut) {
		this.debut = debut;
	}

	public Date getFin() {
		return fin;
	}

	public void setFin(Date fin) {
		this.fin = fin;
	}

	public Boolean getResult() {
		return result;
	}

	public void setResult(Boolean result) {
		this.result = result;
	}

	public void recherche() throws SQLException, ConfigFileReaderException,
			IOException {
		CaillouxForm _form = new CaillouxForm(this.debut, this.fin);
		try {
			this.listAfterSaleCom = _form.search();
			this.result = true;
		} catch (CaillouxException e) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "", e
							.getMessage()));
			this.result = false;
		}
	}
}