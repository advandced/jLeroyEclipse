package fr.la.jproductbase.dao;

import java.sql.SQLException;
import java.util.List;

import fr.la.jproductbase.metier.ApparentCause;

/**
 * Interface dao de cause probable.
 * 
 * @author rmo
 * 
 */
public interface ApparentCauseDao {
	/**
	 * Recherche les causes probables de la base de
	 * donn&eacute;es.
	 * 
	 * @return Liste des causes probables.
	 * 
	 * @throws SQLException
	 */
	public List<ApparentCause> getApparentCauses();

	/**
	 * Recherche une cause probable de la base de donn&eacute;es &agrave; partir
	 * de son identifiant.
	 * 
	 * @param idApparentCause
	 *            Identifiant de la cause probable.
	 * 
	 * @return Cause probable.
	 * 
	 * @throws SQLException
	 */
	public ApparentCause getApparentCause(int idApparentCause);

	/**
	 * Ajouter une cause probable dans la base de donn&eacute;s.
	 * 
	 * @param apparentCause
	 *            Cause probable.
	 * 
	 * @return Cause probable.
	 * 
	 * @throws SQLException
	 * @throws ApparentCauseDaoException
	 */
	public ApparentCause addApparentCause(ApparentCause apparentCause);

	/**
	 * Mettre &agrave; jour une cause probable dans la base de donn&eacute;s.
	 * 
	 * @param apparentCause
	 *            Cause probable.
	 *            
	 * @throws SQLException
	 * @throws ApparentCauseDaoException
	 */
	public void updateApparentCause(ApparentCause apparentCause);

	/**
	 * Supprimer une cause probable d'un rapport de d&eacute;fauts de la base de
	 * donn&eacute;es.
	 * 
	 * @param apparentCause
	 *            Cause probable &grave; supprimer.
	 * 
	 * @throws SQLException
	 */
	public void removeApparentCause(ApparentCause apparentCause);
}