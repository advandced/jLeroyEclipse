package fr.la.jproductbaseweb.converter;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import fr.la.configfilereader.ConfigFileReaderException;
import fr.la.jproductbase.metier.Product;
import fr.la.jproductbase.metier.ProductType;
import fr.la.jproductbase.service.ServiceInterface;

@FacesConverter(value = "productConverter")
public class ProductConverter implements Converter{
	
	
	
private List<Product> productConverter;
	
	public ProductConverter(){
	System.out.println("get productConfActive");
	ServiceInterface _serviceInterface = ServiceInterface.getInstance();
	ProductType _productType = _serviceInterface.getProductType("Carte");
	productConverter = _serviceInterface.getProducts(_productType);
}



@Override
public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
	if(arg2.trim().equals("")){
		return null;
	}else {
		try{
			System.out.println("getAsObject "+arg2);
			int number = Integer.parseInt(arg2);
			for(Product product : productConverter){
				
				if(product.getIdProduct()==number){
					
					return product;
				}
				
			}
		}
		catch(NumberFormatException exception){
			throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR,"Conversion error","probleme Product Conf Model"));
		}
			
			
		
		return null;
		
		
	}
	
}

@Override
public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
if(arg2 == null){
return null;
}
else {

return String.valueOf(((Product) arg2).getIdProduct());
}

}
















	
	
	

}
