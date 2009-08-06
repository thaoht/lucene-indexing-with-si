package com.amin.app.index.directory;

import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

public class CustomisedDirectory  {

	public static Directory getDirectory(String path) throws Exception {
		FSDirectory fsDirectory = FSDirectory.getDirectory(path);;
		if (IndexWriter.isLocked(fsDirectory)) {
			IndexWriter.unlock(fsDirectory);
		}
		return fsDirectory;
	}
}
