package fr.la.jproductbase.presentation;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;

import fr.la.configfilereader.ConfigFileReaderException;
import fr.la.jproductbase.metier.JProductBaseParameters;

public class ClientFileTransfer extends Thread {
	private static boolean suspendRequest = false;

	private boolean suspended = false;
	private boolean stop = false;

	private File reportFilesDirectory;
	private File reportFilesServerDirectory;
	private File dataFilesDirectory;
	private File dataFilesServerDirectory;
	private int sleepDelay;

	public ClientFileTransfer() throws ConfigFileReaderException, IOException,
			ClientFileTransferException {
		JProductBaseParameters _jProductBaseParameters = JProductBaseParameters
				.getInstance();

		// Read transfer parameters
		// Default sleep delay
		int _defaultSleepDelay = 1000;
		this.sleepDelay = _defaultSleepDelay;
		
		// Report file directory
		String _reportFilesDirectory = _jProductBaseParameters
				.getReportFilesDirectory();
		if (null != _reportFilesDirectory) {
			this.reportFilesDirectory = new File(_reportFilesDirectory);
		} else {
			throw new ClientFileTransferException(
					"Répertoire source des rapports des testeurs non défini.");
		}

		// Server report file directory
		String _reportFilesServerDirectory = _jProductBaseParameters
				.getReportFilesServerDirectory();
		if (null != _reportFilesServerDirectory) {
			this.reportFilesServerDirectory = new File(
					_reportFilesServerDirectory);
		} else {
			throw new ClientFileTransferException(
					"Répertoire de destination des rapports des testeurs non défini.");
		}

		// Data file directory
		String _dataFilesDirectory = _jProductBaseParameters
				.getDataFilesDirectory();
		if (null != _dataFilesDirectory) {
			this.dataFilesDirectory = new File(_dataFilesDirectory);
		} else {
			throw new ClientFileTransferException(
					"Répertoire source des fichiers de données non défini.");
		}

		// Server data file directory
		String _dataFilesServerDirectory = _jProductBaseParameters
				.getDataFilesServerDirectory();
		if (null != _dataFilesServerDirectory) {
			this.dataFilesServerDirectory = new File(_dataFilesServerDirectory);
		} else {
			throw new ClientFileTransferException(
					"Répertoire de destination des fichiers de données non défini.");
		}

		// Sleep delay
		this.sleepDelay = _jProductBaseParameters
				.getClientServerTransferFrequency();
	}

	@Override
	public final void run() {
		boolean _running = true;

		while (true == _running) {
			if (false == this.suspended) {
				if (false == ClientFileTransfer.suspendRequest) {
					// Transfére tous les fichiers de reportFilesDirectory vers
					// reportFilesServerDirectory
					this.fileTransfert(this.reportFilesDirectory,
							this.reportFilesServerDirectory);

					// Transfére tous les fichiers de dataFilesDirectory vers
					// dataFilesServerDirectory
					this.fileTransfert(this.dataFilesDirectory,
							this.dataFilesServerDirectory);
				} else {
					this.suspended = true;
				}
			} else {
				// Traitement suspendu
				if (false == ClientFileTransfer.suspendRequest) {
					this.suspended = false;
				}
			}

			if (false == this.stop) {
				// Pause
				try {
					sleep(this.sleepDelay);
				} catch (InterruptedException e) {
					this.stop = true;
				}
			} else {
				_running = false;
			}
		}
	}

	private void fileTransfert(File srcDirectory, File dstDirectory) {
		if ((null != srcDirectory) && (null != dstDirectory)) {
			// Liste des fichiers
			FilenameFilter _srcFileFilter = new FileFilter();
			String[] _srcFilesName = srcDirectory.list(_srcFileFilter);

			// Traitement des fichiers
			String _dstFileName = "";
			File _srcFile = null;
			File _dstFile = null;
			for (String _srcfileName : _srcFilesName) {
				_dstFileName = dstDirectory + File.separator + _srcfileName;
				_dstFile = new File(_dstFileName);
				if (!_dstFile.exists()) {
					// Archivage
					_srcFile = new File(srcDirectory.getPath() + File.separator
							+ _srcfileName);
					boolean _renameSuccess = _srcFile.renameTo(_dstFile);
					if (false == _renameSuccess) {
						// Rename failed
					} else {
						// Rename success
					}
				} else {
					// Fichier déjà présent sur le serveur.
				}
			}
		} else {
			// Répertoire source ou destination inconnu
		}
	}

	public static void setSuspendRequest(boolean suspend) {
		ClientFileTransfer.suspendRequest = suspend;
	}

	public boolean isSuspend() {
		return this.suspended;
	}
}
