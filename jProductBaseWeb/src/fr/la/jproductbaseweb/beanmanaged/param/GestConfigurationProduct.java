package fr.la.jproductbaseweb.beanmanaged.param;

import fr.la.configfilereader.ConfigFileReaderException;
import fr.la.jproductbase.metier.FollowingFormModel;
import fr.la.jproductbase.metier.ProductConf;
import fr.la.jproductbase.metier.ProductConfModel;
import fr.la.jproductbase.metier.ProductFamily;
import fr.la.jproductbase.metier.ProductSupply;
import fr.la.jproductbase.metier.Software;
import fr.la.jproductbaseweb.beanmanaged.ElementBean;
import fr.la.jproductbaseweb.beanmanaged.SoftwareBean;
import fr.la.jproductbaseweb.beanmanaged.exception.ProductConfException;
import fr.la.jproductbaseweb.beanmanaged.modelForm.ProductConfForm;
import fr.la.jproductbaseweb.beanmanaged.modeltable.ProductConfLazy;
import fr.la.jproductbaseweb.converter.ConfigurationProductFamiliyConverter;
import fr.la.jproductbaseweb.converter.ProductConfModelConverter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import org.primefaces.context.RequestContext;
import org.primefaces.event.TabChangeEvent;

@ManagedBean(name = "gestConfigurationProductBean")
@ApplicationScoped
public class GestConfigurationProduct extends GestFormAbstract<ProductConf> {

    private String referenceConfProduct;
    private String minorIndexConfProduct;
    private String majorIndexConfProduct;
    private boolean identifiableConfProduct;
    private int stateConfProduct;
    private List<Software> softwareList;
    private List<ProductConfModel> productConfModelList;
    private List<ProductFamily> productFamilyList;
    private List<ProductSupply> productSupplyList;
    private ProductConfLazy listProductConf = new ProductConfLazy();
    private List<FollowingFormModel> followingFormModelList;
    private List<ProductConf> productConfsList;
    private List<ElementBean> cardElementList;
    private List<SoftwareBean> softwareElementList;
    private ProductConfModel selectedProductConfModel;
    private ProductFamily selectedProductFamily;
    private ProductSupply selectedProductSupply;
    private FollowingFormModel selectedFollowingFormModel;
    private ElementBean selectedElement;

    public GestConfigurationProduct() {
        super();
        this.refresh();
    }

    public void onTabChangeElement(TabChangeEvent event) {
        try {
            this.getElementCard();
            System.out.println("onTabChangeElement");
        } catch (SQLException ex) {
            Logger.getLogger(GestConfigurationProduct.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ConfigFileReaderException ex) {
            Logger.getLogger(GestConfigurationProduct.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(GestConfigurationProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void onTabChangeSoftware(TabChangeEvent event) {
        try {
            this.getSoftwareElement();
            System.out.println("onTabChangeSoftware");
        } catch (SQLException ex) {
            Logger.getLogger(GestConfigurationProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void closepopup() {
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("confProd.hide()");
        this.refresh();
    }

    private void refresh() {
        this.objectList = this.moduleGolbal.getProductConfs();
		@SuppressWarnings("unused")
		ProductConfModelConverter _productConfModel = new ProductConfModelConverter();
        ConfigurationProductFamiliyConverter _configurationProductFamily = new ConfigurationProductFamiliyConverter();
        this.productConfModelList = moduleGolbal.getActiveProductConfModels();
        for (ProductConfModel l : this.productConfModelList) {
            System.out.println(l.getReference());
        }
        this.productFamilyList = _configurationProductFamily
                .getProductConfList();
        this.productSupplyList = this.moduleGolbal
                .getActiveProductSupplies();
        this.followingFormModelList = this.moduleGolbal
                .getAllActiveFollowingFormModel();
    }

    public void checkBoxElement() {
        List<ProductConf> _listElementSave = new ArrayList<ProductConf>();

        for (ElementBean eb : cardElementList) {
            ProductConf _prodConf = new ProductConf();
            if (eb.isSelectedElement()) {

                _prodConf = eb;
                _listElementSave.add(_prodConf);
            }

        }

        if (this.getSelectedObject() == null) {
            this.selectedObject = new ProductConf();
        }

        this.getSelectedObject().setProductConfComponents(_listElementSave);
        this.productConfsList =  moduleGolbal.getProductConfComponents( this.getSelectedObject() );
    }

    public void checkBoxSoftware() {
        List<Software> _listSoftwareSave = new ArrayList<Software>();

        for (SoftwareBean sb : this.softwareElementList) {
            @SuppressWarnings("unused")
            Software _soft = new Software();
            if (sb.isSelectedSoftware()) {

                _soft = sb;
                _listSoftwareSave.add(sb);
            }

        }

        if (getSelectedObject() == null) {
            this.selectedObject = new ProductConf();
        }
        this.getSelectedObject().setProductConfSoftwares(_listSoftwareSave);

        this.softwareList = this.getSelectedObject().getProductConfSoftwares();

    }

    @Override
    public void selectedModify() {
        RequestContext request = RequestContext.getCurrentInstance();
        request.execute("document.getElementById('form:tabViewElement:idTabElementView').click()");
        // TODO Auto-generated method stub
        super.selectedModify();
        this.referenceConfProduct = getSelectedObject().getReference();
        this.minorIndexConfProduct = getSelectedObject().getMinorIndex();
        this.majorIndexConfProduct = getSelectedObject().getMajorIndex();
        this.identifiableConfProduct = getSelectedObject().isIdentifiable();
        this.selectedProductConfModel = getSelectedObject()
                .getProductConfModel();
        this.selectedProductFamily = getSelectedObject().getProductFamily();
        this.selectedProductSupply = getSelectedObject().getProductSupply();
        this.selectedFollowingFormModel = getSelectedObject()
                .getFollowingForm();
        this.stateConfProduct = getSelectedObject().getState();

        try {
            this.productConfsList = moduleGolbal.getProductConfComponents( getSelectedObject() );
            this.softwareList = getSelectedObject().getProductConfSoftwares();
            this.cardElementList = new ArrayList<ElementBean>();
            getElementCard();
            this.softwareElementList = new ArrayList<SoftwareBean>();
            getSoftwareElement();
        } catch (ConfigFileReaderException e) {
        } catch (IOException e) {
        } catch (SQLException e) {
        }
    }

    private List<SoftwareBean> getSoftwareElement() throws SQLException {
        this.softwareElementList = new ArrayList<SoftwareBean>();
        List<Software> _softwareElementList = this.moduleGolbal.getActiveSoftwares();
        if (this.softwareList != null) {
            for (Software _soft : this.softwareList) {
                SoftwareBean __softwareBean = new SoftwareBean();
                __softwareBean.setIdSoftware(_soft.getIdSoftware());
                __softwareBean.setName(_soft.getName());
                __softwareBean.setState(_soft.getState());
                __softwareBean.setVersion(_soft.getVersion());
                __softwareBean.setSelectedSoftware(true);
                this.softwareElementList.add(__softwareBean);
            }
        }
        for (Software soft : _softwareElementList) {
            SoftwareBean _softwareBean = new SoftwareBean();
            if (getSelectedObject() != null) {
                for (Software softwareInProductConf : getSelectedObject()
                        .getProductConfSoftwares()) {
                    if (softwareInProductConf.getIdSoftware() == soft
                            .getIdSoftware()) {

                        _softwareBean.setSelectedSoftware(true);
                    }
                }
            }
            _softwareBean.setIdSoftware(soft.getIdSoftware());
            _softwareBean.setName(soft.getName());
            _softwareBean.setState(soft.getState());
            _softwareBean.setVersion(soft.getVersion());
            Boolean alreadyinlist = false;
            for (SoftwareBean __soft : this.softwareElementList) {
                if (__soft.getIdSoftware() == soft.getIdSoftware()) {
                    alreadyinlist = true;
                }
            }
            if (alreadyinlist == false) {
                this.softwareElementList.add(_softwareBean);
            }
        }
        return this.softwareElementList;
    }

    private List<ElementBean> getElementCard() throws SQLException,
            ConfigFileReaderException, IOException {
        this.cardElementList = new ArrayList<ElementBean>();
        List<ProductConf> productElementList = this.moduleGolbal.getProductConfs(1);
        if (this.softwareList != null) {
            for (ProductConf _productConf : this.productConfsList) {
                ElementBean __elementBean = new ElementBean();
                __elementBean.setIdProductConf(_productConf.getIdProductConf());
                __elementBean.setReference(_productConf.getReference());
                __elementBean.setMajorIndex(_productConf.getMajorIndex());
                __elementBean.setMinorIndex(_productConf.getMinorIndex());
                __elementBean.setProductConfModel(_productConf.getProductConfModel());
                __elementBean.setIdentifiable(_productConf.isIdentifiable());
                __elementBean.setState(_productConf.getState());
                __elementBean.setSelectedElement(true);
                this.cardElementList.add(__elementBean);
            }
        }
        for (ProductConf productCard : productElementList) {
            ElementBean _elementBean = new ElementBean();
            if (getSelectedObject() != null && (productCard.getIdProductConf() != getSelectedObject().getIdProductConf())) {
                for (ProductConf prodConf : moduleGolbal.getProductConfComponents( getSelectedObject() )) {
                    if (prodConf.getIdProductConf() == productCard.getIdProductConf()) {
                        _elementBean.setSelectedElement(true);
                        System.out.println("1");
                    }
                }
            }
            _elementBean.setIdProductConf(productCard.getIdProductConf());
            _elementBean.setReference(productCard.getReference());
            _elementBean.setMajorIndex(productCard.getMajorIndex());
            _elementBean.setMinorIndex(productCard.getMinorIndex());
            _elementBean.setProductConfModel(productCard.getProductConfModel());
            _elementBean.setIdentifiable(productCard.isIdentifiable());
            _elementBean.setState(productCard.getState());
            Boolean alreadyinlist = false;
            for (ElementBean __element : this.cardElementList) {
                if (__element.getIdProductConf() == productCard.getIdProductConf()) {
                    alreadyinlist = true;
                }
            }
            if (alreadyinlist == false) {
                this.cardElementList.add(_elementBean);
            }
        }
        return cardElementList;
    }

    @Override
    public void selectedCreate() {
        System.out.println("SELECT CREATE");
        this.resetTab();
        super.selectedCreate();
        this.selectedObject = null;
        this.softwareList = null;
        this.selectedProductConfModel = null;
        this.selectedProductFamily = null;
        this.selectedProductSupply = null;
        this.selectedFollowingFormModel = null;
        this.productConfsList = null;
        this.cardElementList = null;
        this.referenceConfProduct = null;
        this.majorIndexConfProduct = null;
        this.minorIndexConfProduct = null;
        this.stateConfProduct = 1;
        this.selectedObject = null;
        try {
            getElementCard();
            getSoftwareElement();
        } catch (SQLException e) {
        } catch (ConfigFileReaderException e) {
        } catch (IOException e) {
        }
    }

    public void resetTab() {
        RequestContext request = RequestContext.getCurrentInstance();
        request.execute("document.getElementById('form:tabViewElement:idTabElementView').click()");
    }

    @Override
    protected void create() throws ProductConfException {
        // TODO Auto-generated method stub
        @SuppressWarnings("unused")
        ProductConfForm _productConfForm = new ProductConfForm(
                this.referenceConfProduct, this.majorIndexConfProduct,
                this.selectedProductFamily, this.minorIndexConfProduct,
                this.identifiableConfProduct, this.stateConfProduct,
                this.selectedProductConfModel, this.selectedProductFamily,
                this.selectedProductSupply, this.selectedFollowingFormModel,
                this.softwareList, this.productConfModelList,
                this.productFamilyList, this.productSupplyList,
                this.followingFormModelList, this.productConfsList,
                this.cardElementList, this.softwareElementList);

        try {
            this.moduleGolbal.setProductConf(null, this.referenceConfProduct,
                    this.majorIndexConfProduct, this.minorIndexConfProduct,
                    this.selectedProductConfModel, this.selectedProductFamily,
                    this.selectedProductSupply, this.identifiableConfProduct,
                    this.stateConfProduct, this.selectedFollowingFormModel,
                    this.productConfsList, this.softwareList);
            this.objectList = this.moduleGolbal.getProductConfs();
        } catch (Exception e) {
        }
    }

    @Override
    protected void update() throws ProductConfException {
        // TODO Auto-generated method stub
        ProductConfForm _productConfForm = new ProductConfForm(
                this.referenceConfProduct, this.majorIndexConfProduct,
                this.selectedProductFamily, this.minorIndexConfProduct,
                this.identifiableConfProduct, this.stateConfProduct,
                this.selectedProductConfModel, this.selectedProductFamily,
                this.selectedProductSupply, this.selectedFollowingFormModel,
                this.softwareList, this.productConfModelList,
                this.productFamilyList, this.productSupplyList,
                this.followingFormModelList, this.productConfsList,
                this.cardElementList, this.softwareElementList);

        try {
            this.moduleGolbal.setProductConf(this.selectedObject,
                    _productConfForm.getReference(),
                    _productConfForm.getMajorIndex(),
                    _productConfForm.getMinorIndex(),
                    _productConfForm.getSelectedProductConfModel(),
                    _productConfForm.getProductFamily(),
                    _productConfForm.getSelectedProductSupply(),
                    _productConfForm.isIdentifiableProductConf(),
                    _productConfForm.getState(),
                    _productConfForm.getSelectedFollowingFormModel(),
                    this.productConfsList, this.softwareList);
            this.objectList = this.moduleGolbal.getProductConfs();
        } catch (Exception e) {
        }
    }

    public String getReferenceConfProduct() {
        return referenceConfProduct;
    }

    public void setReferenceConfProduct(String referenceConfProduct) {
        this.referenceConfProduct = referenceConfProduct;
    }

    public String getMinorIndexConfProduct() {
        return minorIndexConfProduct;
    }

    public void setMinorIndexConfProduct(String minorIndexConfProduct) {
        this.minorIndexConfProduct = minorIndexConfProduct;
    }

    public String getMajorIndexConfProduct() {
        return majorIndexConfProduct;
    }

    public void setMajorIndexConfProduct(String majorIndexConfProduct) {
        this.majorIndexConfProduct = majorIndexConfProduct;
    }

    public List<ProductConfModel> getProductConfList() {
        return productConfModelList;
    }

    public void setProductConfList(List<ProductConfModel> productConfList) {
        this.productConfModelList = productConfList;
    }

    public List<ProductFamily> getProductFamilyList() {
        return productFamilyList;
    }

    public void setProductFamilyList(List<ProductFamily> productFamilyList) {
        this.productFamilyList = productFamilyList;
    }

    public List<ProductSupply> getProductSupplyList() {
        return productSupplyList;
    }

    public void setProductSupplyList(List<ProductSupply> productSupplyList) {
        this.productSupplyList = productSupplyList;
    }

    public List<FollowingFormModel> getFollowingFormModelList() {
        return followingFormModelList;
    }

    public void setFollowingFormModelList(
            List<FollowingFormModel> followingFormModelList) {
        this.followingFormModelList = followingFormModelList;
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

    public boolean isIdentifiableConfProduct() {
        return identifiableConfProduct;
    }

    public void setIdentifiableConfProduct(boolean identifiableConfProduct) {
        this.identifiableConfProduct = identifiableConfProduct;
    }

    public int getStateConfProduct() {
        return stateConfProduct;
    }

    public void setStateConfProduct(int stateConfProduct) {
        this.stateConfProduct = stateConfProduct;
    }

    public List<ProductConfModel> getProductConfModelList() {
        return productConfModelList;
    }

    public void setProductConfModelList(
            List<ProductConfModel> productConfModelList) {
        this.productConfModelList = productConfModelList;
    }

    public List<ProductConf> getProductConfsList() {
        return productConfsList;
    }

    public void setProductConfsList(List<ProductConf> productConfsList) {
        this.productConfsList = productConfsList;
    }

    public List<Software> getSoftwareList() {
        return softwareList;
    }

    public void setSoftwareList(List<Software> softwareList) {
        this.softwareList = softwareList;
    }

    public List<ElementBean> getCardElementList() {
        return cardElementList;
    }

    public void setCardElementList(List<ElementBean> cardElementList) {
        this.cardElementList = cardElementList;
    }

    public ElementBean getSelectedElement() {
        return selectedElement;
    }

    public void setSelectedElement(ElementBean selectedElement) {
        this.selectedElement = selectedElement;
    }

    public List<SoftwareBean> getSoftwareElementList() {
        return softwareElementList;
    }

    public void setSoftwareElementList(List<SoftwareBean> softwareElementList) {
        this.softwareElementList = softwareElementList;
    }

	public ProductConfLazy getListProductConf() {
		return listProductConf;
	}

	public void setListProductConf(ProductConfLazy listProductConf) {
		this.listProductConf = listProductConf;
	}
}