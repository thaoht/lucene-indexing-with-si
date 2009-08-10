package com.amin.luceneindexsi.index.manager;

import static org.junit.Assert.assertEquals;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.Field.Index;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.Term;
import org.apache.lucene.index.IndexWriter.MaxFieldLength;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;
import org.apache.tika.metadata.Metadata;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.amin.luceneindexsi.index.manager.IndexingManager;
import com.amin.luceneindexsi.index.work.WorkItem;
import com.amin.luceneindexsi.index.work.WorkItem.WorkItemEvent;

public class IndexingManagerTest {

	private IndexWriter indexWriter;
	private IndexingManager underTest;
	private Directory directory;
	private Document document;
	
	@Before
	public void setUp() throws Exception {
		directory = new RAMDirectory();
		indexWriter = new IndexWriter(directory,new StandardAnalyzer(), MaxFieldLength.UNLIMITED);
		underTest = new IndexingManager(indexWriter);
	}
	
	@Before
	public void setUpData() {
		document = new Document();
		Field f= new Field(Metadata.RESOURCE_NAME_KEY, "/test/pdfFile.pdf", Store.NO, Index.NOT_ANALYZED);
		document.add(f);
	}
	
	@Test
	public void testCanAddDocumentUsingIndexManager() throws Exception{
		final WorkItem workItem  = new WorkItem(WorkItemEvent.ADD, document);
		underTest.add(workItem);
		IndexReader indexReader = IndexReader.open(directory);
		Term t = new Term(Metadata.RESOURCE_NAME_KEY, document.get(Metadata.RESOURCE_NAME_KEY));
		int docFreq = indexReader.docFreq(t);
		assertEquals(1, docFreq);
	}
	
	@Test
	public void testCanUpdateDocumentUsingIndexManager() throws Exception{
		indexWriter.addDocument(document);
		indexWriter.commit();
		
		final WorkItem workItemUpdate  = new WorkItem(WorkItemEvent.UPDATE, document);
		underTest.update(workItemUpdate);
		
		IndexReader indexReader = IndexReader.open(directory);
		assertEquals(1,indexReader.numDocs());
	}
	
	@After
	public void tearDown() throws Exception{
		if (IndexWriter.isLocked(directory)) {
			IndexWriter.unlock(directory);
		}
		IndexReader indexReader = IndexReader.open(directory);
		Term t = new Term(Metadata.RESOURCE_NAME_KEY, document.get(Metadata.RESOURCE_NAME_KEY));
		indexReader.deleteDocuments(t);
		indexReader.flush();
		indexReader.close();
	}
}
