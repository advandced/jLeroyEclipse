package fr.la.jproductbase.metier;

import java.sql.Timestamp;

/**
 * Classe m&eacute;tier de type de test.
 * 
 * @author stc
 *
 */
public class TestType {
	private int idTestType;
	private Timestamp timestamp;
	private int state; // 0: Disable, 1: Enable
	private String name;
	private boolean needTester;
	
	/**
	 * Cr&eacute;er un type de test
	 * 
	 * @param idTestType Identifiant du type de test.
	 * @param timeStamp Horadatage de l'enregistrement.
	 * @param state Etat de l'enregistrement.
	 * @param name Nom du type de test.
	 * @param needTester Le type de test nécessite t-il un testeur.
	 */
	public TestType(int idTestType, Timestamp timeStamp, int state, String name, boolean needTester) {
		this.idTestType = idTestType;
		this.timestamp = timeStamp;
		this.state = state;
		this.name = name;
		this.needTester = needTester;
	}

	/**
	 * GETTERS AND SETTERS 
	 */
	
	/**
	 * @return the idTestType
	 */
	public int getIdTestType() {
		return idTestType;
	}

	/**
	 * @param idTestType the idTestType to set
	 */
	public void setIdTestType(int idTestType) {
		this.idTestType = idTestType;
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

	/**
	 * @return the needTester
	 */
	public boolean isNeedTester() {
		return needTester;
	}

	/**
	 * @param needTester the needTester to set
	 */
	public void setNeedTester(boolean needTester) {
		this.needTester = needTester;
	}
}
