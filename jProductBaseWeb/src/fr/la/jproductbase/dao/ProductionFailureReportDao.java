package fr.la.jproductbase.dao;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import fr.la.configfilereader.ConfigFileReaderException;
import fr.la.jproductbase.metier.ProductionFailureReport;
import fr.la.jproductbase.metier.Product;
import fr.la.jproductbase.metier.TesterReport;

/**
 * Interface dao de rapport de d&eacute;fauts.
 * 
 * @author stc
 * 
 */
public interface ProductionFailureReportDao {
	/**
	 * Recherche les rapport de d&eacute;fauts de la base de donn&eacute;es.
	 * 
	 * @return Liste des rapports de d&eacute;fauts.
	 * 
	 * @throws SQLException
	 * @throws IOException
	 * @throws ConfigFileReaderException
	 */
	public List<ProductionFailureReport> listProductionFailureReport() throws SQLException,
			ConfigFileReaderException, IOException;

	/**
	 * Recherche les rapport de d&eacute;fauts d'un produit de la base de
	 * donn&eacute;es.
	 * 
	 * @param product
	 *            Produit concern&eacute;.
	 * 
	 * @return Liste des rapports de d&eacute;fauts du produit.
	 * 
	 * @throws SQLException
	 * @throws IOException
	 * @throws ConfigFileReaderException
	 */
	public List<ProductionFailureReport> listProductionFailureReport(Product product)
			throws SQLException, ConfigFileReaderException, IOException;

	/**
	 * Recherche les rapport de d&eacute;fauts de la base de donn&eacute;es dont
	 * la date d'enregistrement en comprise entre les dates fournies.
	 * 
	 * @param fromDate
	 *            Date de d&eacute;but.
	 * @param toDate
	 *            Date de fin.
	 * 
	 * @return Liste des rapports de d&eacute;fauts.
	 * 
	 * @throws SQLException
	 * @throws IOException
	 * @throws ConfigFileReaderException
	 * @throws GestRapportDefaultsException 
	 */
	public List<ProductionFailureReport> listProductionFailureReport(Date fromDate, Date toDate)
			throws SQLException, ConfigFileReaderException, IOException;

	/**
	 * Recherche les rapport de d&eacute;fauts non cl&ocirc;tur&eacute; de la
	 * base de donn&eacute;es.
	 * 
	 * @return Liste des rapports de d&eacute;fauts non cl&ocirc;tur&eacute;s.
	 * 
	 * @throws SQLException
	 * @throws IOException
	 * @throws ConfigFileReaderException
	 */
	public List<ProductionFailureReport> unclosedProductionFailureReport() throws SQLException,
			ConfigFileReaderException, IOException;

	/**
	 * Recherche un rapport de d&eacute;faut de la base de donn&eacute;es.
	 * 
	 * @param idProductionFailureReport
	 *            Identifiant du rapport de d&eacute;faut.
	 * 
	 * @return Rapport de d&eacute;fauts.
	 * 
	 * @throws SQLException
	 * @throws IOException
	 * @throws ConfigFileReaderException
	 */
	public ProductionFailureReport getProductionFailureReport(int idProductionFailureReport)
			throws SQLException, ConfigFileReaderException, IOException;

	/**
	 * Recherche un rapport de d&eacute;faut d'un rapport de testeur de la base
	 * de donn&eacute;es.
	 * 
	 * @param testerReport
	 *            Rapport de testeur.
	 * 
	 * @return Rapport de d&eacute;fauts.
	 * 
	 * @throws SQLException
	 */
	public ProductionFailureReport getProductionFailureReport(TesterReport testerReport)
			throws SQLException;

	/**
	 * Ajouter un rapport de d&eacute;faut dans la base de donn&eacute;s.
	 * 
	 * @param registrationDate
	 *            Date de cr&eacute;ation du rapport.
	 * @param product
	 *            Produit concern&eacute;.
	 * @param testerReport
	 *            Rapport de test qui a g&eacute;n&eacute;r&eacute; ce rapport.
	 * @param failureCode
	 *            Code d&eacute;faut du rapport.
	 * 
	 * @return Rapport de d&eacute;fauts.
	 * 
	 * @throws SQLException
	 * @throws ProductionFailureReportDaoException
	 * @throws IOException
	 * @throws ConfigFileReaderException
	 */
	public ProductionFailureReport addProductionFailureReport(Date registrationDate,
			Product product, TesterReport testerReport, String failureCode)
			throws SQLException, ProductionFailureReportDaoException,
			ConfigFileReaderException, IOException;

	/**
	 * Met &agrave; jour un rapport de d&eacute;faut dans la base de
	 * donn&eacute;s.
	 * 
	 * @param failureReport
	 *            Rapport de d&eacute;faut.
	 * @param registrationDate
	 *            Date de cr&eacute;ation du rapport.
	 * @param product
	 *            Produit concern&eacute;.
	 * @param failureCode
	 *            Code d&eacute;faut du rapport.
	 * 
	 * @throws SQLException
	 * @throws ProductionFailureReportDaoException
	 */
	public void updateProductionFailureReport(ProductionFailureReport failureReport,
			java.sql.Date registrationDate, Product product, String failureCode)
			throws SQLException, ProductionFailureReportDaoException;

	/**
	 * Cl&ocirc;turer un rapport de d&eacute;faut dans la base de donn&eacute;s.
	 * 
	 * @param failureReport
	 *            Rapport de d&eacute;faut.
	 * 
	 * @throws SQLException
	 */
	public void closeProductionFailureReport(ProductionFailureReport failureReport)
			throws SQLException;
}
