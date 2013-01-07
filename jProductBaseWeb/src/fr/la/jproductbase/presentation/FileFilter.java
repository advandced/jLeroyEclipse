package fr.la.jproductbase.presentation;

import java.io.File;
import java.io.FilenameFilter;

public class FileFilter implements FilenameFilter {

	@Override
	public boolean accept(File dir, String name) {
		File _file = new File(dir, name);
		return (_file.isFile());
	}
}
