package fr.la.jproductbase.dao;

import java.sql.SQLException;
import java.util.List;

import fr.la.jproductbase.metier.Tester;

/**
 * Interface dao de testeur.
 * 
 * @author stc
 * 
 */
public interface TesterDao {
	/**
	 * Recherche un teteur de la base de donn&eacute;es.
	 * 
	 * @param idTester
	 *            Identifiant du testeur.
	 * 
	 * @return Testeur.
	 * 
	 * @throws SQLException
	 */
	public Tester getTester(int idTester);

	/**
	 * Recherche un teteur de la base de donn&eacute;es.
	 * 
	 * @param name
	 *            Nom du testeur.
	 * 
	 * @return Testeur.
	 * 
	 * @throws SQLException
	 */
	public Tester getTester(String name);

	/**
	 * Recherche les teteurs actif de la base de donn&eacute;es.
	 * 
	 * @return Liste des testeurs actifs.
	 * 
	 * @throws SQLException
	 */
	public List<Tester> getActiveTesters();
	
	/**
	 * Recherche tous les teteurs de la base de donn&eacute;es.
	 * 
	 * @return Liste des testeurs actifs.
	 * 
	 * @throws SQLException
	 */
	public List<Tester> getTesters();

	/**
	 * Ajoute un testeur &agrave; la base de donn&eacute;es.
	 * 
	 * @param name
	 *            Nom du testeur.
	 * 
	 * @return Testeur.
	 * 
	 * @throws SQLException 
	 * @throws TesterDaoException 
	 */
	public Tester addTester(String name);
	
	/**
	 * Ajoute un testeur &agrave; la base de donn&eacute;es.
	 * 
	 * @param name
	 *            Nom du testeur.
	 * @param state
	 *            Etat du testeur.
	 * 
	 * @return Testeur.
	 * 
	 * @throws SQLException 
	 * @throws TesterDaoException 
	 */
	public Tester addTester(String name, int state);
	
	/**
	 * Met &agrave; jour un testeur dans la base de donn&eacute;s.
	 * 
	 * @param tester
	 *            Testeur.
	 *            
	 * @throws SQLException
	 * @throws TesterDaoException
	 */
	public void updateTester(Tester tester);
}
