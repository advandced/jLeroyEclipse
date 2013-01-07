package fr.la.jproductbaseweb.beanmanaged.modelForm;

import fr.la.jproductbaseweb.beanmanaged.exception.ApparentCauseException;
import java.io.Serializable;

public class ApparentCauseCustomerForm implements Serializable {

    private static final long serialVersionUID = 1L;
    private String description;
    private int state;

    public ApparentCauseCustomerForm(String description, int state) throws ApparentCauseException {
        super();
        this.description = description;
        this.state = state;

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
}
