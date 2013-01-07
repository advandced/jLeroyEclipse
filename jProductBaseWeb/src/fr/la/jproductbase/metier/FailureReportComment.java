package fr.la.jproductbase.metier;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

/**
 * Classe m&eacute; de commentaire de rapport de d&eacute;fauts.
 * 
 * @author stc
 *
 */
public class FailureReportComment implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int idFailureReportComment;
	private Timestamp timestamp;
	private String comment;
	private Date commentDate;
	
    /**
     * Cr&eacute;er un commentaire de rapport de d&eacute;fauts.
     * 
     * @param idFailureReportComment Identifiant du commentaire.
     * @param timestamp Horadatage de l'enregistrement.
     * @param comment Commentaire.
     * @param commentDate Date du commentaire. 
     */
	public FailureReportComment(int idFailureReportComment, Timestamp timestamp, String comment, Date commentDate) {
		this.idFailureReportComment = idFailureReportComment;
		this.timestamp = timestamp;
		this.comment = comment;
		this.commentDate = commentDate;
	}
	
	public FailureReportComment(){}

	/**
	 * GETTERS AND SETTERS 
	 */
	
	/**
	 * @return the idFailureReportComment
	 */
	public int getIdFailureReportComment() {
		return idFailureReportComment;
	}

	/**
	 * @param idFailureReportComment the idFailureReportComment to set
	 */
	public void setIdFailureReportComment(int idFailureReportComment) {
		this.idFailureReportComment = idFailureReportComment;
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
	 * @return the comment
	 */
	public String getComment() {
		return comment;
	}

	/**
	 * @param comment the comment to set
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}

	/**
	 * @return the commentDate
	 */
	public Date getCommentDate() {
		return commentDate;
	}

	/**
	 * @param commentDate the commentDate to set
	 */
	public void setCommentDate(Date commentDate) {
		this.commentDate = commentDate;
	}

	@Override
	public String toString() {
		return "FailureReportComment [idFailureReportComment="
				+ idFailureReportComment + ", timestamp=" + timestamp
				+ ", comment=" + comment + ", commentDate=" + commentDate + "]";
	}
	
	
	
	
}
