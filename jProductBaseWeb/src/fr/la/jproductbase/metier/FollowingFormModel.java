package fr.la.jproductbase.metier;

import java.io.Serializable;
import java.sql.Timestamp;

public class FollowingFormModel implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private int idFollowingFormmodel;
    private Timestamp timestamp;
    private int state; // 0: Disable, 1: Enable
    private String name;
    private String version;
    private String jasperReport;

    public FollowingFormModel(int idFollowingFormModel, Timestamp timestamp,
            int state, String name, String version, String jasperReport) {
        this.idFollowingFormmodel = idFollowingFormModel;
        this.timestamp = timestamp;
        this.state = state;
        this.name = name;
        this.version = version;
        this.jasperReport = jasperReport;
    }

    public FollowingFormModel() {
    }

    public FollowingFormModel(int state) {
        this.state = state;
    }

    /**
     * @return the idFollowingFormmodel
     */
    public int getIdFollowingFormmodel() {
        return idFollowingFormmodel;
    }

    /**
     * @param idFollowingFormmodel the idFollowingFormmodel to set
     */
    public void setIdFollowingFormmodel(int idFollowingFormmodel) {
        this.idFollowingFormmodel = idFollowingFormmodel;
    }

    /**
     * @return the timestamp
     */
    public Timestamp getTimestamp() {
        return timestamp;
    }

    /**
     * @param timestamp the timestamp to set
     */
    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * @return the state
     */
    public int getState() {
        return state;
    }

    /**
     * @param state the state to set
     */
    public void setState(int state) {
        this.state = state;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the version
     */
    public String getVersion() {
        return version;
    }

    /**
     * @param version the version to set
     */
    public void setversion(String version) {
        this.version = version;
    }

    /**
     * @return the jasperReport
     */
    public String getJasperReport() {
        return jasperReport;
    }

    /**
     * @param jasperReport the jasperReport to set
     */
    public void setJasperReport(String jasperReport) {
        this.jasperReport = jasperReport;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + idFollowingFormmodel;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        FollowingFormModel other = (FollowingFormModel) obj;
        if (idFollowingFormmodel != other.idFollowingFormmodel) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return name;
    }
}
