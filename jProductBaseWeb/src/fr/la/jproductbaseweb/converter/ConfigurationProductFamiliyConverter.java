package fr.la.jproductbaseweb.converter;

import fr.la.jproductbase.metier.ProductFamily;
import fr.la.jproductbase.service.ServiceInterface;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

@FacesConverter(value = "configurationProductFamilyConverter")
public class ConfigurationProductFamiliyConverter implements Converter {

    private List<ProductFamily> productFamilies;

    public ConfigurationProductFamiliyConverter() {
        System.out.println("get productConfActive");
        ServiceInterface _serviceInterface = ServiceInterface.getInstance();
        productFamilies = _serviceInterface.getProductFamilies();
    }

    @Override
    public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
        if (arg2.trim().equals("")) {
            return null;
        } else {
            try {
                System.out.println("getAsObject " + arg2);
                int number = Integer.parseInt(arg2);
                for (ProductFamily productConf : productFamilies) {

                    if (productConf.getIdProductFamily() == number) {

                        return productConf;
                    }

                }
            } catch (NumberFormatException exception) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion error", "probleme Apparent Cause"));
            }

            return null;

        }

    }

    @Override
    public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
        if (arg2 == null) {
            return null;
        } else {

            return String.valueOf(((ProductFamily) arg2).getIdProductFamily());
        }

    }

    public List<ProductFamily> getProductConfList() {
        return productFamilies;
    }

    public void setProductConfList(List<ProductFamily> productConfList) {
        this.productFamilies = productConfList;
    }
}
