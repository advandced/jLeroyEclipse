package fr.la.jproductbaseweb.beanmanaged.saventry;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name="interventionSheetLinkModel")
@SessionScoped
public class InterventionSheetModel {

	static String pathFile;

	public static String getPathFile() {
		return pathFile;
	}

	@SuppressWarnings("static-access")
	public void setPathFile(String pathFile) {
		this.pathFile = pathFile;
	}
	
}