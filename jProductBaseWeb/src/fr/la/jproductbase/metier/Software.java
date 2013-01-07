package fr.la.jproductbase.metier;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Classe m&eacute;tier d'une configuration produit.
 *
 * @author stc
 *
 */
public class Software implements Serializable {

    private static final long serialVersionUID = 1L;
    protected int idSoftware;
    protected Timestamp timestamp;
    protected int state; // 0: Disable, 1: Enable
    protected String name;
    protected String version;

    /**
     * Cr&eacute;er un logiciel.
     *
     * @param idSoftware Identifiant du logiciel.
     * @param timeStamp Horadatage de l'enregistrement.
     * @param state Etat de l'enregistrement.
     * @param name Nom.
     * @param version Version.
     */
    public Software(int idSoftware, Timestamp timeStamp, int state,
            String name, String version) {
        this(name, version);
        this.idSoftware = idSoftware;
        this.timestamp = timeStamp;
        this.state = state;
    }

    public Software() {
    }

    /**
     * Cr&eacute;er un logiciel.
     *
     * @param name Nom.
     * @param version Version.
     */
    public Software(String name, String version) {
        this.name = name;
        this.version = version;
    }

    /**
     * @return the idSoftware
     */
    public int getIdSoftware() {
        return idSoftware;
    }

    /**
     * @param idSoftware the idSoftware to set
     */
    public void setIdSoftware(int idSoftware) {
        this.idSoftware = idSoftware;
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
    public void setVersion(String version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "Software [" + "getIdSoftware " + getIdSoftware() + " " + "getName " + getName() + " " + "getState " + getState() + " " + "getTimestamp " + getTimestamp() + " " + "getVersion " + getVersion() + "]";
    }
}
