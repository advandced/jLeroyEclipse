package fr.la.juserright.exception;

	public class ErrorException extends Exception {

		private static final long serialVersionUID = 1L;
		
		public ErrorException() {
	        super();
	    }
	    public ErrorException(String s) {
	        super(s);
	    }
	}