package com.amin.luceneindexsi.index.content.extraction;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URI;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.apache.lucene.document.DateTools;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.DateTools.Resolution;
import org.apache.lucene.document.Field.Store;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.Parser;
import org.apache.tika.sax.BodyContentHandler;
import org.springframework.util.Assert;
import org.xml.sax.ContentHandler;


public class TikaContentHandler implements ContentExtractionHandler {
	
	private Set<String> textualMetadataFields = new HashSet<String>();
	
	public TikaContentHandler() {
		textualMetadataFields.add(Metadata.TITLE); 
		textualMetadataFields.add(Metadata.AUTHOR); 
		textualMetadataFields.add(Metadata.COMMENTS); 
		textualMetadataFields.add(Metadata.KEYWORDS); 
		textualMetadataFields.add(Metadata.DESCRIPTION); 
		textualMetadataFields.add(Metadata.SUBJECT);
	}
	
	@Override
	public Document getDocument(URI uri) throws Exception {
		Assert.notNull(uri, "uri cannot be null");
		File fileToBeIndexed = new File(uri);
		Metadata metadata = new Metadata();
		metadata.set(Metadata.RESOURCE_NAME_KEY,  fileToBeIndexed.getCanonicalPath());
		metadata.set(Metadata.LAST_MODIFIED, DateTools.dateToString(new Date(fileToBeIndexed.lastModified()), Resolution.DAY));
	
		
		InputStream inputStream = new FileInputStream(fileToBeIndexed);
		ContentHandler contentHandler = new BodyContentHandler();
		Parser parser = new AutoDetectParser();
		parser.parse(inputStream, contentHandler, metadata);
		String content = contentHandler.toString();

		Document document = new Document();
		document.add(new Field("body", content, Store.COMPRESS, Field.Index.ANALYZED));
		
		for(String name: metadata.names()) {
			String value = metadata.get(name);
			document.add(new Field(name, value,Field.Store.YES, Field.Index.NOT_ANALYZED, Field.TermVector.YES));
		}
	
		return document;
	}


}
