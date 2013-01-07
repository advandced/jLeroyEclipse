package fr.la.jproductbaseweb.converter;

import java.sql.SQLException;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import fr.la.jproductbase.metier.ApparentCause;

import fr.la.jproductbase.service.ServiceInterface;

@FacesConverter(value = "apparentCauseLAIConverter")
public class ApparentCauseLAIConverter implements Converter {

	private List<ApparentCause> apparentCauseList;

	public ApparentCauseLAIConverter() {
		//System.out.println("get apparentCauseLAIrActive");
		ServiceInterface _serviceInterface = new ServiceInterface();
		try {
			apparentCauseList = _serviceInterface.getApparentCauses();
			//System.out.println("taille list apparent cause" + apparentCauseList.size());
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
				//System.out.println("getAsObject " + arg2);
				int number = Integer.parseInt(arg2);
				for (ApparentCause apparentCause : apparentCauseList) {
					//System.out
							//.println(apparentCause.getIdApparentCause() == number);
					if (apparentCause.getIdApparentCause() == number) {

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
		//System.out.println("as string"+((ApparentCause) arg2).getIdApparentCause());
		//System.out.println(arg2);
		if (arg2 == null) {
			return null;
		} else {
			//System.out.println("test");
			return String.valueOf(((ApparentCause) arg2).getIdApparentCause());
		}

	}

	public List<ApparentCause> getApparentCauseList() {
		return apparentCauseList;
	}

	public void setApparentCauseList(List<ApparentCause> apparentCauseList) {
		this.apparentCauseList = apparentCauseList;
	}

}