package com.amin.luceneindexsi.index.integration;

import static org.junit.Assert.assertEquals;

import java.net.URI;
import java.util.concurrent.TimeUnit;

import org.apache.lucene.index.IndexReader;
import org.apache.lucene.store.Directory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.amin.luceneindexsi.index.integration.gateway.IndexingGateway;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"classpath:LuceneIndexingSpringIntegrationTest-context.xml"})
public class LuceneIndexingSpringIntegrationTest {
	
	@Autowired private IndexingGateway indexingGateway;
	private static final String CURRENT_WORKING_DIR = System.getProperty("user.dir");
	
	@Autowired private Directory directory;
	
	@Test
	public void testCanPerformIndexing() throws Exception {
		URI uri = new URI("file://"+CURRENT_WORKING_DIR+"/test-data/springsecurity.pdf");
		indexingGateway.processFileForIndex(uri);
		TimeUnit.SECONDS.sleep(10);
		
		IndexReader indexReader = IndexReader.open(directory);
		int numDocs = indexReader.numDocs();
		assertEquals(1, numDocs);
		
	}
	
}
