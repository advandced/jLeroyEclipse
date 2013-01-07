package fr.la.jproductbase.metier;

import java.io.Serializable;
import java.sql.Timestamp;

public class Defect implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int idDefect;
	private Timestamp timestamp;
	private int state;
	private String sequence;
	private String testName;
	private String function;
	private String value;

	/**
	 * Cr&eacute;er un d&eacute;faut.
	 * 
	 * @param sequence
	 *            S&eacute;quence en d&eacute;faut.
	 * @param testName
	 *            Nom du test en d&eacute;faut.
	 * @param function
	 *            Fonction en d&eacute;faut..
	 * @param value
	 *            Valeur relev&eacute;e.
	 */
	public Defect(String sequence, String testName, String function,
			String value) {
		this.sequence = sequence;
		this.testName = testName;
		this.function = function;
		this.value = value;
	}

	/**
	 * Cr&eacute;er un d&eacute;faut.
	 * 
	 * @param idDefect
	 *            Identifiant du d&eacute;faut.
	 * @param timestamp
	 *            Horadatage de l'enregistrement.
	 * @param state
	 *            Etat de l'enregistrement.
	 * @param sequence
	 *            S&eacute;quence en d&eacute;faut.
	 * @param testName
	 *            Nom du test en d&eacute;faut.
	 * @param function
	 *            Fonction en d&eacute;faut..
	 * @param value
	 *            Valeur relev&eacute;e.
	 */
	public Defect(int idDefect, Timestamp timestamp, int state,
			String sequence, String testName, String function, String value) {
		this(sequence, testName, function, value);
		this.idDefect = idDefect;
		this.timestamp = timestamp;
		this.state = state;
	}

	/**
	 * Cr&eacute;er un d&eacute;faut.
	 */
	public Defect() {
		this.state = 1;
	}

	/**
	 * @return the idDefect
	 */
	public int getIdDefect() {
		return idDefect;
	}

	/**
	 * @param idDefect
	 *            the idDefect to set
	 */
	public void setIdDefect(int idDefect) {
		this.idDefect = idDefect;
	}

	/**
	 * @return the timestamp
	 */
	public Timestamp getTimestamp() {
		return timestamp;
	}

	/**
	 * @param timestamp
	 *            the timestamp to set
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
	 * @param state
	 *            the state to set
	 */
	public void setState(int state) {
		this.state = state;
	}

	/**
	 * @return the sequence
	 */
	public String getSequence() {
		return sequence;
	}

	/**
	 * @param sequence
	 *            the sequence to set
	 */
	public void setSequence(String sequence) {
		this.sequence = sequence;
	}

	/**
	 * @return the testName
	 */
	public String getTestName() {
		return testName;
	}

	/**
	 * @param testName
	 *            the testName to set
	 */
	public void setTestName(String testName) {
		this.testName = testName;
	}

	/**
	 * @return the function
	 */
	public String getFunction() {
		return function;
	}

	/**
	 * @param function
	 *            the function to set
	 */
	public void setFunction(String function) {
		this.function = function;
	}

	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param value
	 *            the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}
}
