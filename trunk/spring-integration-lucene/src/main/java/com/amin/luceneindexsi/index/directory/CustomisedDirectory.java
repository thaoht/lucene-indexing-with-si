package com.amin.luceneindexsi.index.directory;

import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

public class CustomisedDirectory  {

    /**
     * Customised Directory method for returning a FSDirectory. This method checks whether the directory is locked,
     * if it then the directory is unlocked and returned to the caller.
     * @param path Location of the directory on the filesystem
     * @return FSDirectory An FSDirectory is returned
     * @throws Exception Exception is thrown if any errors occured while trying to obtain a directory
     */
	public static Directory getDirectory(String path) throws Exception {
		FSDirectory fsDirectory = FSDirectory.getDirectory(path);
		if (IndexWriter.isLocked(fsDirectory)) {
			IndexWriter.unlock(fsDirectory);
		}
		return fsDirectory;
	}
}
