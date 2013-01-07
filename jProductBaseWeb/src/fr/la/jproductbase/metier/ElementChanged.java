package fr.la.jproductbase.metier;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Classe m&eacute;tier de l'&eacute;l&eacute;ment chang&eacute;.
 * 
 * @author stc
 *
 */
public class ElementChanged implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int idElementChanged;
	private Timestamp timestamp;
	private String code;

    /**
     * Cr&eacute;er un &eacute;l&eacute;ment chang&eacute;.
     * 
     * @param code Code de l'&eacute;l&eacute;ment.
     */
	public ElementChanged(String code) {
		this.code = code;
	}

    /**
     * Cr&eacute;er un &eacute;l&eacute;ment chang&eacute;.
     * 
     * @param idElementChanged Identifiant de l'&eacute;l&eacute;ment.
     * @param timestamp Horodatage de l'enregistrement.
     * @param code Code de l'&eacute;l&eacute;ment.
     */
	public ElementChanged(int idElementChanged, Timestamp timestamp,
			String code) {
		this(code);
		this.idElementChanged = idElementChanged;
		this.timestamp = timestamp;
	}

	/**
	 * GETTERS AND SETTERS 
	 */
	
	/**
	 * @return the idElementChanged
	 */
	public int getIdElementChanged() {
		return idElementChanged;
	}

	/**
	 * @param idElementChanged the idElementChanged to set
	 */
	public void setIdElementChanged(int idElementChanged) {
		this.idElementChanged = idElementChanged;
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

	@Override
	public String toString() {
		return  code ;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
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
		ElementChanged other = (ElementChanged) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		return true;
	}
	
	
	
}
