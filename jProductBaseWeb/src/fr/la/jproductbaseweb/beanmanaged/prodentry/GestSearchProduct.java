package fr.la.jproductbaseweb.beanmanaged.prodentry;

import fr.la.configfilereader.ConfigFileReaderException;
import fr.la.jproductbase.metier.Product;
import fr.la.jproductbase.metier.ProductDocument;
import fr.la.jproductbase.metier.ProductFamily;
import fr.la.jproductbase.metier.Software;
import fr.la.jproductbase.service.ServiceInterface;
import fr.la.jproductbaseweb.beanmanaged.CardElementBean;
import fr.la.jproductbaseweb.beanmanaged.SoftwareBean;
import fr.la.jproductbaseweb.beanmanaged.exception.ProductModifyException;
import fr.la.jproductbaseweb.beanmanaged.modelForm.ProductModifyForm;
import fr.la.jproductbaseweb.beanmanaged.modeltable.ProductLazyList;
import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.dialog.Dialog;
import org.primefaces.event.TabChangeEvent;

@ManagedBean(name = "gestSearchProduct")
@SessionScoped
public class GestSearchProduct extends GestFormSearchAbstract<Product> implements Serializable {

    private static final long serialVersionUID = 1L;
    private List<Product> elementList;
    private List<CardElementBean> cardElementList;
    private List<Software> softwareList;
    private List<SoftwareBean> softwareElementList;
    private ProductLazyList products;
    private Product selectCard;
    private CardElementBean selectedCardElement;
    private SoftwareBean selectedSoftwareElement;
    private List<ProductDocument> productDocumentSelected;

    public GestSearchProduct() {
        super();
        this.moduleGlobale = new ServiceInterface();
        this.state = -1;
        this.products = new ProductLazyList(2);

    }

    @Override
    public void getFamiliesListProduct() throws SQLException {
        // TODO Auto-generated method stub
        List<ProductFamily> _prodList = new ArrayList<ProductFamily>();
        _prodList = this.moduleGlobale.getActiveProductFamilies();
        this.productFamilies = new ArrayList<ProductFamily>();
        for (ProductFamily productFamily : _prodList) {
            if (productFamily.getProductType().getName().equals("Produit")) {

                this.productFamilies.add(productFamily);
            }

        }

    }

    @Override
    public void detailAction() {
        // TODO Auto-generated method stub
        System.out.println("detail action "
                + getSelectedObject().getProductConf().getReference());
        this.reference = getSelectedObject().getProductConf().getReference();
        this.serialNumber = getSelectedObject().getSerialNumber();
        this.macAdress = getSelectedObject().getMacAddress();
        this.dateCode = getSelectedObject().getDatecode();
        this.supplierCode = getSelectedObject().getProviderCode();
        this.state = getSelectedObject().getState();
        this.configuration = this.reference + "~"
                + getSelectedObject().getProductConf().getMajorIndex() + "~"
                + getSelectedObject().getProductConf().getMinorIndex();

        try {
            this.elementList = this.selectedObject.getProductComponents();
            getElementCard();
            this.softwareList = this.selectedObject.getProductSoftwares();
            getSoftwareElement();
            this.productDocumentSelected = moduleGlobale.getProductDocuments(getSelectedObject());
        } catch (ConfigFileReaderException e) {
            // TODO Auto-generated catch block
        } catch (IOException e) {
            // TODO Auto-generated catch block
        } catch (SQLException e) {
            // TODO Auto-generated catch block
        }

    }

    private void getSoftwareElement() throws SQLException {
        this.softwareElementList = new ArrayList<SoftwareBean>();
        List<Software> _softwareElementList = this.moduleGlobale
                .getActiveSoftwares();

        for (Software soft : _softwareElementList) {
            SoftwareBean _softwareBean = new SoftwareBean();
            if (getSelectedObject() != null) {
                for (Software softwareInProductConf : this.selectedObject
                        .getProductSoftwares()) {

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

            System.out.println(_softwareBean);
            this.softwareElementList.add(_softwareBean);

        }

    }

    private void getElementCard() throws SQLException,
            ConfigFileReaderException, IOException {
        System.out.println("getElementCard");
        this.cardElementList = new ArrayList<CardElementBean>();
        // get cards list for product
        List<Product> _productList = this.moduleGlobale.getProductsEnables(
                selectedObject.getIdProduct(), null);
        System.out.println(_productList.size());
        System.out.println(getSelectedObject() == null);
        System.out.println("la taille de la liste des cartes pour le produit"
                + this.selectedObject.getProductComponents());
        for (Product productCard : _productList) {
            CardElementBean _elementBean = new CardElementBean();

            for (Product prodConf : this.selectedObject.getProductComponents()) {

                if (prodConf.getProductConf().getIdProductConf() == productCard
                        .getProductConf().getIdProductConf()) {
                    _elementBean.setSelectedElement(true);

                }

            }

            _elementBean.setIdProduct(productCard.getIdProduct());
            _elementBean.setProductConf(productCard.getProductConf());
            _elementBean.setSerialNumber(productCard.getSerialNumber());
            _elementBean.setDatecode(productCard.getDatecode());
            System.out.println(_elementBean);
            this.cardElementList.add(_elementBean);

        }

    }

    public void selectCheckBox() {

        this.elementList = new ArrayList<Product>();
        for (CardElementBean cardElement : this.cardElementList) {

            if (cardElement.isSelectedElement()) {

                this.elementList.add(cardElement);

            }
        }

    }

    public void selectSoftCheckBox() {

        this.softwareList = new ArrayList<Software>();
        for (SoftwareBean soft : this.softwareElementList) {

            if (soft.isSelectedSoftware()) {

                this.softwareList.add(soft);

            }

        }

    }

    public void onTabChange(TabChangeEvent event) {

        System.out.println("change");

    }

    public List<Product> getElementList() {
        return elementList;
    }

    public void setElementList(List<Product> elementList) {
        this.elementList = elementList;
    }

    public Product getSelectCard() {
        return selectCard;
    }

    public void setSelectCard(Product selectCard) {
        this.selectCard = selectCard;
    }

    public List<CardElementBean> getCardElementList() {
        return cardElementList;
    }

    public void setCardElementList(List<CardElementBean> cardElementList) {
        this.cardElementList = cardElementList;
    }

    public CardElementBean getSelectedCardElement() {
        return selectedCardElement;
    }

    public void setSelectedCardElement(CardElementBean selectedCardElement) {
        this.selectedCardElement = selectedCardElement;
    }

    public List<Software> getSoftwareList() {
        return softwareList;
    }

    public void setSoftwareList(List<Software> softwareList) {
        this.softwareList = softwareList;
    }

    public List<SoftwareBean> getSoftwareElementList() {
        return softwareElementList;
    }

    public void setSoftwareElementList(List<SoftwareBean> softwareElementList) {
        this.softwareElementList = softwareElementList;
    }

    public SoftwareBean getSelectedSoftwareElement() {
        return selectedSoftwareElement;
    }

    public void setSelectedSoftwareElement(SoftwareBean selectedSoftwareElement) {
        this.selectedSoftwareElement = selectedSoftwareElement;
    }

    @Override
    public void modifyProduct(ActionEvent event) {
        // TODO Auto-generated method stub
        CommandButton _commandButton = (CommandButton) event.getSource();
        Dialog _dialog = getDialogToButton(_commandButton);
        System.out.println("Liste des Elements : " + this.elementList.toString());
        System.out.println("selectedObject : " + this.selectedObject.toString());
        this.selectedObject.setProductComponents(this.elementList);
        this.selectedObject.setProductSoftwares(this.softwareList);
        System.out.println(this.selectedObject);

        try {
            @SuppressWarnings("unused")
            ProductModifyForm _productModForm = new ProductModifyForm(
                    this.serialNumber, this.dateCode, this.macAdress,
                    this.supplierCode, this.state, this.elementList,
                    this.softwareList);
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Modification Reussie"));
            this.moduleGlobale.setProduct(this.selectedObject,
                    this.selectedObject.getProductConf(), this.serialNumber,
                    this.dateCode, this.macAdress, this.supplierCode,
                    this.state, this.elementList, this.softwareList);
            hideDialog(_dialog);
        } catch (ProductModifyException e1) {
            // TODO Auto-generated catch block
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                    "Erreur Formulaire", e1.toString()));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        this.products = new ProductLazyList(2);
    }

    @Override
    public void searchProduct() {
        if (this.selectedProductFamily == null) {
            this.products = new ProductLazyList(2);

        } else {
            this.products = new ProductLazyList(2);

        }
    }

    public ProductLazyList getProducts() {
        return products;
    }

    public void setProducts(ProductLazyList products) {
        this.products = products;
    }

    public List<ProductDocument> getProductDocumentSelected() {
        return productDocumentSelected;
    }

    public void setProductDocumentSelected(List<ProductDocument> productDocumentSelected) {
        this.productDocumentSelected = productDocumentSelected;
    }
}
