package fr.la.jproductbase.dao;

import java.sql.SQLException;
import java.util.List;

import fr.la.jproductbase.metier.ElementChanged;
import fr.la.jproductbase.metier.Failure;

/**
 * Interface dao d'&eacute;l&eacute;ment chang&eacute;.
 * 
 * @author stc
 * 
 */
public interface ElementChangedDao {
	/**
	 * Recherche les &eacute;l&eacute;ments chang&eacute;s pour un
	 * d&eacute;faut.
	 * 
	 * @param failure
	 *            D&eacute;faut concern&eacute;
	 * 
	 * @return Liste de &eacute;l&eacute;ments chang&eacute;s.
	 * 
	 * @throws SQLException
	 */
	public List<ElementChanged> getElementsChanged(Failure failure)
			throws SQLException;

	/**
	 * Ajoute un &eacute;l&eacute;ment chang&eacute; &agrave; la base de
	 * donn&eacute;es.
	 * 
	 * @param ElementChanged
	 *            El&eacute;ment chang&eacute;.
	 * @param failure
	 *            D&eacute;faut concern&eacute;
	 * 
	 * @return El&eacute;ment chang&eacute;.
	 * 
	 * @throws SQLException
	 * @throws ElementChangedDaoException
	 */
	public ElementChanged addElementChanged(ElementChanged ElementChanged,
			Failure failure) throws SQLException, ElementChangedDaoException;

	/**
	 * Mettre &agrave; jour un &eacute;l&eacute;ment d'un d&eacute;faut dans la
	 * base de donn&eacute;s.
	 * 
	 * @param elementChanged
	 *            El&eacute;ment chang&eacute;.
	 * @param failure
	 *            D&eacute;fauts auquel appartient l'&eacute;l&eacute;ment.
	 * 
	 * @throws SQLException
	 * @throws ElementChangedDaoException
	 */
	public void updateElementChanged(ElementChanged elementChanged,
			Failure failure) throws SQLException, ElementChangedDaoException;

	/**
	 * Supprimer un &eacute;l&eacute;ment d'un d&eacute;faut de la base de
	 * donn&eacute;es.
	 * 
	 * @param elementChanged
	 *            El&eacute;ment &agrave; supprim&eacute;.
	 * 
	 * @throws SQLException
	 */
	public void removeElementChanged(ElementChanged elementChanged)
			throws SQLException;
}
