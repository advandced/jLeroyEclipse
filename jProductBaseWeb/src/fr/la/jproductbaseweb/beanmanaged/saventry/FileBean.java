package fr.la.jproductbaseweb.beanmanaged.saventry;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.model.StreamedContent;

@ManagedBean(name = "fileBean")
@SessionScoped
public class FileBean implements Serializable {

	// Init
	// ---------------------------------------------------------------------------------------

	private static final long serialVersionUID = 1L;
	
	private String fileName;
	private String interventionSheetLink;
	private String path;
//	private GestSearchSAVBean gestSearchSAV;

	private StreamedContent file;

	public FileBean() {
/*
		FacesContext context = FacesContext.getCurrentInstance();
		gestSearchSAV = (GestSearchSAVBean) context
				.getApplication()
				.getExpressionFactory()
				.createValueExpression(context.getELContext(),
						"#{gestSearchSAV}", GestSearchSAVBean.class)
				.getValue(context.getELContext());
		if (this.gestSearchSAV.getSelectedAfterSaleReport()
				.getInterventionSheetLink() == null) {

			this.interventionSheetLink = null;

		} else {
			System.out.println("init fileBean"
					+ gestSearchSAV.getSelectedAfterSaleReport());

			if (!this.gestSearchSAV.getSelectedAfterSaleReport()
					.getInterventionSheetLink().isEmpty()) {
				this.interventionSheetLink = this.gestSearchSAV
						.getSelectedAfterSaleReport()
						.getInterventionSheetLink();
			} else {
				this.interventionSheetLink = null;
			}
		}*/
	}

	// Actions
	// ------------------------------------------------------------------------------------
	public void submit() {
		InterventionSheetModel _interventionSheetModel = new InterventionSheetModel();
		System.out.println(this.path);
		_interventionSheetModel.setPathFile(this.path);

	}

	// Getters
	// ------------------------------------------------------------------------------------


	public String getFileName() {
		return fileName;
	}

	// Setters
	// ------------------------------------------------------------------------------------

	public String getInterventionSheetLink() {
		return interventionSheetLink;
	}

	public void setInterventionSheetLink(String interventionSheetLink) {
		this.interventionSheetLink = interventionSheetLink;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public StreamedContent getFile() {
		return file;
	}

	public void setFile(StreamedContent file) {
		this.file = file;
	}

}
