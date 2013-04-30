package fr.la.jproductbase.dao;

import java.sql.SQLException;
import java.util.List;

import fr.la.jproductbase.metier.AfterSaleReport;
import fr.la.jproductbase.metier.Failure;
import fr.la.jproductbase.metier.ProductionFailureReport;

/**
 * Interface dao de d&eacute;faut.
 * 
 * @author stc
 * 
 */
public interface FailureDao {
	/**
	 * Recherche les d&eacute;fauts d'un rapport de d&eacute;faut de la base de
	 * donn&eacute;es.
	 * 
	 * @param failureReport
	 *            Rapport de d&eacute;faut.
	 * 
	 * @return Liste des d&eacute;fauts.
	 * 
	 * @throws SQLException
	 */
	public List<Failure> getFailures(ProductionFailureReport failureReport);
	
	/**
	 * Recherche les d&eacute;fauts d'un rapport d'intervention SAV de la base de
	 * donn&eacute;es.
	 * 
	 * @param afterSaleReport
	 *            Rapport d'intervention SAV.
	 * 
	 * @return Liste des d&eacute;fauts.
	 * 
	 * @throws SQLException
	 */
	public List<Failure> getFailures(AfterSaleReport afterSaleReport);

	/**
	 * Recherche un d&eacute;faut de la base de donn&eacute;es &agrave; partir
	 * de son identifiant.
	 * 
	 * @param idFailure
	 *            Identifiant du d&eacute;faut.
	 * 
	 * @return D&eacute;faut.
	 * 
	 * @throws SQLException
	 */
	public Failure getFailure(int idFailure);

	/**
	 * Ajouter un d&eacute;faut dans la base de donn&eacute;s.
	 * 
	 * @param failure
	 *            D&eacute;faut.
	 * @param failureReport
	 *            Rapport de d&eacute;fauts auquel appartient le d&eacute;faut.
	 * 
	 * @return D&eacute;faut.
	 * 
	 * @throws SQLException
	 * @throws FailureDaoException
	 */
	public Failure addFailure(Failure failure, ProductionFailureReport failureReport);
	
	/**
	 * Ajouter une intervention SAV dans la base de donn&eacute;s.
	 * 
	 * @param failure
	 *            D&eacute;faut.
	 * @param failureReport
	 *            Rapport de d&eacute;fauts auquel appartient le d&eacute;faut.
	 * 
	 * @return D&eacute;faut.
	 * 
	 * @throws SQLException
	 * @throws FailureDaoException
	 */
	public Failure addFailure(Failure failure, AfterSaleReport afterSaleReport);

	/**
	 * Mettre &agrave; jour un d&eacute;faut dans la base de donn&eacute;s.
	 * 
	 * @param failure
	 *            D&eacute;faut.
	 * @param failureReport
	 *            Rapport de d&eacute;fauts auquel appartient le d&eacute;faut.
	 * 
	 * @throws SQLException
	 * @throws FailureDaoException
	 */
	public void updateFailure(Failure failure, ProductionFailureReport failureReport);
	
	/**
	 * Mettre &agrave; jour une intervention SAV dans la base de donn&eacute;s.
	 * 
	 * @param failure
	 *            D&eacute;faut.
	 * 
	 * @throws SQLException
	 * @throws FailureDaoException
	 */
	public void updateFailure(Failure failure);

	/**
	 * Supprimer un d&eacute;faut d'un rapport de d&eacute;fauts de la base de
	 * donn&eacute;es.
	 * 
	 * @param failure
	 *            D&eacute;faut &grave; supprimer.
	 * 
	 * @throws SQLException
	 */
	public void removeFailure(Failure failure);
}