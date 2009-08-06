package com.amin.app.index.integration.splitter;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;

public class URISplitter {

	private final Logger  LOGGER = Logger.getLogger(getClass().getName()); 
	
	public List<URI> splitUri(URI uri) throws Exception {
		LOGGER.debug("inside splitter uri " + uri.toString());
		List<URI> listOfUris = new ArrayList<URI>();
		File fileToBeIndexed = new File(uri);
		if (!fileToBeIndexed.exists()) {
			throw new IllegalArgumentException("uri does not exist");
		}
		if (fileToBeIndexed.isDirectory()) {
			List<File> fileListing = getFileListing(fileToBeIndexed);
			for (File file : fileListing) {
				listOfUris.add(file.toURI());
			}
		} else {
			listOfUris.add(fileToBeIndexed.toURI());
		}
		return listOfUris;
	}

	private List<File> getFileListing(File aStartingDir) throws FileNotFoundException {
		validateDirectory(aStartingDir);
		List<File> result = getFileListingNoSort(aStartingDir);
		Collections.sort(result);
		return result;
	}

	private List<File> getFileListingNoSort(File aStartingDir) throws FileNotFoundException {
		List<File> result = new ArrayList<File>();
		File[] filesAndDirs = aStartingDir.listFiles();
		List<File> filesDirs = Arrays.asList(filesAndDirs);
		for (File file : filesDirs) {
			if (!file.isDirectory()) {
				result.add(file);
			}
			if (!file.isFile()) {
				List<File> deeperList = getFileListingNoSort(file);
				result.addAll(deeperList);
			}
		}
		return result;
	}

	private void validateDirectory(File aDirectory) throws FileNotFoundException {
		if (aDirectory == null) {
			throw new IllegalArgumentException("Directory should not be null.");
		}
		if (!aDirectory.exists()) {
			throw new FileNotFoundException("Directory does not exist: "+ aDirectory);
		}
		if (!aDirectory.isDirectory()) {
			throw new IllegalArgumentException("Is not a directory: "+ aDirectory);
		}
		if (!aDirectory.canRead()) {
			throw new IllegalArgumentException("Directory cannot be read: "+ aDirectory);
		}
	}
}
