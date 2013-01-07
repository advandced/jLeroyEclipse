package fr.la.jproductbaseweb.beanmanaged.modelForm;

import fr.la.jproductbase.metier.ApparentCauseCustomer;
import fr.la.jproductbaseweb.beanmanaged.exception.ApparentCauseException;
import java.io.Serializable;

public class ApparentCauseForm implements Serializable {

    private static final long serialVersionUID = 1L;
    private String description;
    private int state;
    private ApparentCauseCustomer apparentCauseCustomer;

    public ApparentCauseForm(String description, int state,
            ApparentCauseCustomer apparentCauseCustomer) throws ApparentCauseException {
        super();

        this.description = description;
        this.state = state;
        this.apparentCauseCustomer = apparentCauseCustomer;


        if (this.description.isEmpty()) {
            throw new ApparentCauseException();
        }
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public ApparentCauseCustomer getApparentCauseCustomer() {
        return apparentCauseCustomer;
    }

    public void setApparentCauseCustomer(ApparentCauseCustomer apparentCauseCustomer) {
        this.apparentCauseCustomer = apparentCauseCustomer;
    }
}
