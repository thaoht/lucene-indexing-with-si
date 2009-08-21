package com.amin.luceneindexsi.index.manager;

import org.apache.log4j.Logger;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.Term;
import org.apache.tika.metadata.Metadata;

import com.amin.luceneindexsi.index.work.WorkItem;

public class IndexingManager {
	
	private final IndexWriter indexWriter;
	private final Logger  LOGGER = Logger.getLogger(getClass()); 
	
	public IndexingManager(final IndexWriter indexWriter) {
		this.indexWriter = indexWriter;
	}
	
	public void add(WorkItem workItem)  {
		try {
			LOGGER.debug("adding workitem to index");
			indexWriter.addDocument(workItem.getPayLoad());
			indexWriter.commit();
		} catch (Exception e) {
			throw new IllegalStateException(e);
		}
	}
	
	public void update(WorkItem workItem) {
		try {
            
			LOGGER.debug("updating workitem to index");
			Term t = new Term(Metadata.RESOURCE_NAME_KEY, workItem.getPayLoad().get(Metadata.RESOURCE_NAME_KEY));
			indexWriter.deleteDocuments(t);	
			indexWriter.addDocument(workItem.getPayLoad());
			indexWriter.commit();
		} catch (Exception e) {
			throw new IllegalStateException(e);
		}
	}
}
