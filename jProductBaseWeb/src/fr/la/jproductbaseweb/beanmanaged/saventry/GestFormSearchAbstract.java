package fr.la.jproductbaseweb.beanmanaged.saventry;

import java.util.List;

import javax.faces.event.ActionEvent;

import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.dialog.Dialog;
import org.primefaces.component.tabview.Tab;
import org.primefaces.context.RequestContext;

import fr.la.jproductbase.metier.ProductFamily;
import fr.la.jproductbase.service.ServiceInterface;

public abstract  class GestFormSearchAbstract<T> {

	protected String reference;
	protected String majorIndex;
	protected String minorIndex;
	protected String dateCode;
	protected String serialNumber;
	protected String model;
	protected String macAdress;
	protected String supplierCode;
	protected String configuration;
	protected int state;
	
	protected List<ProductFamily> productFamilies;
	protected List<T> productObjectList;

	
	protected Tab tabSelected;
	protected ServiceInterface moduleGlobale;
	protected ProductFamily selectedProductFamily;
	protected T selectedObject;
	protected Dialog dialog;
	
	public GestFormSearchAbstract(){
		this.moduleGlobale = ServiceInterface.getInstance();
		getFamiliesListProduct();
	}
			
	protected Dialog getDialogToButton(CommandButton commandButton) {

		Dialog _dialog = null;
		/*Tab _tab = (Tab) commandButton.getParent();
		this.tabSelected = _tab;
		TabView _tabview = (TabView) _tab.getParent();
		_dialog = (Dialog) _tabview.getParent();*/

		return _dialog;
	}

	protected void hideDialog(Dialog dialog){
		System.out.println("methode hide dialog");
		RequestContext _context = RequestContext.getCurrentInstance();
		
	_context.execute(dialog.getWidgetVar() + ".hide()");
		
	}

	
	
	
	

	public void quit(ActionEvent event) {
		System.out.println("quit");
		CommandButton _buttonAnnul = (CommandButton) event.getSource();
	    Dialog _dialog = getDialogToButton(_buttonAnnul);
		 
	    hideDialog(_dialog);
		// return "/param/operator.jsf";

	}
	
	
	public abstract void detailAction();
	
	
	
	public abstract void getFamiliesListProduct();
	public abstract void searchProduct();
	public abstract void modifyProduct(ActionEvent event);

	


	public String getMajorIndex() {
		return majorIndex;
	}


	public void setMajorIndex(String majorIndex) {
		this.majorIndex = majorIndex;
	}


	public String getMinorIndex() {
		return minorIndex;
	}


	public void setMinorIndex(String minorIndex) {
		this.minorIndex = minorIndex;
	}


	public String getDateCode() {
		return dateCode;
	}


	public void setDateCode(String dateCode) {
		this.dateCode = dateCode;
	}


	public String getSerialNumber() {
		return serialNumber;
	}


	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}


	public String getModel() {
		return model;
	}


	public void setModel(String model) {
		this.model = model;
	}


	public String getMacAdress() {
		return macAdress;
	}


	public void setMacAdress(String macAdress) {
		this.macAdress = macAdress;
	}


	public String getSupplierCode() {
		return supplierCode;
	}


	public void setSupplierCode(String supplierCode) {
		this.supplierCode = supplierCode;
	}


	public int getState() {
		return state;
	}


	public void setState(int state) {
		this.state = state;
	}


	public List<ProductFamily> getProductFamilies() {
		return productFamilies;
	}


	public void setProductFamilies(List<ProductFamily> productFamilies) {
		this.productFamilies = productFamilies;
	}


	public List<T> getProductObjectList() {
		return productObjectList;
	}


	public void setProductObjectList(List<T> productObjectList) {
		this.productObjectList = productObjectList;
	}


	public ProductFamily getSelectedProductFamily() {
		return selectedProductFamily;
	}


	public void setSelectedProductFamily(ProductFamily selectedProductFamily) {
		this.selectedProductFamily = selectedProductFamily;
	}


	public T getSelectedObject() {
		System.out.println("selected"+selectedObject);
		return selectedObject;
	}


	public void setSelectedObject(T selectedObject) {
		this.selectedObject = selectedObject;
	}


	public String getConfiguration() {
		return configuration;
	}


	public void setConfiguration(String configuration) {
		this.configuration = configuration;
	}


	public String getReference() {
		return reference;
	}


	public void setReference(String reference) {
		this.reference = reference;
	}

	public Tab getTabSelected() {
		return tabSelected;
	}

	public void setTabSelected(Tab tabSelected) {
		this.tabSelected = tabSelected;
	}
	
	
	
	
	
	
	
}
