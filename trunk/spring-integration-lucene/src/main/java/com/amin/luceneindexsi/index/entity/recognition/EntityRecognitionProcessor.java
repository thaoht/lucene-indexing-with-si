package com.amin.luceneindexsi.index.entity.recognition;

import org.apache.lucene.document.Document;

/**
 * EntityRecognitionProcessor
 *
 * @author: Amin Mohammed-Coleman
 * @since: Apr 3, 2010
 */
public interface EntityRecognitionProcessor {

    public NamedDocument create(Document document);

}
