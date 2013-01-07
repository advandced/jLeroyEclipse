package fr.la.jproductbase.metier;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.net.URL;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import fr.la.configfilereader.ConfigFileReader;
import fr.la.configfilereader.ConfigFileReaderException;
import java.io.Serializable;

/*
 * Source : http://christophej.developpez.com/tutoriel/java/singleton/multithread/
 */
public class JProductBaseParameters implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Instance de JProductParameters
	private static JProductBaseParameters instance;

	private ConfigFileReader configFileReader;

	// Version
	private String version;

	// Redirection des sorties standard et d'erreurs vers des fichiers
	private boolean log;

	// Répertoire distant contenant la dernière mise à jour 
	private String updateRemoteDirectory;

	// Répertoire du poste client contenant les fichiers de données prêts à être
	// transférés au serveur.
	private String dataFilesDirectory;
	// Répertoire du serveur contenant les fichiers de données en attente
	// d'intégration.
	private String dataFilesServerDirectory;
	// Répertoire du serveur d'archivage des fichiers de données intégrés.
	private String dataFilesServerDirectoryArchive;
	// Emails des destinataires des alertes d'intégration des fichiers de
	// données.
	private List<String> dataEmails = new ArrayList<String>();

	// Répertoire du poste client contenant les rapports des testeurs prêts à
	// être transférés au serveur.
	private String reportFilesDirectory;
	// Répertoire du serveur contenant les rapports des testeurs en attente
	// d'intégration.
	private String reportFilesServerDirectory;
	// Répertoire du serveur d'archivage des rapports des testeurs intégrés.
	private String reportFilesServerDirectoryArchive;
	// Emails des destinataires des alertes d'intégration des rapports des
	// testeurs.
	private List<String> reportEmails = new ArrayList<String>();

	// Fréquence de transfert des fichiers vers le serveurs.
	private int clientServerTransferFrequency;

	// Données de connexion à la base de données.
	private String bddAddress;
	private String bddPort;
	private String bddUser;
	private String bddPwd;
	private int bddTimeout;

	// Type de pose client.
	private String stationType;

	// Port série de la douchette.
	private String ScannerSerialPort;

	// Conservation des rapports de ConfigFileReader.
	private boolean reportConfigFileReader;

	// Nom du fichier contenant le pré-rapport du testeur.
	private String preTesterReportXmlFileName;

	// Chemin et nom du programme qui transforme les rapports des testeurs
	// dynamique en rapport de testeur "standard".
	private String importDynTesterData;
	// Répertoire où traiter le rapport du testeur dynamique (décomposition en
	// rapports XML et reconstruction de l'archive "standard").
	private String importDynTesterDataDirectory;

	private JProductBaseParameters() throws ConfigFileReaderException,
			IOException {

		// this.configFileReader = new ConfigFileReader(false,
		// "jProductBase.conf");

		String _webInf = "";
		// className= package/subPackage/subsubPackage/../ClassName.class
		String className = JProductBaseParameters.class.getName().replaceAll(
				"\\.", "/")
				+ ".class";
		// Use the ClassLoader to find the absolute path to this file.
		URL classPath = JProductBaseParameters.class.getClassLoader()
				.getResource(className);

		File f = new File(classPath.getPath());
		while (f != null && !f.getName().equals("WEB-INF")) {
			f = f.getParentFile();
		}
		// if the root is reached without finding the WEB-INF directory
		// WEB-INF will equal null
		if (f == null) {
			this.configFileReader = new ConfigFileReader(false,
					"jProductBase.conf");
		} else {
			_webInf = f.getPath().replace("%20", " ");
			this.configFileReader = new ConfigFileReader(false, _webInf
					+ File.separator // + "classes" + File.separator
					+ "jProductBase.conf");
		}

		// Read parameters
		// Version
		this.version = configFileReader.getParamValue("version");
		
		// Log
		String _log = configFileReader.getParamValue("log");
		this.log = false;
		if (null != _log) {
			if (_log.trim().equals("enable")) {
				this.log = true;
			} else {
				this.log = false;
			}
		} else {
			this.log = false;
		}
		if (true == this.log) {
			File _logFile = new File("jProductBase.log");
			OutputStream _consoleOutput = new FileOutputStream(_logFile);
			PrintStream _consoleStream = new PrintStream(_consoleOutput);
			System.setOut(_consoleStream);

			// Redirection des erreur dans le même flux afin de respecter
			// l'ordre d'apparition des messages.
			System.setErr(_consoleStream);
		} else {
			// Sortie standard et d'erreur classiques
		}

		// Update
		this.updateRemoteDirectory = configFileReader
				.getParamValue("updateRemoteDirectory");

		// Data files
		this.dataFilesDirectory = configFileReader
				.getParamValue("dataFilesDirectory");
		
		File _dataFilesDirectory = new File(dataFilesDirectory);
		if (false == _dataFilesDirectory.exists()) {
			_dataFilesDirectory.mkdirs();
		} else {
			// Directory already exist
		}
		this.dataFilesServerDirectory = configFileReader
				.getParamValue("dataFilesServerDirectory");
		this.dataFilesServerDirectoryArchive = configFileReader
				.getParamValue("dataFilesServerDirectoryArchive");
		@SuppressWarnings("unused")
		String _dataEmails = configFileReader.getParamValue("dataEmails");
		// TODO this.dataEmails;

		// Report files
		this.reportFilesDirectory = configFileReader
				.getParamValue("reportFilesDirectory");
		File _reportFilesDirectory = new File(reportFilesDirectory);
		if (false == _reportFilesDirectory.exists()) {
			_reportFilesDirectory.mkdirs();
		} else {
			// Directory already exist
		}
		this.reportFilesServerDirectory = configFileReader
				.getParamValue("reportFilesServerDirectory");
		this.reportFilesServerDirectoryArchive = configFileReader
				.getParamValue("reportFilesServerDirectoryArchive");
		@SuppressWarnings("unused")
		String _reportEmails = configFileReader.getParamValue("reportEmails");
		// TODO this.reportEmails;

		// Transfer
		String _transferFrequency = configFileReader
				.getParamValue("clientServerTransferFrequency");
		if (null != _transferFrequency) {
			this.clientServerTransferFrequency = 1000 * Integer
					.valueOf(_transferFrequency);
		} else {
			this.clientServerTransferFrequency = 0;
		}

		// Database connection
		this.bddAddress = configFileReader.getParamValue("bddAddress");
		this.bddPort = configFileReader.getParamValue("bddPort");
		this.bddUser = configFileReader.getParamValue("bddUser");
		this.bddPwd = configFileReader.getParamValue("bddPwd");
		if (null != configFileReader.getParamValue("bddTimeout")) {
			this.bddTimeout = Integer.valueOf(configFileReader
					.getParamValue("bddTimeout"));
		} else {
			this.bddTimeout = 0;
		}

		// Station
		this.stationType = configFileReader.getParamValue("stationType");

		// Scanner
		this.ScannerSerialPort = configFileReader
				.getParamValue("ScannerSerialPort");

		// ConfigFileReader
		String _reportConfigFileReader = configFileReader
				.getParamValue("reportConfigFileReader");
		this.reportConfigFileReader = true;
		if (null != _reportConfigFileReader) {
			if (_reportConfigFileReader.trim().equals("enable")) {
				this.reportConfigFileReader = true;
			} else {
				this.reportConfigFileReader = false;
			}
		} else {
			this.reportConfigFileReader = true;
		}
		if (false == this.reportConfigFileReader) {
			this.removeFiles();
		}

		// Pre-tester report
		this.preTesterReportXmlFileName = configFileReader
				.getParamValue("preTesterReportXmlFileName");

		// importDynTesteurData
		this.importDynTesterData = configFileReader
				.getParamValue("importDynTesterData");
		this.importDynTesterDataDirectory = configFileReader
				.getParamValue("importDynTesterDataDirectory");
	}

	public static synchronized JProductBaseParameters getInstance()
			throws ConfigFileReaderException, IOException {
		if (null == JProductBaseParameters.instance) {
			JProductBaseParameters.instance = new JProductBaseParameters();
		}

		return JProductBaseParameters.instance;
	}

	/**
	 * @return the version
	 */
	public String getVersion() {
		return version;
	}

	/**
	 * @return the log
	 */
	public boolean isLog() {
		return log;
	}

	/**
	 * @return the updateRemoteDirectory
	 */
	public String getUpdateRemoteDirectory() {
		return updateRemoteDirectory;
	}

	/**
	 * @return the dataFilesDirectory
	 */
	public String getDataFilesDirectory() {
		return dataFilesDirectory;
	}

	/**
	 * @return the dataFilesServerDirectory
	 */
	public String getDataFilesServerDirectory() {
		return dataFilesServerDirectory;
	}

	/**
	 * @return the dataFilesServerDirectoryArchive
	 */
	public String getDataFilesServerDirectoryArchive() {
		return dataFilesServerDirectoryArchive;
	}

	/**
	 * @return the dataEmails
	 */
	public List<String> getDataEmails() {
		return dataEmails;
	}

	/**
	 * @return the reportFilesDirectory
	 */
	public String getReportFilesDirectory() {
		return reportFilesDirectory;
	}

	/**
	 * @return the reportFilesServerDirectory
	 */
	public String getReportFilesServerDirectory() {
		return reportFilesServerDirectory;
	}

	/**
	 * @return the reportFilesServerDirectoryArchive
	 */
	public String getReportFilesServerDirectoryArchive() {
		return reportFilesServerDirectoryArchive;
	}

	/**
	 * @return the reportEmails
	 */
	public List<String> getReportEmails() {
		return reportEmails;
	}

	/**
	 * @return the clientServerTransferFrequency
	 */
	public int getClientServerTransferFrequency() {
		return clientServerTransferFrequency;
	}

	/**
	 * @return the bddAddress
	 */
	public String getBddAddress() {
		return bddAddress;
	}

	/**
	 * @return the bddPort
	 */
	public String getBddPort() {
		return bddPort;
	}

	/**
	 * @return the bddUser
	 */
	public String getBddUser() {
		return bddUser;
	}

	/**
	 * @return the bddPwd
	 */
	public String getBddPwd() {
		return bddPwd;
	}

	/**
	 * @return the bddTimeout
	 */
	public int getBddTimeout() {
		return bddTimeout;
	}

	/**
	 * @return the stationType
	 */
	public String getStationType() {
		return stationType;
	}

	/**
	 * @return the scannerSerialPort
	 */
	public String getScannerSerialPort() {
		return ScannerSerialPort;
	}

	/**
	 * @return the reportConfigFileReader
	 */
	public boolean getReportConfigFileReader() {
		return reportConfigFileReader;
	}

	/**
	 * @return the preTesterReportXmlFileName
	 */
	public String getPreTesterReportXmlFileName() {
		return preTesterReportXmlFileName;
	}

	/**
	 * @return the configFileReader
	 */
	public ConfigFileReader getConfigFileReader() {
		return configFileReader;
	}

	/**
	 * @return the importDynTesterData
	 */
	public String getImportDynTesterData() {
		return importDynTesterData;
	}

	/**
	 * @return the importDynTesterDataDirectory
	 */
	public String getImportDynTesterDataDirectory() {
		return importDynTesterDataDirectory;
	}

	/*
	 * Remove previous configFileReader
	 */
	private void removeFiles() {
		File _directory = new File(".");
		FilenameFilter _fileFilter = new ConfigFileReaderReportFilenameFilter();
		String[] _filesName = _directory.list(_fileFilter);

		File _file = null;
		for (String _fileName : _filesName) {
			// Remove file
			_file = new File(_fileName);
			boolean _isDeleted = _file.delete();
			if (false == _isDeleted) {
				// File not deleted but it's not very important
			} else {
				// File deleted
			}
		}
	}
}
