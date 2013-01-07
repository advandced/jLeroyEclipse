package fr.la.jproductbaseweb.beanmanaged.modelForm;

import fr.la.jproductbase.metier.FollowingFormModel;
import fr.la.jproductbase.metier.ProductConf;
import fr.la.jproductbase.metier.ProductConfModel;
import fr.la.jproductbase.metier.ProductFamily;
import fr.la.jproductbase.metier.ProductSupply;
import fr.la.jproductbase.metier.Software;
import fr.la.jproductbaseweb.beanmanaged.ElementBean;
import fr.la.jproductbaseweb.beanmanaged.SoftwareBean;
import fr.la.jproductbaseweb.beanmanaged.exception.ProductConfException;
import java.io.Serializable;
import java.util.List;

public class ProductConfForm implements Serializable {

    private static final long serialVersionUID = 1L;
    private String reference;
    private String majorIndex;
    private ProductFamily productFamily;
    private String minorIndex;
    private boolean identifiableProductConf;
    private int state;
    private ProductConfModel selectedProductConfModel;
    private ProductFamily selectedProductFamily;
    private ProductSupply selectedProductSupply;
    private FollowingFormModel selectedFollowingFormModel;
    private List<Software> softwareList;
    private List<ProductConfModel> productConfModelList;

    public ProductConfForm(String reference, String majorIndex,
            ProductFamily productFamily, String minorIndex,
            boolean identifiableProductConf, int state,
            ProductConfModel selectedProductConfModel,
            ProductFamily selectedProductFamily,
            ProductSupply selectedProductSupply,
            FollowingFormModel selectedFollowingFormModel,
            List<Software> softwareList,
            List<ProductConfModel> productConfModelList,
            List<ProductFamily> productFamilyList,
            List<ProductSupply> productSupplyList,
            List<FollowingFormModel> followingFormModelList,
            List<ProductConf> productConfsList,
            List<ElementBean> cardElementList,
            List<SoftwareBean> softwareElementList) throws ProductConfException {
        super();
        this.reference = reference;
        this.majorIndex = majorIndex;
        this.productFamily = productFamily;
        this.minorIndex = minorIndex;
        this.identifiableProductConf = identifiableProductConf;
        this.state = state;
        this.selectedProductConfModel = selectedProductConfModel;
        this.selectedProductFamily = selectedProductFamily;
        this.selectedProductSupply = selectedProductSupply;
        this.selectedFollowingFormModel = selectedFollowingFormModel;
        this.softwareList = softwareList;
        this.productConfModelList = productConfModelList;



        if (reference.isEmpty() || majorIndex.isEmpty() || selectedProductFamily == null) {

            throw new ProductConfException();

        }

    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getMajorIndex() {
        return majorIndex;
    }

    public void setMajorIndex(String majorIndex) {
        this.majorIndex = majorIndex;
    }

    public ProductFamily getProductFamily() {
        return productFamily;
    }

    public void setProductFamily(ProductFamily productFamily) {
        this.productFamily = productFamily;
    }

    public String getMinorIndex() {
        return minorIndex;
    }

    public void setMinorIndex(String minorIndex) {
        this.minorIndex = minorIndex;
    }

    public boolean isIdentifiableProductConf() {
        return identifiableProductConf;
    }

    public void setIdentifiableProductConf(boolean identifiableProductConf) {
        this.identifiableProductConf = identifiableProductConf;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public List<Software> getSoftwareList() {
        return softwareList;
    }

    public void setSoftwareList(List<Software> softwareList) {
        this.softwareList = softwareList;
    }

    public List<ProductConfModel> getProductConfModelList() {
        return productConfModelList;
    }

    public void setProductConfModelList(List<ProductConfModel> productConfModelList) {
        this.productConfModelList = productConfModelList;
    }

    public ProductConfModel getSelectedProductConfModel() {
        return selectedProductConfModel;
    }

    public void setSelectedProductConfModel(
            ProductConfModel selectedProductConfModel) {
        this.selectedProductConfModel = selectedProductConfModel;
    }

    public ProductFamily getSelectedProductFamily() {
        return selectedProductFamily;
    }

    public void setSelectedProductFamily(ProductFamily selectedProductFamily) {
        this.selectedProductFamily = selectedProductFamily;
    }

    public ProductSupply getSelectedProductSupply() {
        return selectedProductSupply;
    }

    public void setSelectedProductSupply(ProductSupply selectedProductSupply) {
        this.selectedProductSupply = selectedProductSupply;
    }

    public FollowingFormModel getSelectedFollowingFormModel() {
        return selectedFollowingFormModel;
    }

    public void setSelectedFollowingFormModel(
            FollowingFormModel selectedFollowingFormModel) {
        this.selectedFollowingFormModel = selectedFollowingFormModel;
    }
}
