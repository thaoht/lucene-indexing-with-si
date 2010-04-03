package com.amin.luceneindexsi.index.repository;

import com.amin.luceneindexsi.index.entity.recognition.NamedDocument;

/**
 * NamedEntityRecognitionRepository
 *
 * @author: Amin Mohammed-Coleman
 * @since: Apr 3, 2010
 */
public interface NamedEntityRecognitionRepository {

    NamedDocument persist(NamedDocument namedDocument);

    NamedDocument findBy(Long id);

    void remove(NamedDocument document);

    NamedDocument update(NamedDocument namedDocument);
}
