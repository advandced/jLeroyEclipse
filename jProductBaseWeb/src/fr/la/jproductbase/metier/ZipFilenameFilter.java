package fr.la.jproductbase.metier;

import java.io.File;
import java.io.FilenameFilter;

public class ZipFilenameFilter implements FilenameFilter {

	@Override
	public boolean accept(File dir, String name) {
		return (name.endsWith(".zip"));
	}
}
