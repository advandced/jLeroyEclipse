package fr.la.jproductbase.metier;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Classe m&eacute;tier de cause probable client.
 * 
 * @author rmo
 * 
 */
public class ApparentCauseCustomer implements Serializable {

	private static final long serialVersionUID = 1L;

	private int idApparentCauseCustomer;
	private Timestamp timestamp;
	private int state;
	private String description;

	/**
	 * Cr&eacute;er une cause probable client.
	 * 
	 * @param idApparentCauseCustomer
	 *            Identifiant de la cause probable client.
	 * @param timestamp
	 *            Horadatage de l'enregistrement.
	 * @param state
	 *            Etat de l'enregistrement.
	 * @param description
	 *            Description.
	 */
	public ApparentCauseCustomer(int idApparentCauseCustomer, Timestamp timestamp, int state,
			String description) {
		this.idApparentCauseCustomer = idApparentCauseCustomer;
		this.timestamp = timestamp;
		this.state = state;
		this.description = description;
	}
	
	public ApparentCauseCustomer(){}

	/**
	 * GETTERS AND SETTERS
	 */

	public int getIdApparentCauseCustomer() {
		return idApparentCauseCustomer;
	}

	public void setIdApparentCauseCustomer(int idApparentCauseCustomer) {
		this.idApparentCauseCustomer = idApparentCauseCustomer;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idApparentCauseCustomer;
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
		ApparentCauseCustomer other = (ApparentCauseCustomer) obj;
		if (idApparentCauseCustomer != other.idApparentCauseCustomer)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return this.description;
	}

	
	
	

}
