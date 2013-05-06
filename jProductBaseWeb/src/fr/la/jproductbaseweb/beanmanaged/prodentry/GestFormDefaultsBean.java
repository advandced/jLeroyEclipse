package fr.la.jproductbaseweb.beanmanaged.prodentry;

import java.sql.SQLException;

import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import fr.la.jproductbase.metier.ProductionFailureReport;

@SessionScoped
public class GestFormDefaultsBean<T> extends GestFormSearchAbstract<ProductionFailureReport> {
	
	public GestFormDefaultsBean(){}

	@Override
	public void detailAction() {}

	@Override
	public void getFamiliesListProduct() throws SQLException {}

	@Override
	public void searchProduct() {}

	@Override
	public void modifyProduct(ActionEvent event) {}

}
