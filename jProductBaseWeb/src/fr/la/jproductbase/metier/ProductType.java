package fr.la.jproductbase.metier;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Classe m&eacute;tier du type de produit.
 * 
 * @author stc
 *
 */
public class ProductType implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int idProductType;
	private Timestamp timestamp;
	private int state; // 0: Disable, 1: Enable
	private String name;

	/**
	 * Cr&eacute;er une alimentation produit.
	 * 
	 * @param idProductType Identifiant du type de produit.
	 * @param timestamp Horadatage de l'enregistrement.
	 * @param state Etat de l'enregistrement.
	 * @param name Nom du type de produit.
	 */
	public ProductType(int idProductType, Timestamp timestamp, int state, String name) {
		this.idProductType = idProductType;
		this.timestamp = timestamp;
		this.state = state;
		this.name = name;
	}

	/**
	 * GETTERS AND SETTERS 
	 */
	
	/**
	 * @return the idProductType
	 */
	public int getIdProductType() {
		return idProductType;
	}

	/**
	 * @param idProductType the idProductType to set
	 */
	public void setIdProductType(int idProductType) {
		this.idProductType = idProductType;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idProductType;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProductType other = (ProductType) obj;
		if (idProductType != other.idProductType)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return name;
	}
	
	
	
	
}
