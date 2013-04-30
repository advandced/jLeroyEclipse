package fr.la.jproductbaseweb.beanmanaged.modelForm;

import fr.la.configfilereader.ConfigFileReaderException;
import fr.la.jproductbase.metier.AfterSaleCom;
import fr.la.jproductbase.service.ServiceInterface;
import fr.la.jproductbaseweb.beanmanaged.exception.NumCommandeException;
import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import javax.naming.NamingException;

public class NumCommandeForm implements Serializable {

    private static final long serialVersionUID = 1L;
    private ServiceInterface moduleGlobal = ServiceInterface.getInstance();
    private String search;
    private List<AfterSaleCom> listAfterSaleCom;

    public NumCommandeForm(List<AfterSaleCom> listAfterSaleCom) throws NumCommandeException, SQLException, ConfigFileReaderException, IOException, NamingException {
        int cpterror = 0;
        try {
            this.listAfterSaleCom = listAfterSaleCom;
            int _error = 0;
            for (AfterSaleCom a : this.listAfterSaleCom) {
                if (a.getSavOrderDate() != null && a.getSavOrderNumber() != 0) {
                    moduleGlobal.addCmd(a);
                }
                if ((a.getSavOrderDate() == null && a.getSavOrderNumber() != 0)
                        || (a.getSavOrderDate() != null && a
                        .getSavOrderNumber() == 0)) {
                    _error++;
                }
                if (_error != 0) {
                    cpterror += _error;
                }
            }
            if (cpterror != 0) {
                throw new NumCommandeException();
            }
        } catch (NumCommandeException e) {
            throw new NumCommandeException(
                    "Un ou plusieurs champs n'ont pas �t� enregistrer car il n'était pas complet.");
        }
    }

    public NumCommandeForm(String search) throws NumCommandeException {

        this.search = search;

        if (this.search.length() == 0) {
        	throw new NumCommandeException("Champ recherche vide.");
        }

        try {
            Integer.parseInt(this.search);
        } catch (Exception e) {
            throw new NumCommandeException(
                    "La recherche ne fonctionne que avec des chiffres.");
        }
    }
}