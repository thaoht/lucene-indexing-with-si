package com.amin.luceneindexsi.index;

import java.io.IOException;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.store.Directory;


public class CustomisedIndexWriter extends IndexWriter {

    public CustomisedIndexWriter(Directory d, Analyzer a, MaxFieldLength mfl) throws IOException {
        super(d, a, mfl);
    }

    /**
     * If directory is locked then an attempt is made to unlock it before
     * usage.
     *
     * @throws Exception Exception thrown if directory cannot be locked.
     */
    public void startUp() throws Exception {
        if (IndexWriter.isLocked(getDirectory())) {
            IndexWriter.unlock(getDirectory());
        }
    }

    public void destroy() throws Exception {
        if (IndexWriter.isLocked(getDirectory())) {
            IndexWriter.unlock(getDirectory());
        }   
    }

}
