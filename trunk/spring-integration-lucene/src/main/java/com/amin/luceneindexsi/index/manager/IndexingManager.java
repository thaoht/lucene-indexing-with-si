package com.amin.luceneindexsi.index.manager;

import org.apache.log4j.Logger;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.Term;
import org.apache.lucene.document.Document;
import org.apache.tika.metadata.Metadata;

public class IndexingManager {
	
	private final IndexWriter indexWriter;
	private final Logger LOGGER = Logger.getLogger(getClass()); 
	
	public IndexingManager(final IndexWriter indexWriter) {
		this.indexWriter = indexWriter;
	}
	
	public void add(Document document)  {
		try {
			LOGGER.debug("adding document to index :" + Thread.currentThread().getName());
			indexWriter.addDocument(document);
			indexWriter.commit();
		} catch (Exception e) {
			throw new IllegalStateException(e);
		}
	}

    /**
     * This updates the index. An update operation results in
     * a deletion of the original Document followed by adding the new Document
     * @param document for indexing
     */
	public void update(Document document) {
		try {
            
			LOGGER.debug("updating document in index :" + Thread.currentThread().getName());
			Term t = new Term(Metadata.RESOURCE_NAME_KEY, document.get(Metadata.RESOURCE_NAME_KEY));
			indexWriter.deleteDocuments(t);	
			indexWriter.addDocument(document);
			indexWriter.commit();
		} catch (Exception e) {
			throw new IllegalStateException(e);
		}
	}
}
