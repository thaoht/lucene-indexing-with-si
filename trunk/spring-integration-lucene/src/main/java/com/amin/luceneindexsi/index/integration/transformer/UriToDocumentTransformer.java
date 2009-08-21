package com.amin.luceneindexsi.index.integration.transformer;

import java.net.URI;

import org.apache.log4j.Logger;
import org.apache.lucene.document.Document;

import com.amin.luceneindexsi.index.content.extraction.ContentExtractionHandler;

public class UriToDocumentTransformer {

	private final ContentExtractionHandler contentExtractionHandler;
	private final Logger  LOGGER = Logger.getLogger(getClass()); 
	
	public UriToDocumentTransformer(final ContentExtractionHandler contentExtractionHandler) {
		this.contentExtractionHandler = contentExtractionHandler;
	}

    /**
     * Transformer that converts a URI into a Lucene Document.
     * @see {@link com.amin.luceneindexsi.index.content.extraction.ContentExtractionHandler} and implementing classes for how
     * content is extracted.
     * @param uri URI for file to be processed.
     * @return Document A lucene document
     */
	public Document transformUriToDocument(URI uri) {

		LOGGER.debug("inside " + getClass().getName() );
		LOGGER.debug("transforming uri [" + uri.toString() + "] to indexable document");
        
		try {
			return contentExtractionHandler.getDocument(uri);
		} catch (Exception e) {
			throw new IllegalStateException(e);
		}
	}
}
