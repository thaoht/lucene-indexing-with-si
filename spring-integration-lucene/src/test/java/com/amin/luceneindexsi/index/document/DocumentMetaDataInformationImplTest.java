package com.amin.luceneindexsi.index.document;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;
import org.apache.lucene.store.LockObtainFailedException;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.tika.metadata.Metadata;
import org.jmock.Mockery;

import java.io.IOException;

public class DocumentMetaDataInformationImplTest {

    private DocumentMetaDataInformationImpl underTest;
    private Directory directory;

    private Mockery mockery;
    private StandardAnalyzer analyzer;
    private IndexWriter indexWriter;
    private Document document;

    @Before
    public void createSUT(){
		directory = new RAMDirectory();
		analyzer = new StandardAnalyzer();
		try {
			indexWriter = new IndexWriter(directory,analyzer, IndexWriter.MaxFieldLength.UNLIMITED);
		} catch (CorruptIndexException e) {
			e.printStackTrace();
		} catch (LockObtainFailedException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

        underTest = new DocumentMetaDataInformationImpl(directory);
    }

    @Before
	public void setUpDataInDirectory() {
		document = new Document();
		Field f= new Field(Metadata.RESOURCE_NAME_KEY, "/Users/xyz/test/pdfFile.pdf", Field.Store.YES, org.apache.lucene.document.Field.Index.NOT_ANALYZED);
		document.add(f);
	}

	@Test
	public void whenItemAlreadyAddedOperationShouldBeUpdate() throws Exception{
		indexWriter.addDocument(document);
		indexWriter.commit();
        DocumentMetaDataInformation.IndexOperation indexOperation = underTest.indexOperationFor(document);
        assertNotNull(indexOperation);
		assertEquals(DocumentMetaDataInformation.IndexOperation.UPDATE,
                indexOperation);
	}

	@Test
	public void whenNoMatchingItemInDirectoryOperationShouldBeAdd() throws Exception{
		document.removeField(Metadata.RESOURCE_NAME_KEY);
		Field f= new Field(Metadata.RESOURCE_NAME_KEY, "/Users/xyz/test/xyz.pdf", Field.Store.YES, org.apache.lucene.document.Field.Index.NOT_ANALYZED);
		document.add(f);

		DocumentMetaDataInformation.IndexOperation indexOperation = underTest.indexOperationFor(document);
        assertNotNull(indexOperation);
		assertEquals(DocumentMetaDataInformation.IndexOperation.ADD,
                indexOperation);
	}

}
