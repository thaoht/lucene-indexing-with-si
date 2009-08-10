package com.amin.luceneindexsi.index.content.extraction;

import static org.junit.Assert.assertNotNull;

import java.net.URI;
import java.util.List;

import org.apache.lucene.document.Document;
import org.junit.Before;
import org.junit.Test;

import com.amin.luceneindexsi.index.content.extraction.TikaContentHandler;

public class TikaContentHandlerTest {

	private TikaContentHandler underTest;
	private static final String CURRENT_WORKING_DIR = System.getProperty("user.dir");
	
	@Before
	public void setUp() {
		underTest = new TikaContentHandler();
	}
	
	@Test
	public void testCanGetFileFromURI() throws Exception {
		URI uri = new URI("file://"+CURRENT_WORKING_DIR+"/test-data/springsecurity.pdf");
		Document luceneDocument = underTest.getDocument(uri);
		assertNotNull(luceneDocument);
		List<?> fields = luceneDocument.getFields();
		assertNotNull(fields);
	}
}