package fr.la.jproductbase.metier;

import java.io.Serializable;
import java.sql.Timestamp;

public class ProductFamily implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private int idProductFamily;
    private Timestamp timestamp;
    private int state; // 0: Disable, 1: Enable
    private String name;
    private ProductType productType;

    /**
     * Cr&eacute;er une famille de produit.
     *
     * @param idProductFamily Identifiant de la famille de produit.
     * @param timestamp Horadatage de l'enregistement.
     * @param state Etat de l'enregistrement.
     * @param name Nom de la famille de produit.
     * @param productType Type du produit.
     */
    public ProductFamily(int idProductFamily, Timestamp timestamp, int state,
            String name, ProductType productType) {
        this.idProductFamily = idProductFamily;
        this.timestamp = timestamp;
        this.state = state;
        this.name = name;
        this.productType = productType;
    }

    /**
     * GETTERS AND SETTERS
     */
    /**
     * @return the idProductFamily
     */
    public int getIdProductFamily() {
        return idProductFamily;
    }

    /**
     * @param idProductFamily the idProductFamily to set
     */
    public void setIdProductFamily(int idProductFamily) {
        this.idProductFamily = idProductFamily;
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
     * @return the productType
     */
    public ProductType getProductType() {
        return productType;
    }

    /**
     * @param productType the productType to set
     */
    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + idProductFamily;
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
        ProductFamily other = (ProductFamily) obj;
        if (idProductFamily != other.idProductFamily) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return name;
    }
}
