package fr.la.jproductbaseweb.userright;

import fr.la.juserright.metier.Ressource;
import java.io.Serializable;

public class MenuRight implements Serializable {

    private static final long serialVersionUID = 1L;
    private int id;
    private String title;
    private String description;
    private Ressource ressource;
    private MenuRight menuMaster;

    public MenuRight() {
    }

    public MenuRight(int id, String title, String description, Ressource ressource) {
        super();
        this.id = id;
        this.title = title;
        this.description = description;
        this.ressource = ressource;
    }

    public MenuRight(int id, String title, String description,
            Ressource ressource, MenuRight menuMaster) {
        super();
        this.id = id;
        this.title = title;
        this.description = description;
        this.ressource = ressource;
        this.menuMaster = menuMaster;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Ressource getResource() {
        return ressource;
    }

    public void setResource(Ressource resource) {
        this.ressource = resource;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public MenuRight getMenuMaster() {
        return menuMaster;
    }

    public void setMenuMaster(MenuRight menuMaster) {
        this.menuMaster = menuMaster;
    }
}