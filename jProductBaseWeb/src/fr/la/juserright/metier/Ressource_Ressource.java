package fr.la.juserright.metier;

import java.io.Serializable;

public class Ressource_Ressource implements Serializable {

    private static final long serialVersionUID = 1L;
    private int idressource_ressource;
    private Ressource idressource;

    public int getIdressource_ressource() {
        return idressource_ressource;
    }

    public void setIdressource_ressource(int idressource_ressource) {
        this.idressource_ressource = idressource_ressource;
    }

    public Ressource getIdressource() {
        return idressource;
    }

    public void setIdressource(Ressource idressource) {
        this.idressource = idressource;
    }

    public Ressource_Ressource(int idressource_ressource, Ressource idressource) {
        this.idressource_ressource = idressource_ressource;
        this.idressource = idressource;
    }

    public Ressource_Ressource(Ressource idressource) {
        this.idressource = idressource;
    }
}