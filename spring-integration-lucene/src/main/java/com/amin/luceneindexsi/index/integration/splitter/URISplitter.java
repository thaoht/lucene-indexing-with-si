package com.amin.luceneindexsi.index.integration.splitter;

import java.io.File;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;

/**
 * 
 * @author amin mohammed-coleman
 * This class is responsible for checking whether the URI provided is a directory or not.
 * If it is a directory then it recursively gets all the files and creates a list of URIs
 *
 */
public class URISplitter {

	private final Logger  LOGGER = Logger.getLogger(getClass().getName());

    /**
     * This the splitter method which is responsible for breaking a URI into
     * smaller URI if the URI is a directory.
     * @param uri Source URI
     * @return A list of individual file uris
     * @throws Exception
     */
	public List<URI> splitUri(URI uri) throws Exception {
		LOGGER.debug("inside splitter uri " + uri.toString());
		List<URI> listOfUris = new ArrayList<URI>();
		File fileToBeIndexed = new File(uri);
        validateUriExists(fileToBeIndexed);
        if (fileToBeIndexed.isDirectory()) {
            listOfUris.addAll(toURI(fileToBeIndexed.listFiles()));
		} else {
			listOfUris.add(fileToBeIndexed.toURI());
		}
		return listOfUris;
	}

    private Collection<? extends URI> toURI(File[] files) {
        List<URI> toReturn = new ArrayList<URI>();
        for (File file : files) {
            toReturn.add(file.toURI());
        }
        return toReturn;
    }

    private void validateUriExists(File fileToBeIndexed) {
        if (!fileToBeIndexed.exists()) {
            throw new IllegalArgumentException("uri does not exist");
        }
    }
}
