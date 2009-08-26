package com.amin.luceneindexsi.index.integration.transformer;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.index.TermDocs;
import org.apache.lucene.store.Directory;
import org.apache.tika.metadata.Metadata;
import org.springframework.util.Assert;
import org.springframework.util.StopWatch;

import com.amin.luceneindexsi.index.work.WorkItem;
import com.amin.luceneindexsi.index.work.WorkItem.WorkItemEvent;

public class DocumentToWorkItemTransformer {

	private final Directory directory;
	private final Logger  LOGGER = Logger.getLogger(getClass()); 
	
	
	public DocumentToWorkItemTransformer(final Directory directory) {
		this.directory = directory;
	}

    /**
     * 
     * @param document
     * @return
     */

 	public WorkItem transformDocumentToWorkItem(Document document) {
    	Assert.notNull(document, "document cannot be null");
		LOGGER.debug("inside " + getClass().getName() );
        StopWatch stopWatch = new StopWatch("transformDocumentToWorkItem");
        stopWatch.start();
		IndexReader indexReader = null;
		WorkItemEvent workItemEvent = null;
		try {
			indexReader = IndexReader.open(directory);
			Term t = new Term(Metadata.RESOURCE_NAME_KEY, document.get(Metadata.RESOURCE_NAME_KEY));
			TermDocs termDocs = indexReader.termDocs(t);
			boolean docAlreadyExists = false;
			while(termDocs.next()) {
				if (termDocs.freq() >= 1) {
					docAlreadyExists = true;
				}
			}
			if (docAlreadyExists) {
				workItemEvent = WorkItemEvent.UPDATE;
			} else {
				workItemEvent = WorkItemEvent.ADD;
			}
		} catch (Exception e) {
			throw new IllegalStateException(e);
		} finally {
			try {
                if (indexReader != null) {
				    indexReader.close();
                }
			} catch (IOException e) {
				throw new IllegalStateException(e);
			}
		}
        stopWatch.stop();
        LOGGER.debug("Total time taken to convert document to workItem '" + stopWatch.getTotalTimeMillis() + "' ms");
		return new WorkItem(workItemEvent, document);
	}
}
