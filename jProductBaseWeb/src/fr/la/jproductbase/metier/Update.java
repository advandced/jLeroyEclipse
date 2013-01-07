package fr.la.jproductbase.metier;

import java.io.FileWriter;
import java.io.IOException;

public class Update {
	public static String launch(String version) {
		String _newVersion = version;

		Float _version = Float.parseFloat(version);

		// Update 0.10
		if (_version > 0) {

		} else {
			// First update
			Update.updateV0_10();
			_newVersion = "0.10";
		}

		return _newVersion;
	}

	private static void updateV0_10() {
		// Add version in config file
		FileWriter _configFileWriter = null;
		String _version = "[version]0.10";
		try {
			_configFileWriter = new FileWriter("jProductBase.conf", true);
			_configFileWriter.write(_version, 0, _version.length());
		} catch (IOException e) {
			System.out.println("Erreur lors de la mise à jour.");
			e.printStackTrace();
		} finally {
			if (_configFileWriter != null) {
				try {
					_configFileWriter.close();
				} catch (IOException e) {
					System.out.println("Echec de fermeture du fichier.");
				}
			}
		}
	}
}
