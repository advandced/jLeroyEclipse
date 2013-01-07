package fr.la.jproductbase.metier;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Classe m&eacute;tier du mod&eacute;le produit.
 *
 * @author stc
 *
 */
public class ProductConfModel implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private int idProductConfModel;
    private Timestamp timestamp;
    private int state; // 0: Disable, 1: Enable
    private String reference;
    private String designation;

    /**
     * Cr&eacute;er un mod&eacute;le produit.
     *
     * @param idProductConfModel Identifiant du mod&eacute;le produit.
     * @param timestamp Horadatage de l'enregistrement.
     * @param state Etat de l'enregistrement.
     * @param reference R&eacute;f&eacute;rence produit.
     * @param designation D&eacute;signation de la r&eacute;f&eacute;rence
     * produit.
     */
    public ProductConfModel(int idProductConfModel, Timestamp timestamp,
            int state, String reference, String designation) {
        this.idProductConfModel = idProductConfModel;
        this.timestamp = timestamp;
        this.state = state;
        this.reference = reference;
        this.designation = designation;
    }

    public ProductConfModel() {
    }

    /**
     * GETTERS AND SETTERS
     */
    /**
     * @return the idProductConfModel
     */
    public int getIdProductConfModel() {
        return idProductConfModel;
    }

    /**
     * @param idProductConfModel the idProductLine to set
     */
    public void setIdProductConfModel(int idProductConfModel) {
        this.idProductConfModel = idProductConfModel;
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
     * @return the reference
     */
    public String getReference() {
        return reference;
    }

    /**
     * @param reference the reference to set
     */
    public void setReference(String reference) {
        this.reference = reference;
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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + idProductConfModel;
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
        ProductConfModel other = (ProductConfModel) obj;
        if (idProductConfModel != other.idProductConfModel) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ProductConfModel{" + "idProductConfModel=" + idProductConfModel + ", timestamp=" + timestamp + ", state=" + state + ", reference=" + reference + ", designation=" + designation + '}';
    }
}
