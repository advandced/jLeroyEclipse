package fr.la.jproductbaseweb.beanmanaged.modelForm;

import fr.la.jproductbase.dao.AfterSaleReportDaoException;
import fr.la.jproductbase.dao.ElementChangedDaoException;
import fr.la.jproductbase.dao.FailureDaoException;
import fr.la.jproductbase.metier.AfterSaleReport;
import fr.la.jproductbase.service.ServiceInterface;
import fr.la.jproductbaseweb.beanmanaged.exception.ValidControlException;
import java.io.Serializable;
import java.sql.SQLException;
import javax.naming.NamingException;

public class ValidControlForm implements Serializable {

    private static final long serialVersionUID = 1L;
    private ServiceInterface moduleGlobal = new ServiceInterface();
    private AfterSaleReport[] listAfterSaleReport;

    public ValidControlForm(AfterSaleReport[] selectedAfterSaleReport)
            throws SQLException, AfterSaleReportDaoException,
            FailureDaoException, ElementChangedDaoException,
            ValidControlException, NamingException {
        this.listAfterSaleReport = selectedAfterSaleReport;
        int _error = 0;
        try {
            for (AfterSaleReport a : this.listAfterSaleReport) {
                if (a.getQualityControlDate() == null) {
                    _error++;
                } else {
                    this.moduleGlobal.updateAfterSaleReportQualityControl(a);
                }
            }
            if (_error != 0) {
                throw new ValidControlException();
            }
        } catch (ValidControlException e) {
            throw new ValidControlException(
                    "Un ou plusieurs champs n'ont pas été enregistrer car il n'était pas complet.");
        }
    }
}