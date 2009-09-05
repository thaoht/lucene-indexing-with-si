package com.amin.luceneindexsi.index.integration.gateway;

import java.net.URI;

import org.springframework.integration.annotation.Gateway;

public interface IndexingGateway {

    /**
     * Gateway for lucene indexing using Spring integration. You will
     * require using this interface via Spring to access the SI flow.
     * @param uriOfFileToBeIndexed  Location of file or directory
     */
	@Gateway(requestChannel = "filesToBeIndexedChannel")
	void processFileForIndex(URI uriOfFileToBeIndexed);
}
