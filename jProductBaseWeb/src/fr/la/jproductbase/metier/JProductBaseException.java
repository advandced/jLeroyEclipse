package fr.la.jproductbase.metier;

public class JProductBaseException extends Exception {
// TODO Erreur conception car appelée depuis plusieurs couches ?
	private static final long serialVersionUID = 1L;

	public JProductBaseException(String pString) {
        super(pString);
    }
}
