package com.amin.app.index.content.extraction;

import java.net.URI;

import org.apache.lucene.document.Document;

public interface ContentExtractionHandler {
	
	Document getDocument(URI uri) throws Exception;
}
