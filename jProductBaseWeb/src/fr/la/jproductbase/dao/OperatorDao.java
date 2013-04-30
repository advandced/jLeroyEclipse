package fr.la.jproductbase.dao;

import java.sql.SQLException;
import java.util.List;

import fr.la.jproductbase.metier.Operator;

/**
 * Interface dao d'op&eacute;rateur.
 * 
 * @author stc
 * 
 */
public interface OperatorDao {
	/**
	 * Recherche un op&eacute;rateur de la base de donn&eacute;es &agrave;
	 * partir de son code.
	 * 
	 * @param code
	 *            Code de l'op&eacute;rateur.
	 * 
	 * @return Op&eacute;rateur.
	 * 
	 * @throws SQLException
	 */
	public Operator getOperator(String code);

	/**
	 * Recherche un op&eacute;rateur de la base de donn&eacute;es &agrave;
	 * partir de son identifiant.
	 * 
	 * @param idOperator
	 *            Identifiant de l'op&eacute;rateur.
	 * 
	 * @return Op&eacute;rateur.
	 * 
	 * @throws SQLException
	 */
	public Operator getOperator(int idOperator);

	/**
	 * Recherche les op&eacute;rateurs actifs de la base de donn&eacute;es.
	 * 
	 * @return Op&eacute;rateurs actifs.
	 * 
	 * @throws SQLException
	 */
	public List<Operator> getActiveOperators();

	/**
	 * Recherche les op&eacute;rateurs de la base de donn&eacute;es.
	 * 
	 * @return Op&eacute;rateurs.
	 * 
	 * @throws SQLException
	 */
	public List<Operator> getOperators();

	// TODO s'occuper de la javadoc des 3 nouvelles méthodes
	public Operator addOperator(String lastName, String firstName, String code,	int state);

	public void updateOperator(Operator operatorToUpdate);

	public void deleteOperator(Operator operatorToDelete);
}
