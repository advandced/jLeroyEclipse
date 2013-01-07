package fr.la.jproductbaseweb.beanmanaged.modelForm;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import fr.la.jproductbase.metier.ApparentCause;
import fr.la.jproductbase.metier.Failure;
import fr.la.jproductbase.metier.Product;
import fr.la.jproductbase.metier.ProductConf;
import fr.la.jproductbase.service.ServiceInterface;
import fr.la.jproductbaseweb.beanmanaged.exception.EntrySavException;
import java.io.Serializable;

public class EntrySavForm implements Serializable {

    private static final long serialVersionUID = 1L;
    private String indexOut;
    private String refProdCustomer;
    private Date arrivalDate;
    private String asker;
    private String numberECS;
    private Date reparationDate;
    private Date qualityDate;
    private Date expeditionDate;
    private int functionnalTest;
    private int visualControl;
    private String comment;
    private String reference = null;
    private String dateCode;
    private String serialNumber;
    private List<Failure> failureList;
    private ApparentCause apparentCause;
    private String InterventionSheetLink;
    private Product product;

    public EntrySavForm(String indexOut, String refProdCustomer,
            Date arrivalDate, String asker, String numberECS,
            Date reparationDate, Date qualityDate, Date expeditionDate,
            int functionnalTest, int visualControl, String comment,
            List<Failure> failureList, ApparentCause apparentCause,
            String interventionSheetLink) throws EntrySavException {
        super();
        this.indexOut = indexOut;
        this.refProdCustomer = refProdCustomer;
        this.arrivalDate = arrivalDate;
        this.asker = asker;
        this.numberECS = numberECS;
        this.reparationDate = reparationDate;
        this.qualityDate = qualityDate;
        this.expeditionDate = expeditionDate;
        this.functionnalTest = functionnalTest;
        this.visualControl = visualControl;
        this.comment = comment;
        this.failureList = failureList;
        this.apparentCause = apparentCause;
        InterventionSheetLink = interventionSheetLink;

        if (this.arrivalDate == null) {

            String message = "";
            if (this.arrivalDate == null) {
                message = message + " La date d'arrivée est obligatoire";
            }
            System.out.println("vide");

            throw new EntrySavException(message);

        }

        if (this.reparationDate != null && this.arrivalDate != null) {

            if (this.arrivalDate.getTime() > this.reparationDate.getTime()) {

                throw new EntrySavException(
                        "Probléme sur les dates : la date de réparation ne peut étre inférieur à la date d'arrivée");

            }
        }

        if (this.expeditionDate != null && this.reparationDate != null) {

            if ((reparationDate.getTime() > expeditionDate.getTime())) {

                throw new EntrySavException(
                        "Probléme sur les dates : la date de réparation ne peut étre inférieur à la date d'arrivée");

            }
        }
    }

    public EntrySavForm(String topoRef) throws EntrySavException {
        if (topoRef.isEmpty() || topoRef == null) {

            throw new EntrySavException(
                    "Merci de remplir une TOPO avant d'enregistrer");

        }

    }

    public EntrySavForm(String reference, String datecode, String serialNumber,
            ProductConf productConf) throws EntrySavException, Exception {

        this.reference = reference;
        this.dateCode = datecode;
        this.serialNumber = serialNumber;
        System.out.println(this.reference);
        System.out.println(this.dateCode);
        System.out.println(this.serialNumber);

        if (this.reference == null || this.dateCode == null
                || this.serialNumber == null) {

            throw new EntrySavException("Pas de sélection de produit");
        } else {

            if ((this.reference.isEmpty() || this.reference == null)
                    || (this.dateCode.isEmpty() || this.dateCode == null)
                    || (this.serialNumber.isEmpty() || this.serialNumber == null)) {

                throw new EntrySavException("Le poduit n'existe pas");

            } else {

                this.product = null;
                ServiceInterface _service = new ServiceInterface();
                System.out.println("ref " + this.reference);
                try {
                    this.product = _service.getProduct(reference, serialNumber,
                            datecode);
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                if (this.product == null) {
                    System.out.println("le produit n'existe pas");

                    this.product = _service.addProduct(productConf,
                            serialNumber, datecode, "");
                    throw new EntrySavException(
                            "le produit n'existe pas il vient d'étre crée avec l'id "
                            + this.product.getIdProduct());

                } else {

                    this.product = null;
                    System.out.println("ref " + this.reference);
                    try {
                        this.product = _service.getProduct(reference,
                                serialNumber, datecode);
                    } catch (SQLException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    if (this.product == null) {
                        System.out.println("le produit n'existe pas");

                        this.product = _service.addProduct(productConf,
                                serialNumber, datecode, "");
                        throw new EntrySavException(
                                "le produit n'existe pas il vient d'étre crée avec l'id "
                                + this.product.getIdProduct());

                    } else {

                        System.out.println("le produit existe");
                    }

                }
            }
        }

    }

    public String getIndexOut() {
        return indexOut;
    }

    public void setIndexOut(String indexOut) {
        this.indexOut = indexOut;
    }

    public String getRefProdCustomer() {
        return refProdCustomer;
    }

    public void setRefProdCustomer(String refProdCustomer) {
        this.refProdCustomer = refProdCustomer;
    }

    public Date getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(Date arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public String getAsker() {
        return asker;
    }

    public void setAsker(String asker) {
        this.asker = asker;
    }

    public String getNumberECS() {
        return numberECS;
    }

    public void setNumberECS(String numberECS) {
        this.numberECS = numberECS;
    }

    public Date getReparationDate() {
        return reparationDate;
    }

    public void setReparationDate(Date reparationDate) {
        this.reparationDate = reparationDate;
    }

    public Date getQualityDate() {
        return qualityDate;
    }

    public void setQualityDate(Date qualityDate) {
        this.qualityDate = qualityDate;
    }

    public Date getExpeditionDate() {
        return expeditionDate;
    }

    public void setExpeditionDate(Date expeditionDate) {
        this.expeditionDate = expeditionDate;
    }

    public int getFunctionnalTest() {
        return functionnalTest;
    }

    public void setFunctionnalTest(int functionnalTest) {
        this.functionnalTest = functionnalTest;
    }

    public int getVisualControl() {
        return visualControl;
    }

    public void setVisualControl(int visualControl) {
        this.visualControl = visualControl;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public List<Failure> getFailureList() {
        return failureList;
    }

    public void setFailureList(List<Failure> failureList) {
        this.failureList = failureList;
    }

    public ApparentCause getApparentCause() {
        return apparentCause;
    }

    public void setApparentCause(ApparentCause apparentCause) {
        this.apparentCause = apparentCause;
    }

    public String getInterventionSheetLink() {
        return InterventionSheetLink;
    }

    public void setInterventionSheetLink(String interventionSheetLink) {
        InterventionSheetLink = interventionSheetLink;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
