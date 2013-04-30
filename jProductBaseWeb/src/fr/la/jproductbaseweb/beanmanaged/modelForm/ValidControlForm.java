package fr.la.jproductbaseweb.beanmanaged.modelForm;

import fr.la.jproductbase.metier.AfterSaleReport;
import fr.la.jproductbase.service.ServiceInterface;
import java.io.Serializable;

public class ValidControlForm implements Serializable {

    private static final long serialVersionUID = 1L;
    private ServiceInterface moduleGlobal = ServiceInterface.getInstance();
    private AfterSaleReport[] listAfterSaleReport;

    public ValidControlForm(AfterSaleReport[] selectedAfterSaleReport) {
        this.listAfterSaleReport = selectedAfterSaleReport;
        int _error = 0;
        for (AfterSaleReport a : this.listAfterSaleReport) {
            if (a.getQualityControlDate() == null) {
                _error++;
            } else {
                this.moduleGlobal.updateAfterSaleReportQualityControl(a);
            }
        }
        if (_error != 0) {
        	//throw new ValidControlException("Un ou plusieurs champs n'ont pas été enregistrer car il n'était pas complet.");
            throw new IllegalStateException();
        }
    }
}