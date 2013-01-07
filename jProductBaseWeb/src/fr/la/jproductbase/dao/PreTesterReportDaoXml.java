package fr.la.jproductbase.dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import org.jdom.Attribute;
import org.jdom.DocType;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

public class PreTesterReportDaoXml {
	private static Element racine = new Element("PreTesterReport");
	private static DocType docType = new DocType("PreTesterReport",
			"testReport.dtd");
	private static Document document = new Document(
			PreTesterReportDaoXml.racine, docType);

	private String xmlFileName;

	public PreTesterReportDaoXml(String preTesterReportFileName) {
		this.xmlFileName = preTesterReportFileName;
	}

	/**
	 * Enregistre le pr&eacute;-rapport dans un fichier.
	 * 
	 * @param productConfReference
	 *            R&eacute;f&eacute;rence de la configuration produit.
	 * @param serialNumber
	 *            Num&eacute;ro de s&eacute;rie du produit.
	 * @param datecode
	 *            Datecode du produit.
	 * @param testTypeLabview
	 *            Code Labview du type de test.
	 * @param inFlow
	 *            Le test doit &ecirc;tre compt&eacute; dans le flux.
	 * @param operatorCode
	 *            Code op&eacute;rateur.
	 * @param append
	 *            Met le ficiher &agrave; jour.
	 * 
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws JDOMException
	 */
	public void writePreTesterReport(String productConfReference,
			String serialNumber, String datecode, String testTypeLabview,
			Boolean inFlow, String operatorCode, boolean append)
			throws FileNotFoundException, IOException, JDOMException {
		File _xmlFile = new File(this.xmlFileName);
		if ((true == append) && (true == _xmlFile.exists())) {
			PreTesterReportDaoXml.readxml(this.xmlFileName);
		} else {
			// Clean xml file
			List<?> _elements = PreTesterReportDaoXml.racine.getChildren();
			_elements.clear();
		}

		// Product test
		Element _productTestElt = new Element("productTest");
		PreTesterReportDaoXml.racine.addContent(_productTestElt);

		// Product
		Element _productElt = new Element("product");
		_productTestElt.addContent(_productElt);
		Element _serialNumberElt = new Element("serialNumber");
		_productElt.addContent(_serialNumberElt);
		_serialNumberElt.setText(serialNumber);
		Element _datecodeElt = new Element("datecode");
		_productElt.addContent(_datecodeElt);
		_datecodeElt.setText(datecode);
		// ProductConf
		Element _productConfElt = new Element("configuration");
		_productElt.addContent(_productConfElt);
		Element _productConfRefElt = new Element("reference");
		_productConfElt.addContent(_productConfRefElt);
		_productConfRefElt.setText(productConfReference);

		// TestType
		Element _testTypeElt = new Element("testType");
		_productTestElt.addContent(_testTypeElt);
		Attribute _testTypeAttr = new Attribute("flow", inFlow.toString());
		_testTypeElt.setAttribute(_testTypeAttr);
		Element _labviewElt = new Element("labview");
		_testTypeElt.addContent(_labviewElt);
		_labviewElt.setText(testTypeLabview);

		// Operator
		Element _operatorElt = new Element("operator");
		_productTestElt.addContent(_operatorElt);
		Element _codeElt = new Element("code");
		_operatorElt.addContent(_codeElt);
		_codeElt.setText(operatorCode);

		// Enregistre le fichier xml
		PreTesterReportDaoXml.save(this.xmlFileName);
	}

	/*
	 * Enregistrement du fichier.
	 */
	private static void save(String fichier) throws FileNotFoundException,
			IOException {
		XMLOutputter _output = new XMLOutputter(Format.getPrettyFormat());
		FileOutputStream _fileOutputStream = new FileOutputStream(
				fichier);
		_output.output(PreTesterReportDaoXml.document, _fileOutputStream);
		
		_fileOutputStream.flush();
		_fileOutputStream.close();
	}

	/**
	 * Met &agrave; jour le r&eacute;sultat du test, sa confirmation et le
	 * commentaire client dans le pr&eacute;-rapport.
	 * 
	 * @param result
	 *            R&eacute;sultat du test.
	 * @param resultConfirmation
	 *            Confirmation du r&eacute;sultat.
	 * 
	 * @throws JDOMException
	 * @throws IOException
	 */
	public void writePreTesterReportResult(String result,
			Boolean resultConfirmation, StringBuffer customerComment)
			throws JDOMException, IOException {
		PreTesterReportDaoXml.readxml(this.xmlFileName);

		// Add new "resultat"
		Element _resultElt = new Element("resultat");
		PreTesterReportDaoXml.racine.addContent(_resultElt);
		Element _testResultElt = new Element("test");
		_resultElt.addContent(_testResultElt);
		_testResultElt.setText(result);
		Element _confirmResultElt = new Element("confirm");
		_resultElt.addContent(_confirmResultElt);
		_confirmResultElt.setText(resultConfirmation.toString());
		Element _customerCommenttElt = new Element("customerComment");
		_resultElt.addContent(_customerCommenttElt);
		_customerCommenttElt.setText(customerComment.toString());

		PreTesterReportDaoXml.save(this.xmlFileName);
	}

	/*
	 * Lit le fichier xml.
	 */
	private static void readxml(String file) throws JDOMException, IOException {
		SAXBuilder _saxBuilder = new SAXBuilder();
		_saxBuilder.setValidation(true);
		PreTesterReportDaoXml.document = _saxBuilder.build(new File(file));
		PreTesterReportDaoXml.racine = document.getRootElement();
	}
}
