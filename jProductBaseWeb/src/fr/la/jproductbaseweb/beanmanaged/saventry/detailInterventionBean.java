package fr.la.jproductbaseweb.beanmanaged.saventry;

import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;

import org.joda.time.DateTime;
import org.joda.time.Weeks;
import org.primefaces.component.fieldset.Fieldset;
import org.primefaces.component.tabview.Tab;
import org.primefaces.component.tabview.TabView;
import org.primefaces.event.TabChangeEvent;

import fr.la.configfilereader.ConfigFileReaderException;
import fr.la.jproductbase.metier.AfterSaleReport;
import fr.la.jproductbase.service.ServiceInterface;

@ManagedBean(name = "detailInterventionBean")
@ViewScoped
public class detailInterventionBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private ServiceInterface moduleGlobal = new ServiceInterface();
	private int idIntervention;
	private List<AfterSaleReport> listAfterSaleReport;
	private AfterSaleReport selectedAfterSaleReport;
	private int indexActive;
	private String headerInterventon;
	private int compteur;
	private int nombreRetourSAV = 1;
	private String nombreSemaineFonctionnel;

	public void onChangeTab(TabChangeEvent event) {
		TabView _tabView = (TabView) event.getSource();
		Tab _currentTab = (Tab) event.getTab();
		// AfterSaleReport _afterSaleReport = (AfterSaleReport)
		// event.getComponent().getAttributes().get("selectedAfterSaleReport");
		for (UIComponent component : _currentTab.getChildren()) {
			if (component instanceof Fieldset) {
				if (this.listAfterSaleReport.get(indexActive)
						.getIdAfterSaleReport() != 0) {

				}
			}
		}
		this.indexActive = _tabView.getActiveIndex();
		this.selectedAfterSaleReport = this.listAfterSaleReport
				.get(indexActive);
		if (this.selectedAfterSaleReport.getArrivalDate() == null) {
			System.out.println("date d'arrivée null");
			this.selectedAfterSaleReport.setAsker("EES-FC");
		}
	}

	public String calculateweek() {
		if (this.listAfterSaleReport.get(this.indexActive).getProduct() != null) {
			if (this.indexActive == 0) {
				String datecode = this.listAfterSaleReport
						.get(this.indexActive).getProduct().getDatecode();

				Date date = this.getdatefromdatecode(datecode);

				int weeks = 0;

				DateTime dateTime1 = new DateTime(date);
				DateTime dateTime2 = null;

				if (this.listAfterSaleReport.get(this.indexActive)
						.getArrivalDate() == null) {
					dateTime2 = new DateTime(new Date());
				} else {
					dateTime2 = new DateTime(this.listAfterSaleReport.get(
							this.indexActive).getArrivalDate());
				}

				weeks = this.numberofweekbetween(dateTime1, dateTime2);

				return String.valueOf(weeks);

			} else {

				int weeks = 0;

				DateTime dateExped = new DateTime(this.listAfterSaleReport.get(
						this.indexActive - 1).getExpeditionDate());

				DateTime dateTime2 = null;

				if (this.listAfterSaleReport.get(this.indexActive)
						.getArrivalDate() == null) {
					dateTime2 = new DateTime(new Date());
				} else {
					dateTime2 = new DateTime(this.listAfterSaleReport.get(
							this.indexActive).getArrivalDate());
				}

				weeks = this.numberofweekbetween(dateExped, dateTime2);

				return String.valueOf(weeks);
			}
		} else {
			return "erreur";
		}
	}

	public int numberofweekbetween(DateTime date1, DateTime date2) {

		DateTime dateTime1 = new DateTime(date1);
		DateTime dateTime2 = new DateTime(date2);

		int weeks = Weeks.weeksBetween(dateTime1, dateTime2).getWeeks();

		return weeks;
	}

	public Date getdatefromdatecode(String datecode) {

		char[] stringArray;

		stringArray = datecode.toCharArray();
		if (stringArray.length == 4) {
			String semaine = Character.toString(stringArray[2])
					+ Character.toString(stringArray[3]);
			String suffix = "";
			if (stringArray[0] == 9) {
				suffix = "19";
			} else {
				suffix = "20";
			}
			String annee = suffix + Character.toString(stringArray[0])
					+ Character.toString(stringArray[1]);
			// We know week number and year.
			int week = Integer.parseInt(semaine);
			int year = Integer.parseInt(annee);

			// Get calendar, clear it and set week number and year.
			Calendar calendar = Calendar.getInstance();
			calendar.clear();
			calendar.set(Calendar.WEEK_OF_YEAR, week);
			calendar.set(Calendar.YEAR, year);

			// Now get the first day of week.
			Date date = calendar.getTime();

			return date;
		} else {
			return null;
		}
	}

	public int getIdIntervention() {
		return idIntervention;
	}

	public void setIdIntervention(int idIntervention) {
		this.idIntervention = idIntervention;
		try {
			this.listAfterSaleReport = moduleGlobal
					.getAfterSaleReports(this.idIntervention);
			this.listAfterSaleReport.add(new AfterSaleReport(0,
					this.moduleGlobal.getProduct(this.idIntervention)));
			this.compteur = this.listAfterSaleReport.size();
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

	public int getIndexActive() {
		return indexActive;
	}

	public void setIndexActive(int indexActive) {
		this.indexActive = indexActive;
	}

	public String getHeaderInterventon() {
		if (this.compteur != 1) {
			this.headerInterventon = "Intervention "
					+ (this.listAfterSaleReport.size() - (this.compteur - 1));
			this.compteur--;
		} else {
			this.headerInterventon = "Nouvelle Intervention";
		}
		return headerInterventon;
	}

	public void setHeaderInterventon(String headerInterventon) {
		this.headerInterventon = headerInterventon;
	}

	public int getCompteur() {
		return compteur;
	}

	public void setCompteur(int compteur) {
		this.compteur = compteur;
	}

	public int getNombreRetourSAV() {
		return nombreRetourSAV;
	}

	public void setNombreRetourSAV(int nombreRetourSAV) {
		this.nombreRetourSAV = nombreRetourSAV;
	}

	public String getNombreSemaineFonctionnel() {
		this.nombreSemaineFonctionnel = this.calculateweek();
		return nombreSemaineFonctionnel;
	}

	public void setNombreSemaineFonctionnel(String nombreSemaineFonctionnel) {
		this.nombreSemaineFonctionnel = nombreSemaineFonctionnel;
	}

	public AfterSaleReport getSelectedAfterSaleReport() {
		return selectedAfterSaleReport;
	}

	public void setSelectedAfterSaleReport(
			AfterSaleReport selectedAfterSaleReport) {
		this.selectedAfterSaleReport = selectedAfterSaleReport;
	}

}