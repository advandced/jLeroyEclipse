package fr.la.jproductbase.dao;

import java.sql.SQLException;
import java.util.List;

import fr.la.jproductbase.metier.ApparentCauseCustomer;

/**
 * Interface dao de cause probable client.
 * 
 * @author rmo
 * 
 */
public interface ApparentCauseCustomerDao {
	/**
	 * Recherche les causes probables client de la base de donn&eacute;es.
	 * 
	 * @return Liste des causes probables client.
	 * 
	 * @throws SQLException
	 */
	public List<ApparentCauseCustomer> getApparentCausesCustomer()
			throws SQLException;

	/**
	 * Recherche une cause probable client de la base de donn&eacute;es &agrave;
	 * partir de son identifiant.
	 * 
	 * @param idApparentCauseCustomer
	 *            Identifiant de la cause probable client.
	 * 
	 * @return Cause probable client.
	 * 
	 * @throws SQLException
	 */
	public ApparentCauseCustomer getApparentCauseCustomer(
			int idApparentCauseCustomer) throws SQLException;

	/**
	 * Ajouter une cause probable client dans la base de donn&eacute;s.
	 * 
	 * @param apparentCauseCustomer
	 *            Cause probable client.
	 * 
	 * @return Cause probable client.
	 * 
	 * @throws SQLException
	 * @throws ApparentCauseDaoException
	 */
	public ApparentCauseCustomer addApparentCauseCustomer(
			ApparentCauseCustomer apparentCauseCustomer) throws SQLException,
			ApparentCauseDaoException;

	/**
	 * Mettre &agrave; jour une cause probable client dans la base de
	 * donn&eacute;s.
	 * 
	 * @param apparentCauseCustomer
	 *            Cause probable client.
	 * 
	 * @throws SQLException
	 * @throws ApparentCauseDaoException
	 */
	public void updateApparentCauseCustomer(
			ApparentCauseCustomer apparentCauseCustomer) throws SQLException,
			ApparentCauseDaoException;

	/**
	 * Supprimer une cause probable client d'un rapport de d&eacute;fauts de la
	 * base de donn&eacute;es.
	 * 
	 * @param apparentCauseCustomer
	 *            Cause probable client &grave; supprimer.
	 * 
	 * @throws SQLException
	 */
	public void removeApparentCauseCustomer(
			ApparentCauseCustomer apparentCauseCustomer) throws SQLException;

	/**
	 * Recherche les causes probables client actives de la base de
	 * donn&eacute;es.
	 * 
	 * @return Liste des causes probables client.
	 * 
	 * @throws SQLException
	 */
	List<ApparentCauseCustomer> getActiveApparentCausesCustomer()
			throws SQLException;
}