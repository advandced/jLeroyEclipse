package fr.la.jproductbaseweb.beanmanaged.modelForm;

import java.sql.SQLException;
import java.util.Date;

import fr.la.jproductbase.metier.Product;
import fr.la.jproductbase.service.ServiceInterface;
import fr.la.jproductbaseweb.beanmanaged.exception.ApparentCauseException;
import fr.la.jproductbaseweb.beanmanaged.exception.EntryDefaultRapportException;
import java.io.Serializable;

public class EntryDefaultRapportForm implements Serializable {

    private static final long serialVersionUID = 1L;
    private String failureReportComment;
    private Date date;
    private String operatorCode;
    private String failureCode;
    private String reference;
    private String dateCode;
    private String serialNumber;
    private Product product;

    public EntryDefaultRapportForm(String failureReportComment, Date date,
            String operatorCode, String failureCode)
            throws ApparentCauseException {

        this.setFailureReportComment(failureReportComment);
        this.setDate(date);
        this.setOperatorCode(operatorCode);
        this.setFailureCode(failureCode);

        if ((failureReportComment.isEmpty() || failureReportComment == null)
                || date == null
                || (operatorCode == null || operatorCode.isEmpty())
                || failureCode.isEmpty()) {

            if (failureReportComment.isEmpty()) {
                // System.out.println("failureReportComment vide");
            } else if (date == null) {
                System.out.println("date null");
            } else if (operatorCode == null) {
                System.out.println("operator null");
            } else if (operatorCode.isEmpty()) {
                System.out.println("operator vide");
            } else if (failureCode.isEmpty()) {
                System.out.println("failure Code vide");
                throw new ApparentCauseException();
            }

        }
    }

    public EntryDefaultRapportForm(String reference, String datecode,
            String serialNumber) throws EntryDefaultRapportException {
        System.out.println("dans le constructeur");
        this.setReference(reference);
        this.setDateCode(datecode);
        this.setSerialNumber(serialNumber);

        if (reference.isEmpty() || datecode.isEmpty() || serialNumber.isEmpty()) {
            System.out.println("null");
            throw new EntryDefaultRapportException();

        } else {

            this.product = null;
            ServiceInterface _service = new ServiceInterface();

            try {
                this.product = _service.getProduct(reference, serialNumber,
                        datecode);
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            if (this.product == null) {
                System.out.println("le produit n'existe pas");
                throw new EntryDefaultRapportException();
            } else {

                System.out.println("le produit existe");
            }

        }

    }

    public String getFailureReportComment() {
        return failureReportComment;
    }

    public void setFailureReportComment(String failureReportComment) {
        this.failureReportComment = failureReportComment;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getOperatorCode() {
        return operatorCode;
    }

    public void setOperatorCode(String operatorCode) {
        this.operatorCode = operatorCode;
    }

    public String getFailureCode() {
        return failureCode;
    }

    public void setFailureCode(String failureCode) {
        this.failureCode = failureCode;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getDateCode() {
        return dateCode;
    }

    public void setDateCode(String dateCode) {
        this.dateCode = dateCode;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
