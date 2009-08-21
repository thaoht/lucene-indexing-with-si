package com.amin.luceneindexsi.index.integration.transformer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriter.MaxFieldLength;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.LockObtainFailedException;
import org.apache.lucene.store.RAMDirectory;
import org.apache.tika.metadata.Metadata;
import org.junit.Before;
import org.junit.Test;

import com.amin.luceneindexsi.index.work.WorkItem;
import com.amin.luceneindexsi.index.work.WorkItem.WorkItemEvent;

public class DocumentToWorkItemTransformerTest {
	
	private Directory directory;
	private Analyzer analyzer;
	private IndexWriter indexWriter;
	private DocumentToWorkItemTransformer underTest;
	private Document document;
	
	
	@Before
	public void setUp() {
		directory = new RAMDirectory();
		analyzer = new StandardAnalyzer();
		try {
			indexWriter = new IndexWriter(directory,analyzer, MaxFieldLength.UNLIMITED);
		} catch (CorruptIndexException e) {
			e.printStackTrace();
		} catch (LockObtainFailedException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		underTest = new DocumentToWorkItemTransformer(directory);
	}
	
	@Before
	public void setUpDataInDirectory() {
		document = new Document();
		Field f= new Field(Metadata.RESOURCE_NAME_KEY, "/Users/xyz/test/pdfFile.pdf", Store.YES, org.apache.lucene.document.Field.Index.NOT_ANALYZED);
		document.add(f);
	}
	
	@Test
	public void testCanCreateUpdateWorkItemUsingTransformer() throws Exception{
		indexWriter.addDocument(document);
		indexWriter.commit();
		WorkItem workItem = underTest.transformDocumentToWorkItem(document);
		assertNotNull(workItem);
		assertEquals(WorkItemEvent.UPDATE, workItem.getWorkItemEvent());
	}
	
	@Test
	public void testCanCreateAddWorkItemUsingTransformer() throws Exception{
		document.removeField(Metadata.RESOURCE_NAME_KEY);
		Field f= new Field(Metadata.RESOURCE_NAME_KEY, "/Users/xyz/test/xyz.pdf", Store.YES, org.apache.lucene.document.Field.Index.NOT_ANALYZED);
		document.add(f);
		
		WorkItem workItem = underTest.transformDocumentToWorkItem(document);
		assertNotNull(workItem);
		assertEquals(WorkItemEvent.ADD, workItem.getWorkItemEvent());
	}
}
