package com.amin.app.index.integration.splitter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.net.URI;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class URISplitterTest {
	
	private URISplitter underTest;
	
	private static final String CURRENT_WORKING_DIR = System.getProperty("user.dir");
	
	@Before
	public void setUp() {
		underTest = new URISplitter();
	}
	
	@Test
	public void testCanRetriveListOfURIsForDirectory() throws Exception {
		URI uri = new URI("file://"+CURRENT_WORKING_DIR+"/test-data");
		List<URI> listOfUris = underTest.splitUri(uri);
		assertNotNull(listOfUris);
		for (URI uri2 : listOfUris) {
			File f = new File(uri2);
			assertFalse(f.isDirectory());
		}
	}
	
	@Test
	public void testCanRetriveListOfURIsForSingleFile() throws Exception {
		URI uri = new URI("file://"+CURRENT_WORKING_DIR+"/test-data/springsecurity.pdf");
		List<URI> list = underTest.splitUri(uri);
		assertNotNull(list);
		assertEquals(1,list.size());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testExceptionThrownIfUriDoesNotExist() throws Exception {
		URI uri = new URI("file://"+CURRENT_WORKING_DIR+"/test-data/xyz.xtf");
		underTest.splitUri(uri);
	}
	
}
