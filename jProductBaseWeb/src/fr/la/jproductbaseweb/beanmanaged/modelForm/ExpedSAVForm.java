package fr.la.jproductbaseweb.beanmanaged.modelForm;

import fr.la.jproductbase.dao.AfterSaleReportDaoException;
import fr.la.jproductbase.dao.ElementChangedDaoException;
import fr.la.jproductbase.dao.FailureDaoException;
import fr.la.jproductbase.metier.AfterSaleCom;
import fr.la.jproductbase.service.ServiceInterface;
import fr.la.jproductbaseweb.beanmanaged.exception.ExpedSAVException;
import java.io.Serializable;
import java.sql.SQLException;
import javax.naming.NamingException;

public class ExpedSAVForm implements Serializable {

    private static final long serialVersionUID = 1L;
    private AfterSaleCom[] listAfterSaleCom;
    private ServiceInterface moduleGlobal = new ServiceInterface();

    public AfterSaleCom[] getListAfterSaleCom() {
        return listAfterSaleCom;
    }

    public void setListAfterSaleCom(AfterSaleCom[] listAfterSaleCom) {
        this.listAfterSaleCom = listAfterSaleCom;
    }

    public ExpedSAVForm(AfterSaleCom[] _listAfterSaleCom) throws SQLException,
            AfterSaleReportDaoException, FailureDaoException,
            ElementChangedDaoException, ExpedSAVException, NamingException {
        this.listAfterSaleCom = _listAfterSaleCom;
        int _error = 0;
        for (AfterSaleCom a : this.listAfterSaleCom) {
            if (a.getAfterSaleReport().getExpeditionDate() != null) {
                this.moduleGlobal.updateAfterSaleReportExpedSAV(a
                        .getAfterSaleReport());
            } else {
                _error++;
            }
        }
        if (_error != 0) {
            throw new ExpedSAVException("Date d'expedition manquante.");
        }
    }
}