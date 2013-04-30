package fr.la.jproductbaseweb.converter;

import fr.la.jproductbase.metier.ProductConfModel;
import fr.la.jproductbase.service.ServiceInterface;
import java.sql.SQLException;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

@FacesConverter(value = "productConfModelConverter")
public class ProductConfModelConverter implements Converter {

    private List<ProductConfModel> productConfModelList;

    public ProductConfModelConverter() {
        System.out.println("get productConfActive");
        ServiceInterface _serviceInterface = ServiceInterface.getInstance();
        productConfModelList = _serviceInterface.getActiveProductConfModels();
    }

    @Override
    public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
        if (arg2.trim().equals("")) {
            return null;
        } else {
            try {
                System.out.println("getAsObject " + arg2);
                int number = Integer.parseInt(arg2);
                for (ProductConfModel productConfModel : productConfModelList) {

                    if (productConfModel.getIdProductConfModel() == number) {

                        return productConfModel;
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

            return String.valueOf(((ProductConfModel) arg2)
                    .getIdProductConfModel());
        }

    }

    public List<ProductConfModel> getProductConfModelList() {
        return productConfModelList;
    }

    public void setProductConfModelList(
            List<ProductConfModel> productConfModelList) {
        this.productConfModelList = productConfModelList;
    }
}
