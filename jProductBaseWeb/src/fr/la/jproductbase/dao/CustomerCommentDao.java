package fr.la.jproductbase.dao;

import java.sql.SQLException;

import fr.la.jproductbase.metier.CustomerComment;
import fr.la.jproductbase.metier.ProductionFailureReport;

/**
 * Classe dao de commentaires client.
 * 
 * @author stc
 * 
 */
public interface CustomerCommentDao {
	/**
	 * Recherche le commentaire client d'un rapport de d&eacute;faut de la base
	 * de donn&eacute;es &agrave; partir de l'identifiant du rapport de
	 * d&eacute;faut.
	 * 
	 * @param failureReport
	 *            Rapport de d&eacute;faut auquel est attach&eacute; le
	 *            commentaire client.
	 * 
	 * @return CustomerComment.
	 * 
	 * @throws SQLException
	 */
	public CustomerComment getCustomerComment(ProductionFailureReport failureReport)
			throws SQLException;

	/**
	 * Ajoute un commentaire client &agrave; un rapport de d&eacute;fauts dans
	 * la base de donn&eacute;s.
	 * 
	 * @param failureReport
	 *            Rapport de d&eacute;fauts auquel est li&eacute; le commentaire
	 *            du client.
	 * @param customerComment
	 *            Commentaire client.
	 * 
	 * @return Commentaire client du rapport de d&eacute;fauts.
	 * 
	 * @throws SQLException
	 * @throws CustomerCommentDaoException
	 */
	public CustomerComment addCustomerComment(ProductionFailureReport failureReport,
			String customerComment) throws SQLException,
			CustomerCommentDaoException;

	/**
	 * Met &agrave; jour le commentaire client du rapport de d&eacute;fauts dans
	 * la base de donn&eacute;s.
	 * 
	 * @param failureReport
	 *            Rapport de d&eacute;fauts auquel est li&eacute; le commentaire
	 *            du client.
	 * @param customerComment
	 *            Commentaire client.
	 * 
	 * @throws SQLException
	 */
	public void updateCustomerComment(ProductionFailureReport failureReport,
			String customerComment) throws SQLException;

	/**
	 * Supprimme le commentaire client du rapport de d&eacute;fauts dans la base
	 * de donn&eacute;s.
	 * 
	 * @param failureReport
	 *            Rapport de d&eacute;fauts auquel est li&eacute; le commentaire
	 *            du client.
	 * 
	 * @throws SQLException
	 */
	public void removeCustomerComment(ProductionFailureReport failureReport)
			throws SQLException;
}
