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
import fr.la.jproductbase.dao.AfterSaleReportDaoException;
import fr.la.jproductbase.dao.ElementChangedDaoException;
import fr.la.jproductbase.dao.FailureDaoException;
import fr.la.jproductbase.metier.AfterSaleReport;
import fr.la.jproductbase.service.ServiceInterface;
import fr.la.jproductbaseweb.beanmanaged.exception.ValidControlException;
import fr.la.jproductbaseweb.beanmanaged.modelForm.ValidControlForm;

@ManagedBean(name = "ValidControlBean")
@SessionScoped
public class ValidControlBean {

	private ServiceInterface moduleGlobal = new ServiceInterface();

	private List<AfterSaleReport> listAfterSaleReport;

	private AfterSaleReport[] selectedAfterSaleReport;

	private Boolean datafound;

	public ValidControlBean() throws SQLException, ConfigFileReaderException,
			IOException, AfterSaleReportDaoException {
		this.refresh();
	}

	public List<AfterSaleReport> getListAfterSaleReport() {
		return listAfterSaleReport;
	}

	public void setListAfterSaleReport(List<AfterSaleReport> listAfterSaleReport) {
		this.listAfterSaleReport = listAfterSaleReport;
	}

	public AfterSaleReport[] getSelectedAfterSaleReport() {
		return selectedAfterSaleReport;
	}

	public void setSelectedAfterSaleReport(
			AfterSaleReport[] selectedAfterSaleReport) {
		this.selectedAfterSaleReport = selectedAfterSaleReport;
	}

	public Boolean getDatafound() {
		return datafound;
	}

	public void setDatafound(Boolean datafound) {
		this.datafound = datafound;
	}

	public void refresh() throws SQLException, ConfigFileReaderException,
			IOException, AfterSaleReportDaoException {
		this.listAfterSaleReport = moduleGlobal
				.getAfterSaleReportQualityControl();
		if (this.listAfterSaleReport.size() == 0) {
			this.datafound = false;
		} else {
			this.datafound = true;
		}
	}

	public void Enregistrer() throws SQLException, AfterSaleReportDaoException,
			FailureDaoException, ElementChangedDaoException,
			ValidControlException, ConfigFileReaderException, IOException, NamingException {
		try {
			@SuppressWarnings("unused")
			ValidControlForm _ValidControlForm = new ValidControlForm(
					this.selectedAfterSaleReport);
		} catch (ValidControlException e) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "", e
							.getMessage()));
		}
		this.refresh();
	}
}