package com.amin.app.index.integration;

import java.net.URI;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.amin.app.index.integration.gateway.IndexingGateway;

public class LuceneIndexingIntegrationDemo {
	
	public static void main (String[] args) throws Exception {
		if (args.length == 0) {
			System.err.println("usage: specify uri of file or directory");
			System.exit(1);
		}
		ApplicationContext ctx = new ClassPathXmlApplicationContext(new String[] {"classpath:context-index-integration.xml","classpath:context-index-jms.xml", "classpath:context-index-writer.xml","classpath:context-index-directory.xml"});
		URI uri = new URI(args[0]);
		IndexingGateway indexingGateway = (IndexingGateway) ctx.getBean("indexingGateway");
		indexingGateway.processFileForIndex(uri);
	}
}
