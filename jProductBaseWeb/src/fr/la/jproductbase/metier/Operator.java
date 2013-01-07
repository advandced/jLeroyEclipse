package fr.la.jproductbase.metier;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Classe m&eacute; d'op&eacute;rateur.
 * 
 * @author stc
 *
 */
public class Operator implements Serializable {

	private static final long serialVersionUID = 1L;

	private int idOperator;
	private Timestamp timestamp;
	private int state; // 0: Disable, 1: Enable
	private String code;
	private String firstName;
	private String lastName;
	
    /**
     * Cr&eacute;er un op&eacute;rateur.
     * 
     * @param idOperator Identifiant de l'op&eacute;rateur.
     * @param timestamp Horadatage de l'enregistrement.
     * @param state Etat de l'enregistrement.
     * @param code Code de l'op&eacute;rateur.
     * @param lastName Nom de l'op&eacute;rateur.
     * @param firstName : Pr&eacute;nom de l'op&eacute;rateur. 
     */
	public Operator(int idOperator, Timestamp timestamp, int state,
			String code, String lastName, String firstName) {
		this.idOperator = idOperator;
		this.timestamp = timestamp;
		this.state = state;
		this.code = code;
		this.lastName = lastName;
		this.firstName = firstName;
	}
	
	public Operator(){}

	/**
	 * GETTERS AND SETTERS 
	 */
	
	/**
	 * @return the idOperator
	 */
	public int getIdOperator() {
		return idOperator;
	}

	/**
	 * @param idOperator the idOperator to set
	 */
	public void setIdOperator(int idOperator) {
		this.idOperator = idOperator;
	}

	/**
	 * @return the timestamp
	 */
	public Timestamp getTimestamp() {
		return timestamp;
	}

	/**
	 * @param timestamp the timestamp to set
	 */
	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	/**
	 * @return the state
	 */
	public int getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(int state) {
		this.state = state;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public String toString() {
		return lastName;
	}
	
	
	
}
