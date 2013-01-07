package fr.la.jproductbaseweb.beanmanaged;

import fr.la.jproductbase.metier.Product;
import fr.la.jproductbase.metier.ProductConf;
import java.sql.Timestamp;

public class CardElementBean extends Product {

    private static final long serialVersionUID = 1L;
    private boolean selectedElement;

    public CardElementBean(int idProduct, Timestamp timestamp, int state,
            String serialNumber, String datecode, String macAddress,
            String providerCode, ProductConf productConf) {
        super(idProduct, timestamp, state, serialNumber, datecode, macAddress,
                providerCode, productConf);
        // TODO Auto-generated constructor stub
    }

    public CardElementBean() {
    }

    public boolean isSelectedElement() {
        return selectedElement;
    }

    public void setSelectedElement(boolean selectedElement) {
        this.selectedElement = selectedElement;
    }
}
