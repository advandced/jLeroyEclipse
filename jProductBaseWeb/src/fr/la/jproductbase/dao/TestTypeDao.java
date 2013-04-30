package fr.la.jproductbase.dao;

import java.sql.SQLException;
import java.util.List;

import fr.la.jproductbase.metier.TestType;

/**
 * Classe dao de type de test.
 * 
 * @author stc
 * 
 */
public interface TestTypeDao {
	/**
	 * Recherche les types de test de la base de donn&eacute;es..
	 * 
	 * @return Liste des types de test.
	 * 
	 * @throws SQLException
	 */
	public List<TestType> getTestTypes();

	/**
	 * Recherche un type de test de la base de donn&eacute;es.
	 * 
	 * @param idTestType
	 *            Identifiant du type de test.
	 * 
	 * @return Type de test.
	 * 
	 * @throws SQLException
	 */
	public TestType getTestType(int idTestType);

	/**
	 * Recherche un type de test de la base de donn&eacute;es.
	 * 
	 * @param name
	 *            Nom du type de test.
	 * 
	 * @return Type de test.
	 * 
	 * @throws SQLException
	 */
	public TestType getTestType(String name);
	
	/**
	 * Ajoute un type de test &agrave; la base de donn&eacute;es.
	 * 
	 * @param name
	 *            Nom du type de test.
	 * @param state
	 *            Etat du type de test.      
	 * @param needTester
	 *            Besoin d'un testeur ou non.      
	 * 
	 * @return Type de test.
	 * 
	 * @throws SQLException
	 * @throws TesterDaoException 
	 */
	public TestType addTestType(String name, int state, boolean needTester);
	
	/**
	 * Met &agrave; jour un type de test dans la base de donn&eacute;s.
	 * 
	 * @param testType
	 *            Type de test.
	 *            
	 * @throws SQLException
	 * @throws TesterDaoException
	 */
	public void updateTestType(TestType testType);
	
}
