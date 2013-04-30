package fr.la.jproductbase.service;

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

import fr.la.configfilereader.ConfigFileReaderException;
import fr.la.jproductbase.dao.AfterSaleComDao;
import fr.la.jproductbase.dao.AfterSaleComImpl;
import fr.la.jproductbase.dao.AfterSaleReportDao;
import fr.la.jproductbase.dao.AfterSaleReportDaoImpl;
import fr.la.jproductbase.dao.ApparentCauseCustomerDao;
import fr.la.jproductbase.dao.ApparentCauseCustomerDaoImpl;
import fr.la.jproductbase.dao.ApparentCauseDao;
import fr.la.jproductbase.dao.ApparentCauseDaoImpl;
import fr.la.jproductbase.dao.ConnectionInfoSchema;
import fr.la.jproductbase.dao.ConnectionOperator;
import fr.la.jproductbase.dao.ConnectionProduct;
import fr.la.jproductbase.dao.ConnectionTester;
import fr.la.jproductbase.dao.CustomerCommentDao;
import fr.la.jproductbase.dao.CustomerCommentDaoImpl;
import fr.la.jproductbase.dao.DefectDao;
import fr.la.jproductbase.dao.DefectDaoImpl;
import fr.la.jproductbase.dao.ElementChangedDao;
import fr.la.jproductbase.dao.ElementChangedDaoImpl;
import fr.la.jproductbase.dao.FailureDao;
import fr.la.jproductbase.dao.FailureDaoImpl;
import fr.la.jproductbase.dao.FailureReportCommentDao;
import fr.la.jproductbase.dao.FailureReportCommentDaoImpl;
import fr.la.jproductbase.dao.FollowingFormModelDao;
import fr.la.jproductbase.dao.FollowingFormModelDaoImpl;
import fr.la.jproductbase.dao.OperatorDao;
import fr.la.jproductbase.dao.OperatorDaoImpl;
import fr.la.jproductbase.dao.ProductConfDao;
import fr.la.jproductbase.dao.ProductConfDaoImpl;
import fr.la.jproductbase.dao.ProductConfModelDao;
import fr.la.jproductbase.dao.ProductConfModelDaoImpl;
import fr.la.jproductbase.dao.ProductDao;
import fr.la.jproductbase.dao.ProductDaoImpl;
import fr.la.jproductbase.dao.ProductDocumentDao;
import fr.la.jproductbase.dao.ProductDocumentDaoImpl;
import fr.la.jproductbase.dao.ProductDocumentTypeDao;
import fr.la.jproductbase.dao.ProductDocumentTypeDaoImpl;
import fr.la.jproductbase.dao.ProductFamilyDao;
import fr.la.jproductbase.dao.ProductFamilyDaoImpl;
import fr.la.jproductbase.dao.ProductLineDao;
import fr.la.jproductbase.dao.ProductLineDaoImpl;
import fr.la.jproductbase.dao.ProductSupplyDao;
import fr.la.jproductbase.dao.ProductSupplyDaoImpl;
import fr.la.jproductbase.dao.ProductTypeDao;
import fr.la.jproductbase.dao.ProductTypeDaoImpl;
import fr.la.jproductbase.dao.ProductionFailureReportDao;
import fr.la.jproductbase.dao.ProductionFailureReportDaoImpl;
import fr.la.jproductbase.dao.SoftwareDao;
import fr.la.jproductbase.dao.SoftwareDaoImpl;
import fr.la.jproductbase.dao.TestTypeDao;
import fr.la.jproductbase.dao.TestTypeDaoImpl;
import fr.la.jproductbase.dao.TesterDao;
import fr.la.jproductbase.dao.TesterDaoImpl;
import fr.la.jproductbase.dao.TesterReportDao;
import fr.la.jproductbase.dao.TesterReportDaoImpl;
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
import fr.la.jproductbaseweb.beanmanaged.exception.GestRapportDefaultsException;

public class ServiceInterface implements Serializable {

    private static final long serialVersionUID = 1L;
    private static ServiceInterface serviceInterfaceInstance = null;
    ServiceManager sm;
    
    public static synchronized ServiceInterface getInstance() {
    	if (serviceInterfaceInstance == null) {
    		serviceInterfaceInstance = new ServiceInterface();
    	}
    	return serviceInterfaceInstance;
    }
    
    private ServiceInterface() {
		sm = ServiceManager.getServiceManagerInstance();

		sm.registerServiceAsUnique(ConnectionOperator.class , ConnectionOperator.class);
		sm.registerServiceAsUnique(ConnectionProduct.class , ConnectionProduct.class);
		sm.registerServiceAsUnique(ConnectionTester.class , ConnectionTester.class);
		sm.registerServiceAsUnique(ConnectionInfoSchema.class , ConnectionInfoSchema.class);
		
		
		sm.registerServiceAsUnique(AfterSaleComDao.class , AfterSaleComImpl.class);
		sm.registerServiceAsUnique(AfterSaleReportDao.class ,	AfterSaleReportDaoImpl.class);
		sm.registerServiceAsUnique(ApparentCauseCustomerDao.class , ApparentCauseCustomerDaoImpl.class);
		sm.registerServiceAsUnique(ApparentCauseDao.class , ApparentCauseDaoImpl.class);
		sm.registerServiceAsUnique(CustomerCommentDao.class , CustomerCommentDaoImpl.class);
		sm.registerServiceAsUnique(DefectDao.class , DefectDaoImpl.class);
		sm.registerServiceAsUnique(ElementChangedDao.class , ElementChangedDaoImpl.class);
		sm.registerServiceAsUnique(FailureDao.class , FailureDaoImpl.class);
		sm.registerServiceAsUnique(FailureReportCommentDao.class ,	FailureReportCommentDaoImpl.class);
		sm.registerServiceAsUnique(FollowingFormModelDao.class , FollowingFormModelDaoImpl.class);
		sm.registerServiceAsUnique(OperatorDao.class , OperatorDaoImpl.class);
		sm.registerServiceAsUnique(ProductConfDao.class , ProductConfDaoImpl.class);
		sm.registerServiceAsUnique(ProductConfModelDao.class ,	ProductConfModelDaoImpl.class);
		sm.registerServiceAsUnique(ProductDao.class , ProductDaoImpl.class);
		sm.registerServiceAsUnique(ProductDocumentDao.class , ProductDocumentDaoImpl.class);
		sm.registerServiceAsUnique(ProductDocumentTypeDao.class , ProductDocumentTypeDaoImpl.class);
		sm.registerServiceAsUnique(ProductFamilyDao.class , ProductFamilyDaoImpl.class);
		sm.registerServiceAsUnique(ProductionFailureReportDao.class , ProductionFailureReportDaoImpl.class);
		sm.registerServiceAsUnique(ProductLineDao.class , ProductLineDaoImpl.class);
		sm.registerServiceAsUnique(ProductSupplyDao.class , ProductSupplyDaoImpl.class);
		sm.registerServiceAsUnique(ProductTypeDao.class , ProductTypeDaoImpl.class);
		sm.registerServiceAsUnique(SoftwareDao.class , SoftwareDaoImpl.class);
		sm.registerServiceAsUnique(TesterDao.class , TesterDaoImpl.class);
		sm.registerServiceAsUnique(TesterReportDao.class , TesterReportDaoImpl.class);
		sm.registerServiceAsUnique(TestTypeDao.class , TestTypeDaoImpl.class);
		
		sm.registerServiceAsUnique(AfterSaleModule.class , AfterSaleModule.class);
		sm.registerServiceAsUnique(ApparentCauseModule.class , ApparentCauseModule.class);
		sm.registerServiceAsUnique(FailureModule.class , FailureModule.class); 
		sm.registerServiceAsUnique(FollowingFormModule.class , FollowingFormModule.class);
		sm.registerServiceAsUnique(OperatorModule.class , OperatorModule.class);
		sm.registerServiceAsUnique(PrintReport.class , PrintReport.class);
		sm.registerServiceAsUnique(ProductConfModelModule.class , ProductConfModelModule.class);
		sm.registerServiceAsUnique(ProductConfModule.class , ProductConfModule.class);
		sm.registerServiceAsUnique(ProductDocumentModule.class , ProductDocumentModule.class);
		sm.registerServiceAsUnique(ProductFamilyModule.class , ProductFamilyModule.class);
		sm.registerServiceAsUnique(ProductModule.class , ProductModule.class);
		sm.registerServiceAsUnique(ProductSupplyModule.class , ProductSupplyModule.class);
		sm.registerServiceAsUnique(ProductTypeModule.class , ProductTypeModule.class);
		sm.registerServiceAsUnique(SoftwareModule.class , SoftwareModule.class);
		sm.registerServiceAsUnique(TesterModule.class , TesterModule.class);
		sm.registerServiceAsUnique(TesterReportModule.class , TesterReportModule.class);
		sm.registerServiceAsUnique(TestTypeModule.class , TestTypeModule.class);
		sm.registerServiceAsUnique(XMLProductModule.class , XMLProductModule.class);
		sm.registerServiceAsUnique(XMLTesterModule.class , XMLTesterModule.class);
		
				
				
				
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
    public ApparentCause getApparentCause(int idApparentCause) {
        ApparentCauseModule _apparentCauseModule = (ApparentCauseModule) sm.getService(ApparentCauseModule.class);
        return _apparentCauseModule.getApparentCause(idApparentCause);
    }

    /**
     * Recherche les causes probables de la base de donn&eacute;es.
     *
     * @return Liste des causes probables.
     *
     * @throws SQLException
     */
    public List<ApparentCause> getApparentCauses() {
    	ApparentCauseModule _apparentCauseModule = (ApparentCauseModule) sm.getService(ApparentCauseModule.class);
        return _apparentCauseModule.getApparentCauses();
    }

    public ApparentCause setApparentCause(ApparentCause apparentCause, int state, String description, ApparentCauseCustomer apparentCauseCustomer) {
    	ApparentCauseModule _apparentCauseModule = (ApparentCauseModule) sm.getService(ApparentCauseModule.class);
        return _apparentCauseModule.setApparentCause(apparentCause, state, description, apparentCauseCustomer);
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
    public ApparentCauseCustomer getApparentCauseCustomer(int idApparentCauseCustomer) {
    	ApparentCauseModule _apparentCauseModule = (ApparentCauseModule) sm.getService(ApparentCauseModule.class);
        ApparentCauseCustomer _apparentCauseCustomer = _apparentCauseModule.getApparentCauseCustomer(idApparentCauseCustomer);
        return _apparentCauseCustomer;
    }

    /**
     * Recherche les causes probables client de la base de donn&eacute;es.
     *
     * @return Liste des causes probables client.
     *
     * @throws SQLException
     */
    public List<ApparentCauseCustomer> getApparentCausesCustomer() {
    	ApparentCauseModule _apparentCauseModule = (ApparentCauseModule) sm.getService(ApparentCauseModule.class);
        List<ApparentCauseCustomer> _apparentCausesCustomer = _apparentCauseModule.getApparentCausesCustomer();
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
    public List<ApparentCauseCustomer> getActiveApparentCausesCustomer() {
    	ApparentCauseModule _apparentCauseModule = (ApparentCauseModule) sm.getService(ApparentCauseModule.class);
        List<ApparentCauseCustomer> _apparentCausesCustomer = _apparentCauseModule.getActiveApparentCausesCustomer();
        return _apparentCausesCustomer;
    }

    // TODO Javadoc
    public ApparentCauseCustomer setApparentCauseCustomer(ApparentCauseCustomer apparentCauseCustomer, int state, String description) {
    	ApparentCauseModule _apparentCauseModule = (ApparentCauseModule) sm.getService(ApparentCauseModule.class);
    	ApparentCauseCustomer _apparentCauseCustomer = _apparentCauseModule.setApparentCauseCustomer(apparentCauseCustomer, state, description);
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
    public CustomerComment getCustomerComment(ProductionFailureReport productionFailureReport) {
        FailureModule _failureModule = (FailureModule) sm.getService(FailureModule.class);
        CustomerComment _customerComment = _failureModule.getCustomerComment(productionFailureReport);
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
    public void closeFailureReport(ProductionFailureReport productionFailureReport) {
    	FailureModule _failureModule = (FailureModule) sm.getService(FailureModule.class);
        _failureModule.closeProductionFailureReport(productionFailureReport);
    }

    // TODO : Javadoc
    public ProductionFailureReport getProductionFailureReport(int idProductionFailureReport)  {
    	FailureModule _failureModule = (FailureModule) sm.getService(FailureModule.class);
        ProductionFailureReport _failureReport = _failureModule.getProductionFailureReport(idProductionFailureReport);
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
    public List<ProductionFailureReport> getFailureReports(Product product) {
    	FailureModule _failureModule = (FailureModule) sm.getService(FailureModule.class);
        List<ProductionFailureReport> _failureReports = _failureModule.getProductionFailureReports(product);
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
    public List<ProductionFailureReport> getProductionFailureReports(Date fromDate, Date toDate) {
    	FailureModule _failureModule = (FailureModule) sm.getService(FailureModule.class);
        List<ProductionFailureReport> _failureReports = _failureModule.getProductionFailureReports(fromDate, toDate);
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
    protected ProductionFailureReport getFailureReport(TesterReport testerReport) {
    	FailureModule _failureModule = (FailureModule) sm.getService(FailureModule.class);
        ProductionFailureReport _failureReport = _failureModule.getProductionFailureReport(testerReport);
        return _failureReport;
    }

    // TODO : Javadoc
    public List<ProductionFailureReport> getUnclosedProductionFailureReports() {
    	FailureModule _failureModule = (FailureModule) sm.getService(FailureModule.class);
        List<ProductionFailureReport> _failureReports = _failureModule.getUnclosedProductionFailureReports();
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
            List<Failure> failures) {
    	FailureModule _failureModule = (FailureModule) sm.getService(FailureModule.class);
        ProductionFailureReport _failureReport = _failureModule
                .setProductionFailureReport(failureReport, failureReportDate,
                productConfReference, productConfMajorIndex,
                productMinorIndex, serialNumber, datecode,
                testTypeName, testerName, operatorCode,
                customerComment, failureCode, comment, failures);

        return _failureReport;
    }

    public Failure addFailureDismantedCard(Failure failure, ProductionFailureReport productionFailureReport) {
        FailureModule _failureModule = (FailureModule) sm.getService(FailureModule.class);
        Failure _failure = _failureModule.addFailureDismantedCard(productionFailureReport, failure);
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
            String customerComment) {
    	FailureModule _failureModule = (FailureModule) sm.getService(FailureModule.class);
        ProductionFailureReport _failureReport = _failureModule.setProductionFailureReport(testerReport, customerComment);
        return _failureReport;
    }

    public ProductionFailureReport updateFailureReport(ProductionFailureReport productionFailureReport) {
    	FailureModule _failureModule = (FailureModule) sm.getService(FailureModule.class);
        ProductionFailureReport _failureReport = _failureModule.updateProductionFailureReport(productionFailureReport);
        return _failureReport;
    }

    /*
     * FailureReportComment
     */
    public FailureReportComment getFailureReportComment(ProductionFailureReport productionFailureReport){
    	FailureModule _failureModule = (FailureModule) sm.getService(FailureModule.class);
        FailureReportComment _failureReportComment = _failureModule.getFailureReportComment(productionFailureReport);
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
    public List<FollowingFormModel> getAllActiveFollowingFormModel() {
        FollowingFormModule _followingFormModule = (FollowingFormModule) sm.getService(FollowingFormModule.class);
        List<FollowingFormModel> _followingFormModels = _followingFormModule.getAllActiveFollowingFormModel();
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
    public FollowingFormModel getFollowingFormModel(int idFollowingFormModel) {
    	FollowingFormModule _followingFormModule = (FollowingFormModule) sm.getService(FollowingFormModule.class);
        FollowingFormModel _followingFormModel = _followingFormModule.getFollowingFormModel(idFollowingFormModel);
        return _followingFormModel;
    }

    public Operator addOperator(String firstName, String lastName, String code, int state) {
    	OperatorModule _operatorModule = (OperatorModule) sm.getService(OperatorModule.class);
        Operator _operator = _operatorModule.addOperator(firstName, lastName, code, state);
        return _operator;
    }

    public Operator getOperator(int idOperator) {
    	OperatorModule _operatorModule = (OperatorModule) sm.getService(OperatorModule.class);
        Operator _operator = _operatorModule.getOperator(idOperator);
        return _operator;
    }

    public Operator getOperator(String code) throws SQLException {
    	OperatorModule _operatorModule = (OperatorModule) sm.getService(OperatorModule.class);
        Operator _operator = _operatorModule.getOperator(code);
        return _operator;
    }

    public List<Operator> getOperators() {
    	OperatorModule _operatorModule = (OperatorModule) sm.getService(OperatorModule.class);
        List<Operator> _operators = _operatorModule.getOperators();
        return _operators;
    }

    public List<Operator> getActiveOperators() {
    	OperatorModule _operatorModule = (OperatorModule) sm.getService(OperatorModule.class);
        List<Operator> _operators = _operatorModule.getActiveOperators();
        return _operators;
    }

    public void updateOperator(Operator operator) {
    	OperatorModule _operatorModule = (OperatorModule) sm.getService(OperatorModule.class);
        _operatorModule.updateOperator(operator);
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
    public Product addProduct(ProductConf productConf, String serialNumber, String datecode, String providerCode) {
        ProductModule _productModule = (ProductModule) sm.getService(ProductModule.class);
        Product _product = _productModule.addProduct(productConf, serialNumber, datecode, providerCode);
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
    public Product getProduct(int idProduct) {
    	ProductModule _productModule = (ProductModule) sm.getService(ProductModule.class);
        Product _product = _productModule.getProduct(idProduct);
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
    	ProductModule _productModule = (ProductModule) sm.getService(ProductModule.class);
        return _productModule.countQueryResult(filters, type);
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
    public Product getProduct(String productConfReference, String serialNumber, String datecode)  {
    	ProductModule _productModule = (ProductModule) sm.getService(ProductModule.class);
        return _productModule.getProduct(productConfReference, serialNumber, datecode);
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
    public Product getProduct(ProductConf productConf, String serialNumber, String datecode) {
    	ProductModule _productModule = (ProductModule) sm.getService(ProductModule.class);
        return _productModule.getProduct(productConf, serialNumber, datecode);
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
    public Product getMainProduct(Product carte) {
    	ProductModule _productModule = (ProductModule) sm.getService(ProductModule.class);
        return _productModule.getMainProduct(carte);
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
    public List<Product> getProducts(ProductType productType)  {
    	ProductModule _productModule = (ProductModule) sm.getService(ProductModule.class);
        return _productModule.getProducts(productType);
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
    public List<Product> getProducts(ProductConfModel productConfModel) {
    	ProductModule _productModule = (ProductModule) sm.getService(ProductModule.class);
        return _productModule.getProducts(productConfModel);
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
    public List<Product> getProducts(ProductConf productConf) {
    	ProductModule _productModule = (ProductModule) sm.getService(ProductModule.class);
        return _productModule.getProducts(productConf);
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
    public List<Product> getProductsEnables(int idproduct, String reference) {
    	ProductModule _productModule = (ProductModule) sm.getService(ProductModule.class);
        return _productModule.getProductsEnables(idproduct, reference);
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
    public List<Product> getProductsRecordables(String modele) {
    	ProductModule _productModule = (ProductModule) sm.getService(ProductModule.class);
        return _productModule.getProductsRecordables(modele);
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
    public List<Product> getProductsSearch(int startingAt, int maxPerPage, Map<String, String> filters, int type) {
    	ProductModule _productModule = (ProductModule) sm.getService(ProductModule.class);
    	return _productModule.getProductsSearch(startingAt, maxPerPage, filters, type);
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
    public List<Product> getProductComponents(Product product) {
    	ProductModule _productModule = (ProductModule) sm.getService(ProductModule.class);
        return _productModule.getProductComponents(product);
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
    public List<Product> getProducts(String serialNumber, String datecode) {
    	ProductModule _productModule = (ProductModule) sm.getService(ProductModule.class);
        return _productModule.getProducts(serialNumber, datecode);
    }

    /**
     * Recherche les produits devant faire l'objet d'une demande de
     * d&eacute;rogation.
     *
     * @return Produits devant faire l'objet d'une demande de d&eacute;rogation.
     *
     * @throws SQLException
     */
    public List<Product> getProductsDispensationNeeded() {
    	ProductModule _productModule = (ProductModule) sm.getService(ProductModule.class);
        return _productModule.getProductsDispensationNeeded();
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
    protected boolean isNeedDispensation(Product product) {
    	ProductModule _productModule = (ProductModule) sm.getService(ProductModule.class);
        return _productModule.isNeedDispensation(product);
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
    public Product retreiveProduct(String productConfReference, String serialNumber, String datecode) {
    	ProductModule _productModule = (ProductModule) sm.getService(ProductModule.class);
        return _productModule.retreiveProduct(productConfReference, serialNumber, datecode);
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
    public Product setProduct(Product product) {
    	ProductModule _productModule = (ProductModule) sm.getService(ProductModule.class);
        return _productModule.setProduct(product);
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
            String[][] productSoftwares) {
    	ProductModule _productModule = (ProductModule) sm.getService(ProductModule.class);
        return _productModule.setProduct(idProduct,
                productConfReference, productConfMajorIndex,
                productConfMinorIndex, serialNumber, datecode,
                productComponents, productSoftwares);
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
            List<Software> productSoftwares) {
    	ProductModule _productModule = (ProductModule) sm.getService(ProductModule.class);
        return _productModule.setProduct(product, productConf,
                serialNumber, dateCode, macAddress, providerCode, state,
                productComponents, productSoftwares);
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
            String[][] productSoftwares) {
    	XMLProductModule _XMLproductModule = (XMLProductModule) sm.getService(XMLProductModule.class);
        _XMLproductModule.setProductXml(productConfReference,
                productConfMajorIndex, productConfMinorIndex, serialNumber,
                datecode, productComponents, productSoftwares);
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
    public void updateProduct(Product product, String macAddress) {
    	ProductModule _productModule = (ProductModule) sm.getService(ProductModule.class);
        _productModule.updateProduct(product, macAddress);
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
            StringBuffer customerComment) {
    	XMLProductModule _XMLProductModule = (XMLProductModule) sm.getService(XMLProductModule.class);
        _XMLProductModule.integrationTransfer(testerReportFileName,labviewTesterReport, confirmTestResult, customerComment);
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
    public ProductConfModel getProductConfModel(int idProductConfModel) {
        ProductConfModelModule _productConfModelModule = (ProductConfModelModule) sm.getService(ProductConfModelModule.class);
        ProductConfModel _productConfModel = _productConfModelModule.getProductConfModel(idProductConfModel);
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
    public ProductConfModel getProductConfModel(String reference) {
        ProductConfModelModule _productConfModelModule = (ProductConfModelModule) sm.getService(ProductConfModelModule.class);
        ProductConfModel _productConfModel = _productConfModelModule.getProductConfModel(reference);
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
    public List<ProductConfModel> getProductConfModels() {
        ProductConfModelModule _productConfModelModule = (ProductConfModelModule) sm.getService(ProductConfModelModule.class);
        List<ProductConfModel> _productConfModels = _productConfModelModule.getProductConfModels();
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
    public List<ProductConfModel> getActiveProductConfModels(ProductType productType) {
        ProductConfModelModule _productConfModelModule = (ProductConfModelModule) sm.getService(ProductConfModelModule.class);
        List<ProductConfModel> _productConfModels = _productConfModelModule.getActiveProductConfModels(productType);
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
    public List<ProductConfModel> getActiveProductConfModels() {
        ProductConfModelModule _productConfModelModule = (ProductConfModelModule) sm.getService(ProductConfModelModule.class);
        List<ProductConfModel> _productConfModels = _productConfModelModule.getActiveProductConfModels();
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
    public List<ProductConfModel> getProductConfModels(int type) {
        ProductConfModelModule _productConfModelModule = (ProductConfModelModule) sm.getService(ProductConfModelModule.class);
        List<ProductConfModel> _productConfModels = _productConfModelModule.getProductConfModels(type);
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
    public ProductConf getProductConf(int idProductConf) {
    	ProductConfModule _productConfModule = (ProductConfModule) sm.getService(ProductConfModule.class);
        ProductConf _productConf = _productConfModule.getProductConf(idProductConf);
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
    public ProductConf getProductConf(String reference, String majorIndex, String minorIndex) {
    	ProductConfModule _productConfModule = (ProductConfModule) sm.getService(ProductConfModule.class);
        ProductConf _productConf = _productConfModule.getProductConf(reference, majorIndex, minorIndex);
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
    public ProductConf getLastActiveProductConf(String reference, String majorIndex, String minorIndex) {
    	ProductConfModule _productConfModule = (ProductConfModule) sm.getService(ProductConfModule.class);
        ProductConf _productConf = _productConfModule.getLastActiveProductConf(reference, majorIndex, minorIndex);
        return _productConf;
    }

    /**
     * Recherche les configurations produit de la base de donn&eacute;es.
     *
     * @return Liste des configurations produit.
     *
     * @throws SQLException
     */
    public List<ProductConf> getProductConfs() {
    	ProductConfModule _productConfModule = (ProductConfModule) sm.getService(ProductConfModule.class);
        List<ProductConf> _productConfs = _productConfModule.getProductConfs();
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
    public List<ProductConf> getProductConfs(int type) {
    	ProductConfModule _productConfModule = (ProductConfModule) sm.getService(ProductConfModule.class);
        List<ProductConf> _productConfs = _productConfModule.getProductConfs(type);
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
    public List<ProductConf> getProductConfs(String reference) {
    	ProductConfModule _productConfModule = (ProductConfModule) sm.getService(ProductConfModule.class);
        List<ProductConf> _productConfs = _productConfModule.getProductConfs(reference);
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
    public List<ProductConf> getActiveProductConfs() {
    	ProductConfModule _productConfModule = (ProductConfModule) sm.getService(ProductConfModule.class);
        List<ProductConf> _productConfs = _productConfModule.getActiveProductConfs();
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
    public List<ProductConf> getActiveProductConfs(ProductType productType) {
    	ProductConfModule _productConfModule = (ProductConfModule) sm.getService(ProductConfModule.class);
        List<ProductConf> _productConfs = _productConfModule.getActiveProductConfs(productType);
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
    public List<ProductConf> getProductConfComponents(ProductConf productConf) {
    	ProductConfModule _productConfModule = (ProductConfModule) sm.getService(ProductConfModule.class);
        List<ProductConf> _productConfs = _productConfModule.getProductConfComponents(productConf);
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
            List<Software> productConfSoftwares) {
    	ProductConfModule _productConfModule = (ProductConfModule) sm.getService(ProductConfModule.class);

        ProductConf _productConf = _productConfModule.setProductConf(
                productConf, reference, majorIndex, minorIndex,
                productConfModel, productFamily, productSupply, identifiable,
                state, followingFormModel, productConfComponents,
                productConfSoftwares);

        return _productConf;
    }

    /*
     * ProductFamily
     */
    // 15-12-11 : RMO : Creation de la méthode
    // TODO Javadoc
    public ProductFamily addProductFamily(String name, int state, ProductType productType) {
    	ProductFamilyModule _productFamilyModule = (ProductFamilyModule) sm.getService(ProductFamilyModule.class);
        ProductFamily _productFamily = _productFamilyModule.addProductFamily(name, state, productType);
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
    public ProductFamily getProductFamily(int idProductFamily) {
    	ProductFamilyModule _productFamilyModule = (ProductFamilyModule) sm.getService(ProductFamilyModule.class);
        ProductFamily _productFamily = _productFamilyModule.getProductFamily(idProductFamily);
        return _productFamily;
    }

    /**
     * Recherche les familles de produit de la base de donn&eacute;es.
     *
     * @return Liste des familles de produit.
     *
     * @throws SQLException
     */
    public List<ProductFamily> getProductFamilies() {
    	ProductFamilyModule _productFamilyModule = (ProductFamilyModule) sm.getService(ProductFamilyModule.class);
    	List<ProductFamily> _productFamilies = _productFamilyModule.getProductFamilies();
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
    public List<ProductFamily> getProductFamilies(int type) {
    	ProductFamilyModule _productFamilyModule = (ProductFamilyModule) sm.getService(ProductFamilyModule.class);
        List<ProductFamily> _productFamilies = _productFamilyModule.getProductFamilies(type);
        return _productFamilies;
    }

    /**
     * Recherche les familles de produit actives de la base de donn&eacute;es.
     *
     * @return Liste des familles de produit.
     *
     * @throws SQLException
     */
    public List<ProductFamily> getActiveProductFamilies() {
    	ProductFamilyModule _productFamilyModule = (ProductFamilyModule) sm.getService(ProductFamilyModule.class);
        List<ProductFamily> _productFamilies = _productFamilyModule.getActiveProductFamilies();
        return _productFamilies;
    }

    /**
     * Mise à jour d'une famille de produits déjà enregistrée en base
     *
     * @param productFamily la famille de produits à mettre à jour
     * @throws NamingException
     */
    public void updateProductFamily(ProductFamily productFamily) {
    	ProductFamilyModule _productFamilyModule = (ProductFamilyModule) sm.getService(ProductFamilyModule.class);
        _productFamilyModule.updateProductFamily(productFamily);
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
    public ProductSupply addProductSupply(String name, int state) {
    	ProductSupplyModule _productSupplyModule = (ProductSupplyModule) sm.getService(ProductSupplyModule.class);
        ProductSupply _productSupply = _productSupplyModule.addProductSupply(name, state);
        return _productSupply;
    }

    /**
     * Recherche une alimentation de produit de la base de donn&eacute;es.
     *
     * @return Alimentation de produit.
     *
     * @throws SQLException
     */
    public ProductSupply getProductSupply(int idProductSupply) {
    	ProductSupplyModule _productSupplyModule = (ProductSupplyModule) sm.getService(ProductSupplyModule.class);
        ProductSupply _productSupply = _productSupplyModule.getProductSupply(idProductSupply);
        return _productSupply;
    }

    /**
     * Recherche les alimentations de produit de la base de donn&eacute;es.
     *
     * @return Liste des alimentations de produit.
     *
     * @throws SQLException
     */
    public List<ProductSupply> getProductSupplies() {
    	ProductSupplyModule _productSupplyModule = (ProductSupplyModule) sm.getService(ProductSupplyModule.class);
        List<ProductSupply> _productSupplies = _productSupplyModule.getProductSupplies();
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
    public List<ProductSupply> getActiveProductSupplies() {
    	ProductSupplyModule _productSupplyModule = (ProductSupplyModule) sm.getService(ProductSupplyModule.class);
        List<ProductSupply> _productSupplies = _productSupplyModule.getActiveProductSupplies();
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
    public void updateProductSupply(ProductSupply productSupply) {
    	ProductSupplyModule _productSupplyModule = (ProductSupplyModule) sm.getService(ProductSupplyModule.class);
        _productSupplyModule.updateProductSupply(productSupply);
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
    public ProductType addProductType(String name, int state) {
    	ProductTypeModule _productTypeModule = (ProductTypeModule) sm.getService(ProductTypeModule.class);
        ProductType _productType = _productTypeModule.addProductType(name, state);
        return _productType;
    }

    /**
     * Recherche les types de produit actifs de la base de donn&eacute;es.
     *
     * @return Liste des types de produit.
     *
     * @throws SQLException
     */
    public List<ProductType> getActiveProductTypes() {
    	ProductTypeModule _productTypeModule = (ProductTypeModule) sm.getService(ProductTypeModule.class);
        List<ProductType> _productTypes = _productTypeModule.getActiveProductTypes();
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
    public ProductType getProductType(int idProductTypeName) {
    	ProductTypeModule _productTypeModule = (ProductTypeModule) sm.getService(ProductTypeModule.class);
        ProductType _productType = _productTypeModule.getProductType(idProductTypeName);
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
    public ProductType getProductType(String productTypeName) {
    	ProductTypeModule _productTypeModule = (ProductTypeModule) sm.getService(ProductTypeModule.class);
        ProductType _productType = _productTypeModule.getProductType(productTypeName);
        return _productType;
    }

    /**
     * Recherche les types de produit de la base de donn&eacute;es.
     *
     * @return Liste des types de produit.
     *
     * @throws SQLException
     */
    public List<ProductType> getProductTypes() {
    	ProductTypeModule _productTypeModule = (ProductTypeModule) sm.getService(ProductTypeModule.class);
        List<ProductType> _productTypes = _productTypeModule.getProductTypes();
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
    public void updateProductType(ProductType productType) {
    	ProductTypeModule _productTypeModule = (ProductTypeModule) sm.getService(ProductTypeModule.class);
        _productTypeModule.updateProductType(productType);
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
    public Software addSoftware(String name, String version) {
    	SoftwareModule _softwareModule = (SoftwareModule) sm.getService(SoftwareModule.class);
        Software _software = _softwareModule.addSoftware(name, version);
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
    public Software addSoftware(int state, String name, String version) {
    	SoftwareModule _softwareModule = (SoftwareModule) sm.getService(SoftwareModule.class);
        Software _software = _softwareModule.addSoftware(state, name, version);
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
    public Software getSoftware(String name, String version) {
    	SoftwareModule _softwareModule = (SoftwareModule) sm.getService(SoftwareModule.class);
        Software _software = _softwareModule.getSoftware(name, version);
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
    public Software getSoftware(int idSoftware) {
    	SoftwareModule _softwareModule = (SoftwareModule) sm.getService(SoftwareModule.class);
        Software _software = _softwareModule.getSoftware(idSoftware);
        return _software;
    }

    /**
     * Recherche les logiciels de la base de donn&eacute;es.
     *
     * @return software Logiciel.
     *
     * @throws SQLException
     */
    public List<Software> getSoftwares() {
    	SoftwareModule _softwareModule = (SoftwareModule) sm.getService(SoftwareModule.class);
        List<Software> _softwares = _softwareModule.getSoftwares();
        return _softwares;
    }

    /**
     * Recherche les logiciels actifs de la base de donn&eacute;es.
     *
     * @return _software Liste de logiciel.
     *
     * @throws SQLException
     */
    public List<Software> getActiveSoftwares() {
    	SoftwareModule _softwareModule = (SoftwareModule) sm.getService(SoftwareModule.class);
        List<Software> _softwares = _softwareModule.getActiveSoftwares();
        return _softwares;
    }

    /**
     * Mise à jour d'un logiciel déjà enregistré en base
     *
     * @param software Logiciel &agrave; mettre à jour
     * @throws NamingException
     */
    public void updateSoftware(Software software) {
    	SoftwareModule _softwareModule = (SoftwareModule) sm.getService(SoftwareModule.class);
        _softwareModule.updateSoftware(software);
    }

    /*
     * Tester
     */
    public Tester addTester(String name, int state) {
    	TesterModule _testerModule = (TesterModule) sm.getService(TesterModule.class);
        Tester _tester = _testerModule.addTester(name, state);
        return _tester;
    }

    public Tester getTester(int idTester) {
    	TesterModule _testerModule = (TesterModule) sm.getService(TesterModule.class);
        Tester _tester = _testerModule.getTester(idTester);
        return _tester;
    }

    public Tester getTester(String name) {
    	TesterModule _testerModule = (TesterModule) sm.getService(TesterModule.class);
        Tester _tester = _testerModule.getTester(name);
        return _tester;
    }

    public List<Tester> getTesters() {
    	TesterModule _testerModule = (TesterModule) sm.getService(TesterModule.class);
    	List<Tester> _testers = _testerModule.getTesters();
        return _testers;
    }

    public List<Tester> getActiveTesters() {
    	TesterModule _testerModule = (TesterModule) sm.getService(TesterModule.class);
    	List<Tester> _testers = _testerModule.getActiveTesters();
        return _testers;
    }

    public void updateTester(Tester tester) {
    	TesterModule _testerModule = (TesterModule) sm.getService(TesterModule.class);
    	_testerModule.updateTester(tester);
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
            String testTypeName) {
    	TesterReportModule _testerReportModule = (TesterReportModule) sm.getService(TesterReportModule.class);
        TesterReport _testerReport = _testerReportModule.getTesterReport(
                testerReportDate, productConfReference, productConfMajorIndex,
                productConfMinorIndex, serialNumber, datecode, testTypeName);
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
            String customerComment) {
    	TesterReportModule _testerReportModule = (TesterReportModule) sm.getService(TesterReportModule.class);
    	TesterReport _testerReport = _testerReportModule.setTesterReport(
                testerReportDate, productConfReference, productConfMajorIndex,
                productConfMinorIndex, serialNumber, datecode, testTypeName,
                reportOperatorCode, testResult, customerComment);
        return _testerReport;
    }

    public TesterReport addTesterReport(TestType testType, Tester tester,String operatorCode, Product product) {
        TesterReportModule _testerReportModule = (TesterReportModule) sm.getService(TesterReportModule.class);
        TesterReport _testerReport = _testerReportModule.addTesterReport(testType, tester, operatorCode, product);
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
    public boolean inFlowProcess(Product product, TestType testType) {
    	TesterReportModule _testerReportModule = (TesterReportModule) sm.getService(TesterReportModule.class);
        boolean _inFlowProcess = _testerReportModule.inFlowProcess(product,testType);
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
    public TesterReport saveTesterReport(PreTesterReport preTesterReport,LabviewTesterReport labviewTesterReport) {
    	TesterReportModule _testerReportModule = (TesterReportModule) sm.getService(TesterReportModule.class);
        TesterReport _testerReport = _testerReportModule.saveTesterReport(preTesterReport, labviewTesterReport);
        return _testerReport;
    }

    public TestType addTestType(String name, int state, boolean needTester) {
        TestTypeModule _testTypeModule = (TestTypeModule) sm.getService(TestTypeModule.class);
        TestType _testType = _testTypeModule.addTestType(name, state,needTester);
        return _testType;
    }

    public TestType getTestType(int idTestType) {
    	TestTypeModule _testTypeModule = (TestTypeModule) sm.getService(TestTypeModule.class);
        TestType _testType = _testTypeModule.getTestType(idTestType);
        return _testType;
    }

    public TestType getTestType(String name) {
    	TestTypeModule _testTypeModule = (TestTypeModule) sm.getService(TestTypeModule.class);
        TestType _testType = _testTypeModule.getTestType(name);
        return _testType;
    }

    public List<TestType> getTestTypes() {
    	TestTypeModule _testTypeModule = (TestTypeModule) sm.getService(TestTypeModule.class);
        List<TestType> _testTypes = _testTypeModule.getTestTypes();
        return _testTypes;
    }

    /**
     * Recherche une type de test dans la la base de donn&eacute;es.
     *
     * @param labviewTestType Type de test labview.
     *
     * @return Type de test.
     */
    public TestType retreiveTestType(LabviewTestType labviewTestType) {
    	TestTypeModule _testTypeModule = (TestTypeModule) sm.getService(TestTypeModule.class);
        TestType _testType = _testTypeModule.retreiveTestType(labviewTestType);
        return _testType;
    }

    public void updateTestType(TestType testType) {
    	TestTypeModule _testTypeModule = (TestTypeModule) sm.getService(TestTypeModule.class);
    	_testTypeModule.updateTestType(testType);
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
    public void insertClientFiles() {
    	XMLProductModule _xmlProductModule = (XMLProductModule) sm.getService(XMLProductModule.class);
        _xmlProductModule.insertClientFiles();
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
    public Product setProductFEDDtoLAI(int idProductFEDD, ProductConf config, String serialNumber, String datecode) {
        ProductModule _productModule = (ProductModule) sm.getService(ProductModule.class);
        Product _product = _productModule.setProductFEDDtoLAI(idProductFEDD,config, serialNumber, datecode);
        return _product;
    }

    public void addFailures(AfterSaleReport afterSaleReport) {
        FailureModule _failureModule = (FailureModule) sm.getService(FailureModule.class);
        _failureModule.addFailures(afterSaleReport);
    }

    public Failure addFailure(Failure failure, AfterSaleReport afterSaleReport) {
    	FailureModule _failureModule = (FailureModule) sm.getService(FailureModule.class);
    	return _failureModule.addFailure(failure, afterSaleReport);
    }

    public void removeFailure(Failure failure) {
    	FailureModule _failureModule = (FailureModule) sm.getService(FailureModule.class);
        _failureModule.removeFailure(failure);
    }

    public void updateFailure(Failure failure) {
    	FailureModule _failureModule = (FailureModule) sm.getService(FailureModule.class);
        _failureModule.updateFailure(failure);
    }

    public List<Failure> updateFailure(Product product, List<Failure> failures) {
    	FailureModule _failureModule = (FailureModule) sm.getService(FailureModule.class);
        return _failureModule.updateFailures(product, failures);
    }

    public List<AfterSaleReport> getAfterSaleReports() {
    	AfterSaleModule _afterSaleReportModule = (AfterSaleModule) sm.getService(AfterSaleModule.class);
        return _afterSaleReportModule.getAfterSaleReports();
    }

    public List<AfterSaleReport> getAfterSaleReports(Product product) {
    	AfterSaleModule _afterSaleReportModule = (AfterSaleModule) sm.getService(AfterSaleModule.class);
        return _afterSaleReportModule.getAfterSaleReports(product);
    }

    public List<AfterSaleReport> getAfterSaleReports(int idproduct) {
    	AfterSaleModule _afterSaleReportModule = (AfterSaleModule) sm.getService(AfterSaleModule.class);
        return _afterSaleReportModule.getAfterSaleReports(idproduct);
    }

    public List<AfterSaleReport> getAfterSaleReports(Date fromDate, Date toDate) {
    	AfterSaleModule _afterSaleReportModule = (AfterSaleModule) sm.getService(AfterSaleModule.class);
        java.sql.Date _fromDate = new java.sql.Date(fromDate.getTime());
        java.sql.Date _toDate = new java.sql.Date(toDate.getTime());
        List<AfterSaleReport> _afterSaleReports = _afterSaleReportModule.getAfterSaleReports(_fromDate, _toDate);
        return _afterSaleReports;
    }

    public AfterSaleReport getAfterSaleReport(int idAfterSaleReport) {
    	AfterSaleModule _afterSaleReportModule = (AfterSaleModule) sm.getService(AfterSaleModule.class);
        AfterSaleReport _afterSaleReport = _afterSaleReportModule.getAfterSaleReport(idAfterSaleReport);
        return _afterSaleReport;
    }

    public AfterSaleReport setAfterSaleReport(AfterSaleReport afterSaleReport,
            Date arrivalDate, String ecsNumber, String ncNature,
            Date firstAnalyseDate, Date materialInfoDate, Date reparationDate,
            Date qualityControlDate, Date expeditionDate, int functionnalTest,
            int visualControl, String asker, String intervenant,
            String interventionSheetLink, String comment,
            List<Failure> failures, ApparentCause apparentCause,
            String majorIndexIn, String majorIndexOut, Product product) {
    	AfterSaleModule _afterSaleReportModule = (AfterSaleModule) sm.getService(AfterSaleModule.class);

        AfterSaleReport _afterSaleReport = _afterSaleReportModule
                .setAfterSaleReport(afterSaleReport, arrivalDate, ecsNumber,
                ncNature, firstAnalyseDate, materialInfoDate,
                reparationDate, qualityControlDate, expeditionDate,
                functionnalTest, visualControl, asker, intervenant,
                interventionSheetLink, comment, failures,
                apparentCause, majorIndexIn, majorIndexOut, product);

        return _afterSaleReport;
    }

    public AfterSaleReport setAfterSaleReport(AfterSaleReport afterSaleReport) {
    	AfterSaleModule _afterSaleReportModule = (AfterSaleModule) sm.getService(AfterSaleModule.class);
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

        return _afterSaleReport;
    }

    /*
     * Mise à jour de la date qualitycontroldate en fonction de l'id
     * afterSaleReport
     */
    public void updateAfterSaleReportQualityControl(AfterSaleReport AfterSaleReport) {
        // Update failureReport
    	AfterSaleModule _afterSaleReportModule = (AfterSaleModule) sm.getService(AfterSaleModule.class);
        _afterSaleReportModule.updateAfterSaleReportQualityControl(AfterSaleReport);
    }

    /*
     * Renvoi la List de toutes les AfterSaleReport possédant une reparationDate
     * et n'ayant pas de qualityControlDate et expeditionDate
     */
    public List<AfterSaleReport> getAfterSaleReportQualityControl() {
    	AfterSaleModule _afterSaleReportModule = (AfterSaleModule) sm.getService(AfterSaleModule.class);
        List<AfterSaleReport> _afterSaleReports = _afterSaleReportModule.getAfterSaleReportsValidControl();
        return _afterSaleReports;
    }

    /*
     * Mise à jour de la date qualitycontroldate en fonction de l'id
     * afterSaleReport
     */
    public void updateAfterSaleReportExpedSAV(AfterSaleReport AfterSaleReport) {
    	AfterSaleModule _afterSaleReportModule = (AfterSaleModule) sm.getService(AfterSaleModule.class);
        _afterSaleReportModule.updateAfterSaleReportExpedSAV(AfterSaleReport);
    }

    /*
     * Renvoi la List de toutes les AfterSaleReport possédant une reparationDate
     * et n'ayant pas de qualityControlDate et expeditionDate
     */
    public List<AfterSaleReport> getAfterSaleReportExpedSAV() {
    	AfterSaleModule _afterSaleReportModule = (AfterSaleModule) sm.getService(AfterSaleModule.class);
        List<AfterSaleReport> _afterSaleReports = _afterSaleReportModule.getAfterSaleReportsExpedSAV();
        return _afterSaleReports;
    }

    /*
     * Renvoi toutes les données de la table AfterSaleCom
     */
    public List<AfterSaleCom> getAfterSaleCom() {
    	AfterSaleModule _afterSaleReportModule = (AfterSaleModule) sm.getService(AfterSaleModule.class);
        List<AfterSaleCom> _afterSaleCom = _afterSaleReportModule.getAfterSaleCom();
        return _afterSaleCom;
    }

    /*
     * Renvoi une aftersalecom en fonction de l'id
     */
    public AfterSaleCom getAfterSaleCom(String idaftersalecom) {
    	AfterSaleModule _afterSaleReportModule = (AfterSaleModule) sm.getService(AfterSaleModule.class);
        AfterSaleCom _afterSaleCom = _afterSaleReportModule.getAfterSaleCom(idaftersalecom);
        return _afterSaleCom;
    }

    /*
     * Ajout le Numero de Devis, le Montant, la date du devis et le commentaire
     * a un AfterSaleCom
     */
    public void updateDevisPrea(AfterSaleCom AfterSaleCom) {
    	AfterSaleModule _afterSaleReportModule = (AfterSaleModule) sm.getService(AfterSaleModule.class);
        _afterSaleReportModule.UpdateDevisPrealable(AfterSaleCom);
    }

    /*
     * Renvoi les donn�es qui servent a remplir le formulaire Devis Prealable
     */
    public List<AfterSaleCom> getDevisPrea() {
    	AfterSaleModule _afterSaleReportModule = (AfterSaleModule) sm.getService(AfterSaleModule.class);
        List<AfterSaleCom> _aftersalecom = _afterSaleReportModule.getAfterSaleDevisPrea();
        return _aftersalecom;
    }

    /*
     * Renvoi les donn�es qui servent a remplir le formulaire Devis Repa
     */
    public List<AfterSaleReport> getDevisRepa() {
    	AfterSaleModule _afterSaleReportModule = (AfterSaleModule) sm.getService(AfterSaleModule.class);
    	List<AfterSaleReport> _aftersalereport = _afterSaleReportModule.getAfterSaleDevisRepa();
        return _aftersalereport;
    }
    
    /*
     * Renvoi le resultat de la recherche pour le formulaire numCommande.
     */
    public List<AfterSaleCom> RechercheNumCmd(String recherche) {
    	AfterSaleModule _afterSaleReportModule = (AfterSaleModule) sm.getService(AfterSaleModule.class);
        List<AfterSaleCom> _afterSaleCom = _afterSaleReportModule.rechercheNumCmd(recherche);
        return _afterSaleCom;
    }

    /*
     * Enregistre le Numero et la Date de Commande pour un aftersalecom
     */
    public void addCmd(AfterSaleCom aftersalecom) {
    	AfterSaleModule _afterSaleReportModule = (AfterSaleModule) sm.getService(AfterSaleModule.class);
        _afterSaleReportModule.addCmd(aftersalecom);
    }

    /*
     * Update un AfterSaleCom
     */
    public void updateAfterSaleCom(AfterSaleCom _aftersalecom) throws Exception {
    	AfterSaleModule _afterSaleReportModule = (AfterSaleModule) sm.getService(AfterSaleModule.class);
        _afterSaleReportModule.updateAfterSaleCom(_aftersalecom);
    }

    /*
     * Retourne l'ensemble des AfterSaleComs pour les ExpedSAV
     */
    public List<AfterSaleCom> getAfterSaleComExped()  {
    	AfterSaleModule _afterSaleReportModule = (AfterSaleModule) sm.getService(AfterSaleModule.class);
        List<AfterSaleCom> _aftersalecom = _afterSaleReportModule.getAfterSaleComExpedSAV();
        return _aftersalecom;

    }

    // TODO Javadoc
	/*
     * 21-06-12 : RMO : Méthodes sur les productDocuments et
     * ProductDocumentTypes
     */
    public ProductDocument addProductDocument(int state, String title, String link, ProductDocumentType productDocumentType, Product product) {
    	ProductDocumentModule _productDocumentModule = (ProductDocumentModule) sm.getService(ProductDocumentModule.class);
        ProductDocument _productDocument = _productDocumentModule.addProductDocument(state, title, link, productDocumentType,product);
        return _productDocument;

    }

    public ProductDocument updateProductDocument(ProductDocument productdocument) {
    	ProductDocumentModule _productDocumentModule = (ProductDocumentModule) sm.getService(ProductDocumentModule.class);
        ProductDocument _productDocument = _productDocumentModule.updateProductDocument(productdocument);
        return _productDocument;

    }

    public ProductDocument getProductDocument(String title, String link) {
    	ProductDocumentModule _productDocumentModule = (ProductDocumentModule) sm.getService(ProductDocumentModule.class);
        ProductDocument _productDocument = _productDocumentModule.getProductDocument(title, link);
        return _productDocument;

    }

    public ProductDocument getProductDocument(int idProductDocument) {
    	ProductDocumentModule _productDocumentModule = (ProductDocumentModule) sm.getService(ProductDocumentModule.class);
        ProductDocument _productDocument = _productDocumentModule.getProductDocument(idProductDocument);
        return _productDocument;
    }

    public List<ProductDocument> getProductDocuments() {
    	ProductDocumentModule _productDocumentModule = (ProductDocumentModule) sm.getService(ProductDocumentModule.class);
        List<ProductDocument> _productDocuments = _productDocumentModule.getProductDocuments();
        return _productDocuments;
    }

    public List<ProductDocument> getProductDocuments(Product product) {
    	ProductDocumentModule _productDocumentModule = (ProductDocumentModule) sm.getService(ProductDocumentModule.class);
        List<ProductDocument> _productDocuments = _productDocumentModule.getProductDocuments(product);
        return _productDocuments;
    }

    public List<ProductDocument> getProductDocuments(Product product, ProductDocumentType productDocumentType) {
    	ProductDocumentModule _productDocumentModule = (ProductDocumentModule) sm.getService(ProductDocumentModule.class);
        List<ProductDocument> _productDocuments = _productDocumentModule.getProductDocuments(product, productDocumentType);
        return _productDocuments;
    }

    public ProductDocumentType addProductDocumentType(int state, String name) {
    	ProductDocumentModule _productDocumentModule = (ProductDocumentModule) sm.getService(ProductDocumentModule.class);
        ProductDocumentType _productDocumentType = _productDocumentModule.addProductDocumentType(state, name);
        return _productDocumentType;
    }

    public ProductDocumentType updateProductDocumentType(ProductDocumentType productDocumentType) {
    	ProductDocumentModule _productDocumentModule = (ProductDocumentModule) sm.getService(ProductDocumentModule.class);
        ProductDocumentType _productDocumentType = _productDocumentModule.updateProductDocumentType(productDocumentType);
        return _productDocumentType;
    }

    public ProductDocumentType getProductDocumentType(String name) {
    	ProductDocumentModule _productDocumentModule = (ProductDocumentModule) sm.getService(ProductDocumentModule.class);
        ProductDocumentType _productDocumentType = _productDocumentModule.getProductDocumentType(name);
        return _productDocumentType;
    }

    public ProductDocumentType getProductDocumentType(int idProductDocumentType) {
    	ProductDocumentModule _productDocumentModule = (ProductDocumentModule) sm.getService(ProductDocumentModule.class);
        ProductDocumentType _productDocumentType = _productDocumentModule.getProductDocumentType(idProductDocumentType);
        return _productDocumentType;
    }

    public List<ProductDocumentType> getProductDocumentTypes() {
    	ProductDocumentModule _productDocumentModule = (ProductDocumentModule) sm.getService(ProductDocumentModule.class);
        List<ProductDocumentType> _productDocumentTypes = _productDocumentModule.getProductDocumentTypes();
        return _productDocumentTypes;
    }

    public List<ProductDocumentType> getActiveProductDocumentTypes() {
    	ProductDocumentModule _productDocumentModule = (ProductDocumentModule) sm.getService(ProductDocumentModule.class);
        List<ProductDocumentType> _productDocumentTypes = _productDocumentModule.getActiveProductDocumentTypes();
        return _productDocumentTypes;
    }

    public List<ProductDocument> getFEDDProductDocuments(Product product, ProductDocumentType productDocumentType) {
    	ProductDocumentModule _productDocumentModule = (ProductDocumentModule) sm.getService(ProductDocumentModule.class);
        List<ProductDocument> _productDocuments = _productDocumentModule.getFEDDProductDocuments(product, productDocumentType);
        return _productDocuments;
    }

    public List<AfterSaleCom> getLazyRecapCom(int limit, int maxperpage) {
    	AfterSaleModule _aftersalemodule = (AfterSaleModule) sm.getService(AfterSaleModule.class);
        List<AfterSaleCom> _aftersalecom = _aftersalemodule.getLazyRecapCom(limit, maxperpage);
        return _aftersalecom;
    }

    public int countRecapCom() {
    	AfterSaleModule _aftersalemodule = (AfterSaleModule) sm.getService(AfterSaleModule.class);
        int count = _aftersalemodule.countRecapCom();
        return count;
    }

    public List<AfterSaleCom> getRepairDatetoDate(Date debut, Date fin) {
    	AfterSaleModule _aftersalemodule = (AfterSaleModule) sm.getService(AfterSaleModule.class);
        List<AfterSaleCom> _aftersalecom = _aftersalemodule.getRepairDatetoDate(debut, fin);
        return _aftersalecom;
    }

    public List<FollowingFormModel> getAllFollowingFormModel() {
    	FollowingFormModule _followingFormModule = (FollowingFormModule) sm.getService(FollowingFormModule.class);
        List<FollowingFormModel> _followingFormModel = _followingFormModule.getAllFollowingFormModel();
        return _followingFormModel;
    }

    public void addFollowingFormModel(FollowingFormModel _followingFormModel) {
    	FollowingFormModule _followingFormModule = (FollowingFormModule) sm.getService(FollowingFormModule.class);
        _followingFormModule.addFollowingFormModel(_followingFormModel);
    }

    public void updateFollowingFormModel(FollowingFormModel _followingFormModel) {
    	FollowingFormModule _followingFormModule = (FollowingFormModule) sm.getService(FollowingFormModule.class);
        _followingFormModule.updateFollowingFormModel(_followingFormModel);
    }

    public void deleteFollowingFormModel(int id) {
    	FollowingFormModule _followingFormModule = (FollowingFormModule) sm.getService(FollowingFormModule.class);
        _followingFormModule.deleteFollowingFormModel(id);
    }

    public void addProductConfModels(ProductConfModel _productConfModel) {
    	ProductConfModelModule _productConfModelModule = (ProductConfModelModule) sm.getService(ProductConfModelModule.class);
        _productConfModelModule.addProductConfModels(_productConfModel);
    }

    public void delProductConfModels(int id) {
    	ProductConfModelModule _productConfModelModule = (ProductConfModelModule) sm.getService(ProductConfModelModule.class);
        _productConfModelModule.delProductConfModels(id);
    }

    /*
     * Retourne une liste de product avec la configuration sur laquel ils sont montes
     */
    public List<Product> getProductWithMother(int startingAt, int maxPerPage, Map<String, String> filters) {
    	ProductModule _productModule = (ProductModule) sm.getService(ProductModule.class);
        List<Product> _product = _productModule.getProductWithMother(startingAt, maxPerPage, filters);
        return _product;

    }

    public void updateProductConfModel(ProductConfModel _productConfModel) {
    	ProductConfModelModule _productConfModelModule = (ProductConfModelModule) sm.getService(ProductConfModelModule.class);
    	_productConfModelModule.updateProductConfModels(_productConfModel);
    }

    /*
     * Permet le lazing sur les productConf
     */
    public List<ProductConf> getProductConfLazy(Map<String, String> filters, int limit, int maxperpage) {
    	ProductConfModule _productConfModule = (ProductConfModule) sm.getService(ProductConfModule.class);
        List<ProductConf> _productConf = _productConfModule.getProductConfLazy(filters, limit, maxperpage);
        return _productConf;
    }

    /*
     * Permet le lazing sur les productConf
     */
    public int countProductConf(Map<String, String> filters) {
    	ProductConfModule _productConfModule = (ProductConfModule) sm.getService(ProductConfModule.class);
        int i = _productConfModule.countProductConf(filters);
        return i;
    }

    /*
     * Permet le lazing sur les productConfModel
     */
    public List<ProductConfModel> getProductConfModelLazy(Map<String, String> filters, int limit, int maxperpage) {
    	ProductConfModelModule _productConfModelModule = (ProductConfModelModule) sm.getService(ProductConfModelModule.class);
        List<ProductConfModel> _productConf = _productConfModelModule.getProductConfModelLazy(filters, limit, maxperpage);
        return _productConf;
    }

    /*
     * Permet le lazing sur les productConfModel
     */
    public Product getProductWithProductConfRef(String reference) {
    	ProductModule _productModule = (ProductModule) sm.getService(ProductModule.class);
        Product _product = _productModule.getProductWithProductConfRef(reference);
        return _product;
    }

    /*
     * Permet le lazing sur les productConfModel
     */
    public int countProductConfModel(Map<String, String> filters) {
    	ProductConfModelModule _productConfModelModule = (ProductConfModelModule) sm.getService(ProductConfModelModule.class);
        int i = _productConfModelModule.countProductConfModel(filters);
        return i;
    }

}
