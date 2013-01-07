package fr.la.jproductbaseweb.beanmanaged.prodentry;

import java.sql.SQLException;

import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import fr.la.jproductbase.metier.ProductionFailureReport;

@SessionScoped
public class GestFormDefaultsBean<T> extends GestFormSearchAbstract<ProductionFailureReport> {
	
	public GestFormDefaultsBean(){
				
		System.out.println("valeur path prout" + this.pathLoadingPage);
		
	}
	
	
	

	@Override
	public void detailAction() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void getFamiliesListProduct() throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void searchProduct() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modifyProduct(ActionEvent event) {
		// TODO Auto-generated method stub
		
	}

}
