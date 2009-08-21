package com.amin.luceneindexsi.index.content.extraction;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URI;
import java.util.Date;
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

    private static final String BODY_FIELD = "body";
    private static final String FILE_NAME_FIELD = "filename";

    /**
     * Converts a URI into a Lucene Document using Apache Tika
     * @param uri URI of file to be converted
     * @return A Lucene Document
     * @throws Exception  If failure of converting occurs
     */
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
		document.add(new Field(BODY_FIELD, content, Store.COMPRESS, Field.Index.ANALYZED));
		
		for(String name: metadata.names()) {
			String value = metadata.get(name);
			document.add(new Field(name, value,Field.Store.YES, Field.Index.NOT_ANALYZED, Field.TermVector.YES));
		}
	    document.add(new Field(FILE_NAME_FIELD, fileToBeIndexed.getCanonicalPath(),Field.Store.YES, Field.Index.NOT_ANALYZED));
		return document;
	}


}
