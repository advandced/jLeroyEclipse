package fr.la.jproductbaseweb.beanmanaged.exception;

public class TesterException extends FormException  {

	private static final long serialVersionUID = 1L;

	public TesterException(){
		System.out.println("Formulaire incomplet");
	}
}