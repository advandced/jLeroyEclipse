package fr.la.jproductbase.metier;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

public class FilesTools {
	/**
	 * Copy a file.
	 * 
	 * @param source
	 *            Source file.
	 * 
	 * @param dest
	 *            Destination file.
	 */
	public static void copyFile(File source, File dest) {
		FileChannel _in = null;
		FileChannel _out = null;

		try {
			// Source
			_in = new FileInputStream(source).getChannel();
			try {
				// Destination
				_out = new FileOutputStream(dest).getChannel();

				// Copie de in vers out
				_in.transferTo(0, _in.size(), _out);
			} catch (IOException e) {
				System.out.println("Erreur de copie de '" + source + "' vers '"
						+ dest + "'.");
			} finally {
				if (_out != null) {
					_out.close();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (_in != null) {
				try {
					_in.close();
				} catch (IOException e) {
					System.out.println("File not closed.");
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * Copy a file.
	 * 
	 * @param sourceName
	 *            Source file name.
	 * 
	 * @param destName
	 *            Destination file name.
	 */
	public static void copyFile(String sourceName, String destName) {
		File _sourceFile = new File(sourceName);

		if (_sourceFile.isFile()) {
			FileChannel _sourceFileChannel = null;
			FileChannel _destFileChannel = null;
			try {
				/* Init */
				_sourceFileChannel = new FileInputStream(sourceName)
						.getChannel();
				_destFileChannel = new FileOutputStream(destName).getChannel();

				/* Copy from source to dest */
				_sourceFileChannel.transferTo(0, _sourceFileChannel.size(),
						_destFileChannel);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				/* Close channels */
				if (_sourceFileChannel != null) {
					try {
						_sourceFileChannel.close();
					} catch (IOException e) {
					}
				}
				if (_destFileChannel != null) {
					try {
						_destFileChannel.close();
					} catch (IOException e) {
					}
				}
			}
		} else {
			if (_sourceFile.isDirectory()) {
				/* Create destination directory */
				File _outFile = new File(destName);

				_outFile.mkdir();
			} else {
				/* Unknown file type */
			}
		}
	}
}
