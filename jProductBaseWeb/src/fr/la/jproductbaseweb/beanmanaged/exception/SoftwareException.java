package fr.la.jproductbaseweb.beanmanaged.exception;

public class SoftwareException extends Exception {

	private static final long serialVersionUID = 1L;

	public SoftwareException(){
		System.out.println("Tous les champs sont obligatoires");
	}
}