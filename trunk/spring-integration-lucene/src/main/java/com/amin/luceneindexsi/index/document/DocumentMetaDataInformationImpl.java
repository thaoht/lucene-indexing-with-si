package com.amin.luceneindexsi.index.document;

import org.apache.lucene.document.Document;
import org.apache.lucene.store.Directory;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.index.TermDocs;
import org.apache.tika.metadata.Metadata;
import com.amin.luceneindexsi.index.directory.DirectoryTemplate;
import static com.amin.luceneindexsi.index.directory.DirectoryTemplate.WithItemReader;

import java.io.IOException;

public class DocumentMetaDataInformationImpl implements DocumentMetaDataInformation {
    private DirectoryTemplate<IndexOperation> template;
    private Directory directory;

    public DocumentMetaDataInformationImpl(Directory directory) {
        this.directory = directory;
        template = new DirectoryTemplate<IndexOperation>();
    }

    @Override
    public IndexOperation indexOperationFor(final Document document) {
        return template.open(directory, new WithItemReader<IndexOperation>() {
            @Override
            public IndexOperation execute(IndexReader indexReader) throws IOException {
                Term t = new Term(Metadata.RESOURCE_NAME_KEY, document.get(Metadata.RESOURCE_NAME_KEY));
                TermDocs termDocs = indexReader.termDocs(t);
                return updateOrAdd(termDocs);
            }
        });
    }


    private IndexOperation updateOrAdd(TermDocs termDocs) throws IOException {
        boolean docAlreadyExists = false;
        while(termDocs.next() && !docAlreadyExists) {
            if (termDocs.freq() >= 1) {
                docAlreadyExists = true;
            }
        }
        IndexOperation operation = IndexOperation.ADD;
        if (docAlreadyExists) {
            operation = IndexOperation.UPDATE;
        }
        return operation;
    }
}
