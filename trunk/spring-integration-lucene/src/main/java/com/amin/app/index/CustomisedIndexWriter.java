package com.amin.app.index;

import java.io.IOException;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.LockObtainFailedException;

public class CustomisedIndexWriter extends IndexWriter {
	
	public CustomisedIndexWriter(Directory d, Analyzer a, MaxFieldLength mfl) throws CorruptIndexException, LockObtainFailedException, IOException {
		super(d, a, mfl);
	}

	public void initialise() throws Exception {
		if (IndexWriter.isLocked(getDirectory())) {
			IndexWriter.unlock(getDirectory());
		}
	}
}
