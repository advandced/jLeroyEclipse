package fr.la.jproductbaseweb.beanmanaged.saventry;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import fr.la.jproductbase.metier.AfterSaleReport;
import fr.la.jproductbase.service.ServiceInterface;
import fr.la.jproductbaseweb.beanmanaged.modelForm.ValidControlForm;

@ManagedBean(name = "ValidControlBean")
@SessionScoped
public class ValidControlBean {

	private ServiceInterface moduleGlobal = ServiceInterface.getInstance();

	private List<AfterSaleReport> listAfterSaleReport;

	private AfterSaleReport[] selectedAfterSaleReport;

	private Boolean datafound;

	public ValidControlBean() {
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

	public void refresh() {
		this.listAfterSaleReport = moduleGlobal.getAfterSaleReportQualityControl();
		if (this.listAfterSaleReport.size() == 0) {
			this.datafound = false;
		} else {
			this.datafound = true;
		}
	}

	public void Enregistrer() {
		@SuppressWarnings("unused")
		ValidControlForm _ValidControlForm = new ValidControlForm(this.selectedAfterSaleReport);
		this.refresh();
	}
}