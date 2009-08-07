package com.amin.app.index.integration.splitter;

import java.io.File;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.io.FileUtils;
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
			Collection<?> collectionOfFiles = FileUtils.listFiles(fileToBeIndexed, null, true);
			for (Object fileObj : collectionOfFiles) {
				File file = (File)fileObj;
				listOfUris.add(file.toURI());
			}
		} else {
			listOfUris.add(fileToBeIndexed.toURI());
		}
		return listOfUris;
	}
}
