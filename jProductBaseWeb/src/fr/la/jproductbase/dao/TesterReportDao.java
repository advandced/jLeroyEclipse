package fr.la.jproductbase.dao;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import fr.la.jproductbase.metier.ProductionFailureReport;
import fr.la.jproductbase.metier.Product;
import fr.la.jproductbase.metier.TestType;
import fr.la.jproductbase.metier.Tester;
import fr.la.jproductbase.metier.TesterReport;

/**
 * Classe dao de rapport de test.
 * 
 * @author stc
 * 
 */
public interface TesterReportDao {
	/**
	 * Recherche un rapport de test de la base de donn&eacute;es.
	 * 
	 * @param idTesterReport
	 *            Identifiant du rapport de test.
	 * 
	 * @return Rapport de test.
	 * 
	 * @throws SQLException
	 */
	public TesterReport getTesterReport(int idTesterReport) throws SQLException;

	/**
	 * Recherche un rapport de test de la base de donn&eacute;es.
	 * 
	 * @param reportDate
	 *            Date du rapport de test.
	 * @param testType
	 *            Type de test.
	 * @param product
	 *            Produit test&eacute;.
	 * 
	 * @return Rapport de test.
	 * 
	 * @throws SQLException
	 */
	public TesterReport getTesterReport(Timestamp reportDate,
			TestType testType, Product product)
			throws SQLException;

	/**
	 * Recherche un rapport de test de la base de donn&eacute;es.
	 * 
	 * @param reportDate
	 *            Date du rapport de test.
	 * @param testType
	 *            Type de test.
	 * @param tester
	 *            Testeur.
	 * @param product
	 *            Produit test&eacute;.
	 * 
	 * @return Rapport de test.
	 * 
	 * @throws SQLException
	 */
	public TesterReport getTesterReport(Timestamp reportDate,
			TestType testType, Tester tester, Product product)
			throws SQLException;

	/**
	 * Ajoute un rapport testeur &agrave; la base de donn&eacute;es.
	 * 
	 * @param testerReport
	 *            Rapport testeur.
	 * 
	 * @return Rapport testeur.
	 * 
	 * @throws SQLException
	 * @throws TesterReportDaoException
	 */
	public TesterReport addTesterReport(TesterReport testerReport)
			throws SQLException, TesterReportDaoException;

	/**
	 * Ajoute un rapport testeur &agrave; la base de donn&eacute;es.
	 * 
	 * @param testType
	 *            Type du rapport de test.
	 * @param tester
	 *            Testeur qui &agrave; g&eacute;n&eacute;r&eacute; le rapport de
	 *            test.
	 * @param date
	 *            Timestamp du rapport de test.
	 * @param operatorCode
	 *            Code de l'p&eacute;rateur qui a valid&eacute; le rapport de
	 *            test.
	 * @param product
	 *            Produit en erreur.
	 * 
	 * @return Rapport de test.
	 * 
	 * @throws SQLException
	 * @throws TesterReportDaoException
	 */
	public TesterReport addTesterReport(TestType testType, Tester tester,
			Timestamp date, String operatorCode, Product product)
			throws SQLException, TesterReportDaoException;

	/**
	 * Ajoute un rapport testeur &agrave; la base de donn&eacute;es.
	 * 
	 * @param state
	 *            Etat de l'enregistrement.
	 * @param testType
	 *            Type du rapport de test.
	 * @param timestamp
	 *            Date et heure du rapport de test.
	 * @param operatorCode
	 *            Code de l'p&eacute;rateur qui a valid&eacute; le rapport de
	 *            test.
	 * @param product
	 *            Produit en erreur.
	 * @param result
	 *            R&eacute;sultat du test.
	 * 
	 * @return Rapport de test.
	 * 
	 * @throws SQLException
	 * @throws TesterReportDaoException
	 */
	public TesterReport addTesterReport(int state, TestType testType,
			Timestamp timestamp, String operatorCode, Product product,
			String result) throws SQLException, TesterReportDaoException;

	/**
	 * Met &agrave; jour un rapport de test &agrave; la base de donn&eacute;es.
	 * 
	 * @param testerReport
	 *            Rapport testeur.
	 * @param testerReportNext
	 *            Rapport testeur suivant.
	 * 
	 * @throws SQLException
	 */
	public void updateTesterReport(TesterReport testerReport,
			TesterReport testerReportNext) throws SQLException;

	/**
	 * Met &agrave; jour un rapport de test &agrave; la base de donn&eacute;es.
	 * 
	 * @param failureReport
	 *            Rapport de d&eacute;faut.
	 * @param testType
	 *            Type du rapport de test.
	 * @param tester
	 *            Testeur qui &agrave; g&eacute;n&eacute;r&eacute; le rapport de
	 *            test.
	 * @param operatorCode
	 *            Code de l'p&eacute;rateur qui a valid&eacute; le rapport de
	 *            test.
	 * @param product
	 *            Produit en erreur.
	 * 
	 * @throws SQLException
	 * @throws TesterReportDaoException 
	 */
	public void updateTesterReport(ProductionFailureReport failureReport,
			TestType testType, Tester tester, java.sql.Date reportDate,
			String operatorCode, Product product) throws SQLException, TesterReportDaoException;

	/**
	 * Liste des rapports des testeurs dans le flux d'un produit dans la base de
	 * donn&eacute;es.
	 * 
	 * @param product
	 *            Produit.
	 * 
	 * @return Liste des rapports des testeurs dans le flux d'un produit.
	 * 
	 * @throws SQLException
	 */
	public List<TesterReport> getInFlowTesterReport(Product product)
			throws SQLException;

	/**
	 * Recherche les rapports de testeurs dont le r&eacute;sultat est "Failed"
	 * et comptant dans le flux.
	 * 
	 * @return Rapports de testeurs.
	 * 
	 * @throws SQLException
	 */
	public List<TesterReport> getTesterReportsFailedInflow()
			throws SQLException;

	/**
	 * Recherche les rapports de testeurs devant apparaitre sur le fiche
	 * suiveuse d'un produit.\nPour cela le rapport doit satisfaire les
	 * conditions suivantes :\n\t* ne pas avoir g&eacute;n&eacute;r&eacute; de
	 * rapport de d&eacute;fauts\n\t* le rapport de d&eacute;fauts
	 * g&eacute;n&eacute;r&eacute; doit avoir un code d&eacute;faut 'U00'\n\t*
	 * &ecirc;tre dans le fux
	 * 
	 * @param product
	 *            Produit.
	 * 
	 * @return Rapports de testeurs.
	 * 
	 * @throws SQLException
	 */
	public List<TesterReport> getTesterReportsFollowingForm(Product product)
			throws SQLException;
}
