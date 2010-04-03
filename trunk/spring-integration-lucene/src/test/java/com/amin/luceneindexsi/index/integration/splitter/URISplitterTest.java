package com.amin.luceneindexsi.index.integration.splitter;

import java.net.URI;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.Test;

public class URISplitterTest {
	
	private URISplitter underTest;
	
	private static final String CURRENT_WORKING_DIR = System.getProperty("user.dir");
	
	@Before
	public void init() {
		underTest = new URISplitter();
	}
	
	@Test
	public void canRetriveListOfURIsForSingleFile() throws Exception {
		URI uri = new URI("file://"+CURRENT_WORKING_DIR+"/test-data/springsecurity.pdf");
		List<URI> list = underTest.splitUri(uri);
		assertNotNull(list);
		assertEquals(1,list.size());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void exceptionThrownIfUriDoesNotExist() throws Exception {
		URI uri = new URI("file://"+CURRENT_WORKING_DIR+"/test-data/xyz.xtf");
		underTest.splitUri(uri);
	}
	
}
