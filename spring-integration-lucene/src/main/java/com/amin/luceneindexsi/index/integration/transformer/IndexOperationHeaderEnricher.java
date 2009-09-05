package com.amin.luceneindexsi.index.integration.transformer;

import com.amin.luceneindexsi.index.document.DocumentMetaDataInformation;
import org.apache.lucene.document.Document;
import org.springframework.integration.core.Message;
import org.springframework.integration.transformer.HeaderEnricher;
import org.springframework.integration.transformer.Transformer;

import java.util.Collections;

public class IndexOperationHeaderEnricher implements Transformer{

    public static final String INDEX_OPERATION_HEADER_NAME = "index.operation";

    private final DocumentMetaDataInformation documentMetaDataInformation;

    public IndexOperationHeaderEnricher(DocumentMetaDataInformation documentMetaDataInformation) {
        this.documentMetaDataInformation = documentMetaDataInformation;
    }


    @Override
    public Message<?> transform(Message<?> m) {
        DocumentMetaDataInformation.IndexOperation indexOperation = documentMetaDataInformation.indexOperationFor((Document) m.getPayload());
        Message<?> message = new HeaderEnricher(Collections.singletonMap(INDEX_OPERATION_HEADER_NAME,
                (Object) indexOperation.name().toLowerCase())).transform(m);
        return message;
    }
}
