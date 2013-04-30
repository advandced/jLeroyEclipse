package fr.la.jproductbaseweb.converter;

import fr.la.configfilereader.ConfigFileReaderException;
import fr.la.jproductbase.metier.FollowingFormModel;
import fr.la.jproductbase.service.ServiceInterface;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

@FacesConverter(value = "followingFormModelConverter")
public class FollowingModelConverter implements Converter {

    private List<FollowingFormModel> followingFormList;

    public FollowingModelConverter() {
        System.out.println("get apparentCauseCustomerActive");
        ServiceInterface _serviceInterface = ServiceInterface.getInstance();
        followingFormList = _serviceInterface.getAllActiveFollowingFormModel();
    }

    @Override
    public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
        if (arg2.trim().equals("")) {
            return null;
        } else {
            try {
                System.out.println("getAsObject " + arg2);
                int number = Integer.parseInt(arg2);
                for (FollowingFormModel followingForm : followingFormList) {

                    if (followingForm.getIdFollowingFormmodel() == number) {

                        return followingForm;
                    }

                }
            } catch (NumberFormatException exception) {
                throw new ConverterException(new FacesMessage(
                        FacesMessage.SEVERITY_ERROR, "Conversion error",
                        "probleme Apparent Cause"));
            }

            return null;

        }

    }

    @Override
    public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
        if (arg2 == null) {
            return null;
        } else {

            return String.valueOf(((FollowingFormModel) arg2)
                    .getIdFollowingFormmodel());
        }

    }

    public List<FollowingFormModel> getFollowingFormList() {
        return followingFormList;
    }

    public void setFollowingFormList(List<FollowingFormModel> followingFormList) {
        this.followingFormList = followingFormList;
    }
}
