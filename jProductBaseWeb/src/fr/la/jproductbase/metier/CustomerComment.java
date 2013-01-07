package fr.la.jproductbase.metier;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Classe m&eacute;tier de commentaires client.
 * 
 * @author stc
 *
 */
public class CustomerComment implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int idCustomerComment;
	private Timestamp timestamp;
	private String comment;

	/**
	 * Cr&eacute;er un commentaire client.
	 * 
	 * @param idCustomerComment
	 *            Identifiant du commentaire.
	 * @param timestamp
	 *            Horodatage de l'enregistrement.
	 * @param comment
	 *            Commentaire.
	 */
	public CustomerComment(String comment) {
		this.comment = comment;
	}

	public CustomerComment(int idCustomerComment, Timestamp timestamp,
			String comment) {
		this.idCustomerComment = idCustomerComment;
		this.timestamp = timestamp;
		this.comment = comment;
	}

	/**
	 * GETTERS AND SETTERS
	 */

	/**
	 * @return the idCustomerComment
	 */
	public int getIdCustomerComment() {
		return idCustomerComment;
	}

	/**
	 * @param idCustomerComment
	 *            the idCustomerComment to set
	 */
	public void setIdCustomerComment(int idCustomerComment) {
		this.idCustomerComment = idCustomerComment;
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
	 * @return the comment
	 */
	public String getComment() {
		return comment;
	}

	/**
	 * @param comment
	 *            the comment to set
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}
}
