package fr.la.jproductbaseweb.beanmanaged.modelForm;

import fr.la.jproductbase.metier.AfterSaleCom;
import fr.la.jproductbase.service.ServiceInterface;
import fr.la.jproductbaseweb.beanmanaged.exception.DevisPrealableException;
import java.io.Serializable;
import java.util.List;

public class DevisPrealableForm implements Serializable {

    private static final long serialVersionUID = 1L;
    private ServiceInterface moduleGlobal = new ServiceInterface();
    private List<AfterSaleCom> listAfterSaleCom;

    public DevisPrealableForm(List<AfterSaleCom> listAfterSaleCom)
            throws Exception {
        this.listAfterSaleCom = listAfterSaleCom;
        int _errortotal = 0;
        for (AfterSaleCom a : this.listAfterSaleCom) {
            int _error = 0;
            if (a.getQuotationNumber() != null
                    && (a.getSavPrice() != null || a.getSavPrice() != 0)
                    && a.getQuotationDate() != null) {
                if (a.getQuotationNumber() == "0") {
                    _error++;
                }
                if (a.getSavPrice() == null || a.getSavPrice() == 0) {
                    _error++;
                }
                if (a.getQuotationDate() == null) {
                    _error++;
                }
                if (_error == 0) {
                    moduleGlobal.updateDevisPrea(a);
                } else {
                    _errortotal++;
                }
            }
        }
        if (_errortotal != 0) {
            throw new DevisPrealableException(
                    "Une ou plusieurs lignes n'ont pas été enregistrer car elle n'�tait pas complete.");
        }
    }
}