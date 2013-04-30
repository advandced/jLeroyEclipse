package fr.la.jproductbaseweb.converter;

import java.sql.SQLException;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import fr.la.jproductbase.metier.ApparentCauseCustomer;
import fr.la.jproductbase.service.ServiceInterface;

@FacesConverter(value = "apparentCauseClientConverter")
public class ApparentCauseClientConverter implements Converter {

	private List<ApparentCauseCustomer> apparentCauseList;

	public ApparentCauseClientConverter() {
		ServiceInterface _serviceInterface = ServiceInterface.getInstance();
		apparentCauseList = _serviceInterface.getActiveApparentCausesCustomer();
	}

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		if (arg2.trim().equals("")) {
			return null;
		} else {
			try {
				//System.out.println("getAsObject " + arg2);
				int number = Integer.parseInt(arg2);
				for (ApparentCauseCustomer apparentCause : apparentCauseList) {

					if (apparentCause.getIdApparentCauseCustomer() == number) {

						return apparentCause;
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
			System.out.println(1);
			return null;
		} else {
			System.out.println(2);
			return String.valueOf(((ApparentCauseCustomer) arg2)
					.getIdApparentCauseCustomer());
		}

	}

	public List<ApparentCauseCustomer> getApparentCauseList() {
		return apparentCauseList;
	}

	public void setApparentCauseList(
			List<ApparentCauseCustomer> apparentCauseList) {
		this.apparentCauseList = apparentCauseList;
	}

}
