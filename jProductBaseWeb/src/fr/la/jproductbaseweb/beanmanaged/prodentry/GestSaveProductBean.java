package fr.la.jproductbaseweb.beanmanaged.prodentry;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import fr.la.jproductbase.metier.Product;
import fr.la.jproductbase.service.ServiceInterface;
import fr.la.jproductbaseweb.beanmanaged.ProductBean;

@ManagedBean(name = "gestSaveProductBean")
@ViewScoped
public class GestSaveProductBean {

    private List<ProductBean> productBeanList;
    private ServiceInterface globalService;
    private Boolean result = false;

    public GestSaveProductBean() {
        this.globalService = ServiceInterface.getInstance();
    }

    public void displayProduct() {
        this.productBeanList = new ArrayList<ProductBean>();
        String modele = null;

    	java.util.Date uDate = new java.util.Date (System.currentTimeMillis ()); //Relever l'heure avant le debut du progamme (en milliseconde) 
        System.out.println("Debut de la recherche de la liste des produits");
        List<Product> _productList = this.globalService.getProductsRecordables(modele);
        System.out.println("Fin de la recherche de la liste des produits");
    	Date dateFin = new Date (System.currentTimeMillis()); //Relever l'heure a la fin du progamme (en milliseconde) 
    	Date duree = new Date (System.currentTimeMillis()); //Pour calculer la diff�rence
    	duree.setTime (dateFin.getTime () - uDate.getTime ());  //Calcul de la diff�rence
    	long secondes = duree.getTime () / 1000;
    	long min = secondes / 60;
    	long heures = min / 60;
    	long mili = duree.getTime () % 1000;
    	secondes %= 60;
    	System.out.println ("Temps pass� durant le traitement : \nHeures : " + heures + "\nMinutes : " + min + "\nSecondes : " + secondes + "\nMilisecondes : " + mili + "\n");

        for (Product p : _productList) {

            ProductBean _productBean = new ProductBean();

            _productBean.setIdProduct(p.getIdProduct()); // JB : cette ligne ne semble pas servir
            _productBean.setSerialNumber(p.getSerialNumber());
            _productBean.setProductConf(p.getFEDDProductConf());
            _productBean.setDatecode(p.getDatecode());
            //_productBean.getProductConf().setReference(p.getProductConf().getReference()); en commentaire par RMO

           this.productBeanList.add(_productBean);
        }
        System.out.println("Fin de la recuperation des informations produits");
        this.result = true;
    }

    public void saveProduct() {
        for (ProductBean productBean : productBeanList) {
            if (productBean.isSelectedProduct()) {
            	this.globalService.setProductFEDDtoLAI(productBean.getIdProduct(), productBean.getProductConf(), productBean.getSerialNumber(), productBean.getDatecode());
            }
        }
        displayProduct();
    }

    public List<ProductBean> getProductBeanList() {
        return productBeanList;
    }

    public void setProductBeanList(List<ProductBean> productBeanList) {
        this.productBeanList = productBeanList;
    }

    public Boolean getResult() {
        return result;
    }

    public void setResult(Boolean result) {
        this.result = result;
    }
}
