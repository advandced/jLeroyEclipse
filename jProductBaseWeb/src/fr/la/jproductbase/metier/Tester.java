package fr.la.jproductbase.metier;

import java.sql.Timestamp;

/**
 * Classe m&eacute;tier de testeur.
 * 
 * @author stc
 *
 */
public class Tester {
	private int idTester;
	private Timestamp timestamp;
	private int state; // 0: Disable, 1: Enable
	private String name;
	
	/**
	 * Cr&eacute;er un testeur.
	 * 
	 * @param idTester Identifiant du testeur.
	 * @param timeStamp Horadatage de l'enregistrement.
	 * @param state Etat de l'enregistrement.
	 * @param name Nom du testeur.
	 */
	public Tester(int idTester, Timestamp timeStamp, int state, String name) {
		this.idTester = idTester;
		this.timestamp = timeStamp;
		this.state = state;
		this.name = name;
	}

	/**
	 * GETTERS AND SETTERS 
	 */
	
	/**
	 * @return the idTester
	 */
	public int getIdTester() {
		return idTester;
	}

	/**
	 * @param idTester the idTester to set
	 */
	public void setIdTester(int idTester) {
		this.idTester = idTester;
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
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
}
