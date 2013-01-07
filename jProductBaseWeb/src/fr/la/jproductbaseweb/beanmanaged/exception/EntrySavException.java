package fr.la.jproductbaseweb.beanmanaged.exception;

public class EntrySavException extends FormException {
private String message;

	private static final long serialVersionUID = 1L;

	public EntrySavException(){
		
		System.out.println("erreur");
		
	}
	
	public EntrySavException(String message){
		
		this.message = message;
		System.out.println(message);
		
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}