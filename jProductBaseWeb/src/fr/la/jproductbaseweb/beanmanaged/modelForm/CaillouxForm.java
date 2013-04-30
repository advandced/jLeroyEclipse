package fr.la.jproductbaseweb.beanmanaged.modelForm;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import fr.la.configfilereader.ConfigFileReaderException;
import fr.la.jproductbase.metier.AfterSaleCom;
import fr.la.jproductbase.service.ServiceInterface;
import fr.la.jproductbaseweb.beanmanaged.exception.CaillouxException;
import java.io.Serializable;

public class CaillouxForm implements Serializable {

    private static final long serialVersionUID = 1L;
    private ServiceInterface moduleGlobal = ServiceInterface.getInstance();
    private Date debut;
    private Date fin;
    List<AfterSaleCom> listaftersalecom;

    public CaillouxForm(Date debut, Date fin) {
        this.debut = debut;
        this.fin = fin;
    }

    public List<AfterSaleCom> search() throws SQLException,
            ConfigFileReaderException, IOException, CaillouxException {
        if (this.debut != null && this.fin != null) {
            this.listaftersalecom = moduleGlobal.getRepairDatetoDate(
                    this.debut, this.fin);
        } else {
            throw new CaillouxException("Date Manquante");
        }
        if (this.listaftersalecom.size() == 0) {
            throw new CaillouxException("Aucun resultat");
        }
        return this.listaftersalecom;
    }
}