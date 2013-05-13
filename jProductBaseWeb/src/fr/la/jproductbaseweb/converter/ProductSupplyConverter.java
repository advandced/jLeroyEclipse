package fr.la.jproductbaseweb.converter;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import fr.la.jproductbase.metier.ProductSupply;
import fr.la.jproductbase.service.ServiceInterface;

@FacesConverter(value = "productSupplyConverter")
public class ProductSupplyConverter implements Converter {

	private List<ProductSupply> productSupplyConverter;

	public ProductSupplyConverter() {
		System.out.println("get productConfActive");
		ServiceInterface _serviceInterface = ServiceInterface.getInstance();
		productSupplyConverter = _serviceInterface.getActiveProductSupplies();
	}

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		if (arg2.trim().equals("")) {
			return null;
		} else {
			try {
				System.out.println("getAsObject " + arg2);
				int number = Integer.parseInt(arg2);
				for (ProductSupply productSupply : productSupplyConverter) {
					if (productSupply.getIdProductSupply() == number) {

						return productSupply;
					}
				}
			} catch (NumberFormatException exception) {
				throw new ConverterException(new FacesMessage(
						FacesMessage.SEVERITY_ERROR, "Conversion error",
						"probleme Product Conf Model"));
			}
			return null;
		}
	}


	public List<ProductSupply> getProductSupplyConverter() {
		return productSupplyConverter;
	}

	public void setProductSupplyConverter(
			List<ProductSupply> productSupplyConverter) {
		this.productSupplyConverter = productSupplyConverter;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		if (arg2 == null) {
			return null;
		} else {
			return String.valueOf(((ProductSupply) arg2).getIdProductSupply());
		}
	}

}
