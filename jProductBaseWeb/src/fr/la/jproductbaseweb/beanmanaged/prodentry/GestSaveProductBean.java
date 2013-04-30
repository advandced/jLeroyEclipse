package fr.la.jproductbaseweb.beanmanaged.prodentry;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.naming.NamingException;

import fr.la.configfilereader.ConfigFileReaderException;
import fr.la.jproductbase.metier.JProductBaseException;
import fr.la.jproductbase.metier.Product;
import fr.la.jproductbase.service.ServiceInterface;
import fr.la.jproductbaseweb.beanmanaged.ProductBean;

@ManagedBean(name = "gestSaveProductBean")
@ApplicationScoped
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
        System.out.println("START");
        List<Product> _productList = this.globalService.getProductsRecordables(modele);
        System.out.println("OUT");
    	Date dateFin = new Date (System.currentTimeMillis()); //Relever l'heure a la fin du progamme (en milliseconde) 
    	Date duree = new Date (System.currentTimeMillis()); //Pour calculer la différence
    	duree.setTime (dateFin.getTime () - uDate.getTime ());  //Calcul de la différence
    	long secondes = duree.getTime () / 1000;
    	long min = secondes / 60;
    	long heures = min / 60;
    	long mili = duree.getTime () % 1000;
    	secondes %= 60;
    	System.out.println ("Temps passé durant le traitement : \nHeures : " + heures + "\nMinutes : " + min + "\nSecondes : " + secondes + "\nMilisecondes : " + mili + "\n");

        for (Product p : _productList) {

            ProductBean _productBean = new ProductBean();

            _productBean.setIdProduct(p.getIdProduct());
            _productBean.setSerialNumber(p.getSerialNumber());

            _productBean.setProductConf(p.getProductConf());
            _productBean.setDatecode(p.getDatecode());
            //_productBean.getProductConf().setReference(p.getProductConf().getReference());

            this.productBeanList.add(_productBean);

        }
        System.out.println("STOP");
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
