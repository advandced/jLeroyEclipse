package fr.la.jproductbaseweb.beanmanaged.modelForm;

import fr.la.jproductbaseweb.beanmanaged.exception.TesterException;
import java.io.Serializable;

public class TesterForm implements Serializable {

    private static final long serialVersionUID = 1L;
    private String description;
    private int state;

    public TesterForm(String description, int state) throws TesterException {
        super();
        System.out.println("description" + description);
        this.description = description;
        this.state = state;
        if (description.isEmpty()) {
            System.out.println("null description");
            throw new TesterException();

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
