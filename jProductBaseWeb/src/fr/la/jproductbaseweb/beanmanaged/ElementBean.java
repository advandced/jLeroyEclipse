package fr.la.jproductbaseweb.beanmanaged;

import fr.la.jproductbase.metier.FollowingFormModel;
import fr.la.jproductbase.metier.ProductConf;
import fr.la.jproductbase.metier.ProductConfModel;
import fr.la.jproductbase.metier.ProductFamily;
import fr.la.jproductbase.metier.ProductLine;
import fr.la.jproductbase.metier.ProductSupply;
import java.sql.Timestamp;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "formOperator")
@SessionScoped
public class ElementBean extends ProductConf {

    private static final long serialVersionUID = 1L;
    private boolean selectedElement;

    public ElementBean(int idProductConf, Timestamp timeStamp, int state,
            String reference, String majorIndex, String minorIndex,
            boolean identifiable, ProductSupply productSupply,
            ProductFamily productFamily, FollowingFormModel followingFormModel,
            ProductLine productLine, ProductConfModel productConfModel) {
        super(idProductConf, timeStamp, state, reference, majorIndex, minorIndex,
                identifiable, productSupply, productFamily, followingFormModel,
                productLine, productConfModel);
        // TODO Auto-generated constructor stub
    }

    public ElementBean() {
    }

    public boolean isSelectedElement() {
        return selectedElement;
    }

    public void setSelectedElement(boolean selectedElement) {
        this.selectedElement = selectedElement;
    }

    @Override
    public String toString() {
        return "ElementBean [selectedElement=" + selectedElement
                + ", idProductConf=" + super.getIdProductConf() + ", reference="
                + super.getReference() + ", majorIndex=" + super.getMajorIndex() + ", minorIndex="
                + super.getMinorIndex() + "]";
    }
}
