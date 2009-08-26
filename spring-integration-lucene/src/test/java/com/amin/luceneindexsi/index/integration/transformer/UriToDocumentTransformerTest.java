package com.amin.luceneindexsi.index.integration.transformer;

import java.net.URI;

import org.apache.lucene.document.Document;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.amin.luceneindexsi.index.content.extraction.ContentExtractionHandler;
import com.amin.mock.annotation.Mocked;
import com.amin.mock.annotation.MockeryContext;
import com.amin.mock.jmock.runner.JMockRunner;

@RunWith(JMockRunner.class)
public class UriToDocumentTransformerTest {
	
	@MockeryContext private Mockery mockery;
	@Mocked private ContentExtractionHandler contentExtractionHandler;
	private UriToDocumentTransformer underTest;
    private static final String CURRENT_WORKING_DIR = System.getProperty("user.dir");
	
	@Before
	public void setUp() {                                                  
		underTest = new UriToDocumentTransformer(contentExtractionHandler);
	}
	
	@Test
	public void testCanTransformUriToDocument() throws Exception {
		final URI uri = new URI("file://"+CURRENT_WORKING_DIR+"/test-data/springsecurity.pdf");
		final Document document = new Document();
		mockery.checking(new Expectations() {{
			oneOf(contentExtractionHandler).getDocument(uri); will(returnValue(document));
		}});
		underTest.transformUriToDocument(uri);
	}
	
}
