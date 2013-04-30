package fr.la.jproductbase.dao;

import java.sql.Date;
import java.sql.SQLException;

import fr.la.jproductbase.metier.ProductionFailureReport;
import fr.la.jproductbase.metier.FailureReportComment;

/**
 * Classe dao de commentaire de rapport de d&eacute;fauts.
 * 
 * @author stc
 * 
 */
public interface FailureReportCommentDao {
	/**
	 * Recherche le commentaire d'un rapport d&eacute;faut de de la base de
	 * donn&eacute;es &agrave; partir de l'identifiant du rapport de
	 * d&eacute;faut.
	 * 
	 * @param failureReport
	 *            Rapport de d&eacute;faut.
	 * 
	 * @return FailureReportComment.
	 * 
	 * @throws SQLException
	 */
	public FailureReportComment getFailureReportComment(ProductionFailureReport failureReport);

	/**
	 * Ajoute un commentaire de rapport de d&eacute;faut dans la base de
	 * donn&eacute;s.
	 * 
	 * @param failureReport
	 *            Rapport de d&eacute;faut concern&eacute;.
	 * @param comment
	 *            Commentaire.
	 * @param commentDate
	 *            Date du commentaire.
	 * 
	 * @return Commentaire de rapport de d&eacute;fauts.
	 * 
	 * @throws SQLException
	 * @throws FailureReportCommentDaoException
	 */
	public FailureReportComment addFailureReportComment(ProductionFailureReport failureReport, String comment, Date commentDate);

	/**
	 * Ajoute un commentaire &grave; un rapport de d&eacute;faut dans la base de
	 * donn&eacute;s.
	 * 
	 * @param failureReport
	 *            Rapport de d&eacute;faut auquel est li&eacute; le commentaire.
	 * @param comment
	 *            Commentaire.
	 * 
	 * @return Commentaire de rapport de d&eacute;fauts.
	 * 
	 * @throws SQLException
	 * @throws FailureReportCommentDaoException
	 */
	public FailureReportComment addFailureReportComment(ProductionFailureReport failureReport, String comment);

	/**
	 * Met &agrave; jour le commentaire d'un rapport de d&eacute;faut dans la
	 * base de donn&eacute;s.
	 * 
	 * @param failureReport
	 *            Rapport de d&eacute;faut auquel est li&eacute; le commentaire.
	 * @param comment
	 *            Commentaire.
	 * 
	 * @throws SQLException
	 * @throws FailureReportCommentDaoException 
	 */
	public void updateFailureReportComment(ProductionFailureReport failureReport, String comment);

	/**
	 * Supprimme le commentaire d'un rapport de d&eacute;fauts dans la base de
	 * donn&eacute;s.
	 * 
	 * @param failureReport
	 *            Rapport de d&eacute;fauts auquel est li&eacute; le
	 *            commentaire.
	 * 
	 * @throws SQLException
	 */
	public void removeFailureReportComment(ProductionFailureReport failureReport);
}
