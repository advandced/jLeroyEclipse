package fr.la.jproductbaseweb.beanmanaged.modelForm;

import fr.la.jproductbaseweb.beanmanaged.exception.SoftwareException;
import java.io.Serializable;

public class SoftwareForm implements Serializable {

    private static final long serialVersionUID = 1L;
    private String name;
    private String version;
    private int state;

    public SoftwareForm(String name, String version, int state) {
        super();
        if (name.isEmpty() || version.isEmpty()) {
        	// TODO : ????
           // throw new SoftwareException();

        }
        this.name = name;
        this.version = version;
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
