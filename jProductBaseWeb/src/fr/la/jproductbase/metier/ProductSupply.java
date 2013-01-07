package fr.la.jproductbase.metier;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Classe m&eacute;tier d'alimentation produit.
 * 
 * @author stc
 * 
 */
public class ProductSupply implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	private int idProductSupply;
	private Timestamp timestamp;
	private int state; // 0: Disable, 1: Enable
	private String name;
	
	public ProductSupply() {
		
	}


	/**
	 * Cr&eacute;er une alimentation produit.
	 * 
	 * @param idProductSupply
	 *            Identifiant de l'alimentation produit.
	 * @param timestamp
	 *            Horadatage de l'enregistrement.
	 * @param state
	 *            Etat de l'enregistrement.
	 * @param name
	 *            Nom de l'alimentation produit.
	 */
	public ProductSupply(int idProductSupply, Timestamp timestamp, int state,
			String name) {
		this.idProductSupply = idProductSupply;
		this.timestamp = timestamp;
		this.state = state;
		this.name = name;
	}

	/**
	 * GETTERS AND SETTERS
	 */

	/**
	 * @return the idProductSupply
	 */
	public int getIdProductSupply() {
		return idProductSupply;
	}

	/**
	 * @param idProductSupply
	 *            the idProductSupply to set
	 */
	public void setIdProductSupply(int idProductSupply) {
		this.idProductSupply = idProductSupply;
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
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idProductSupply;
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
		ProductSupply other = (ProductSupply) obj;
		if (idProductSupply != other.idProductSupply)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return name;
	}
	
	
	
	
}
