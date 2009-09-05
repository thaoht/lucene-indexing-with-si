package com.amin.luceneindexsi.index.directory;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.store.Directory;

import java.io.IOException;

public final class DirectoryTemplate<T> {

    public T open(final Directory directory, WithItemReader<T> withDirectory){
        IndexReader indexReader = null;
        try{
            if (IndexWriter.isLocked(directory)) {
                IndexWriter.unlock(directory);
            }
            IndexReader indexReader1 = IndexReader.open(directory);
            return withDirectory.execute(indexReader1);
        }catch(Exception e){
            throw new RuntimeException("Error opening indexReader", e);
        }finally {
            if(indexReader != null){
                try {
                    indexReader.close();
                } catch (IOException e) {
                    throw new RuntimeException("Error closing indexReader", e);
                }
            }
        }
    }


    public static interface WithItemReader<T>{
        public T execute(IndexReader indexReader) throws IOException;
    }

}
