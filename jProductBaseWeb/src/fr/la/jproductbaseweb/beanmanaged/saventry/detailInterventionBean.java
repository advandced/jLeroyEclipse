package fr.la.jproductbaseweb.beanmanaged.saventry;

import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.joda.time.DateTime;
import org.joda.time.Weeks;
import org.primefaces.component.celleditor.CellEditor;
import org.primefaces.component.column.Column;
import org.primefaces.component.commandlink.CommandLink;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.component.fieldset.Fieldset;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.selectbooleancheckbox.SelectBooleanCheckbox;
import org.primefaces.component.selectonemenu.SelectOneMenu;
import org.primefaces.component.tabview.Tab;
import org.primefaces.component.tabview.TabView;
import org.primefaces.context.RequestContext;
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.TabChangeEvent;
import java.text.ParseException;

import fr.la.configfilereader.ConfigFileReaderException;
import fr.la.jproductbase.dao.AfterSaleReportDaoException;
import fr.la.jproductbase.dao.CustomerCommentDaoException;
import fr.la.jproductbase.dao.ElementChangedDaoException;
import fr.la.jproductbase.dao.FailureDaoException;
import fr.la.jproductbase.dao.FailureReportCommentDaoException;
import fr.la.jproductbase.dao.ProductDaoException;
import fr.la.jproductbase.metier.AfterSaleReport;
import fr.la.jproductbase.metier.ApparentCause;
import fr.la.jproductbase.metier.ApparentCauseCustomer;
import fr.la.jproductbase.metier.ElementChanged;
import fr.la.jproductbase.metier.Failure;
import fr.la.jproductbase.metier.JProductBaseException;
import fr.la.jproductbase.metier.Operator;
import fr.la.jproductbase.metier.Product;
import fr.la.jproductbase.metier.ProductConf;
import fr.la.jproductbase.service.FailureModuleException;
import fr.la.jproductbase.service.ServiceInterface;
import fr.la.jproductbaseweb.beanmanaged.exception.EntrySavException;
import fr.la.jproductbaseweb.beanmanaged.modelForm.EntrySavForm;

@ManagedBean(name = "detailInterventionBean")
@ViewScoped
public class detailInterventionBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private ServiceInterface moduleGlobal = new ServiceInterface();
	private boolean deleteCard = false;
	private int compteur;
	private int cptFailure;
	private int idIntervention;
	private int indexActive;
	private String elementChangedSelected;
	private String headerInterventon;
	private String nombreSemaineFonctionnel;
	private String topoRef;
	private AfterSaleReport afterSaleReport;
	private AfterSaleReport selectedAfterSaleReport;
	private ApparentCauseCustomer selectedApparentCauseCustomer;
	private Failure selectedFailure;
	private Product selectedObject;
	private Product productCardSelected;
	private SelectEvent selectedEvent;
	private List<AfterSaleReport> listAfterSaleReport;
	private List<ApparentCause> listLAICause;
	private List<ApparentCauseCustomer> listCustormerCause;
	private List<ElementChanged> listElementChangedToSave;
	private List<Failure> newListFailure;

	@PostConstruct
	public void initialisation() {
		try {
			this.listLAICause = moduleGlobal.getApparentCauses();
			this.listCustormerCause = moduleGlobal
					.getActiveApparentCausesCustomer();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

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

	private void newAfterSaleReport() {
		this.afterSaleReport = new AfterSaleReport(0, null, 1, null, "", "",
				null, null, null, null, null, 2, 2, "", "", "", "", null, null,
				this.selectedObject);
		this.afterSaleReport.setMajorIndexIn(this.selectedObject
				.getProductConf().getMajorIndex());

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

	public void modifyProduct(ActionEvent event) {
		System.out.println("modify");
		// CommandButton _commandButton = (CommandButton) event.getSource();
		this.selectedAfterSaleReport = (AfterSaleReport) event.getComponent()
				.getAttributes().get("selectedAfterSaleReport");
		System.out.println(this.selectedAfterSaleReport);
		System.out
				.println("selected aftersalereport" + selectedAfterSaleReport);
		// this.selectedAfterSaleReport = selectedAfterSaleReport;
		FacesContext context = FacesContext.getCurrentInstance();
		@SuppressWarnings("unused")
		InterventionSheetModel _intervention = (InterventionSheetModel) context
				.getApplication()
				.getExpressionFactory()
				.createValueExpression(context.getELContext(),
						"#{interventionSheetLinkModel}",
						InterventionSheetModel.class)
				.getValue(context.getELContext());
		System.out.println("path file name" + InterventionSheetModel.pathFile);
		this.selectedAfterSaleReport
				.setInterventionSheetLink(InterventionSheetModel.pathFile);
		int cptFailure = 0;
		if (this.selectedAfterSaleReport.getFailures() != null) {
			for (Failure failure : this.selectedAfterSaleReport.getFailures()) {
				System.out.println("id processing "
						+ failure.getProduct().getIdProduct());
				if (this.newListFailure != null) {
					for (Failure newFailure : this.newListFailure) {
						System.out.println("test verificcation"
								+ failure.getIdFailure() + " == "
								+ newFailure.getIdFailure());
						if (failure.getIdFailure() == newFailure.getIdFailure()
								&& failure.getProduct().getIdProduct() == newFailure
										.getProduct().getIdProduct()
								&& failure.getFailureCode().equals(
										newFailure.getFailureCode())) {

							// failure.setIdFailure(0);

							this.selectedAfterSaleReport.getFailures()
									.get(cptFailure).setIdFailure(0);

							System.out.println("format id " + failure);
						}

					}
				}
				Product _productSearched = null;
				EntrySavForm _entrySAV = null;
				try {
					_entrySAV = new EntrySavForm(
							this.selectedAfterSaleReport.getFailures()
									.get(cptFailure).getProduct()
									.getProductConf().getReference(),
							this.selectedAfterSaleReport.getFailures()
									.get(cptFailure).getProduct().getDatecode(),
							this.selectedAfterSaleReport.getFailures()
									.get(cptFailure).getProduct()
									.getSerialNumber(),
							this.selectedAfterSaleReport.getFailures()
									.get(cptFailure).getProduct()
									.getProductConf());
				} catch (EntrySavException e) {
					// TODO Auto-generated catch block
					FacesContext.getCurrentInstance().addMessage(
							null,
							new FacesMessage(FacesMessage.SEVERITY_WARN,
									"Erreur", e.getMessage()));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				_productSearched = _entrySAV.getProduct();
				this.selectedAfterSaleReport.getFailures().get(cptFailure)
						.setProduct(_productSearched);
				cptFailure++;
			}

		} else {

			this.selectedAfterSaleReport.setFailures(new ArrayList<Failure>());

		}

		try {
			@SuppressWarnings("unused")
			EntrySavForm _entrySAVForm = new EntrySavForm(
					this.selectedAfterSaleReport.getMajorIndexOut(),
					this.selectedAfterSaleReport.getRefCustomer(),
					this.selectedAfterSaleReport.getArrivalDate(),
					this.selectedAfterSaleReport.getAsker(),
					this.selectedAfterSaleReport.getEcsNumber(),
					this.selectedAfterSaleReport.getReparationDate(),
					this.selectedAfterSaleReport.getQualityControlDate(),
					this.selectedAfterSaleReport.getExpeditionDate(),
					this.selectedAfterSaleReport.getFunctionnalTest(),
					this.selectedAfterSaleReport.getVisualControl(),
					this.selectedAfterSaleReport.getComment(),
					this.selectedAfterSaleReport.getFailures(),
					this.selectedAfterSaleReport.getApparentCause(),
					this.selectedAfterSaleReport.getInterventionSheetLink());

			ProductConf _productConf = this.moduleGlobal.getProductConf(
					this.selectedObject.getProductConf().getReference(),
					this.selectedAfterSaleReport.getMajorIndexOut(), "");

			// ApparentCause _apparentCause = new ApparentCause();
			// _apparentCause.setIdApparentCause(Integer.parseInt(selectLAICause));
			if (_productConf == null) {
				System.out.println("productConf null");
				ProductConf _newProductConf = this.selectedObject
						.getProductConf();
				System.out.println(this.selectedAfterSaleReport
						.getMajorIndexOut());
				System.out.println("testetetet"
						+ this.selectedAfterSaleReport.getApparentCause());
				_productConf = this.moduleGlobal.setProductConf(null,
						_newProductConf.getReference(),
						this.selectedAfterSaleReport.getMajorIndexOut(), "",
						_newProductConf.getProductConfModel(),
						_newProductConf.getProductFamily(),
						_newProductConf.getProductSupply(),
						_newProductConf.isIdentifiable(),
						_newProductConf.getState(),
						_newProductConf.getFollowingForm(),
						_newProductConf.getProductConfComponents(),
						_newProductConf.getProductConfSoftwares());
				System.out.println("test");

			}

			// this.afterSaleReport.setMajorIndexOut(this.majorIndexOut);
			System.out.println("afterSaleReport set dataBase"
					+ this.getAfterSaleReport());
			this.getAfterSaleReport().setMajorIndexIn(
					this.selectedObject.getProductConf().getMajorIndex());
			this.getAfterSaleReport().setProduct(this.selectedObject);
			System.out.println("enregistrement de la conf " + _productConf);

			// this.afterSaleReport.setApparentCause(_apparentCause);
			this.selectedObject.setProductConf(_productConf);
			// this.moduleGlobale.setProduct(selectedObject);
			// this.afterSaleReport.setFailures(listFailure);
			System.out.println("value failures"
					+ this.selectedAfterSaleReport.getFailures());

			if (this.getAfterSaleReport().getAsker().isEmpty()) {

				this.getAfterSaleReport().setAsker("EES-FC");
			}
			System.out.println(this.getAfterSaleReport());

			AfterSaleReport _newAfterSaleReport = this.moduleGlobal
					.setAfterSaleReport(this.selectedAfterSaleReport);

			this.getAfterSaleReport().setIdAfterSaleReport(
					_newAfterSaleReport.getIdAfterSaleReport());

			this.selectedAfterSaleReport = _newAfterSaleReport;
			if (this.newListFailure == null) {
				this.newListFailure = new ArrayList<>();
			}
			this.newListFailure.clear();
			System.out.println(this.getAfterSaleReport());

			context = FacesContext.getCurrentInstance();

			context.addMessage(null, new FacesMessage("Modification Reussie"));
			this.refresh();
			// hideDialog(dialog);

		} catch (EntrySavException e1) {
			// TODO Auto-generated catch block
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Erreur", e1
							.getMessage()));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FailureModuleException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (AfterSaleReportDaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FailureDaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ElementChangedDaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FailureReportCommentDaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CustomerCommentDaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ConfigFileReaderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JProductBaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ProductDaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// System.out.println(this.selectedObject.getProductConf().getReference()
		// +" / "+this.selectedAfterSaleReport.getMajorIndexOut());

	}

	public void handleCauseClientChange() {
		if (this.getSelectedApparentCauseCustomer() == null) {
			try {
				this.listLAICause = this.moduleGlobal.getApparentCauses();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			List<ApparentCause> _apparentCauses = new ArrayList<ApparentCause>();
			this.listLAICause = new ArrayList<ApparentCause>();
			try {
				_apparentCauses = this.moduleGlobal.getApparentCauses();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			for (ApparentCause apparentCause : _apparentCauses) {
				if (apparentCause.getApparentCauseCustomer()
						.getIdApparentCauseCustomer() == this
						.getSelectedApparentCauseCustomer()
						.getIdApparentCauseCustomer()) {
					this.listLAICause.add(apparentCause);
				}
			}
		}
	}

	public void newFailure(String cardChanged) {

		this.cptFailure++;
		System.out.println("valeur de cptFailure" + cptFailure);
		if (cardChanged.equals("false")) {
			System.out.println("action" + cardChanged);
			Product _product = new Product();
			_product.setProductConf(new ProductConf());
			List<ElementChanged> _listElements = new ArrayList<ElementChanged>();

			this.listElementChangedToSave = new ArrayList<ElementChanged>();
			Failure _failure = new Failure(this.cptFailure, new Date(), "", "",
					"", "", "", new Operator(), _product, _listElements, false);
			List<Failure> _failures = new ArrayList<Failure>();

			if (this.selectedAfterSaleReport.getIdAfterSaleReport() == 0
					&& this.selectedAfterSaleReport.getFailures() == null) {

				System.out.println("create failure list");
				this.selectedAfterSaleReport.setFailures(_failures);

			}

			System.out.println("rajout de la ligne failure dans la liste : "
					+ _failure);

			this.selectedAfterSaleReport.getFailures().add(_failure);
			addFailureinList(_failure);
		} else {

			List<ElementChanged> _listElements = new ArrayList<ElementChanged>();

			this.listElementChangedToSave = new ArrayList<ElementChanged>();

			Product _product = new Product();
			_product.setProductConf(new ProductConf());

			Failure _failure = new Failure(this.cptFailure, new Date(), "", "",
					"", "", "", new Operator(), _product, _listElements, false,
					this.selectedFailure);

			System.out.println("creation new faiurel " + _failure);

			List<Failure> _failures = new ArrayList<Failure>();

			if (this.selectedAfterSaleReport.getIdAfterSaleReport() == 0
					&& this.selectedAfterSaleReport.getFailures() == null) {

				System.out.println("create failure list");
				this.selectedAfterSaleReport.setFailures(_failures);

			}
			System.out.println("rajout de la ligne failure dans la liste : "
					+ _failure);
			this.selectedAfterSaleReport.getFailures().add(_failure);

			System.out.println("selected aftersale report List failure :"
					+ this.selectedAfterSaleReport.getFailures());

			addFailureinList(_failure);

		}

		/*
		 * Product _product = new Product();
		 * 
		 * List<ElementChanged> _listElements = new ArrayList<ElementChanged>();
		 * 
		 * this.listElementChangedToSave = new ArrayList<ElementChanged>();
		 * Failure _failure = new Failure(0, new Date(), "", "", "", "", "",
		 * null, _product, _listElements, false); List<Failure> _failures = new
		 * ArrayList<Failure>(); this.listFailure.add(_failure);
		 * System.out.println(this.selectedAfterSaleReport); if
		 * (this.selectedAfterSaleReport.getIdAfterSaleReport() == 0 &&
		 * this.selectedAfterSaleReport.getFailures() == null) {
		 * 
		 * System.out.println("create failure list");
		 * this.selectedAfterSaleReport.setFailures(_failures);
		 * 
		 * } this.selectedAfterSaleReport.getFailures().add(_failure);
		 */

	}

	private void addFailureinList(Failure _failure) {
		System.out.println("add failure in List");
		if (this.newListFailure == null) {
			this.newListFailure = new ArrayList<Failure>();
		}
		this.newListFailure.add(_failure);

	}

	public void selectedFailure(SelectEvent event) {

		this.selectedEvent = event;

	}

	/*
	 * Methode appelle lors de la mise a jour d'une ligne pour créer une Failure
	 */
	public void editCardRow(RowEditEvent evt) {
		Failure _failure = this.selectedFailure;
		System.out.println("test" + this.selectedFailure);
		// System.out.println(evt.getSource());
		DataTable _dataTable = new DataTable();

		boolean createFailure = false;
		// Failure _failure = (Failure) evt.getObject();
		System.out.println(_failure.getElementsChanged());
		@SuppressWarnings("unused")
		Failure _oldFailure = null;
		if (_failure.getElementsChanged() != null) {
			if (_failure.getElementsChanged().size() != 0) {
				listElementChangedToSave.addAll(_failure.getElementsChanged());
			}
		}
		_failure.setElementsChanged(listElementChangedToSave);

		_dataTable = (DataTable) evt.getSource();

		for (Column col : _dataTable.getColumns()) {

			if (col.getHeaderText().equals("Diagnostic et analyse")) {
				for (UIComponent compo : col.getChildren()) {
					System.out.println(compo instanceof CellEditor);
					if (compo instanceof CellEditor) {
						CellEditor _cellEditor = (CellEditor) compo;
						System.out.println(_cellEditor.getFacet("input"));
						if (_cellEditor.getFacet("input") instanceof SelectOneMenu) {
							SelectOneMenu _html = (SelectOneMenu) _cellEditor
									.getFacet("input");
							try {
								this.productCardSelected = this.moduleGlobal
										.getProduct(Integer.parseInt(_html
												.getValue().toString()));

								if (this.productCardSelected != null) {
									if (_failure.getProduct().getDatecode()
											.isEmpty()
											|| _failure.getProduct()
													.getSerialNumber()
													.isEmpty()) {

										_failure.getProduct().setProductConf(
												productCardSelected
														.getProductConf());
										_failure.getProduct().setDatecode(
												productCardSelected
														.getDatecode());
										_failure.getProduct().setSerialNumber(
												productCardSelected
														.getSerialNumber());

									} else {
										// todo ne rien faire
									}
								} else {
								}
								System.out.println(_failure.getProduct()
										.getDatecode());

							} catch (NumberFormatException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							this.listElementChangedToSave = new ArrayList<ElementChanged>();
							// System.out.println(this.productCardSelected);
							System.out.println(_failure);

						}
					}
				}
			}

			if (col.getHeaderText().equals("Date Code")) {
				for (UIComponent compo : col.getChildren()) {
					System.out.println(compo instanceof CellEditor);
					if (compo instanceof CellEditor) {
						CellEditor _cellEditor = (CellEditor) compo;
						System.out.println(_cellEditor.getFacet("input"));
						if (_cellEditor.getFacet("input") instanceof InputText) {
							InputText _html = (InputText) _cellEditor
									.getFacet("input");
							if (this.productCardSelected != null) {
								try {

									_failure.getProduct().setDatecode(
											_html.getValue().toString());

								} catch (NumberFormatException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
							this.listElementChangedToSave = new ArrayList<ElementChanged>();

						}
					}
				}

			}

			if (col.getHeaderText().equals("N Serie")) {
				for (UIComponent compo : col.getChildren()) {
					System.out.println(compo instanceof CellEditor);
					if (compo instanceof CellEditor) {
						CellEditor _cellEditor = (CellEditor) compo;
						System.out.println(_cellEditor.getFacet("input"));
						if (_cellEditor.getFacet("input") instanceof InputText) {
							InputText _html = (InputText) _cellEditor
									.getFacet("input");
							if (this.productCardSelected != null) {
								try {

									_failure.getProduct().setSerialNumber(
											_html.getValue().toString());

								} catch (NumberFormatException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}

						}
					}
				}

			}

			if (col.getHeaderText().equals("Supprimer")) {
				System.out.println("Colonne supprimer");
				for (UIComponent compo : col.getChildren()) {
					System.out.println(compo instanceof CellEditor);
					if (compo instanceof CellEditor) {
						CellEditor _cellEditor = (CellEditor) compo;
						System.out.println(_cellEditor.getFacet("input"));
						if (_cellEditor.getFacet("input") instanceof SelectBooleanCheckbox) {
							SelectBooleanCheckbox _selectBoolean = (SelectBooleanCheckbox) _cellEditor
									.getFacet("input");
							if (_selectBoolean.getValue().toString()
									.equals("true")) {

								System.out.println(this.selectedFailure
										.getIdFailure());
								int cpt = 0;
								for (Failure failure : this.selectedAfterSaleReport
										.getFailures()) {

									if (failure.getIdFailure() == this.selectedFailure
											.getIdFailure()) {
										System.out
												.println("suppression de "
														+ this.selectedAfterSaleReport
																.getFailures()
																.get(cpt));
										this.selectedAfterSaleReport
												.getFailures().remove(cpt);
										this.setDeleteCard(false);
										break;
									}

									cpt++;
								}
								// System.out.println("supprimer " +
								// this.selectedFailure.getIdFailure());

							}

						}
					}
				}

			}

			if (col.getHeaderText().equals("Carte remplacee")) {

				// on reva récuperer la valeur de la case coché de la ligne
				// modifiée
				for (UIComponent compo : col.getChildren()) {
					System.out.println(compo instanceof CellEditor);
					if (compo instanceof CellEditor) {
						CellEditor _cellEditor = (CellEditor) compo;

						if (_cellEditor.getFacet("input") instanceof SelectBooleanCheckbox) {

							SelectBooleanCheckbox _selectBoolean = (SelectBooleanCheckbox) _cellEditor
									.getFacet("input");
							// si l'utilisateur a coché carte remplacée
							if (_selectBoolean.getValue().toString()
									.equals("true")) {
								System.out.println("carte remplacee cochée");
								@SuppressWarnings("unused")
								FacesContext facesContext = FacesContext
										.getCurrentInstance();

								if (this.selectedAfterSaleReport.getFailures()
										.size() != 1) {

									for (Failure failure : this.selectedAfterSaleReport
											.getFailures()) {

										if (failure.getNewFailureCardChanged() != null) {

											if (failure
													.getNewFailureCardChanged()
													.getIdFailure() == this.selectedFailure
													.getIdFailure()) {
												createFailure = false;
												break;
											} else {
												createFailure = true;
												_oldFailure = _failure;
											}
										} else {
											createFailure = true;
											_oldFailure = _failure;
										}
									}

								} else {
									// premiere insertion dans la liste
									createFailure = true;
									_oldFailure = _failure;

								}

								if (createFailure == true) {
									// System.out.println("del "+del);
									System.out.println("changement carte "
											+ _failure);
									System.out.println("list failure"
											+ this.selectedAfterSaleReport
													.getFailures());

									System.out
											.println("enregistrement de la failure"
													+ _failure);
									// insertin de l'id = 0 pour la failure pour
									// indiquer a la couche DAO que c'est une
									// nouvelle Failure

									/*
									 * this.selectedFailure = this.moduleGlobale
									 * .addFailureDismantedCard(_failure,
									 * this.selectedObject);
									 */
									// this.selectedFailure = _failure;
									System.out
											.println(this.selectedAfterSaleReport
													.getFailures());

									newFailure("true");

									RequestContext request = RequestContext
											.getCurrentInstance();

									// on effectue un click en js pour
									// raffraichir le tableau

									request.execute("document.getElementById('formDial:tabViewAfterSaleReport:"
											+ this.indexActive
											+ ":refreshFailure').click()");
									createFailure = false;

								}

							}
						}
					}
				}
			}
		}

		// searching product in failure
		Product _productSearching = null;

		try {
			EntrySavForm _entrySAV = new EntrySavForm(_failure.getProduct()
					.getProductConf().getReference(), _failure.getProduct()
					.getDatecode(), _failure.getProduct().getSerialNumber(),
					_failure.getProduct().getProductConf());
			_productSearching = _entrySAV.getProduct();

			_failure.setProduct(_productSearching);

			/*
			 * _failure.setProduct(new Product(_failure.getProduct()
			 * .getProductConf(), _failure.getProduct().getDatecode(),
			 * _failure.getProduct().getSerialNumber(), _failure
			 * .getProduct().getProviderCode()));
			 */

			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("Produit trouvé" + " "
					+ _productSearching.getIdProduct()));

		} catch (EntrySavException e) {

			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Information",
							e.getMessage()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void editRow(ActionEvent event) {

		this.selectedFailure = (Failure) event.getComponent().getAttributes()
				.get("selectedFailure");
		CommandLink _commandLink = (CommandLink) event.getSource();
		Column _column = (Column) _commandLink.getParent();
		DataTable _dataTable = (DataTable) _column.getParent();

		for (Column column : _dataTable.getColumns()) {

			if (column.getHeaderText().equals("Date Code")) {
				for (UIComponent compo : column.getChildren()) {
					System.out.println(compo instanceof CellEditor);
					if (compo instanceof CellEditor) {
						CellEditor _cellEditor = (CellEditor) compo;
						System.out.println(_cellEditor.getFacet("input"));
						if (_cellEditor.getFacet("input") instanceof InputText) {
							InputText _html = (InputText) _cellEditor
									.getFacet("input");
							System.out.println("valeur de l'input "
									+ this.selectedFailure
											.getNewFailureCardChanged());
							if (this.selectedFailure.getNewFailureCardChanged() == null) {
								System.out.println("set a null");
								_html.setDisabled(true);

							} else {
								_html.setDisabled(false);
							}
						}
					}
				}
			}

			if (column.getHeaderText().equals("N Serie")) {
				for (UIComponent compo : column.getChildren()) {
					System.out.println(compo instanceof CellEditor);
					if (compo instanceof CellEditor) {
						CellEditor _cellEditor = (CellEditor) compo;
						System.out.println(_cellEditor.getFacet("input"));
						if (_cellEditor.getFacet("input") instanceof InputText) {
							InputText _html = (InputText) _cellEditor
									.getFacet("input");
							if (this.selectedFailure.getNewFailureCardChanged() == null) {
								System.out.println("set a null");
								_html.setDisabled(true);
							} else {
								_html.setDisabled(false);
							}
						}
					}
				}

			}
		}
		System.out.println(_column.getParent());

		System.out.println("testtt click" + selectedFailure);

	}

	public void selectedProductFailure() {
		System.out.println("selected produtct"
				+ this.selectedFailure.getProduct().getIdProduct());
		Product _product = null;

		try {
			_product = this.moduleGlobal.getProduct(this.selectedFailure
					.getProduct().getIdProduct());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		this.selectedFailure.setProduct(_product);

	}

	public void addTopo() {

		try {
			@SuppressWarnings("unused")
			EntrySavForm _entrySavFORM = new EntrySavForm(this.getTopoRef());
			this.selectedFailure.getElementsChanged().add(
					new ElementChanged(this.getTopoRef()));
		} catch (EntrySavException e) {
			// TODO Auto-generated catch block

			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_FATAL, "Erreur", e
							.getMessage()));
			System.out.println("warning null" + e.getMessage());
			;
		}

		// this.listElementChanged.add(this.topoRef);

		// this.listElementChangedToSave.add(new ElementChanged(this.topoRef));

	}

	public void delTopo() {

		Failure _failure = (Failure) this.selectedEvent.getObject();

		System.out.println("delete Topo" + this.getElementChangedSelected());
		int i = 0;

		for (ElementChanged elementChangedSelected : _failure
				.getElementsChanged()) {
			System.out.println(elementChangedSelected.getCode() + " = "
					+ this.getElementChangedSelected());
			if (elementChangedSelected.getCode().equals(
					this.getElementChangedSelected())) {

				_failure.getElementsChanged().remove(i);
				break;
			}

			i++;

		}

	}

	public void refresh(){
		try {
			this.selectedObject = moduleGlobal.getProduct(this.idIntervention);
			this.listAfterSaleReport = moduleGlobal
					.getAfterSaleReports(this.idIntervention);
			this.newAfterSaleReport();
			this.listAfterSaleReport.add(this.afterSaleReport);
			this.compteur = this.listAfterSaleReport.size();
		} catch (SQLException | ConfigFileReaderException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public int getIdIntervention() {
		return idIntervention;
	}

	public void setIdIntervention(int idIntervention) {
		this.idIntervention = idIntervention;
		this.refresh();
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

	public List<ApparentCause> getListLAICause() {
		return listLAICause;
	}

	public void setListLAICause(List<ApparentCause> listLAICause) {
		this.listLAICause = listLAICause;
	}

	public List<ApparentCauseCustomer> getListCustormerCause() {
		return listCustormerCause;
	}

	public void setListCustormerCause(
			List<ApparentCauseCustomer> listCustormerCause) {
		this.listCustormerCause = listCustormerCause;
	}

	public ApparentCauseCustomer getSelectedApparentCauseCustomer() {
		return selectedApparentCauseCustomer;
	}

	public void setSelectedApparentCauseCustomer(
			ApparentCauseCustomer selectedApparentCauseCustomer) {
		this.selectedApparentCauseCustomer = selectedApparentCauseCustomer;
	}

	public boolean isDeleteCard() {
		return deleteCard;
	}

	public void setDeleteCard(boolean deleteCard) {
		this.deleteCard = deleteCard;
	}

	public String getElementChangedSelected() {
		return elementChangedSelected;
	}

	public void setElementChangedSelected(String elementChangedSelected) {
		this.elementChangedSelected = elementChangedSelected;
	}

	public Product getSelectedObject() {
		return selectedObject;
	}

	public void setSelectedObject(Product selectedObject) {
		this.selectedObject = selectedObject;
	}

	public String getTopoRef() {
		return topoRef;
	}

	public void setTopoRef(String topoRef) {
		this.topoRef = topoRef;
	}

	public AfterSaleReport getAfterSaleReport() {
		return afterSaleReport;
	}

	public void setAfterSaleReport(AfterSaleReport afterSaleReport) {
		this.afterSaleReport = afterSaleReport;
	}

}