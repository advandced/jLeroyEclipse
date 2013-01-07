package fr.la.jproductbase.metier;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Classe m&eacute;tier de type de document li&eacute; à un produit.
 * exemple : d&eacute;rogation.
 * 
 * @author rmo
 * 
 */
public class ProductDocumentType implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int idProductDocumentType;
	private Timestamp timestamp;
	private int state;
	private String name;

	/**
	 * Cr&eacute;er un type de document.
	 * 
	 * @param idProductDocument
	 *            Identifiant du type de document.
	 * @param timestamp
	 *            Horadatage de l'enregistrement.
	 * @param state
	 *            Etat de l'enregistrement.
	 * @param timestamp
	 * @param state
	 * @param name
	 */
	public ProductDocumentType(int idProductDocumentType, Timestamp timestamp,
			int state, String name) {
		this.idProductDocumentType = idProductDocumentType;
		this.timestamp = timestamp;
		this.state = state;
		this.name = name;
	}
	
	/**
	 * GETTERS AND SETTERS
	 */

	public int getIdProductDocumentType() {
		return idProductDocumentType;
	}

	public void setIdProductDocumentType(int idProductDocumentType) {
		this.idProductDocumentType = idProductDocumentType;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	
}
