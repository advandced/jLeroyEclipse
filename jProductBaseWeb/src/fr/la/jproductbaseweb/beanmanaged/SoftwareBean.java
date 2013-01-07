package fr.la.jproductbaseweb.beanmanaged;

import fr.la.jproductbase.metier.Software;
import java.sql.Timestamp;

public class SoftwareBean extends Software {

    private static final long serialVersionUID = 1L;
    private boolean selectedSoftware;

    public SoftwareBean(int idSoftware, Timestamp timeStamp, int state,
            String name, String version) {
        super(idSoftware, timeStamp, state, name, version);
        // TODO Auto-generated constructor stub
    }

    public SoftwareBean() {
    }

    public boolean isSelectedSoftware() {
        return selectedSoftware;
    }

    public void setSelectedSoftware(boolean selectedSoftware) {
        this.selectedSoftware = selectedSoftware;
    }

    @Override
    public String toString() {
        return "SoftwareBean [selectedSoftware=" + selectedSoftware
                + ", idSoftware=" + idSoftware + ", state=" + state + ", name="
                + name + ", version=" + version + "]";
    }
}
