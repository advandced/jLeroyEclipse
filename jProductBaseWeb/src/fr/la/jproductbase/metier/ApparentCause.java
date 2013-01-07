package fr.la.jproductbase.metier;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Classe m&eacute;tier de cause probable.
 * 
 * @author rmo
 * 
 */
public class ApparentCause implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int idApparentCause;
	private Timestamp timestamp;
	private int state;
	private String description;
	private ApparentCauseCustomer apparentCauseCustomer;

	/**
	 * Cr&eacute;er une cause probable.
	 * 
	 * @param idApparentCause
	 *            Identifiant de la cause probable.
	 * @param timestamp
	 *            Horadatage de l'enregistrement.
	 * @param state
	 *            Etat de l'enregistrement.
	 * @param description
	 *            Description.
	 */
	public ApparentCause(int idApparentCause, Timestamp timestamp, int state,
			String description, ApparentCauseCustomer apparentCauseCustomer) {
		this.idApparentCause = idApparentCause;
		this.timestamp = timestamp;
		this.state = state;
		this.description = description;
		this.apparentCauseCustomer = apparentCauseCustomer;
	}
	public ApparentCause(){}

	/**
	 * GETTERS AND SETTERS
	 */

	public int getIdApparentCause() {
		return idApparentCause;
	}

	public void setIdApparentCause(int idApparentCause) {
		this.idApparentCause = idApparentCause;
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
	
	public ApparentCauseCustomer getApparentCauseCustomer() {
		return apparentCauseCustomer;
	}

	public void setApparentCauseCustomer(ApparentCauseCustomer apparentCauseCustomer) {
		this.apparentCauseCustomer = apparentCauseCustomer;
	}

	
	@Override
	public  String toString() {
		return this.description;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idApparentCause;
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
		ApparentCause other = (ApparentCause) obj;
		if (idApparentCause != other.idApparentCause)
			return false;
		return true;
	}
	
	
	
	

}
