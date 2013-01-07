package fr.la.jproductbase.dao;

import java.io.IOException;
import java.util.Date;
import java.sql.SQLException;
import java.util.List;

import fr.la.configfilereader.ConfigFileReaderException;
import fr.la.jproductbase.metier.AfterSaleReport;
import fr.la.jproductbase.metier.ApparentCause;
import fr.la.jproductbase.metier.Product;

/**
 * Interface dao de rapport d'intervention SAV.
 * 
 * @author stc
 * 
 */
public interface AfterSaleReportDao {
	/**
	 * Recherche les rapport de d&eacute;fauts de la base de donn&eacute;es.
	 * 
	 * @return Liste des rapports de d&eacute;fauts.
	 * 
	 * @throws SQLException
	 * @throws IOException
	 * @throws ConfigFileReaderException
	 */
	public List<AfterSaleReport> listAfterSaleReport() throws SQLException;

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
	public List<AfterSaleReport> listAfterSaleReport(Product product)
			throws SQLException, ConfigFileReaderException, IOException;

	/**
	 * Recherche les rapport de d&eacute;fauts d'un produit de la base de
	 * donn&eacute;es.
	 * 
	 * @param idproduct
	 *           idProduit concern&eacute;.
	 * 
	 * @return Liste des rapports de d&eacute;fauts du produit.
	 * 
	 * @throws SQLException
	 * @throws IOException
	 * @throws ConfigFileReaderException
	 */
	public List<AfterSaleReport> listAfterSaleReport(int idproduct)
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
	 */
	public List<AfterSaleReport> listAfterSaleReport(Date fromDate, Date toDate)
			throws SQLException, ConfigFileReaderException, IOException;

	/**
	 * Recherche un rapport de d&eacute;faut de la base de donn&eacute;es.
	 * 
	 * @param idFailureReport
	 *            Identifiant du rapport de d&eacute;faut.
	 * 
	 * @return Rapport de d&eacute;fauts.
	 * 
	 * @throws SQLException
	 * @throws IOException
	 * @throws ConfigFileReaderException
	 */
	public AfterSaleReport getAfterSaleReport(int idAfterSaleReport)
			throws SQLException, ConfigFileReaderException, IOException;

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
	 * @throws FailureReportDaoException
	 * @throws IOException
	 * @throws ConfigFileReaderException
	 */
	public AfterSaleReport addAfterSaleReport(Date arrivalDate, String ecsNumber,
			String ncNature, Date firstAnalyseDate, Date materialInfoDate,
			Date reparationDate, Date qualityControlDate, Date expeditionDate, 
			int functionnalTest, int visualControl, String asker,
			String intervenant, String InterventionSheetLink, String comment, 
			ApparentCause apparentCause, String majorIndexIn,
			String majorIndexOut, Product product)
			throws SQLException, AfterSaleReportDaoException,
			ConfigFileReaderException, IOException;

	/**
	 * Met &agrave; jour une intervention SAV dans la base de
	 * donn&eacute;es.
	 * 
	 * @param afterSaleReport
	 *            intervention SAV.
	 * @param toutes les infos d'une intervention
	 * 
	 * @return afterSaleReport
	 * 			  l'intervention SAV modifi&eacute; 
	 * 
	 * @throws SQLException
	 * @throws FailureReportDaoException
	 */
	public AfterSaleReport updateAfterSaleReport(AfterSaleReport afterSaleReport,
			Date arrivalDate, String ecsNumber,
			String ncNature, Date firstAnalyseDate, Date materialInfoDate,
			Date reparationDate, Date qualityControlDate, Date expeditionDate, 
			int functionnalTest, int visualControl, String asker,
			String intervenant, String InterventionSheetLink, String comment, 
			ApparentCause apparentCause, String majorIndexIn,
			String majorIndexOut, Product product)
			throws SQLException, AfterSaleReportDaoException;
	
	/**
	 * Met &agrave; jour une intervention SAV dans la base de
	 * donn&eacute;es.
	 * 
	 * @param afterSaleReport
	 *            intervention SAV.
	 * @return afterSaleReport
	 * 			  l'intervention SAV modifi&eacute; 
	 * 
	 * @throws SQLException
	 * @throws FailureReportDaoException
	 */	
	public AfterSaleReport updateAfterSaleReport(AfterSaleReport afterSaleReport)
			throws SQLException, AfterSaleReportDaoException;
	
	public void updateAfterSaleReportQualityControl(AfterSaleReport AfterSaleReport)
			throws SQLException, AfterSaleReportDaoException;

	public List<AfterSaleReport> SelectAfterSaleReportValidControl()
			throws SQLException, AfterSaleReportDaoException;

	public List<AfterSaleReport> SelectAfterSaleReportExpedSAV()
			throws SQLException, AfterSaleReportDaoException;
	
	public void updateAfterSaleReportExpedSAV(AfterSaleReport AfterSaleReport)
			throws SQLException, AfterSaleReportDaoException;
	
	public List<AfterSaleReport> ListDevisRepa() 
			throws SQLException, ConfigFileReaderException, IOException;
}
