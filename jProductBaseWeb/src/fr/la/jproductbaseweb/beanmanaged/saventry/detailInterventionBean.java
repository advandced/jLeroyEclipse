package fr.la.jproductbaseweb.beanmanaged.saventry;

import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import fr.la.configfilereader.ConfigFileReaderException;
import fr.la.jproductbase.metier.AfterSaleReport;
import fr.la.jproductbase.service.ServiceInterface;


@ManagedBean(name="detailInterventionBean")
@ViewScoped
public class detailInterventionBean implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ServiceInterface moduleGlobal = new ServiceInterface();
	private int idIntervention;
	private List<AfterSaleReport> listAfterSaleReport;
	
	public int getIdIntervention() {
		return idIntervention;
	}

	public void setIdIntervention(int idIntervention) {
		this.idIntervention = idIntervention;
		try {
			this.listAfterSaleReport = moduleGlobal.getAfterSaleReports(this.idIntervention);
			System.out.println("Taille Liste : " + this.listAfterSaleReport.size() + " ID : " + this.idIntervention + " Liste : " + this.listAfterSaleReport.toString());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ConfigFileReaderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public List<AfterSaleReport> getListAfterSaleReport() {
		return listAfterSaleReport;
	}

	public void setListAfterSaleReport(List<AfterSaleReport> listAfterSaleReport) {
		this.listAfterSaleReport = listAfterSaleReport;
	}

}