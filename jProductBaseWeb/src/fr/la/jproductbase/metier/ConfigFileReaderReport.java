package fr.la.jproductbase.metier;

import java.io.File;
import java.io.FilenameFilter;

import fr.la.configfilereader.ConfigFileReader;
import java.io.Serializable;

public class ConfigFileReaderReport implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public ConfigFileReaderReport(ConfigFileReader configFileReader) {
		if (null != configFileReader) {
			String _reportConfigFileReader = configFileReader.getParamValue("reportConfigFileReader");
			if (_reportConfigFileReader.trim().equals("disable")) {
				// Delete all configFileReader report
				this.removeFiles();
			}
		}
	}

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
