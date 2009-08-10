package com.amin.luceneindexsi.index.integration.gateway;

import java.net.URI;

import org.springframework.integration.annotation.Gateway;

public interface IndexingGateway {
	
	@Gateway(requestChannel = "filesToBeIndexedInChannel")
	public void processFileForIndex(URI uriOfFileToBeIndexed);
}
