package fr.la.jproductbase.dao;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

import fr.la.jproductbase.metier.JProductBaseParameters;

public class ProductDaoXml {
	private Element racine = new Element("productIntegration");
	private Document document = new Document(this.racine);

	public void addProduct(String productConfReference,
			String productConfMajorIndex, String productConfMinorIndex,
			String serialNumber, String datecode, String[][] productComponents,
			String[][] productSoftwares) {
		// Product
		Element _productElt = new Element("product");
		this.racine.addContent(_productElt);
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
		Element _productConfMajorElt = new Element("major");
		_productConfElt.addContent(_productConfMajorElt);
		_productConfMajorElt.setText(productConfMajorIndex);
		Element _productConfMinorElt = new Element("minor");
		_productConfElt.addContent(_productConfMinorElt);
		_productConfMinorElt.setText(productConfMinorIndex);


		// Components
		int _nbComponentsRow = productComponents[0].length;
		String _productComponentConfReference;
		String _productComponentConfMajorIndex;
		String _productComponentConfMinorIndex;
		String _productComponentDatecode;
		String _productComponentSerialNumber;
		String _productComponentProvider;
		for (int _componentRow = 0; _componentRow < _nbComponentsRow; _componentRow++) {
			_productComponentConfReference = productComponents[1][_componentRow];
			_productComponentConfMajorIndex = productComponents[2][_componentRow];
			_productComponentConfMinorIndex = productComponents[3][_componentRow];
			_productComponentDatecode = productComponents[5][_componentRow];
			_productComponentSerialNumber = productComponents[6][_componentRow];
			_productComponentProvider = productComponents[7][_componentRow];

			Element _productComponentElt = new Element("component");
			_productElt.addContent(_productComponentElt);

			// Product
			Element _productComponentSerialNumberElt = new Element(
					"serialNumber");
			_productComponentElt.addContent(_productComponentSerialNumberElt);
			_productComponentSerialNumberElt
					.setText(_productComponentSerialNumber);
			Element _productComponentDatecodeElt = new Element("datecode");
			_productComponentElt.addContent(_productComponentDatecodeElt);
			_productComponentDatecodeElt.setText(_productComponentDatecode);
			Element _productComponentProviderElt = new Element("provider");
			_productComponentElt.addContent(_productComponentProviderElt);
			_productComponentProviderElt.setText(_productComponentProvider);

			// ProductConf
			Element _productComponentConfElt = new Element("configuration");
			_productComponentElt.addContent(_productComponentConfElt);
			Element _productComponentConfRefElt = new Element("reference");
			_productComponentConfElt.addContent(_productComponentConfRefElt);
			_productComponentConfRefElt.setText(_productComponentConfReference);
			Element _productComponentConfMajorElt = new Element("major");
			_productComponentConfElt.addContent(_productComponentConfMajorElt);
			_productComponentConfMajorElt
					.setText(_productComponentConfMajorIndex);
			Element _productComponentConfMinorElt = new Element("minor");
			_productComponentConfElt.addContent(_productComponentConfMinorElt);
			_productComponentConfMinorElt
					.setText(_productComponentConfMinorIndex);
		}

		// Softwares
		int _nbSoftwaresRow = productSoftwares[0].length;
		String _productSoftwareName;
		String _productSoftwareVersion;
		for (int _softwareRow = 0; _softwareRow < _nbSoftwaresRow; _softwareRow++) {
			_productSoftwareName = productSoftwares[1][_softwareRow];
			_productSoftwareVersion = productSoftwares[2][_softwareRow];

			Element _productSoftwareElt = new Element("software");
			_productElt.addContent(_productSoftwareElt);

			// Software
			Element _productSoftwareNameElt = new Element(
					"name");
			_productSoftwareElt.addContent(_productSoftwareNameElt);
			_productSoftwareNameElt
					.setText(_productSoftwareName);
			Element _productSoftwareVersionElt = new Element("version");
			_productSoftwareElt.addContent(_productSoftwareVersionElt);
			_productSoftwareVersionElt.setText(_productSoftwareVersion);
		}

		// Répertoire du fichier xml
		JProductBaseParameters _jProductBaseParameters = JProductBaseParameters.getInstance();
		String _fileDirectory = _jProductBaseParameters.getDataFilesDirectory();
		// Nom du fichier xml
		Date _now = new Date();
		SimpleDateFormat _nowFormatted = new SimpleDateFormat("yyyyMMddHHmmss");
		String _fileName = _fileDirectory + File.separator
				+ productConfReference + "_" + datecode + "_" + serialNumber
				+ "_" + _nowFormatted.format(_now) + ".xml";

		save(_fileName);
	}

	/*
	 * Enregistrement de l'arborescence XML dans un fichier.
	 * 
	 * @param fileName Chemin et nom du fichier.
	 */
	private void save(String fileName) {
		XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
		try {
			sortie.output(this.document, new FileOutputStream(fileName));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
