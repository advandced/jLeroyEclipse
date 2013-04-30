package fr.la.jproductbaseweb.beanmanaged.modelForm;

import java.util.Date;

import fr.la.jproductbase.metier.AfterSaleCom;
import fr.la.jproductbase.service.ServiceInterface;
import java.io.Serializable;

public class RecapComForm implements Serializable {

    private static final long serialVersionUID = 1L;
    private ServiceInterface moduleGlobal = ServiceInterface.getInstance();
    private int idaftersalecom;
    private String quotationNumber;
    private Date quotationDate;
    private String quotationComment;
    private float SAVPrice;
    private int SAVOrderNumber;
    private Date SAVOrderData;
    private String orderComment;

    public RecapComForm(int idaftersalecom, String quotationNumber,
            Date quotationDate, String quotationComment, float SAVPrice,
            int SAVOrderNumber, Date SAVOrderData, String orderComment)
            throws Exception {
        this.idaftersalecom = idaftersalecom;
        this.quotationNumber = quotationNumber;
        this.quotationDate = quotationDate;
        this.SAVPrice = SAVPrice;
        this.quotationComment = quotationComment;
        this.SAVOrderNumber = SAVOrderNumber;
        this.SAVOrderData = SAVOrderData;
        this.orderComment = orderComment;
        AfterSaleCom _afterSaleCom = new AfterSaleCom(this.idaftersalecom,
                this.quotationNumber, this.quotationDate, this.SAVPrice,
                this.quotationComment, this.SAVOrderNumber, this.SAVOrderData,
                this.orderComment);
        moduleGlobal.updateAfterSaleCom(_afterSaleCom);
    }

    public int getIdaftersalecom() {
        return idaftersalecom;
    }

    public void setIdaftersalecom(int idaftersalecom) {
        this.idaftersalecom = idaftersalecom;
    }

    public String getQuotationNumber() {
        return quotationNumber;
    }

    public void setQuotationNumber(String quotationNumber) {
        this.quotationNumber = quotationNumber;
    }

    public Date getQuotationDate() {
        return quotationDate;
    }

    public void setQuotationDate(Date quotationDate) {
        this.quotationDate = quotationDate;
    }

    public float getSAVPrice() {
        return SAVPrice;
    }

    public void setSAVPrice(float sAVPrice) {
        SAVPrice = sAVPrice;
    }

    public int getSAVOrderNumber() {
        return SAVOrderNumber;
    }

    public void setSAVOrderNumber(int sAVOrderNumber) {
        SAVOrderNumber = sAVOrderNumber;
    }

    public Date getSAVOrderData() {
        return SAVOrderData;
    }

    public void setSAVOrderData(Date sAVOrderData) {
        SAVOrderData = sAVOrderData;
    }

    public String getQuotationComment() {
        return quotationComment;
    }

    public void setQuotationComment(String quotationComment) {
        this.quotationComment = quotationComment;
    }

    public String getOrderComment() {
        return orderComment;
    }

    public void setOrderComment(String orderComment) {
        this.orderComment = orderComment;
    }
}