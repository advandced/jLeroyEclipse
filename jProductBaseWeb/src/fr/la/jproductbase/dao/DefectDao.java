package fr.la.jproductbase.dao;

import java.sql.SQLException;
import java.util.List;

import fr.la.jproductbase.metier.Defect;
import fr.la.jproductbase.metier.TesterReport;

/**
 * Interface dao de d&eacute;faut de rapport de test.
 * 
 * @author stc
 * 
 */
public interface DefectDao {
	/**
	 * Recherche les d&eacute;fauts d'un rapport testeur de la base de
	 * donn&eacute;es.
	 * 
	 * @param testerReport
	 *            Rapport de testeur.
	 * 
	 * @return Liste des d&eacute;fauts.
	 * 
	 * @throws SQLException
	 */
	public List<Defect> getDefects(TesterReport testerReport);

	/**
	 * Ajoute un d&eacute;faut de rapport testeur &agrave; la base de
	 * donn&eacute;es.
	 * 
	 * @param defect
	 *            D&eacute;faut.
	 * @param testerReport
	 *            Rapport de testeur.
	 * 
	 * @return D&eacute;faut.
	 * 
	 * @throws SQLException
	 * @throws DefectDaoException
	 */
	public Defect addDefect(Defect defect, TesterReport testerReport);

}
