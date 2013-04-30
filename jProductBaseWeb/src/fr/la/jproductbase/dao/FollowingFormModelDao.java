package fr.la.jproductbase.dao;

import fr.la.jproductbase.metier.FollowingFormModel;
import java.sql.SQLException;
import java.util.List;

/**
 * Interface dao de mod&eacute;le de fiche suiveuse.
 *
 * @author stc
 *
 */
public interface FollowingFormModelDao {

    /**
     * Recherche un mod&eacute;le de fiche suiveuse de la base de
     * donn&eacute;es.
     *
     * @param idFollowingFormModel Identifiant.
     *
     * @return Mod&eacute;le de fiche suiveuse.
     *
     * @throws SQLException
     */
    public FollowingFormModel getFollowingFormModel(int idFollowingFormModel);

    /**
     * Recherche les mod&eacute;les de fiche suiveuse actifs de la base de
     * donn&eacute;es.
     *
     * @return Liste des mod&eacute;le de fiche suiveuse.
     *
     * @throws SQLException
     */
    public List<FollowingFormModel> getAllActiveFollowingFormModel();

    public List<FollowingFormModel> getAllFollowingFormModel();

    public void addFollowingFormModel(FollowingFormModel followingformmodel);

    public void deleteFollowingFormModel(int id);

    public void updateFollowingFormModel(FollowingFormModel followingformmodel);
}
