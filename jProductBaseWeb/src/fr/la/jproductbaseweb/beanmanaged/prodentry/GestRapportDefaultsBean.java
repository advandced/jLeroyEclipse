package fr.la.jproductbaseweb.beanmanaged.prodentry;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;
import javax.naming.NamingException;

import org.primefaces.component.celleditor.CellEditor;
import org.primefaces.component.column.Column;
import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.component.dialog.Dialog;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.inputtextarea.InputTextarea;
import org.primefaces.component.selectbooleancheckbox.SelectBooleanCheckbox;
import org.primefaces.component.selectonemenu.SelectOneMenu;
import org.primefaces.context.RequestContext;
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;

import fr.la.configfilereader.ConfigFileReaderException;
import fr.la.jproductbase.dao.ProductDaoException;
import fr.la.jproductbase.dao.TesterReportDaoException;
import fr.la.jproductbase.metier.CustomerComment;
import fr.la.jproductbase.metier.ElementChanged;
import fr.la.jproductbase.metier.Failure;
import fr.la.jproductbase.metier.FailureReportComment;
import fr.la.jproductbase.metier.JProductBaseException;
import fr.la.jproductbase.metier.Operator;
import fr.la.jproductbase.metier.Product;
import fr.la.jproductbase.metier.ProductConf;
import fr.la.jproductbase.metier.ProductionFailureReport;
import fr.la.jproductbase.service.FailureModuleException;
import fr.la.jproductbaseweb.beanmanaged.exception.ApparentCauseException;
import fr.la.jproductbaseweb.beanmanaged.exception.EntryDefaultRapportException;
import fr.la.jproductbaseweb.beanmanaged.exception.GestRapportDefaultsException;
import fr.la.jproductbaseweb.beanmanaged.modelForm.EntryDefaultRapportForm;

@ManagedBean(name = "gestRapportDefaults")
@SessionScoped
public class GestRapportDefaultsBean extends
		GestFormSearchAbstract<ProductionFailureReport> {

	private List<Failure> listFailure;
	private List<ElementChanged> listElementChangedToSave;
	private List<Failure> newListFailure;
	private List<Operator> operatorList;

	private Failure selectedFailure;
	private SelectEvent selectedEvent;
	private Product productCardSelected;
	private Operator operatorSelected;

	private Date dateStart;
	private Date dateEnd;

	private String topoRef;
	private String elementChangedSelected;
	private String action;

	private int cptFailure;

	private Boolean search = false;

	public GestRapportDefaultsBean() {
		super();

		this.pathLoadingPage = this.context.getViewRoot().getViewId();

		selectedObject = new ProductionFailureReport();
		this.cptFailure = 0;
		this.newListFailure = new ArrayList<Failure>();
		this.operatorList = new ArrayList<Operator>();
		this.listElementChangedToSave = new ArrayList<ElementChanged>();
		this.topoRef = null;
		this.dateStart = new Date();
		this.dateEnd = new Date();
		Calendar _dateEnd = GregorianCalendar.getInstance();
		_dateEnd.add(Calendar.MONTH, +1);
		this.dateEnd = _dateEnd.getTime();
		DateFormat _formatDate = new SimpleDateFormat("yyyy-MM-dd");
		_formatDate.format(this.dateStart);
		_formatDate.format(this.dateEnd);
		System.out.println("98" + this.dateEnd);

		try {
			this.productObjectList = this.moduleGlobale
					.getUnclosedProductionFailureReports();
			this.operatorList = this.moduleGlobale.getOperators();
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

		this.selectedObject = new ProductionFailureReport();
		Product _product = new Product();
		_product.setProductConf(new ProductConf());
		this.selectedObject.setProduct(_product);

	}

	public void newFailure(String cardChanged) {
		this.cptFailure++;

		if (cardChanged.equals("false")) {
			System.out.println("126 action" + cardChanged);
			Product _product = new Product();
			_product.setProductConf(new ProductConf());
			List<ElementChanged> _listElements = new ArrayList<ElementChanged>();

			this.listElementChangedToSave = new ArrayList<ElementChanged>();
			Failure _failure = new Failure(this.cptFailure, new Date(), "", "",
					"", "", "", new Operator(), _product, _listElements, false);
			List<Failure> _failures = new ArrayList<Failure>();

			if (this.selectedObject.getIdProductionFailureReport() == 0
					&& this.selectedObject.getFailures() == null) {

				System.out.println("139 create failure list");
				this.selectedObject.setFailures(_failures);

			}

			System.out.println("144 rajout de la ligne failure dans la liste");

			this.selectedObject.getFailures().add(_failure);
			addFailureinList(_failure);
		} else {

			List<ElementChanged> _listElements = new ArrayList<ElementChanged>();

			this.listElementChangedToSave = new ArrayList<ElementChanged>();

			Failure _failure = new Failure(this.cptFailure, new Date(), "", "",
					"", "", "", new Operator(),
					this.selectedFailure.getProduct(), _listElements, false,
					this.selectedFailure);

			List<Failure> _failures = new ArrayList<Failure>();

			if (this.selectedObject.getIdProductionFailureReport() == 0
					&& this.selectedObject.getFailures() == null) {

				System.out.println("164 create failure list");
				this.selectedObject.setFailures(_failures);

			}
			System.out.println("168 rajout de la ligne failure dans la liste");
			this.selectedObject.getFailures().add(_failure);

			addFailureinList(_failure);

		}
	}

	public void allDefauts() {
		try {
			this.productObjectList = this.moduleGlobale
					.getProductionFailureReports(dateStart, dateEnd);
			if (productObjectList.size() == 0) {
				throw new GestRapportDefaultsException("Aucun resultat");
			} else {
				this.search = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ConfigFileReaderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (GestRapportDefaultsException e) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "", e
							.getMessage()));
			this.search = false;
		}

	}

	public void editCardRow(RowEditEvent evt) {

		System.out.println("206 " + evt.getSource());
		DataTable _dataTable = new DataTable();
		this.productCardSelected = new Product();
		Failure _failure = (Failure) evt.getObject();
		@SuppressWarnings("unused")
		Failure _oldFailure = null;
		boolean createFailure = false;
		System.out.println("213 " + _failure.getElementsChanged());
		if (_failure.getElementsChanged() != null) {
			if (_failure.getElementsChanged().size() != 0) {
				System.out.println("216 _failure.getElementsChanged()"
						+ _failure.getElementsChanged());
				listElementChangedToSave.addAll(_failure.getElementsChanged());
			}
		}
		_failure.setElementsChanged(listElementChangedToSave);

		_dataTable = (DataTable) evt.getSource();
		_dataTable.getColumns();
		for (Column col : _dataTable.getColumns()) {

			// on verifie qu'on se trouve sur la colonne Carte remplacee
			if (col.getHeaderText().equals("Carte Remplacee")) {

				// on reva récuperer la valeur de la case coché de la ligne
				// modifiée
				for (UIComponent compo : col.getChildren()) {
					System.out.println("233 " + (compo instanceof CellEditor));
					if (compo instanceof CellEditor) {
						CellEditor _cellEditor = (CellEditor) compo;

						if (_cellEditor.getFacet("input") instanceof SelectBooleanCheckbox) {

							SelectBooleanCheckbox _selectBoolean = (SelectBooleanCheckbox) _cellEditor
									.getFacet("input");
							// si l'utilisateur à coché carte remplacée
							if (_selectBoolean.getValue().toString()
									.equals("true")) {
								System.out
										.println("244 carte remplacee cochée");
								@SuppressWarnings("unused")
								FacesContext facesContext = FacesContext
										.getCurrentInstance();

								if (this.selectedObject.getFailures().size() != 1) {

									for (Failure failure : this.selectedObject
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
									System.out.println("281 changement carte "
											+ _failure);
									System.out.println("283 list failure"
											+ this.selectedObject

											.getFailures());

									System.out
											.println("289 enregistrement de la failure"
													+ _failure);
									// insertin de l'id = 0 pour la failure pour
									// indiquer a la couche DAO que c'est une
									// nouvelle Failure

									/*
									 * this.selectedFailure = this.moduleGlobale
									 * .addFailureDismantedCard(_failure,
									 * this.selectedObject);
									 */
									this.selectedFailure = _failure;
									System.out
											.println("301 "
													+ this.selectedObject
															.getFailures());

									newFailure("true");

									RequestContext request = RequestContext
											.getCurrentInstance();

									// on effectue un click en js pour
									// raffraichir le tableau

									request.execute("document.getElementById('formDial:refreshFailure').click()");
									createFailure = false;

								}

							}
						}
					}

				}

			}

			if (col.getHeaderText().equals("Ref Carte")) {
				for (UIComponent compo : col.getChildren()) {
					System.out.println("327 " + (compo instanceof CellEditor));
					if (compo instanceof CellEditor) {
						CellEditor _cellEditor = (CellEditor) compo;
						System.out.println("330"
								+ _cellEditor.getFacet("input"));
						if (_cellEditor.getFacet("input") instanceof SelectOneMenu) {
							SelectOneMenu _html = (SelectOneMenu) _cellEditor
									.getFacet("input");

							try {
								this.productCardSelected = this.moduleGlobale
										.getProduct(Integer.parseInt(_html
												.getValue().toString()));

								_failure.getProduct().setProductConf(
										this.productCardSelected
												.getProductConf());

								_failure.getProduct().setIdProduct(
										Integer.parseInt(_html.getValue()
												.toString()));

								// _failure.setProduct(productCardSelected);

							} catch (NumberFormatException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							this.listElementChangedToSave = new ArrayList<ElementChanged>();
							// System.out.println(this.productCardSelected);
							// System.out.println(_failure);

						}
					}
				}

			}

			if (col.getHeaderText().equals("Date Code")) {
				for (UIComponent compo : col.getChildren()) {
					System.out.println("369 " + (compo instanceof CellEditor));
					if (compo instanceof CellEditor) {
						CellEditor _cellEditor = (CellEditor) compo;
						System.out.println("372 "
								+ _cellEditor.getFacet("input"));
						if (_cellEditor.getFacet("input") instanceof InputText) {
							InputText _html = (InputText) _cellEditor
									.getFacet("input");
							try {

								_failure.getProduct().setDatecode(
										_html.getValue().toString());

							} catch (NumberFormatException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							this.listElementChangedToSave = new ArrayList<ElementChanged>();

						}
					}
				}

			}

			if (col.getHeaderText().equals("N Serie")) {
				for (UIComponent compo : col.getChildren()) {
					System.out.println("395 " + (compo instanceof CellEditor));
					if (compo instanceof CellEditor) {
						CellEditor _cellEditor = (CellEditor) compo;
						System.out.println("398 "
								+ _cellEditor.getFacet("input"));
						if (_cellEditor.getFacet("input") instanceof InputText) {
							InputText _html = (InputText) _cellEditor
									.getFacet("input");
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

			if (col.getHeaderText().equals("Operateur")) {
				for (UIComponent compo : col.getChildren()) {
					System.out.println("420 " + (compo instanceof CellEditor));
					if (compo instanceof CellEditor) {
						CellEditor _cellEditor = (CellEditor) compo;
						System.out.println("423 "
								+ _cellEditor.getFacet("input"));
						if (_cellEditor.getFacet("input") instanceof SelectOneMenu) {
							SelectOneMenu _html = (SelectOneMenu) _cellEditor
									.getFacet("input");
							try {
								this.operatorSelected = this.moduleGlobale
										.getOperator(Integer.parseInt(_html
												.getValue().toString()));

								_failure.setOperator(operatorSelected);

							} catch (NumberFormatException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}

						}
					}
				}
			}

		}

		// searching product in failure
		Product _productSearching = null;

		try {
			EntryDefaultRapportForm _entryDefaut = new EntryDefaultRapportForm(
					_failure.getProduct().getProductConf().getReference(),
					_failure.getProduct().getDatecode(), _failure.getProduct()
							.getSerialNumber());
			_productSearching = _entryDefaut.getProduct();

			_failure.setProduct(_productSearching);

			System.out.println("461 " + _failure.getProduct().getIdProduct());

			/*
			 * _failure.setProduct(new Product(_failure.getProduct()
			 * .getProductConf(), _failure.getProduct().getDatecode(),
			 * _failure.getProduct().getSerialNumber(), _failure
			 * .getProduct().getProviderCode()));
			 */
			System.out.println("469 valeur de failure" + _failure);

			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("Produit trouvé" + " "
					+ _productSearching.getIdProduct()));

		} catch (EntryDefaultRapportException e) {
			System.out.println("476 erreur");
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(
					FacesMessage.SEVERITY_WARN, "Erreur Produit n'existe pas",
					e.toString()));
		}

	}

	public void editRow(ActionEvent event) {

		this.selectedFailure = (Failure) event.getComponent().getAttributes()
				.get("selectedFailure");

		System.out.println("testtt click" + selectedFailure);

	}

	public void addTopo() {
		Failure _failure = (Failure) this.selectedEvent.getObject();
		System.out.println("488 action erererer" + this.topoRef);
		System.out.println("489 " + _failure.getDiagnosisDate());
		_failure.getElementsChanged().add(new ElementChanged(this.topoRef));
		// this.listElementChanged.add(this.topoRef);

		// this.listElementChangedToSave.add(new ElementChanged(this.topoRef));

	}

	public void defaultsnoclosed() {

		try {
			this.productObjectList = this.moduleGlobale
					.getUnclosedProductionFailureReports();
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

	public void delTopo() {

		Failure _failure = (Failure) this.selectedEvent.getObject();

		System.out.println("519 delete Topo" + this.elementChangedSelected);
		int i = 0;

		for (ElementChanged elementChangedSelected : _failure
				.getElementsChanged()) {
			System.out.println("524 " + elementChangedSelected.getCode()
					+ " = " + this.elementChangedSelected);
			if (elementChangedSelected.getCode().equals(
					this.elementChangedSelected)) {

				_failure.getElementsChanged().remove(i);
				break;
			}
			i++;
		}

	}

	public void selectedFailure(SelectEvent event) {
		System.out.println("538 ID failure seleted"
				+ ((Failure) event.getObject()).getIdFailure());
		Failure failure = (Failure) event.getObject();
		if (failure.getProduct().getProductConf() != null) {
			System.out
					.println("543 selected Failure"
							+ ((Failure) event.getObject()).getProduct()
									.getIdProduct());
		}
		this.selectedEvent = event;
	}

	public void deleteFailure() {

		System.out.println("552 delete Failure");
		// System.out.println("taille list Failure" + this.listFailure.size());
		Failure _failureSelected = (Failure) this.selectedEvent.getObject();
		int i = 0;
		for (Failure failure : this.selectedObject.getFailures()) {
			System.out.println("557 "
					+ (failure.getProduct().getProductConf().getReference()
							+ " = " + _failureSelected.getProduct()
							.getProductConf().getReference()));
			if (failure
					.getProduct()
					.getProductConf()
					.getReference()
					.equals((_failureSelected.getProduct().getProductConf()
							.getReference()))
					&& failure.getFailureCode().equals(
							_failureSelected.getFailureCode())) {
				System.out.println("569 remove");
				this.selectedObject.getFailures().remove(i);
				break;

			}
			i++;
		}
		this.cptFailure--;
	}

	@Override
	public void detailAction() {
		// TODO Auto-generated method stub
		FacesContext facesContext = FacesContext.getCurrentInstance();
		InputTextarea _inputTextArea = (InputTextarea) facesContext
				.getViewRoot().findComponent("formDial:failureReportComment");

		if (this.selectedObject.getFailureReportComment().getComment() == null) {

			_inputTextArea.setDisabled(false);
		} else {
			_inputTextArea.setDisabled(true);
		}
	}

	public void test(AjaxBehaviorEvent event) {
		System.out.println("595 test" + event.getSource());
		InputTextarea _inputTextarea = (InputTextarea) event.getSource();
		@SuppressWarnings("unused")
		String _textFailureReportComment = ((FailureReportComment) this.selectedObject
				.getFailureReportComment()).getComment();
		if (_inputTextarea.getValue().equals("")) {
			_inputTextarea.setDisabled(false);
		} else {

			_inputTextarea.setDisabled(true);
		}
		System.out.println("606 "
				+ this.selectedObject.getFailureReportComment());

	}

	@Override
	public void getFamiliesListProduct() throws SQLException {

	}

	@Override
	public void searchProduct() {
		// TODO Auto-generated method stub

	}

	private void addFailureinList(Failure _failure) {

		this.newListFailure.add(_failure);

	}

	@Override
	public void modifyProduct(ActionEvent event) {
		CommandButton _commandButton = (CommandButton) event.getSource();
		Dialog _dialog = getDialogToButton(_commandButton);

		// verification des Failures rajoutés
		int cptFailure = 0;
		for (Failure failure : this.selectedObject.getFailures()) {

			for (Failure newFailure : this.newListFailure) {
				System.out.println("637 test verificcation"
						+ failure.getIdFailure() + " == "
						+ newFailure.getIdFailure());
				if (failure.getIdFailure() == newFailure.getIdFailure()
						&& failure.getProduct().getIdProduct() == newFailure
								.getProduct().getIdProduct()
						&& failure.getFailureCode().equals(
								newFailure.getFailureCode())) {

					failure.setIdFailure(0);
					this.selectedObject.getFailures().get(cptFailure)
							.setIdFailure(0);

				}

			}
			cptFailure++;
		}
		try {
			System.out.println("656 valeur de failure code"
					+ this.selectedObject.getFailureCode());
			@SuppressWarnings("unused")
			EntryDefaultRapportForm _entryDefautlForm = new EntryDefaultRapportForm(
					this.selectedObject.getFailureReportComment().getComment(),
					this.selectedObject.getRegistrationDate(),
					this.selectedObject.getTesterReport().getOperatorCode(),
					this.selectedObject.getFailureCode());

			if (this.selectedObject.getCustomerComment() == null) {

				CustomerComment _customerComment = new CustomerComment("");
				this.selectedObject.setCustomerComment(_customerComment);

			}
			this.moduleGlobale
					.setFailureReport(this.selectedObject, this.selectedObject
							.getRegistrationDate(), this.selectedObject
							.getProduct().getProductConf().getReference(),
							this.selectedObject.getProduct().getProductConf()
									.getMajorIndex(), this.selectedObject
									.getProduct().getProductConf()
									.getMinorIndex(), this.selectedObject
									.getProduct().getSerialNumber(),
							this.selectedObject.getProduct().getDatecode(),
							this.selectedObject.getTesterReport().getTestType()
									.getName(), this.selectedObject
									.getTesterReport().getTester().getName(),
							this.selectedObject.getTesterReport()
									.getOperatorCode(), this.selectedObject
									.getCustomerComment().getComment(),
							this.selectedObject.getFailureCode(),
							this.selectedObject.getFailureReportComment()
									.getComment(), this.selectedObject
									.getFailures());

			this.productObjectList = this.moduleGlobale
					.getUnclosedProductionFailureReports();
			this.newListFailure.clear();
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("Modification Reussie"));
			hideDialog(_dialog);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ConfigFileReaderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JProductBaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ProductDaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FailureModuleException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TesterReportDaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ApparentCauseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN,
							"Erreur Formulaire", e.toString()));
		}

	}

	public void closeProduct() {

		System.out.println("733 " + this.selectedObject);

		System.out.println("735 la taille de la liste des nes failures"
				+ this.newListFailure.size());
		// verification des Failures rajoutés
		int cptFailure = 0;
		for (Failure failure : this.selectedObject.getFailures()) {

			for (Failure newFailure : this.newListFailure) {
				System.out.println("742 test verificcation"
						+ failure.getIdFailure() + " == "
						+ newFailure.getIdFailure());
				if (failure.getIdFailure() == newFailure.getIdFailure()
						&& failure.getProduct().getIdProduct() == newFailure
								.getProduct().getIdProduct()
						&& failure.getFailureCode().equals(
								newFailure.getFailureCode())) {

					failure.setIdFailure(0);
					this.selectedObject.getFailures().get(cptFailure)
							.setIdFailure(0);
					System.out
							.println("755 format de l'id failure"
									+ this.selectedObject.getFailures().get(
											cptFailure));
					System.out.println("758 valeur apres formatage"
							+ this.selectedObject);
				}
			}
			cptFailure++;
		}
		try {
			System.out.println("765 update: " + selectedObject);
			// close productionFailureReport
			this.selectedObject.setState(2);

			System.out.println("1 " + this.selectedObject);
			System.out
					.println("2 " + this.selectedObject.getRegistrationDate());
			System.out.println("3 "
					+ this.selectedObject.getProduct().getProductConf()
							.getReference());
			System.out.println("4 "
					+ this.selectedObject.getProduct().getProductConf()
							.getMajorIndex());
			System.out.println("5 "
					+ this.selectedObject.getProduct().getProductConf()
							.getMinorIndex());
			System.out.println("6 "
					+ this.selectedObject.getProduct().getSerialNumber());
			System.out.println("7 "
					+ this.selectedObject.getProduct().getDatecode());
			System.out.println("8 "
					+ this.selectedObject.getTesterReport().getTestType()
							.getName());
			System.out.println("9 "
					+ this.selectedObject.getTesterReport().getTester()
							.getName());
			System.out.println("10 "
					+ this.selectedObject.getTesterReport().getOperatorCode());
			System.out.println("11 "
					+ this.selectedObject.getCustomerComment().getComment());
			System.out.println("12 " + this.selectedObject.getFailureCode());
			System.out.println("13 "
					+ this.selectedObject.getFailureReportComment()
							.getComment());
			System.out.println("14 " + this.selectedObject.getFailures());

			this.moduleGlobale
					.setFailureReport(this.selectedObject, this.selectedObject
							.getRegistrationDate(), this.selectedObject
							.getProduct().getProductConf().getReference(),
							this.selectedObject.getProduct().getProductConf()
									.getMajorIndex(), this.selectedObject
									.getProduct().getProductConf()
									.getMinorIndex(), this.selectedObject
									.getProduct().getSerialNumber(),
							this.selectedObject.getProduct().getDatecode(),
							this.selectedObject.getTesterReport().getTestType()
									.getName(), this.selectedObject
									.getTesterReport().getTester().getName(),
							this.selectedObject.getTesterReport()
									.getOperatorCode(), this.selectedObject
									.getCustomerComment().getComment(),
							this.selectedObject.getFailureCode(),
							this.selectedObject.getFailureReportComment()
									.getComment(), this.selectedObject
									.getFailures());

			this.productObjectList = this.moduleGlobale
					.getUnclosedProductionFailureReports();

			this.newListFailure.clear();

			RequestContext context = RequestContext.getCurrentInstance();
			context.execute("dialProd.hide()");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ConfigFileReaderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JProductBaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ProductDaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FailureModuleException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TesterReportDaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public List<Failure> getListFailure() {
		return listFailure;
	}

	public void setListFailure(List<Failure> listFailure) {
		this.listFailure = listFailure;
	}

	public Failure getSelectedFailure() {
		return selectedFailure;
	}

	public void setSelectedFailure(Failure selectedFailure) {
		this.selectedFailure = selectedFailure;
	}

	public String getTopoRef() {
		return topoRef;
	}

	public void setTopoRef(String topoRef) {
		this.topoRef = topoRef;
	}

	public String getElementChangedSelected() {
		return elementChangedSelected;
	}

	public void setElementChangedSelected(String elementChangedSelected) {
		this.elementChangedSelected = elementChangedSelected;
	}

	public List<Operator> getOperatorList() {
		return operatorList;
	}

	public void setOperatorList(List<Operator> operatorList) {
		this.operatorList = operatorList;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public Date getDateStart() {
		return dateStart;
	}

	public void setDateStart(Date dateStart) {
		this.dateStart = dateStart;
	}

	public Date getDateEnd() {
		return dateEnd;
	}

	public void setDateEnd(Date dateEnd) {
		this.dateEnd = dateEnd;
	}

	public Boolean getSearch() {
		return search;
	}

	public void setSearch(Boolean search) {
		this.search = search;
	}

}