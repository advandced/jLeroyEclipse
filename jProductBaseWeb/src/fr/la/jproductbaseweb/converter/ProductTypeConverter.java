package fr.la.jproductbaseweb.converter;

import java.sql.SQLException;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import fr.la.jproductbase.metier.ProductType;
import fr.la.jproductbase.service.ServiceInterface;

@FacesConverter(value = "productFamilyConverter")
public class ProductTypeConverter implements Converter {

	private List<ProductType> productTypeConverter;

	public ProductTypeConverter() {
		ServiceInterface _serviceInterface = new ServiceInterface();
		try {
			productTypeConverter = _serviceInterface.getActiveProductTypes();
			System.out.println("taille list : " + productTypeConverter.size());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		if (arg2.trim().equals("")) {
			return null;
		} else {
			try {
				System.out.println("getAsObject " + arg2);
				int number = Integer.parseInt(arg2);
				for (ProductType productType : productTypeConverter) {
					if (productType.getIdProductType() == number) {
						return productType;
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

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		if (arg2 == null) {
			return null;
		} else {

			return String.valueOf(((ProductType) arg2).getIdProductType());
		}
	}

	public List<ProductType> getProductTypeConverter() {
		return productTypeConverter;
	}

	public void setProductTypeConverter(List<ProductType> productTypeConverter) {
		this.productTypeConverter = productTypeConverter;
	}
}
