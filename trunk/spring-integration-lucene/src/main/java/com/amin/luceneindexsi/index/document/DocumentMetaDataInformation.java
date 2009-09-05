package com.amin.luceneindexsi.index.document;

import org.apache.lucene.document.Document;

public interface DocumentMetaDataInformation {


    IndexOperation indexOperationFor(Document d);


    public static enum IndexOperation {
        ADD, DELETE, UPDATE
    }

}
