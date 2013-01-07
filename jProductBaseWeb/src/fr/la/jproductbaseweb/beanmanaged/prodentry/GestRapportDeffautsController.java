package fr.la.jproductbaseweb.beanmanaged.prodentry;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import fr.la.jproductbase.metier.ProductionFailureReport;

@SessionScoped
@ManagedBean(name="gestRapportDeffautsController")
public class GestRapportDeffautsController {

	private ProductionFailureReport selectedProduct;

	
	
		
	public GestRapportDeffautsController(){
		
	}




	public ProductionFailureReport getSelectedProduct() {
		return selectedProduct;
	}




	public void setSelectedProduct(ProductionFailureReport selectedProduct) {
	
		this.selectedProduct = selectedProduct;
	}
	
	
	
	
	
	
	
	
	
}
