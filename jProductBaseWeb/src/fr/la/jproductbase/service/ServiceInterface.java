package fr.la.jproductbase.service;

import fr.la.configfilereader.ConfigFileReaderException;
import fr.la.jproductbase.dao.AfterSaleReportDaoException;
import fr.la.jproductbase.dao.ConnectionOperator;
import fr.la.jproductbase.dao.ConnectionProduct;
import fr.la.jproductbase.dao.ConnectionTester;
import fr.la.jproductbase.dao.CustomerCommentDaoException;
import fr.la.jproductbase.dao.ElementChangedDaoException;
import fr.la.jproductbase.dao.FailureDaoException;
import fr.la.jproductbase.dao.FailureReportCommentDaoException;
import fr.la.jproductbase.dao.OperatorDaoException;
import fr.la.jproductbase.dao.ProductDaoException;
import fr.la.jproductbase.dao.ProductDocumentDaoException;
import fr.la.jproductbase.dao.ProductDocumentTypeDaoException;
import fr.la.jproductbase.dao.ProductFamilyDaoException;
import fr.la.jproductbase.dao.SoftwareDaoException;
import fr.la.jproductbase.dao.TestTypeDaoException;
import fr.la.jproductbase.dao.TesterDaoException;
import fr.la.jproductbase.dao.TesterReportDaoException;
import fr.la.jproductbase.metier.AfterSaleCom;
import fr.la.jproductbase.metier.AfterSaleReport;
import fr.la.jproductbase.metier.ApparentCause;
import fr.la.jproductbase.metier.ApparentCauseCustomer;
import fr.la.jproductbase.metier.CustomerComment;
import fr.la.jproductbase.metier.Failure;
import fr.la.jproductbase.metier.FailureReportComment;
import fr.la.jproductbase.metier.FollowingFormModel;
import fr.la.jproductbase.metier.JProductBaseException;
import fr.la.jproductbase.metier.LabviewTestType;
import fr.la.jproductbase.metier.LabviewTesterReport;
import fr.la.jproductbase.metier.LabviewTesterReportException;
import fr.la.jproductbase.metier.Operator;
import fr.la.jproductbase.metier.PreTesterReport;
import fr.la.jproductbase.metier.Product;
import fr.la.jproductbase.metier.ProductConf;
import fr.la.jproductbase.metier.ProductConfModel;
import fr.la.jproductbase.metier.ProductDocument;
import fr.la.jproductbase.metier.ProductDocumentType;
import fr.la.jproductbase.metier.ProductFamily;
import fr.la.jproductbase.metier.ProductSupply;
import fr.la.jproductbase.metier.ProductType;
import fr.la.jproductbase.metier.ProductionFailureReport;
import fr.la.jproductbase.metier.Software;
import fr.la.jproductbase.metier.TestType;
import fr.la.jproductbase.metier.Tester;
import fr.la.jproductbase.metier.TesterReport;
import fr.la.jproductbase.metier.TesterReportException;
import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.naming.NamingException;
import javax.xml.parsers.ParserConfigurationException;
import org.jdom.JDOMException;
import org.xml.sax.SAXException;

public class ServiceInterface implements Serializable {

    private static final long serialVersionUID = 1L;
    private ConnectionOperator cnxOperator;
    private ConnectionProduct cnxProduct;
    private ConnectionTester cnxTester;

    public ServiceInterface() {
        try {
            this.cnxOperator = new ConnectionOperator();
            this.cnxProduct = new ConnectionProduct();
            this.cnxTester = new ConnectionTester();
        } catch (ConfigFileReaderException | IOException | SQLException e) {
        	throw new RuntimeException(e);
        }
    }

    /*
     * ApparentCause
     */
    /**
     * Recherche une cause probable de la base de donn&eacute;es.
     *
     * @return Causes probable.
     *
     * @throws SQLException
     */
    public ApparentCause getApparentCause(int idApparentCause)
            throws SQLException {
        ApparentCauseModule _apparentCauseModule = new ApparentCauseModule(
                this.cnxProduct);
        ApparentCause _apparentCause = _apparentCauseModule
                .getApparentCause(idApparentCause);

        // Close connections
        this.closeConnections();

        return _apparentCause;
    }

    /**
     * Recherche les causes probables de la base de donn&eacute;es.
     *
     * @return Liste des causes probables.
     *
     * @throws SQLException
     */
    public List<ApparentCause> getApparentCauses() throws SQLException {
        ApparentCauseModule _apparentCauseModule = new ApparentCauseModule(
                this.cnxProduct);
        List<ApparentCause> _apparentCauses = _apparentCauseModule
                .getApparentCauses();

        // Close connections
        this.closeConnections();

        return _apparentCauses;
    }

    // TODO Javadoc
    public ApparentCause setApparentCause(ApparentCause apparentCause,
            int state, String description,
            ApparentCauseCustomer apparentCauseCustomer) throws Exception {
        ApparentCauseModule _apparentCauseModule = new ApparentCauseModule(
                this.cnxProduct);
        ApparentCause _apparentCause = _apparentCauseModule.setApparentCause(
                apparentCause, state, description, apparentCauseCustomer);

        // Close connections
        this.closeConnections();

        return _apparentCause;
    }

    /*
     * ApparentCauseCustomer
     */
    /**
     * Recherche une cause probable client de la base de donn&eacute;es.
     *
     * @return Cause probable client.
     *
     * @throws SQLException
     */
    public ApparentCauseCustomer getApparentCauseCustomer(
            int idApparentCauseCustomer) throws SQLException {
        ApparentCauseModule _apparentCauseModule = new ApparentCauseModule(
                this.cnxProduct);
        ApparentCauseCustomer _apparentCauseCustomer = _apparentCauseModule
                .getApparentCauseCustomer(idApparentCauseCustomer);

        // Close connections
        this.closeConnections();

        return _apparentCauseCustomer;
    }

    /**
     * Recherche les causes probables client de la base de donn&eacute;es.
     *
     * @return Liste des causes probables client.
     *
     * @throws SQLException
     */
    public List<ApparentCauseCustomer> getApparentCausesCustomer()
            throws SQLException {
        ApparentCauseModule _apparentCauseModule = new ApparentCauseModule(
                this.cnxProduct);
        List<ApparentCauseCustomer> _apparentCausesCustomer = _apparentCauseModule
                .getApparentCausesCustomer();

        // Close connections
        this.closeConnections();

        return _apparentCausesCustomer;
    }

    /**
     * Recherche les causes probables client actives de la base de
     * donn&eacute;es.
     *
     * @return Liste des causes probables client.
     *
     * @throws SQLException
     */
    public List<ApparentCauseCustomer> getActiveApparentCausesCustomer()
            throws SQLException {
        ApparentCauseModule _apparentCauseModule = new ApparentCauseModule(
                this.cnxProduct);
        List<ApparentCauseCustomer> _apparentCausesCustomer = _apparentCauseModule
                .getActiveApparentCausesCustomer();

        // Close connections
        this.closeConnections();

        return _apparentCausesCustomer;
    }

    // TODO Javadoc
    public ApparentCauseCustomer setApparentCauseCustomer(
            ApparentCauseCustomer apparentCauseCustomer, int state,
            String description) throws Exception {
        ApparentCauseModule _apparentCauseModule = new ApparentCauseModule(
                this.cnxProduct);
        ApparentCauseCustomer _apparentCauseCustomer = _apparentCauseModule
                .setApparentCauseCustomer(apparentCauseCustomer, state,
                description);

        // Close connections
        this.closeConnections();

        return _apparentCauseCustomer;
    }

    /*
     * CustomerComment
     */
    /**
     * Recherche le commentaire client d'un rapport de d&eacute;fauts.
     *
     * @param productionFailureReport Rapport de d&eacute;fauts.
     *
     * @return Commentaire client.
     *
     * @throws SQLException
     */
    public CustomerComment getCustomerComment(
            ProductionFailureReport productionFailureReport)
            throws SQLException {
        FailureModule _failureModule = new FailureModule(this.cnxProduct,
                this.cnxOperator, this.cnxTester);
        CustomerComment _customerComment = _failureModule
                .getCustomerComment(productionFailureReport);

        // Close connections
        this.closeConnections();

        return _customerComment;
    }

    /*
     * FailureReport
     */
    /**
     * Cl&ocirc;ture d'un rapport de d&eacute;fauts.
     *
     * @throws Exception
     */
    public void closeFailureReport(
            ProductionFailureReport productionFailureReport) throws Exception {
        FailureModule _failureModule = new FailureModule(this.cnxProduct,
                this.cnxOperator, this.cnxTester);
        _failureModule.closeProductionFailureReport(productionFailureReport);

        // Close connections
        this.closeConnections();
    }

    // TODO : Javadoc
    public ProductionFailureReport getProductionFailureReport(
            int idProductionFailureReport) throws SQLException,
            ConfigFileReaderException, IOException {
        FailureModule _failureModule = new FailureModule(this.cnxProduct,
                this.cnxOperator, this.cnxTester);
        ProductionFailureReport _failureReport = _failureModule
                .getProductionFailureReport(idProductionFailureReport);

        // Close connections
        this.closeConnections();

        return _failureReport;
    }

    /**
     * Recherche les rapports de d&eacute;fauts d'un produit de la base de
     * donn&eacute;es.
     *
     * @param product Produit concern&eacute;.
     *
     * @return Liste des rapports de d&eacute;fauts du produit.
     *
     * @throws SQLException
     * @throws IOException
     * @throws ConfigFileReaderException
     */
    public List<ProductionFailureReport> getFailureReports(Product product)
            throws SQLException, ConfigFileReaderException, IOException {
        FailureModule _failureModule = new FailureModule(this.cnxProduct,
                this.cnxOperator, this.cnxTester);
        List<ProductionFailureReport> _failureReports = _failureModule
                .getProductionFailureReports(product);

        // Close connections
        this.closeConnections();

        return _failureReports;
    }

    /**
     * Recherche les rapports de d&eacute;fauts d'une p&eacute;riode de la base
     * de donn&eacute;es.
     *
     * @param fromDate Date de d&eacute;but.
     * @param toDate Date de fin.
     *
     * @return Liste des rapports de d&eacute;fauts du produit.
     *
     * @throws SQLException
     * @throws IOException
     * @throws ConfigFileReaderException
     * @throws GestRapportDefaultsException
     */
    public List<ProductionFailureReport> getProductionFailureReports(
            Date fromDate, Date toDate) throws SQLException,
            ConfigFileReaderException, IOException {
        FailureModule _failureModule = new FailureModule(this.cnxProduct,
                this.cnxOperator, this.cnxTester);
        List<ProductionFailureReport> _failureReports = _failureModule
                .getProductionFailureReports(fromDate, toDate);

        // Close connections
        this.closeConnections();

        return _failureReports;
    }

    /**
     * Recherche le rapport de d&eacute;fauts d'un rapport de testeur de la base
     * de donn&eacute;es.
     *
     * @param testerReport Rapport de testeur.
     *
     * @return Liste des rapports de d&eacute;fauts du rapport de testeur.
     *
     * @throws SQLException
     */
    protected ProductionFailureReport getFailureReport(TesterReport testerReport)
            throws SQLException {
        FailureModule _failureModule = new FailureModule(this.cnxProduct,
                this.cnxOperator, this.cnxTester);
        ProductionFailureReport _failureReport = _failureModule
                .getProductionFailureReport(testerReport);

        // Close connections
        this.closeConnections();

        return _failureReport;
    }

    // TODO : Javadoc
    public List<ProductionFailureReport> getUnclosedProductionFailureReports()
            throws SQLException, ConfigFileReaderException, IOException {
        FailureModule _failureModule = new FailureModule(this.cnxProduct,
                this.cnxOperator, this.cnxTester);
        List<ProductionFailureReport> _failureReports = _failureModule
                .getUnclosedProductionFailureReports();

        // Close connections
        this.closeConnections();

        return _failureReports;
    }

    /**
     * Enregistre un rapport de d&eacute;faut dans la base de donn&eacute;s.
     *
     * @param failureReport Rapport de d&eacute;faut.
     * @param failureReportDate Date du rapport de d&eacute;faut.
     * @param productConfReference R&eacute;f&eacute;rence de la configuration
     * produit.
     * @param productConfMajorIndex Indice majeur de la configuration produit.
     * @param productMinorIndex Indice mineur de la configuration produit.
     * @param serialNumber Num&eacute;ro de s&eacute;rie du produit.
     * @param datecode Datecode du produit.
     * @param testTypeName Type de test.
     * @param testerName Nom du testeur.
     * @param operatorCode Code de l'op&eacute;rateur.
     * @param customerComment Description du d&eacute;faut.
     * @param failureCode Code d&eacute;faut.
     * @param comment Commentaire.
     * @param failures
     *
     * @return FailureReport Rapport de d&eacute;faut.
     *
     * @throws SQLException
     * @throws ProductDaoException
     * @throws FailureModuleException
     * @throws ParseException
     * @throws IOException
     * @throws ConfigFileReaderException
     * @throws TesterReportDaoException
     * @throws NamingException
     */
    public ProductionFailureReport setFailureReport(
            ProductionFailureReport failureReport, Date failureReportDate,
            String productConfReference, String productConfMajorIndex,
            String productMinorIndex, String serialNumber, String datecode,
            String testTypeName, String testerName, String operatorCode,
            String customerComment, String failureCode, String comment,
            List<Failure> failures) throws SQLException, JProductBaseException,
            ProductDaoException, FailureModuleException,
            ConfigFileReaderException, IOException, ParseException,
            TesterReportDaoException, NamingException {
        FailureModule _failureModule = new FailureModule(this.cnxProduct,
                this.cnxOperator, this.cnxTester);
        ProductionFailureReport _failureReport = _failureModule
                .setProductionFailureReport(failureReport, failureReportDate,
                productConfReference, productConfMajorIndex,
                productMinorIndex, serialNumber, datecode,
                testTypeName, testerName, operatorCode,
                customerComment, failureCode, comment, failures);

        // Close connections
        this.closeConnections();

        return _failureReport;
    }

    public Failure addFailureDismantedCard(Failure failure,
            ProductionFailureReport productionFailureReport) {

        FailureModule _failureModule = new FailureModule(this.cnxProduct,
                this.cnxOperator, this.cnxTester);
        System.out.println("valeur de failure " + failure);
        Failure _failure = _failureModule.addFailureDismantedCard(
                productionFailureReport, failure);
        System.out.println("return failure " + _failure);
        return _failure;
    }

    /**
     * Enregistre un rapport de d&eacute;faut dans la base de donn&eacute;s.
     *
     * @param testerReport Rapport d'un testeur.
     *
     * @return FailureReport Rapport de d&eacute;faut.
     *
     * @throws SQLException
     * @throws FailureModuleException
     * @throws IOException
     * @throws ConfigFileReaderException
     * @throws NamingException
     */
    public ProductionFailureReport setFailureReport(TesterReport testerReport,
            String customerComment) throws SQLException,
            FailureModuleException, ConfigFileReaderException, IOException,
            NamingException {
        FailureModule _failureModule = new FailureModule(this.cnxProduct,
                this.cnxOperator, this.cnxTester);
        ProductionFailureReport _failureReport = _failureModule
                .setProductionFailureReport(testerReport, customerComment);

        // Close connections
        this.closeConnections();

        return _failureReport;
    }

    public ProductionFailureReport updateFailureReport(
            ProductionFailureReport productionFailureReport)
            throws SQLException, JProductBaseException, ProductDaoException,
            FailureModuleException, ConfigFileReaderException, IOException,
            ParseException, TesterReportDaoException, NamingException {
        FailureModule _failureModule = new FailureModule(this.cnxProduct,
                this.cnxOperator, this.cnxTester);
        ProductionFailureReport _failureReport = _failureModule
                .updateProductionFailureReport(productionFailureReport);

        // Close connections
        this.closeConnections();

        return _failureReport;
    }

    /*
     * FailureReportComment
     */
    public FailureReportComment getFailureReportComment(
            ProductionFailureReport productionFailureReport)
            throws SQLException {
        FailureModule _failureModule = new FailureModule(this.cnxProduct,
                this.cnxOperator, this.cnxTester);
        FailureReportComment _failureReportComment = _failureModule
                .getFailureReportComment(productionFailureReport);

        // Close connections
        this.closeConnections();

        return _failureReportComment;
    }

    /*
     * FollowingFormModel
     */
    /**
     * Recherche les mod&egrave;les de fiche suiveuse actifs de la base de
     * donn&eacute;es.
     *
     * @return Liste des mod&egrave;les de fiches suiveuse actifs.
     *
     * @throws ConfigFileReaderException
     * @throws IOException
     * @throws SQLException
     */
    public List<FollowingFormModel> getAllActiveFollowingFormModel()
            throws ConfigFileReaderException, IOException, SQLException {
        FollowingFormModule _followingFormModule = new FollowingFormModule(
                this.cnxProduct);
        List<FollowingFormModel> _followingFormModels = _followingFormModule
                .getAllActiveFollowingFormModel();

        // Close connections
        this.closeConnections();

        return _followingFormModels;
    }

    /**
     * Recherche un mod&egrave;le de fiche suiveuse de la base de
     * donn&eacute;es.
     *
     * @param idFollowingFormModel Identifiant du mod&egrave;le de fiche
     * suiveuse.
     *
     * @return Mod&egrave;le de fiche suiveuse
     *
     * @throws ConfigFileReaderException
     * @throws IOException
     * @throws SQLException
     */
    public FollowingFormModel getFollowingFormModel(int idFollowingFormModel)
            throws ConfigFileReaderException, IOException, SQLException {
        FollowingFormModule _followingFormModule = new FollowingFormModule(
                this.cnxProduct);
        FollowingFormModel _followingFormModel = _followingFormModule
                .getFollowingFormModel(idFollowingFormModel);

        // Close connections
        this.closeConnections();

        return _followingFormModel;
    }

    /*
     * Operator
     */
    // TODO Javadoc
    public Operator addOperator(String firstName, String lastName, String code,
            int state) throws SQLException, OperatorDaoException,
            NamingException {
        OperatorModule _operatorModule = new OperatorModule(this.cnxOperator);
        Operator _operator = _operatorModule.addOperator(firstName, lastName,
                code, state);

        // Close connections
        this.closeConnections();

        return _operator;
    }

    // TODO Javadoc
    public Operator getOperator(int idOperator) throws SQLException {
        OperatorModule _operatorModule = new OperatorModule(this.cnxOperator);
        Operator _operator = _operatorModule.getOperator(idOperator);

        // Close connections
        this.closeConnections();

        return _operator;
    }

    // TODO Javadoc
    public Operator getOperator(String code) throws SQLException {
        OperatorModule _operatorModule = new OperatorModule(this.cnxOperator);
        Operator _operator = _operatorModule.getOperator(code);

        // Close connections
        this.closeConnections();

        return _operator;
    }

    // TODO Javadoc
    public List<Operator> getOperators() throws SQLException {
        OperatorModule _operatorModule = new OperatorModule(this.cnxOperator);
        List<Operator> _operators = _operatorModule.getOperators();

        // Close connections
        this.closeConnections();

        return _operators;
    }

    // TODO Javadoc
    public List<Operator> getActiveOperators() throws SQLException {
        OperatorModule _operatorModule = new OperatorModule(this.cnxOperator);
        List<Operator> _operators = _operatorModule.getActiveOperators();

        // Close connections
        this.closeConnections();

        return _operators;
    }

    // TODO Javadoc
    public void updateOperator(Operator operator) throws SQLException,
            NamingException {
        OperatorModule _operatorModule = new OperatorModule(this.cnxOperator);
        _operatorModule.updateOperator(operator);

        // Close connections
        this.closeConnections();
    }

    /*
     * Product
     */
    /**
     * Ajout d'un produit dans la base de donn&eacute;es.
     *
     * @param productConf Configuration produit.
     * @param serialNumber Num&eacute;ro de s&eacute;rie.
     * @param datecode Datecode.
     *
     * @return Produit.
     * @throws NamingException
     */
    public Product addProduct(ProductConf productConf, String serialNumber,
            String datecode, String providerCode) throws SQLException,
            ProductDaoException, NamingException {
        ProductModule _productModule = new ProductModule(this.cnxProduct);
        Product _product = _productModule.addProduct(productConf, serialNumber,
                datecode, providerCode);

        // Close connections
        this.closeConnections();

        return _product;
    }

    /**
     * Recherche un produit de la base de donn&eacute;es.
     *
     * @param idProduct Identifiant du produit.
     *
     * @return Produit.
     *
     * @throws SQLException
     */
    public Product getProduct(int idProduct) throws SQLException {
        ProductModule _productModule = new ProductModule(this.cnxProduct);
        Product _product = _productModule.getProduct(idProduct);

        // Close connections
        this.closeConnections();

        return _product;
    }

    /**
     * Retourne le nombre de résultat d'une requete
     *
     * @param idModele
     * @param refConf
     * @param majorIndexConf
     * @param minorIndexConf
     * @param dateCode
     * @param serialNumber
     * @param idType
     * @param idFamily
     * @param macAddress
     * @param providerCode
     * @param state
     * @return le nombre de resultat
     * @throws SQLException
     */
    public int getCountQuery(Map<String, String> filters, int type) throws SQLException {

        ProductModule _productModule = new ProductModule(this.cnxProduct);

        int _count = _productModule.countQueryResult(filters, type);

        // Close connections
        this.closeConnections();

        return _count;

    }

    /**
     * Recherche un produit de la base de donn&eacute;es.
     *
     * @param productConfReference R&eacute;f&eacute;rence de la configuration
     * produit.
     * @param serialNumber Num&eacute;ro de s&eacute;rie du produit.
     * @param datecode Datecode du produit.
     *
     * @return Produit.
     *
     * @throws SQLException
     */
    public Product getProduct(String productConfReference, String serialNumber,
            String datecode) throws SQLException {
        ProductModule _productModule = new ProductModule(this.cnxProduct);
        Product _product = _productModule.getProduct(productConfReference,
                serialNumber, datecode);

        // Close connections
        this.closeConnections();

        return _product;
    }

    /**
     * Recherche un produit de la base de donn&eacute;es.
     *
     * @param productConf Configuration produit.
     * @param serialNumber Num&eacute;ro de s&eacute;rie du produit.
     * @param datecode Datecode du produit.
     *
     * @return Produit.
     *
     * @throws SQLException
     */
    public Product getProduct(ProductConf productConf, String serialNumber,
            String datecode) throws SQLException {
        ProductModule _productModule = new ProductModule(this.cnxProduct);
        Product _product = _productModule.getProduct(productConf, serialNumber,
                datecode);

        // Close connections
        this.closeConnections();

        return _product;
    }

    /**
     * Recherche le produit principal auquel une carte appartient.
     *
     * @param carte carte de laquelle on cherche le produit principal.
     *
     * @return Produit.
     *
     * @throws SQLException
     */
    public Product getMainProduct(Product carte) throws SQLException {
        ProductModule _productModule = new ProductModule(this.cnxProduct);
        Product _product = _productModule.getMainProduct(carte);

        // Close connections
        this.closeConnections();

        return _product;
    }

    /**
     * Recherche les produits de la base de donn&eacute;es en fonction du type
     * de produit.
     *
     * @param productType Type de produit.
     *
     * @return Liste des produits.
     *
     * @throws SQLException
     */
    public List<Product> getProducts(ProductType productType)
            throws SQLException {
        ProductModule _productModule = new ProductModule(this.cnxProduct);
        List<Product> _products = _productModule.getProducts(productType);

        // Close connections
        this.closeConnections();

        return _products;
    }

    /**
     * Recherche les produits de la base de donn&eacute;es en fonction du
     * mod&eacute;le de configuration produit.
     *
     * @param productConfModel Mod&eacute;le de configuration produit.
     *
     * @return Liste des produits.
     *
     * @throws SQLException
     */
    public List<Product> getProducts(ProductConfModel productConfModel)
            throws SQLException {
        ProductModule _productModule = new ProductModule(this.cnxProduct);
        List<Product> _products = _productModule.getProducts(productConfModel);

        // Close connections
        this.closeConnections();

        return _products;
    }

    /**
     * Recherche les produits de la base de donn&eacute;es en fonction de la
     * configuration produit.
     *
     * @param productConf Configuration produit.
     *
     * @return Liste des produits.
     *
     * @throws SQLException
     * @throws IOException
     * @throws ConfigFileReaderException
     */
    public List<Product> getProducts(ProductConf productConf)
            throws SQLException, ConfigFileReaderException, IOException {
        ProductModule _productModule = new ProductModule(this.cnxProduct);
        List<Product> _products = _productModule.getProducts(productConf);

        // Close connections
        this.closeConnections();

        return _products;
    }

    /**
     * Recherche les cartes disponibles donc non rattachés à un produit de la
     * base de donn&eacute;es en fonction de la reference de la configuration
     * produit de la carte.
     *
     * @param reference Reference de la config produit d'une carte.
     *
     * @return Liste des produits.
     *
     * @throws SQLException
     */
    public List<Product> getProductsEnables(int idproduct, String reference)
            throws SQLException {
        ProductModule _productModule = new ProductModule(this.cnxProduct);
        List<Product> _products = _productModule.getProductsEnables(idproduct,
                reference);

        // Close connections
        this.closeConnections();

        return _products;
    }

    /**
     * Recherche les produits enregistrables donc présents dans la base FEDD
     * avec un contôle final ok et non-présents dans la abse LAI. Possibilité de
     * filter sur un modèle (T101, T102, etc.).
     *
     * @param modele Modèle de produit.
     *
     * @return Liste des produits.
     *
     * @throws SQLException
     */
    public List<Product> getProductsRecordables(String modele)
            throws SQLException {
        ProductModule _productModule = new ProductModule(this.cnxProduct);
        List<Product> _products = _productModule.getProductsRecordables(modele);

        // Close connections
        this.closeConnections();

        return _products;
    }

    /**
     * Recherche les produits de la base de donn&eacute;es en fonction des
     * critères selectionés sur l'ecran de recherche produits.
     *
     * @param idModele Modéle du produit.
     * @param refConf Reference de la configuration du produit.
     * @param majorIndexConf Indice majeur de la configuration du produit.
     * @param minorIndexConf Indice mineur de la configuration du produit.
     * @param dateCode Date code du produit.
     * @param serialNumber Numéro de série du produit.
     * @param idType Identifiant du type du produit.
     * @param idFamily Identifiant de la famille du produit.
     * @param macAddress Adresse Mac du produit.
     * @param providerCode Code fournisseur du produit.
     * @param state Etat du produit.
     *
     * @return Liste des produits.
     *
     * @throws SQLException
     * @throws IOException
     * @throws ConfigFileReaderException
     */
    public List<Product> getProductsSearch(int startingAt, int maxPerPage, Map<String, String> filters, int type)
            throws SQLException, ConfigFileReaderException, IOException {

        ProductModule _productModule = new ProductModule(this.cnxProduct);

        List<Product> _products = _productModule.getProductsSearch(startingAt, maxPerPage, filters, type);

        // Close connections
        this.closeConnections();

        return _products;
    }

    /**
     * Recherche les composants d'un produit de la base de donn&eacute;es.
     *
     * @param product Produit auquel appartiennent les composants.
     *
     * @return Composants du produit.
     *
     * @throws SQLException
     */
    public List<Product> getProductComponents(Product product)
            throws SQLException {
        ProductModule _productModule = new ProductModule(this.cnxProduct);
        List<Product> _products = _productModule.getProductComponents(product);

        // Close connections
        this.closeConnections();

        return _products;
    }

    /**
     * Recherche les produits de la base de donn&eacute;es en fonction du
     * num&eacute;ro de s&eacute;rie et du datecode.
     *
     * @param serialNumber Num&eacute;ro de s&eacute;rie.
     * @param datecode Datecode.
     *
     * @return Liste des produits.
     *
     * @throws SQLException
     */
    public List<Product> getProducts(String serialNumber, String datecode)
            throws SQLException {
        ProductModule _productModule = new ProductModule(this.cnxProduct);
        List<Product> _products = _productModule.getProducts(serialNumber,
                datecode);

        // Close connections
        this.closeConnections();

        return _products;
    }

    /**
     * Recherche les produits devant faire l'objet d'une demande de
     * d&eacute;rogation.
     *
     * @return Produits devant faire l'objet d'une demande de d&eacute;rogation.
     *
     * @throws SQLException
     */
    public List<Product> getProductsDispensationNeeded() throws SQLException {
        ProductModule _productModule = new ProductModule(this.cnxProduct);
        List<Product> _products = _productModule
                .getProductsDispensationNeeded();

        // Close connections
        this.closeConnections();

        return _products;
    }

    /**
     * D&eacute;termine si un produit doit faire l'objet d'une demande de
     * d&eacute;rogation.
     *
     * @return Le produits doit faire l'objet d'une demande de
     * d&eacute;rogation.
     *
     * @throws SQLException
     */
    protected boolean isNeedDispensation(Product product) throws SQLException {
        ProductModule _productModule = new ProductModule(this.cnxProduct);
        boolean _isNeedDispensation = _productModule
                .isNeedDispensation(product);

        // Close connections
        this.closeConnections();

        return _isNeedDispensation;
    }

    /**
     * Recherche un produit dans la base de donn&eacute;es.
     *
     * @param productConfReference R&eacute;f&eacute;rence de la configuration
     * produit.
     *
     * @param serialNumber Num&eacute;ro de s&eacute;rie du produit.
     *
     * @param datecode Datecode du produit.
     *
     * @return Produit.
     */
    public Product retreiveProduct(String productConfReference,
            String serialNumber, String datecode)
            throws ConfigFileReaderException, IOException, SQLException,
            JProductBaseException {
        ProductModule _productModule = new ProductModule(this.cnxProduct);
        Product _product = _productModule.retreiveProduct(productConfReference,
                serialNumber, datecode);

        // Close connections
        this.closeConnections();

        return _product;
    }

    /**
     * Sauvegarde un produit.
     *
     * @param product Produit &grave; enregistr&eacute;.
     *
     * @return Produit sauvegard&eacute;.
     * @throws NamingException
     *
     * @throws Exception
     */
    public Product setProduct(Product product) throws SQLException,
            ProductDaoException, ConfigFileReaderException, IOException,
            SoftwareDaoException, JProductBaseException, NamingException {
        ProductModule _productModule = new ProductModule(this.cnxProduct);
        Product _product = _productModule.setProduct(product);

        // Close connections
        this.closeConnections();

        return _product;
    }

    /**
     * Sauvegarde un produit.
     *
     * @param idProduct Produit &grave; enregistr&eacute;.
     * @param productConfReference R&eacute;f&eacute;rence de la configuration
     * produit.
     * @param productConfMajorIndex Indice majeur de la configuration produit.
     * @param productConfMinorIndex Indice mineur de la configuration produit.
     * @param serialNumber Num&eacute;ro de s&eacute;rie du produit.
     * @param datecode Datecode du produit.
     * @param productComponents Composants du produits.
     *
     * @return Produit sauvegard&eacute;.
     *
     * @throws SQLException
     * @throws ProductDaoException
     * @throws JProductBaseException
     * @throws SoftwareDaoException
     * @throws IOException
     * @throws ConfigFileReaderException
     * @throws NamingException
     */
    public Product setProduct(int idProduct, String productConfReference,
            String productConfMajorIndex, String productConfMinorIndex,
            String serialNumber, String datecode, String[][] productComponents,
            String[][] productSoftwares) throws SQLException,
            ProductDaoException, JProductBaseException, SoftwareDaoException,
            ConfigFileReaderException, IOException, NamingException {
        ProductModule _productModule = new ProductModule(this.cnxProduct);
        Product _product = _productModule.setProduct(idProduct,
                productConfReference, productConfMajorIndex,
                productConfMinorIndex, serialNumber, datecode,
                productComponents, productSoftwares);

        // Close connections
        this.closeConnections();

        return _product;
    }

    // 16-04-12 : RMO : Création de la méthode
    /**
     * Enregistre un produit.
     *
     * @param product Produit concern&eacute;.
     * @param productConf configuration du produit.
     * @param serialNumber Num&eacute;ro de s&eacute;rie du produit.
     * @param dateCode Date code du produit.
     * @param macAddress Adresse Mac du produit.
     * @param providerCode Code fournisseur du produit.
     * @param state Etat de l'enregistrement.
     * @param productComponents Composants du produit.
     * @param productSoftwares Logiciels du produit.
     *
     * @return Produit.
     *
     * @throws Exception
     */
    public Product setProduct(Product product, ProductConf productConf,
            String serialNumber, String dateCode, String macAddress,
            String providerCode, int state, List<Product> productComponents,
            List<Software> productSoftwares) throws Exception {
        ProductModule _productModule = new ProductModule(this.cnxProduct);
        Product _product = _productModule.setProduct(product, productConf,
                serialNumber, dateCode, macAddress, providerCode, state,
                productComponents, productSoftwares);
        // Close connections
        this.closeConnections();
        return _product;
    }

    /**
     * Sauvegarde un produit dans un fichier XML.
     *
     * @param productConfReference R&eacute;f&eacute;rence de la configuration
     * produit.
     * @param productConfMajorIndex Indice majeur de la configuration produit.
     * @param productConfMinorIndex Indice mineur de la configuration produit.
     * @param serialNumber Num&eacute;ro de s&eacute;rie du produit.
     * @param datecode Datecode du produit.
     * @param productComponents Composants du produits.
     * @param productSoftwares Logiciels du produits.
     *
     * @throws IOException
     * @throws ConfigFileReaderException
     */
    public void setProductXml(String productConfReference,
            String productConfMajorIndex, String productConfMinorIndex,
            String serialNumber, String datecode, String[][] productComponents,
            String[][] productSoftwares) throws ConfigFileReaderException,
            IOException {
        XMLProductModule _XMLproductModule = new XMLProductModule();
        _XMLproductModule.setProductXml(productConfReference,
                productConfMajorIndex, productConfMinorIndex, serialNumber,
                datecode, productComponents, productSoftwares);

        // Close connections
        this.closeConnections();
    }

    /**
     * Met à jour un produit.
     *
     * @param product Produit concern&eacute;.
     * @param macAddress Adresse Mac du produit.
     *
     * @throws ProductDaoException
     * @throws SQLException
     */
    public void updateProduct(Product product, String macAddress)
            throws SQLException, ProductDaoException {
        ProductModule _productModule = new ProductModule(this.cnxProduct);
        _productModule.updateProduct(product, macAddress);

        // Close connections
        this.closeConnections();
    }

    /**
     * Pr&eacute;paration et transfert dans le dossier d'int&eacute;gration d'un
     * rapport de testeur.
     *
     * @param testerReportFileName Rapport testeur &agrave; int&eacute;grer.
     * @param labviewTesterReport Rapport Labview.
     * @param confirmTestResult Confirmation du r&eacute;sultat du test.
     * @param customerComment Commentaire op&eacute;rateur.
     *
     * @throws IOException
     * @throws SAXException
     * @throws ParserConfigurationException
     * @throws ConfigFileReaderException
     * @throws JDOMException
     * @throws LabviewTesterReportException
     * @throws JProductBaseException
     * @throws SQLException
     */
    public void integrationTransfer(String testerReportFileName,
            LabviewTesterReport labviewTesterReport, boolean confirmTestResult,
            StringBuffer customerComment) throws ParserConfigurationException,
            SAXException, IOException, ConfigFileReaderException,
            JDOMException, LabviewTesterReportException, SQLException,
            JProductBaseException {
        XMLProductModule _XMLProductModule = new XMLProductModule();
        _XMLProductModule.integrationTransfer(testerReportFileName,
                labviewTesterReport, confirmTestResult, customerComment);

        // Close connections
        this.closeConnections();
    }

    /*
     * ProductConfModel
     */
    /**
     * Recherche un mod&eacute;le de configuration produit de la base de
     * donn&eacute;es.
     *
     * @param idProductConfModel id du mod&eacute;le.
     *
     * @return Mod&eacute;le de configuration produit.
     *
     * @throws SQLException
     */
    public ProductConfModel getProductConfModel(int idProductConfModel)
            throws SQLException {
        ProductConfModelModule _productConfModelModule = new ProductConfModelModule(
                this.cnxProduct);
        ProductConfModel _productConfModel = _productConfModelModule
                .getProductConfModel(idProductConfModel);

        // Close connections
        this.closeConnections();

        return _productConfModel;
    }

    /**
     * Recherche un mod&eacute;le de configuration produit de la base de
     * donn&eacute;es.
     *
     * @param reference R&eacute;f&eacute;rence du mod&eacute;le.
     *
     * @return Mod&eacute;le de configuration produit.
     *
     * @throws SQLException
     */
    public ProductConfModel getProductConfModel(String reference)
            throws SQLException {
        ProductConfModelModule _productConfModelModule = new ProductConfModelModule(
                this.cnxProduct);
        ProductConfModel _productConfModel = _productConfModelModule
                .getProductConfModel(reference);

        // Close connections
        this.closeConnections();

        return _productConfModel;
    }

    /**
     * Recherche tous les mod&eacute;les de configuration produit de la base de
     * donn&eacute;es.
     *
     * @return Liste de mod&eacute;les de configuration produit.
     *
     * @throws SQLException
     */
    public List<ProductConfModel> getProductConfModels() throws SQLException {
        ProductConfModelModule _productConfModelModule = new ProductConfModelModule(
                this.cnxProduct);
        List<ProductConfModel> _productConfModels = _productConfModelModule
                .getProductConfModels();

        // Close connections
        this.closeConnections();

        return _productConfModels;
    }

    /**
     * Recherche les mod&eacute;les de configurations produit actives de la base
     * de donn&eacute;es.
     *
     * @param productType Type de produit recherch&eacute;e.
     *
     * @return Liste des mod&eacute;les de configurations produit actives.
     *
     * @throws SQLException
     */
    public List<ProductConfModel> getActiveProductConfModels(
            ProductType productType) throws SQLException {
        ProductConfModelModule _productConfModelModule = new ProductConfModelModule(
                this.cnxProduct);
        List<ProductConfModel> _productConfModels = _productConfModelModule
                .getActiveProductConfModels(productType);

        // Close connections
        this.closeConnections();

        return _productConfModels;
    }

    /**
     * Recherche les mod&eacute;les de configurations produit actives de la base
     * de donn&eacute;es.
     *
     * @return Liste des mod&eacute;les de configurations produit actives.
     *
     * @throws SQLException
     */
    public List<ProductConfModel> getActiveProductConfModels()
            throws SQLException {
        ProductConfModelModule _productConfModelModule = new ProductConfModelModule(
                this.cnxProduct);
        List<ProductConfModel> _productConfModels = _productConfModelModule
                .getActiveProductConfModels();

        // Close connections
        this.closeConnections();

        return _productConfModels;
    }

    /**
     * Recherche tous les mod&eacute;les de configuration produit de la base de
     * donn&eacute;es pour un type de produit donn&eacute;.
     *
     * @param type Type de produit
     *
     * @return Liste de mod&eacute;les de configuration produit.
     *
     * @throws SQLException
     */
    public List<ProductConfModel> getProductConfModels(int type)
            throws SQLException {
        ProductConfModelModule _productConfModelModule = new ProductConfModelModule(
                this.cnxProduct);
        List<ProductConfModel> _productConfModels = _productConfModelModule
                .getProductConfModels(type);

        // Close connections
        this.closeConnections();

        return _productConfModels;
    }

    /*
     * ProductConf
     */
    /**
     * Recherche une configuration produit de la base de donn&eacute;es.
     *
     * @param idProductConf Identifiant de la configuration produit.
     *
     * @return Configuration produit.
     *
     * @throws SQLException
     */
    public ProductConf getProductConf(int idProductConf) throws SQLException {
        ProductConfModule _productConfModule = new ProductConfModule(
                this.cnxProduct);
        ProductConf _productConf = _productConfModule
                .getProductConf(idProductConf);

        // Close connections
        this.closeConnections();

        return _productConf;
    }

    /**
     * Recherche la configuration produit &grave; partir de sa
     * r&eacute;f&eacute;rence et son indice majeur.
     *
     * @param reference R&eacute;f&eacute;rence de la configuration produit.
     * @param majorIndex Indice majeur de la configuration produit.
     * @param minorIndex Indice mineur de la configuration produit.
     *
     * @return Configuratoin produit.
     *
     * @throws SQLException
     */
    public ProductConf getProductConf(String reference, String majorIndex,
            String minorIndex) throws SQLException {
        ProductConfModule _productConfModule = new ProductConfModule(
                this.cnxProduct);
        ProductConf _productConf = _productConfModule.getProductConf(reference,
                majorIndex, minorIndex);

        // Close connections
        this.closeConnections();

        return _productConf;
    }

    /**
     * Recherche la derni&eagrave;re configuration produit active de la base de
     * donn&eacute;es.
     *
     * @param reference R&eacute;f&eacute;rence de la configuration;
     * @param majorIndex Indice majeur de la configuration;
     * @param minorIndex Indice mineur de la configuration;
     *
     * @return Derni&eagrave;re configuration produit active.
     *
     * @throws SQLException
     */
    public ProductConf getLastActiveProductConf(String reference,
            String majorIndex, String minorIndex) throws SQLException {
        ProductConfModule _productConfModule = new ProductConfModule(
                this.cnxProduct);
        ProductConf _productConf = _productConfModule.getLastActiveProductConf(
                reference, majorIndex, minorIndex);

        // Close connections
        this.closeConnections();

        return _productConf;
    }

    /**
     * Recherche les configurations produit de la base de donn&eacute;es.
     *
     * @return Liste des configurations produit.
     *
     * @throws SQLException
     */
    public List<ProductConf> getProductConfs() throws SQLException {
        ProductConfModule _productConfModule = new ProductConfModule(
                this.cnxProduct);
        List<ProductConf> _productConfs = _productConfModule.getProductConfs();

        // Close connections
        this.closeConnections();

        return _productConfs;
    }

    /**
     * Recherche les configurations produit de la base de donn&eacute;es par
     * rapport à un type de conf donné.
     *
     * @param type Type de configuration
     *
     * @return Liste des configurations produit.
     *
     * @throws SQLException
     */
    public List<ProductConf> getProductConfs(int type) throws SQLException {
        ProductConfModule _productConfModule = new ProductConfModule(
                this.cnxProduct);
        List<ProductConf> _productConfs = _productConfModule
                .getProductConfs(type);
        // Close connections
        this.closeConnections();

        return _productConfs;
    }

    /**
     * Recherche les configurations produit en fonction d'une
     * r&eacute;f&eacute;rence de la base de donn&eacute;es.
     *
     * @param reference R&eacute;f&eacute;rence recherch&eacute;e.
     *
     * @return Liste des configurations produit.
     *
     * @throws SQLException
     */
    public List<ProductConf> getProductConfs(String reference)
            throws SQLException {
        ProductConfModule _productConfModule = new ProductConfModule(
                this.cnxProduct);
        List<ProductConf> _productConfs = _productConfModule
                .getProductConfs(reference);

        // Close connections
        this.closeConnections();

        return _productConfs;
    }

    /**
     * Recherche les configurations produit actives de la base de
     * donn&eacute;es.
     *
     * @return Liste des configurations produit actifs.
     *
     * @throws SQLException
     */
    public List<ProductConf> getActiveProductConfs() throws SQLException {
        ProductConfModule _productConfModule = new ProductConfModule(
                this.cnxProduct);
        List<ProductConf> _productConfs = _productConfModule
                .getActiveProductConfs();

        // Close connections
        this.closeConnections();

        return _productConfs;
    }

    /**
     * Recherche les configurations produit actives de la base de donn&eacute;es
     * en fonction du type de produit.
     *
     * @param productType Type de produit recherch&eacute;e.
     *
     * @return Liste des configurations produit actives.
     *
     * @throws SQLException
     * @throws IOException
     * @throws ConfigFileReaderException
     */
    public List<ProductConf> getActiveProductConfs(ProductType productType)
            throws SQLException, ConfigFileReaderException, IOException {
        ProductConfModule _productConfModule = new ProductConfModule(
                this.cnxProduct);
        List<ProductConf> _productConfs = _productConfModule
                .getActiveProductConfs(productType);

        // Close connections
        this.closeConnections();

        return _productConfs;
    }

    /**
     * Recherche les composants d'une configuration produit de la base de
     * donn&eacute;es.
     *
     * @return Liste des composants d'une configuration produit.
     *
     * @throws SQLException
     */
    public List<ProductConf> getProductConfComponents(ProductConf productConf)
            throws SQLException {
        ProductConfModule _productConfModule = new ProductConfModule(
                this.cnxProduct);
        List<ProductConf> _productConfs = _productConfModule
                .getProductConfComponents(productConf);

        // Close connections
        this.closeConnections();

        return _productConfs;
    }

    // 20-01-12 : RMO : Création de la méthode
    /**
     * Enregistre une configuration produit.
     *
     * @param productConf Identifiant de la configuration produit.
     * @param reference R&eacute;f&eacute;rence de la configuration produit.
     * @param majorIndex Indice majeur de la configuration produit.
     * @param minorIndex Indice mineur de la configuration produit.
     * @param productConfModel Modele de la configuration produit.
     * @param productFamily Famille du mod&eacute;le de produit.
     * @param productSupply Alimentation de la configuration produit.
     * @param identifiable configuration identifiable ou pas.
     * @param state Etat de l'enregistrement.
     * @param followingFormModel Modele de fiche suiveuse de la configuration
     * produit.
     * @param productConfComponents Composants de la configuration produit.
     * @param productConfSoftwares Logiciels de la configuration produit.
     *
     * @return Configuration produit.
     *
     * @throws Exception
     */
    public ProductConf setProductConf(ProductConf productConf,
            String reference, String majorIndex, String minorIndex,
            ProductConfModel productConfModel, ProductFamily productFamily,
            ProductSupply productSupply, Boolean identifiable, int state,
            FollowingFormModel followingFormModel,
            List<ProductConf> productConfComponents,
            List<Software> productConfSoftwares) throws Exception {
        ProductConfModule _productConfModule = new ProductConfModule(
                this.cnxProduct);

        System.out.println("KNOCK 9999");
        ProductConf _productConf = _productConfModule.setProductConf(
                productConf, reference, majorIndex, minorIndex,
                productConfModel, productFamily, productSupply, identifiable,
                state, followingFormModel, productConfComponents,
                productConfSoftwares);

        // Close connections
        this.closeConnections();

        return _productConf;
    }

    /*
     * ProductFamily
     */
    // 15-12-11 : RMO : Creation de la méthode
    // TODO Javadoc
    public ProductFamily addProductFamily(String name, int state,
            ProductType productType) throws SQLException,
            ProductFamilyDaoException {
        ProductFamilyModule _productFamilyModule = new ProductFamilyModule(
                this.cnxProduct);
        ProductFamily _productFamily = _productFamilyModule.addProductFamily(
                name, state, productType);

        // Close connections
        this.closeConnections();

        return _productFamily;
    }

    /**
     * Recherche une famille de produit de la base de donn&eacute;es.
     *
     * @param idProductFamily Identifiant de la famille de produit.
     *
     * @return Famille de produit.
     *
     * @throws SQLException
     */
    public ProductFamily getProductFamily(int idProductFamily)
            throws SQLException {
        ProductFamilyModule _productFamilyModule = new ProductFamilyModule(
                this.cnxProduct);
        ProductFamily _productFamily = _productFamilyModule
                .getProductFamily(idProductFamily);

        // Close connections
        this.closeConnections();

        return _productFamily;
    }

    /**
     * Recherche les familles de produit de la base de donn&eacute;es.
     *
     * @return Liste des familles de produit.
     *
     * @throws SQLException
     */
    public List<ProductFamily> getProductFamilies() throws SQLException {
        ProductFamilyModule _productFamilyModule = new ProductFamilyModule(
                this.cnxProduct);
        List<ProductFamily> _productFamilies = _productFamilyModule
                .getProductFamilies();

        // Close connections
        this.closeConnections();

        return _productFamilies;
    }

    /**
     * Recherche les familles de produit de la base de donn&eacute;es pour un
     * type de produits donn&eacute;.
     *
     * @param type Type de produits (Produits, Cartes...)
     *
     * @return Liste des familles de produit.
     *
     * @throws SQLException
     */
    public List<ProductFamily> getProductFamilies(int type) throws SQLException {
        ProductFamilyModule _productFamilyModule = new ProductFamilyModule(
                this.cnxProduct);
        List<ProductFamily> _productFamilies = _productFamilyModule
                .getProductFamilies(type);

        // Close connections
        this.closeConnections();

        return _productFamilies;
    }

    /**
     * Recherche les familles de produit actives de la base de donn&eacute;es.
     *
     * @return Liste des familles de produit.
     *
     * @throws SQLException
     */
    public List<ProductFamily> getActiveProductFamilies() throws SQLException {
        ProductFamilyModule _productFamilyModule = new ProductFamilyModule(
                this.cnxProduct);
        List<ProductFamily> _productFamilies = _productFamilyModule
                .getActiveProductFamilies();

        // Close connections
        this.closeConnections();

        return _productFamilies;
    }

    /**
     * Mise à jour d'une famille de produits déjà enregistrée en base
     *
     * @param productFamily la famille de produits à mettre à jour
     * @throws NamingException
     */
    public void updateProductFamily(ProductFamily productFamily)
            throws SQLException, NamingException {
        ProductFamilyModule _productFamilyModule = new ProductFamilyModule(
                this.cnxProduct);
        _productFamilyModule.updateProductFamily(productFamily);

        // Close connections
        this.closeConnections();
    }

    /*
     * ProductSupply
     */
    // 15-12-11 : RMO : Creation de la méthode
    /**
     * Ajout d'une alimentation dans la base de données
     *
     * @param name Nom de l'alimentation
     *
     * @param state Etat de l'alimenation //0 : Inactif, 1 : Actif
     *
     * @throws SQLException
     * @throws ProductDaoException
     * @throws NamingException
     */
    public ProductSupply addProductSupply(String name, int state)
            throws SQLException, ProductDaoException, NamingException {
        ProductSupplyModule _productSupplyModule = new ProductSupplyModule(
                this.cnxProduct);
        ProductSupply _productSupply = _productSupplyModule.addProductSupply(
                name, state);

        // Close connections
        this.closeConnections();

        return _productSupply;
    }

    /**
     * Recherche une alimentation de produit de la base de donn&eacute;es.
     *
     * @return Alimentation de produit.
     *
     * @throws SQLException
     */
    public ProductSupply getProductSupply(int idProductSupply)
            throws SQLException {
        ProductSupplyModule _productSupplyModule = new ProductSupplyModule(
                this.cnxProduct);
        ProductSupply _productSupply = _productSupplyModule
                .getProductSupply(idProductSupply);

        // Close connections
        this.closeConnections();

        return _productSupply;
    }

    /**
     * Recherche les alimentations de produit de la base de donn&eacute;es.
     *
     * @return Liste des alimentations de produit.
     *
     * @throws SQLException
     */
    public List<ProductSupply> getProductSupplies() throws SQLException {
        ProductSupplyModule _productSupplyModule = new ProductSupplyModule(
                this.cnxProduct);
        List<ProductSupply> _productSupplies = _productSupplyModule
                .getProductSupplies();

        // Close connections
        this.closeConnections();

        return _productSupplies;
    }

    /**
     * Recherche les alimentations de produit actives de la base de
     * donn&eacute;es.
     *
     * @return Liste des alimentations de produit.
     *
     * @throws SQLException
     */
    public List<ProductSupply> getActiveProductSupplies() throws SQLException {
        ProductSupplyModule _productSupplyModule = new ProductSupplyModule(
                this.cnxProduct);
        List<ProductSupply> _productSupplies = _productSupplyModule
                .getActiveProductSupplies();

        // Close connections
        this.closeConnections();

        return _productSupplies;
    }

    // 15-12-11 : RMO : Creation de la méthode
    /**
     * Mise à jour d'une alimentation déjà enregistrée en base
     *
     * @param productSupply Alimentation produit.
     *
     * @throws SQLException
     * @throws NamingException
     */
    public void updateProductSupply(ProductSupply productSupply)
            throws SQLException, NamingException {
        ProductSupplyModule _productSupplyModule = new ProductSupplyModule(
                this.cnxProduct);
        _productSupplyModule.updateProductSupply(productSupply);

        // Close connections
        this.closeConnections();
    }

    /*
     * ProductType
     */
    /**
     * Ajout d'un type de produit dans la base de données
     *
     * @param name Nom du type de produit
     *
     * @param state Etat du type de produit //0 : Inactif, 1 : Actif
     *
     * @throws SQLException
     * @throws ProductDaoException
     * @throws NamingException
     */
    public ProductType addProductType(String name, int state)
            throws SQLException, ProductDaoException, NamingException {
        ProductTypeModule _productTypeModule = new ProductTypeModule(
                this.cnxProduct);
        ProductType _productType = _productTypeModule.addProductType(name,
                state);

        // Close connections
        this.closeConnections();

        return _productType;
    }

    /**
     * Recherche les types de produit actifs de la base de donn&eacute;es.
     *
     * @return Liste des types de produit.
     *
     * @throws SQLException
     */
    public List<ProductType> getActiveProductTypes() throws SQLException {
        ProductTypeModule _productTypeModule = new ProductTypeModule(
                this.cnxProduct);
        List<ProductType> _productTypes = _productTypeModule
                .getActiveProductTypes();

        // Close connections
        this.closeConnections();

        return _productTypes;
    }

    /**
     * Recherche un type de produit de la base de donn&eacute;es.
     *
     * @param idProductTypeName Identifiant du type de produit.
     *
     * @return Type de produit.
     *
     * @throws SQLException
     */
    public ProductType getProductType(int idProductTypeName)
            throws SQLException {
        ProductTypeModule _productTypeModule = new ProductTypeModule(
                this.cnxProduct);
        ProductType _productType = _productTypeModule
                .getProductType(idProductTypeName);

        // Close connections
        this.closeConnections();

        return _productType;
    }

    /**
     * Recherche un type de produit de la base de donn&eacute;es.
     *
     * @param productTypeName Nom du type de produit.
     *
     * @return Type de produit.
     *
     * @throws SQLException
     * @throws IOException
     * @throws ConfigFileReaderException
     */
    public ProductType getProductType(String productTypeName)
            throws SQLException, ConfigFileReaderException, IOException {
        ProductTypeModule _productTypeModule = new ProductTypeModule(
                this.cnxProduct);
        ProductType _productType = _productTypeModule
                .getProductType(productTypeName);

        // Close connections
        this.closeConnections();

        return _productType;
    }

    /**
     * Recherche les types de produit de la base de donn&eacute;es.
     *
     * @return Liste des types de produit.
     *
     * @throws SQLException
     */
    public List<ProductType> getProductTypes() throws SQLException {
        ProductTypeModule _productTypeModule = new ProductTypeModule(
                this.cnxProduct);
        List<ProductType> _productTypes = _productTypeModule.getProductTypes();

        // Close connections
        this.closeConnections();

        return _productTypes;
    }

    /**
     * Mise à jour d'un type de produit déjà enregistré en base
     *
     * @param productType Type de produit.
     *
     * @throws SQLException
     * @throws NamingException
     */
    public void updateProductType(ProductType productType) throws SQLException,
            NamingException {
        ProductTypeModule _productTypeModule = new ProductTypeModule(
                this.cnxProduct);
        _productTypeModule.updateProductType(productType);

        // Close connections
        this.closeConnections();
    }

    /*
     * Software
     */
    /**
     * Ajout d'un logiciel dans la base de donn&eacute;es.
     *
     * @param name Nom du logiciel.
     *
     * @param version Version du logiciel.
     *
     * @return software Logiciel.
     *
     * @throws SQLException
     * @throws SoftwareDaoException
     * @throws NamingException
     */
    public Software addSoftware(String name, String version)
            throws SQLException, SoftwareDaoException, NamingException {
        SoftwareModule _softwareModule = new SoftwareModule(this.cnxProduct);
        Software _software = _softwareModule.addSoftware(name, version);

        // Close connections
        this.closeConnections();

        return _software;
    }

    /**
     * Ajout d'un logiciel dans la base de donn&eacute;es.
     *
     * @param state Etat du Logiciel //0 : Inactif, 1 : Actif
     *
     * @param name Nom du Logiciel
     *
     * @param version Version du Logiciel
     *
     * @throws SQLException
     * @throws SoftwareDaoException
     * @throws NamingException
     */
    public Software addSoftware(int state, String name, String version)
            throws SQLException, SoftwareDaoException, NamingException {
        SoftwareModule _softwareModule = new SoftwareModule(this.cnxProduct);
        Software _software = _softwareModule.addSoftware(state, name, version);

        // Close connections
        this.closeConnections();

        return _software;
    }

    /**
     * Recherche un logiciel de la base de donn&eacute;es.
     *
     * @param name Nom du logiciel.
     *
     * @param version Version du logiciel.
     *
     * @return software Logiciel.
     *
     * @throws SQLException
     */
    public Software getSoftware(String name, String version)
            throws SQLException {
        SoftwareModule _softwareModule = new SoftwareModule(this.cnxProduct);
        Software _software = _softwareModule.getSoftware(name, version);

        // Close connections
        this.closeConnections();

        return _software;
    }

    /**
     * Recherche un logiciel de la base de donn&eacute;es.
     *
     * @param idSoftware Identifiant du logiciel.
     *
     * @return Logiciel.
     *
     * @throws SQLException
     */
    public Software getSoftware(int idSoftware) throws SQLException {
        SoftwareModule _softwareModule = new SoftwareModule(this.cnxProduct);
        Software _software = _softwareModule.getSoftware(idSoftware);

        // Close connections
        this.closeConnections();

        return _software;
    }

    /**
     * Recherche les logiciels de la base de donn&eacute;es.
     *
     * @return software Logiciel.
     *
     * @throws SQLException
     */
    public List<Software> getSoftwares() throws SQLException {
        SoftwareModule _softwareModule = new SoftwareModule(this.cnxProduct);
        List<Software> _softwares = _softwareModule.getSoftwares();

        // Close connections
        this.closeConnections();

        return _softwares;
    }

    /**
     * Recherche les logiciels actifs de la base de donn&eacute;es.
     *
     * @return _software Liste de logiciel.
     *
     * @throws SQLException
     */
    public List<Software> getActiveSoftwares() throws SQLException {
        SoftwareModule _softwareModule = new SoftwareModule(this.cnxProduct);
        List<Software> _softwares = _softwareModule.getActiveSoftwares();

        // Close connections
        this.closeConnections();

        return _softwares;
    }

    /**
     * Mise à jour d'un logiciel déjà enregistré en base
     *
     * @param software Logiciel &agrave; mettre à jour
     * @throws NamingException
     */
    public void updateSoftware(Software software) throws SQLException,
            NamingException {
        SoftwareModule _softwareModule = new SoftwareModule(this.cnxProduct);
        _softwareModule.updateSoftware(software);

        // Close connections
        this.closeConnections();
    }

    /*
     * Tester
     */
    // TODO Javadoc
    public Tester addTester(String name, int state) throws SQLException,
            TesterDaoException, NamingException {
        TesterModule _testerModule = new TesterModule(this.cnxProduct,
                this.cnxTester);
        Tester _tester = _testerModule.addTester(name, state);

        // Close connections
        this.closeConnections();

        return _tester;
    }

    // TODO Javadoc
    public Tester getTester(int idTester) throws SQLException {
        TesterModule _testerModule = new TesterModule(this.cnxProduct,
                this.cnxTester);
        Tester _tester = _testerModule.getTester(idTester);

        // Close connections
        this.closeConnections();

        return _tester;
    }

    // TODO Javadoc
    public Tester getTester(String name) throws SQLException {
        TesterModule _testerModule = new TesterModule(this.cnxProduct,
                this.cnxTester);
        Tester _tester = _testerModule.getTester(name);

        // Close connections
        this.closeConnections();

        return _tester;
    }

    // TODO Javadoc
    public List<Tester> getTesters() throws SQLException {
        TesterModule _testerModule = new TesterModule(this.cnxProduct,
                this.cnxTester);
        List<Tester> _testers = _testerModule.getTesters();

        // Close connections
        this.closeConnections();

        return _testers;
    }

    // TODO Javadoc
    public List<Tester> getActiveTesters() throws SQLException {
        TesterModule _testerModule = new TesterModule(this.cnxProduct,
                this.cnxTester);
        List<Tester> _testers = _testerModule.getActiveTesters();

        // Close connections
        this.closeConnections();

        return _testers;
    }

    // TODO Javadoc
    public void updateTester(Tester tester) throws SQLException,
            TesterDaoException, NamingException {
        TesterModule _testerModule = new TesterModule(this.cnxProduct,
                this.cnxTester);
        _testerModule.updateTester(tester);

        // Close connections
        this.closeConnections();
    }

    /*
     * TesterReport
     */
    /**
     * Recherche un rapport de testeur dans la base de donn&eacute;s.
     *
     * @param testerReportDate Date du rapport.
     * @param productConfReference R&eacute;f&eacute;rence de la configuration
     * produit.
     * @param productConfMajorIndex Indice majeur de la configuration produit.
     * @param productConfMinorIndex Indice mineur de la configuration produit.
     * @param serialNumber Num&eacute;ro de s&eacute;rie du produit.
     * @param datecode Datecode du produit.
     * @param testTypeName Type de test.
     *
     * @throws SQLException
     * @throws TesterModuleException
     */
    public TesterReport getTesterReport(Date testerReportDate,
            String productConfReference, String productConfMajorIndex,
            String productConfMinorIndex, String serialNumber, String datecode,
            String testTypeName) throws SQLException, TesterModuleException {
        TesterReportModule _testerReportModule = new TesterReportModule(
                this.cnxOperator, this.cnxProduct, this.cnxTester);
        TesterReport _testerReport = _testerReportModule.getTesterReport(
                testerReportDate, productConfReference, productConfMajorIndex,
                productConfMinorIndex, serialNumber, datecode, testTypeName);

        // Close connections
        this.closeConnections();

        return _testerReport;
    }

    /**
     * Enregistre un rapport de testeur dans la base de donn&eacute;s.
     *
     * @param testerReportDate Date du rapport.
     * @param productConfReference R&eacute;f&eacute;rence de la configuration
     * produit.
     * @param productConfMajorIndex Indice majeur de la configuration produit.
     * @param productConfMinorIndex Indice mineur de la configuration produit.
     * @param serialNumber Num&eacute;ro de s&eacute;rie du produit.
     * @param datecode Datecode du produit.
     * @param testTypeName Type de test.
     * @param reportOperatorCode Code de l'op&eacute;rateur.
     * @param testResult R&eacute;sultat du test.
     * @param customerComment Description des d&eacute;fauts.
     *
     * @throws SQLException
     * @throws TesterModuleException
     * @throws NamingException
     */
    public TesterReport setTesterReport(Date testerReportDate,
            String productConfReference, String productConfMajorIndex,
            String productConfMinorIndex, String serialNumber, String datecode,
            String testTypeName, String reportOperatorCode, String testResult,
            String customerComment) throws SQLException, TesterModuleException,
            NamingException {
        TesterReportModule _testerReportModule = new TesterReportModule(
                this.cnxOperator, this.cnxProduct, this.cnxTester);
        TesterReport _testerReport = _testerReportModule.setTesterReport(
                testerReportDate, productConfReference, productConfMajorIndex,
                productConfMinorIndex, serialNumber, datecode, testTypeName,
                reportOperatorCode, testResult, customerComment);

        // Close connections
        this.closeConnections();

        return _testerReport;
    }

    // TODO Javadoc
    public TesterReport addTesterReport(TestType testType, Tester tester,
            String operatorCode, Product product) throws SQLException,
            TesterReportDaoException, NamingException {
        TesterReportModule _testerReportModule = new TesterReportModule(
                this.cnxOperator, this.cnxProduct, this.cnxTester);
        TesterReport _testerReport = _testerReportModule.addTesterReport(
                testType, tester, operatorCode, product);

        // Close connections
        this.closeConnections();

        return _testerReport;
    }

    /**
     * V&eacute;rifie que le produit respecte le process.
     *
     * @param product Produit.
     * @param testType Type de test.
     *
     * @return Respect du process.
     *
     * @throws SQLException
     * @throws IOException
     * @throws ConfigFileReaderException
     */
    public boolean inFlowProcess(Product product, TestType testType)
            throws SQLException {
        TesterReportModule _testerReportModule = new TesterReportModule(
                this.cnxOperator, this.cnxProduct, this.cnxTester);
        boolean _inFlowProcess = _testerReportModule.inFlowProcess(product,
                testType);

        // Close connections
        this.closeConnections();

        return _inFlowProcess;
    }

    /**
     * Enregistre un rapport de testeur dans la base de donn&eacute;s.
     *
     * @param preTesterReport Pr&eacute;-rapport.
     * @param labviewTesterReport Rapport testeur Labview.
     *
     * @return Rapport testeur.
     *
     * @throws ParseException
     * @throws TesterReportException
     * @throws TesterModuleException
     * @throws IOException
     * @throws ConfigFileReaderException
     * @throws FailureModuleException
     * @throws TesterReportDaoException
     * @throws SQLException
     * @throws JProductBaseException
     * @throws NamingException
     */
    public TesterReport saveTesterReport(PreTesterReport preTesterReport,
            LabviewTesterReport labviewTesterReport) throws ParseException,
            SQLException, TesterReportDaoException, FailureModuleException,
            ConfigFileReaderException, IOException, TesterModuleException,
            TesterReportException, JProductBaseException, NamingException {
        TesterReportModule _testerReportModule = new TesterReportModule(
                this.cnxOperator, this.cnxProduct, this.cnxTester);
        TesterReport _testerReport = _testerReportModule.saveTesterReport(
                preTesterReport, labviewTesterReport);

        // Close connections
        this.closeConnections();

        return _testerReport;
    }

    /*
     * TestType
     */
    // TODO Javadoc
    public TestType addTestType(String name, int state, boolean needTester)
            throws SQLException, TestTypeDaoException, NamingException {
        TestTypeModule _testTypeModule = new TestTypeModule(this.cnxTester);
        TestType _testType = _testTypeModule.addTestType(name, state,
                needTester);

        // Close connections
        this.closeConnections();

        return _testType;
    }

    // TODO Javadoc
    public TestType getTestType(int idTestType) throws SQLException {
        TestTypeModule _testTypeModule = new TestTypeModule(this.cnxTester);
        TestType _testType = _testTypeModule.getTestType(idTestType);

        // Close connections
        this.closeConnections();

        return _testType;
    }

    // TODO Javadoc
    public TestType getTestType(String name) throws SQLException {
        TestTypeModule _testTypeModule = new TestTypeModule(this.cnxTester);
        TestType _testType = _testTypeModule.getTestType(name);

        // Close connections
        this.closeConnections();

        return _testType;
    }

    // TODO Javadoc
    public List<TestType> getTestTypes() throws SQLException {
        TestTypeModule _testTypeModule = new TestTypeModule(this.cnxTester);
        List<TestType> _testTypes = _testTypeModule.getTestTypes();

        // Close connections
        this.closeConnections();

        return _testTypes;
    }

    /**
     * Recherche une type de test dans la la base de donn&eacute;es.
     *
     * @param labviewTestType Type de test labview.
     *
     * @return Type de test.
     */
    public TestType retreiveTestType(LabviewTestType labviewTestType)
            throws SQLException {
        TestTypeModule _testTypeModule = new TestTypeModule(this.cnxTester);
        TestType _testType = _testTypeModule.retreiveTestType(labviewTestType);

        // Close connections
        this.closeConnections();

        return _testType;
    }

    // TODO Javadoc
    public void updateTestType(TestType testType) throws SQLException,
            TestTypeDaoException, NamingException {
        TestTypeModule _testTypeModule = new TestTypeModule(this.cnxTester);
        _testTypeModule.updateTestType(testType);

        // Close connections
        this.closeConnections();
    }

    /*
     * Various
     */
    /**
     * Intégration des fichiers de données en attente.
     *
     * @throws JProductBaseException
     * @throws ConfigFileReaderException
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     * @throws JDOMException
     *
     */
    public void insertClientFiles() throws JDOMException, IOException,
            SAXException, ParserConfigurationException,
            ConfigFileReaderException, JProductBaseException {
        XMLProductModule _xmlProductModule = new XMLProductModule();
        _xmlProductModule.insertClientFiles();

        // Close connections
        this.closeConnections();
    }

    /**
     * Enregistre un produit de la base FEDD vers la base LAI.
     *
     * @param product Produit &grave; enregistr&eacute;.
     *
     * @return Produit sauvegard&eacute;.
     * @throws NamingException
     *
     * @throws Exception
     */
    public Product setProductFEDDtoLAI(int idProductFEDD, ProductConf config,
            String serialNumber, String datecode) throws SQLException,
            ProductDaoException, ConfigFileReaderException, IOException,
            SoftwareDaoException, JProductBaseException, NamingException {
        ProductModule _productModule = new ProductModule(this.cnxProduct);
        Product _product = _productModule.setProductFEDDtoLAI(idProductFEDD,
                config, serialNumber, datecode);

        // Close connections
        this.closeConnections();

        return _product;
    }

    // TODO javadoc
    public void addFailures(AfterSaleReport afterSaleReport)
            throws SQLException, FailureDaoException,
            ElementChangedDaoException, ConfigFileReaderException, IOException {
        FailureModule _failureModule = new FailureModule(this.cnxProduct,
                this.cnxOperator, this.cnxTester);

        _failureModule.addFailures(afterSaleReport);

        // Close connections
        this.closeConnections();
    }

    // TODO javadoc
    public Failure addFailure(Failure failure, AfterSaleReport afterSaleReport)
            throws SQLException, FailureDaoException,
            ElementChangedDaoException {
        FailureModule _failureModule = new FailureModule(this.cnxProduct,
                this.cnxOperator, this.cnxTester);
        Failure _failure;
        _failure = _failureModule.addFailure(failure, afterSaleReport);

        // Close connections
        this.closeConnections();

        return _failure;
    }

    // TODO javadoc
	/*
     * 14-05-12 : RMO : La méthode existante sous failureModule en private a été
     * passée en protected de manière à pouvoir y accéder depuis le module
     * afterSaleReportModule où on a eu besoin de supprimer un failure de la
     * base.
     */
    public void removeFailure(Failure failure) throws SQLException,
            FailureDaoException, ElementChangedDaoException {
        FailureModule _failureModule = new FailureModule(this.cnxProduct,
                this.cnxOperator, this.cnxTester);

        _failureModule.removeFailure(failure);

        // Close connections
        this.closeConnections();

    }

    // TODO javadoc
	/*
     * 05-06-12 : RMO : La méthode existante sous failureModule en private a été
     * passée en protected de manière à pouvoir y accéder depuis le module
     * afterSaleReportModule où on a eu besoin de supprimer un failure de la
     * base.
     */
    public void updateFailure(Failure failure) throws SQLException,
            FailureDaoException, ElementChangedDaoException {
        FailureModule _failureModule = new FailureModule(this.cnxProduct,
                this.cnxOperator, this.cnxTester);

        _failureModule.updateFailure(failure);

        // Close connections
        this.closeConnections();

    }

    /*
     * Méthode permettant de mettre a jour des failures
     */
    public List<Failure> updateFailure(Product product, List<Failure> failures)
            throws ConfigFileReaderException, IOException, SQLException,
            ParseException, JProductBaseException, ProductDaoException,
            NamingException {
        FailureModule _failureModule = new FailureModule(this.cnxProduct,
                this.cnxOperator, this.cnxTester);

        List<Failure> _listFailure = _failureModule.updateFailures(product, failures);

        // Close connections
        this.closeConnections();

        return _listFailure;
    }

    public List<AfterSaleReport> getAfterSaleReports() throws SQLException,
            ConfigFileReaderException, IOException {
        AfterSaleModule _afterSaleReportModule = new AfterSaleModule(
                this.cnxProduct, this.cnxOperator);

        List<AfterSaleReport> _afterSaleReports = _afterSaleReportModule
                .getAfterSaleReports();

        // Close connections
        this.closeConnections();

        return _afterSaleReports;
    }

    public List<AfterSaleReport> getAfterSaleReports(Product product)
            throws SQLException, ConfigFileReaderException, IOException {
        AfterSaleModule _afterSaleReportModule = new AfterSaleModule(
                this.cnxProduct, this.cnxOperator);

        List<AfterSaleReport> _afterSaleReports = _afterSaleReportModule
                .getAfterSaleReports(product);

        // Close connections
        this.closeConnections();

        return _afterSaleReports;
    }

    public List<AfterSaleReport> getAfterSaleReports(int idproduct)
            throws SQLException, ConfigFileReaderException, IOException {
        AfterSaleModule _afterSaleReportModule = new AfterSaleModule(
                this.cnxProduct, this.cnxOperator);

        List<AfterSaleReport> _afterSaleReports = _afterSaleReportModule
                .getAfterSaleReports(idproduct);

        // Close connections
        this.closeConnections();

        return _afterSaleReports;
    }

    public List<AfterSaleReport> getAfterSaleReports(Date fromDate, Date toDate)
            throws SQLException, ConfigFileReaderException, IOException {
        AfterSaleModule _afterSaleReportModule = new AfterSaleModule(
                this.cnxProduct, this.cnxOperator);

        java.sql.Date _fromDate = new java.sql.Date(fromDate.getTime());
        java.sql.Date _toDate = new java.sql.Date(toDate.getTime());

        List<AfterSaleReport> _afterSaleReports = _afterSaleReportModule
                .getAfterSaleReports(_fromDate, _toDate);

        // Close connections
        this.closeConnections();

        return _afterSaleReports;
    }

    public AfterSaleReport getAfterSaleReport(int idAfterSaleReport)
            throws SQLException, ConfigFileReaderException, IOException {
        AfterSaleModule _afterSaleReportModule = new AfterSaleModule(
                this.cnxProduct, this.cnxOperator);

        AfterSaleReport _afterSaleReport = _afterSaleReportModule
                .getAfterSaleReport(idAfterSaleReport);

        // Close connections
        this.closeConnections();

        return _afterSaleReport;
    }

    public AfterSaleReport setAfterSaleReport(AfterSaleReport afterSaleReport,
            Date arrivalDate, String ecsNumber, String ncNature,
            Date firstAnalyseDate, Date materialInfoDate, Date reparationDate,
            Date qualityControlDate, Date expeditionDate, int functionnalTest,
            int visualControl, String asker, String intervenant,
            String interventionSheetLink, String comment,
            List<Failure> failures, ApparentCause apparentCause,
            String majorIndexIn, String majorIndexOut, Product product)
            throws SQLException, FailureModuleException,
            AfterSaleReportDaoException, FailureDaoException,
            ElementChangedDaoException, FailureReportCommentDaoException,
            CustomerCommentDaoException, ConfigFileReaderException,
            IOException, ParseException, JProductBaseException,
            ProductDaoException, NamingException {

        AfterSaleModule _afterSaleReportModule = new AfterSaleModule(
                this.cnxProduct, this.cnxOperator);

        AfterSaleReport _afterSaleReport = _afterSaleReportModule
                .setAfterSaleReport(afterSaleReport, arrivalDate, ecsNumber,
                ncNature, firstAnalyseDate, materialInfoDate,
                reparationDate, qualityControlDate, expeditionDate,
                functionnalTest, visualControl, asker, intervenant,
                interventionSheetLink, comment, failures,
                apparentCause, majorIndexIn, majorIndexOut, product);


        // Close connections
        this.closeConnections();

        return _afterSaleReport;
    }

    public AfterSaleReport setAfterSaleReport(AfterSaleReport afterSaleReport)
            throws SQLException, FailureModuleException,
            AfterSaleReportDaoException, FailureDaoException,
            ElementChangedDaoException, FailureReportCommentDaoException,
            CustomerCommentDaoException, ConfigFileReaderException,
            IOException, ParseException, JProductBaseException,
            ProductDaoException, NamingException {

        AfterSaleModule _afterSaleReportModule = new AfterSaleModule(
                this.cnxProduct, this.cnxOperator);
        AfterSaleReport _afterSaleReport = afterSaleReport;
        if (null != afterSaleReport) {

            /*
             * Si l'id de l'intervention est à 0, c'est qu'il s'agit d'une
             * nouvelle intervention à rentrer dans la abse, donc la déclare à
             * null pour que ce soit pris en compte
             */
            if (afterSaleReport.getIdAfterSaleReport() == 0) {
                _afterSaleReport = null;
            }

            System.out.println("dans Service Interface setAfterSaleReport"
                    + _afterSaleReport);

            _afterSaleReport = _afterSaleReportModule.setAfterSaleReport(
                    _afterSaleReport, afterSaleReport.getArrivalDate(),
                    afterSaleReport.getEcsNumber(),
                    afterSaleReport.getNcNature(),
                    afterSaleReport.getFirstAnalyseDate(),
                    afterSaleReport.getMaterialInfoDate(),
                    afterSaleReport.getReparationDate(),
                    afterSaleReport.getQualityControlDate(),
                    afterSaleReport.getExpeditionDate(),
                    afterSaleReport.getFunctionnalTest(),
                    afterSaleReport.getVisualControl(),
                    afterSaleReport.getAsker(),
                    afterSaleReport.getIntervenant(),
                    afterSaleReport.getInterventionSheetLink(),
                    afterSaleReport.getComment(),
                    afterSaleReport.getFailures(),
                    afterSaleReport.getApparentCause(),
                    afterSaleReport.getMajorIndexIn(),
                    afterSaleReport.getMajorIndexOut(),
                    afterSaleReport.getProduct());
        }

        // Close connections
        this.closeConnections();

        return _afterSaleReport;
    }

    /*
     * Mise à jour de la date qualitycontroldate en fonction de l'id
     * afterSaleReport
     */
    public void updateAfterSaleReportQualityControl(
            AfterSaleReport AfterSaleReport) throws SQLException,
            AfterSaleReportDaoException, FailureDaoException,
            ElementChangedDaoException, NamingException {

        // Update failureReport
        AfterSaleModule _afterSaleReportModule = new AfterSaleModule(
                this.cnxProduct, this.cnxOperator);

        _afterSaleReportModule
                .updateAfterSaleReportQualityControl(AfterSaleReport);
        // Close connections
        this.closeConnections();
    }

    /*
     * Renvoi la List de toutes les AfterSaleReport possédant une reparationDate
     * et n'ayant pas de qualityControlDate et expeditionDate
     */
    public List<AfterSaleReport> getAfterSaleReportQualityControl()
            throws SQLException, ConfigFileReaderException, IOException,
            AfterSaleReportDaoException {

        AfterSaleModule _afterSaleReportModule = new AfterSaleModule(
                this.cnxProduct, this.cnxOperator);

        List<AfterSaleReport> _afterSaleReports = _afterSaleReportModule
                .getAfterSaleReportsValidControl();

        // Close connections
        this.closeConnections();

        return _afterSaleReports;
    }

    /*
     * Mise à jour de la date qualitycontroldate en fonction de l'id
     * afterSaleReport
     */
    public void updateAfterSaleReportExpedSAV(AfterSaleReport AfterSaleReport)
            throws SQLException, AfterSaleReportDaoException,
            FailureDaoException, ElementChangedDaoException, NamingException {

        // Update failureReport
        AfterSaleModule _afterSaleReportModule = new AfterSaleModule(
                this.cnxProduct, this.cnxOperator);

        _afterSaleReportModule.updateAfterSaleReportExpedSAV(AfterSaleReport);

        // Close connections
        this.closeConnections();
    }

    /*
     * Renvoi la List de toutes les AfterSaleReport possédant une reparationDate
     * et n'ayant pas de qualityControlDate et expeditionDate
     */
    public List<AfterSaleReport> getAfterSaleReportExpedSAV()
            throws SQLException, ConfigFileReaderException, IOException,
            AfterSaleReportDaoException {

        AfterSaleModule _afterSaleReportModule = new AfterSaleModule(
                this.cnxProduct, this.cnxOperator);

        List<AfterSaleReport> _afterSaleReports = _afterSaleReportModule
                .getAfterSaleReportsExpedSAV();

        // Close connections
        this.closeConnections();

        return _afterSaleReports;
    }

    /*
     * Renvoi toutes les données de la table AfterSaleCom
     */
    public List<AfterSaleCom> getAfterSaleCom() throws SQLException,
            ConfigFileReaderException, IOException, AfterSaleReportDaoException {

        AfterSaleModule _afterSaleReportModule = new AfterSaleModule(
                this.cnxProduct, this.cnxOperator);

        List<AfterSaleCom> _afterSaleCom = _afterSaleReportModule
                .getAfterSaleCom();

        // Close connections
        this.closeConnections();

        return _afterSaleCom;
    }

    /*
     * Renvoi une aftersalecom en fonction de l'id
     */
    public AfterSaleCom getAfterSaleCom(String idaftersalecom)
            throws SQLException, ConfigFileReaderException, IOException,
            AfterSaleReportDaoException {

        AfterSaleModule _afterSaleReportModule = new AfterSaleModule(
                this.cnxProduct, this.cnxOperator);

        AfterSaleCom _afterSaleCom = _afterSaleReportModule
                .getAfterSaleCom(idaftersalecom);

        // Close connections
        this.closeConnections();

        return _afterSaleCom;
    }

    /*
     * Ajout le Numero de Devis, le Montant, la date du devis et le commentaire
     * a un AfterSaleCom
     */
    public void updateDevisPrea(AfterSaleCom AfterSaleCom) throws Exception {

        AfterSaleModule _afterSaleReportModule = new AfterSaleModule(
                this.cnxProduct, this.cnxOperator);

        _afterSaleReportModule.UpdateDevisPrealable(AfterSaleCom);

        // Close connections
        this.closeConnections();

    }

    /*
     * Renvoi les donn�es qui servent a remplir le formulaire Devis Prealable
     */
    public List<AfterSaleCom> getDevisPrea() throws SQLException,
            ConfigFileReaderException, IOException, AfterSaleReportDaoException {

        AfterSaleModule _afterSaleReportModule = new AfterSaleModule(
                this.cnxProduct, this.cnxOperator);

        List<AfterSaleCom> _aftersalecom = _afterSaleReportModule
                .getAfterSaleDevisPrea();

        // Close connections
        this.closeConnections();

        return _aftersalecom;
    }

    /*
     * Renvoi les donn�es qui servent a remplir le formulaire Devis Repa
     */
    public List<AfterSaleReport> getDevisRepa() throws SQLException,
            ConfigFileReaderException, IOException, AfterSaleReportDaoException {

        AfterSaleModule _afterSaleReportModule = new AfterSaleModule(
                this.cnxProduct, this.cnxOperator);

        List<AfterSaleReport> _aftersalereport = _afterSaleReportModule
                .getAfterSaleDevisRepa();

        // Close connections
        this.closeConnections();

        return _aftersalereport;
    } /*
     * Renvoi le resultat de la recherche pour le formulaire numCommande.
     */


    public List<AfterSaleCom> RechercheNumCmd(String recherche)
            throws SQLException, ConfigFileReaderException, IOException {

        AfterSaleModule _afterSaleReportModule = new AfterSaleModule(
                this.cnxProduct, this.cnxOperator);

        List<AfterSaleCom> _afterSaleCom = _afterSaleReportModule
                .rechercheNumCmd(recherche);

        // Close connections
        this.closeConnections();

        return _afterSaleCom;
    }

    /*
     * Enregistre le Numero et la Date de Commande pour un aftersalecom
     */
    public void addCmd(AfterSaleCom aftersalecom) throws SQLException,
            ConfigFileReaderException, IOException, NamingException {

        AfterSaleModule _afterSaleReportModule = new AfterSaleModule(
                this.cnxProduct, this.cnxOperator);

        _afterSaleReportModule.addCmd(aftersalecom);

        // Close connections
        this.closeConnections();

    }

    /*
     * Update un AfterSaleCom
     */
    public void updateAfterSaleCom(AfterSaleCom _aftersalecom) throws Exception {

        AfterSaleModule _afterSaleReportModule = new AfterSaleModule(
                this.cnxProduct, this.cnxOperator);

        _afterSaleReportModule.updateAfterSaleCom(_aftersalecom);

        // Close connections
        this.closeConnections();

    }

    /*
     * Retourne l'ensemble des AfterSaleComs pour les ExpedSAV
     */
    public List<AfterSaleCom> getAfterSaleComExped() throws SQLException,
            ConfigFileReaderException, IOException {

        AfterSaleModule _afterSaleReportModule = new AfterSaleModule(
                this.cnxProduct, this.cnxOperator);

        List<AfterSaleCom> _aftersalecom = _afterSaleReportModule
                .getAfterSaleComExpedSAV();

        // Close connections
        this.closeConnections();

        return _aftersalecom;

    }

    // TODO Javadoc
	/*
     * 21-06-12 : RMO : Méthodes sur les productDocuments et
     * ProductDocumentTypes
     */
    public ProductDocument addProductDocument(int state, String title,
            String link, ProductDocumentType productDocumentType,
            Product product) throws SQLException, ProductDocumentDaoException {

        ProductDocumentModule _productDocumentModule = new ProductDocumentModule(
                this.cnxProduct);

        ProductDocument _productDocument = _productDocumentModule
                .addProductDocument(state, title, link, productDocumentType,
                product);

        return _productDocument;

    }

    public ProductDocument updateProductDocument(ProductDocument productdocument)
            throws SQLException, ProductDocumentDaoException {

        ProductDocumentModule _productDocumentModule = new ProductDocumentModule(
                this.cnxProduct);

        ProductDocument _productDocument = _productDocumentModule
                .updateProductDocument(productdocument);

        // Close connections
        this.closeConnections();

        return _productDocument;

    }

    public ProductDocument getProductDocument(String title, String link)
            throws SQLException {

        ProductDocumentModule _productDocumentModule = new ProductDocumentModule(
                this.cnxProduct);

        ProductDocument _productDocument = _productDocumentModule
                .getProductDocument(title, link);

        // Close connections
        this.closeConnections();

        return _productDocument;

    }

    public ProductDocument getProductDocument(int idProductDocument)
            throws SQLException {

        ProductDocumentModule _productDocumentModule = new ProductDocumentModule(
                this.cnxProduct);

        ProductDocument _productDocument = _productDocumentModule
                .getProductDocument(idProductDocument);

        // Close connections
        this.closeConnections();

        return _productDocument;

    }

    public List<ProductDocument> getProductDocuments() throws SQLException {

        ProductDocumentModule _productDocumentModule = new ProductDocumentModule(
                this.cnxProduct);

        List<ProductDocument> _productDocuments = _productDocumentModule
                .getProductDocuments();

        // Close connections
        this.closeConnections();

        return _productDocuments;

    }

    public List<ProductDocument> getProductDocuments(Product product)
            throws SQLException {

        ProductDocumentModule _productDocumentModule = new ProductDocumentModule(
                this.cnxProduct);

        List<ProductDocument> _productDocuments = _productDocumentModule
                .getProductDocuments(product);

        // Close connections
        this.closeConnections();

        return _productDocuments;

    }

    public List<ProductDocument> getProductDocuments(Product product,
            ProductDocumentType productDocumentType) throws SQLException {

        ProductDocumentModule _productDocumentModule = new ProductDocumentModule(
                this.cnxProduct);

        List<ProductDocument> _productDocuments = _productDocumentModule
                .getProductDocuments(product, productDocumentType);

        // Close connections
        this.closeConnections();

        return _productDocuments;

    }

    public ProductDocumentType addProductDocumentType(int state, String name)
            throws SQLException, ProductDocumentTypeDaoException {

        ProductDocumentModule _productDocumentModule = new ProductDocumentModule(
                this.cnxProduct);

        ProductDocumentType _productDocumentType = _productDocumentModule
                .addProductDocumentType(state, name);

        // Close connections
        this.closeConnections();

        return _productDocumentType;

    }

    public ProductDocumentType updateProductDocumentType(
            ProductDocumentType productDocumentType) throws SQLException,
            ProductDocumentTypeDaoException {

        ProductDocumentModule _productDocumentModule = new ProductDocumentModule(
                this.cnxProduct);

        ProductDocumentType _productDocumentType = _productDocumentModule
                .updateProductDocumentType(productDocumentType);

        // Close connections
        this.closeConnections();

        return _productDocumentType;

    }

    public ProductDocumentType getProductDocumentType(String name)
            throws SQLException {

        ProductDocumentModule _productDocumentModule = new ProductDocumentModule(
                this.cnxProduct);

        ProductDocumentType _productDocumentType = _productDocumentModule
                .getProductDocumentType(name);

        // Close connections
        this.closeConnections();

        return _productDocumentType;

    }

    public ProductDocumentType getProductDocumentType(int idProductDocumentType)
            throws SQLException {

        ProductDocumentModule _productDocumentModule = new ProductDocumentModule(
                this.cnxProduct);

        ProductDocumentType _productDocumentType = _productDocumentModule
                .getProductDocumentType(idProductDocumentType);

        // Close connections
        this.closeConnections();

        return _productDocumentType;

    }

    public List<ProductDocumentType> getProductDocumentTypes()
            throws SQLException {

        ProductDocumentModule _productDocumentModule = new ProductDocumentModule(
                this.cnxProduct);

        List<ProductDocumentType> _productDocumentTypes = _productDocumentModule
                .getProductDocumentTypes();

        // Close connections
        this.closeConnections();

        return _productDocumentTypes;

    }

    public List<ProductDocumentType> getActiveProductDocumentTypes()
            throws SQLException {

        ProductDocumentModule _productDocumentModule = new ProductDocumentModule(
                this.cnxProduct);

        List<ProductDocumentType> _productDocumentTypes = _productDocumentModule
                .getActiveProductDocumentTypes();

        // Close connections
        this.closeConnections();

        return _productDocumentTypes;

    }

    public List<ProductDocument> getFEDDProductDocuments(Product product,
            ProductDocumentType productDocumentType) throws SQLException {

        ProductDocumentModule _productDocumentModule = new ProductDocumentModule(
                this.cnxProduct);

        List<ProductDocument> _productDocuments = _productDocumentModule
                .getFEDDProductDocuments(product, productDocumentType);

        // Close connections
        this.closeConnections();

        return _productDocuments;

    }

    public List<AfterSaleCom> getLazyRecapCom(int limit, int maxperpage)
            throws SQLException {

        AfterSaleModule _aftersalemodule = new AfterSaleModule(this.cnxProduct,
                this.cnxOperator);

        List<AfterSaleCom> _aftersalecom = _aftersalemodule.getLazyRecapCom(
                limit, maxperpage);

        // Close connections
        this.closeConnections();

        return _aftersalecom;
    }

    public int countRecapCom() throws SQLException {

        AfterSaleModule _aftersalemodule = new AfterSaleModule(this.cnxProduct,
                this.cnxOperator);

        int count = _aftersalemodule.countRecapCom();

        // Close connections
        this.closeConnections();

        return count;
    }

    public List<AfterSaleCom> getRepairDatetoDate(Date debut, Date fin)
            throws SQLException, ConfigFileReaderException, IOException {

        AfterSaleModule _aftersalemodule = new AfterSaleModule(this.cnxProduct,
                this.cnxOperator);

        List<AfterSaleCom> _aftersalecom = _aftersalemodule
                .getRepairDatetoDate(debut, fin);

        // Close connections
        this.closeConnections();

        return _aftersalecom;
    }
    /*
     * Retourne tous les FollowingFormModel
     */

    public List<FollowingFormModel> getAllFollowingFormModel() throws SQLException {
        FollowingFormModule _followingFormModule = new FollowingFormModule(
                this.cnxProduct);
        List<FollowingFormModel> _followingFormModel = _followingFormModule
                .getAllFollowingFormModel();

        // Close connections
        this.closeConnections();

        return _followingFormModel;
    }
    /*
     * Ajoute un FollowingFormModel
     */

    public void addFollowingFormModel(FollowingFormModel _followingFormModel) throws SQLException {
        FollowingFormModule _followingFormModule = new FollowingFormModule(
                this.cnxProduct);

        _followingFormModule.addFollowingFormModel(_followingFormModel);

        // Close connections
        this.closeConnections();
    }
    /*
     * Update un FollowingFormModel
     */

    public void updateFollowingFormModel(FollowingFormModel _followingFormModel) throws SQLException {
        FollowingFormModule _followingFormModule = new FollowingFormModule(
                this.cnxProduct);

        _followingFormModule.updateFollowingFormModel(_followingFormModel);

        // Close connections
        this.closeConnections();
    }
    /*
     * Supprimer un FollowingFormModel
     */

    public void deleteFollowingFormModel(int id) throws SQLException {
        FollowingFormModule _followingFormModule = new FollowingFormModule(
                this.cnxProduct);

        _followingFormModule.deleteFollowingFormModel(id);

        // Close connections
        this.closeConnections();
    }
    /*
     * Ajoute un ProductConfModels
     */

    public void addProductConfModels(ProductConfModel _productConfModel) throws SQLException {
        ProductConfModelModule _productConfModelModule = new ProductConfModelModule(
                this.cnxProduct);
        _productConfModelModule.addProductConfModels(_productConfModel);

        // Close connections
        this.closeConnections();
    }
    /*
     * Supprime un ProductConfModels
     */

    public void delProductConfModels(int id) throws SQLException {
        ProductConfModelModule _productConfModelModule = new ProductConfModelModule(
                this.cnxProduct);
        _productConfModelModule.delProductConfModels(id);

        // Close connections
        this.closeConnections();
    }

    /*
     * Retourne une liste de product avec la configuration sur laquel ils sont montes
     */
    public List<Product> getProductWithMother(int startingAt, int maxPerPage, Map<String, String> filters) throws SQLException {

        ProductModule _productModule = new ProductModule(cnxProduct);

        List<Product> _product = _productModule.getProductWithMother(startingAt, maxPerPage, filters);

        // Close connections
        this.closeConnections();

        return _product;

    }
    /*
     * Update un ProductConfModel
     */

    public void updateProductConfModel(ProductConfModel _productConfModel) throws SQLException {

        ProductConfModelModule _productConfModule = new ProductConfModelModule(cnxProduct);

        _productConfModule.updateProductConfModels(_productConfModel);

        // Close connections
        this.closeConnections();

    }

    /*
     * Permet le lazing sur les productConf
     */
    public List<ProductConf> getProductConfLazy(Map<String, String> filters, int limit, int maxperpage) throws SQLException {

        ProductConfModule _productConfModule = new ProductConfModule(cnxProduct);

        List<ProductConf> _productConf = _productConfModule.getProductConfLazy(filters, limit, maxperpage);

        // Close connections
        this.closeConnections();

        return _productConf;

    }

    /*
     * Permet le lazing sur les productConf
     */
    public int countProductConf(Map<String, String> filters) throws SQLException {

        ProductConfModule _productConfModule = new ProductConfModule(cnxProduct);

        int i = _productConfModule.countProductConf(filters);

        // Close connections
        this.closeConnections();

        return i;

    }

    /*
     * Permet le lazing sur les productConfModel
     */
    public List<ProductConfModel> getProductConfModelLazy(Map<String, String> filters, int limit, int maxperpage) throws SQLException {

        ProductConfModelModule _productConfModelModule = new ProductConfModelModule(cnxProduct);

        List<ProductConfModel> _productConf = _productConfModelModule.getProductConfModelLazy(filters, limit, maxperpage);

        // Close connections
        this.closeConnections();

        return _productConf;

    }

    /*
     * Permet le lazing sur les productConfModel
     */
    public int countProductConfModel(Map<String, String> filters) throws SQLException {

        ProductConfModelModule _productConfModelModule = new ProductConfModelModule(cnxProduct);

        int i = _productConfModelModule.countProductConfModel(filters);

        // Close connections
        this.closeConnections();

        return i;

    }

    /*
     * Close all connections
     */
    private void closeConnections() {
        this.cnxOperator.closeCnx();
        this.cnxProduct.closeCnx();
        this.cnxTester.closeCnx();
    }
}
