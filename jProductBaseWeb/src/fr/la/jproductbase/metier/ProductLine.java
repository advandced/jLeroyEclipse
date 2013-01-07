package fr.la.jproductbase.metier;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Classe m&eacute;tier du type de produit.
 *
 * @author stc
 *
 */
public class ProductLine implements Serializable {

    private static final long serialVersionUID = 1L;
    private int idProductLine;
    private Timestamp timestamp;
    private int state; // 0: Disable, 1: Enable
    private String designation;

    /**
     * Cr&eacute;er une gamme de produit.
     *
     * @param idProductLine Identifiant de la gamme de produit.
     * @param timestamp Horadatage de l'enregistrement.
     * @param state Etat de l'enregistrement.
     * @param designation D&eacute;signation de la gamme de produit.
     */
    public ProductLine(int idProductLine, Timestamp timestamp, int state,
            String designation) {
        this.idProductLine = idProductLine;
        this.timestamp = timestamp;
        this.state = state;
        this.designation = designation;
    }

    /**
     * GETTERS AND SETTERS
     */
    /**
     * @return the idProductLine
     */
    public int getIdProductLine() {
        return idProductLine;
    }

    /**
     * @param idProductLine the idProductLine to set
     */
    public void setIdProductLine(int idProductLine) {
        this.idProductLine = idProductLine;
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
     * @return the designation
     */
    public String getDesignation() {
        return designation;
    }

    /**
     * @param designation the designation to set
     */
    public void setDesignation(String designation) {
        this.designation = designation;
    }
}
