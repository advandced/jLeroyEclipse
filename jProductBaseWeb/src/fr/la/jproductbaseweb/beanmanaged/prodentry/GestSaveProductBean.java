package fr.la.jproductbaseweb.beanmanaged.prodentry;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.naming.NamingException;

import fr.la.configfilereader.ConfigFileReaderException;
import fr.la.jproductbase.dao.ProductDaoException;
import fr.la.jproductbase.dao.SoftwareDaoException;
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
        this.globalService = new ServiceInterface();
    }

    public void displayProduct() {
        this.productBeanList = new ArrayList<ProductBean>();
        String modele = null;
        try {
            List<Product> _productList = this.globalService.getProductsRecordables(modele);
            for (Product p : _productList) {

                ProductBean _productBean = new ProductBean();

                _productBean.setIdProduct(p.getIdProduct());
                _productBean.setSerialNumber(p.getSerialNumber());

                _productBean.setProductConf(p.getProductConf());
                _productBean.setDatecode(p.getDatecode());
                //_productBean.getProductConf().setReference(p.getProductConf().getReference());

                this.productBeanList.add(_productBean);

            }
            this.result = true;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public void saveProduct() {

        for (ProductBean productBean : productBeanList) {

            if (productBean.isSelectedProduct()) {
                try {
                    this.globalService.setProductFEDDtoLAI(productBean.getIdProduct(), productBean.getProductConf(), productBean.getSerialNumber(), productBean.getDatecode());
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (ProductDaoException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (ConfigFileReaderException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (SoftwareDaoException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (JProductBaseException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (NamingException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

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
