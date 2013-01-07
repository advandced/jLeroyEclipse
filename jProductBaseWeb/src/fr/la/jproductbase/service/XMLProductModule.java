package fr.la.jproductbase.service;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import javax.naming.NamingException;
import javax.swing.JOptionPane;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import fr.la.configfilereader.ConfigFileReaderException;
import fr.la.importdyntesteurdata.ImportDynTesteurData;
import fr.la.jproductbase.dao.ProductDaoXml;
import fr.la.jproductbase.dao.TesterReportDaoException;
import fr.la.jproductbase.metier.FilesTools;
import fr.la.jproductbase.metier.JProductBaseException;
import fr.la.jproductbase.metier.JProductBaseParameters;
import fr.la.jproductbase.metier.LabviewTesterReport;
import fr.la.jproductbase.metier.LabviewTesterReportException;
import fr.la.jproductbase.metier.PreTesterReport;
import fr.la.jproductbase.metier.Product;
import fr.la.jproductbase.metier.ProductIntegrationHandler;
import fr.la.jproductbase.metier.ProductTest;
import fr.la.jproductbase.metier.TesterReportException;
import fr.la.jproductbase.metier.XmlFilenameFilter;
import fr.la.jproductbase.metier.ZipFilenameFilter;

public class XMLProductModule {
	protected void setProductXml(String productConfReference,
			String productConfMajorIndex, String productConfMinorIndex,
			String serialNumber, String datecode, String[][] productComponents,
			String[][] productSoftwares) throws ConfigFileReaderException,
			IOException {
		ProductDaoXml _productDaoXml = new ProductDaoXml();
		_productDaoXml.addProduct(productConfReference, productConfMajorIndex,
				productConfMinorIndex, serialNumber, datecode,
				productComponents, productSoftwares);
	}

	protected void insertClientFiles() throws JDOMException, IOException,
			SAXException, ParserConfigurationException,
			ConfigFileReaderException, JProductBaseException {
		this.insertDataFiles();
		this.insertReportFiles();
	}

	/*
	 * Intégration des fichiers de données en attente. Liste des fichiers XML du
	 * répertoire data.
	 */
	private void insertDataFiles() throws JDOMException, IOException,
			SAXException, ParserConfigurationException,
			ConfigFileReaderException, JProductBaseException {
		JProductBaseParameters _jProductBaseParameters = JProductBaseParameters
				.getInstance();

		// Répertoire contenant les fichiers à intégrer
		String _dataFilesServerDirectory = _jProductBaseParameters
				.getDataFilesServerDirectory();

		if (null != _dataFilesServerDirectory) {
			File _file = new File(_dataFilesServerDirectory);
			FilenameFilter _dataFileFilter = new XmlFilenameFilter();
			String[] _dataFilesName = _file.list(_dataFileFilter);

			for (String _dataFileName : _dataFilesName) {
				String _fileName = _dataFilesServerDirectory + File.separator
						+ _dataFileName;
				File _dataFile = new File(_fileName);
				this.insertDataFile(_dataFile);
			}
		} else {
			// TODO Exception ?
		}
	}

	/*
	 * Intégration des fichiers de données en attente. Vérification si le
	 * fichier XML à déjà été archivé et archivage après intégration.
	 * 
	 * @param dataFile Fichier à intégrer.
	 */
	private void insertDataFile(File dataFile)
			throws ConfigFileReaderException, IOException {
		JProductBaseParameters _jProductBaseParameters = JProductBaseParameters
				.getInstance();

		System.out.println("Intégration du fichier : " + dataFile);

		// Error file name
		String _dataFileErrName = _jProductBaseParameters
				.getDataFilesServerDirectory()
				+ File.separator
				+ "errors"
				+ File.separator + dataFile.getName();

		// Archive file name
		String _dataFileArchiveName = _jProductBaseParameters
				.getDataFilesServerDirectoryArchive()
				+ File.separator
				+ dataFile.getName();
		File _dataFileArchive = new File(_dataFileArchiveName);
		if (!_dataFileArchive.exists()) {
			try {
				// Intégration
				this.insertXmlDataFile(dataFile);

				// Archivage
				boolean _archive = this.renameFileTo(dataFile,
						_dataFileArchiveName);
				if (true == _archive) {
					// Archivage OK
					System.out.println("Archivage : OK");
				} else {
					// Archivage NOK
					this.renameFileTo(dataFile, _dataFileErrName);
				}
			} catch (Exception e) {
				e.printStackTrace();

				this.renameFileTo(dataFile, _dataFileErrName);
			}
		} else {
			// File already imported
			this.renameFileTo(dataFile, _dataFileErrName);
		}
	}

	/*
	 * Renomme un fichier.
	 * 
	 * @param dataFile Fichier à renommer.
	 * 
	 * @param dataFileName Nouveau nom du fichier.
	 */
	private boolean renameFileTo(File dataFile, String dataFileName) {
		boolean _result = false;

		File _newDataFile = new File(dataFileName);

		boolean _archive = dataFile.renameTo(_newDataFile);
		if (true == _archive) {
			// Renommage OK
			_result = true;
		} else {
			// Renommage NOK
			_result = false;

			System.out.println("Echec de déplacement du fichier " + dataFile
					+ " dans " + _newDataFile);
		}

		return _result;
	}

	/*
	 * Intégration des fichiers de données en attente. Vérificaton que la racine
	 * du fichier de données xml est "productIntegration".
	 * 
	 * @param dataFile Fichier à intégrer.
	 */
	private void insertXmlDataFile(File dataFile) throws JDOMException,
			IOException, SAXException, ParserConfigurationException,
			XMLProductModuleException {
		// Open xml file
		SAXBuilder saxb = new SAXBuilder();
		Document document = saxb.build(dataFile);

		// Read xml file
		Element racine = document.getRootElement();

		if (racine.getQualifiedName().equals("productIntegration")) {
			// xml généré à l'étape d'intégration produit
			this.insertProductIntegrationXmlFile(dataFile);
		} else {
			throw new XMLProductModuleException(
					"La racine du fichier de données XML n'est pas 'productIntegration'.");
		}
	}

	/*
	 * Intégration des fichiers de données en attente. Intégration du fichier de
	 * données au format xml.
	 */
	private void insertProductIntegrationXmlFile(File dataFile)
			throws SAXException, IOException, ParserConfigurationException {
		SAXParserFactory _saxFactory = SAXParserFactory.newInstance();
		SAXParser _saxParser = _saxFactory.newSAXParser();

		DefaultHandler _handler = new ProductIntegrationHandler();
		_saxParser.parse(dataFile, _handler);
	}

	/*
	 * Intégration des fichiers de données en attente. Liste des fichiers ZIP du
	 * répertoire report.
	 */
	private void insertReportFiles() throws ConfigFileReaderException,
			IOException {
		JProductBaseParameters _jProductBaseParameters = JProductBaseParameters
				.getInstance();

		// Répertoire contenant les fichiers à intégrer
		String _reportFilesServerDirectory = _jProductBaseParameters
				.getReportFilesServerDirectory();

		if (null != _reportFilesServerDirectory) {
			File _file = new File(_reportFilesServerDirectory);
			FilenameFilter _reportFileFilter = new ZipFilenameFilter();
			String[] _reportFilesName = _file.list(_reportFileFilter);

			// Intégration des fichiers
			for (String _reportFileName : _reportFilesName) {
				String _fileName = _reportFilesServerDirectory + File.separator
						+ _reportFileName;
				File _reportFile = new File(_fileName);

				// Intégration du fichier
				this.insertReportFile(_reportFile);
			}
		} else {
			// TODO Exception ?
		}
	}

	/*
	 * Intégration des fichiers de données en attente. Vérification si le
	 * fichier ZIP à déjà été archivé et archivage après intégration.
	 * 
	 * @param reportFile Rapport de testeur à intégrer.
	 */
	private void insertReportFile(File reportFile)
			throws ConfigFileReaderException, IOException {
		JProductBaseParameters _jProductBaseParameters = JProductBaseParameters
				.getInstance();

		System.out.println("Intégration du fichier : " + reportFile);

		// Error file name
		String _reportFileErrName = _jProductBaseParameters
				.getReportFilesServerDirectory()
				+ File.separator
				+ "errors"
				+ File.separator + reportFile.getName();

		// Archive file name
		String _reportFileArchiveName = _jProductBaseParameters
				.getReportFilesServerDirectoryArchive()
				+ File.separator
				+ reportFile.getName();
		File _reportFileArchive = new File(_reportFileArchiveName);
		if (!_reportFileArchive.exists()) {
			// Intégration
			try {
				this.insertZipDataFile(reportFile);

				// Archivage
				boolean _archive = reportFile.renameTo(_reportFileArchive);
				if (true == _archive) {
					// Archivage OK
					System.out.println("Archivage : OK.");
				} else {
					// Archivage NOK
					this.renameFileTo(reportFile, _reportFileErrName);
				}
			} catch (Exception e) {
				e.printStackTrace();

				this.renameFileTo(reportFile, _reportFileErrName);
			}
		} else {
			// File already imported
			this.renameFileTo(reportFile, _reportFileErrName);
		}
	}

	/*
	 * Intégration des fichiers de données en attente. Lecture des fichiers
	 * contenu dans l'archive. Détermine si le type du rapport en fonction de
	 * l'extension du fichier contenu dans l'archive.
	 */
	private void insertZipDataFile(File reportFile)
			throws ConfigFileReaderException, IOException, JDOMException,
			SQLException, TesterReportException, ParserConfigurationException,
			SAXException, XMLProductModuleException, ParseException,
			TesterReportDaoException, FailureModuleException,
			TesterModuleException, JProductBaseException, NamingException {
		// Read config file parameters
		JProductBaseParameters _jProductBaseParameters = JProductBaseParameters
				.getInstance();
		String _preTesterReportXmlFileName = _jProductBaseParameters
				.getPreTesterReportXmlFileName();

		// Open zip file
		@SuppressWarnings("resource")
		ZipFile _zipFile = new ZipFile(reportFile);
		Enumeration<? extends ZipEntry> _zipEntries = _zipFile.entries();
		PreTesterReport _preTesterReport = null;
		String _zipEntryExtension = "";
		ZipEntry _zipEntryTesterReport = null;
		while (((null == _preTesterReport) || (null == _zipEntryTesterReport))
				&& (_zipEntries.hasMoreElements())) {
			ZipEntry _zipEntry = (ZipEntry) _zipEntries.nextElement();
			String _zipEntryName = _zipEntry.getName().trim();
			if (_zipEntryName.equals(_preTesterReportXmlFileName)) {
				// Pré-rapport
				InputStream _preTesterReportInputStream = _zipFile
						.getInputStream(_zipEntry);
				_preTesterReport = PreTesterReport
						.getPreTesterReport(_preTesterReportInputStream);
			} else {
				// Rapport testeur
				int _lastPoint = _zipEntryName.lastIndexOf(".");
				if (-1 != _lastPoint) {
					_zipEntryExtension = _zipEntryName.substring(_lastPoint)
							.toLowerCase();
					_zipEntryTesterReport = _zipEntry;
				} else {
					// No file extension
				}
			}
		}

		if (null != _preTesterReport) {
			if (null != _zipEntryTesterReport) {
				if (_zipEntryExtension.equals(".xml")) {
					// Read XML labview report file
					InputStream _zipInputStream = _zipFile
							.getInputStream(_zipEntryTesterReport);
					LabviewTesterReport _labviewTesterReport = LabviewTesterReport
							.getLabviewXmlTesterReport(_zipInputStream);

					// Save testerReport
					ServiceInterface _serviceInterface = new ServiceInterface();
					_serviceInterface.saveTesterReport(_preTesterReport,
							_labviewTesterReport);
				} else {
					if (_zipEntryExtension.equals(".zip")) {
						// ZIP file
						this.convertTestDynTesterReport(_zipFile.getName(),
								_preTesterReport);
					} else {
						throw new XMLProductModuleException(
								"Extension de rapport inconnue.");
					}
				}
			} else {
				throw new XMLProductModuleException(
						"L'archive ne contient pas de rapport.");
			}
		} else {
			throw new XMLProductModuleException(
					"L'archive ne contient pas de pré-rapport.");
		}
	}

	/*
	 * Conversion d'un rapport testeur dynamique en rapport standard.
	 */
	private void convertTestDynTesterReport(String zipFileName,
			PreTesterReport preTesterReport) throws ConfigFileReaderException,
			IOException, ParserConfigurationException, SAXException {
		// Read directory to save standard testerReport in config file
		// parameters
		JProductBaseParameters _jProductBaseParameters = JProductBaseParameters
				.getInstance();
		String _importDynTesterDataDirectory = _jProductBaseParameters
				.getImportDynTesterDataDirectory();

		// Extrait l'archive dans "importDynTesterDataDirectory"
		this.extractImportDynTesterData(zipFileName,
				_importDynTesterDataDirectory);

		// Exécute le programme "importDynTesterData.jar" pour créer des
		// rapports au format XML standard.
		this.importDynTesteurData();

		// Reconstruction des archives avec les fichiers au format XML standard
		// et le fichier preTesterReport.xml correspondant.
		this.buildTesterReportArchives(_importDynTesterDataDirectory,
				preTesterReport);

		// TODO : Supprime les fichiers du dossier
		// "importDynTesterDataDirectory"
	}

	/*
	 * Extrait le rapport de testeur dynamique dans le répertoire où il sera
	 * traité.
	 * 
	 * @param zipFileName Nom du rapport du testeur dynamique.
	 * 
	 * @param importDynTesterDataDirectory Répertoire de destination.
	 */
	private void extractImportDynTesterData(String zipFileName,
			String destDirectory) throws ConfigFileReaderException, IOException {
		// Décompression du zip.
		final int _BUFFERSIZE = 2048;
		byte _data[] = new byte[_BUFFERSIZE];
		BufferedOutputStream _outputStream = null;
		FileInputStream _fileInputStream = new FileInputStream(zipFileName);
		BufferedInputStream _buffInputStream = new BufferedInputStream(
				_fileInputStream);
		ZipInputStream _zipInputStream = new ZipInputStream(_buffInputStream);
		ZipEntry _zipEntry;
		while (null != (_zipEntry = _zipInputStream.getNextEntry())) {
			FileOutputStream _fileOutputStream = new FileOutputStream(
					destDirectory + File.separator + _zipEntry.getName());
			_outputStream = new BufferedOutputStream(_fileOutputStream,
					_BUFFERSIZE);
			int _count;
			while (-1 != (_count = _zipInputStream.read(_data, 0, _BUFFERSIZE))) {
				_outputStream.write(_data, 0, _count);
			}
			_outputStream.flush();
			_outputStream.close();
		}
		_zipInputStream.close();
	}

	/*
	 * Exécute le programme "importDynTesterData.jar"
	 */
	private void importDynTesteurData() throws ConfigFileReaderException,
			IOException {
		ImportDynTesteurData _importDynTesteurData = new ImportDynTesteurData();
		_importDynTesteurData.exec();
	}

	/*
	 * Reconstruction des archives avec les fichiers au format XML standard et
	 * le fichier preTesterReport.xml correspondant.
	 * 
	 * @param dataDirectory Répertoire contenant les fichiers à traiter.
	 * 
	 * @param preTesterReport pré-rapport.
	 */
	private void buildTesterReportArchives(String dataDirectory,
			PreTesterReport preTesterReport) throws ConfigFileReaderException,
			IOException, ParserConfigurationException, SAXException {
		// PreTesterReport XML file name
		JProductBaseParameters _jProductBaseParameters = JProductBaseParameters
				.getInstance();
		String _preTesterReportXmlFileName = _jProductBaseParameters
				.getPreTesterReportXmlFileName();

		// Directory XML files
		File _file = new File(dataDirectory);
		FilenameFilter _dataFileFilter = new XmlFilenameFilter();
		String[] _dataFilesName = _file.list(_dataFileFilter);
		for (String _dataFileName : _dataFilesName) {
			if (_dataFileName.equals(_preTesterReportXmlFileName)) {
				// PreTesterReport => don't process
			} else {
				// TesterReport
				String _testerReportXmlFileName = dataDirectory
						+ File.separator + _dataFileName;
				try {
					this.buildTesterReportArchive(preTesterReport,
							_testerReportXmlFileName);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	/*
	 * Reconstruction une archive avec le fichier au format XML standard et le
	 * fichier preTesterReport.xml correspondant.
	 * 
	 * @param preTesterReport Fichier preTesterReport.xml avec tous les
	 * produits.
	 * 
	 * @param testerReportXmlFileName Fichier XML du rapport testeur à archiver.
	 */
	private void buildTesterReportArchive(PreTesterReport preTesterReport,
			String testerReportXmlFileName)
			throws ParserConfigurationException, SAXException, IOException,
			ConfigFileReaderException, JDOMException {
		// Read standard testerReport XML file
		LabviewTesterReport _labviewTesterReport = LabviewTesterReport
				.getLabviewTesterReport(testerReportXmlFileName);

		// Product serial number in preTesterReport
		ProductTest _selectedProductTest = null;
		for (ProductTest _productTest : preTesterReport.getProductTests()) {
			if (_labviewTesterReport.getSerialNumber().equals(
					_productTest.getProductSerialNumber())) {
				_selectedProductTest = _productTest;
			}
		}
		if (null != _selectedProductTest) {
			// Build preTesterReport

			// Read preTesterReport XML file name in config file parameters
			JProductBaseParameters _jProductBaseParameters = JProductBaseParameters
					.getInstance();
			String _preTesterReportXmlFileName = _jProductBaseParameters
					.getPreTesterReportXmlFileName();

			XMLTesterModule _xmlTesterModule = new XMLTesterModule();

			// Save preTesterReport productTest data.
			_xmlTesterModule.setPreTesterReportXml(_preTesterReportXmlFileName,
					_selectedProductTest.getProductConfReference(),
					_selectedProductTest.getProductSerialNumber(),
					_selectedProductTest.getProductDatecode(),
					_selectedProductTest.getLabviewTestType(),
					_selectedProductTest.isInFlow(),
					_selectedProductTest.getOperatorCode(), false);

			// Save test result and confirmation in preTesterReport file
			StringBuffer customerComment = new StringBuffer(
					preTesterReport.getCustomerComment());
			_xmlTesterModule.updatePreTesterReportXml(
					_preTesterReportXmlFileName,
					_labviewTesterReport.getResultat(), true, customerComment);

			// Prepare report file transfer
			this.reportTransfer(_preTesterReportXmlFileName,
					testerReportXmlFileName);
		} else {
			// TODO : Product not found => Exception
		}

		// Suppression du rapport de testeur.
		File _testerReportFile = new File(testerReportXmlFileName);
		_testerReportFile.deleteOnExit();
	}

	/*
	 * Cr&eacute;e un rapport de test qui contient le pr&eacute;-rapport et le
	 * rapport du testeur puis les transf&eacute;re dans le dossier
	 * d'int&eacute;gration.
	 * 
	 * @param preTesterReportXmlFileName Pr&eacute;-rapport.
	 * 
	 * @param testerReportXmlFileName Rapport du testeur.
	 * 
	 * @throws ConfigFileReaderException
	 * 
	 * @throws IOException
	 */
	private void reportTransfer(String preTesterReportXmlFileName,
			String testerReportXmlFileName) throws IOException,
			ConfigFileReaderException {
		File _testerReportFileArchive = this.buildTesterReportArchive(
				preTesterReportXmlFileName, testerReportXmlFileName);

		this.transferTesterReportArchive(_testerReportFileArchive);

		// Remove archive file
		_testerReportFileArchive.delete();

		// Remove preTesterReport file
		File _preTesterReportFile = new File(preTesterReportXmlFileName);
		boolean _isDeleted = _preTesterReportFile.delete();
		if (true == _isDeleted) {
			// Delete successful
		} else {
			// Delete error
		}
	}

	/*
	 * Compresse les pr&eacute;-rapport et rapport du testeur dans un fichier
	 * d'archive.
	 * 
	 * @param preTesterReportXmlFileName Chemin + nom du rapport du testeur.
	 * 
	 * @param testerReportXmlFileName Chemin + nom du fichier de
	 * pr&eacute;-rapport.
	 * 
	 * @return Fichier d'archive.
	 */
	private File buildTesterReportArchive(String preTesterReportXmlFileName,
			String testerReportXmlFileName) throws IOException {
		File _fileArchive = this.createFileArchive(testerReportXmlFileName);
		FileOutputStream _fileArchiveOutputStream = new FileOutputStream(
				_fileArchive);
		BufferedOutputStream _bufferOutputStream = new BufferedOutputStream(
				_fileArchiveOutputStream);

		ZipOutputStream _zipOutputStream = new ZipOutputStream(
				_bufferOutputStream);

		String[] _files = { preTesterReportXmlFileName, testerReportXmlFileName };
		final int _BUFFERSIZE = 2048;
		byte _data[] = new byte[_BUFFERSIZE];
		for (int _i = 0; _i < _files.length; _i++) {
			try {
				File _file = new File(_files[_i]);
				FileInputStream _fileInputStream = new FileInputStream(_file);

				BufferedInputStream _bufferInputStream = new BufferedInputStream(
						_fileInputStream, _BUFFERSIZE);

				ZipEntry _zipEntry = new ZipEntry(_file.getName());

				_zipOutputStream.putNextEntry(_zipEntry);

				int _count;
				while (-1 != (_count = _bufferInputStream.read(_data, 0,
						_BUFFERSIZE))) {
					_zipOutputStream.write(_data, 0, _count);
				}
				_zipOutputStream.flush();
				_zipOutputStream.closeEntry();

				_bufferInputStream.close();
			} catch (IOException e) {
				// Don't archive this file
			}
		}
		_zipOutputStream.flush();
		_zipOutputStream.close();

		return _fileArchive;
	}

	/*
	 * Cr&eacute;e l'archive sans extension afin de ne pas écraser un fichier
	 * ayant le m&ecirc;me nom (Exemple : les rapports de testeur dynamique ont
	 * une extension "zip").
	 * 
	 * @param testerReportXmlFileName Chemin + nom du fichier de
	 * pr&eacute;-rapport.
	 */
	private File createFileArchive(String testerReportXmlFileName) {
		String _fileArchiveName = "archive";
		int _lastPoint = testerReportXmlFileName.lastIndexOf(".");
		if (-1 != _lastPoint) {
			_fileArchiveName = testerReportXmlFileName.substring(0, _lastPoint);
		} else {
			_fileArchiveName = testerReportXmlFileName;
		}
		File _fileArchive = new File(_fileArchiveName);

		return _fileArchive;
	}

	/*
	 * Transf&eacute;re le rapport de test dans le dossier partag&eacute; avec
	 * le serveur.
	 * 
	 * @param productTestArchive Fichier d'archive.
	 */
	private void transferTesterReportArchive(File productTestArchive)
			throws IOException, ConfigFileReaderException {
		// Read config file parameters
		JProductBaseParameters _jProductBaseParameters = JProductBaseParameters
				.getInstance();

		// Répertoire de l'archive;
		String _reportFilesDirectory = _jProductBaseParameters
				.getReportFilesDirectory();

		// Nom de l'archive (nom du fichier d'archive + l'extension zip)
		String _reportFileName = _reportFilesDirectory + File.separator
				+ productTestArchive.getName() + ".zip";
		File _reportFile = new File(_reportFileName);

		if (!productTestArchive.getAbsolutePath().equals(
				_reportFile.getAbsolutePath())) {
			// Copy file (overwrite if file already exist)
			FilesTools.copyFile(productTestArchive, _reportFile);
		} else {
			// Source and destination are the same.
		}
	}

	protected void integrationTransfer(String testerReportFileName,
			LabviewTesterReport labviewTesterReport, boolean confirmTestResult,
			StringBuffer customerComment) throws ParserConfigurationException,
			SAXException, IOException, ConfigFileReaderException,
			JDOMException, LabviewTesterReportException, SQLException,
			JProductBaseException {
		// Read config file parameters
		JProductBaseParameters _jProductBaseParameters = JProductBaseParameters
				.getInstance();
		// Pre-testerReport file
		String _preTesterReportXmlFileName = _jProductBaseParameters
				.getPreTesterReportXmlFileName();
		if (null == _preTesterReportXmlFileName) {
			throw new JProductBaseException(
					"Fichier de pré-rapport non configuré.");
		}

		// Save test result and confirmation in preTesterReport file
		XMLTesterModule _xmlTesterModule = new XMLTesterModule();
		_xmlTesterModule.updatePreTesterReportXml(_preTesterReportXmlFileName,
				labviewTesterReport.getResultat(), confirmTestResult,
				customerComment);

		// Read preTesterReport after file transfer (for dispensation alert).
		InputStream _preTesterReportInputStream = new FileInputStream(
				_preTesterReportXmlFileName);
		PreTesterReport _preTesterReport = PreTesterReport
				.getPreTesterReport(_preTesterReportInputStream);

		// Prepare report file transfer
		this.reportTransfer(_preTesterReportXmlFileName, testerReportFileName);

		// Dispensation alert
		this.dispensationAlert(_preTesterReport);
	}

	private void dispensationAlert(PreTesterReport preTesterReport)
			throws ConfigFileReaderException, IOException, SQLException,
			JProductBaseException {
		// Result "Passed"
		if (preTesterReport.getResult().equals("Passed")) {
			// Products
			ServiceInterface _serviceInterface = new ServiceInterface();

			List<ProductTest> _productTests = preTesterReport.getProductTests();
			for (ProductTest _productTest : _productTests) {
				Product _product = _serviceInterface.retreiveProduct(
						_productTest.getProductConfReference(),
						_productTest.getProductSerialNumber(),
						_productTest.getProductDatecode());

				if (null != _product) {
					boolean _isNeedDispensation = _serviceInterface
							.isNeedDispensation(_product);
					if (true == _isNeedDispensation) {
						String _alertMsg = "Une demande de dérogation doit être faite pour le produit "
								+ _product.getProductConf().getReference()
								+ " "
								+ _product.getDatecode()
								+ " "
								+ _product.getSerialNumber() + ".";

						JOptionPane.showMessageDialog(null, _alertMsg,
								"Information", JOptionPane.INFORMATION_MESSAGE);

					} else {
						// No dispensation alert
					}
				} else {
					// Unknown product
				}
			}
		} else {
			// No dispensation alert
		}
	}
}
